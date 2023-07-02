package com.futurecreator.dao.pojo.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelModuleInterface {
    private Long id;
    private Long moduleId;
    private Long interfaceId;
    private Integer amount;
    private String description;
}
