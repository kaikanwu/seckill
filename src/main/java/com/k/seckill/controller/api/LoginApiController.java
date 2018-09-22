package com.k.seckill.controller.api;


import com.k.seckill.base.controller.BaseApiController;
import com.k.seckill.base.result.Result;
import com.k.seckill.base.result.ResultCode;
import com.k.seckill.controller.LoginController;
import com.k.seckill.model.User;
import com.k.seckill.service.IUserService;
import com.k.seckill.util.MD5Util;
import com.k.seckill.util.UUIDUtil;
import com.k.seckill.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Controller上的@Controller改为@RestController,
 * '@RestController'相关于'@Controller'和'@ResponseBody'的合并注解，
 * 如果类注解了@RestController，下面的方法就不用每 都写@'ResponseBody'了。
 */
@RestController
public class LoginApiController extends BaseApiController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/login")
    public Result<Object> login(@ModelAttribute(value="user") @Valid User user, BindingResult bindingResult, HttpSession session, String code, Model model, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return Result.failure();//500, "error"
        }
        //get the user
        UserVO dbUser = userService.getUser(user.getUsername());

        if (dbUser != null) {
            String inputPassword = MD5Util.inputToDB(user.getPassword(), dbUser.getDbflag());
            //判断密码是否相等
            if (dbUser.getPassword().equals(inputPassword)) {


                System.out.println("===========================================");
                System.out.println("login api 方法里：" + "username="+user.getUsername()+";password="+user.getPassword() );
                System.out.println("===========================================");

                //将登陆成功的user信息存入redis中
                String token = UUIDUtil.getUUID();
                userService.saveUserToRedisByToken(dbUser, token);
                Cookie cookie = new Cookie("token", token);


                System.out.println("==============");
                System.out.println("login api 方法里 token===== "+token);
                System.out.println("==============");

                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);
                return Result.success();//success, "200"

            }else {
                return Result.failure(ResultCode.USER_LOGIN_ERROR);
            }

        }else {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
    }
}
