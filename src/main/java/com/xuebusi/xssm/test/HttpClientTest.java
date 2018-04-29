package com.xuebusi.xssm.test;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: syj
 * @CreateDate: 2018/4/27 19:07
 */
public class HttpClientTest {

    public static void main(String[] args) {
        try {
            HttpClientBuilder builder = HttpClientBuilder.create();
            CloseableHttpClient httpClient = builder.build();
            String url = "http://dev.womaoapp.com/qmall/favormall/signOut";
            HttpPost request = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<>();
            NameValuePair userId = new BasicNameValuePair("userId", "433639351490842624");
            params.add(userId);
            if (params != null) {
                List<NameValuePair> parameters = new ArrayList<>();
                parameters.addAll(params);
                HttpEntity entity = new UrlEncodedFormEntity(parameters, "UTF-8");
                request.setEntity(entity);
            }
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = IOUtils.toString(entity.getContent(), "UTF-8");
                System.out.println("=======================" + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
