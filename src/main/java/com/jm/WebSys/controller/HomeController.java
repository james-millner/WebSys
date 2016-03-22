package com.jm.WebSys.controller;

import com.jm.WebSys.domain.Recipe;
import com.jm.WebSys.DAO.MongoDBRecipeDAO;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 09/03/2016.
 */
@Controller
public class HomeController {

    @RequestMapping("/homepage")
    public String home(Model model,
                       @RequestParam("name") String name) {
        //Display User.
        model.addAttribute("name", name);
        // Since 2.10.0, uses MongoClient.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBRecipeDAO dao = new MongoDBRecipeDAO(mongo);

        List<Recipe> recipes = dao.getRecipes();
        System.out.println(recipes.size());
        //Adds all recipes to the model to output in the view.
        model.addAttribute("recipes", recipes);

        return "homepage";
    }

}
