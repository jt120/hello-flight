package com.jt.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;

import java.util.Date;

/**
 * since 2015/10/29.
 */
public class BaseModel {

    @JsonIgnore
    @Column("ct")
    @ColDefine(customType = "timestamp")
    private Date createTime;

    @JsonIgnore
    @Column("ut")
    @ColDefine(customType = "timestamp")
    private Date updateTime;

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
}
