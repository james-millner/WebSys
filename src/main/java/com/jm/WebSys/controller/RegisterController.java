//Package info.
package com.jm.WebSys.controller;

//Import models being used. 
import com.jm.WebSys.DAO.MongoDBUserDAO;
import com.jm.WebSys.converter.UserConverter;
import com.jm.WebSys.domain.User;
import com.jm.WebSys.domain.Encrypter;

//Importing spring framework
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

//Importing mongo tools.
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
/**
 * Created by James on 03/03/2016.
 *
 * Register Controller
 *
 * This controller handles the registration process to sign up and use the application. It creates users with a combination of forms
 * and Mongo Access Objects.
 *
 */

@Controller
public class RegisterController {
	
	//Registration method.
	@RequestMapping("/register")
    public String register(@ModelAttribute("userModel")
                           @Valid User userDetails,
                           BindingResult bindingResult,
                           HttpServletResponse response) {
    	
    	//Just reached the page, if null stop.
    	if(userDetails.getFname() == null) {
    		//Just reached the page. Show the registration area.
    		return "register";
    	} else {
            if (bindingResult.hasErrors()) {
                return "register";
            } else {

                //Make an encryption object with the users password.
                Encrypter ec = new Encrypter(userDetails.getPassword());
                String ecPass = ec.encrypt();

                //MongoClient
                MongoClient mongo = new MongoClient("localhost", 27017);
                MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
                DBCollection table = userDAO.db;

                //Check the username doesn't already exist.
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("username", userDetails.getUsername());

                DBCursor cursor = table.find(searchQuery);

                while (cursor.hasNext()) {
                    //Success
                    UserConverter userConverter = new UserConverter();
                    User user = userConverter.toUser(cursor.next());
                    String name =  user.getUsername();
                    return "redirect:signInError?name=" + name;
                }

                userDetails.setPassword(ecPass);
                MongoDBUserDAO dao = new MongoDBUserDAO(mongo);
                dao.createUser(userDetails);
                mongo.close();

                Cookie cookie = new Cookie("user", userDetails.getUsername());
                cookie.setMaxAge(10000);
                response.addCookie(cookie);

                return "redirect:homepage";
            }
        }

   }
    //If the username is taken the user is displayed an error screen with the taken username.
    @RequestMapping("/signInError")
    public String error(Model model,
                        @RequestParam("name") String name) {
        model.addAttribute("name", name);
        return "signInUsernameError";
    }



	

}

