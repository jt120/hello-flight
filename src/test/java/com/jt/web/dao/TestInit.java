package com.jt.web.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jt.web.model.Av;
import com.jt.web.model.City;
import com.jt.web.model.CityRelation;
import com.jt.web.model.Policy;
import com.jt.web.model.Rule;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.lang.Files;
import qunar.api.json.JsonMapper;
import qunar.api.json.MapperBuilder;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * since 2015/10/30.
 */
public class TestInit {

    Ioc ioc = new NutIoc(new JsonLoader("dao.json"));
    DataSource ds = ioc.get(DataSource.class);
    Dao dao = new NutDao(ds);
    JsonMapper mapper = MapperBuilder.getDefaultMapper();


    @Test
    public void testInit() throws Exception {
        dao.create(City.class, true);
        dao.create(CityRelation.class, true);
        dao.create(Policy.class, true);
        dao.create(Av.class, true);
        dao.create(Rule.class, true);
    }

    @Test
    public void testLoadCity() throws Exception {
        String read = Files.read("citys.txt");
        //System.out.println(read);
        List<TestUrl.CityResult> cityResults = mapper.readValue(read, new TypeReference<List<TestUrl.CityResult>>() {
        });
        List<City> list = Lists.newArrayList();
        Map<String, City> map = Maps.newHashMap();
        for (TestUrl.CityResult rt : cityResults) {
            for (TestUrl.CityCont cityCont : rt.getResult()) {
                String x = mapper.writeValueAsString(cityCont);
                //System.out.println(x);
                City city1 = mapper.readValue(x, City.class);
                //System.out.println(city1);
                map.put(city1.getCode(), city1);
            }
        }
        System.out.println("total " + map.size());
        //for (City city : map.values()) {
        //    City fetch = dao.fetch(City.class, city.getCode());
        //    if (fetch != null) {
        //        continue;
        //    }
        //}
        dao.fastInsert(map.values());
    }

    @Test
    public void testPs() throws Exception {
        List<City> list = Lists.newArrayList();
        City city = new City();
        city.setKey("hello");
        list.add(city);
        System.out.println(mapper.writeValueAsString(list));
    }
    @Test
    public void testCity() throws Exception {

        createRelation();

    }

    private void createRelation() {
        City pek = getCity("PEK");
        City hkg = getCity("hkg");

        CityRelation r1 = getCityRelation(pek, hkg);
        CityRelation r2 = getCityRelation(hkg, pek);

        dao.insert(pek);
        dao.insert(hkg);

        dao.insert(r1);
        dao.insert(r2);
    }

    @Test
    public void testCityMore() throws Exception {
        City pek = getCity("PEK");
        City sin = getCity("sin");
        CityRelation r1 = getCityRelation(pek, sin);
        CityRelation r2 = getCityRelation(sin, pek);

        dao.insert(sin);

        dao.insert(r1);
        dao.insert(r2);

        City pek1 = dao.fetch(City.class, "PEK");
        City city = dao.fetchLinks(pek1, "fromRelations");
        System.out.println(city.getFromRelations());

    }

    @Test
    public void testCityUpdate() throws Exception {
        City pek1 = dao.fetch(City.class, "PEK");
        pek1.setUpdateTime(new Date());
        dao.update(pek1);
    }

    @Test
    public void testRel() throws Exception {
        City pek = dao.fetch(City.class, "PEK");
        City hkg = dao.fetch(City.class, "HKG");
        CityRelation r1 = getCityRelation("PEK", "HKG");
        CityRelation r2 = getCityRelation("HKG", "PEK");
        pek.setFromRelations(Lists.newArrayList(r1));
        pek.setToRelations(Lists.newArrayList(r2));
        dao.insertLinks(pek, "fromRelations|toRelations");
    }

    @Test
    public void testPolicy() throws Exception {
        String policy = "{\n" +
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

        Policy policy1 = mapper.readValue(policy, Policy.class);
        //System.out.println(policy1);
        policy1.setFlyType(1);
        System.out.println(mapper.writeValueAsString(policy1));
    }

    @Test
    public void testRule() throws Exception {
        String s = "{\n" +
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
        Rule rule = mapper.readValue(s, Rule.class);
        String s1 = mapper.writeValueAsString(rule);
        System.out.println(s1);

    }

    @Test
    public void testAv() throws Exception {
        String s = "{\n" +
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
        Av av = mapper.readValue(s, Av.class);
        //System.out.println(av);

        System.out.println(mapper.writeValueAsString(av));
    }

    @Test
    public void testAll() throws Exception {
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
                "}";        String as = "{\n" +
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

    @Test
    public void testFind() throws Exception {
        CityRelation fetch = dao.fetch(CityRelation.class, Cnd.where("fromName", "=", "PEK").and("toName", "=", "HKG"));
        dao.fetchLinks(fetch, "policies");
        dao.fetchLinks(fetch, "avs");

        for (Policy policy : fetch.getPolicies()) {
            dao.fetchLinks(policy, "rule");
        }

        System.out.println(fetch.getPolicies());
        System.out.println(fetch.getAvs());
    }

    private CityRelation getCityRelation(City from, City to) {
        CityRelation relation = new CityRelation();
        return relation;
    }

    private CityRelation getCityRelation(String from, String to) {
        CityRelation relation = new CityRelation();
        return relation;
    }

    private City getCity(String name) {
        name = name.toUpperCase();
        City city = new City();
        return city;
    }

    @Test
    public void testFindCity() throws Exception {
        List<City> query = dao.query(City.class, null);
        System.out.println(query);
    }

}
