package com.futurecreator.dao.mapper.module;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.futurecreator.dao.pojo.module.Interface;
import com.futurecreator.dao.pojo.module.Module;
import com.futurecreator.dao.pojo.module.R.InterfaceR;
import com.futurecreator.dao.pojo.module.R.ModuleR;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

@Component
public class ModuleRMapper {
    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private InterfaceMapper interfaceMapper;

    /**
     * 得到所有module信息
     * @return
     */
    public List<ModuleR> getAllModuleR(){
        return moduleMapper.getAllModuleR();
    }

    /**
     * 根据moduleName得到对应的module信息
     * @return
     */
    public ModuleR getModuleRByName(String name){
        return moduleMapper.getModuleRByName(name);
    }

    /**
     * 添加module信息
     * 若module重复就返回false
     * 否则返回true
     * @param moduleR
     */
    public Boolean addModuleR(@NotNull ModuleR moduleR){
        //检查是否有重复元素
        if(getModuleRByName(moduleR.getName())!=null)
            return false;

        //添加module部分
        Module module = new Module();
        BeanUtils.copyProperties(moduleR,module);
        moduleMapper.insert(module);

        //添加interface部分
        for(InterfaceR interfaceR:moduleR.getInterfaces()){
            addInterface(interfaceR,module.getId());
        }
        return true;
    }

    /**
     * 修改module信息(根据module name)
     * 属性为空表示不修改
     * @param moduleR
     */
    public void updateModuleR(@NotNull ModuleR moduleR){
        //没有元素无法修改
        ModuleR moduleRByName = getModuleRByName(moduleR.getName());
        if(moduleRByName ==null)
            return;

        Long moduleId = moduleRByName.getId();
        //更新module部分
        Module module = new Module();
        BeanUtils.copyProperties(moduleR,module);
        LambdaQueryWrapper<Module> updateWrapper = new LambdaQueryWrapper<>();
        updateWrapper.eq(Module::getName,module.getName());
        moduleMapper.update(module, updateWrapper);

        //更新interface部分
        List<InterfaceR> interfaces1 = moduleRByName.getInterfaces();
        List<InterfaceR> interfaces2 = moduleR.getInterfaces();
        if(interfaces1!=null&&interfaces2!=null){
            interfaces1.sort(Comparator.comparing(InterfaceR::getName));
            interfaces2.sort(Comparator.comparing(InterfaceR::getName));
            updateInterfaceRs(interfaces1,0,interfaces2,0,moduleId);
        }
        else if(interfaces1==null&&interfaces2!=null)
            interfaces2.forEach((interfaceR)-> addInterface(interfaceR,moduleId));
        else if(interfaces1 != null)
            interfaces1.forEach((interfaceR -> deleteInterface(interfaceR,moduleId)));
    }

    private void updateInterfaceRs(List<InterfaceR> s1,int p1,List<InterfaceR> s2,int p2,Long moduleId){
        //s1用完,添加s2
        if(s1.size()==p1){
            for(;p2<s2.size();p2++)
                addInterface(s2.get(p2),moduleId);
        }
        //s2用完,删除s1
        else if(s2.size()==p2){
            for (;p1<s1.size();p1++)
                deleteInterface(s1.get(p1),moduleId);
        }
        else{
            InterfaceR r1 = s1.get(p1);
            InterfaceR r2 = s2.get(p2);
            //r1 name == r2 name 都前进一步
            if(r1.getName().equals(r2.getName())){
                if(!r1.equals(r2)) {
                    updateInterface(r2,moduleId);
                }
                updateInterfaceRs(s1,p1+1,s2,p2+1,moduleId);
            }else if(r1.getName().compareTo(r2.getName())>0){
                addInterface(r2,moduleId);
                updateInterfaceRs(s1,p1,s2,p2+1,moduleId);
            }else {
                deleteInterface(r1,moduleId);
                updateInterfaceRs(s1,p1+1,s2,p2,moduleId);
            }
        }
    }

    private void deleteInterface(InterfaceR interfaceR, Long moduleId){
        LambdaQueryWrapper<Interface> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Interface::getModuleId,moduleId);
        queryWrapper.eq(Interface::getName,interfaceR.getName());
        interfaceMapper.delete(queryWrapper);
    }

    private void addInterface(InterfaceR interfaceR, Long moduleId){
        Interface i=new Interface();
        BeanUtils.copyProperties(interfaceR,i);
        i.setModuleId(moduleId);
        interfaceMapper.insert(i);
    }

    private void updateInterface(InterfaceR interfaceR,Long moduleId){
        LambdaQueryWrapper<Interface> updateWrapper = new LambdaQueryWrapper<>();
        updateWrapper.eq(Interface::getName,interfaceR.getName());
        Interface anInterface = new Interface();
        BeanUtils.copyProperties(interfaceR,anInterface);
        anInterface.setModuleId(moduleId);
        interfaceMapper.update(anInterface, updateWrapper);
    }
}
