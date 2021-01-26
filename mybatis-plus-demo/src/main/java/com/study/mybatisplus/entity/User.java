package com.study.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /*
        @TableId(type = IdType.AUTO) 自动增长策略
        INPUT:自己设置id值
        NONE:输入
        UUID：随机唯一值
        MP自带策略：
            ASSIGN_ID       ：MP自带策略，生成19位值，主键类型：Long,Integer,String
            ASSIGN_UUID     ：MP自带策略，生成19位值，主键类型：String
     */
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //版本号
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @TableLogic //3.3版本后不需要去注册插件
    private Integer deleted;
}
