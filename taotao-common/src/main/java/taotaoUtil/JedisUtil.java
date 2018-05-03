package taotaoUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtil {
    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    private  void close(Jedis jedis) {
        try{
            jedisPool.returnResource(jedis);
        }catch (Exception e){
            if(jedis.isConnected()){
                jedis.quit();
                jedis.disconnect();
            }
        }
    }
//    private void close(String key,Jedis jedis){
//        this.close(jedis);
//    }
//    private void close(String key,String value,Jedis jedis){
//        this.close(jedis);
//    }
//    private void close(String key,Integer time,Jedis jedis){
//        this.close(jedis);
//    }
//    private void close(String key,String value,Integer time,Jedis jedis){
//        this.close(jedis);
//    }

    public void set(String key,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key,value);
        } catch (Exception e) {
        } finally {
            close(jedis);
        }

    }
    public String get(String key){
        Jedis jedis = null;
        String res = null;
        try {
            jedis = jedisPool.getResource();
            res = jedis.get(key);
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
        return res;
    }
    public void expire(String key, int time) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.expire(key,time);
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
    }
    public void set(String key, String value, int time) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key,value);
            jedis.expire(key,time);
        } catch (Exception e) {
        } finally {
            close(jedis);
        }
    }
}
