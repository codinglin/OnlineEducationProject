package com.study.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.mybatisplus.entity.User;
import com.study.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jws.soap.SOAPBinding;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusDemoApplicationTests {
    @Autowired
    private UserMapper userMapper;

    // 查询user表所有数据
    @Test
    void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    // 添加操作
    /*
        数据库中没有把id设置为单调递增
        mybatisPlus会自动设置id值（主键）（生成19位)

        主键策略
            自动增长    AUTO INCREMENT
            UUID      每次生成随机唯一的值    缺点：排序不方便
            Redis实现
            mp自带策略  snowflake算法
    */
    @Test
    void addUser(){
        User user=new User();
        user.setName("岳不群");
        user.setAge(30);
        user.setEmail("luck@qq.com");
        int insert = userMapper.insert(user);
        System.out.println("insert"+insert);
    }

    //修改操作
    @Test
    void updateUser(){
        User user=new User();
        user.setId(1353735866713145346L);
        user.setAge(40);
        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    //测试乐观锁
    @Test
    void testOptimisticLocker(){
        //根据id查询数据
        User user = userMapper.selectById(1353877084386426882L);
        //进行修改
        user.setAge(200);
        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    // 多个id批量查询
    @Test
    public void testSelectDemo1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(users);
    }

    // 简单的条件查询
    @Test
    void testSelectByMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","Jone");
        map.put("age",18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //分页查询
    @Test
    void testPage(){
        //1.创建page对象
        //传入两个参数:当前页和每页显示记录数
        Page<User> page=new Page<>(1,3);
        //调用mp分页查询方法
        //调用mp分页查询过程中，底层封装，把分页所有数据分装到page对象里面
        userMapper.selectPage(page,null);

        //通过page对象获取分页数据
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.getPages());//总页数

        System.out.println(page.hasNext());//是否有下页
        System.out.println(page.hasPrevious());//是否有上页
    }

    // 删除操作 物理删除
    // 配置后，可变为逻辑删除
    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(1353891429182877698L);
        System.out.println(result);
    }

    //批量删除
    @Test
    public void testDeleteBatchIds(){
        int result = userMapper.deleteBatchIds(Arrays.asList(1,2));
        System.out.println(result);
    }

    //mp实现复杂查询操作
    @Test
    public void testSelectQuery(){
        //创建QueryWrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过QueryWrapper设置条件
        //ge、gt、le、lt    >=   >    <=    <

        //查询age>=30记录
        //第一个参数字段名称，第二个参数设置值
        wrapper.ge("age",30);

        //eq、ne    =   !=
        wrapper.eq("name","lilei");

        //between   范围
        wrapper.between("age",20,30);

        //like   模糊查询
        wrapper.like("name","岳");

        //orderByDesc 降序 orderByAsc升序     排序
        wrapper.orderByDesc("id");

        //last  语句最后拼接
        wrapper.last("limit 1");

        //指定要查询的列
        wrapper.select("id","name");

        List<User> users= userMapper.selectList(wrapper);
        System.out.println(users);
    }

}
