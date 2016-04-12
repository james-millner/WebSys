package com.jm.WebSys.controller;

import com.jm.WebSys.DAO.MongoDBUserDAO;
import com.jm.WebSys.domain.Encrypter;
import com.jm.WebSys.domain.Recipe;
import com.jm.WebSys.DAO.MongoDBRecipeDAO;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by James on 09/03/2016.
 *
 * HOME CONTROLLER
 *
 * This is the base controller that handles requests once the user has signed into the application.
 * It also contains the extra methods created top handle reporting.
 *
 */
@Controller
public class HomeController {

    //Homepage base view, displaying the general list of recipes from MongoDB.
    @RequestMapping("/homepage")
    public String home(Model model,
                       @CookieValue(value = "user") String user) {

        //Display User.
        model.addAttribute("name", user);

        //MongoClient and Recipe Data Access Object.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBRecipeDAO rdao = new MongoDBRecipeDAO(mongo);

        //Get a list of recipes using the DAO.
        List<Recipe> recipes = rdao.getRecipes();

        //Adds all recipes to the model to output in the view.
        model.addAttribute("recipes", recipes);
        mongo.close();
        return "homepage";
    }

    //By views view, which sorts the recipes by views and displays them on screen.
    @RequestMapping("/byviews")
    public String reportViews(Model model,
                              @CookieValue(value = "user") String user, String type) {
        //Display User.
        model.addAttribute("name", user);

        //MongoClient and Recipe Data Access Object.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBRecipeDAO rdao = new MongoDBRecipeDAO(mongo);

        //Get general list of recipes
        List<Recipe> recipes = rdao.getRecipes();
        //Create a new list and sort by view count
        List<Recipe> sorted = sortByViews(recipes);

        //Adds all recipes to the model to output in the view.
        model.addAttribute("recipes", sorted);

        mongo.close();
        return "/reports/byViews";
    }

    //By Type view. Similar to by views, but only retrieves recipes of a certain type.
    @RequestMapping("/byType")
    public String reportType(Model model,
                             @CookieValue(value = "user") String user,
                              @RequestParam("type") String type) {
        //Display User.
        model.addAttribute("name", user);

        //MongoClient and Recipe Data Access Object.
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

    //By views again, but with a param value.
    @RequestMapping("/byViewsParam")
    public String reportViewsParam(Model model,
                                   @CookieValue(value = "user") String user) {
        //Display User.
        model.addAttribute("name", user);

        //MongoClient and Recipe Data Access Object.
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

    //Recipes under 30 minutes. This sorts the recipes by minutes and displays quick recipes.
    @RequestMapping("/recipesUnder30")
    public String recipesUnder30Mins(Model model,
                                     @CookieValue(value = "user") String user) {
        //Display User.
        model.addAttribute("name", user);

        //MongoClient and Recipe Data Access Object.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBRecipeDAO rdao = new MongoDBRecipeDAO(mongo);

        //Get general list of recipes
        List<Recipe> recipes = rdao.getRecipes();
        //Create a new list and sort by view count
        List<Recipe> shortRecipes = recipesUnder30Minutes(recipes);
        model.addAttribute("recipes", shortRecipes);
        return "/reports/recipesUnder30";
    }

    @RequestMapping("/byAtoZ")
    public String atoz(Model model,
                       @CookieValue(value = "user") String user) {
        //Display User.
        model.addAttribute("name", user);

        //MongoClient and Recipe Data Access Object.
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDBRecipeDAO rdao = new MongoDBRecipeDAO(mongo);

        //Get general list of recipes
        List<Recipe> recipes = rdao.getRecipes();
        List<Recipe> alphabetical = sortAtoZ(recipes);
        model.addAttribute("recipes", alphabetical);
        return "/reports/byAtoZ";
    }

    //Method for sorting the recipes by type.
    public List<Recipe> sortByType(List<Recipe> recipes, String fType) {
        //Make a results list to return.
        List<Recipe> results = new ArrayList<Recipe>();
        //Int to loop through entire array.
        int size = recipes.size();
        for(int i = 0; i < size; i++) {
            Recipe r = recipes.get(i);
            //If the recipe matches the passed type param, add to the results list.
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

    //Method for sorting recipe list by views.
    public List<Recipe> sortAtoZ(List<Recipe> recipes) {
        //Make a results list to return.
        List<Recipe> results = new ArrayList<Recipe>();
        //Establish a Array with the size of the recipe list passed to the method.
        Recipe[] recipeArray = new Recipe[recipes.size()];
        //Convert the passed list from list to an array.
        recipeArray = recipes.toArray(recipeArray);
        //Sorts using the comparable interface.
        Arrays.sort(recipeArray, Recipe.recipeNameComparator);
        //Add all recipes to the list.
        for(Recipe temp: recipeArray) {
            results.add(temp);
        }
        //return the list.
        return results;

    }

    //Method for sorting by a specified number of views.
    public List<Recipe> sortByViewsSpecified(List<Recipe> recipes, int cutoff){
        List<Recipe> results = new ArrayList<Recipe>();
        for(int i = 0; i < recipes.size(); i++){
            //Get the first recipes in the list.
            Recipe r = recipes.get(i);
            //If the view count is bigger than the passed parameter, store it.
            if(r.getViews() > cutoff ) {
                results.add(r);
            }
        }
        return results;
    }

    //Method for showing recipes that are under 30 minutes in total.
    public List<Recipe> recipesUnder30Minutes(List<Recipe> recipes){
        List<Recipe> results = new ArrayList<Recipe>();
        for(int i = 0; i < recipes.size(); i++){
            //Get the first recipes in the list.
            Recipe r = recipes.get(i);
            //If the recipe minute count is 15 or under, then add to results.
            if(r.getRmins() <= 30 && r.getRhours() == 0 ) {
                results.add(r);
            }
        }
        return results;
    }

}
