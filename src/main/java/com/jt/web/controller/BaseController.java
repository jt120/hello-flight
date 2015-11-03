package com.jt.web.controller;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;

/**
 * Created by he on 2015/10/31.
 */
public class BaseController {

    @Inject
    protected Dao dao;
}
