package com.study.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.eduservice.entity.EduSubject;
import com.study.eduservice.entity.excel.SubjectData;
import com.study.eduservice.entity.subject.OneSubject;
import com.study.eduservice.entity.subject.TwoSubject;
import com.study.eduservice.entity.vo.SubjectVO;
import com.study.eduservice.listener.SubjectExcelListener;
import com.study.eduservice.mapper.EduSubjectMapper;
import com.study.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author lin
 * @since 2021-01-29
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try{
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //课程分类列表(树形)
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1.查询所有一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");

        List<EduSubject> oneSubjectList=baseMapper.selectList(wrapperOne);
        //2.查询所有二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList=baseMapper.selectList(wrapperTwo);

        //封装
        List<OneSubject> finalSubjectList = new ArrayList<>();
        
        //封装一级分类
        for (EduSubject subject : oneSubjectList) {
            OneSubject oneSubject=new OneSubject();
            BeanUtils.copyProperties(subject,oneSubject);
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (EduSubject eduSubject : twoSubjectList) {
                if(eduSubject.getParentId().equals(subject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjectList);
            finalSubjectList.add(oneSubject);
        }

        return finalSubjectList;
    }

    @Override
    public List<SubjectVO> listWithTree() {
        List<EduSubject> entities = list();

        List<SubjectVO> subjectVOS = entities.stream().map(subject -> {
            SubjectVO subjectVO = new SubjectVO();
            BeanUtils.copyProperties(subject, subjectVO);
            return subjectVO;
        }).collect(Collectors.toList());

        List<SubjectVO> list = subjectVOS.stream().filter(subjectVO ->
                subjectVO.getParentId().equals("0")
        ).map((menu)->{
            menu.setChildren(getChildrenData(menu,subjectVOS));
            return menu;
        }).collect(Collectors.toList());
        return list;
    }

    private List<SubjectVO> getChildrenData(SubjectVO root, List<SubjectVO> all) {
        List<SubjectVO> children = all.stream().filter(subjectVO ->
            subjectVO.getParentId().equals(root.getId())
        ).map(subjectVO -> {
            subjectVO.setChildren(getChildrenData(subjectVO,all));
            return subjectVO;
        }).collect(Collectors.toList());
        return children;
    }

}
