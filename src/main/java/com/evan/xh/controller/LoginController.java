package com.evan.xh.controller;

import com.evan.xh.pojo.User;
import com.evan.xh.result.Result;
import com.evan.xh.result.ResultFactory;
import com.evan.xh.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LoginController {

    // username : admin
    // password : admin
    // code = eq ? 200 : 404

    @Autowired
    UserService userService;

    // 设置hash，算法迭代次数
    int times = 2;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session){
        // 对html 标签进行转义，防止XSS攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, requestUser.getPassword());
        token.setRememberMe(true);
        try{
            subject.login(token);
            return ResultFactory.buildSuccessResult(username);
        }catch (Exception e){
            String msg = "账号密码错误";
            return ResultFactory.buildFailResult(msg);
        }
    }

    @ResponseBody
    @PostMapping("api/register")
    public Result register(@RequestBody User user){

        User u = setUser(user);

        String name = u.getUsername();

        name = HtmlUtils.htmlEscape(name);
        user.setUsername(name);


        boolean exists = userService.isExists(name);
        if(exists){
            return ResultFactory.buildFailResult("用户名已被使用");
        }
        // 生成盐，默认16位
        String slat = new SecureRandomNumberGenerator().nextBytes().toString();
        // 得到hash后的密码
        String md5Password = new SimpleHash("md5", u.getPassword(), slat, times).toString();
        // 存储用户信息，包括slat 与 hash后的密码

        u.setSalt(slat);
        u.setPassword(md5Password);
        userService.add(u);
        return ResultFactory.buildSuccessResult(u);
    }

    @ResponseBody
    @GetMapping("api/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String msg = "成功登出";
        return ResultFactory.buildSuccessResult(msg);
    }

    @ResponseBody
    @GetMapping("api/authentication")
    public String authentication(){
        return "身份验证成功";
    }

    public static User setUser(User user){
        User u = new User();

        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getName();
        String phone = user.getPhone();
        String email = user.getEmail();

        u.setUsername(username);
        u.setPhone(phone);
        u.setPassword(password);
        u.setName(name);
        u.setEmail(email);
        return u;
    }

}
