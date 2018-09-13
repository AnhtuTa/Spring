package baeldung_RedisDemo;

import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisDemo {

	public static void main(String[] args) {
		Jedis jedis = new Jedis();
		
		// 5.1. Strings
		jedis.set("my_city", "Hanoi, Vietnam");
		System.out.println(jedis.get("my_city"));
		
		// 5.2. Lists (cái này giống queue/stack)
		jedis.lpush("stdList", "Nguyen Bka");
		jedis.lpush("stdList", "Att");
		jedis.lpush("stdList", "Huy gà");
		jedis.lpush("stdList", "Toàn");
		jedis.rpush("stdList", "Phanh Lee");
		System.out.println(jedis.lpop("stdList"));
		System.out.println(jedis.rpop("stdList"));
		
		// 5.3. Sets
		jedis.sadd("nicknames", "nickname#1");
		jedis.sadd("nicknames", "nickname#2");
		jedis.sadd("nicknames", "nickname#1");
		jedis.sadd("nicknames", "nickname#3");
		
		Set<String> nicknames = jedis.smembers("nicknames");
		for(String name : nicknames) {
			System.out.println(name);
		}
		
		// 5.4. Hashes: lưu dưới dạng map
		jedis.hset("userHash", "fewagaew", "anhtu");
		jedis.hset("userHash", "gsaeawfg", "Nguyen Bka");
		jedis.hset("userHash", "gbbnawre", "Huy gà");
		jedis.hset("userHash", "vafewafe", "Huyền");
		Map<String, String> userHash = jedis.hgetAll("userHash");
		for(String key : userHash.keySet()) {
			System.out.println(userHash.get(key));
		}
		
		// ...
		// 8. Publish/Subscribe
		// Make sure the subscriber and publisher threads do not share the same Jedis connection.
		Jedis jSubscriber = new Jedis();
		
		// Subscribe and listen to messages sent to a channel
		jSubscriber.subscribe(new JedisPubSub() {
			@Override
			public void onMessage(String channel, String message) {
				System.out.println("Received message: " + message);
			}
		}, "channel");
		
		Jedis jPublisher = new Jedis();
		
		// send messages
		jPublisher.publish("channel", "test message");
		
		jSubscriber.close();
		jPublisher.close();
		jedis.close();
	}
}
