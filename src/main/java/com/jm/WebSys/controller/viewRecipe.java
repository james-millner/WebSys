package com.jm.WebSys.controller;

import com.jm.WebSys.DAO.MongoDBRecipeDAO;
import com.jm.WebSys.domain.Recipe;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by James on 25/03/2016.
 */

@Controller
public class viewRecipe {

    @RequestMapping("/viewRecipe")
    public String viewRecipe(Model model,
                             @RequestParam("_id") String id,
                             @RequestParam("name") String name,
                             Recipe recipe) {
        //Add users name
        model.addAttribute("name", name);

        // Since 2.10.0, uses MongoClient.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBRecipeDAO dao = new MongoDBRecipeDAO(mongo);


        return "viewRecipe";
    }
}
