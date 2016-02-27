package web_app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Game;
import domain.Store;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/store")
public class StoreServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private Store store;
  private List<Game> discountedGames;

  public StoreServlet() {
    super();
    store = Store.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String action = request.getParameter("action");
//    System.out.println("!!!!!!!!!!!!!! " + action);
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
      case "main":
      default:
	listDiscountGamesMain(request, response);
    }
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
