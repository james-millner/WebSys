package com.jm.WebSys.controller;

//Importing spring framework
import com.jm.WebSys.DAO.MongoDBRecipeDAO;
import com.jm.WebSys.domain.Recipe;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by James on 17/03/2016.
 */

@Controller
public class addRecipeController {

    @RequestMapping("/addRecipe")
    public String add(Model model,
                      @RequestParam("name") String name,
                      Recipe recipe) {
        model.addAttribute("name", name);

        if(recipe.getRname() == null ){
            return "addRecipe";
        }

        System.out.println(recipe.getRname());
        System.out.println(recipe.getRdesc());
        System.out.println(recipe.getRingredients());
        System.out.println(recipe.getRhours());
        System.out.println(recipe.getRmins());
        System.out.println(recipe.getAdditional());
        recipe.setCreator(name);

        if (recipe.getRname() != null ) {
            model.addAttribute("success", recipe.getRname() + " has been added!");
        }

        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBRecipeDAO dao = new MongoDBRecipeDAO(mongo);
        dao.createRecipe(recipe);

        return "addRecipe";
    }
}
