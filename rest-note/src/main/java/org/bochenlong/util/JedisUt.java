package org.bochenlong.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.UUID;

/**
 * Created by bochenlong on 16-10-9.
 */
public class JedisUt {
    private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    static {
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMaxIdle(1000);
        jedisPoolConfig.setMinIdle(500);
        jedisPoolConfig.setMaxWaitMillis(10000);
    }

    private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379, 2000);


    public static void set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
        jedis.close();
    }
}
