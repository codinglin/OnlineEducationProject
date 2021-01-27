package com.study.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.study.commonutils.utils.PageUtils;
import com.study.commonutils.utils.Query;
import com.study.eduservice.entity.EduTeacher;
import com.study.eduservice.entity.vo.TeacherQuery;
import com.study.eduservice.mapper.EduTeacherMapper;
import com.study.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author lin
 * @since 2021-01-26
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {


    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params) {
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        TeacherQuery teacherQuery= (TeacherQuery) params.get("teacherQuery");
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(StringUtils.hasLength(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(StringUtils.hasLength(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(StringUtils.hasLength(end)) {
            wrapper.le("gmt_create",end);
        }
        IPage<EduTeacher> page = this.page(
                new Query<EduTeacher>().getPage(params),
                wrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        List<EduTeacher> records = page.getRecords(); //数据list集合
        pageUtils.setList(records);
        return pageUtils;
    }
}
