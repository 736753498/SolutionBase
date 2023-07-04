package com.futurecreator.searchquestion.utils.threadlocal;

import com.futurecreator.dao.pojo.user.User;

/**
 * 封装user信息到当前thread中
 */
public class UserStorage {
    private static ThreadLocal<User> userThreadLocal;
    private static ThreadLocal<String> userTokenThreadLocal;

    static {
        userThreadLocal=new ThreadLocal<>();
        userTokenThreadLocal=new ThreadLocal<>();
    }

    public static void setUser(User user){
        userThreadLocal.set(user);
    }

    public static void setToken(String token){
        userTokenThreadLocal.set(token);
    }

    public static User getUser(){
        return userThreadLocal.get();
    }

    public static String getToken(){
        return userTokenThreadLocal.get();
    }

    public static void removeUser(){
        userThreadLocal.remove();
        userTokenThreadLocal.remove();
    }
}
