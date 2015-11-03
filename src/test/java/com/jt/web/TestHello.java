package com.jt.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * since 2015/10/29.
 */
public class TestHello {

    private static final Logger log = LoggerFactory.getLogger(TestHello.class);

    @Test
    public void test() throws Exception {
        log.info("hello {}", "log");
    }
}
