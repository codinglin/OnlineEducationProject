package com.study.demo.excel;

import com.alibaba.excel.EasyExcel;

public class TestEasyReadExcel {
    public static void main(String[] args) {
        //实现excel读操作
        //1.设置写入文件夹地址和excel文件名称
        String filename = "E:/write.xlsx";

        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }
}
