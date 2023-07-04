package com.futurecreator.searchquestion.controller.auth;

import com.futurecreator.common.vo.Result;
import com.futurecreator.common.vo.user.UserParam;
import com.futurecreator.searchquestion.service.auth.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private AuthService authService;

    @PostMapping("login")
    public Result<String> login(@RequestBody UserParam userParam){
        return authService.login(userParam);
    }

    @PostMapping("logout")
    public Result<String> logout(@RequestHeader("Authorization")String token){
        authService.logout(token);
        return Result.success();
    }

    @PostMapping("register")
    public Result<String> register(@RequestBody UserParam userParam){
        return authService.register(userParam);
    }
}
