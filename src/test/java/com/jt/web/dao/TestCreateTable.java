package com.jt.web.dao;

import com.jt.web.model.Av;
import com.jt.web.model.City;
import com.jt.web.model.CityRelation;
import com.jt.web.model.Policy;
import com.jt.web.model.Rule;
import com.jt.web.model.resp.Routing;
import com.jt.web.model.resp.Segment;
import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import qunar.api.json.JsonMapper;
import qunar.api.json.MapperBuilder;

import javax.sql.DataSource;

/**
 * since 2015/10/29.
 */
public class TestCreateTable {

    Ioc ioc = new NutIoc(new JsonLoader("dao.json"));
    DataSource ds = ioc.get(DataSource.class);
    Dao dao = new NutDao(ds);

    String s = "{" +
            "\"data\":\"3da0a93eba26c6e8f28955fe65f426fadbec03d9\"," +
            "\"currency\":\"RMB\"," +
            "\"adultPrice\":800," +
            "\"adultTax\":66," +
            "\"childPrice\":0," +
            "\"childTax\":0," +
            "\"nationalityType\":1," +
            "\"nationality\":\"CN,HK,TW\"," +
            "\"suitAge\":\"12~59\"," +
            "\"adultTaxType\":0," +
            "\"childTaxType\":0," +
            "\"ticketTimeLimit\":60," +
            "\"priceType\":0," +
            "\"applyType\":0," +
            "\"rule\":{" +
            "\"hasRefund\":1," +
            "\"refund\":\"200-72-300-48-1000-0-*\"," +
            "\"partRefund\":1," +
            "\"partRefundPrice\":500," +
            "\"hasEndorse\":1," +
            "\"endorse\":\"200-72-300-48-1000-*\"," +
            "\"partEndorse\":1," +
            "\"partEndorsePrice\":300," +
            "\"endorsement\":0," +
            "\"hasBaggage\":1," +
            "\"baggage\":\"1-23;2-30\"," +
            "\"hasNoShow\":1," +
            "\"noShowLimitTime\":48," +
            "\"penalty\":500," +
            "\"specialNoShow\":0," +
            "\"other\":\"退改签需要提前三个工作日联系客服\"" +
            "}," +
            "\"fromSegments\":[{" +
            "\"carrier\":\"AA\"," +
            "\"depAirport\":\"LAX\"," +
            "\"depTime\":\"201203140140\"," +
            "\"arrAirport\":\"PEK\"," +
            "\"arrTime\":\"201203150530\"," +
            "\"stopCities\":\"\"," +
            "\"codeShare\":false," +
            "\"cabin\":\"E\"," +
            "\"aircraftCode\":\"Boeing 777\"," +
            "\"flightNumber\":\"AA89\"," +
            "\"cabinClass\":2" +
            "}," +
            "{" +
            "\"carrier\":\"CA\"," +
            "\"depAirport\":\"PEK\"," +
            "\"depTime\":\"201203151400\"," +
            "\"arrAirport\":\"TPE\"," +
            "\"arrTime\":\"201203151715\"," +
            "\"stopCities\":\"HKG/TYO/CTU\"," +
            "\"codeShare\":false," +
            "\"cabin\":\"E\"," +
            "\"aircraftCode\":\"Boeing 737\"," +
            "\"flightNumber\":\"CA189\"," +
            "\"cabinClass\":2" +
            "}]," +
            "\"retSegments\":[" +
            "{" +
            "\"carrier\":\"CA\"," +
            "\"depAirport\":\"PEK\"," +
            "\"depTime\":\"201203151400\"," +
            "\"arrAirport\":\"TPE\"," +
            "\"arrTime\":\"201203151715\"," +
            "\"stopCities\":\"HKG/TYO/CTU\"," +
            "\"codeShare\":false," +
            "\"cabin\":\"E\"," +
            "\"aircraftCode\":\"Boeing 737\"," +
            "\"flightNumber\":\"CA189\"," +
            "\"cabinClass\":2" +
            "}"
            +
            "]" +
            "}";

    @Test
    public void test01() throws Exception {

        dao.create(Routing.class, true);
        //dao.create(Rule.class, true);
        dao.create(Segment.class, true);
    }

    @Test
    public void test00() throws Exception {
        dao.create(City.class, true);
        dao.create(CityRelation.class, true);
        dao.create(Policy.class, true);
        dao.create(Av.class, true);
        dao.create(Rule.class, true);
    }

    @Test
    public void test02() throws Exception {
        //解析不了这种时间201203140140
        //Routing routing = Json.fromJson(Routing.class, s);
        dao.create(Routing.class, true);
        dao.create(Rule.class, true);
        dao.create(Segment.class, true);

        JsonMapper mapper = MapperBuilder.getDefaultMapper();
        Routing routing = mapper.readValue(s, Routing.class);
        System.out.println(routing);

        dao.insertWith(routing, "rule|fromSegments|retSegments");
    }

    @Test
    public void test03() throws Exception {
        Routing fetch = dao.fetch(Routing.class, 1);
        Routing routing = dao.fetchLinks(fetch, "rule|fromSegments");
        System.out.println(fetch);

        System.out.println("++++++++++");
        System.out.println(routing.getRule());

        System.out.println("+++++++++++");
        System.out.println(routing.getFromSegments());
    }
}
