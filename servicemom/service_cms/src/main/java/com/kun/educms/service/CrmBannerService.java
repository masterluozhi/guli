package com.kun.educms.service;

import com.kun.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author kun
 * @since 2021-09-04
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selAll();
}
