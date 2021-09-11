package com.kun.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kun.commonutils.JwtUtils;
import com.kun.commonutils.R;
import com.kun.eduorder.entity.TOrder;
import com.kun.eduorder.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author kun
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/eduorder/torder")
@CrossOrigin
public class TOrderController {
    @Autowired
    private TOrderService tOrderService;
    //根据课程和用户形象生成订单号
    @PostMapping("addOrderId/{courseId}")
    public R selOrder(@PathVariable String courseId, HttpServletRequest request){
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        String   orderNO= tOrderService.createOrderNo(courseId, memberIdByJwtToken);
        return R.ok().data("orderId", orderNO);
    }
    //根据订单id查询订单信息
    @GetMapping("/getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId){
        QueryWrapper<TOrder> tOrderQueryWrapper = new QueryWrapper<>();
        QueryWrapper<TOrder> order_no = tOrderQueryWrapper.eq("order_no", orderId);
        TOrder byId = tOrderService.getOne(order_no);
        return R.ok().data("item",byId);
    }

    @GetMapping("/selIsBuy/{courseId}/{memberId}")
    public  boolean selIsBuy(@PathVariable String courseId,@PathVariable String memberId){
        QueryWrapper<TOrder> tOrderQueryWrapper = new QueryWrapper<>();
        tOrderQueryWrapper.eq("course_id",courseId);
        tOrderQueryWrapper.eq("member_id",memberId);
        tOrderQueryWrapper.eq("status",1);
        int count = tOrderService.count(tOrderQueryWrapper);
        if (count>0){
        return   true;
      }else{
          return  false;
      }
    }
}

