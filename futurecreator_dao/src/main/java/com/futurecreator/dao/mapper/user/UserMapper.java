package com.futurecreator.dao.mapper.user;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.futurecreator.dao.pojo.user.User;

public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据phone,password返回对应的用户
     * @param phone
     * @param password
     * @return
     */
    User getUserByPhoneAndPassWord(@Param("phone") String phone, @Param("password") String password);

    /**
     * 根据phone来更新user
     * @return
     */
    int updateUserByPhone(User user);
}
