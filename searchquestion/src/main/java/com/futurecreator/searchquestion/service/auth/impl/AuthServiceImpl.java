package com.futurecreator.searchquestion.service.auth.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.futurecreator.common.enums.TransCode;
import com.futurecreator.common.vo.Result;
import com.futurecreator.common.vo.user.UserParam;
import com.futurecreator.dao.mapper.user.UserMapper;
import com.futurecreator.dao.pojo.user.User;
import com.futurecreator.searchquestion.service.auth.AuthService;
import com.futurecreator.searchquestion.service.token.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private TokenService tokenService;

    @Override
    public Result<String> login(UserParam userParam) {
        String phone = userParam.getPhone();
        String password = userParam.getPassword();
        User user = userMapper.getUserByPhoneAndPassWord(phone,password);
        if(user!=null){
            String token = tokenService.storeUserGetToken(user);
            return Result.success(token);
        }else
            return Result.fail(TransCode.ACCOUNT_OR_PASSWORD_HAVE_ERROR);
    }

    @Override
    public void logout(String token) {
        tokenService.clearUserInfoByToken(token);
    }

    /**
     * 根据信息创建用户
     * 先检查参数是否合法
     * 再检查电话是否已经被注册
     * 最后在创建新用户
     *      先把用户创建
     *      根据id生成用户初始name
     *      再把用户信息更新到数据库中
     * @param userParam
     * @return
     */
    @Override
    public Result<String> register(UserParam userParam) {
        String phone = userParam.getPhone();
        String password = userParam.getPassword();
        if(StringUtils.isBlank(phone)||StringUtils.isBlank(password))
            return Result.fail(TransCode.CHECK_PARAM_HAVE_ERROR);
        else {
            User user = userMapper.getUserByPhone(phone);
            if(user!=null){
                return Result.fail(TransCode.MOBILE_PHONE_USED);
            }else{
                user = new User();
                user.setName("tempUser");
                user.setPhone(phone);
                user.setPassword(password);
                userMapper.insert(user);

                String userName = "user_" + user.getId();
                user.setName(userName);
                userMapper.updateUserByPhone(user);

                String token = tokenService.storeUserGetToken(user);
                return Result.success(token);
            }
        }
    }
}
