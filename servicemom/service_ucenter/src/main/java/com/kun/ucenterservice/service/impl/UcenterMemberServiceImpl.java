package com.kun.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kun.commonutils.JwtUtils;
import com.kun.commonutils.MD5;
import com.kun.exceptionhandler.GuLiExceptionHandle;
import com.kun.ucenterservice.entity.UcenterMember;
import com.kun.ucenterservice.entity.vo.RegistVo;
import com.kun.ucenterservice.mapper.UcenterMemberMapper;
import com.kun.ucenterservice.service.UCENTERMEMBERervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2021-09-05
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UCENTERMEMBERervice {
@Autowired
private RedisTemplate<String,String> redisTemplate;
    @Override
    public String login(UcenterMember ucenterMember) {
        //获取手机号和密码
        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();
        if(StringUtils.isEmpty(mobile)|| StringUtils.isEmpty(password)){
            throw  new GuLiExceptionHandle(20001,"登入失败");
        };
         //判断手机号是否正确
        QueryWrapper<UcenterMember> ucenterMemberQueryWrapper = new QueryWrapper<>();
        ucenterMemberQueryWrapper.eq("mobile",mobile);
        UcenterMember ucenterMember1 = baseMapper.selectOne(ucenterMemberQueryWrapper);
        if (ucenterMember1==null){
            throw  new GuLiExceptionHandle(20001,"登入失败");
        }
        //判断密码
        String encrypt = MD5.encrypt(password);
        if (!encrypt.equals(ucenterMember1.getPassword())){
            throw  new GuLiExceptionHandle(20001,"登入失败");
        }
        //判断是否禁用
        if(ucenterMember1.getIsDisabled()){
            throw  new GuLiExceptionHandle(20001,"登入失败");
        }
           //登入成功生成token字符串
        String jwtToken = JwtUtils.getJwtToken(ucenterMember1.getId(), ucenterMember1.getNickname());
        return jwtToken;
    }

    @Override
    public void regist(RegistVo registVo) {
        //获取注册数据
        String code = registVo.getCode();
        String mobile = registVo.getMobile();
        String nickename = registVo.getNickname();
        String password =  registVo.getPassword();
        if (StringUtils.isEmpty(code)||StringUtils.isEmpty(mobile)||StringUtils.isEmpty(nickename)||StringUtils.isEmpty(password )){
            throw  new GuLiExceptionHandle(20001,"注册失败");
        }
        String rediscode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(rediscode)){
            throw  new GuLiExceptionHandle(20001,"注册失败");
        }
        QueryWrapper<UcenterMember> ucenterMemberQueryWrapper = new QueryWrapper<>();
        ucenterMemberQueryWrapper.eq("mobile", mobile);
        Integer integer = baseMapper.selectCount(ucenterMemberQueryWrapper);
        if (integer>=1){
            throw  new GuLiExceptionHandle(20001,"注册失败");
        }
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setNickname(nickename);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
            baseMapper.insert(ucenterMember);
    }

    @Override
    public UcenterMember getByid(String memberIdByJwtToken) {
        UcenterMember ucenterMember = baseMapper.selectById(memberIdByJwtToken);

        return ucenterMember;
    }

    @Override
    public UcenterMember getMenberByOperid(String openid) {
        QueryWrapper<UcenterMember> ucenterMemberQueryWrapper = new QueryWrapper<>();
        ucenterMemberQueryWrapper.eq("openid",openid);
        UcenterMember ucenterMember = baseMapper.selectOne(ucenterMemberQueryWrapper);

        return ucenterMember;
    }

    @Override
    public Integer countOneDay(String day) {
        Integer integer = baseMapper.countOneDay(day);
        return integer;
    }
}
