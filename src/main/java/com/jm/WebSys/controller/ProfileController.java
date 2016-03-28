package com.jm.WebSys.controller;

import com.jm.WebSys.DAO.MongoDBRecipeDAO;
import com.jm.WebSys.DAO.MongoDBUserDAO;
import com.jm.WebSys.domain.Encrypter;
import com.jm.WebSys.domain.Recipe;
import com.jm.WebSys.domain.User;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        int rcount = rdao.getCreatorsRecipes(recipe).size();
        System.out.println("** I MADE : " + rcount);

        return "profile";
    }

}
