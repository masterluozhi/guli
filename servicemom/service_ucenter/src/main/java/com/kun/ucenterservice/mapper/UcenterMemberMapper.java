package com.kun.ucenterservice.mapper;

import com.kun.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author test.java
 * @since 2021-09-05
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    Integer countOneDay(String day);
}
