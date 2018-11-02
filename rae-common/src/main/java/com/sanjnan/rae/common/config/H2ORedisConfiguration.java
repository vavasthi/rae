/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;

/**
 * Created by vinay on 7/27/16.
 */
@Configuration
public class H2ORedisConfiguration {
    private
    @Value("${redis.host}")
    String redisHost;
    private
    @Value("${redis.password:}")
    String redisPassword;
    private
    @Value("${redis.readreplica}")
    String redisReadReplica;
    private
    @Value("${redis.port}")
    int redisPort;
    @Value("${redis.database}")
    private int redisDatabase;
    @Value("${redis.pool.maxIdle:20}")
    private int maxIdle;
    private
    @Value("${redis.pool.minIdle:5}")
    int minIdle;
    private
    @Value("${redis.pool.maxTotal:2000}")
    int maxTotal;
    private
    @Value("${redis.pool.maxWaitMillis:30000}")
    int maxWaitMillis;
    private
    @Value("${redis.pool.maxRedirects:3}")
    int maxRedirects;
    @Bean
    @Primary
    JedisConnectionFactory jedisConnectionFactory() {

        String[] hosts = redisHost.split(",");
        return connectionFactory(hosts);
    }

    @Bean(name = "redisTemplate")
    @Primary
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    JedisConnectionFactory jedisReadReplicaConnectionFactory() {
        String[] hosts = redisReadReplica.split(",");
        return connectionFactory(hosts);
    }


    JedisConnectionFactory connectionFactory(String[] hosts) {

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setMaxTotal(maxTotal);

        if (hosts.length == 1) {
            return new JedisConnectionFactory(poolConfig);
        }
        else {

            RedisClusterConfiguration configuration = new RedisClusterConfiguration(Arrays.asList(hosts));
            if (redisPassword != null || !redisPassword.isEmpty()) {
                configuration.setPassword(RedisPassword.of(redisPassword));
            }
            configuration.setMaxRedirects(maxRedirects);
            JedisConnectionFactory factory = new JedisConnectionFactory(configuration, poolConfig);
            return factory;
        }
    }
    @Bean(name = "redisReadReplicaTemplate")
    public RedisTemplate<Object, Object> redisReadReplicaTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(jedisReadReplicaConnectionFactory());
        return redisTemplate;
    }


}
