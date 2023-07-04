package com.futurecreator.searchquestion.service.info;

import com.futurecreator.common.vo.Result;

public interface InfoService {
    /**
     * 根据user更新用户name
     *
     * @param name
     * @return
     */
    Result<String> changeUserName(String name);

    /**
     * 根据user更新用户vx
     *
     * @param vxAccount
     * @return
     */
    Result<String> changeUserVxAccount(String vxAccount);

    /**
     * 更新用户pwd
     *
     * @param password
     * @return
     */
    Result<String> changeUserPassword(String password);
}
