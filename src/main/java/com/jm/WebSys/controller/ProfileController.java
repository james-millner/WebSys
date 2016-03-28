package com.jm.WebSys.controller;

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
                       @RequestParam("name") String name) {
        model.addAttribute("name", name);
        return "profile";
    }

}
