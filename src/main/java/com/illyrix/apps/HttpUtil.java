package com.illyrix.apps;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public class HttpUtil {
    public static String httpGet(String url) throws Exception {
        CloseableHttpAsyncClient httpclient;
        httpclient = HttpAsyncClients.createDefault();
        try {
            httpclient.start();

            HttpGet request = new HttpGet(url);
            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();
            httpclient.close();
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
        return "";
    }
    public static String httpPost(String url, Map<String, String> map) throws Exception {
        CloseableHttpAsyncClient httpclient;
        httpclient = HttpAsyncClients.createDefault();
        try {
            httpclient.start();

            HttpPost request = new HttpPost(url);

            List<NameValuePair> formparams = new ArrayList<>();
            map.forEach((String n, String v) -> {
                formparams.add(new BasicNameValuePair(n, v));
            });

            HttpEntity entity = new UrlEncodedFormEntity(formparams, "utf-8");
            request.setEntity(entity);

            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();
            httpclient.close();
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
        return "";
    }
}
