package web_app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Address;
import domain.Customer;
import domain.Game;
import domain.Order;
import domain.OrderItem;
import domain.ShoppingCart;
import domain.Store;
import domain.enumerations.Platform;
import domain.enumerations.Title;
import loyalty_scheme.LoyaltyManager;
import payment.BankAccount;
import payment.BankManager;
import supplier_interface.StockManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/store")
public class StoreServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

	private Store store;
	private List<Game> discountedGames;
	boolean success = false;
	private HttpSession session;
	private boolean loginFail = false;
	private boolean checkoutPass = false;
	public StoreServlet() {
		super();
		store = Store.getInstance();
		store.registerObserver(new StockManager());
		store.registerObserver(new LoyaltyManager());
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
		case "login":
			login(request, response);
			return;
		case "logout":
			logout(request,response);
			return;
		case "signin":
			signin(request,response);
			return;
		case "sign-new-customer":
			signNewCustomer(request, response);
			return;
		case "checkout":
			checkout(request,response);
			return;
		case "proceed-payment":
			proceedPayment(request, response);
			return;
		case "main":
		default:
			listDiscountGamesMain(request, response);
		}
	}

	private void proceedPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cardNo = request.getParameter("cardNumber");
		int cvc = 0;
		if (!request.getParameter("cvc").equals("")){
			cvc = Integer.parseInt(request.getParameter("cvc"));
		}
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		double total = cart.getTotal();
		LoyaltyManager lm = new LoyaltyManager();
		Customer customer = (Customer) session.getAttribute("customer");
		if (request.getParameter("redeem") != null && request.getParameter("redeem").equals("true")) {
			total -= lm.getLoyaltyPoints(customer.getLoyaltyAccount()) * 0.01d;
			lm.updateLoyaltyPoints(customer.getLoyaltyAccount(), 0);
		}
		BankManager bm = new BankManager();
		if (bm.verifyPayment(cardNo, cvc) == 1) { // if bank account verified
			if (bm.deductBalance(cardNo, total) == 1) {
				List<OrderItem> cartItems = cart.getBasket();
				Order order = new Order(cartItems, customer);
				customer.createOrderList(); // TODO delete when orders are implemented into database
				customer.addOrder(order);
				store.procedOrder(order);
				store.notifyObservers(order);
				session.setAttribute("cart", null);
				request.setAttribute("paymentStatus", true);
				request.getRequestDispatcher("WEB-INF/jsp/view/orderConfirmation.jsp")
				.forward(request, response);
			} else {
				request.getRequestDispatcher("WEB-INF/jsp/view/orderFailed.jsp")
				.forward(request, response);
			}
		} else {		
			request.setAttribute("loyaltyPoints", lm.getLoyaltyPoints(customer.getLoyaltyAccount()));
			request.setAttribute("total", total);
			request.setAttribute("detailsCheckFail", true);
			request.getRequestDispatcher("WEB-INF/jsp/view/checkout.jsp")
			.forward(request, response);
		}
		
	}

	private void checkout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (session.getAttribute("customer") == null) {
			checkoutPass = false;
		} else {
			ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
			double total = cart.getTotal();
			Customer customer = (Customer) session.getAttribute("customer");
			LoyaltyManager lm = new LoyaltyManager();
			request.setAttribute("loyaltyPoints", lm.getLoyaltyPoints(customer.getLoyaltyAccount()));
			request.setAttribute("total", total);
			checkoutPass = true;
		}
		request.setAttribute("detailsCheckFail", false);
		request.setAttribute("checkoutPass", checkoutPass);
		request.getRequestDispatcher("WEB-INF/jsp/view/checkout.jsp")
		.forward(request, response);
	}

	private void viewBasket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		List<OrderItem> cartItems = cart.getBasket();
		double total = cart.getTotal();
		request.setAttribute("total", total);
		request.setAttribute("cartItems", cartItems);
		request.getRequestDispatcher("WEB-INF/jsp/view/basket.jsp")
		.forward(request, response);
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
			session.setAttribute("cart", new ShoppingCart());
		}

		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");

		if (cart.findItem(gameBarcode) != null) {
			cart.findItem(gameBarcode).setQuantity(
					cart.findItem(gameBarcode).getQuantity() + quantity);
		} else {
			cart.addNewItem(new OrderItem(quantity, store.getGame(gameBarcode)));
		}
		success = true; // to display message about placing item in basket
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
		success = false;// to display message about placing item in basket only once
	}

	private void listDiscountGamesMain(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		discountedGames = store.getDiscountedGames();
		List<Platform> platforms = Store.getPlatformValues();
		request.setAttribute("loginFail", loginFail);
		request.setAttribute("games", discountedGames);
		this.getServletContext().setAttribute("platforms", platforms);
		request.getRequestDispatcher("WEB-INF/jsp/view/main.jsp")
		.forward(request, response);
		loginFail = false;
	}

  // Correct Login: andrew@rewy.co::test or test@test.com::test
  
  private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String email = request.getParameter("email");
    String password = request.getParameter("pass");
    Customer customer = store.checkCustomer(email);
    if (customer == null) {
      loginFail = true;
      session.setAttribute("customer", null);
    } else if (store.checkCustomerPassword(customer, password)) {
      session.setAttribute("customer", customer);
    } else {
      loginFail = true;
      session.setAttribute("customer", null);
    }
    response.sendRedirect("store");
  }

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		session.invalidate();
		response.sendRedirect("store");
	}

	private void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("titles", Arrays.asList(Title.values()));
		request.getRequestDispatcher("WEB-INF/jsp/view/sign.jsp")
		.forward(request, response);
	}


	private void signNewCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Address address = new Address();
		address.setHouseNumber(request.getParameter("houseNumber"));
		address.setStreet(request.getParameter("street"));
		address.setCity(request.getParameter("city"));
		address.setCounty(request.getParameter("county"));
		address.setPostcode(request.getParameter("postcode"));

		Customer customer = new Customer();
		customer.setTitle(Title.valueOf(request.getParameter("title")));
		customer.setFirstName(request.getParameter("firstName"));
		customer.setSecondName(request.getParameter("lastName"));
		customer.setEmail(request.getParameter("email"));
		customer.setPassword(request.getParameter("password"));
		customer.setTelephoneNumber(request.getParameter("telephone"));
		customer.setLoyaltyAccount(request.getParameter("loyaltyAcc"));
		customer.setAddress(address);

		store.addCustomer(customer);
		session.setAttribute("customer", customer);
		response.sendRedirect("store");
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}	