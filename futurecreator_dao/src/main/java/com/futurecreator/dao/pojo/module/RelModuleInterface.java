package com.futurecreator.dao.pojo.module;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelModuleInterface {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long moduleId;
    private Long interfaceId;
    private Integer amount;
    private String description;
}
