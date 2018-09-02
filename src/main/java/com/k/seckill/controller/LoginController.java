package com.k.seckill.controller;

import com.k.seckill.model.User;
import com.k.seckill.service.UserService;
import com.k.seckill.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;


    /**
     * 登录页面，GET请求
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "登录页面");
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult bindingResult, HttpSession session, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            return "/login";
        }

        logger.info("==============" + user.getPassword());
        logger.info("==============" + user.getUsername());

        //判断用户是否存在
        User temp = userService.getUser(user.getUsername());

        if (temp != null) {
            String inputPassword = MD5Util.inputToDB(user.getPassword(), temp.getDbflag());
            //判断密码是否相等
            if (temp.getPassword().equals(inputPassword)) {
                session.setAttribute("user", temp);
                logger.info("==============登录成功!========");

                return "redirect:/home";
            }else {
                return "login";
            }

        }else {
            return "login";
        }



    }


}
