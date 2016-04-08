package com.jm.WebSys.controller;

import com.jm.WebSys.DAO.MongoDBUserDAO;
import com.jm.WebSys.domain.Encrypter;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created by James on 09/03/2016.
 */
@Controller
public class HomeController {

    @RequestMapping("/homepage")
    public String home(Model model,
                       @RequestParam("name") String name) {
        //Decrypt URL Variable
        Encrypter e = new Encrypter(name);
        String crypt = e.smDecrypt();

        //Display User.
        model.addAttribute("name", crypt);
        model.addAttribute("ecLink", name);
        // Since 2.10.0, uses MongoClient.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBRecipeDAO rdao = new MongoDBRecipeDAO(mongo);

        List<Recipe> recipes = rdao.getRecipes();
        System.out.println(recipes.size());
        //Adds all recipes to the model to output in the view.
        model.addAttribute("recipes", recipes);
        mongo.close();
        sortByViews(recipes);
        return "homepage";
    }

    @RequestMapping("/byviews")
    public String reportViews(Model model,
                              @RequestParam("name") String name, String type) {
        //Decrypt URL Variable
        Encrypter e = new Encrypter(name);
        String crypt = e.smDecrypt();

        //Display User.
        model.addAttribute("name", crypt);
        model.addAttribute("ecLink", name);
        // Since 2.10.0, uses MongoClient.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBRecipeDAO rdao = new MongoDBRecipeDAO(mongo);

        //Get general list of recipes
        List<Recipe> recipes = rdao.getRecipes();
        //Create a new list and sort by view count
        List<Recipe> sorted = sortByViews(recipes);
        //Adds all recipes to the model to output in the view.
        model.addAttribute("recipes", sorted);
        System.out.println("SELECTED : " + type);
        mongo.close();
        return "/reports/byViews";
    }

    @RequestMapping("/byType")
    public String reportType(Model model,
                              @RequestParam("name") String name,
                              @RequestParam("type") String type) {
        //Decrypt URL Variable
        Encrypter e = new Encrypter(name);
        String crypt = e.smDecrypt();

        //Display User.
        model.addAttribute("name", crypt);
        model.addAttribute("ecLink", name);
        // Since 2.10.0, uses MongoClient.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBRecipeDAO rdao = new MongoDBRecipeDAO(mongo);

        //Get general list of recipes
        List<Recipe> recipes = rdao.getRecipes();
        List<Recipe> sorted = sortByType(recipes,type);
        //Adds all recipes to the model to output in the view.
        model.addAttribute("recipes", sorted);
        mongo.close();
        return "/reports/byType";
    }

    @RequestMapping("/byViewsParam")
    public String reportViewsParam(Model model,
                                   @RequestParam("name") String name, String type) {
        //Decrypt URL Variable
        Encrypter e = new Encrypter(name);
        String crypt = e.smDecrypt();

        //Display User.
        model.addAttribute("name", crypt);
        model.addAttribute("ecLink", name);
        // Since 2.10.0, uses MongoClient.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBRecipeDAO rdao = new MongoDBRecipeDAO(mongo);

        //Get general list of recipes
        List<Recipe> recipes = rdao.getRecipes();
        //Create a new list and sort by view count
        List<Recipe> sorted = sortByViews(recipes);
        List<Recipe> sortedByParam = sortByViewsSpecified(sorted, 50);
        model.addAttribute("recipes", sortedByParam);
        return "/reports/byViewsByParam";
    }

    public List<Recipe> sortByType(List<Recipe> recipes, String fType) {
        //Make a results list to return.
        List<Recipe> results = new ArrayList<Recipe>();
        //Int to loop through entire array.
        int size = recipes.size();
        System.out.println(size + " SIZE HERE");

        for(int i = 0; i < size; i++) {
            Recipe r = recipes.get(i);
            if (r.getFtype().equals(fType)) {
                results.add(r);
            }
        }
        return results;
    }

    //Method for sorting recipe list by views.
    public List<Recipe> sortByViews(List<Recipe> recipes) {
        //Make a results list to return.
        List<Recipe> results = new ArrayList<Recipe>();
        //Establish a Array with the size of the recipe list passed to the method.
        Recipe[] recipeArray = new Recipe[recipes.size()];
        //Convert the passed list from list to an array.
        recipeArray = recipes.toArray(recipeArray);
        //Sorts using the comparable interface.
        Arrays.sort(recipeArray);
        //Add all recipes to the list.
        for(Recipe temp: recipeArray) {
            results.add(temp);
        }
        //return the list.
        return results;

    }

    public List<Recipe> sortByViewsSpecified(List<Recipe> recipes, int cutoff){
        List<Recipe> results = new ArrayList<Recipe>();
        for(int i = 0; i < recipes.size(); i++){
            //Get the first recipes in the list.
            Recipe r = recipes.get(i);
            //If the view count is bigger than the passed parameter, store it.
            if(r.getViews() > cutoff ) {
                System.out.println(r.getViews());
                results.add(r);
            }
        }
        return results;
    }

}
