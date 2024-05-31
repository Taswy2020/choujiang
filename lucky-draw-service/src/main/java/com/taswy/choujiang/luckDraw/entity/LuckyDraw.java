package com.taswy.choujiang.luckDraw.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (LuckyDraw)实体类
 *
 * @author makejava
 * @since 2024-04-23 19:57:53
 */
public class LuckyDraw implements Serializable {
    private static final long serialVersionUID = -76750076112536398L;

    private Integer id;

    private String name;

    private String profile;

    private Date createtime;

    private Date endtime;

    private Integer capacity;

    private Integer participantNumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getParticipantNumber() {
        return participantNumber;
    }

    public void setParticipantNumber(Integer participantNumber) {
        this.participantNumber = participantNumber;
    }

}

