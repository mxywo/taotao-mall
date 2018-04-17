package com.taotao.rest.dao.Impl;

import com.taotao.rest.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/4/3 16:53
 */
public class JedisClientSingleImpl implements JedisClient {

    @Autowired
    private JedisPool jedisPool;//Autowire是通过类型注入
    //<bean id="redisClient" class="redis.clients.jedis.JedisPool">
    //由此注入的redisPool

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String s = jedis.get(key);
        jedis.close();
        return s;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String s = jedis.set(key, value);
        jedis.close();
        return s;
    }

    @Override
    public String hget(String hashKey, String key) {
        Jedis jedis = jedisPool.getResource();
        String s = jedis.hget(hashKey, key);
        jedis.close();
        return s;
    }

    @Override
    public long hset(String hashKey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(hashKey, key, value);
        jedis.close();
        return result;
    }

    @Override
    public long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long incr = jedis.incr(key);
        jedis.close();
        return incr;
    }

    @Override
    public long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long expire = jedis.expire(key, second);
        jedis.close();
        return expire;
    }

    @Override
    public long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long ttl = jedis.ttl(key);
        jedis.close();
        return ttl;
    }

    @Override
    public long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long del = jedis.del(key);
        jedis.close();
        return del;
    }

    @Override
    public long hdel(String hashKey, String key) {
        Jedis jedis = jedisPool.getResource();
        Long hdel = jedis.hdel(hashKey, key);
        jedis.close();
        return hdel;
    }
}
