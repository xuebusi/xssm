package com.xuebusi.xssm.common.security;

import com.alibaba.fastjson.JSON;
import com.xuebusi.xssm.pojo.XUser;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;

/**
 * 计算CheckSum
 * Created by SYJ on 2018/7/20.
 */
public class CheckSumBuilder {

    private static final String QMALLL_LOGIN_REQUEST_HEADER_CURTIME = "curTime";
    private static final String QMALLL_LOGIN_REQUEST_HEADER_MD5 = "md5";
    private static final String QMALLL_LOGIN_REQUEST_HEADER_CHECKSUM = "checkSum";
    private static final String QMALLL_LOGIN_APPSECRET = "DkkGmp3PdhwEp6I8";

    /**
     * 校验MD5,CheckSum
     * @param request
     * @param requestBody
     * @return
     */
    private Boolean checkMsg(HttpServletRequest request, String requestBody) {
        if (request == null) {
            return false;
        }
        String curTime = request.getHeader(QMALLL_LOGIN_REQUEST_HEADER_CURTIME);
        String md5 = request.getHeader(QMALLL_LOGIN_REQUEST_HEADER_MD5);
        String checkSum = request.getHeader(QMALLL_LOGIN_REQUEST_HEADER_CHECKSUM);
        if (StringUtils.isEmpty(md5) || StringUtils.isEmpty(checkSum)) {
            return false;
        }
        // 计算md5
        String verifyMD5 = CheckSumBuilder.getMD5(requestBody);
        // 计算checkSum
        String verifyChecksum = CheckSumBuilder.getCheckSum(QMALLL_LOGIN_APPSECRET, verifyMD5, curTime);

        // 比较md5,checkSum是否一致
        return md5.equals(verifyMD5) && checkSum.equals(verifyChecksum) ? true : false;
    }

    /**
     * 计算并获取CheckSum
     * 使用sha1加密
     *
     * @param appSecret
     * @param nonce
     * @param curTime
     * @return
     */
    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }

    /**
     * 计算并获取md5值
     *
     * @param requestBody
     * @return
     */
    public static String getMD5(String requestBody) {
        return encode("md5", requestBody);
    }

    public static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把密文转换成十六进制的字符串形式
     *
     * @param bytes
     * @return
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static void main(String[] args) {
//        rsaTest();
        signTest();
    }

    /**
     * 签名测试
     */
    private static void signTest() {
        // 请求时间戳
        String curTime = String.valueOf(System.currentTimeMillis());

        // 模拟请求入参
        XUser xUser = new XUser();
        xUser.setId(1);
        xUser.setName("张三");
        xUser.setAddress("北京博瑞大厦");
        xUser.setAge(10);
        xUser.setPhone("13966675899");
        String requestBody = JSON.toJSONString(xUser);

        // 对请求入参使用md5加密
        String md5 = CheckSumBuilder.getMD5(requestBody);

        // 使用sha1加密
        String checkSum = CheckSumBuilder.getCheckSum(QMALLL_LOGIN_APPSECRET, md5, curTime);

        //  校验
        checkSumTest(curTime, requestBody, md5, checkSum);
    }

    /**
     * 测试RSA加密和解密
     */
    private static void rsaTest() {
        String sha1 = CheckSumBuilder.encode("sha1", "admin");
        String md5 = CheckSumBuilder.encode("md5", "admin");
        System.out.println("sha1密文:" + sha1);
        System.out.println("md5密文:" + md5);
    }

    public static boolean checkSumTest(String curTime, String requestBody, String md5, String checkSum) {
        System.out.println("入参:curTime=" + curTime);
        System.out.println("入参:requestBody=" + requestBody);
        System.out.println("入参:md5=" + md5);
        System.out.println("入参:checkSum=" + checkSum);
        // 计算md5
        String verifyMD5 = CheckSumBuilder.getMD5(requestBody);
        // 计算checkSum
        String verifyChecksum = CheckSumBuilder.getCheckSum(QMALLL_LOGIN_APPSECRET, verifyMD5, curTime);

        System.out.println("md5值:" + verifyMD5);
        System.out.println("校验和:" + verifyChecksum);

        // 比较md5,checkSum是否一致
        boolean checkResult = md5.equals(verifyMD5) && checkSum.equals(verifyChecksum) ? true : false;
        System.out.println("校验结果:" + checkResult);
        return checkResult;
    }

}
