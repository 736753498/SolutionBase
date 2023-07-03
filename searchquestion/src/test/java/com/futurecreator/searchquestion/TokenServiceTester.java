package com.futurecreator.searchquestion;

import com.futurecreator.dao.pojo.user.User;
import com.futurecreator.searchquestion.service.token.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TokenServiceTester {
    @Resource
    private TokenService tokenService;
    @Test
    void testTokenCreate(){
        User user = new User();
        user.setId(19999L);
        user.setName("cxk");
        System.out.println(tokenService.storeUserGetToken(user));
    }

    @Test
    void testTokenState(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODgzNzU3NTcsImlhdCI6MTY4ODM3MjE1NywiY29udGVudCI6IjE5OTk5In0.xN1agGrT72DsIla0JY-QQEb85ITQ9nvckVL--OyS9Rw";
        System.out.println(tokenService.getUserTokenStoreState(token));
    }

    @Test
    void testTokenGet(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODgzNzIzMDUsImlhdCI6MTY4ODM2ODcwNSwiY29udGVudCI6IjE5OTk5In0.BBu0gfCPKVaiwK9J9VjMVevSscsr1avkjOMU3PUKPk4";
        System.out.println(tokenService.getInfoByToken(token));
    }


    @Test
    void testClearToken(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODgzNzU4MTAsImlhdCI6MTY4ODM3MjIxMCwiY29udGVudCI6IjE5OTk5In0.1-Ju1XTFRQu6xmV2yrTSW1YI-0PxPgNnanbRdvrHOZM";
        tokenService.clearUserInfoByToken(token);
    }
}
