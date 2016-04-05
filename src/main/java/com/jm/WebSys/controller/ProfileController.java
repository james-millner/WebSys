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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 28/03/2016.
 */
@Controller
public class ProfileController {

    @RequestMapping("/profile")
    public String profile(Model model,
                          User user,
                       @RequestParam("name") String name) {
        //Decrypt URL Variable
        Encrypter e = new Encrypter(name);
        String crypt = e.smDecrypt();
        //Display User.
        model.addAttribute("name", crypt);
        model.addAttribute("ecLink", name);

        User u = new User();
        u.setUsername(crypt);

        // Since 2.10.0, uses MongoClient.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBUserDAO dao = new MongoDBUserDAO(mongo);

        user = dao.getUser(u);
        System.out.println(crypt);
        model.addAttribute("user", user);

        //Get recipe count
        Recipe recipe = new Recipe();
        recipe.setCreator(crypt);

        MongoDBRecipeDAO rdao = new MongoDBRecipeDAO(mongo);
        List<Recipe> recipeList = rdao.getCreatorsRecipes(recipe);
        model.addAttribute("rList", recipeList);

        //Get a list of liked recipes by the USERS ID.
        MongoDBLikeDAO likeDAO = new MongoDBLikeDAO(mongo);
        Like blank = new Like();
        blank.setUid(user.getId());
        List<Like> likes = likeDAO.getLikesByUID(blank);

        //With that list find the relevant recipes.
        List<Recipe> allRecipes = rdao.getRecipes();
        List<Recipe> likedRecipes = new ArrayList<Recipe>();
        for(int i = 0; i < likes.size(); i++) {
            String rid = likes.get(i).getRid();
            Recipe r = new Recipe();
            r.setId(rid);
            Recipe f = rdao.getRecipe(r);
            likedRecipes.add(f);
        }
        model.addAttribute("likesList", likedRecipes);
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
