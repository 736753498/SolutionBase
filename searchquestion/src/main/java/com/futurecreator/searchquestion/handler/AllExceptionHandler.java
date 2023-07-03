package com.futurecreator.searchquestion.handler;

import com.futurecreator.common.enums.BusinessCodeEnum;
import com.futurecreator.common.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//异常处理
@ControllerAdvice
public class AllExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.fail(BusinessCodeEnum.DEFAULT_SYS_ERROR);
    }
}