package com.kun.eduorder.service.impl;

import com.kun.commonutils.CoursePublicOrder;
import com.kun.commonutils.UcenterMemberOrder;
import com.kun.eduorder.client.EduClient;
import com.kun.eduorder.client.UcenterClienr;
import com.kun.eduorder.entity.TOrder;
import com.kun.eduorder.mapper.TOrderMapper;
import com.kun.eduorder.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kun.eduorder.util.OrderNoUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author kun
 * @since 2021-09-08
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
@Autowired
private EduClient eduClient;
@Autowired
private UcenterClienr ucenterClienr;
    @Override
    public String createOrderNo(String courseId, String memberIdByJwtToken) {
        CoursePublicOrder courseInfo = eduClient.getCourseInfo(courseId);
        UcenterMemberOrder userInfoByid = ucenterClienr.getUserInfoByid(memberIdByJwtToken);
        TOrder tOrder = new TOrder();
        tOrder.setOrderNo(OrderNoUtil.getOrderNo());
        tOrder.setCourseId(courseId);
        tOrder.setCourseTitle(courseInfo.getTitle());
        tOrder.setCourseCover(courseInfo.getCover());
        tOrder.setTeacherName(courseInfo.getTeacherName());
        tOrder.setTotalFee(courseInfo.getPrice());

        tOrder.setMemberId(userInfoByid.getId());
        tOrder.setMobile(userInfoByid.getMobile());
        tOrder.setNickname(userInfoByid.getNickname());


        tOrder.setStatus(0);
        tOrder.setPayType(1);
        baseMapper.insert(tOrder);
        return tOrder.getOrderNo();
    }
}
