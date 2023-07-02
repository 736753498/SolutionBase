package com.futurecreator.dao.pojo.provider;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provider {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String phone;
    private String password;
    private String vxAccount;
}
