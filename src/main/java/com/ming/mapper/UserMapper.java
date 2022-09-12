package com.ming.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.pojo.User;
import org.springframework.stereotype.Repository;

//在对应的Mapper上面实现基本的接口 BaseMapper
@Repository //代表持久层
public interface UserMapper extends BaseMapper<User> {
    //所有的CRUD已经实现
}
