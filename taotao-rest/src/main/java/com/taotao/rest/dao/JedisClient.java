package com.taotao.rest.dao;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/4/3 16:52
 */
public interface JedisClient {

    String get(String key);

    String set(String key, String value);

    String hget(String hashKey, String key);

    long hset(String hashKey, String key, String value);

    long incr(String key);

    long expire(String key, int second);

    long ttl(String key);

    long del(String key);

    long hdel(String hashKey, String key);
}
