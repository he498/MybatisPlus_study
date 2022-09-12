package com.ming;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.mapper.UserMapper;
import com.ming.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;


    @Test
    void contextLoads() {
        //查询name不为空的用户，并且邮箱不为空的用户,年龄大于等于12
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age",12);
        userMapper.selectList(wrapper).forEach(System.out::println);

    }

    @Test
    public void test2(){
        //查询名字 kk
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","kk");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);

    }

    @Test
    public void test3(){
        //查询20-30岁之间有多少
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",20,30);
        Integer count = userMapper.selectCount(wrapper); //查询结果数
        System.out.println(count);

    }


    @Test
    public void test4(){
        //模糊查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //左和右 %e%
        wrapper.notLike("name","e")
                .likeRight("email","t");
        List<Map<String,Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }


    @Test
    public void test5(){

        QueryWrapper<User> wrapper = new QueryWrapper<>();
       //id 在子查询中查出来
        wrapper
                .inSql("id","select id from user where id < 3");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    @Test
    public void test6(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过id排序
        wrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
