package com.futurecreator.searchquestion;


import com.futurecreator.searchquestion.utils.jwt.JWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class JWTesters {
    @Resource
    private JWT jwt;
    @Test
    public void testJWT(){
        String token = jwt.createToken("1999", 10000L);

        System.out.println(jwt.isTokenValid(token));

        System.out.println(jwt.getContentByToken(token));
    }
}
