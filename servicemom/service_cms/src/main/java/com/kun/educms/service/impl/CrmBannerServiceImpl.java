package com.kun.educms.service.impl;

import com.kun.educms.entity.CrmBanner;
import com.kun.educms.mapper.CrmBannerMapper;
import com.kun.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;



import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author kun
 * @since 2021-09-04
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
@Cacheable(value = "banners",key="'selectBanners'")
    @Override
    public List<CrmBanner> selAll() {
        List<CrmBanner> crmBanners = baseMapper.selectList(null);
        return crmBanners;
    }
}
