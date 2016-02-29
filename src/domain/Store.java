package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import domain.enumerations.Platform;

public class Store {

  private static Store uniqueInstance;
  private Database database;
  List<Game> games;

  private Store() {
    getDatabase();
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

  public static List<Platform> getPlatformValues() {
    return Arrays.asList(Platform.values());
  }

  public Game getGame(String productBarcode) {
    return database.retrieveGame(productBarcode);
  }
}