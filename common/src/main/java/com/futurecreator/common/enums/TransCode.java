package com.futurecreator.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum TransCode {
    HTTP_NO_LOGIN(401,"登录已失效，请重新登录"),
    HTTP_NO_PERMISSION(403,"抱歉，你没有访问权限"),
    DEFAULT_SUCCESS(200,"success"),
    ACCOUNT_OR_PASSWORD_HAVE_ERROR(209,"账号或密码有误"),
    CHECK_PARAM_HAVE_ERROR(210,"参数异常"),
    DEFAULT_SYS_ERROR(209,"系统错误"),
    CHECK_PARAM_NO_RESULT(210,"检测参数无结果"),
    CHECK_BIZ_NO_RESULT(211,"检查业务无结果"),
    CHECK_ACTION_NO_RESULT(212,"检查执行情况无结果"),
    ILLEGAL_REQUEST(213,"非法请求"),
    CHECK_PARAM_NOT_MATCH(101,"检查参数不匹配"),
    CHECK_BIZ_ERROR_VALIDATE_CODE(102,"检查业务验证码错误"),
    MOBILE_PHONE_USED(103,"电话号码已被注册"),

    //login
    NO_LOGIN(120,"未登录")
    ;


    private static final Map<Integer, TransCode> codeMap = new HashMap<>((int) (TransCode.values().length / 0.75) + 1);

    static{
        for(TransCode transCode : values()){
            codeMap.put(transCode.getCode(), transCode);
        }
    }

    /**
     * 根据code获取枚举值
     * @param code
     * @return
     */
    public static TransCode valueOfCode(int code){
        return codeMap.get(code);
    }

    private int code;
    private String msg;

    TransCode(int code, String msg) {
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
