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

        //Initially add a view count to the recipe.
        //Set up an empty recipe with the ID that has been selected.
        Recipe r = new Recipe();
        r.setId(id);

        recipe = dao.getRecipe(r);
        recipe.setViews(recipe.getViews() + 1);

        dao.updateRecipe(recipe);

        String ing = recipe.getRingredients();
        int pos = 0;
        while ((pos = ing.indexOf(".", pos)) != -1) {
            System.out.println(ing.substring(pos));
            pos++;
        }

        //Add the recipe on screen.
        model.addAttribute("recipeModel", recipe);


        return "viewRecipe";
    }
}
