package com.futurecreator.dao.pojo.module.R;

import lombok.Data;
import java.util.List;

@Data
public class ModuleR {
    private Long id;
    private String name;
    private String description;
    private List<InterfaceR> interfaces;
}
