package com.study.eduservice.controller;


import com.study.commonutils.ResponseEntity;
import com.study.eduservice.entity.subject.OneSubject;
import com.study.eduservice.entity.vo.SubjectVO;
import com.study.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author lin
 * @since 2021-01-29
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取上传过来的文件，把文件内容读取出来
    @PostMapping("addSubject")
    public ResponseEntity addSubject(MultipartFile file){
        //上传过来excel文件
        subjectService.saveSubject(file,subjectService);
        return ResponseEntity.success();
    }

    //课程分类列表
    //课上写法
    @GetMapping("getAllSubject")
    public ResponseEntity getAllSubject(){
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return ResponseEntity.success().data("list",list);
    }

    //自己写法
    @GetMapping("getAllSubjects")
    public ResponseEntity getAllSubjects(){
        List<SubjectVO> list = subjectService.listWithTree();

        return ResponseEntity.success().data("list",list);
    }
}

