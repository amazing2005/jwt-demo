package com.example.tokenjwtdemo.controller;

import com.example.tokenjwtdemo.entity.UserLoginRequest;
import com.example.tokenjwtdemo.entity.UserLoginResponse;
import com.example.tokenjwtdemo.service.UserService;
import com.example.tokenjwtdemo.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private  UserService userService;

    @RequestMapping("/login")
    public ResponseEntity<UserLoginResponse> login(String username, String pwd, HttpServletResponse response) {
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername(username);
        userLoginRequest.setPwd(pwd);

        UserLoginResponse login = userService.login(userLoginRequest);
        if(Const.SUCCESS.equals(login.getCode())){
            response.addHeader("Set-Cookie","access_token="+login.getToken());
        }
        return ResponseEntity.ok(login);
    }
}
