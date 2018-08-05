package com.xuebusi.xssm.service.cache;

/**
 * 如果缓存中不存在，则从其他数据源加载数据
 * @param <T>
 */
public interface CacheLoadable<T> {
    /**
     * 加载数据
     * @param <T>
     * @return
     */
    <T> T load();
}
