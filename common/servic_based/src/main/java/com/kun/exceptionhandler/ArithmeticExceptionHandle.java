package com.kun.exceptionhandler;




import com.kun.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
public class ArithmeticExceptionHandle extends  RuntimeException{
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return  R.error().message("执行了ArithmeticException异常");
    }
}
