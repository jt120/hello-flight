package com.jt.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * since 2015/10/30.
 */
@Table("t_av")
public class Av extends BaseModel {

    @JsonIgnore
    @Id
    private int id;

    @JsonIgnore
    @Column
    private int cityRelationId;

    /**
     * 航司IATA二字码，必须与flightNumber航司相同
     */
    @Column
    private String carrier;
    /**
     * 航班号，如：CA123。航班号数字前若有多余的数字0，必须去掉，如CZ006需返回CZ6
     */
    @Column
    private String flightNumber;
    /**
     * 出发机场IATA三字码
     */
    @Column
    private String depAirport;
    /**
     * 起飞日期时间，格式：YYYYMMDDHHMM例：201203100300表示2012年3月10日3时0分
     */
    @Column
    @ColDefine(customType = "timestamp")
    private Date depTime;
    /**
     * 到达机场IATA三字码
     */
    @Column
    private String arrAirport;
    /**
     * 到达日期时间，格式：YYYYMMDDHHMM例：201203101305表示2012年3月10日13时5分
     */
    @Column
    @ColDefine(customType = "timestamp")
    private Date arrTime;
    /**
     * 经停地,/分隔城市三字码
     */
    @Column
    private String stopCities;
    /**
     * 代码共享标识（true代码共享/false非代码共享）
     */
    @Column
    private boolean codeShare;
    /**
     * 舱位
     */
    @Column
    private String cabin;
    /**
     * 机型
     */
    @Column
    private String aircraftCode;
    /**
     * 舱位等级，1代表经济舱，2代表商务舱，3代表头等舱，
     */
    @Column
    private int cabinClass;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityRelationId() {
        return cityRelationId;
    }

    public void setCityRelationId(int cityRelationId) {
        this.cityRelationId = cityRelationId;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepAirport() {
        return depAirport;
    }

    public void setDepAirport(String depAirport) {
        this.depAirport = depAirport;
    }

    public Date getDepTime() {
        return depTime;
    }

    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }

    public String getArrAirport() {
        return arrAirport;
    }

    public void setArrAirport(String arrAirport) {
        this.arrAirport = arrAirport;
    }

    public Date getArrTime() {
        return arrTime;
    }

    public void setArrTime(Date arrTime) {
        this.arrTime = arrTime;
    }

    public String getStopCities() {
        return stopCities;
    }

    public void setStopCities(String stopCities) {
        this.stopCities = stopCities;
    }

    public boolean isCodeShare() {
        return codeShare;
    }

    public void setCodeShare(boolean codeShare) {
        this.codeShare = codeShare;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public int getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(int cabinClass) {
        this.cabinClass = cabinClass;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Av{");
        sb.append("id=").append(id);
        sb.append(", cityRelationId=").append(cityRelationId);
        sb.append(", carrier='").append(carrier).append('\'');
        sb.append(", flightNumber='").append(flightNumber).append('\'');
        sb.append(", depAirport='").append(depAirport).append('\'');
        sb.append(", depTime=").append(depTime);
        sb.append(", arrAirport='").append(arrAirport).append('\'');
        sb.append(", arrTime=").append(arrTime);
        sb.append(", stopCities='").append(stopCities).append('\'');
        sb.append(", codeShare=").append(codeShare);
        sb.append(", cabin='").append(cabin).append('\'');
        sb.append(", aircraftCode='").append(aircraftCode).append('\'');
        sb.append(", cabinClass=").append(cabinClass);
        sb.append('}');
        return sb.toString();
    }
}
