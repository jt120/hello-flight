package com.jt.web;

import com.jt.web.model.Av;
import com.jt.web.model.City;
import com.jt.web.model.CityRelation;
import com.jt.web.model.Policy;
import com.jt.web.model.Rule;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qunar.api.json.JsonMapper;
import qunar.api.json.MapperBuilder;

/**
 * since 2015/10/30.
 */
public class MainSetup implements Setup {

    private static final Logger log = LoggerFactory.getLogger(MainSetup.class);


    @Override
    public void init(NutConfig nc) {
        Ioc ioc = nc.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, "com.jt.web.model", false);

        int count = dao.count(City.class);
        if (count == 0) {
            log.info("init data");
//            createAll(dao, MapperBuilder.getDefaultMapper());
        }
    }

    @Override
    public void destroy(NutConfig nc) {

    }

    private CityRelation getCityRelation(City from, City to) {
        CityRelation relation = new CityRelation();
        return relation;
    }

    private City getCity(String name) {
        name = name.toUpperCase();
        City city = new City();
        return city;
    }

    private void createRelation(Dao dao) {
        City pek = getCity("PEK");
        City hkg = getCity("hkg");

        CityRelation r1 = getCityRelation(pek, hkg);
        CityRelation r2 = getCityRelation(hkg, pek);

        dao.insert(pek);
        dao.insert(hkg);

        dao.insert(r1);
        dao.insert(r2);
    }

    private void createAll(Dao dao, JsonMapper mapper) {
        String ps = "{\n" +
                "\t\"currency\": \"RMB\",\n" +
                "\t\"adultPrice\": 800,\n" +
                "\t\"adultTax\": 66,\n" +
                "\t\"childPrice\": 0,\n" +
                "\t\"childTax\": 0,\n" +
                "\t\"nationalityType\": 1,\n" +
                "\t\"nationality\": \"CN,HK,TW\",\n" +
                "\t\"suitAge\": \"12~59\",\n" +
                "\t\"adultTaxType\": 0,\n" +
                "\t\"childTaxType\": 0,\n" +
                "\t\"ticketTimeLimit\": 60,\n" +
                "\t\"priceType\": 0,\n" +
                "\t\"applyType\": 0\n" +
                "}";

        String rs = "{\n" +
                "\t\"hasRefund\": 1,\n" +
                "\t\"refund\": \"200-72-300-48-1000-0-*\",\n" +
                "\t\"partRefund\": 1,\n" +
                "\t\"partRefundPrice\": 500,\n" +
                "\t\"hasEndorse\": 1,\n" +
                "\t\"endorse\": \"200-72-300-48-1000-*\",\n" +
                "\t\"partEndorse\": 1,\n" +
                "\t\"partEndorsePrice\": 300,\n" +
                "\t\"endorsement\": 0,\n" +
                "\t\"hasBaggage\": 1,\n" +
                "\t\"baggage\": \"1-23;2-30\",\n" +
                "\t\"hasNoShow\": 1,\n" +
                "\t\"noShowLimitTime\": 48,\n" +
                "\t\"penalty\": 500,\n" +
                "\t\"specialNoShow\": 0,\n" +
                "\t\"other\": \"退改签需要提前三个工作日联系客服\"\n" +
                "}";
        String as = "{\n" +
                "\t\"carrier\": \"AA\",\n" +
                "\t\"depAirport\": \"LAX\",\n" +
                "\t\"depTime\": \"201203140140\",\n" +
                "\t\"arrAirport\": \"PEK\",\n" +
                "\t\"arrTime\": \"201203150530\",\n" +
                "\t\"stopCities\": \"\",\n" +
                "\t\"codeShare\": false,\n" +
                "\t\"cabin\": \"E\",\n" +
                "\t\"aircraftCode\": \"Boeing 777\",\n" +
                "\t\"flightNumber\": \"AA89\",\n" +
                "\t\"cabinClass\": 2\n" +
                "}";
        createRelation(dao);

        Policy policy = mapper.readValue(ps, Policy.class);
        CityRelation fetch = dao.fetch(CityRelation.class, Cnd.where("fromName", "=", "PEK").and("toName", "=", "HKG"));
        policy.setCityRelationId(fetch.getId());
        policy.setFlyType(1);

        Rule rule = mapper.readValue(rs, Rule.class);
        policy.setRule(rule);

        Av av = mapper.readValue(as, Av.class);
        av.setCityRelationId(fetch.getId());

        dao.insertWith(policy, "rule");
        dao.insert(av);
    }
}
