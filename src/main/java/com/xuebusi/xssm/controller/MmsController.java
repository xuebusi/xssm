package com.xuebusi.xssm.controller;

import com.alibaba.fastjson.JSON;
import com.xuebusi.xssm.dto.MmsDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/mms")
public class MmsController extends BaseController{

    @ResponseBody
    @RequestMapping(value = "/sendMms1", method = RequestMethod.POST)
    public String sendMms1(@RequestBody MmsDto mmsDto) {
        String messageJson = JSON.toJSONString(mmsDto);
        return success(messageJson);
    }

    @ResponseBody
    @RequestMapping(value = "/sendMms2", method = RequestMethod.POST)
    public String sendMms2(MmsDto mmsDto) {
        String messageJson = JSON.toJSONString(mmsDto);
        return success(messageJson);
    }
}
