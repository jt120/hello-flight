package com.jt.web.dao;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Lists;
import com.jt.web.model.CityRelation;
import org.junit.Test;
import org.nutz.dao.QueryResult;
import qunar.api.json.JsonMapper;
import qunar.api.json.MapperBuilder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * since 2015/10/30.
 */
public class TestUrl {

    Pattern compile = Pattern.compile("jQuery\\d+_\\d+\\(([\\s\\S]*)\\)");

    JsonMapper mapper = MapperBuilder.getDefaultMapper();


    @Test
    public void test01() throws Exception {
        String url = "http://www.qunar.com/suggest/livesearch2" +
                ".jsp?lang=zh&q=#q#&sa=true&ver=1&callback=jQuery17204482635734602809_1446196558640&_=1446196583547";

        String sUrl = "http://www.qunar.com/suggest/livesearch2" +
                ".jsp?lang=zh&q=p&sa=true&ver=1&callback=jQuery172049383273092098534_1446202144858&_=1446202154849";
        List<String> strings = buildArray();
        List<CityResult> queryResults = Lists.newArrayList();
        for (String s : strings) {
            String ret = url.replace("#q#", s);
            System.out.println("query " + s);
            queryResults.add(queryOne(ret));
            Thread.sleep(1000);
        }

        System.out.println("=================");
        System.out.println(mapper.writeValueAsString(queryResults));
    }

    public CityResult queryOne(String url) {
        String body = HttpRequest.get(url).body();

        String ret = find(body);
        //System.out.println("ret: " + ret);
        if (ret == null) {
            System.out.println("is null");
            return null;
        }
        CityResult cityResult = mapper.readValue(ret, CityResult.class);
        return cityResult;
    }

    @Test
    public void testre() throws Exception {
        String url = "http://www.qunar.com/suggest/livesearch2" +
                ".jsp?lang=zh&q=#q#&sa=true&ver=1&callback=jQuery17204482635734602809_1446196558640&_=1446196583547";

        List<String> strings = buildArray();
        for (String s : strings) {
            String ret = url.replace("#q#", s);
            System.out.println(ret);
            break;
        }
    }

    public List<String> buildArray() {
        List<String> ret = Lists.newArrayList();
        for (int i = 97; i <= 122; i++) {
            ret.add(((char) i) + "");
        }
        System.out.println(ret);
        return ret;
    }

    @Test
    public void test03() throws Exception {
        System.out.println((int)'a');
        System.out.println((int)'z');
        List<String> ret = Lists.newArrayList();
        for (int i = 97; i <= 122; i++) {
            ret.add(((char) i) + "");
        }
        System.out.println(ret);
    }

    @Test
    public void test02() throws Exception {
        String s = "jQuery17204482635734602809_1446196558640({\"userInput\":\"a\",\"c\":0," +
                "\"result\":[{\"country\":\"新西兰\",\"code\":\"AKL\",\"type\":3,\"key\":\"奥克兰\"," +
                "\"display\":\"奥克兰 新西兰 AKL (auckland)\"},{\"country\":\"澳大利亚\",\"code\":\"MEL\",\"type\":3," +
                "\"key\":\"墨尔本\",\"display\":\"墨尔本(AVV) 澳大利亚\"},{\"country\":\"中国\",\"code\":\"AKU\",\"type\":3," +
                "\"key\":\"阿克苏\",\"display\":\"阿克苏(akesu)\"},{\"country\":\"中国澳门\",\"code\":\"MFM\",\"type\":3," +
                "\"key\":\"澳门\",\"display\":\"澳门(aomen)\"},{\"country\":\"中国\",\"code\":\"AQG\",\"type\":3," +
                "\"key\":\"安庆\",\"display\":\"安庆(anqing)\"},{\"country\":\"中国\",\"code\":\"ACX\",\"type\":3," +
                "\"key\":\"兴义\",\"display\":\"兴义(ACX)\"},{\"country\":\"中国\",\"code\":\"AEB\",\"type\":3," +
                "\"key\":\"百色\",\"display\":\"百色(AEB)\"},{\"country\":\"缅甸\",\"code\":\"VBA\",\"type\":3," +
                "\"key\":\"安\",\"display\":\"安 缅甸 VBA (A)\"}]});";



    }

    public String find(String s) {
        Matcher matcher = compile.matcher(s);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }



    public static class CityResult {
        private String userInput;
        private String c;
        private List<CityCont> result;

        public String getUserInput() {
            return userInput;
        }

        public void setUserInput(String userInput) {
            this.userInput = userInput;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public List<CityCont> getResult() {
            return result;
        }

        public void setResult(List<CityCont> result) {
            this.result = result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("CityResult{");
            sb.append("userInput='").append(userInput).append('\'');
            sb.append(", c='").append(c).append('\'');
            sb.append(", result=").append(result);
            sb.append('}');
            return sb.toString();
        }
    }

    public static class CityCont {
        private String country;
        private String code;
        private String type;
        private String key;
        private String display;


        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getDisplay() {
            return display;
        }

        public void setDisplay(String display) {
            this.display = display;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("CityCont{");
            sb.append("country='").append(country).append('\'');
            sb.append(", code='").append(code).append('\'');
            sb.append(", type='").append(type).append('\'');
            sb.append(", key='").append(key).append('\'');
            sb.append(", display='").append(display).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
