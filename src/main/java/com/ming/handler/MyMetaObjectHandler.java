package com.ming.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component //记住一定要把处理器加入IOC容器中
public class MyMetaObjectHandler implements MetaObjectHandler {

    //插入时的填充策略
    //setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
    //更新是的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("updateTime",new Date(),metaObject);

    }
}
