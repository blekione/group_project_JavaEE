package web_app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Game;
import domain.OrderItem;
import domain.ShoppingCart;
import domain.Store;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/store")
public class StoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Store store;
	private List<Game> discountedGames;
	boolean success = false;
	HttpSession session;

	public StoreServlet() {
		super();
		store = Store.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("success", success);
		session = request.getSession();
		
		String action = request.getParameter("action");
		
		if (action == null) {
			action = "main";
		}
		switch (action) {
		case "category":
			String platform = request.getParameter("platform");
			listGamesForPlatform(request, response, platform);
			return;
		case "product":
			String productBarcode = request.getParameter("barcode");
			displayProductDetails(request, response, productBarcode);
			return;
		case "addToBasket":
			addToBasket(request, response);
			return;
		case "viewBasket":
			viewBasket(request, response);
			return;
		case "main":
		default:
			listDiscountGamesMain(request, response);
		}
	}

	private void viewBasket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/view/basket.jsp")
		.forward(request, response);
//		request.getSession().invalidate();
	}

	private void addToBasket(HttpServletRequest request, HttpServletResponse response) throws IOException {
				
		String gameBarcode = "";
		int quantity = 0;
		
		try {
			gameBarcode = request.getParameter("barcode");
			quantity = Integer.parseInt(request.getParameter("quantity"));
		} catch (Exception e) {
			response.sendRedirect("store");
			return;
		}		
		
		if (session.getAttribute("cart") == null) {
			System.out.println("cart is null");
			session.setAttribute("cart", new ShoppingCart());
		}
		
		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		System.out.println("cart size: " + cart.getSize());
		
		if (cart.findItem(gameBarcode) != null) {
			cart.findItem(gameBarcode).setQuantity(
					cart.findItem(gameBarcode).getQuantity() + quantity);
		} else {
			cart.addNewItem(new OrderItem(quantity, store.getGame(gameBarcode)));
		}
		success = true;
		response.sendRedirect("store?action=category&platform=" + store.getGame(gameBarcode).getPlatform());
	}

	private void displayProductDetails(HttpServletRequest request, HttpServletResponse response,
			String productBarcode) throws ServletException, IOException {

		Game game = store.getGame(productBarcode);
		request.setAttribute("game", game);
		request.getRequestDispatcher("WEB-INF/jsp/view/product.jsp")
		.forward(request, response);
	}

	private void listGamesForPlatform(HttpServletRequest request, HttpServletResponse response, String platform) throws ServletException, IOException {

		List<Game> platformGames = store.getPlatformGames(platform);
		discountedGames = store.getDiscountedGames();

		List<Game> platformDiscounts = new ArrayList<>();

		for (Game game: discountedGames) {
			if (game.getPlatform().toString().equals(platform)) {
				platformDiscounts.add(game);
			}
		}

		request.setAttribute("platformGames", platformGames);
		request.setAttribute("discountedGames", platformDiscounts);

		request.setAttribute("platform", platform);
		request.getRequestDispatcher("WEB-INF/jsp/view/platform.jsp")
		.forward(request, response);
		success = false;
	}

	private void listDiscountGamesMain(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		discountedGames = store.getDiscountedGames();

		request.setAttribute("games", discountedGames);

		request.getRequestDispatcher("WEB-INF/jsp/view/main.jsp")
		.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}