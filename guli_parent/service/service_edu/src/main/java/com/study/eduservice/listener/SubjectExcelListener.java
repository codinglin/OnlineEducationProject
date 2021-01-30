package com.study.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.commonutils.Exception.RRException;
import com.study.commonutils.enums.ResultCodeEnum;
import com.study.eduservice.entity.EduSubject;
import com.study.eduservice.entity.excel.SubjectData;
import com.study.eduservice.service.EduSubjectService;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //因为SubjectExcelListener不能交给spring管理，需要自己new，不能注入其他对象
    //不能实现数据库操作
    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //读取excel内容，一行一行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw new RRException("文件数据为空", ResultCodeEnum.ERROR.getCode());
        }
        //一行一行读取，每次读取有两个值，第一个值为一级分类，第二个值为二级分类
        //判断一级分类是否重复
        EduSubject existOneSubject = existOneSubject(subjectData.getOneSubjectName());
        if(existOneSubject == null){ //没有相同一级分类，进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());//一级分类名称
            subjectService.save(existOneSubject);
        }
        String pid=existOneSubject.getId();
        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject = existTwoSubject(subjectData.getTwoSubjectName(),pid);
        if(existTwoSubject == null){ //没有相同二级分类，进行添加
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//二级分类名称
            subjectService.save(existTwoSubject);
        }
    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(String name){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject=subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(String name,String pid){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject=subjectService.getOne(wrapper);
        return twoSubject;
    }


    //判断二级分类不能重复添加

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
