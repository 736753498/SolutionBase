package com.futurecreator.dao.pojo.solution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solution {
    private Long id;
    private String name;
    private String description;
    private String keyword;
    private String text;
}
