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
    Store store; 
    List<Game> discountedGames;
	
    public StoreServlet() {
        super();
        store = Store.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		getHtmlHead(request, response);
		writer.append("\n\t\t<p>here is a body of index page<p>");
		listDiscountGames(request, response);
		getHtmlFooter(response);
	}

	private void listDiscountGames(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter writer = response.getWriter();
		
		discountedGames = store.getDiscountedGames();
		
		request.setAttribute("games", discountedGames);
		
		request.getRequestDispatcher("WEB-INF/jsp/view/main.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void getHtmlHead(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		PrintWriter writer =  response.getWriter();
		writer.append("<!doctype html>")
			.append("\n<html>")
			.append("\n\t<head>")
//			.append("\n\t\t<title>E-commerce</title>")
			.append("\n\t\t<link rel=\"stylesheet\" type='text/css'")
			.append(" href=\"" +request.getContextPath() +"/css/bootstrap.min.css\" />")
			.append("\n\t</head>")
			.append("\n\t<body>");
		}
	
	private void getHtmlFooter(HttpServletResponse response) throws IOException {
		PrintWriter writer =  response.getWriter();
		writer.append("\n\t</body>")
			.append("\n</html>");
	}
}
