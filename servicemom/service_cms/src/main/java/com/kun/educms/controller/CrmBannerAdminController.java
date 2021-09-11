package com.kun.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun.commonutils.R;
import com.kun.educms.entity.CrmBanner;
import com.kun.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author kun
 * @since 2021-09-04
 */
@Api("后台banner管理")
@RestController
@RequestMapping("/educms/bannerAdmin")
@CrossOrigin
public class CrmBannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    //分页查询
    @GetMapping("/selPage/{page}/{limit}")
    public R selPage(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> crmBannerPage = new Page<>(page, limit);
        crmBannerService.page(crmBannerPage, null);
        return R.ok().data("item", crmBannerPage.getRecords()).data("total", crmBannerPage.getTotal());
    }

    //添加
    @PostMapping("/addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.save(crmBanner);
        return R.ok();
    }

    //修改
    @PostMapping("/updataBanner")
    public R updataBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.updateById(crmBanner);
        return R.ok();
    }

    @DeleteMapping("/delBannerByid/{id}")
    public R delBannerByid(@PathVariable String id) {
        crmBannerService.removeById(id);
        return R.ok();
    }

    @GetMapping("/selBannerByid/{id}")
    public R selBannerByid(@PathVariable String id) {
        CrmBanner byId = crmBannerService.getById(id);
        return R.ok().data("item", byId);
    }
}



