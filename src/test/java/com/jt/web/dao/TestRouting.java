package com.jt.web.dao;

import com.jt.web.model.resp.Routing;
import org.junit.Test;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
//import qunar.api.json.JsonMapper;
//import qunar.api.json.MapperBuilder;

/**
 * since 2015/10/29.
 */
public class TestRouting {
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
            "\"retSegments\":[]" +
            "}";

    @Test
    public void test01() throws Exception {


        //JsonMapper mapper = MapperBuilder.getDefaultMapper();
        //Routing r = mapper.readValue(s, Routing.class);
        //System.out.println(r);
        //System.out.println(r.getFromSegments());
    }

    @Test
    public void test02() throws Exception {
        Routing routing = Json.fromJson(Routing.class, s);
        System.out.println(routing);
        System.out.println(routing.getFromSegments());
    }

    @Test
    public void test03() throws Exception {
        Routing routing = Json.fromJson(Routing.class, s);

        System.out.println(Json.toJson(routing, JsonFormat.compact()));
        System.out.println(Json.toJson(routing, JsonFormat.full()));
        System.out.println(Json.toJson(routing, JsonFormat.nice()));
        System.out.println(Json.toJson(routing, JsonFormat.forLook()));
        System.out.println(Json.toJson(routing, JsonFormat.tidy()));
    }

    @Test
    public void test04() throws Exception {
        Routing routing = Json.fromJson(Routing.class, s);
        System.out.println(Json.toJson(routing, JsonFormat.forLook().setLocked("^(id|routingId)$")));
    }
}
