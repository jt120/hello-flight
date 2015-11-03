package com.jt.web.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

/**
 * 城市对
 * since 2015/10/30.
 */
@Table("t_city_relation")
public class CityRelation extends BaseModel {

    @Id
    private int id;

    @Column
    private String fromCode;

    @Column
    private String toCode;

    @Many(target = Av.class, field = "cityRelationId")
    private List<Av> avs;

    @Many(target = Policy.class, field = "cityRelationId")
    private List<Policy> policies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromCode() {
        return fromCode;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public List<Av> getAvs() {
        return avs;
    }

    public void setAvs(List<Av> avs) {
        this.avs = avs;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CityRelation{");
        sb.append("id=").append(id);
        sb.append(", fromCode='").append(fromCode).append('\'');
        sb.append(", toCode='").append(toCode).append('\'');
        sb.append(", avs=").append(avs);
        sb.append(", policies=").append(policies);
        sb.append('}');
        return sb.toString();
    }
}
