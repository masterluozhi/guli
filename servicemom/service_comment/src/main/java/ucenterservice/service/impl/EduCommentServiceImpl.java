package ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ucenterservice.entity.EduComment;
import ucenterservice.mapper.EduCommentMapper;
import ucenterservice.service.EDUCOMMENTervice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2021-09-07
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EDUCOMMENTervice {

    @Override
    public Map<String, Object> pageList(Page<EduComment> pageS, String courseId) {
         QueryWrapper<EduComment> eduCommentQueryWrapper = new QueryWrapper<>();
         eduCommentQueryWrapper.eq("course_id", courseId);
         baseMapper.selectPage(pageS,eduCommentQueryWrapper);

        long total = pageS.getTotal();
        long current = pageS.getCurrent();
        long pages = pageS.getPages();
        List<EduComment> records = pageS.getRecords();
        long size = pageS.getSize();
        boolean hasPrevious = pageS.hasPrevious();
        boolean hasNext = pageS.hasNext();

        HashMap<String, Object> map= new HashMap<>();
        map.put("total",total);
        map.put("current",current);
        map.put("pages",pages);
        map.put("records",records);
        map.put("size",size);
        map.put("hasPrevious",hasPrevious);
        map.put("hasNext",hasNext);
        return map;
    }
}
