package com.futurecreator.searchquestion.controller.info;

import com.futurecreator.common.vo.Result;
import com.futurecreator.searchquestion.service.info.InfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/info")
public class InfoController {
    @Resource
    private InfoService infoService;

    @GetMapping("changeName")
    public Result<String> changeUserName(@RequestParam("name") String name){
        return infoService.changeUserName(name);
    }

    @GetMapping("changeVxAccount")
    public Result<String> changeUserVxAccount(@RequestParam("vx") String vx){
        return infoService.changeUserVxAccount(vx);
    }

    @GetMapping("changePassword")
    public Result<String> changeUserPassword(@RequestParam("password") String password){
        return infoService.changeUserPassword(password);
    }
}
