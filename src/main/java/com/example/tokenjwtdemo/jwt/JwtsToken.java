package com.example.tokenjwtdemo.jwt;

import com.example.tokenjwtdemo.jwt.JwtInfo;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtsToken {

    public static String generateToken(JwtInfo jwtInfo, long ttlMillis){
        long nowMillis = System.currentTimeMillis();
        long expMillis  = nowMillis + ttlMillis;
        Date now = new Date(nowMillis);
        Date exp = new Date(expMillis );

        JwtBuilder jwtBuilder = Jwts.builder()
                .claim(JwtConstants.JWT_KEY_USER_ID,jwtInfo.getUserId())
                .claim(JwtConstants.ROLE,jwtInfo.getRole())
                .setExpiration(exp).signWith(SignatureAlgorithm.HS256,generalKey());
        return jwtBuilder.compact();
    }

    private static Key generalKey() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] dc = DatatypeConverter.parseBase64Binary("user");
        return  new SecretKeySpec(dc,signatureAlgorithm.getJcaName());
    }

    public static  JwtInfo getTokenInfo(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer) claims.get(JwtConstants.JWT_KEY_USER_ID);
        return new JwtInfo(userId);
    }

    public static void main(String[] args) {
       // JwtInfo jwtInfo = new JwtInfo(12);
       // String s = generateToken(jwtInfo);
      //  System.out.println(s);


        String token="eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsImV4cCI6MTU1MTE0OTg3N30.lzPKlNp_W2BRvGvjaqUajfy_PSv-gLrHbNLgsEIDgPU";
        JwtInfo tokenInfo = getTokenInfo(token);
        System.out.println("from token:"+tokenInfo);
    }
}
