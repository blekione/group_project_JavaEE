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
import domain.OrderItem;
import domain.ShoppingCart;
import domain.Store;
import domain.enumerations.Platform;
import domain.enumerations.Title;

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
      case "login":
        login(request, response);
        return;
      case "logout":
        logout(request, response);
        return;
      case "signin":
        signin(request, response);
        return;
      case "sign-new-customer":
        signNewCustomer(request, response);
        return;
      case "main":
      default:
        listDiscountGamesMain(request, response);
    }
  }

  private void viewBasket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    List<OrderItem> cartItems = cart.getBasket();
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
      System.out.println("cart is null");
      session.setAttribute("cart", new ShoppingCart());
    }

    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    System.out.println("cart size: " + cart.getSize());

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

    for (Game game : discountedGames) {
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
    session.setAttribute("customer", null);
    response.sendRedirect("store");
  }

  private void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("in signin");
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
