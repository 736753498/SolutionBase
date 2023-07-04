package com.futurecreator.searchquestion.controller.module;

import com.futurecreator.common.vo.Result;
import com.futurecreator.dao.pojo.module.R.ModuleR;
import com.futurecreator.searchquestion.service.module.ModuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/module")
public class ModuleController {
    @Resource
    private ModuleService moduleService;

    @PostMapping("add")
    public Result<String> addNewModule(@RequestBody ModuleR moduleR){
        return moduleService.addNewModule(moduleR);
    }

    @PostMapping("update")
    public Result<String> updateModule(@RequestBody ModuleR moduleR){
        return moduleService.updateModule(moduleR);
    }

    @PostMapping("get/all")
    public Result<List<ModuleR>> getAllModules(){
        return moduleService.getAllModules();
    }
}
