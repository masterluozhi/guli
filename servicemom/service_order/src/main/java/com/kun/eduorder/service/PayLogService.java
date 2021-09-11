package com.kun.eduorder.service;

import com.kun.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author kun
 * @since 2021-09-08
 */
public interface PayLogService extends IService<PayLog> {

    Map<String,Object> createNative(String orderNo);

    Map<String,String> selPayStatus(String orderId);

    void updataOrderStatus(Map<String,String> map);
}
