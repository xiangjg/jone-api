package com.jone.joneapi.entity;

import javax.persistence.*;

@Entity
@Table(name="c_weather_code",schema="sysdb")
public class WeatherCode {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "cid", nullable = false)
    private String cid;
    @Column(name="cname")
    private String cname;
    @Column(name="province")
    private String province;//省
    @Column(name="city")
    private String city;//市

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
