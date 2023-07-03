package com.futurecreator.searchquestion;

import com.futurecreator.dao.mapper.module.ModuleMapper;
import com.futurecreator.dao.mapper.module.ModuleRMapper;
import com.futurecreator.dao.pojo.module.R.InterfaceR;
import com.futurecreator.dao.pojo.module.R.ModuleR;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class ModuleMapperTests {
    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private ModuleRMapper moduleRMapper;
    @Test
    void testSelectAllR(){
        System.out.println(moduleMapper.getAllModuleR());
    }

    @Test
    void testSelectAllRByR(){
        System.out.println(moduleRMapper.getAllModuleR());
    }

    @Test
    void testSelectRByR(){
        System.out.println(moduleRMapper.getModuleRByName("ccc"));
    }

    @Test
    void testInsertModuleR(){
        ModuleR moduleR = new ModuleR();
        moduleR.setName("ccc");
        moduleR.setDescription("cccaaa");

        ArrayList<InterfaceR> interfaces = new ArrayList<>();

        InterfaceR interfaceR = new InterfaceR();
        interfaceR.setAmount(1);
        interfaceR.setName("c1");
        interfaceR.setDescription("c1c1");
        interfaces.add(interfaceR);

        interfaceR=new InterfaceR();
        interfaceR.setAmount(2);
        interfaceR.setName("c2");
        interfaceR.setDescription("c2c2");
        interfaces.add(interfaceR);
        moduleR.setInterfaces(interfaces);

        moduleRMapper.addModuleR(moduleR);
    }

    @Test
    void testUpdateModuleR(){

        ModuleR ccc = moduleRMapper.getModuleRByName("ccc");

        //添加
//        InterfaceR interfaceR = new InterfaceR();
//        interfaceR.setAmount(9);
//        interfaceR.setName("c3");
//        interfaceR.setDescription("c3c3");
//        ccc.getInterfaces().add(interfaceR);

        //删除
//        ccc.getInterfaces().remove(2);

        InterfaceR interfaceR1 = ccc.getInterfaces().get(1);
        interfaceR1.setAmount(12);

        moduleRMapper.updateModuleR(ccc);


    }
}
