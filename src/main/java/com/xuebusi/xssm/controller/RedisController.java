package com.xuebusi.xssm.controller;

import com.xuebusi.xssm.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * Created by 学布斯 on 2017/12/18.
 */
@Controller
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private RedisService redisService;

	@RequestMapping("/get/{key}")
	public String get(@PathVariable("key") String key) {
		String result = redisService.get(key);
		return result;
	}

	@RequestMapping("/set")
	public String set() {
		String result = redisService.set("testkey_" + System.currentTimeMillis(), UUID.randomUUID().toString());
		return result;
	}
}
