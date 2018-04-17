import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/4/3 9:46
 */
public class JedisTest {

    @Test
    public void testJedisSingle() {
        //创建对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //调用方法
        jedis.set("key1", "Single1");
        String s = jedis.get("key1");
        System.out.println(s);
        //关闭jedis
        jedis.close();
    }

    @Test
    public void testJedisPool() {
        //创建连接池
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        //获取对象
        Jedis jedis = jedisPool.getResource();
        String s = jedis.get("key1");
        System.out.println(s);
        //关闭jedis
        jedis.close();
        jedisPool.close();
    }

    @Test
    public void testJedisCluster() {
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1", 6380));
        nodes.add(new HostAndPort("127.0.0.1", 6381));
        nodes.add(new HostAndPort("127.0.0.1", 6382));
        nodes.add(new HostAndPort("127.0.0.1", 6383));
        nodes.add(new HostAndPort("127.0.0.1", 6384));
        nodes.add(new HostAndPort("127.0.0.1", 6385));

        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("key1", "1000");
        System.out.println(cluster.get("key1"));
        cluster.close();
    }

    @Test
    public void testSpringJedisSingle() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
        JedisPool pool = (JedisPool) context.getBean("redisClient");
        Jedis jedis = pool.getResource();
        System.out.println(jedis.get("key1"));
        jedis.close();
        pool.close();
    }

    @Test
    public void testSpringJedisCluster() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
        JedisCluster cluster = (JedisCluster) context.getBean("redisClient");
        System.out.println(cluster.get("key1"));
        cluster.close();
    }
}
