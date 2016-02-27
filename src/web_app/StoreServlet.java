package web_app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Game;
import domain.Store;

@WebServlet("/store")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Store store; 
    private List<Game> discountedGames;
	
    public StoreServlet() {
        super();
        store = Store.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productBarcode;
		String action = request.getParameter("action");
        if(action == null)
            action = "main";
        switch(action) {
        	case "category": 
        		String platform = request.getParameter("platform");
        		listGamesForPlatform(request, response, platform);
        		return;
        	case "product":
        		productBarcode = request.getParameter("barcode");
        		displayProductDetails(request,response, productBarcode);
        		return;
        	case "addToBasket":
        		productBarcode = request.getParameter("barcode");
        		addToBasket(request, response, productBarcode);
        		return;
        	case "main":
        	default:
        		listDiscountGamesMain(request, response);
        }
	}

	private void addToBasket(HttpServletRequest request, HttpServletResponse response, String productBarcode) throws ServletException, IOException {
		
		System.out.println("in add to basket");
		
		this.listGamesForPlatform(request, response, "XboxOne");
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
		discountedGames = store.getDiscountedGames(platformGames);
		
		request.setAttribute("platformGames", platformGames);
		request.setAttribute("discountedGames", discountedGames);
		
		
		request.setAttribute("platform", platform);
		request.getRequestDispatcher("WEB-INF/jsp/view/platform.jsp")
			.forward(request, response);
	}

	private void listDiscountGamesMain(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		discountedGames = store.getDiscountedGames(null);
		
		request.setAttribute("games", discountedGames);
		
		request.getRequestDispatcher("WEB-INF/jsp/view/main.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
