package ucenterservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.IService;
import ucenterservice.entity.EduComment;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author test.java
 * @since 2021-09-07
 */
public interface EDUCOMMENTervice extends IService<EduComment> {

    Map<String,Object> pageList(Page<EduComment> pageS, String courseId);
}
