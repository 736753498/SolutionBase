package com.futurecreator.dao.mapper.user;
import org.apache.ibatis.annotations.Param;
import com.futurecreator.dao.pojo.user.User;

public interface UserMapper{
    /**
     * 判断是否有phone,password对应的用户
     * @param phone
     * @param password
     * @return
     */
    Boolean isUserValid(@Param("phone") String phone, @Param("password") String password);

    /**
     * 插入对应的user
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据id更新user
     * @param user
     * @return
     */
    int updateSelective(User user);
}
