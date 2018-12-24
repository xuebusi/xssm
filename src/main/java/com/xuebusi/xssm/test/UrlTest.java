package com.xuebusi.xssm.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlTest {

    public static String toFaceUrls = "/tech/face,/event/facefree,/event/facesdk-offline";

    public static void main(String[] args) {

//        System.out.println(hasRequestUrl("/tech/face"));
//        System.out.println(hasRequestUrl("/event/facefree"));
//        System.out.println(hasRequestUrl("/event/facesdk-offline"));
        System.out.println(hasRequestUrl("/tech/face/*"));
    }

    /**
     * 是否匹配请求URL
     *
     * @param requestUrl
     * @return
     */
    public static boolean hasRequestUrl(String requestUrl) {
        String[] urls = UrlTest.toFaceUrls.split(",");
        if (urls == null || urls.length == 0) {
            return false;
        }
        String[] formatUrls = removeArrSuffix(urls);
        requestUrl = removeSuffix(requestUrl, "/");
        boolean hasAsterisk = requestUrl.endsWith("*");
        String removeAsteriskUrl = null;
        if (hasAsterisk) {
            removeAsteriskUrl = removeSuffix(requestUrl, "*");
        }

        for (int i = 0; i < formatUrls.length; i++) {
            if (requestUrl.equals(formatUrls[i])) {
                return true;
            } else if (removeAsteriskUrl != null && formatUrls[i].startsWith(removeAsteriskUrl)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 去掉数组中所有字符串末尾的斜线
     * 返回一个新的数组
     *
     * @param arr
     * @return
     */
    private static String[] removeArrSuffix(String[] arr) {
        String[] newArr = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = removeSuffix(arr[i], "/");
        }
        return newArr;
    }

    /**
     * 去掉字符串末尾的字符
     *
     * @param str
     * @param separator
     * @return
     */
    public static String removeSuffix(String str, String separator) {
        if (str != null && str.endsWith(separator)) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public static void printUrl() {
        String url1 = "/tech/speech/lsr";
        String url2 = "tech/speech/lsr";
        String url3 = "/tech/speech/lsr/";

        String url4 = "/tech/*";
        String url5 = "/tech/speech/*";

        System.out.println(getUrls(url1).toString());
        System.out.println(getUrls(url2).toString());
        System.out.println(getUrls(url3).toString());
        System.out.println(getUrls(url4).toString());
        System.out.println(getUrls(url5).toString());
    }

    public static List<String> getUrls(String url) {
        String[] urls = url.split("/");
        List<String> urlList = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            url = urls[i];
            if ("".equals(url)) {
                continue;
            }
            urlList.add(url);
        }
        return urlList;
    }

    public static String matchRequestPath(String requestPath) {
        // 判断 Request Path 中是否带有占位符
        if (requestPath.matches(".+\\{\\w+\\}.*")) {
            // 将请求路径中的占位符 {\w+} 转换为正则表达式 (\\w+)
            requestPath = replaceAll(requestPath, "\\{\\w+\\}", "(\\\\w+)");
        }
        return requestPath;
    }

    public static boolean matchUrl(String requestPath, String currentRequestPath) {
        // 去掉当前请求路径末尾的“/”
        if (currentRequestPath.endsWith("/")) {
            currentRequestPath = currentRequestPath.substring(0, currentRequestPath.length() - 1);
        }
        // 获取请求路径匹配器（使用正则表达式匹配请求路径并从中获取相应的请求参数）
        Matcher requestPathMatcher = Pattern.compile(requestPath).matcher(currentRequestPath);
        return requestPathMatcher.matches();
    }

    /**
     * 替换固定格式的字符串（支持正则表达式）
     */
    public static String replaceAll(String str, String regex, String replacement) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, replacement);
        }
        m.appendTail(sb);
        return sb.toString();
    }

}
