package com.futurecreator.searchquestion.handler;

import com.futurecreator.common.enums.TransCode;
import com.futurecreator.common.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//异常处理
@ControllerAdvice
public class AllExceptionHandler {
    /**
     * 当业务出现异常时给client返回sys error code
     * @param ex
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.fail(TransCode.DEFAULT_SYS_ERROR);
    }
}