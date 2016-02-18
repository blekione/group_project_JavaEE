package web_app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class IndexPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		getHtmlHead(response);
		writer.append("\n\t\t<p>here is a body of index page<p>");
		getHtmlFooter(response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void getHtmlHead(HttpServletResponse response) throws IOException {
		PrintWriter writer =  response.getWriter();
		writer.append("<!doctype html>")
			.append("\n<html>")
			.append("\n\t<head>")
			.append("\n\t\t<title>E-commerce</title>")
			.append("\n\t</head>")
			.append("\n\t<body>");
	}
	
	private void getHtmlFooter(HttpServletResponse response) throws IOException {
		PrintWriter writer =  response.getWriter();
		writer.append("\n\t</body>")
			.append("\n</html>");
	}
}
