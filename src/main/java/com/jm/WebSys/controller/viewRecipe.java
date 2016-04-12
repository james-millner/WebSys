package com.jm.WebSys.controller;

import com.jm.WebSys.DAO.MongoDBCommentDAO;
import com.jm.WebSys.DAO.MongoDBLikeDAO;
import com.jm.WebSys.DAO.MongoDBRecipeDAO;
import com.jm.WebSys.DAO.MongoDBUserDAO;
import com.jm.WebSys.domain.*;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by James on 25/03/2016.
 *
 * View Recipe Controller
 *
 * Handles the requests to view each recipe. It has a number of custom methods to sort the list of recipes stored in MongoDB.
 * It also handles the functionality for liking and de-liking a recipe.
 *
 */

@Controller
public class viewRecipe {

    @RequestMapping("/viewRecipe")
    public String viewRecipe(Model model,
                             @RequestParam("_id") String id,
                             @CookieValue(value = "user") String user,
                             Recipe recipe,
                             Comment comment) {

        //Display User.
        model.addAttribute("name", user);
        //MongoClient and Recipe Data Access Object.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBRecipeDAO dao = new MongoDBRecipeDAO(mongo);

        //Initially add a view count to the recipe.
        //Set up an empty recipe with the ID that has been selected.
        Recipe r = new Recipe();
        r.setId(id);
        //Get recipe with the passed ID.
        recipe = dao.getRecipe(r);
        //Increase the view count as the recipe has been requested.
        recipe.setViews(recipe.getViews() + 1);
        //Update the recipe.
        dao.updateRecipe(recipe);

        //Add the recipe on screen.
        model.addAttribute("recipeModel", recipe);

        //Get all comments
        MongoDBCommentDAO cdao = new MongoDBCommentDAO(mongo);

        //Make a comment with the ID of the recipe.
        Comment c = new Comment();
        c.setRid(id);
        //Get a list of comments that have the recipes ID.
        List<Comment> commentList = cdao.getRecipeComments(c);
        //Add it to screen.
        model.addAttribute("comments", commentList);

        //All comments added to page. Then restrict adding a blank comment.
        if (comment.getComment() == null) {
            model.addAttribute("ecLink", user);

            /**
             *
             * Check to see if user has liked the recipe.
             *
             */


            //Make a user access object and create a user with the logged in username.
            MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
            User u = new User();
            u.setUsername(user);
            User found = userDAO.getUser(u);
            String uid = found.getId();

            MongoDBLikeDAO likeDAO = new MongoDBLikeDAO(mongo);
            Like lr = new Like();
            lr.setRid(id);
            List<Like> totalRecipeLikes =  likeDAO.getLikesByRID(lr);
            Like like = findUserLike(totalRecipeLikes, uid);
            String yn = "";
            if(like.getId() == null) {
                yn = "n";
            } else {
                yn = "y";
            }
            model.addAttribute("liked", yn);

            return "viewRecipe";

        } else {
            //Fill in extra details for the comment.
            comment.setRid(id);
            comment.setAuthor(user);

            //get current date time with Date()
            Date date = new Date();
            comment.setTimestamp(date);

            cdao.createComment(comment);
        }
        return "redirect:viewRecipe?_id=" + id;
    }

    @RequestMapping("/addLike")
    public String addLike(Like like,
                          Model model,
                          @RequestParam("_id") String rid,
                          @CookieValue(value = "user") String user) {


        //Display User.
        model.addAttribute("name", user);

        //MongoClient and User Data Access Object.
        //Get user details first.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
        User u = new User();
        u.setUsername(user);
        User found = userDAO.getUser(u);
        String uid = found.getId();

        //Build a like from the URL pass recipe ID, and user ID found above.
        MongoDBLikeDAO likeDAO = new MongoDBLikeDAO(mongo);
        Like addLike = new Like();
        addLike.setRid(rid);
        addLike.setUid(uid);

        likeDAO.createLike(addLike);
        return "redirect:/viewRecipe?_id=" + rid;
    }

    @RequestMapping("/removeLike")
    public String removeLike(Like like,
                             Model model,
                             @RequestParam("_id") String rid,
                             @CookieValue(value = "user") String user){


        //Display User.
        model.addAttribute("name", user);

        //MongoClient and User Data Access Object.
        //Get user details first.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
        User u = new User();
        u.setUsername(user);
        User found = userDAO.getUser(u);
        String uid = found.getId();

        //Build a like from the URL pass recipe ID, and user ID found above.
        MongoDBLikeDAO likeDAO = new MongoDBLikeDAO(mongo);
        Like lk = new Like();
        lk.setRid(rid);

        List<Like> totalRecipeLikes =  likeDAO.getLikesByRID(lk);
        Like usersLike = findUserLike(totalRecipeLikes, uid);
        likeDAO.deleteLike(usersLike);

        return "redirect:/viewRecipe?_id=" + rid;

    }

    public Like findUserLike(List<Like> likes, String uid) {
        Like like = new Like();
        for(int i = 0; i < likes.size(); i++) {
            Like fl = likes.get(i);
            if(fl.getUid().equals(uid)) {
                like = fl;
            }
        }
        return like;
    }


}
