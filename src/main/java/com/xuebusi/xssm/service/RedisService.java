package com.xuebusi.xssm.service;

/**
 * Created by 学布斯 on 2017/12/18.
 */
public interface RedisService {

	String set(String key, String value);

	String get(String key);
}
