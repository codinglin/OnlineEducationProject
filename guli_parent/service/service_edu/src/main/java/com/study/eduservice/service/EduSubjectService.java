package com.study.eduservice.service;

import com.study.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.eduservice.entity.subject.OneSubject;
import com.study.eduservice.entity.vo.SubjectVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author lin
 * @since 2021-01-29
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();

    List<SubjectVO> listWithTree();
}
