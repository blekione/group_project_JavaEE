package web_app;

import domain.Customer;
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

    if (session.getAttribute("customer") != null) {
      Customer customer = (Customer) session.getAttribute("customer");
      if (!customer.getEmail().equals("andrew@rewy.co")) {
        response.sendRedirect("store");
        return;
      }
    } else {
      response.sendRedirect("store");
      return;
    }

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
      case "listGames":
        showGameList(request, response);
        return;
      case "editGame":
        showEditGame(request, response);
        return;
      case "updateGame":
        updateGame(request, response);
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

  private void showGameList(HttpServletRequest request,
          HttpServletResponse response)
          throws IOException, ServletException {
    List<Game> games = database.retrieveAll();
    request.setAttribute("games", games);
    request.setAttribute("edit", false);
    request.getRequestDispatcher("WEB-INF/jsp/view/editgame.jsp")
            .forward(request, response);
  }
  
  private void showEditGame(HttpServletRequest request,
          HttpServletResponse response)
          throws IOException, ServletException {
    String barcode = request.getParameter("barcode");
    Game game = database.retrieveGame(barcode);
    List<Platform> platforms = Store.getPlatformValues();
    List<Genre> genres = Arrays.asList(Genre.values());
    request.setAttribute("platforms", platforms);
    request.setAttribute("genres", genres);
    request.setAttribute("game", game);
    request.setAttribute("edit", true);
    request.getRequestDispatcher("WEB-INF/jsp/view/editgame.jsp")
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
  
  private void updateGame(HttpServletRequest request,
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
      database.updateGame(newGame);
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
