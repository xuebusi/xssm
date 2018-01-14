package com.xuebusi.xssm.common;

import com.alibaba.fastjson.JSON;

/**
 * 返回json
 * @author 学布斯
 * @version 2018-1-14
 */
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

    public static String error() {
        return JSON.toJSONString(new Result<>().error(ViewHint.ERROR));
    }

    public static String error(Object data) {
        return JSON.toJSONString(new Result<>().error(ViewHint.ERROR, data));
    }

    public static String error(ViewHint viewHint, Object data) {
        return JSON.toJSONString(new Result<>().error(viewHint, data));
    }
}
