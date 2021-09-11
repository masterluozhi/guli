package ucenterservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun.commonutils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ucenterservice.entity.EduComment;
import ucenterservice.service.EDUCOMMENTervice;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author test.java
 * @since 2021-09-07
 */
@RestController
@RequestMapping("/educomment")
@CrossOrigin
public class EduCommentController {
    @Autowired
    private EDUCOMMENTervice educommenTervice;

    @PostMapping("/pageComment/{page}/{limit}")
    public R pageComment(@PathVariable long page, @PathVariable long limit,String courseId) {
        Page<EduComment> pageS = new Page<>(page, limit);
        Map<String,Object> map= educommenTervice.pageList(pageS, courseId);
        return R.ok().data("data", map);
    }

    @GetMapping("selCommentBycourseId/{courseid}")
    public R selCommentBycourseId(@PathVariable String courseid) {
        QueryWrapper<EduComment> eduCommentQueryWrapper = new QueryWrapper<>();
        eduCommentQueryWrapper.eq("course_id", courseid);
        List<EduComment> list = educommenTervice.list(eduCommentQueryWrapper);
        return R.ok().data("items", list);
    }

    @PostMapping("addComment/BycourseId/{courseid}")
    public R addComment(@PathVariable String courseid, @RequestBody(required = false) EduComment eduComment) {
        eduComment.setCourseId(courseid);
        educommenTervice.save(eduComment);
        return R.ok();
    }
}

