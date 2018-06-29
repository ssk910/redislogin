package com.hsk.redislogin.config;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisConfig {

    private static int TIMEOUT = 2000;
    private static HostAndPort hnp = HostAndPortUtil.getRedisServers().get(0);
    private String authPassword = null;
    //    private Jedis jedis = null;
    private static JedisPool pool = null;

    public RedisConfig() {
        openConnection();
    }

    public RedisConfig(String authPassword) {
        this.authPassword = authPassword;
        openConnection();
    }

    public void openConnection() {
        pool = new JedisPool(new JedisPoolConfig(), hnp.getHost(), hnp.getPort(), TIMEOUT, authPassword);
    }

    public void closeConnection() {
        pool.destroy();
    }

    public void addString(String key, String value) {
        Jedis jedis = pool.getResource();

        try {
            jedis.set(key, value);
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public String getString(String key) {
        Jedis jedis = pool.getResource();
        String value = null;

        try {
            value = jedis.get(key);
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            jedis.close();
            return value;
        }
    }

    public void addHash(String key, String field, String value) {
        Jedis jedis = pool.getResource();

        try {
            jedis.hset(key, field, value);
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public void addHashMap(String key, Map<String, String> map) {
        Jedis jedis = pool.getResource();

        try {
            jedis.hmset(key, map);
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public String getHash(String key, String field) {
        Jedis jedis = pool.getResource();
        String value = "";

        try {
            value = jedis.hget(key,field);
        } catch (JedisException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            value = "";
        } finally {
            jedis.close();
            return value;
        }
    }

    public Set<String> getHashFieldsByKey(String key) {
        Jedis jedis = pool.getResource();
        Set<String> fields = null;

        try {
            fields = jedis.hkeys(key);
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            jedis.close();
            return fields;
        }
    }


    public Map<String, String> getHashAll(String key) {
        Jedis jedis = pool.getResource();
        Map<String, String> map = null;

        try {
            map = jedis.hgetAll(key);
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            jedis.close();
            return map;
        }
    }

    public List<String> getHashFieldValue(String field) {
        Jedis jedis = pool.getResource();
        List<String> list = null;

        try {
            list = jedis.hmget("users", field);
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            jedis.close();
            return list;
        }
    }

    public List<String> hvals(String key) {
        Jedis jedis = pool.getResource();
        List<String> list = null;

        try {
            list = jedis.hvals(key);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            jedis.close();
            return list;
        }
    }

    public Long hlen(String key) {
        Jedis jedis = pool.getResource();
        Long count = 0L;

        try {
            // return: fields count
            count = jedis.hlen(key);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            jedis.close();
            return count;
        }
    }

    public boolean hashExists(String key, String field) {
        Jedis jedis = pool.getResource();
        boolean exists = false;

        try {
            exists = jedis.hexists(key, field);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            jedis.close();
            return exists;
        }
    }

    public boolean keyExists(String key) {
        Jedis jedis = pool.getResource();
        boolean exists = false;

        try {
            exists = jedis.exists(key);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            jedis.close();
            return exists;
        }
    }

    public Long del(String key) {
        Jedis jedis = pool.getResource();
        Long count = 0L;

        try {
            // return: deleted keys count
            count = jedis.del(key);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            jedis.close();
            return count;
        }
    }
}
