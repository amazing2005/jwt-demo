package com.example.tokenjwtdemo.service;

import com.example.tokenjwtdemo.entity.CheckAuthResponse;
import com.example.tokenjwtdemo.entity.UserLoginRequest;
import com.example.tokenjwtdemo.entity.UserLoginResponse;
import com.example.tokenjwtdemo.interceptor.LoginInterceptor;
import com.example.tokenjwtdemo.jwt.JwtInfo;
import com.example.tokenjwtdemo.jwt.JwtsToken;
import com.example.tokenjwtdemo.util.Const;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public CheckAuthResponse checkAuth(String accessToken) {
        CheckAuthResponse response = new CheckAuthResponse();
        try {
            JwtInfo jwtInfo = JwtsToken.getTokenInfo(accessToken);
            logger.info("jwtInfo="+jwtInfo);
            response.setCode(Const.SUCCESS);
            System.out.println("JwtInfo="+jwtInfo);
        } catch (ExpiredJwtException e) {
            response.setCode("-1");
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        // TODO  实际项目中对数据库中用户名和密码进行验证
        UserLoginResponse response = new UserLoginResponse();
        response.setCode(Const.SUCCESS);
        JwtInfo jwtInfo = new JwtInfo(1);
        response.setToken(JwtsToken.generateToken(jwtInfo, 3600*10000));
        return response;
    }
}
