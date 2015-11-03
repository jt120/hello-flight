package com.jt.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 退改签信息
 * since 2015/10/29.
 */
@Table("t_rule")
public class Rule extends BaseModel {

    @JsonIgnore
    @Id
    private int id;
    /**
     * 是否允许退票：0表示不允许，1表示允许
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int hasRefund;

    /**
     * 可输入格式如：200-72-300-48-1000-0-*，代表72小时前退票手续费200（单位以输入的币种为准）；48小时到72小时之间，退票手续费300（单位以输入的币种为准）；飞机起飞不足48小时，手续费1000
     * （单位以输入的币种为准）；起飞后不予退票（输入*），为空表示按航司规定
     */
    @Column
    private String refund;

    /**
     * 部分退票规则，0表示不允许，1表示允许；单程直飞为0
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int partRefund;

    /**
     * 1：部分退票费用，为空或正整数，为空表示按航司规定执行（单位以所输入的币种为准，如：USD, EUR, 或IDR等）
     * 2：当currency字段输入不合法，或输入不为支付中心支持的币种符号，该字段不返回结果
     */
    @Column
    private int partRefundPrice;

    /**
     * 是否允许改期：0表示不允许，1表示允许
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int hasEndorse;

    /**
     * 可输入格式如：200-72-300-48-1000-0-*，代表72小时前改期手续费200（单位以输入的币种为准）；48小时到72小时之间，改期手续费300（单位以输入的币种为准）；飞机起飞不足48小时，手续费1000
     * （单位以输入的币种为准）；起飞后不予改期（输入*），为空表示按航司规定
     */
    @Column
    private String endorse;

    /**
     * 部分改期规则，0表示不允许，1表示允许；单程直飞为0
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int partEndorse;

    /**
     * 1：部分改期费用，为空或正整数，为空表示按航司规定执行（单位以所输入的币种为准，如：USD, EUR, 或IDR等）
     * 2：当currency字段输入不合法，或输入不为支付中心支持的币种符号，该字段不返回结果
     */
    @Column
    @ColDefine(type = ColType.INT, unsigned = true)
    private int partEndorsePrice;

    /**
     * 签转规则，0表示不支持，1表示支持
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int endorsement;

    /**
     * 是否提供免费行李额：0表示不提供，1表示提供
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int hasBaggage;

    /**
     * 单程直飞时，样例：1-23，表示1PC，23kg；单程中转和往返直飞录入1-23;1-23，中间用分号隔开；往返中转录入1-23;1-23;1-23;1-23。如某段为空表示按航空公司规定执行，如“-;-;-;
     * -”表示四段均按航司规定执行（其中分号不可缺少）
     */
    @Column
    private String baggage;

    /**
     * 是否有noshow规则，0表示没有规则限制，1表示有规则限制
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int hasNoShow;

    /**
     * Noshow时限，输入正整数
     */
    @Column
    @ColDefine(type = ColType.INT, unsigned = true)
    private int noShowLimitTime;

    /**
     * 1：Noshow罚金，可为空，若输入则为正整数。输入为空表示按照航司规定执行（单位以所输入的币种为准，如：USD, EUR, 或IDR等）
     * 2： 当currency字段输入不合法，或输入不为支付中心支持的币种符号，该字段不返回结果
     */
    @Column
    @ColDefine(type = ColType.INT, unsigned = true)
    private int penalty;

    /**
     * Noshow特殊规则：0（无） 1（Noshow时限内不允许退票） 2（Noshow时限内不允许改期） 3（Noshow时限内不允许退票和改期），
     */
    @Column
    @ColDefine(customType = "tinyint", unsigned = true)
    private int specialNoShow;

    /**
     * 其他说明，最大 300 个字符
     */
    @Column
    @ColDefine(width = 1000)
    private String other;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHasRefund() {
        return hasRefund;
    }

    public void setHasRefund(int hasRefund) {
        this.hasRefund = hasRefund;
    }

    public String getRefund() {
        return refund;
    }

    public void setRefund(String refund) {
        this.refund = refund;
    }

    public int getPartRefund() {
        return partRefund;
    }

    public void setPartRefund(int partRefund) {
        this.partRefund = partRefund;
    }

    public int getPartRefundPrice() {
        return partRefundPrice;
    }

    public void setPartRefundPrice(int partRefundPrice) {
        this.partRefundPrice = partRefundPrice;
    }

    public int getHasEndorse() {
        return hasEndorse;
    }

    public void setHasEndorse(int hasEndorse) {
        this.hasEndorse = hasEndorse;
    }

    public String getEndorse() {
        return endorse;
    }

    public void setEndorse(String endorse) {
        this.endorse = endorse;
    }

    public int getPartEndorse() {
        return partEndorse;
    }

    public void setPartEndorse(int partEndorse) {
        this.partEndorse = partEndorse;
    }

    public int getPartEndorsePrice() {
        return partEndorsePrice;
    }

    public void setPartEndorsePrice(int partEndorsePrice) {
        this.partEndorsePrice = partEndorsePrice;
    }

    public int getEndorsement() {
        return endorsement;
    }

    public void setEndorsement(int endorsement) {
        this.endorsement = endorsement;
    }

    public int getHasBaggage() {
        return hasBaggage;
    }

    public void setHasBaggage(int hasBaggage) {
        this.hasBaggage = hasBaggage;
    }

    public String getBaggage() {
        return baggage;
    }

    public void setBaggage(String baggage) {
        this.baggage = baggage;
    }

    public int getHasNoShow() {
        return hasNoShow;
    }

    public void setHasNoShow(int hasNoShow) {
        this.hasNoShow = hasNoShow;
    }

    public int getNoShowLimitTime() {
        return noShowLimitTime;
    }

    public void setNoShowLimitTime(int noShowLimitTime) {
        this.noShowLimitTime = noShowLimitTime;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public int getSpecialNoShow() {
        return specialNoShow;
    }

    public void setSpecialNoShow(int specialNoShow) {
        this.specialNoShow = specialNoShow;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rule{");
        sb.append("id=").append(id);
        sb.append(", hasRefund=").append(hasRefund);
        sb.append(", refund='").append(refund).append('\'');
        sb.append(", partRefund=").append(partRefund);
        sb.append(", partRefundPrice=").append(partRefundPrice);
        sb.append(", hasEndorse=").append(hasEndorse);
        sb.append(", endorse='").append(endorse).append('\'');
        sb.append(", partEndorse=").append(partEndorse);
        sb.append(", partEndorsePrice=").append(partEndorsePrice);
        sb.append(", endorsement=").append(endorsement);
        sb.append(", hasBaggage=").append(hasBaggage);
        sb.append(", baggage='").append(baggage).append('\'');
        sb.append(", hasNoShow=").append(hasNoShow);
        sb.append(", noShowLimitTime=").append(noShowLimitTime);
        sb.append(", penalty=").append(penalty);
        sb.append(", specialNoShow=").append(specialNoShow);
        sb.append(", other='").append(other).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
