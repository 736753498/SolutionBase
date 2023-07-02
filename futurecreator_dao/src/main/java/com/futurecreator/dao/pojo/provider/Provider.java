package com.futurecreator.dao.pojo.provider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provider {
    private Long id;
    private String name;
    private String phone;
    private String password;
    private String vxAccount;
}
