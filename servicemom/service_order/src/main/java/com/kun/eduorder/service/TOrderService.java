package com.kun.eduorder.service;

import com.kun.eduorder.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author kun
 * @since 2021-09-08
 */
public interface TOrderService extends IService<TOrder> {

    String createOrderNo(String courseId, String memberIdByJwtToken);
}
