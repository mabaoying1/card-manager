package com.healthpay.modules.sys.service.test2;


import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;


public class HttpPostTest {
	Map<String, String> params;
	String url;
	public static String post(String url, Map<String, String> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		HttpPost post = postForm(url, params);
		
		body = invoke(httpclient, post);
		
		httpclient.getConnectionManager().shutdown();
		
		return body;
	}

	public static String post(String url,String content) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String body = null;

		HttpPost httpPost = new HttpPost(url);
		ContentType contentType = ContentType.create("application/x-www-form-urlencode", Consts.UTF_8);
		StringEntity entity = new StringEntity(content,contentType);
		httpPost.setEntity(entity);

		CloseableHttpResponse response = httpclient.execute(httpPost);
		HttpEntity responseEntity = response.getEntity();

		if (null != responseEntity)
			body = EntityUtils.toString(responseEntity,Consts.UTF_8);

		EntityUtils.consume(responseEntity);

		return body;
	}

	public HttpPostTest(String url, Map<String, String> params){
		this.url = url;
		this.params = params;
	}
	public static String get(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		HttpGet get = new HttpGet(url);
		body = invoke(httpclient, get);
		
		httpclient.getConnectionManager().shutdown();
		
		return body;
	}
		
	
	private static String invoke(DefaultHttpClient httpclient,
			HttpUriRequest httpost) {
		
		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);
		
		return body;
	}

	private static String paseResponse(HttpResponse response) {
		HttpEntity entity = response.getEntity();

		String charset = EntityUtils.getContentCharSet(entity);
		
		String body = null;
		try {
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return body;
	}

	private static HttpResponse sendRequest(DefaultHttpClient httpclient,
			HttpUriRequest httpost) {

		HttpResponse response = null;
		
		try {
			response = httpclient.execute(httpost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private static HttpPost postForm(String url, Map<String, String> params){
		
		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList <NameValuePair>();
		
		Set<String> keySet = params.keySet();
		for(String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}

		try {

			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return httpost;
	}

	public static void main(String []agrs){
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "thinkgem");
		params.put("password", "admin");
			
		String xml = HttpPostTest.post("http://localhost:8080/HeartCare/a/login", params);
	}
	
	public  String post(){

//		Map<String, String> params = new HashMap<String, String>();
//		params.put("name", "thinkgem");
//		params.put("password", "admin");
			
		String xml = HttpPostTest.post(url, params);


	  return xml;
	}
}
