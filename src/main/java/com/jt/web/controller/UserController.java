package com.jt.web.controller;

import com.jt.web.model.User;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

/**
 * Created by he on 2015/10/31.
 */
@IocBean
@At("/user")
public class UserController extends BaseController {

    @At("/")
    @Ok("jsp:jsp.user.list")
    public void index() {

    }

    @At
    public Object add(@Param("..") User user) {
        NutMap nutMap = new NutMap();
        dao.insert(user);
        return nutMap.setv("ok", true).setv("msg", "添加用户成功");
    }
}
