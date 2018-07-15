package com.xuebusi.xssm.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: syj
 * @CreateDate: 2018/5/10 9:23
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "test1")
    public String test1() {
        String reqUrl = "http://wthrcdn.etouch.cn/weather_mini?citykey=101280601";
        ResponseEntity<String> entity = restTemplate.getForEntity(reqUrl, String.class);
        System.out.println(JSON.toJSON(entity));
        return "test1";
    }
}
