package com.jt.web.model.resp;

import com.jt.web.model.BaseModel;
import com.jt.web.model.Rule;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Index;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.TableIndexes;
import org.nutz.json.JsonIgnore;

import java.util.List;

/**
 * since 2015/10/29.
 */
//@Table("t_routing")
//@TableIndexes({@Index(name = "r_id", unique = false, fields = {"ruleId"})})
public class Routing extends BaseModel {

    @JsonIgnore
    @Id
    private int id;

    @JsonIgnore
    @Column
    private int ruleId;

    /**
     * 可保存必要信息，之后生单按原值回转。最大1000个字符
     */
    private String data;

    /**
     * 没有该字段时，默认为RMB
     */
    @Column
    private String currency;

    /**
     * 成人单价
     */
    @Column
    @ColDefine(type = ColType.INT, unsigned = true)
    private int adultPrice;

    /**
     * 成人税费
     */
    @Column
    private int adultTax;

    /**
     * 儿童单价
     */
    @Column
    @ColDefine(type = ColType.INT, unsigned = true)
    private int childPrice;

    /**
     * 儿童税费
     */
    @Column
    private int childTax;

    /**
     * 乘客国籍类型:0全部/1适用/2不适用
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int nationalityType;

    /**
     * 乘客国籍，可以为空，若输入则为国家二字码，多个用,分隔，最多支持五个国家或地区
     */
    @Column
    private String nationality;

    /**
     * 适用乘客年龄，可以为空，输入格式为12~59，表示适用于12~59岁的乘客预订，年龄限制范围为12~99，仅支持录入一个年龄段
     */
    @Column
    private String suitAge;

    /**
     * 报价类型：0 普通价 / 1留学生价
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int priceType;

    /**
     * 报价类型：0 预定价 / 1申请价/2预约
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int applyType;

    /**
     * 成人税费类型：0 未含税 / 1已含税
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int adultTaxType;

    /**
     * 儿童税费类型：0 未含税 / 1已含税
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int childTaxType;

    /**
     * 出票速度：[1~10080] （以分钟为单位）
     */
    @Column
    private int ticketTimeLimit;

    /**
     * 报销凭证类型：0行程单/1旅行发票
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int ticketInvoiceType;

    /**
     * 退改签信息，参考 Rule Element
     */
    @One(target = Rule.class, field = "ruleId")
    private Rule rule;

    /**
     * 去程航段按顺序，参考下面的Segment Element
     * 如果为多程第一程、第二程的信息，全输出到此节点。
     */
    @Many(target = Segment.class,field = "routingId")
    private List<Segment> fromSegments;

    /**
     * 回程航段按顺序，参考下面的Segment Element（单程搜索为空）
     */
    @Many(target = Segment.class, field = "routingId")
    private List<Segment> retSegments;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getAdultTax() {
        return adultTax;
    }

    public void setAdultTax(int adultTax) {
        this.adultTax = adultTax;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }

    public int getChildTax() {
        return childTax;
    }

    public void setChildTax(int childTax) {
        this.childTax = childTax;
    }

    public int getNationalityType() {
        return nationalityType;
    }

    public void setNationalityType(int nationalityType) {
        this.nationalityType = nationalityType;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSuitAge() {
        return suitAge;
    }

    public void setSuitAge(String suitAge) {
        this.suitAge = suitAge;
    }

    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    public int getApplyType() {
        return applyType;
    }

    public void setApplyType(int applyType) {
        this.applyType = applyType;
    }

    public int getAdultTaxType() {
        return adultTaxType;
    }

    public void setAdultTaxType(int adultTaxType) {
        this.adultTaxType = adultTaxType;
    }

    public int getChildTaxType() {
        return childTaxType;
    }

    public void setChildTaxType(int childTaxType) {
        this.childTaxType = childTaxType;
    }

    public int getTicketTimeLimit() {
        return ticketTimeLimit;
    }

    public void setTicketTimeLimit(int ticketTimeLimit) {
        this.ticketTimeLimit = ticketTimeLimit;
    }

    public int getTicketInvoiceType() {
        return ticketInvoiceType;
    }

    public void setTicketInvoiceType(int ticketInvoiceType) {
        this.ticketInvoiceType = ticketInvoiceType;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public List<Segment> getFromSegments() {
        return fromSegments;
    }

    public void setFromSegments(List<Segment> fromSegments) {
        this.fromSegments = fromSegments;
    }

    public List<Segment> getRetSegments() {
        return retSegments;
    }

    public void setRetSegments(List<Segment> retSegments) {
        this.retSegments = retSegments;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Routing{");
        sb.append("id=").append(id);
        sb.append(", data='").append(data).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", adultPrice=").append(adultPrice);
        sb.append(", adultTax=").append(adultTax);
        sb.append(", childPrice=").append(childPrice);
        sb.append(", childTax=").append(childTax);
        sb.append(", nationalityType=").append(nationalityType);
        sb.append(", nationality='").append(nationality).append('\'');
        sb.append(", suitAge='").append(suitAge).append('\'');
        sb.append(", priceType=").append(priceType);
        sb.append(", applyType=").append(applyType);
        sb.append(", adultTaxType=").append(adultTaxType);
        sb.append(", childTaxType=").append(childTaxType);
        sb.append(", ticketTimeLimit=").append(ticketTimeLimit);
        sb.append(", ticketInvoiceType=").append(ticketInvoiceType);
        sb.append(", rule=").append(rule);
        sb.append(", fromSegments=").append(fromSegments);
        sb.append(", retSegments=").append(retSegments);
        sb.append('}');
        return sb.toString();
    }
}
