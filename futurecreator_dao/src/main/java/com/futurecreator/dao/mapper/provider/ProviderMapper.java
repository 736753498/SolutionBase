package com.futurecreator.dao.mapper.provider;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.futurecreator.dao.pojo.provider.Provider;

public interface ProviderMapper extends BaseMapper<Provider> {
    /**
     * 判断是否有phone,password对应的用户
     * @param phone
     * @param password
     * @return
     */
    Boolean isUserValid(@Param("phone") String phone, @Param("password") String password);

    /**
     * 根据phone来更新provider
     * @return
     */
    int updateProviderByPhone(Provider provider);
}
