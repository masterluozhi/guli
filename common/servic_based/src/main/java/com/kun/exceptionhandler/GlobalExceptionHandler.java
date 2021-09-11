package com.kun.exceptionhandler;


import com.kun.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return  R.error().message("执行了全局异常");
    }
    @ExceptionHandler(GuLiExceptionHandle.class)
    @ResponseBody
    public R GuLierror(GuLiExceptionHandle e){
        log.error(e.getMsg());
        e.printStackTrace();
        return  R.error().code(e.getCode()).message(e.getMsg());
    }
}
