package com.k.seckill.controller;

import com.k.seckill.model.User;
import com.k.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    public UserService userService;


    /**
     * get , jump to the register page
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView toRegister(ModelMap modelMap) {
        User user = new User();

        return new ModelAndView("register").addObject(user);
    }

    /**
     * post-> submit the username and password
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute(value = "user") @Valid  User user, BindingResult bindingResult) {
        user.setId(2018);


        User newUser = userService.register(user);

        /**
         * 验证用户名和密码有误的话，返回注册页面
         */
        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }


        if (newUser == null) {
            return new ModelAndView("home");

        }else {
            return new ModelAndView("register");
        }
    }


}
