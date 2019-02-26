package com.example.tokenjwtdemo.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for(int i=0;i < cookies.length;i++){
                Cookie cookie = cookies[i];
                String cookieName = cookie.getName();
                if(name.equals(cookieName)){
                    String value = cookie.getValue();
                    return value;
                }
            }
        }
        return null;
    }
}
