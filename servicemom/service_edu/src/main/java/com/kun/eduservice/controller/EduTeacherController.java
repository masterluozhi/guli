package com.kun.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun.commonutils.R;
import com.kun.eduservice.entity.EduCourse;
import com.kun.eduservice.entity.EduTeacher;
import com.kun.eduservice.entity.vo.EduTeacherVo;
import com.kun.eduservice.service.EDUTEACHERervice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author test.java
 * @since 2021-08-16
 */
@Api(description = "讲师管理功能")
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EDUTEACHERervice EduService;

    @ApiOperation("查找所有讲师")
    @GetMapping("findAll")
    public R findAll() {
        List<EduTeacher> list = EduService.list(null);


        return R.ok().data("item", list);
    }

    @ApiOperation("逻辑删除老师")
    @DeleteMapping("delLogic/{id}")
    public R delLogic(@PathVariable String id) {
        boolean b = EduService.removeById(id);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation("分页")
    @GetMapping("pages/{current}/{limit}")
    public R pages(@ApiParam(name = "current", value = "当前页",example = "0") @PathVariable long current,
                   @ApiParam(name = "limit", value = "每页几个元素",example = "0") @PathVariable long limit) {
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        EduService.page(teacherPage, null);

        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("条件分页")
    @PostMapping("pagesKind/{current}/{limit}")
    public R pagesKind(@ApiParam(name = "current", value = "当前页",example = "0") @PathVariable long current,
                       @ApiParam(name = "limit", value = "每页几个元素",example = "0") @PathVariable long limit,
                       @RequestBody(required = false) EduTeacherVo eduTeacherVodu
    ) {
 //mybatis分页对象
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
//条件查询
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = eduTeacherVodu.getName();
        Integer level = eduTeacherVodu.getLevel();
        Date gmtCreate = eduTeacherVodu.getGmtCreate();
        Date gmtModified = eduTeacherVodu.getGmtModified();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(gmtCreate)) {
            wrapper.ge("gmt_create", gmtCreate);
        }
        if (!StringUtils.isEmpty(gmtModified)) {
            wrapper.le("gmt_modified",gmtModified);
        }
//执行查询
        EduService.page(teacherPage, wrapper);
//总条数
        long total = teacherPage.getTotal();
        //符合条件条数
        List<EduTeacher> records = teacherPage.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody(required = false) EduTeacher eduTeacher) {
        boolean save = EduService.save(eduTeacher);
        if (save){
            return  R.ok();
        }else {
            return  R.error();
        }

    }
    @ApiOperation("根据id查询讲师")
    @GetMapping("selectTeacherById/{id}")
    public R selectTeacherById(@PathVariable String id) {
        EduTeacher byId = EduService.getById(id);
            return  R.ok().data("teacher",byId);
    }

    @ApiOperation("根据id修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody  EduTeacher eduTeacher) {
        boolean flag = EduService.updateById(eduTeacher);
        if (flag){
            return  R.ok();
        }else {
            return  R.error();
        }
    }
    @ApiOperation("根据老师姓名查id")
    @GetMapping("selTeacherByTeacherID")
    public R selTeacherByCourseID(String teacherName) {
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        eduTeacherQueryWrapper.eq("name",teacherName);
        EduTeacher one = EduService.getOne(eduTeacherQueryWrapper);
        return R.ok().data("id",one.getId());
    }
}

