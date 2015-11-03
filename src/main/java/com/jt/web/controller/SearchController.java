package com.jt.web.controller;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jt.web.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by he on 2015/11/1.
 */
@IocBean
@At("/search")
public class SearchController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    @At("/")
    @Ok("jsp:jsp.home")
    public void index() {

    }

    @At
    public Object query(@Param("..") SearchRequest query, @Param("..") Pager pager) {
        QueryResult queryResult = new QueryResult();
        CityRelation fetch = dao.fetch(CityRelation.class, Cnd.where("fromCode", "=", query.getDep()).and("toCode", "=", query.getArr()));
        dao.fetchLinks(fetch, "avs|policies");
        List<Av> avs = fetch.getAvs();
        List<Policy> policies = fetch.getPolicies();
        List<SearchResult> list = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(avs) && CollectionUtils.isNotEmpty(policies)) {
            ImmutableMap<String, Av> avMap = Maps.uniqueIndex(avs, new Function<Av, String>() {
                @Override
                public String apply(Av input) {
                    return input.getFlightNumber();
                }
            });
            ImmutableMap<String, Policy> policyMap = Maps.uniqueIndex(policies, new Function<Policy, String>() {
                @Override
                public String apply(Policy input) {
                    return input.getCarrier();
                }
            });
            for (Map.Entry<String,Av> entry : avMap.entrySet()) {
                Policy policy = policyMap.get(entry.getKey().substring(0, 2));
                if (policy != null) {
                    SearchResult result = new SearchResult();
                    result.setCarrier(entry.getValue().getCarrier());
                    result.setFlightNumber(entry.getValue().getFlightNumber());
                    result.setDepTime(entry.getValue().getDepTime());
                    result.setArrTime(entry.getValue().getArrTime());
                    result.setAdultPrice(policy.getAdultPrice());
                    result.setAdultTax(policy.getAdultTax());
                    list.add(result);
                }

            }

        }
        log.info("search result {}", list);
        queryResult.setList(list);
        queryResult.setPager(pager);
        return queryResult;
    }
}
