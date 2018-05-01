package com.xuebusi.xssm.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuebusi.xssm.common.util.HttpClientUtil;
import com.xuebusi.xssm.common.util.Xml2Json;
import org.apache.commons.codec.binary.Base64;
import org.dom4j.DocumentException;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 亿美接口测试类
 */
public class EmayTest {

    private static Object MMSContent;

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
        byte[] mmsContent = getMMSContent("src/test2.zip");
        String base64ToString = encode(mmsContent);
        // 彩信内容
        paramMap.put("MMSContent", base64ToString);
        // 发送类型
        paramMap.put("sendType", "1");

        // 发送彩信
        String result = HttpClientUtil.sendPost(reqURL, paramMap, "UTF-8", "application/x-www-form-urlencoded");
        // 最终返回的结果存到map
        Map<String, String> resultMap = new HashMap<>();
        try {
            // 将xml转成json对象
            JSONObject jsonObject = Xml2Json.xml2Json(result);
            if (jsonObject != null) {
                // 获取key为string的部分
                String resultStr = (String) jsonObject.get("string");
                // 根据亿美的文档，如果以OK开头，表示彩信发送成功
                if (!StringUtils.isEmpty(resultStr) && resultStr.startsWith("OK")) {
                    resultMap.put("result", "true");
                    return JSON.toJSONString(resultMap);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        resultMap.put("result", "false");
        return JSON.toJSONString(resultMap);
    }

    /**
     * 测试查询账户余额接口
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
        // 亿美接口返回数据格式如下
        /*
            <?xml version="1.0" encoding="utf-8"?>
            <long xmlns="http://tempuri.org/">0</long>
        */
        String result = HttpClientUtil.sendPost(reqURL, paramMap, "UTF-8", "application/x-www-form-urlencoded");
        try {
            // 将xml转成json对象
            JSONObject jsonObject = Xml2Json.xml2Json(result);
            if (jsonObject != null) {
                return jsonObject.toString();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 测试查询账户状态接口
     * @return
     */
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
        // 亿美接口返回结果格式如下:
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
        String result = HttpClientUtil.sendPost(reqURL, paramMap, "UTF-8", "application/x-www-form-urlencoded");
        if (!StringUtils.isEmpty(result)) {
            // 由于返回的不是标准的xml格式，需要处理成标准的xml格式才能正确解析：
            // (1)将其中所欲的&gt;替换成>号;
            // (2)将所有的&lt;替换成<号;
            // (3)并将其中的<?xml version="1.0"?>部分删除;
            String replaceResult = result.replace("&gt;", ">").replace("&lt;", "<").replace("<?xml version=\"1.0\"?>", "");
            try {
                // 使用工具类将xml转成json对象
                JSONObject jsonObject = Xml2Json.xml2Json(replaceResult);
                if (jsonObject != null) {
                    // 将名为MMSResult的部分解析出来并返回
                    Object mmsResult = jsonObject.get("MMSResult");
                    if (mmsResult != null) {
                        return mmsResult.toString();
                    }
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 读取zip包数据
     * @return
     */
    public static byte[] getMMSContent(String filePath) {
        FileInputStream fs;
        byte[] content = new byte[0];
        try {
            fs = new FileInputStream(filePath);
            content=new byte[fs.available()];
            fs.read(content,0,content.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 将二进制数据编码为BASE64字符串
     * @param binaryData
     * @return
     */
    public static String encode(byte[] binaryData) {
        try {
            return new String(Base64.encodeBase64(binaryData), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

}
