package com.xuebusi.xssm.service.impl;

import com.xuebusi.xssm.dao.JedisClient;
import com.xuebusi.xssm.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 学布斯 on 2017/12/18.
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public String set(String key, String value) {
		String result = null;
		try {
			result = jedisClient.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String get(String key) {
		return jedisClient.get(key);
	}

}
