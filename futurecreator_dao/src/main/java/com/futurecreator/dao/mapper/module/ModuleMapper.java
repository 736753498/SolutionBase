package com.futurecreator.dao.mapper.module;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.futurecreator.dao.pojo.module.Module;
import com.futurecreator.dao.pojo.module.R.ModuleR;

public interface ModuleMapper extends BaseMapper<Module> {
    /**
     * 得到所有模块信息
     * @return
     */
    List<ModuleR> getAllModuleR();
}
