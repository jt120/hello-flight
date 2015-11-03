package com.jt.web.model;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

/**
 * 城市
 * since 2015/10/30.
 */
@Table("t_city")
public class City extends BaseModel {

    @Id
    private int id;

    @Name
    private String code;

    @Column("name")
    private String key;

    @Column("info")
    @ColDefine(width = 1000)
    private String display;

    @Column
    private String country;

    @Many(target = CityRelation.class, field = "fromCode")
    private List<CityRelation> fromRelations;

    @Many(target = CityRelation.class, field = "toCode")
    private List<CityRelation> toRelations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<CityRelation> getFromRelations() {
        return fromRelations;
    }

    public void setFromRelations(List<CityRelation> fromRelations) {
        this.fromRelations = fromRelations;
    }

    public List<CityRelation> getToRelations() {
        return toRelations;
    }

    public void setToRelations(List<CityRelation> toRelations) {
        this.toRelations = toRelations;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", key='").append(key).append('\'');
        sb.append(", display='").append(display).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", fromRelations=").append(fromRelations);
        sb.append(", toRelations=").append(toRelations);
        sb.append('}');
        return sb.toString();
    }
}
