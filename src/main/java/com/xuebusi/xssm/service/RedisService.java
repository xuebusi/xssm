package com.xuebusi.xssm.service;

public interface RedisService {

	String set(String key, String value);

	String get(String key);
}
