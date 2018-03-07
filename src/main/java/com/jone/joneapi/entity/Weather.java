package com.jone.joneapi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="d_weather",schema="sysdb")
public class Weather {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "cid", nullable = false)
    private String cid;
    @Column(name="cname")
    private String cname;
    @Column(name="text")
    private String text;
    @Column(name="code")
    private Integer code;
    @Column(name="temperature")
    private Integer temperature;
    @Column(name="update_time")
    private Date updateTime;
    @Column(name="create_time")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
