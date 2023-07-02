package com.futurecreator.common.vo;

import com.futurecreator.common.enums.BusinessCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 业务结果采用result返回,需要能够序列化
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {


    /**
     * 成功标志
     */
    private boolean success;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 结果对象
     */
    private T result;

    public static <T> Result<T> success(){
        return new Result<>(true,BusinessCodeEnum.DEFAULT_SUCCESS.getMsg(), BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),null);
    }

    public static <T> Result<T> success(T data){
        return new Result<>(true,"success", BusinessCodeEnum.DEFAULT_SUCCESS.getCode(),data);
    }

    public static <T> Result<T> fail(Integer code, String message){
        return new Result<>(false,message,code,null);
    }

    public static <T> Result<T> fail(){
        return new Result<>(false,BusinessCodeEnum.DEFAULT_SYS_ERROR.getMsg(),BusinessCodeEnum.DEFAULT_SYS_ERROR.getCode(),null);
    }

    public static <T> Result<T> fail(BusinessCodeEnum codeEnum){
        return new Result<>(false,codeEnum.getMsg(),codeEnum.getCode(),null);
    }

    public static <T> Result<T> noPermission(){
        return new Result<>(false, BusinessCodeEnum.HTTP_NO_PERMISSION.getMsg(),BusinessCodeEnum.HTTP_NO_PERMISSION.getCode(),null);
    }

    public static <T> Result<T> noLogin() {
        return new Result<>(false,BusinessCodeEnum.HTTP_NO_LOGIN.getMsg(),BusinessCodeEnum.HTTP_NO_LOGIN.getCode(),null);
    }
}

