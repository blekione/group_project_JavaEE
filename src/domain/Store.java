package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import domain.enumerations.Platform;
import domain.enumerations.Title;

public class Store {

  private static Store uniqueInstance;
  private Database database;
  private List<Game> games;
  private List<Order> orders;
  private List<Customer> customers;

  private Store() {
    getDatabase();
    customers = new ArrayList<>();
    test();
  }

  private void test() {
	  Address testAddress = new Address(22, "Lavender Ave", "Mitcham", "Surrey", "cr4 3rs");
	  customers.add(new Customer("test@test.com", "test", Title.Mr, "Marcin", "Kruglik", testAddress, testAddress, "ABC123456", "07828141565", "128.0.0.1"));
}

public static Store getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new Store();
    }
    return uniqueInstance;
  }
  
  private Database getDatabase() {
    if (database == null) {
      database = new Database();
    }
    return database;
  }

  public List<Game> getDiscountedGames() {
	List<Game> gamesWithDiscount = database.retrieveDiscounts();
	List<Game> gamesSix = new ArrayList<>();
	if (gamesWithDiscount.size() > 6) {
		for (int i =0; i < 6; i++) {
			gamesSix.add(gamesWithDiscount.get(i));
		}
		gamesWithDiscount = gamesSix;
	}
    return gamesWithDiscount;
  }

  public List<Game> getPlatformGames(String platform) { 
    return database.retrievePlatform(platform);
  }


  public Game getGame(String productBarcode) {
    return database.retrieveGame(productBarcode);
  }
  
  /** return types of platforms (Xbox One, PS4 etc.), converting enum types into
   * array list of platform values.
   * @return ArrayList<Platform>
   */
  public static List<Platform> getPlatformValues() {
    return Arrays.asList(Platform.values());
  }
  /** adds new orders to the database
   * 
   * @param order
   */
  public void addOrder(Order order) {
	  orders.add(order);
  }
  /** Checks if customer with such an email exists in database.
   *  If existing returns true, otherwise returns false. 
   * 
   * @param email
   * @return
   */
  public Customer checkCustomer(String email) {
	  for (Customer customer : customers) {
		  if (customer.getEmail().equals(email)) {
			  return customer;
		  }
	  }
	  return null;
  }
  
  public boolean checkCustomerPassword(Customer customer, String password) {
	  if (customer.getPassword().equals(password)) {
		  return true;
	  } else {
		  return false;
	  }  
  }
  
}