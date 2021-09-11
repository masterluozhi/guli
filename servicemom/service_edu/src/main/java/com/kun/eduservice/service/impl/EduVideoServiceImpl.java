package com.kun.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kun.eduservice.client.VodClient;
import com.kun.eduservice.entity.EduVideo;
import com.kun.eduservice.mapper.EduVideoMapper;
import com.kun.eduservice.service.EDUVIDEOervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2021-08-23
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EDUVIDEOervice {
@Autowired
private VodClient vodClient;
    @Override
    public void delVoideByCouseId(String courseId) {
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        QueryWrapper<EduVideo> video_source_id = eduVideoQueryWrapper.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(eduVideoQueryWrapper);

        ArrayList<String> vidList = new ArrayList<>();
        for (int i = 0; i <eduVideos.size() ; i++) {
            EduVideo eduVideo = eduVideos.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)){
                vidList.add(videoSourceId );
            }
        }
        if (vidList.size()>0){
            vodClient.delAll(vidList);
        }

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
