package com.futurecreator.searchquestion.service.module;

import com.futurecreator.common.vo.Result;
import com.futurecreator.dao.pojo.module.R.ModuleR;

import java.util.List;

public interface ModuleService {
    /**
     * 根据module信息来添加module
     * @param moduleR
     * @return
     */
    Result<String> addNewModule(ModuleR moduleR);

    /**
     * 得到所有module信息
     * @return
     */
    Result<List<ModuleR>> getAllModules();

    /**
     * 修改module信息
     * @param moduleR
     * @return
     */
    Result<String> updateModule(ModuleR moduleR);
}
