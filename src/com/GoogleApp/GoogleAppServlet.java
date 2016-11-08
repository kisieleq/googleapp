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
public class GoogleAppServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();				
		ObjectifyService.register(User.class);
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		ObjectifyService.register(User.class);
		
		String userName = req.getParameter("username");
		String userPassword = req.getParameter("password");
		
		

			User user = new User();	
			user.setUserId(UUID.randomUUID().toString());
			user.setUsername(userName);
			user.setPassword(userPassword);
			
			ObjectifyService.ofy().save().entity(user).now();
			
			resp.sendRedirect("/googleapp");

	}
	
}
