package com.futurecreator.searchquestion.service.info.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.futurecreator.common.enums.TransCode;
import com.futurecreator.common.vo.Result;
import com.futurecreator.dao.mapper.user.UserMapper;
import com.futurecreator.dao.pojo.user.User;
import com.futurecreator.searchquestion.service.auth.AuthService;
import com.futurecreator.searchquestion.service.info.InfoService;
import com.futurecreator.searchquestion.service.token.TokenService;
import com.futurecreator.searchquestion.utils.pwdsalt.PwdUtil;
import com.futurecreator.searchquestion.utils.threadlocal.UserStorage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InfoServiceImpl implements InfoService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthService authService;

    @Resource
    private TokenService tokenService;

    @Resource
    private PwdUtil pwdUtil;

    /**
     * 根据userId更新用户name
     * 先检查是否有重复的userName
     * 再修改userName
     *
     * @param name
     * @return
     */
    @Override
    public Result<String> changeUserName(String name) {
        Long userId = UserStorage.getUser().getId();

        if(StringUtils.isBlank(name))
            return Result.fail(TransCode.CHECK_PARAM_HAVE_ERROR);
        LambdaQueryWrapper<User> nameSearcher = new LambdaQueryWrapper<>();
        nameSearcher.eq(User::getName,name);
        User user = userMapper.selectOne(nameSearcher);
        if(user==null){
            user=new User();
            user.setId(userId);
            user.setName(name);
            userMapper.updateById(user);
            return Result.success();
        }else
            return Result.fail(TransCode.USER_NAME_USED);
    }

    /**
     * 根据userStorage得到user信息
     * 先检查是否有重复的vx
     * 再修改vx
     *
     * @param vxAccount
     * @return
     */
    @Override
    public Result<String> changeUserVxAccount(String vxAccount) {
        Long userId = UserStorage.getUser().getId();

        if(StringUtils.isBlank(vxAccount))
            return Result.fail(TransCode.CHECK_PARAM_HAVE_ERROR);
        LambdaQueryWrapper<User> vxSearcher = new LambdaQueryWrapper<>();
        vxSearcher.eq(User::getVxAccount,vxAccount);
        User user = userMapper.selectOne(vxSearcher);
        if(user==null){
            user=new User();
            user.setId(userId);
            user.setVxAccount(vxAccount);
            userMapper.updateById(user);
            return Result.success();
        }else
            return Result.fail(TransCode.VX_ACCOUNT_USED);
    }

    /**
     * 根据userId更新用户pwd
     * 直接修改即可
     * 然后下线用户
     * @param password
     * @return
     */
    @Override
    public Result<String> changeUserPassword(String password) {
        if(StringUtils.isBlank(password))
            return Result.fail(TransCode.CHECK_PARAM_HAVE_ERROR);

        Long userId = UserStorage.getUser().getId();
        User user = new User();
        user.setId(userId);
        user.setPassword(pwdUtil.getSaltedPassword(password));
        userMapper.updateById(user);

        authService.logout(UserStorage.getToken());

        return Result.success();
    }
}
