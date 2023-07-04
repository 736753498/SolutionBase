package com.futurecreator.searchquestion.service.auth;

import com.futurecreator.common.vo.Result;
import com.futurecreator.common.vo.user.UserParam;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    /**
     * 根据用户信息进行登录
     * 并返回结果
     * @param userParam
     * @return
     */
    Result<String> login(UserParam userParam);
    /**
     * 根据用户token信息退出登录
     * 并返回结果
     * @param token
     * @return
     */
    void logout(String token);

    /**
     * 根据信息创建用户
     * 并返回结果
     * @param userParam
     * @return
     */
    Result<String> register(UserParam userParam);
}
