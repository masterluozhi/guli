package com.kun.edustatic.controller;


import com.kun.commonutils.R;
import com.kun.edustatic.client.UcentClient;
import com.kun.edustatic.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author kun
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/edustatic/statistics-daily")
@CrossOrigin
public class StatisticsDailyController {
@Autowired
    private StatisticsDailyService staticService;

//统计注册人数并添加到static表中
@PostMapping("registCount/{day}")
    public R  registCount(@PathVariable String day ) {
    staticService.registCountList(day);
    return R.ok();
}
    //图表显示，返回两部分数据，日期json数组，数量json数组
    @GetMapping("showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,@PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = staticService.getShowData(type,begin,end);
        return R.ok().data("data",map);
    }
}

