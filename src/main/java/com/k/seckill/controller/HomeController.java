package com.k.seckill.controller;

import com.k.seckill.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {


    private static Logger logger = LoggerFactory.getLogger(HomeController.class);



    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, User user) {
//        System.out.println("========" + user.getUsername());
            logger.info(user.getUsername());


        model.addAttribute("user", user);
        return "home";
    }

}
