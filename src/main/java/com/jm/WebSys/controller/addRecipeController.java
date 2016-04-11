package com.jm.WebSys.controller;

//Importing spring framework
import com.jm.WebSys.DAO.MongoDBRecipeDAO;
import com.jm.WebSys.domain.Encrypter;
import com.jm.WebSys.domain.Recipe;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by James on 17/03/2016.
 */

@Controller
public class addRecipeController {

    @RequestMapping("/addRecipe")
    public String add(Model model,
                      @CookieValue(value = "user") String user,
                      Recipe recipe) {
        //Display User.
        model.addAttribute("name", user);


        if(recipe.getRname() == null ){
            return "addRecipe";
        }

        System.out.println(recipe.getRname());
        System.out.println(recipe.getRdesc());
        System.out.println(recipe.getRingredients());
        System.out.println(recipe.getRhours());
        System.out.println(recipe.getRmins());
        System.out.println(recipe.getRmethod());
        recipe.setCreator(user);
        recipe.setViews(0);

        //get current date time with Date()
        Date date = new Date();
        recipe.setTimecreated(date);

        if (recipe.getRname() != null ) {
            model.addAttribute("success", recipe.getRname() + " has been added!");
        }

        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBRecipeDAO dao = new MongoDBRecipeDAO(mongo);
        dao.createRecipe(recipe);
        mongo.close();

        return "addRecipe";
    }
}
