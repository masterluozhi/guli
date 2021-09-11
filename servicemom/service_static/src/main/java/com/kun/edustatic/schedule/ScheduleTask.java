package com.kun.edustatic.schedule;

import com.kun.edustatic.service.StatisticsDailyService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

@Component
public class ScheduleTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;
    @Scheduled(cron ="0 0 1 * * ? " )
    public  void  run(){
        Date date = DateUtils.addDays(new Date(), -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        String format = simpleDateFormat.format(DateUtils.addDays(new Date(), -1));
        statisticsDailyService.registCountList(format);
    }
}
