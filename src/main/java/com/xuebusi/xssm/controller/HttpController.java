package com.xuebusi.xssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(value = "/httptest")
public class HttpController {

    @RequestMapping(value = "getForObject")
    @ResponseBody
    public String getForObject() {
        RestTemplate restTemplate = new RestTemplate();
        String reqUrl = "https://www.sojson.com/open/api/weather/json.shtml?city=北京";
        String result = null;
        try {
            result = restTemplate.getForObject(reqUrl, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return result;
    }
}
