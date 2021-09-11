package com.kun.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun.commonutils.JwtUtils;
import com.kun.commonutils.R;
import com.kun.eduservice.client.OrderClient;
import com.kun.eduservice.entity.EduCourse;
import com.kun.eduservice.entity.OneSubject;
import com.kun.eduservice.entity.chapter.ChapterVo;
import com.kun.eduservice.entity.frontVo.CourseFrontVo;
import com.kun.eduservice.entity.frontVo.CourseVudeoVo;
import com.kun.eduservice.entity.vo.CoursePublicVo;
import com.kun.eduservice.service.EDUCHAPTERervice;
import com.kun.eduservice.service.EDUCOURSEervice;
import com.kun.eduservice.service.EDUSUBJECTervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontConteoller {
    @Autowired
    private EDUCOURSEervice educoursEervice;
    @Autowired
    private EDUCHAPTERervice educhapteRervice;
    @Autowired
    private OrderClient orderClient;
    //条件查询分页
    @PostMapping("/getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {

        Map<String, Object> map = educoursEervice.pageList(page, limit, courseFrontVo);

       return R.ok().data("data", map );
    }
    //课程详情
    @GetMapping("/getFrontCourseInfo/{Id}")
    public  R getFrontCourseInfo(@PathVariable String Id, HttpServletRequest request){
        //根据课程id查询sql语句查询课程信息
        CourseVudeoVo baseCourseId = educoursEervice.getBaseCourseId(Id);
        //根据课程id查询章节小节语句查询课程信息
        List<ChapterVo> chapterVideo = educhapteRervice.getChapterVideo(Id);
        //根据课程和用户id查询转态
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        boolean b = orderClient.selIsBuy(Id, memberIdByJwtToken);
        return R.ok().data("baseCourse",baseCourseId).data("courseInfo",chapterVideo).data("isBuy",b);

    }
}
