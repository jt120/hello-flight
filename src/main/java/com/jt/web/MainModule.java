package com.jt.web;

import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * since 2015/10/30.
 */
@Modules(scanPackage = true)
@IocBy(type = ComboIocProvider.class, args = {"*js", "ioc/",
        "*anno", "com.jt.web",
        "*tx"})
@SetupBy(MainSetup.class)
@Fail("http:500")
@Ok("json")
public class MainModule {


}
