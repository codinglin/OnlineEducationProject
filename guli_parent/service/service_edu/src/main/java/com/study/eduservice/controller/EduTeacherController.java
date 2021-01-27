package com.study.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.commonutils.ResponseEntity;
import com.study.commonutils.utils.PageUtils;
import com.study.eduservice.entity.EduTeacher;
import com.study.eduservice.entity.vo.TeacherQuery;
import com.study.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lin
 * @since 2021-01-26
 */
@Api(tags = {"讲师管理"})
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;


    //1.查询讲师表所有数据
    @ApiOperation("所有讲师列表")
    @GetMapping("/findAll")
    public ResponseEntity findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return ResponseEntity.success().data("items",list);
    }

    //2.逻辑删除讲师的方法
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("/{id}")
    public ResponseEntity removeTeacher(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable("id") String id){
        boolean flag = teacherService.removeById(id);
        if(flag){
            return ResponseEntity.success();
        }else{
            return ResponseEntity.error();
        }
    }

    //3 分页查询讲师的方法
    //current 当前页
    //limit 每页记录数
    @GetMapping("pageTeacher/{current}/{limit}")
    public ResponseEntity pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //调用方法实现分页
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        teacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合

        return ResponseEntity.success().data("total",total).data("rows",records);
    }

    //4.条件查询带分页的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public ResponseEntity pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        //创建page对象
//        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
//
//        //构建条件
//        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
//        // 多条件组合查询
//        // mybatis学过 动态sql
//        String name = teacherQuery.getName();
//        Integer level = teacherQuery.getLevel();
//        String begin = teacherQuery.getBegin();
//        String end = teacherQuery.getEnd();
//        //判断条件值是否为空，如果不为空拼接条件
//        if(!StringUtils.hasLength(name)) {
//            //构建条件
//            wrapper.like("name",name);
//        }
//        if(!StringUtils.isEmpty(level)) {
//            wrapper.eq("level",level);
//        }
//        if(!StringUtils.hasLength(begin)) {
//            wrapper.ge("gmt_create",begin);
//        }
//        if(!StringUtils.hasLength(end)) {
//            wrapper.le("gmt_create",end);
//        }
//
//        //调用方法实现条件查询分页
//        teacherService.page(pageTeacher,wrapper);
//
//        long total = pageTeacher.getTotal();//总记录数
//        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合

        Map<String,Object> params = new HashMap<>();
        params.put("current",String.valueOf(current));
        params.put("limit",String.valueOf(limit));
        params.put("teacherQuery",teacherQuery);
        PageUtils page = teacherService.queryBaseAttrPage(params);
        return ResponseEntity.success().data("page",page);
    }
}

