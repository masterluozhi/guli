package com.kun.educms.controller;

import com.kun.commonutils.R;
import com.kun.educms.entity.CrmBanner;
import com.kun.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/educms/bannerFront")
public class CrmBannerFontController {
    @Autowired
    private CrmBannerService crmBannerService;
    @GetMapping("/selAllBanner")
    public R selAllBanner(){
        List<CrmBanner> crmBanners = crmBannerService.selAll();
        return R.ok().data("items",crmBanners);
    }
}
