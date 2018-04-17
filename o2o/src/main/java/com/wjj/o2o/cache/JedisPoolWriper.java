package com.wjj.o2o.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * 强指定redis的JedisPool接口构造函数，这样才能在centos成功创建jedispool。
 * 
 * @author 82712
 *
 */
public class JedisPoolWriper {
	/** Redis连接池对象。 */

	private JedisPool jedisPool;

	public JedisPoolWriper(final JedisPoolConfig poolConfig, final String host, final int port,final String password) {
		try {
			jedisPool = new JedisPool(poolConfig, host, port,Protocol.DEFAULT_TIMEOUT,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get the Redis pool object.
	 * 
	 * @return JedisPool
	 */
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	/**
	 * set the Redis pool object.
	 * 
	 * @param jedisPool
	 */
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

}
