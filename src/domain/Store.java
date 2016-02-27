package domain;

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

  public List<Game> getDiscountedGames(List<Game> gamesList) {
    return database.retrieveDiscounts();
  }

  public List<Game> getPlatformGames(String platform) {
    return database.retrievePlatform(platform);
  }

  public static List<Platform> getPlatformValues() {
    return Arrays.asList(Platform.values());
  }

  public Game getGame(String productBarcode) {
// TODO change into database query when database is implemented
    for (Game game : games) {
      if (game.getBarcodeGS1().equals(productBarcode)) {
	System.out.println(game.getBarcodeGS1().getClass());
	return game;
      }
    }
    return null;
  }
}