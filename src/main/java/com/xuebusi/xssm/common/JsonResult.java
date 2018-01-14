package com.xuebusi.xssm.common;

import com.alibaba.fastjson.JSON;

public class JsonResult {

    public JsonResult() {
    }

    public static String success() {
        return JSON.toJSONString(new Result<>().success(ViewHint.SUCCESS));
    }

    public static String success(Object data) {
        return JSON.toJSONString(new Result<>().success(ViewHint.SUCCESS, data));
    }

    public static String success(ViewHint viewHint, Object data) {
        return JSON.toJSONString(new Result<>().success(viewHint, data));
    }

    public static String fail() {
        return JSON.toJSONString(new Result<>().success(ViewHint.FAIL));
    }

    public static String fail(Object data) {
        return JSON.toJSONString(new Result<>().success(ViewHint.FAIL, data));
    }

    public static String fail(ViewHint viewHint, Object data) {
        return JSON.toJSONString(new Result<>().fail(viewHint, data));
    }
}
