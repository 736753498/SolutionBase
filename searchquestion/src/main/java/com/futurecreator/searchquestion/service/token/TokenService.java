package com.futurecreator.searchquestion.service.token;

import com.futurecreator.dao.pojo.user.User;

public interface TokenService {
    String storeUserGetToken(User user);

    User getInfoByToken(String token);

    void clearUserInfoByToken(String token);
}
