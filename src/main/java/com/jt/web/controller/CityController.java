package com.jt.web.controller;

import com.jt.web.model.City;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

import java.util.List;

/**
 * since 2015/10/29.
 */

@IocBean
@At("/city")
public class CityController {

    @Inject
    private Dao dao;

    @At
    public int count() {
        return dao.count(City.class);
    }

    @At("/")
    @Ok("jsp:jsp.city.list")
    public void index() {

    }

    public void add() {

    }

    @At
    public Object query() {
        QueryResult queryResult = new QueryResult();
        List<City> ret = dao.query(City.class, null);
        queryResult.setList(ret);
        return queryResult;
    }
}
