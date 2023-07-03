package com.futurecreator.searchquestion.service.token;

import com.futurecreator.dao.pojo.user.User;

public interface TokenService {
    enum UserTokenStorageState{
        /**
         * token信息有误
         */
        TOKEN_ERROR,
        /**
         * user没存token
         */
        NO_STORE,
        /**
         * user保存了这个token
         */
        STORE_THIS,
        /**
         * user保存了其他token
         */
        STORE_OTHER;
    }

    /**
     * 储存token和对应的用户信息
     * @param user
     * @return
     */
    String storeUserGetToken(User user);

    /**
     * 根据上传的token中userId来判断当前用户是否使用还再使用该token
     *
     * @param token
     * @return
     */
    UserTokenStorageState getUserTokenStoreState(String token);

    /**
     * 根据token取出redis中存储的user info
     * @param token
     * @return
     */
    User getInfoByToken(String token);

    /**
     * 删除对应的token
     * @param token
     */
    void clearUserInfoByToken(String token);
}
