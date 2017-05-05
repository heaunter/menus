package com.vota.reservation.common.cache;

import redis.clients.jedis.ShardedJedis;

/**
 * Redis DataSource
 */
public interface RedisDataSource {

    /**
     * 获取Redis链接
     *
     * @return
     */
    public abstract ShardedJedis getRedisClient();

    /**
     * 释放资源
     *
     * @param shardedJedis
     */
    public void returnResource(ShardedJedis shardedJedis);

    /**
     * 释放资源
     *
     * @param shardedJedis
     * @param broken
     */
    public void returnResource(ShardedJedis shardedJedis, boolean broken);
}