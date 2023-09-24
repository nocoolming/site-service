package com.ming.site.util;

import xyz.downgoon.snowflake.Snowflake;

public class SnowflakeUtil {
    protected static Snowflake snowflake = new Snowflake(6, 8);
    private static SnowflakeUtil instance = new SnowflakeUtil();

    public static SnowflakeUtil getInstance() {
        return instance;
    }

    public static long nextId() {
        return snowflake.nextId();
    }
}
