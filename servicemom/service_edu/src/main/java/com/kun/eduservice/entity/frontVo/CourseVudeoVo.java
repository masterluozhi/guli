package com.kun.eduservice.entity.frontVo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseVudeoVo {
    @ApiModelProperty(value = "课程ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;
    @ApiModelProperty(value = "总课时",example = "0")
    private Integer lessonNum;
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "销售数量")
    private Long buyCount;
    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;
    @ApiModelProperty(value = "课程简介")
    private  String description;
    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;
    @ApiModelProperty(value = "课程讲师姓名")
    private  String teachername;
    @ApiModelProperty(value = "课程讲师资历")
    private  String intro;
    @ApiModelProperty(value = "课程讲师头像")
    private  String avatar;
    @ApiModelProperty(value = "一级分类")
    private String oneSubject;
    @ApiModelProperty(value = "二级分类")
    private String twoSubject;
    @ApiModelProperty(value = "一级分类id")
    private String oneSubjectId;
    @ApiModelProperty(value = "二级分类id")
    private String twoSubjectId;
}
