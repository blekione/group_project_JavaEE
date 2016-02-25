<%@ page session="false"  import="domain.Game" %>

<% 
	Game game = (Game)request.getAttribute("game");
	%>
	
	<%= game.getName() %>
</body>
</html>