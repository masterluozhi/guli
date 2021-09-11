package com.kun.edustatic.service;

import com.kun.edustatic.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author kun
 * @since 2021-09-08
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
  void  registCountList(String day);

    Map<String,Object> getShowData(String type, String begin, String end);
}
