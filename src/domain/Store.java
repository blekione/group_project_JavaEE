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

  public List<Game> getDiscountedGames() {
    return database.retrieveDiscounts();
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