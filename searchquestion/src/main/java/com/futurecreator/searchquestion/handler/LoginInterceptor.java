package com.futurecreator.searchquestion.handler;

import com.alibaba.fastjson.JSON;
import com.futurecreator.common.enums.TransCode;
import com.futurecreator.common.vo.Result;
import com.futurecreator.dao.pojo.user.User;
import com.futurecreator.searchquestion.service.token.TokenService;
import com.futurecreator.searchquestion.utils.threadlocal.UserStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//登录拦截器,在这里处理用户信息的赋值与清理
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private TokenService tokenService;

    //在request对应的控制器被执行前将user信息记录到本地变量中
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //不是HandlerMethod方法,例如请求资源等方法就跳过
        if(!(handler instanceof HandlerMethod))
            return true;

        //log日志
        String token = request.getHeader("Authorization");
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        //只有token合法且有意义时才放行,并保存user至threadLocal
        TokenService.UserTokenStorageState userTokenStoreState = tokenService.getUserTokenStoreState(token);
        if(userTokenStoreState== TokenService.UserTokenStorageState.STORE_THIS) {
            User user = tokenService.getInfoByToken(token);
            UserStorage.setUser(user);
            return true;
        }else{
            TransCode transCode=null;
            switch (userTokenStoreState){
                case TOKEN_ERROR:
                    transCode=TransCode.CHECK_PARAM_HAVE_ERROR;
                    break;
                case NO_STORE:
                    transCode=TransCode.NO_LOGIN;
                    break;
                case STORE_OTHER:
                    transCode=TransCode.HTTP_NO_LOGIN;
                    break;
            }
            //返回错误信息
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(Result.fail(transCode)));
            return false;
        }
    }

    //控制器执行后回收资源,防止内存泄露
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserStorage.removeUser();
    }
}