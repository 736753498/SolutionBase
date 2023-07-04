package com.futurecreator.searchquestion.service.module.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.futurecreator.common.enums.TransCode;
import com.futurecreator.common.vo.Result;
import com.futurecreator.dao.mapper.module.ModuleMapper;
import com.futurecreator.dao.mapper.module.ModuleRMapper;
import com.futurecreator.dao.pojo.module.Module;
import com.futurecreator.dao.pojo.module.R.ModuleR;
import com.futurecreator.searchquestion.service.module.ModuleService;
import com.futurecreator.searchquestion.utils.threadlocal.UserStorage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Resource
    private ModuleRMapper moduleRMapper;

    @Resource
    private ModuleMapper moduleMapper;

    /**
     * 根据module信息创建新module
     * 先检查module参数是否正常
     * 再由user创建该module
     * @param moduleR
     * @return
     */
    @Override
    public Result<String> addNewModule(ModuleR moduleR) {
        if(moduleR==null)
            return Result.fail(TransCode.CHECK_PARAM_HAVE_ERROR);
        else{
            Long userId = UserStorage.getUser().getId();
            moduleR.setCreateUser(userId);
            if(moduleRMapper.addModuleR(moduleR))
                return Result.success();
            else
                return Result.fail(TransCode.MODULE_DUPLICATE);
        }
    }

    /**
     * 得到所有的module信息
     * @return
     */
    @Override
    public Result<List<ModuleR>> getAllModules() {
        return Result.success(moduleRMapper.getAllModuleR());
    }

    /**
     * 更新module信息
     * 先查看数据库中的module
     *      是否存在该module
     *      是否是该用户创建的
     * 最后再修改module
     * @param moduleR
     * @return
     */
    @Override
    public Result<String> updateModule(ModuleR moduleR) {
        if(moduleR==null)
            return Result.fail(TransCode.CHECK_PARAM_HAVE_ERROR);
        else{
            LambdaQueryWrapper<Module> moduleSearcher = new LambdaQueryWrapper<>();
            moduleSearcher.eq(Module::getName,moduleR.getName());
            Module module = moduleMapper.selectOne(moduleSearcher);
            if(module==null)
                return Result.fail(TransCode.CHECK_PARAM_NO_RESULT);
            else {
                if(!module.getCreateUser().equals(UserStorage.getUser().getId()))
                    return Result.fail(TransCode.HTTP_NO_PERMISSION);
                else{
                    moduleRMapper.updateModuleR(moduleR);
                    return Result.success();
                }
            }
        }
    }
}
