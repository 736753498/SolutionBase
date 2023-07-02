package com.futurecreator.dao.pojo.solution;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solution {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String keyword;
    private String text;
}
