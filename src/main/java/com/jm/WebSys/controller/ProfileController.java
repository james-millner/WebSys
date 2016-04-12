package com.jm.WebSys.controller;

import com.jm.WebSys.DAO.MongoDBLikeDAO;
import com.jm.WebSys.DAO.MongoDBRecipeDAO;
import com.jm.WebSys.DAO.MongoDBUserDAO;
import com.jm.WebSys.domain.Encrypter;
import com.jm.WebSys.domain.Like;
import com.jm.WebSys.domain.Recipe;
import com.jm.WebSys.domain.User;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 28/03/2016.
 *
 * Profile Controller
 *
 * This controller handles the users profile. It uses a combination of various Mongo Access Objects to provide;
 *         -User details
 *         -User Stats
 *         -User recipes
 *         -User liked recipes
 *
 */
@Controller
public class ProfileController {

    @RequestMapping("/profile")
    public String profile(Model model,
                          User user,
                          @CookieValue(value = "user") String userC) {
        //Display User.
        model.addAttribute("name", userC);

        /**
         * Gets the user information initially.
         */
        //MongoClient and User Data Access Object.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBUserDAO dao = new MongoDBUserDAO(mongo);
        //Create an empty user, and set the username as the Cookie created from sign in.
        User u = new User();
        u.setUsername(userC);
        //Use the Data Access Object to find the user.
        user = dao.getUser(u);
        //Add the user to the model,
        model.addAttribute("user", user);

        /**
         * Gets a list of the users recipes.
         */
        //Get recipe count by creating a recipe with the creator attribute set as the Cookie Value.
        Recipe recipe = new Recipe();
        recipe.setCreator(userC);

        //Create a recipe data access object.
        MongoDBRecipeDAO rdao = new MongoDBRecipeDAO(mongo);
        //Get a list of the creators recipes
        List<Recipe> recipeList = rdao.getCreatorsRecipes(recipe);
        //Add the list to the model.
        model.addAttribute("rList", recipeList);

        /**
         * Gets a list of any recipes that the user has liked.
         */
        //Get a list of liked recipes by the USERS ID.
        MongoDBLikeDAO likeDAO = new MongoDBLikeDAO(mongo);
        Like blank = new Like();
        blank.setUid(user.getId());
        List<Like> likes = likeDAO.getLikesByUID(blank);

        List<Recipe> likedRecipes = new ArrayList<Recipe>();
        for(int i = 0; i < likes.size(); i++) {
            String rid = likes.get(i).getRid();
            Recipe r = new Recipe();
            r.setId(rid);
            Recipe f = rdao.getRecipe(r);
            likedRecipes.add(f);
        }
        model.addAttribute("likesList", likedRecipes);

        /**
         * Get some basic profile stats.
         */
        //Profile Stats:
        int totalRecipes = recipeList.size();
        int totalViews = 0;
        for(int i = 0; i < totalRecipes; i++) {
            Recipe r = recipeList.get(i);
            totalViews += r.getViews();
        }
        model.addAttribute("tRecipes", totalRecipes);
        model.addAttribute("tViews", totalViews);

        return "profile";
    }

}
