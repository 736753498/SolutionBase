package com.futurecreator.searchquestion.service.token.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.futurecreator.dao.pojo.user.User;
import com.futurecreator.searchquestion.service.token.TokenService;
import com.futurecreator.searchquestion.utils.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService {
    @Resource
    private JWT jwt;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Value("${token.expire}")
    private Long expireTime;

    private final String tokenServicePrefix="Token_";
    private final String tokenStorePrefix="token_";
    private final String userStorePrefix="user_";

    /**
     * 得到redis中存储的token键的格式
     * @param token
     * @return
     */
    private String getStorageFormatToken(String token){
        return tokenServicePrefix+tokenStorePrefix+token;
    }

    /**
     * 得到redis中存储的userId键的格式
     * @param userId
     * @return
     */
    private String getStorageFormatUserId(Long userId){
        return tokenServicePrefix+userStorePrefix+userId;
    }

    /**
     * 在redis中储存token和对应的用户信息
     * jwt先生成含过期时长的token
     * 先在redis中检查是否存在当前userId对应的token值
     *      若存在:将存在的token删除
     * 在redis中添加以token为键,user信息为值的键值对
     * 在redis中添加以userId为键,token信息为值的键值对
     * @param user
     * @return
     */
    @Override
    public String storeUserGetToken(User user) {
        String token = jwt.createToken(user.getId().toString(),expireTime*1000*60);

        String userToken = redisTemplate.opsForValue().get(getStorageFormatUserId(user.getId()));
        if(userToken!=null)
            redisTemplate.delete(getStorageFormatToken(userToken));

        redisTemplate.opsForValue().set(getStorageFormatToken(token), JSON.toJSONString(user),expireTime, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(getStorageFormatUserId(user.getId()),token,expireTime, TimeUnit.MINUTES);
        return token;
    }

    /**
     * 根据上传的token中userId来判断当前用户是否使用还再使用该token
     * 先判断content是否失效或不合法
     * 再从数据库中得到当前用户的token
     * 进行比较并返回结果
     *
     * @param token
     * @return
     */
    @Override
    public UserTokenStorageState getUserTokenStoreState(String token) {
        String content=null;
        if(StringUtils.isBlank(token)||(content = jwt.getContentByToken(token))==null)
            return UserTokenStorageState.TOKEN_ERROR;

        Long userId=null;
        try {
            userId=Long.parseLong(content);
        }catch (NumberFormatException e){
            return UserTokenStorageState.TOKEN_ERROR;
        }

        String userToken = redisTemplate.opsForValue().get(getStorageFormatUserId(userId));
        if(userToken==null)
            return UserTokenStorageState.NO_STORE;
        else if(userToken.equals(token))
            return UserTokenStorageState.STORE_THIS;
        else
            return UserTokenStorageState.STORE_OTHER;
    }

    /**
     * 根据token取出redis中存储的user info
     * 先判断token是否合法和过期
     * 再在redis中取出user信息
     * 最后返回实例化的user对象
     * @param token
     * @return
     */
    @Override
    public User getInfoByToken(String token) {
        if (StringUtils.isBlank(token)||!jwt.isTokenValid(token))
            return null;
        String userJson = redisTemplate.opsForValue().get(getStorageFormatToken(token));
        return JSON.parseObject(userJson, User.class);
    }

    /**
     * 删除redis中对应的token
     * 并将user-token map中的user信息删除
     * @param token
     */
    @Override
    public void clearUserInfoByToken(String token) {
        redisTemplate.delete(getStorageFormatToken(token));
        try{
            long userId = Long.parseLong(jwt.getContentByToken(token));
            redisTemplate.delete(getStorageFormatUserId(userId));
        }catch (NumberFormatException ignored){}
    }
}
