package com.study.eduservice.service;

import com.study.commonutils.utils.PageUtils;
import com.study.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author lin
 * @since 2021-01-26
 */
public interface EduTeacherService extends IService<EduTeacher> {
    PageUtils queryBaseAttrPage(Map<String, Object> params);
}
