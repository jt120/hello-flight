package com.jt.web.controller;

import com.jt.web.model.CityRelation;
import com.jt.web.model.Policy;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import java.util.List;

/**
 * since 2015/10/30.
 */
@IocBean
@At("/policy")
public class PolicyController extends BaseController {

    @At("/")
    @Ok("jsp:jsp.policy.list")
    public void index() {}

    @At
    public Object add(@Param("..") Policy policy) {
        NutMap nutMap = new NutMap();
        CityRelation cr = dao.fetch(CityRelation.class, Cnd.where("fromCode", "=", policy.getDepAirport()).and("toCode", "=", policy.getArrAirport()));
        if (cr == null) {
            cr = new CityRelation();
            cr.setFromCode(policy.getDepAirport());
            cr.setToCode(policy.getArrAirport());
            dao.insert(cr);
        }
        policy.setCityRelationId(cr.getId());
        dao.insert(policy);
        nutMap.setv("ok", true).setv("msg", "insert ok");
        return nutMap;
    }

    @At
    public Object query(@Param("..") Pager pager) {
        List<Policy> policies = dao.query(Policy.class, null, pager);
        QueryResult result = new QueryResult();
        result.setList(policies);
        result.setPager(pager);
        return result;
    }


}
