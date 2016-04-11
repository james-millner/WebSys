//Package info.
package com.jm.WebSys.controller;

//Import models being used.
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

    	System.out.println(userDetails.getUsername() + " - " + userDetails.getPassword());

		//Creates a Encryption object that encrypts the password.
		Encrypter e = new Encrypter(userDetails.getPassword());
		String dPass = e.encrypt();

    	// Since 2.10.0, uses MongoClient
    	MongoClient mongo = new MongoClient( "localhost" , 27017 );

    	DB db = mongo.getDB("WebSys");

    	DBCollection table = db.getCollection("users");
			//Old Encryption
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.put("username", userDetails.getUsername());
    	searchQuery.put("password", dPass);

    	DBCursor cursor = table.find(searchQuery);
		Encrypter disp = new Encrypter();
    	if (cursor.hasNext()) {
    		//Success
			UserConverter userConverter = new UserConverter();
			User pass = userConverter.toUser(cursor.next());
			Cookie cookie = new Cookie("user", pass.getUsername());
			cookie.setMaxAge(10000);
			response.addCookie(cookie);
			return "redirect:homepage";
    	}

    	System.out.println("not recognised");
		model.addAttribute("error", "Username or password incorrect. Please try again.");
		mongo.close();

    	return "signIn";
    	}
    }
    



}
