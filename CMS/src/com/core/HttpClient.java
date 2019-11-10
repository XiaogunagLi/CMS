package com.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient {
	private final static HttpClient httpClient = new HttpClient();
	
	private HttpClient(){
	}
	
	public static HttpClient getInstance(){
		return httpClient;
	}
	
	public String sendPost(String url, String postParm){
		String result = "";
		try {
			URL realUrl = new URL(url);
			 // 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
	        // 设置通用的请求属性
	        conn.setRequestProperty("accept", "*/*");
	        conn.setRequestProperty("accept-language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
	        conn.setRequestProperty("accept-encoding", "gzip, deflate");
	        conn.setRequestProperty("connection", "Keep-Alive");
	        //conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	        // 发送POST请求必须设置如下两行
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        // 获取URLConnection对象对应的输出流
	       // PrintWriter out = new PrintWriter(conn.getOutputStream());
	        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"utf-8"));
	        // 发送请求参数
	        out.print(postParm);
	        // flush输出流的缓冲
	        out.flush();
	        // 定义BufferedReader输入流来读取URL的响应
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        
	        while ((line = in.readLine()) != null) {
	            result += line;
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String args[]){
		String type = "1";
		String roleId = "0";
		String title = "测试邮件"; 
		String content = "测试邮件";
		String attach = "";
		
		StringBuffer sb = new StringBuffer();
		sb.append("type=" + type);
		sb.append("&roleId=" + roleId);
		sb.append("&title=" + title);
		sb.append("&content=" + content);
		sb.append("&attach=" + attach);
		
		
		String result = getInstance().sendPost("http://192.168.1.9:8080/Dgame/email", sb.toString());
		System.out.println(result);
	}
}
