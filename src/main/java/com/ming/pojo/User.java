package com.ming.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;


public class User {

    //主键自增策略
    //对应数据库中的主键(uuid、自增id、雪花算法、redis、zk)
    //    AUTO(0),  //主键自增，1.需要在字段上@TableId(type = IdType.AUTO) 2.数据库字段一定要设置自增
    //    NONE(1),  //不使用
    //    INPUT(2), //手动输入
    //    ID_WORKER(3),  默认用这个全局id(雪花算法)
    //    UUID(4),  //全局唯一uuid
    //    ID_WORKER_STR(5);  //id_work的字符串表示法
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    //代码级别的自动填充
    //需要编写处理器
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version //乐观锁version注解
    private Integer version;

    @TableLogic //逻辑删除
    private Integer deleted;

    public User(Long id, String name, Integer age, String email, Date createTime, Date updateTime, Integer version, Integer deleted) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.version = version;
        this.deleted = deleted;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", version=" + version +
                ", deleted=" + deleted +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
