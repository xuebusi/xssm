package com.xuebusi.xssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuebusi.xssm.common.util.Base64Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 图片处理Controller
 *
 */
@Controller
@RequestMapping("image")
public class ImageController {

    /**
     * 二值图片处理
     *
     * @param base64img
     * @return
     */
    @RequestMapping(value = "imgToBase64", method = RequestMethod.POST)
    public JSONObject imgToBase64(@RequestParam("base64img") String base64img) {
        String result = Base64Util.imgToBase64(base64img, 498, 310);
        System.out.println(result);
        JSONObject json = new JSONObject();
        json.put("data", result);
        return json;
    }
}
