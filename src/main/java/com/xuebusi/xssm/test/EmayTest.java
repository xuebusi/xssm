package com.xuebusi.xssm.test;

import com.xuebusi.xssm.common.util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 亿美接口测试类
 */
public class EmayTest {

    public static void main(String[] args) {
        // 测试发送彩信接口
        String result1 = sendMMS();
        System.out.println("------------发送彩信接口返回结果--------------");
        System.out.println(result1);

        // 测试查询余额接口
        String result2 = getMMSCount();
        System.out.println("------------查询余额接口返回结果--------------");
        System.out.println(result2);

        // 测试查询账户状态接口
        String result3 = getStatusReport();
        System.out.println("------------查询账户状态接口返回结果--------------");
        System.out.println(result3);

    }

    /**
     * 测试发送彩信接口
     */
    public static String sendMMS() {
        // 发送彩信接口地址
        String reqURL = "http://mmsplat.eucp.b2m.cn/MMSCenterInterface/MMS.asmx/SendMMS";

        // 发送彩信接口所需参数
        Map<String, Object> paramMap = new HashMap<>();
        // 用户名
        paramMap.put("userName", "bf-test");
        // 密码
        paramMap.put("password", "c5f1213ce8");
        // 彩信标题
        paramMap.put("title", "testmms");
        // 用户手机号
        paramMap.put("userNumbers", "17610639158");
        // 彩信内容
        paramMap.put("MMSContent", "0");
        // 发送类型
        paramMap.put("sendType", "1");

        // 发送彩信
        String result = HttpClientUtil.sendPost(reqURL, paramMap, "UTF-8", "application/x-www-form-urlencoded");

        return result;
    }

    /**
     * 测试查询账户余额接口
     */
    // 返回数据格式如下
    /*
        <?xml version="1.0" encoding="utf-8"?>
        <long xmlns="http://tempuri.org/">0</long>
    */
    public static String getMMSCount() {
        // 查询账户余额接口地址
        String reqURL = "http://mmsplat.eucp.b2m.cn/MMSCenterInterface/MMS.asmx/GetMMSCount";

        // 查询账户余额接口所需参数
        Map<String, Object> paramMap = new HashMap<>();
        // 用户名
        paramMap.put("userName", "bf-test");
        // 密码
        paramMap.put("password", "c5f1213ce8");
        // 发送类型
        paramMap.put("sendType", "1");

        // 查询账户余额
        String result = HttpClientUtil.sendPost(reqURL, paramMap, "UTF-8", "application/x-www-form-urlencoded");

        return result;
    }

    /**
     * 测试查询余额接口
     * @return
     */
    //返回结果格式如下:
    /*<string xmlns="http://tempuri.org/">
      <?xml version="1.0"?>
        <MMSResult
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:xsd="http://www.w3.org/2001/XMLSchema">
            <StartIndex>0</StartIndex>
            <SumCount>0</SumCount>
            <PageCount>0</PageCount>
            <RsList />
        </MMSResult>
    </string>*/
    public static String getStatusReport() {
        // 查询账户状态接口地址
        String reqURL = "http://mmsplat.eucp.b2m.cn/MMSCenterInterface/MMS.asmx/GetStatusReport";

        // 查询账户状态接口所需参数
        Map<String, Object> paramMap = new HashMap<>();
        // 用户名
        paramMap.put("userName", "bf-test");
        // 密码
        paramMap.put("password", "c5f1213ce8");

        // 查询账户状态
        String result = HttpClientUtil.sendPost(reqURL, paramMap, "UTF-8", "application/x-www-form-urlencoded");

        return result;
    }
}
