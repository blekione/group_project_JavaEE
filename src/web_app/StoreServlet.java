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
		
		String action = request.getParameter("action");
		System.out.println("!!!!!!!!!!!!!! " + action);
        if(action == null)
            action = "main";
        switch(action) {
        	case "XboxOne": 
        		listDiscountGamesPlatform(request, response, action);
        		return;
        	case "Playstation4": 
        		listDiscountGamesPlatform(request, response, action);
        		return;
        	case "main":
        	default:
        		listDiscountGamesMain(request, response);
        }
	}

	private void listDiscountGamesPlatform(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
		
		List<Game> platformGames = store.getPlatformGames(action);
		discountedGames = store.getDiscountedGames(platformGames);
//		String test = "pc";
//	System.out.println(test.substring(0, 4));
		
		request.setAttribute("platformGames", platformGames);
		request.setAttribute("discountedGames", discountedGames);
		
		
		request.setAttribute("platform", action);
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
