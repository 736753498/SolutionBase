package com.futurecreator.searchquestion;

import com.futurecreator.dao.mapper.provider.ProviderMapper;
import com.futurecreator.dao.pojo.provider.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ProviderMapperTests {
    @Resource
    private ProviderMapper providerMapper;

    @Test
    void testInsertProvider(){
        Provider provider = new Provider();
        provider.setName("cxk");
        provider.setPhone("111111");
        provider.setPassword("ccccc");
        provider.setVxAccount("aaaaaa");
        System.out.println(providerMapper.insert(provider));
    }

    @Test
    void testUpdateProvider(){
        Provider provider = new Provider();
        //provider.setName("cxk");
        provider.setPhone("111111");
        //provider.setPassword("ccccc");
        provider.setVxAccount("bbbbb");
        System.out.println(providerMapper.updateProviderByPhone(provider));
    }

    @Test
    void testProviderValid(){
        System.out.println(providerMapper.isUserValid("111111","ccccc"));
    }
}
