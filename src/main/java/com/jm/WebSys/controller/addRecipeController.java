package com.jm.WebSys.controller;

//Importing spring framework
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
                       @RequestParam("name") String name) {

        model.addAttribute("name", name);
        return "addRecipe";
    }
}
