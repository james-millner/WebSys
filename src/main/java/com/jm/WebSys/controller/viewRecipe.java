package com.jm.WebSys.controller;

import com.jm.WebSys.DAO.MongoDBCommentDAO;
import com.jm.WebSys.DAO.MongoDBLikeDAO;
import com.jm.WebSys.DAO.MongoDBRecipeDAO;
import com.jm.WebSys.DAO.MongoDBUserDAO;
import com.jm.WebSys.domain.*;
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

        //Add the recipe on screen.
        model.addAttribute("recipeModel", recipe);

        //Get all comments
        MongoDBCommentDAO cdao = new MongoDBCommentDAO(mongo);

        //Make a comment with the ID of the recipe.
        Comment c = new Comment();
        c.setRid(id);

        List<Comment> commentList = cdao.getRecipeComments(c);

        model.addAttribute("comments", commentList);

        //All comments added to page. Then restrict adding a blank comment.
        if (comment.getComment() == null) {
            model.addAttribute("ecLink", crypt);

            /**
             *
             * Check to see if user has liked the recipe.
             *
             */


            //Make a user access object and create a user with the logged in username.
            MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
            User u = new User();
            u.setUsername(crypt);
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
                System.out.println("You haven't liked this");
            } else {
                yn = "y";
                System.out.println("You have Liked this");
            }
            model.addAttribute("liked", yn);

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

    @RequestMapping("/addLike")
    public String addLike(Like like,
                          Model model,
                          @RequestParam("_id") String rid,
                          @RequestParam("name") String name) {

        //Decrypt URL Variable
        Encrypter e = new Encrypter(name);
        String crypt = e.smDecrypt();
        System.out.println("User: " + crypt + " liked the recipe " + rid);

        // Since 2.10.0, uses MongoClient.
        //Get user details first.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
        User u = new User();
        u.setUsername(crypt);
        User found = userDAO.getUser(u);
        String uid = found.getId();

        //Build a like from the URL pass recipe ID, and user ID found above.
        MongoDBLikeDAO likeDAO = new MongoDBLikeDAO(mongo);
        Like addLike = new Like();
        addLike.setRid(rid);
        addLike.setUid(uid);

        likeDAO.createLike(addLike);
        return "redirect:/viewRecipe?_id=" + rid + "&name=" +name ;
    }

    @RequestMapping("/removeLike")
    public String removeLike(Like like,
                             Model model,
                             @RequestParam("_id") String rid,
                             @RequestParam("name") String name){

        //Decrypt URL Variable
        Encrypter e = new Encrypter(name);
        String crypt = e.smDecrypt();
        System.out.println("User: " + crypt + " liked the recipe " + rid);

        // Since 2.10.0, uses MongoClient.
        //Get user details first.
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
        User u = new User();
        u.setUsername(crypt);
        User found = userDAO.getUser(u);
        String uid = found.getId();

        //Build a like from the URL pass recipe ID, and user ID found above.
        MongoDBLikeDAO likeDAO = new MongoDBLikeDAO(mongo);
        Like lk = new Like();
        lk.setRid(rid);

        List<Like> totalRecipeLikes =  likeDAO.getLikesByRID(lk);
        Like usersLike = findUserLike(totalRecipeLikes, uid);
        likeDAO.deleteLike(usersLike);

        return "redirect:/viewRecipe?_id=" + rid + "&name=" +name ;

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
