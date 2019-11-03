package com.gene.information.domain;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class WechatOAConfig {

	public static final String APP_ID = "wxe23f18d7b8947aa6";
	public static final String secret = "889ef7319d78c271e75b8d858bb2b5f1";
    public static final String BQEGIN_ANSWER= "http://ailaide.jingtu99.com/paper/index";
    public static final String READ_REPORT = "http://ailaide.jingtu99.com/paper/getReportPage";
		
	private static Logger logger = LoggerFactory.getLogger(WechatOAConfig.class);
	
	
	public static String getAccessToken(String code) throws Exception {
		String openid = "";
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
		String para = "appid=wxe23f18d7b8947aa6&secret=889ef7319d78c271e75b8d858bb2b5f1&code="+code+"&grant_type=authorization_code";
		String res = sendGet(url, para);
		Map<String, String> map = new HashMap<String, String>();
		map=JSON.parseObject(res, Map.class);
		openid=map.get("openid");
		return openid;
	}
	
	/*public static AccessToken getAccessTokenss(String code) {
		AccessToken accessToken = null;
		Map<String, Object> message = new HashMap<>();
		String uri = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APP_ID+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
		JSONObject parseObject = JSON.parseObject(NetUtil.get(uri));
		 if (null != parseObject) {
			  try {
				  accessToken = new AccessToken();
				  accessToken.setAccess_token(parseObject.getString("access_token"));
				  accessToken.setExpires_in(parseObject.getString("expires_in"));
				  accessToken.setRefresh_token(parseObject.getString("refresh_token"));
				  accessToken.setOpenid(parseObject.getString("openid"));
				  accessToken.setScope(parseObject.getString("scope"));
			} catch (Exception e) {
				accessToken = null;
				int errorCode = parseObject.getInteger("errcode");
				String errorMsg = parseObject.getString("errmsg");
				logger.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		 }
		
		return accessToken;
	}*/
	
	
	
	public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	public static String getAccessToken() {
		String access_token = null;
		String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APP_ID+"&secret="+secret;
		try {
			URL urlGet = new URL(uri);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			JSONObject demoJson = JSON.parseObject(message);
			access_token = demoJson.getString("access_token");
			is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		return access_token;
	}
	

	public static String getJsapiTicket(String access_token) {
		String ticket = null;
		//String accessToken = getAccessToken();
		String uri = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
		 try {
				URL urlGet = new URL(uri);
				HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
				http.setRequestMethod("GET"); // 必须是get方式请求
				http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				http.setDoOutput(true);
				http.setDoInput(true);
				System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
				System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
				http.connect();
				InputStream is = http.getInputStream();
				int size = is.available();
				byte[] jsonBytes = new byte[size];
				is.read(jsonBytes);
				String message = new String(jsonBytes, "UTF-8");
				JSONObject demoJson = JSON.parseObject(message);
				ticket = demoJson.getString("ticket");
				is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}
}
