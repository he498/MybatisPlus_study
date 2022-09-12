package com.ming;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ming.mapper.UserMapper;
import com.ming.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //所有的方式继承于父类
    @Test
    void contextLoads() {
        //参数是一个Wrapper,条件构造器，这里先不用，null
        //查询全部用户
        List<User> users = userMapper.selectList(null);
//        for (User user : users) {
//            System.out.println(user);
//        }
        users.forEach(System.out::println);
    }

//    测试插入
    @Test
    public void testInset(){
        User user = new User();
        user.setAge(3);
        user.setName("ming");
        user.setEmail("1111111@qq.com");
        int result = userMapper.insert(user);   //自动帮我们生成id，id会自增
        System.out.println(result);
        System.out.println(user);

        //雪花算法（SnowFlake）
        //SnowFlake算法生成id的结果是一个64bit大小的整数
//        1、1bit，不用，因为二进制中最高位是符号位，1表示负数，0表示正数。生成的id一般都是用整数，所以最高位固定为0。
//        2、41bit时间戳，毫秒级。可以表示的数值范围是 （2^41-1），转换成单位年则是69年。
//        3、10bit工作机器ID，用来表示工作机器的ID，包括5位datacenterId和5位workerId。
//        4、12bit序列号，用来记录同毫秒内产生的不同id，12位可以表示的最大整数为4095，来表示同一机器同一时间截（毫秒)内产生的4095个ID序号。

    }

    //测试更新
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(6L);
        user.setName("kk");
        //虽然是byId但是参数是一个对象！！！
        int i = userMapper.updateById(user);
        System.out.println(i);
    }


    //乐观锁实现
    //1.取出记录时，获取当前version
    //2.更新时，带上这个version
    //3.执行更新时，set version = newVersion where version = oldVersion
    //如果version不对则会更新失败
    //先查询获得版本号，更新的时候set version+1且判断version == 获得的version

    //mp 乐观锁插件：先增加字段version
    //测试乐观锁
    @Test
    public void testOptimisticLocker(){
        //测试成功！
        //1.查询用户信息
        User user = userMapper.selectById(1L);
        //2.修改用户信息;
        user.setName("mm");
        user.setEmail("222222@qq.com");
        user.setAge(99);
        //执行修改
        userMapper.updateById(user);

    }
    @Test
    public void testOptimisticLocker2(){
        //模拟失败
        //线程1
        User user = userMapper.selectById(1L);
        user.setName("mm");
        user.setEmail("222222@qq.com");
        user.setAge(99);
        //线程2
        User user2 = userMapper.selectById(1L);
        user2.setName("kk");
        userMapper.updateById(user2);
        //可以使用自旋锁来多次尝试提交
        userMapper.updateById(user); //如果没有乐观锁就会覆盖值
    }


    //测试查询
    //批量查询测试
    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }
    //按条件查询map
    @Test
    public void testSelectByMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","kk");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //分页查询
    @Test
    public void testPage(){
        //参数1:当前页
        //参数2:页面大小
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal()); //总数
        System.out.println(page.getCurrent()); //当前页面
        System.out.println(page.getPages()); //总共分了多少页
    }

    //测试删除
    @Test
    public void testDeleteById(){
        userMapper.deleteById(1532994152659189764L);
    }
    //批量删除
    @Test
    public void testDeleteBatchId(){
        userMapper.deleteBatchIds(Arrays.asList(1532994152659189763L,1532994152659189762L,1532994152659189761L,1532994152659189765L));
    }
    //通过map删除
    @Test
    public void testDeleteMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","ming");
        userMapper.deleteByMap(map);
    }
    //逻辑删除：在数据库中没有被移除，而是通过一个变量让他失效,deleted = 0 => deleted = 1,回收站原理
    //物理删除：从数据库中直接移除
    //在数据表中增加一个deleted 字段
    //测试
    @Test
    public void logicDelete(){

        //userMapper.deleteById(1L); //走的是更新操作
        User user = userMapper.selectById(1l); //删除后查询测试，Preparing: SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE id=? AND deleted=0
        System.out.println(user);
    }





}
