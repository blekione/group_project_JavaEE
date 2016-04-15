package web_app;

import domain.Database;
import domain.Game;
import domain.Store;
import domain.enumerations.Genre;
import domain.enumerations.Platform;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/marketing")
public class MarketingServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  boolean success = false;
  private HttpSession session;
  private boolean loginFail = false;
  private boolean checkoutPass = false;
  private Database database = null;

  public MarketingServlet() {
    super();
    getDatabase();
  }

  private Database getDatabase() {
    if (database == null) {
      database = new Database();
    }
    return database;
  }

  @Override
  protected void doGet(HttpServletRequest request,
	  HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("success", success);
    session = request.getSession();

    //To check who is logged in as authentication.
    //Customer customer = (Customer) session.getAttribute("customer");
    String action = request.getParameter("action");

    if (action == null) {
      action = "main";
    }
    switch (action) {
      case "newGame":
	showNewGame(request, response);
	return;
      case "newPromo":
	showNewPromo(request, response);
	return;
      case "stats":
	showStoreStats(request, response);
	return;
      case "addNewPromo":
	addNewPromo(request, response);
	return;
      case "addNewGame":
	addNewGame(request, response);
	return;
      default:
	showOptions(request, response);
    }
  }

  private void showOptions(HttpServletRequest request,
	  HttpServletResponse response)
	  throws IOException, ServletException {
    request.getRequestDispatcher("WEB-INF/jsp/view/marketing.jsp")
	    .forward(request, response);
    success = false;
  }

  private void showNewGame(HttpServletRequest request,
	  HttpServletResponse response)
	  throws IOException, ServletException {
    List<Platform> platforms = Store.getPlatformValues();
    List<Genre> genres = Arrays.asList(Genre.values());
    request.setAttribute("platforms", platforms);
    request.setAttribute("genres", genres);

    request.getRequestDispatcher("WEB-INF/jsp/view/newgame.jsp")
	    .forward(request, response);
  }

  private void showNewPromo(HttpServletRequest request,
	  HttpServletResponse response)
	  throws IOException, ServletException {
    List<Game> games = database.retrieveAll();
    request.setAttribute("games", games);
    request.getRequestDispatcher("WEB-INF/jsp/view/newpromo.jsp")
	    .forward(request, response);
  }

  private void showStoreStats(HttpServletRequest request,
	  HttpServletResponse response)
	  throws IOException, ServletException {
    request.getRequestDispatcher("WEB-INF/jsp/view/storestats.jsp")
	    .forward(request, response);
  }

  private void addNewPromo(HttpServletRequest request,
	  HttpServletResponse response)
	  throws IOException, ServletException {
    String barcode = request.getParameter("barcode");
    double discount = Double.parseDouble(request.getParameter("discount"));
    double pointMult = Double.parseDouble(request.getParameter("pointMult"));

    database.addPromotion(barcode, discount, pointMult);
    success = true;

    response.sendRedirect("marketing");
  }

  private void addNewGame(HttpServletRequest request,
	  HttpServletResponse response)
	  throws IOException, ServletException {
    try {
      String barcode = request.getParameter("gameBarcode");
      String name = request.getParameter("gameName");
      String description = request.getParameter("gameDesc");
      Genre genre = Genre.valueOf(request.getParameter("gameGenre"));
      Platform platform = Platform.valueOf(request.getParameter("gamePlatform"));
      double price = Double.parseDouble(request.getParameter("gamePrice"));
      double discount = Double.parseDouble(request.getParameter("gameDiscount"));
      int stock = Integer.parseInt(request.getParameter("gameStock"));
      double pointMult = Double.parseDouble(request.getParameter("gamePointMult"));
      String image = "resources/images/default.jpg";

      Game newGame = new Game(name, description, stock, barcode, price, pointMult,
	      image, image, discount, genre, platform);
      database.persist(newGame);
      success = true;
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      response.sendRedirect("marketing");
    }

  }

  @Override
  protected void doPost(HttpServletRequest request,
	  HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }
}
