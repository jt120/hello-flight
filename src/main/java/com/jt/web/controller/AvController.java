package com.jt.web.controller;

import com.jt.web.model.Av;
import com.jt.web.model.CityRelation;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import java.util.List;

/**
 * since 2015/10/29.
 */
@IocBean
@At("/av")
public class AvController extends BaseController {

    @At
    public Object add(@Param("..")Av av) {
        NutMap nutMap = new NutMap();
        CityRelation cr = dao.fetch(CityRelation.class, Cnd.where("fromCode", "=", av.getDepAirport()).and("toCode", "=", av.getArrAirport()));
        if (cr == null) {
            cr = new CityRelation();
            cr.setFromCode(av.getDepAirport());
            cr.setToCode(av.getArrAirport());
            dao.insert(cr);
        }
        av.setCityRelationId(cr.getId());
        dao.insert(av);
        nutMap.setv("ok", true).setv("data", "插入成功");
        return nutMap;
    }

    @At("/")
    @Ok("jsp:jsp.av.list")
    public void index() {
    }

    @At
    public Object query(@Param("..")Pager pager) {
        QueryResult result = new QueryResult();
        List<Av> query = dao.query(Av.class, null, pager);
        int count = dao.count(Av.class);
        pager.setRecordCount(count);
        result.setList(query);
        result.setPager(pager);
        return result;
    }


}
