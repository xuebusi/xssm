package com.xuebusi.xssm.common.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpClientUtil {
    private static final int CHECK_SOKET_TIMEOUT = 2000;//超时2秒记录提醒日志
    private static final int MAX_SOKET_TIMEOUT = 30000;
    private static final int MAX_CONNECTION_TIMEOUT = 30000;
    private static final RequestConfig reqConfig = RequestConfig.custom()//.setProxy(new HttpHost("192.168.20.6",3128))
            .setSocketTimeout(MAX_SOKET_TIMEOUT)
            .setConnectTimeout(MAX_CONNECTION_TIMEOUT)
            .setStaleConnectionCheckEnabled(true).build();
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    private HttpClientUtil() {

    }

    public static CloseableHttpResponse getRequest(String reqURL) {
        long startTime = System.currentTimeMillis();   //获取开始时间
        long needTime = 0;//花费时间
        String respContent = "通信失败";
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(reqConfig).build();
        //如果有&去除
//        reqURL = StringUtil.trimEnd(reqURL, "&");
//        reqURL = StringUtil.trimEnd(reqURL, "?");
        HttpGet httpGet = new HttpGet(reqURL);
        try {
            CloseableHttpResponse e = httpClient.execute(httpGet);
            return e;
        } catch (ConnectTimeoutException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (SocketTimeoutException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (ClientProtocolException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (ParseException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (IOException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (Exception e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } finally {
            long endTime = System.currentTimeMillis(); //获取结束时间
            needTime = endTime - startTime;
            if (needTime > CHECK_SOKET_TIMEOUT) {
                LOGGER.warn("程序运行时间监控:{}ms,地址：{}", needTime, reqURL);
            }
        }
        return null;
    }

    public static String sendGetRequest(String reqURL) {
        long startTime = System.currentTimeMillis();   //获取开始时间
        long needTime = 0;//花费时间
        String respContent = "通信失败";
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(reqConfig).build();
        //如果有&去除
//        reqURL = StringUtil.trimEnd(reqURL, "&");
//        reqURL = StringUtil.trimEnd(reqURL, "?");
        HttpGet httpGet = new HttpGet(reqURL);
        // LOGGER.info("GET-url："+reqURL);
        try {
            CloseableHttpResponse e = httpClient.execute(httpGet);
            HttpEntity entity = e.getEntity();
            if (null != entity) {
                Charset respHeaderDatas = ContentType.getOrDefault(entity).getCharset();
                //gzf:返回值中文乱码，respHeaderDatas=null;
                if (respHeaderDatas == null) {
                    respHeaderDatas = Charset.forName("UTF-8");
                }
                respContent = EntityUtils.toString(entity, respHeaderDatas);
                EntityUtils.consume(entity);
            }

            // System.out.println("-------------------------------------------------------------------------------------------");
            StringBuilder respHeaderDatas = new StringBuilder();
            Header[] respStatusLine = e.getAllHeaders();
            int respHeaderMsg = respStatusLine.length;

            for (int respBodyMsg = 0; respBodyMsg < respHeaderMsg; ++respBodyMsg) {
                Header header = respStatusLine[respBodyMsg];
                respHeaderDatas.append(header.toString()).append("\r\n");
            }
        } catch (ConnectTimeoutException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (SocketTimeoutException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (ClientProtocolException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (ParseException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (IOException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (Exception e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } finally {
            long endTime = System.currentTimeMillis(); //获取结束时间
            needTime = endTime - startTime;
            if (needTime > CHECK_SOKET_TIMEOUT) {
                LOGGER.warn("程序运行时间监控:{}ms,地址：{}", needTime, reqURL);
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return respContent;
    }


    public static String sendGetRequest(String reqURL, Map<String, Object> param, String encodeCharset) {
        long startTime = System.currentTimeMillis();   //获取开始时间
        long needTime = 0;//花费时间
        String respContent = "通信失败";
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(reqConfig).build();
        String str_param = HttpClientUtil.getUrlParamsByMap(param);
        try {
            if (encodeCharset == null || encodeCharset.length() < 1) {
                encodeCharset = "utf-8";
            }
            reqURL = HttpClientUtil.urlFormat(reqURL, str_param, encodeCharset);
            //LOGGER.info("GET-url："+reqURL);
            HttpGet httpGet = new HttpGet(reqURL);

            CloseableHttpResponse e = httpClient.execute(httpGet);
            HttpEntity entity = e.getEntity();
            if (null != entity) {
                Charset respHeaderDatas = ContentType.getOrDefault(entity).getCharset();
                //gzf:返回值中文乱码，respHeaderDatas=null;
                if (respHeaderDatas == null) {
                    respHeaderDatas = Charset.forName("UTF-8");
                }
                respContent = EntityUtils.toString(entity, respHeaderDatas);
                EntityUtils.consume(entity);
            }

            // System.out.println("-------------------------------------------------------------------------------------------");
            StringBuilder respHeaderDatas = new StringBuilder();
            Header[] respStatusLine = e.getAllHeaders();
            int respHeaderMsg = respStatusLine.length;

            for (int respBodyMsg = 0; respBodyMsg < respHeaderMsg; ++respBodyMsg) {
                Header header = respStatusLine[respBodyMsg];
                respHeaderDatas.append(header.toString()).append("\r\n");
            }

            // String str_respStatusLine = e.getStatusLine().toString();
            // String str_respHeaderMsg = respHeaderDatas.toString().trim();
            // System.out.println("HTTP应答完整报文=[" + str_respStatusLine + "\r\n" + str_respHeaderMsg + "\r\n\r\n" + respContent + "]");
            // System.out.println("-------------------------------------------------------------------------------------------");
        } catch (ConnectTimeoutException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (SocketTimeoutException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (ClientProtocolException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (ParseException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (IOException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (Exception e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } finally {
            long endTime = System.currentTimeMillis(); //获取结束时间
            needTime = endTime - startTime;
            if (needTime > CHECK_SOKET_TIMEOUT) {
                LOGGER.warn("程序运行时间监控:{}ms,地址：{}", needTime, reqURL);
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return respContent;
    }

    public static String sendPost(String reqURL, Map<String, Object> maps, String encodeCharset, String contentType) {
        Set<Map.Entry<String, Object>> entrySet = maps.entrySet();
        List<BasicNameValuePair> pairList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : entrySet) {
            BasicNameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
            pairList.add(pair);
        }
        String reqData = URLEncodedUtils.format(pairList, StandardCharsets.UTF_8);
        return sendPostRequest(reqURL, reqData, encodeCharset, contentType);
    }

    public static String sendPostRequest(String reqURL, String reqData, String encodeCharset, String contentType) {
        long startTime = System.currentTimeMillis();   //获取开始时间
        long needTime = 0;//花费时间
        String reseContent = "通信失败";
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(reqConfig).build();
        HttpPost httpPost = new HttpPost(reqURL);
        try {
            reqURL = HttpClientUtil.urlFormat(reqURL, reqData, encodeCharset);
            if (encodeCharset == null || encodeCharset.length() < 1) {
                encodeCharset = "utf-8";
            }
            if (contentType == null
                    || contentType.length() < 1) {
                contentType = "application/x-www-form-urlencoded";
            }
            httpPost.setHeader("Content-Type", "" + contentType + "; charset=" + encodeCharset);
            httpPost.setEntity(new StringEntity(reqData == null ? "" : reqData, encodeCharset));
            CloseableHttpResponse e = httpClient.execute(httpPost);
            HttpEntity entity = e.getEntity();

            if (null != entity) {
                //gzf:返回中文乱码ContentType.getOrDefault(dto).getCharset()==null;
                Charset cs = ContentType.getOrDefault(entity).getCharset();
                if (cs == null) {
                    cs = Charset.forName("UTF-8");
                }
                reseContent = EntityUtils.toString(entity, cs);
                EntityUtils.consume(entity);
            }
        } catch (ConnectTimeoutException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (SocketTimeoutException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (Exception e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } finally {
            long endTime = System.currentTimeMillis(); //获取结束时间
            needTime = endTime - startTime;
            if (needTime > CHECK_SOKET_TIMEOUT) {
                LOGGER.warn("程序运行时间监控:{}ms,地址：{}", needTime, reqURL);
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return reseContent;
    }

    public static String sendPostRequestRetCode(String reqURL, String reqData, String encodeCharset, String contentType) {
        long startTime = System.currentTimeMillis();   //获取开始时间
        long needTime = 0;//花费时间
        String reseContent = "通信失败";
        CloseableHttpClient httpClient = null;
        try {

            httpClient = HttpClients.custom().setDefaultRequestConfig(reqConfig).build();

            reqURL = HttpClientUtil.urlFormat(reqURL, reqData, encodeCharset);

            HttpPost httpPost = new HttpPost(reqURL);
            httpPost.setHeader("Content-Type", "" + contentType + "; charset=" + encodeCharset);
            httpPost.setEntity(new StringEntity(reqData == null ? "" : reqData, encodeCharset));
            CloseableHttpResponse e = httpClient.execute(httpPost);
            HttpEntity entity = e.getEntity();

            if (null != entity) {
                //gzf:返回中文乱码ContentType.getOrDefault(dto).getCharset()==null;
                Charset cs = ContentType.getOrDefault(entity).getCharset();
                if (cs == null) {
                    cs = Charset.forName("UTF-8");
                }
                reseContent = EntityUtils.toString(entity, cs);
                reseContent = reseContent.replaceAll("}", ",'httpcode':" + e.getStatusLine().getStatusCode() + "}");
                reseContent = reseContent.replaceAll("}", ",'code':" + e.getStatusLine().getStatusCode() + "}");
                EntityUtils.consume(entity);
            }
        } catch (ConnectTimeoutException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (SocketTimeoutException e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } catch (Exception e) {
            LOGGER.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", e);
        } finally {
            long endTime = System.currentTimeMillis(); //获取结束时间
            needTime = endTime - startTime;
            if (needTime > CHECK_SOKET_TIMEOUT) {
                LOGGER.warn("程序运行时间监控:{}ms,地址：{}", needTime, reqURL);
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return reseContent;
    }

    public static String sendPostSSLRequest(String reqURL, Map<String, Object> params, String encodeCharset) {
        String responseContent = "通信失败";
        X509TrustManager trustManager = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
            public void verify(String host, SSLSocket ssl) throws IOException {
            }

            public void verify(String host, X509Certificate cert) throws SSLException {
            }

            public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
            }

            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        };

        try {
            SSLContext e = SSLContext.getInstance("SSL");
            e.init((KeyManager[]) null, new TrustManager[]{trustManager}, new SecureRandom());
            CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(reqConfig).setSSLSocketFactory(new SSLConnectionSocketFactory(e, hostnameVerifier)).build();

            reqURL = HttpClientUtil.urlFormat(reqURL, HttpClientUtil.getUrlParamsByMap(params), encodeCharset);

            HttpPost httpPost = new HttpPost(reqURL);
            if (null != params) {
                ArrayList response = new ArrayList();
                Iterator entity = params.entrySet().iterator();

                while (entity.hasNext()) {
                    Map.Entry entry = (Map.Entry) entity.next();
                    response.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
                }

                httpPost.setEntity(new UrlEncodedFormEntity(response, encodeCharset));
            }

            CloseableHttpResponse response1 = httpClient.execute(httpPost);
            HttpEntity entity1 = response1.getEntity();
            if (null != entity1) {
                responseContent = EntityUtils.toString(entity1, ContentType.getOrDefault(entity1).getCharset());
                EntityUtils.consume(entity1);
            }

            httpClient.close();
        } catch (ConnectTimeoutException e) {
            LOGGER.error("请求通信[" + reqURL + "]时协议异常,堆栈轨迹如下", e);
        } catch (SocketTimeoutException e) {
            LOGGER.error("请求通信[" + reqURL + "]时协议异常,堆栈轨迹如下", e);
        } catch (Exception e) {
            LOGGER.error("请求通信[" + reqURL + "]时协议异常,堆栈轨迹如下", e);
        }

        return responseContent;
    }


    /**
     * 将map转换成url
     *
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.length() - 1);
            //s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }


    /**
     * 链接格式化
     *
     * @param urlPattern   链接模板
     * @param urlParameter 请求参数
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlFormat(String urlPattern, String urlParameter, String encoding) throws Exception {
        Map<String, String[]> urlParameterMap = new HashMap<String, String[]>();
        parseParameters(urlParameterMap, urlParameter, encoding);
        Pattern pattern = Pattern.compile("\\{(\\w+)\\}");
        Matcher matcher = pattern.matcher(urlPattern);
        StringBuffer sbr = new StringBuffer();
        boolean ismatch = false;
        while (matcher.find()) {
            ismatch = true;
            String[] values = urlParameterMap.get(matcher.group(1));
            if (values != null) {
                matcher.appendReplacement(sbr, values[0]);
            } else {
                throw new Exception(matcher.group(1) + "必填項缺失。");
            }
        }
        if (ismatch) {
            matcher.appendTail(sbr);
            return sbr.toString();
        } else {
            if (urlPattern.indexOf("?") > 0) {
                urlPattern = urlPattern + "&" + urlParameter;
            } else {
                urlPattern = urlPattern + "?" + urlParameter;
            }
            return urlPattern;
        }
    }

    private static void parseParameters(Map map, String data, String encoding)
            throws UnsupportedEncodingException {
        if ((data == null) || (data.length() <= 0)) {
            return;
        }

        byte[] bytes = null;
        try {
            if (encoding == null)
                bytes = data.getBytes();
            else
                bytes = data.getBytes(encoding);
        } catch (UnsupportedEncodingException uee) {
        }
        parseParameters(map, bytes, encoding);
    }

    private static void parseParameters(Map map, byte[] data, String encoding) throws UnsupportedEncodingException {
        if ((data != null) && (data.length > 0)) {
            int ix = 0;
            int ox = 0;
            String key = null;
            String value = null;
            while (ix < data.length) {
                byte c = data[(ix++)];
                switch ((char) c) {
                    case '&':
                        value = new String(data, 0, ox, encoding);
                        if (key != null) {
                            putMapEntry(map, key, value);
                            key = null;
                        }
                        ox = 0;
                        break;
                    case '=':
                        if (key == null) {
                            key = new String(data, 0, ox, encoding);
                            ox = 0;
                        } else {
                            data[(ox++)] = c;
                        }
                        break;
                    case '+':
                        data[(ox++)] = 32;
                        break;
                    case '%':
                        data[(ox++)] = (byte) ((convertHexDigit(data[(ix++)]) << 4) + convertHexDigit(data[(ix++)]));

                        break;
                    default:
                        data[(ox++)] = c;
                }
            }

            if (key != null) {
                value = new String(data, 0, ox, encoding);
                putMapEntry(map, key, value);
            }
        }
    }

    private static void putMapEntry(Map map, String name, String value) {
        String[] newValues = null;
        String[] oldValues = (String[]) (String[]) map.get(name);
        if (oldValues == null) {
            newValues = new String[1];
            newValues[0] = value;
        } else {
            newValues = new String[oldValues.length + 1];
            System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
            newValues[oldValues.length] = value;
        }
        map.put(name, newValues);
    }

    protected static byte convertHexDigit(byte b) {
        if ((b >= 48) && (b <= 57)) return (byte) (b - 48);
        if ((b >= 97) && (b <= 102)) return (byte) (b - 97 + 10);
        if ((b >= 65) && (b <= 70)) return (byte) (b - 65 + 10);
        return 0;
    }
}
