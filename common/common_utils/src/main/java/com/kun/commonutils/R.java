package com.kun.commonutils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
//统一返回结果
public class R {

    private Boolean success;


    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    private R() {
    }

    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.EOORE);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }
    public R data(String key,Object valve){
        this.data.put(key,valve);
        return  this;

    }
}
