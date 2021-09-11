package com.kun.eduorder.controller;


import com.kun.commonutils.R;
import com.kun.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author kun
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/eduorder/paylog")
@CrossOrigin
public class PayLogController {
    @Autowired
    private PayLogService payLogService;
    //1.生成微信二维码
    @GetMapping("createNative/{orderNo}")
 public  R createNative(@PathVariable String orderNo){
       Map<String,Object> map= payLogService.createNative(orderNo);
       return R.ok().data("map",map);
    }
    //查询支付状态
    @GetMapping("selPayStatus/{orderId}")
    public  R selPayStatus(@PathVariable String orderId){
      Map<String,String>  map=payLogService.selPayStatus(orderId);
      if (map==null){
          return R.error().message("支付出错了");
      }
      if (map.get("trade_state").equals("SUCCESS")){
          payLogService.updataOrderStatus(map);
          return R.ok().message("支付成功");
      }
        return R.error().code(25000).message("支付中");
    }
}


