package com.kun.ucenterservice.service;

import com.kun.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kun.ucenterservice.entity.vo.RegistVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author test.java
 * @since 2021-09-05
 */
public interface UCENTERMEMBERervice extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    void regist(RegistVo registVo);

    UcenterMember getByid(String memberIdByJwtToken);

    UcenterMember getMenberByOperid(String openid);

    Integer countOneDay(String day);
}
