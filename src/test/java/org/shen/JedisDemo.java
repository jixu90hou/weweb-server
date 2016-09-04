package org.shen;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * Created by jackshen on 16/8/21.
 */
public class JedisDemo {
    JedisPool pool;
    Jedis jedis;
    @Before
    public void setUp() {
        pool = new JedisPool(new JedisPoolConfig(), "192.168.1.100",6379);
        jedis = pool.getResource();
       jedis.auth("shen");
    }
    @Test
    public void testList(){
        System.out.println(jedis.ping());
        jedis.lpush("name_list","zhangsan1");
        jedis.lpush("name_list","zhangsan2");
        jedis.lpush("name_list","zhangsan3");
        jedis.lpush("name_list","zhangsan4");
        List<String> nameList=jedis.lrange("name_list",0,3);
       // for (int i=0;i<nameList.size();i++) System.out.println(nameList.get(i));
        for (String name:nameList) System.out.println(name);


    }
}
