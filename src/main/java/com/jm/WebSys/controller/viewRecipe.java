package com.jm.WebSys.controller;

import com.jm.WebSys.DAO.MongoDBCommentDAO;
import com.jm.WebSys.DAO.MongoDBRecipeDAO;
import com.jm.WebSys.domain.Comment;
import com.jm.WebSys.domain.Encrypter;
import com.jm.WebSys.domain.Recipe;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by James on 25/03/2016.
 */

@Controller
public class viewRecipe {

    @RequestMapping("/viewRecipe")
    public String viewRecipe(Model model,
                             @RequestParam("_id") String id,
                             @RequestParam("name") String name,
                             Recipe recipe,
                             Comment comment) {

        //Decrypt URL Variable
        Encrypter e = new Encrypter(name);
        String crypt = e.smDecrypt();
        //Display User.
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

        System.out.println(recipe.getRingredients());

        //Add the recipe on screen.
        model.addAttribute("recipeModel", recipe);

        //Get all comments
        MongoDBCommentDAO cdao = new MongoDBCommentDAO(mongo);

        //Make a comment with the ID of the recipe.
        Comment c = new Comment();
        c.setRid(id);

        List<Comment> commentList = cdao.getRecipeComments(c);

        model.addAttribute("comments", commentList);

        if (comment.getComment() == null) {
            model.addAttribute("ecLink", crypt);
            return "viewRecipe";
        } else {
            //Fill in extra details for the comment.
            comment.setRid(id);
            comment.setAuthor(crypt);

            //get current date time with Date()
            Date date = new Date();
            comment.setTimestamp(date);

            cdao.createComment(comment);

            }

        return "redirect:viewRecipe?_id=" + id;

    }
}
