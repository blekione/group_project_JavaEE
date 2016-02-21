package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.enumerations.Genre;
import domain.enumerations.Platform;

public class Store {

	private static Store uniqueInstance;
	List<Game> games;
	
	private Store() {
		test();
	}
	
	private void test() {
//	method for testing purpose will be deleted when database will be implemented
		Game game1 = new Game("Call of Duty, Black Ops", 
				"one of the best shooters ever blah blah blah",
				20, "5030917085765", 35.00, 1.00, "resources/game0.jpg", 0.00, Genre.Shooter,
				Platform.XboxOne);
		Game game2 = new Game("Minecraft", 
				"why all people love Minceraft? blah blah blah",
				15, "799007825272", 25.00, 1.00, "resources/game0.jpg", 30.00, Genre.Simulator,
				Platform.XboxOne);
		Game game3 = new Game("Tomb Rider", 
				"Lara Croft in filled with action 2nd perspective adventure game",
				20, "662248917689", 39.99, 1.00, "resources/game0.jpg", 15.00, Genre.Action, 
				Platform.XboxOne);
		Game game4 = new Game("Fallout 4",
				"next part of one of the best game sagas. Placed in postnuclear world",
				30, "093155170421", 44.95, 1.00, "resources/game0.jpg", 20.00, Genre.Action,
				Platform.XboxOne);
		Game game5 = new Game("The Witcher 3",
				"best game of 2015. period.",
				45, "883929485123", 39.99, 1.00, "resources/game0.jpg", 20.00, Genre.Action,
				Platform.XboxOne);
		games = Arrays.asList(game1, game2, game3, game4, game5);
	}

	public static Store getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Store();
		}
		return uniqueInstance;
	}

	public List<Game> getDiscountedGames() {
// TODO change it to query database, when applied
		List<Game> discountedGames= new ArrayList<>();
		for (Game game : games) {
			if (game.getDiscount() > 0.00) {
				discountedGames.add(game);
			}
			if (discountedGames.size() == 4) {
				return discountedGames;
			}
		}
		return null;
	}
	
	public static List<Platform> getPlatformValues() {
		return Arrays.asList(Platform.values());
	}
	
}
