package com.xuebusi.xssm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xuebusi.xssm.mapper.XUserMapper;
import com.xuebusi.xssm.pojo.XUser;
import com.xuebusi.xssm.service.RedisService;
import com.xuebusi.xssm.service.cache.CacheLoadable;
import com.xuebusi.xssm.service.cache.CacheTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;

@Service
public class CacheServiceImpl {

    private static final String XSSM_USER_CACHE_KEY = "xssm-user-id:{0}";

    @Autowired
    private XUserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private CacheTemplate cacheTemplate;

    /**
     * 高并发环境下的缓存穿透测试
     *
     * @param id
     * @return
     */
    public XUser selectByPrimaryKey(Integer id) {
        //XUser xUser = getUserFromCache1(id);
        //XUser xUser = getUserFromCache2(id);
        //XUser xUser = getUserFromCache3(id);
        //XUser xUser = getUserFromCache4(id);
        XUser xUser = getUserFromCache5(id);
        return xUser;

    }

    /**
     * 不加锁，高并发时缓存会穿透
     *
     * @param id
     * @return
     */
    private XUser getUserFromCache1(Integer id) {
        // 先查询缓存
        String json = redisService.get(MessageFormat.format(XSSM_USER_CACHE_KEY, id));
        if (!StringUtils.isEmpty(json)) {
            XUser xUser = JSON.parseObject(json, XUser.class);
            if (xUser != null) {
                System.out.println("=======查询缓存=======");
                return xUser;
            }
        }
        // 缓存没有的话查询DB
        XUser user = userMapper.selectByPrimaryKey(id);
        System.out.println("=======查询数据库=======");
        // 更新缓存
        redisService.set(MessageFormat.format(XSSM_USER_CACHE_KEY, user.getId()), JSON.toJSONString(user));
        return user;
    }

    /**
     * 使用synchronized对方法加锁，
     * 高并发时缓存不会穿透，但性能低。
     *
     * @param id
     * @return
     */
    private synchronized XUser getUserFromCache2(Integer id) {
        return getUserFromCache1(id);
    }

    /**
     * 缩小锁的粒度，只在查询数据库并更新缓存时加锁，
     * 但高并发时缓存会穿透。
     *
     * @param id
     * @return
     */
    private XUser getUserFromCache3(Integer id) {
        // 先查询缓存
        String json = redisService.get(MessageFormat.format(XSSM_USER_CACHE_KEY, id));
        if (!StringUtils.isEmpty(json)) {
            XUser xUser = JSON.parseObject(json, XUser.class);
            if (xUser != null) {
                System.out.println("=======查询缓存=======");
                return xUser;
            }
        }
        synchronized (this) {
            // 缓存没有的话查询DB
            XUser user = userMapper.selectByPrimaryKey(id);
            System.out.println("=======查询数据库=======");
            // 更新缓存
            redisService.set(MessageFormat.format(XSSM_USER_CACHE_KEY, user.getId()), JSON.toJSONString(user));
            return user;
        }
    }

    /**
     * 双重检查缓存，
     * 高并发时缓存不会穿透，代码复用性差。
     *
     * @param id
     * @return
     */
    private XUser getUserFromCache4(Integer id) {
        // 先查询缓存
        String json = redisService.get(MessageFormat.format(XSSM_USER_CACHE_KEY, id));
        if (!StringUtils.isEmpty(json)) {
            XUser xUser = JSON.parseObject(json, XUser.class);
            if (xUser != null) {
                System.out.println("=======查询缓存=======");
                return xUser;
            }
        }
        synchronized (this) {
            // 先查询缓存
            String json2 = redisService.get(MessageFormat.format(XSSM_USER_CACHE_KEY, id));
            if (!StringUtils.isEmpty(json2)) {
                XUser xUser = JSON.parseObject(json2, XUser.class);
                if (xUser != null) {
                    System.out.println("=======查询缓存=======");
                    return xUser;
                }
            }
            // 缓存没有的话查询DB
            XUser user = userMapper.selectByPrimaryKey(id);
            System.out.println("=======查询数据库=======");
            // 更新缓存
            redisService.set(MessageFormat.format(XSSM_USER_CACHE_KEY, user.getId()), JSON.toJSONString(user));
            return user;
        }
    }

    /**
     * 使用缓存模板，高并发时缓存不会穿透，
     * 代码简洁，推荐使用这种方式。
     *
     * @param id
     * @return
     */
    private XUser getUserFromCache5(final Integer id) {
        return cacheTemplate.findCache(MessageFormat.format(XSSM_USER_CACHE_KEY, id), new TypeReference<XUser>() {
        }, new CacheLoadable<XUser>() {
            @Override
            public XUser load() {
                return userMapper.selectByPrimaryKey(id);
            }
        });
    }
}
