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
 *
 * Add Recipe Controller.
 *
 * This controller handles adding a new recipe into the system. It uses a combination of Cookies and a Form to build recipe data.
 *
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

        recipe.setCreator(user);
        recipe.setViews(0);
        //get current date time with Date()
        Date date = new Date();
        recipe.setTimecreated(date);

        if (recipe.getRname() != null ) {
            model.addAttribute("success", "true");
            model.addAttribute("recipeName", recipe.getRname());
        }

        //MongoClient and Recipe Data Access Object.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBRecipeDAO dao = new MongoDBRecipeDAO(mongo);
        dao.createRecipe(recipe);
        mongo.close();

        return "addRecipe";
    }
}
