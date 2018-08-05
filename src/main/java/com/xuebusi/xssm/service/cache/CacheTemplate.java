package com.xuebusi.xssm.service.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xuebusi.xssm.mapper.XUserMapper;
import com.xuebusi.xssm.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 缓存模板类
 */
@Component
public class CacheTemplate {

    private static final String XSSM_USER_CACHE_KEY = "XSSM-USER-ID:{0}";

    @Autowired
    private XUserMapper userMapper;

    @Autowired
    private RedisService redisService;

    public <T> T findCache(String key, TypeReference<T> clazz, CacheLoadable<T> cacheLoadable) {
        // 查缓存
        T t1 = getDataFromCache(key, clazz);
        if (t1 != null) {
            return t1;
        }
        synchronized (this) {
            // 查缓存
            T t2 = getDataFromCache(key, clazz);
            if (t2 != null) {
                return t2;
            }
            // 查数据库
            T t3 = cacheLoadable.load();
            System.out.println("=======查询数据库=======");
            // 更新缓存
            redisService.set(key, JSON.toJSONString(t3));
            return t3;
        }
    }

    /**
     * 查询缓存
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> T getDataFromCache(String key, TypeReference<T> clazz) {
        // 先查询缓存
        String value = redisService.get(key);
        if (!StringUtils.isEmpty(value)) {
            T t = JSON.parseObject(value, clazz);
            if (t != null) {
                System.out.println("=======查询缓存=======");
                return t;
            }
        }
        return null;
    }
}