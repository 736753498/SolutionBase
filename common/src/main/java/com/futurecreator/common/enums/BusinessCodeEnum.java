package com.futurecreator.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum BusinessCodeEnum {
    HTTP_NO_LOGIN(401,"登录已失效，请重新登录"),
    HTTP_NO_PERMISSION(403,"抱歉，你没有访问权限"),
    DEFAULT_SUCCESS(200,"success"),
    DEFAULT_SYS_ERROR(209,"系统错误"),
    CHECK_PARAM_NO_RESULT(210,"检测参数无结果"),
    CHECK_BIZ_NO_RESULT(211,"检查业务无结果"),
    CHECK_ACTION_NO_RESULT(212,"检查执行情况无结果"),
    ILLEGAL_REQUEST(213,"非法请求"),
    CHECK_PARAM_NOT_MATCH(101,"检查参数不匹配"),
    CHECK_BIZ_ERROR_VALIDATE_CODE(102,"检查业务验证码错误"),
    CHECK_BIZ_ERROR_MOBILE_USED(103,"检查业务电话号码已被使用"),

    //login
    NO_LOGIN(120,"未登录")
    ;


    private static final Map<Integer, BusinessCodeEnum> codeMap = new HashMap<>((int) (BusinessCodeEnum.values().length / 0.75) + 1);

    static{
        for(BusinessCodeEnum businessCodeEnum: values()){
            codeMap.put(businessCodeEnum.getCode(), businessCodeEnum);
        }
    }

    /**
     * 根据code获取枚举值
     * @param code
     * @return
     */
    public static BusinessCodeEnum valueOfCode(int code){
        return codeMap.get(code);
    }

    private int code;
    private String msg;

    BusinessCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
