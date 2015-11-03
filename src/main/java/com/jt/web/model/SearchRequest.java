package com.jt.web.model;

/**
 * Created by he on 2015/11/1.
 */
public class SearchRequest {

    private String dep;
    private String arr;
    private String depTime;
    private String arrTime;
    private int flyType;

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getArr() {
        return arr;
    }

    public void setArr(String arr) {
        this.arr = arr;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public int getFlyType() {
        return flyType;
    }

    public void setFlyType(int flyType) {
        this.flyType = flyType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchRequest{");
        sb.append("dep='").append(dep).append('\'');
        sb.append(", arr='").append(arr).append('\'');
        sb.append(", depTime='").append(depTime).append('\'');
        sb.append(", arrTime='").append(arrTime).append('\'');
        sb.append(", flyType=").append(flyType);
        sb.append('}');
        return sb.toString();
    }
}
