package com.sanjnan.rae.common.caching;

import com.sanjnan.rae.common.annotations.ORMCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by vinayhubble on 17/8/16.
 */
@Service
public class AbstractCacheService {

    @Value("${spring.profiles.active}")
    private String springProfilesActive;

    protected long getExpiry() {

        return this.getClass().getAnnotation(ORMCache.class).expiry();
    }
    protected String getPrefix() {
        return getClass().getAnnotation(ORMCache.class).prefix();
    }
    protected void storeObject(RedisTemplate<Object, Object> redisTemplate,
                               final Map<Object, Object> keyValuePairs) {

        String metricName = springProfilesActive + "_STORE_" + getClass().getAnnotation(ORMCache.class).name();
        Map<Object, Object> prefixedKeyValuePairs = new HashMap<>();
        for (Map.Entry<Object, Object> e : keyValuePairs.entrySet()) {
            if (e.getKey() != null) {

                prefixedKeyValuePairs.put(new CacheKeyPrefix(getPrefix(), e.getKey()), e.getValue());
            }
        }
        redisTemplate.opsForValue().multiSet(prefixedKeyValuePairs);
        for (Object key : prefixedKeyValuePairs.keySet()) {

            redisTemplate.expire(key, getExpiry(), TimeUnit.SECONDS);
        }
    }
    protected void deleteObject(RedisTemplate<Object, Object> redisTemplate,
                                final List<Object> keys) {

        String metricName = springProfilesActive + "_DELETE_" + getClass().getAnnotation(ORMCache.class).name();
        List<Object> nonNullKeys = new ArrayList<>();
        for (Object o : keys) {
            if (o != null) {
                nonNullKeys.add(new CacheKeyPrefix(getPrefix(), o));
            }
        }
        redisTemplate.delete(nonNullKeys);
    }
    protected void deleteKey(RedisTemplate<Object, Object> redisTemplate,
                             final Object key) {

        String metricName = springProfilesActive + "_DELETE_" + getClass().getAnnotation(ORMCache.class).name();
        List<Object> nonNullKeys = new ArrayList<>();
        if (key != null) {
            nonNullKeys.add(new CacheKeyPrefix(getPrefix(), key));
        }
        redisTemplate.delete(nonNullKeys);
    }
    protected <T> T get(RedisTemplate<Object, Object> redisReadReplicaTemplate,
                        RedisTemplate<Object, Object> redisTemplate,
                        Object key, Class<T> type) {
        String metricName = springProfilesActive + "_READ_" + getClass().getAnnotation(ORMCache.class).name();
        Object value;
        try {

            value = redisReadReplicaTemplate.opsForValue().get(new CacheKeyPrefix(getPrefix(), key));
        }
        catch(SerializationException serializationException) {
            deleteKey(redisTemplate, key);
            return null;
        }
        catch(Exception ex) {

            value = redisTemplate.opsForValue().get(new CacheKeyPrefix(getPrefix(), key));
        }
        return (T)value;
    }

    protected Boolean setIfAbsent(RedisTemplate<Object, Object> redisTemplate,
                                  Object key, Object Value) {

        String metricName = springProfilesActive + "_STORE_IF_ABSENT_" + getClass().getAnnotation(ORMCache.class).name();
        Boolean status = redisTemplate.opsForValue().setIfAbsent(new CacheKeyPrefix(getPrefix(), key),Value);
        redisTemplate.expire(new CacheKeyPrefix(getPrefix(), key), getExpiry(), TimeUnit.SECONDS);
        return status;
    }
}
