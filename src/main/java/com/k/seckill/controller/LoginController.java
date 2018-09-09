package com.k.seckill.controller;

import com.k.seckill.model.User;
import com.k.seckill.service.UserService;
import com.k.seckill.util.MD5Util;
import com.k.seckill.util.UUIDUtil;
import com.k.seckill.util.ValidateCode;
import com.k.seckill.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String login(@ModelAttribute(value="user") @Valid User user, BindingResult bindingResult, HttpSession session, String code, Model model, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login";
        }


        String sessionCode = (String) session.getAttribute("code");
        if (!StringUtils.equalsIgnoreCase(code, sessionCode)) {  //忽略验证码大小写
            model.addAttribute("message", "验证码错误");
            return "login";

        }

        logger.info("==============" + user.getPassword());
        logger.info("==============" + user.getUsername());

        //判断用户是否存在
//        User temp = userService.getUser(user.getUsername());
        UserVO dbUser = userService.getUser(user.getUsername());



        if (dbUser != null) {
            String inputPassword = MD5Util.inputToDB(user.getPassword(), dbUser.getDbflag());
            //判断密码是否相等
            if (dbUser.getPassword().equals(inputPassword)) {
//                session.setAttribute("user", dbUser);
//                logger.info("==============登录成功!========");

                //将登陆成功的user存入redis中
                String token = UUIDUtil.getUUID();
                userService.saveUserToRedisByToken(dbUser, token);
                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);



                return "redirect:/home";
            }else {
                return "login";
            }

        }else {
            return "login";
        }
    }


    /**
     * 图形验证码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/validateCode")
    public String validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession();

        ValidateCode vCode = new ValidateCode(120,40,5,100);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
        return null;
    }


}
