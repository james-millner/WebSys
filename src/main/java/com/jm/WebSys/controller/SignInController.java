package com.jm.WebSys.controller;

import com.jm.WebSys.DAO.MongoDBUserDAO;
import com.jm.WebSys.converter.UserConverter;
import com.jm.WebSys.domain.Encrypter;
import com.jm.WebSys.domain.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by James on 03/03/2016.
 *
 * Sign-in Controller
 *
 * This controller handles the entry to the application. It provides a basic sign in form for the user to enter their details.
 * With a combination of Mongo Access objects and Cookies it provides entry into the system.
 */
@Controller
public class SignInController {

    
    @RequestMapping("/signin")
    public String signin(Model model,
    					User userDetails,
						HttpServletResponse response) {
 	  	    	
    	if(userDetails.getUsername() == null) {
    		return "signIn";
    	} else {


		//Creates a Encryption object that encrypts the password.
		Encrypter e = new Encrypter(userDetails.getPassword());
		String dPass = e.encrypt();

		//MongoClient and User Data Access Object.
    	MongoClient mongo = new MongoClient( "localhost" , 27017 );
		MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
    	DBCollection table = userDAO.db;

		//Search for the specific username / password combo.
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.put("username", userDetails.getUsername());
    	searchQuery.put("password", dPass);

    	DBCursor cursor = table.find(searchQuery);
    	if (cursor.hasNext()) {
    		//Success, convert the cursor to user object to set as Cookie Value.
			UserConverter userConverter = new UserConverter();
			User pass = userConverter.toUser(cursor.next());
			Cookie cookie = new Cookie("user", pass.getUsername());
			cookie.setMaxAge(10000);
			response.addCookie(cookie);
			//Sign the user in.
			return "redirect:homepage";
    	}
		//User not recognised.
		model.addAttribute("error", "Username or password incorrect. Please try again.");
		mongo.close();

    	return "signIn";
    	}
    }
    



}
