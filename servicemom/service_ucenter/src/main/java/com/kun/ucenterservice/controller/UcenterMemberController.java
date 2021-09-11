package com.kun.ucenterservice.controller;


import com.kun.commonutils.JwtUtils;
import com.kun.commonutils.R;
import com.kun.commonutils.UcenterMemberOrder;
import com.kun.ucenterservice.Utils.ConstantWxUtils;
import com.kun.ucenterservice.entity.UcenterMember;
import com.kun.ucenterservice.entity.vo.RegistVo;
import com.kun.ucenterservice.service.UCENTERMEMBERervice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author test.java
 * @since 2021-09-05
 */
@RestController
@RequestMapping("/educenter")
@CrossOrigin
public class UcenterMemberController {
@Autowired
    private UCENTERMEMBERervice centerRervice;
@PostMapping("/login")
    public R login(@RequestBody(required = false) UcenterMember ucenterMember){
    String token = centerRervice.login(ucenterMember);
    System.out.println(token);
    return  R.ok().data("token",token);
}
    @PostMapping("/regist")
    public R regist(@RequestBody RegistVo registVo){
       centerRervice.regist( registVo);
        return  R.ok();
    }
    @GetMapping("/getMemberInfo")
    public  R getMemberInfo(HttpServletRequest request){
    //根据request中的token获取id
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
    //根据id获取昵称和头像
      UcenterMember ucenterMember=centerRervice.getByid(memberIdByJwtToken);
      return R.ok().data("userInfo",ucenterMember);
    }
    //根据id获得用户信息
      @GetMapping("getUserInfoByid/{id}")
    public UcenterMemberOrder getUserInfoByid(@PathVariable String id){
          UcenterMember byid = centerRervice.getByid(id);
          UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
          BeanUtils.copyProperties(byid,ucenterMemberOrder);
          return  ucenterMemberOrder ;
      }
      @GetMapping("/countOneDay/{day}")
    public  R countOneDay(@PathVariable String day){
        Integer sum =  centerRervice.countOneDay(day);
         return R.ok().data("countRegister",sum);
      }
}

