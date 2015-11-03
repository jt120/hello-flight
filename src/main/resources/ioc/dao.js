var ioc = {
    dataSource: {
        type: "com.alibaba.druid.pool.DruidDataSource",
        events: {
            create: "init",
            depose: 'close'
        },
        fields: {
            url: "jdbc:mysql://localhost/afare_go",
            username: "root",
            password: "root",
            testWhileIdle: true,
            validationQuery: "select 1",
            maxActive: 100
        }
    },
    dao: {
        type: "org.nutz.dao.impl.NutDao",
        args: [
            {refer: "dataSource"}
        ]
    }
};