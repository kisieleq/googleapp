package com.GoogleApp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.*;
import com.googlecode.objectify.ObjectifyService;
import com.GoogleApp.User;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();				
		ObjectifyService.register(User.class);
		
		out.println("<head>"
				+ "<style>"
				+ "body {"
			
				+ " background: #76b852;"
				+ "background: -webkit-linear-gradient(right, #76b852, #8DC26F);"
				+ "background: -moz-linear-gradient(right, #76b852, #8DC26F);"
				+ "background: -o-linear-gradient(right, #76b852, #8DC26F);"
				+ "background: linear-gradient(to left, #76b852, #8DC26F);"
				+ "font-family: 'Roboto', sans-serif;"
				+ "-webkit-font-smoothing: antialiased;"
				+ "-moz-osx-font-smoothing: grayscale;"
				+ "}"
								
				+ ".listHeader"
				+ "{ font-weight: bold; font-size: 24px; color: #000000; }"
				+ ".backBtn"
				+ "{ text-decoration: none; border: 1px solid black; background-color: white; color: black; padding: 6px; border-radius: 6px; margin-top: 20px; margin-bottom: 20px }"
				+ "</style>"
				+ "</head>");
		
		out.println("<body>");
		resp.getWriter().println("<div class='listHeader'><center>List of registered people:</center></div>" + "<br> <br>");
		
		List<User> allUsers = ofy().load().type(User.class).list();

		int userIndex = 1;
		for(User element : allUsers)
		{
			if(element != null)
			{
				resp.getWriter().println("<center>User " + userIndex + ": " + element.getUsername() + "</center><br>");
				resp.getWriter().println("<center>Password: " + element.getPassword() + "</center><br> <br>");
			}
			userIndex++;
		}
		out.println("<center><a href='/index.html' class='backBtn'>Back to main page</a></center>");
		out.println("</body>");
		
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		ObjectifyService.register(User.class);
		
		String userName = req.getParameter("username");
		String userPassword = req.getParameter("password");
		String userEmail = req.getParameter("email");
		

			User user = new User();	
			user.setUserId(UUID.randomUUID().toString());
			user.setUsername(userName);
			user.setEmail(userEmail);
			user.setPassword(userPassword);
			
			
			ObjectifyService.ofy().save().entity(user).now();
			
			resp.sendRedirect("/register");

	}
	
}
