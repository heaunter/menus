package com.vota.reservation.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpClient的工具类
 * 
 * @author liujiawei
 */
@SuppressWarnings("deprecation")
public final class HttpUtils {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	// 设置超时时间为8秒
	private static final long TIME_OUT = 5000;
	private static int _timeout = 5000;
	private static final String CONTENT_CHARSET = "UTF-8";

	/**
	 * Post by params string.
	 * 
	 * @param url
	 *            the url
	 * @param headerMap
	 *            the header map
	 * @param paramMap
	 *            the param map
	 * @return the string
	 */
	public static String postByParams(String url, Map<String, String> headerMap,
			Map<String, String> paramMap) {
		long startTime = System.currentTimeMillis();
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setConnectionManagerTimeout(TIME_OUT);
		httpClient.getParams().setSoTimeout((int) TIME_OUT);
		PostMethod method = null;
		String response = null;
		try {
			method = new PostMethod();
			method.setURI(new URI(url, false));
			if (headerMap != null && !headerMap.isEmpty()) {
				for (Entry<String, String> entry : headerMap.entrySet()) {
					method.setRequestHeader(entry.getKey(), entry.getValue());
				}
			}
			if (paramMap != null && !paramMap.isEmpty()) {
				for (Entry<String, String> entry : paramMap.entrySet()) {
					method.setParameter(entry.getKey(), entry.getValue());
				}
			}
			int statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: {}", method.getStatusLine());
			}
			response = new String(method.getResponseBody(), "utf-8");
		} catch (Exception e) {
			logger.error("RestManager.postByParams has a error : ", e);
		} finally {
			if (method != null) {
				method.releaseConnection();
			}
			long endTime = System.currentTimeMillis();
			logger.info("postByParams url={}, used time {}ms", url, (endTime - startTime));
		}
		return response;
	}

	/**
	 * Get string.
	 * 
	 * @param uri
	 *            the uri
	 * @return the string
	 */
	public static String get(String uri) {
		String data = "";

		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, CONTENT_CHARSET);
		httpClient.setConnectionTimeout(_timeout);
		httpClient.setTimeout(_timeout);

		GetMethod getMethod = new GetMethod();
		try {
			getMethod.setURI(new URI(uri, false));
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			getMethod.setRequestHeader("Connection", "close");

			long beginTime = System.currentTimeMillis();
			int statusCode = httpClient.executeMethod(getMethod);
			long endTime = System.currentTimeMillis();

			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + getMethod.getStatusLine());
				return data;
			}

			data = new String(getMethod.getResponseBody(), "utf-8");
			logger.info("Get url:{}, response data:{}, costs {}ms.", uri, data,
					(endTime - beginTime));
			return data;
		} catch (HttpException e) {
			logger.error("Please check your provided http address!");
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (getMethod != null)
				getMethod.releaseConnection();
		}
		return data;
	}

	/**
	 * Post by form string.
	 * 
	 * @param uri
	 *            the uri
	 * @param paramsMap
	 *            the params map
	 * @return the string
	 */
	public static String postByForm(String uri, Map<String, String> paramsMap) {
		long startTime = System.currentTimeMillis();
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;
		logger.info("HTTP Post: uri=" + uri + ",params="
				+ ToStringBuilder.reflectionToString(paramsMap));
		HttpPost post = postForm(uri, paramsMap);
		body = invoke(httpclient, post);
		httpclient.getConnectionManager().shutdown();
		long endTime = System.currentTimeMillis();
		logger.info("HTTP Post response=" + body + ",costs= " + (endTime - startTime) + "ms.");
		return body;
	}

	private static HttpPost postForm(String url, Map<String, String> params) {
		HttpPost httpost = new HttpPost(url);
		List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
		}
		try {
			logger.info("set utf-8 form entity to httppost");
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return httpost;
	}

	private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost) {
		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);
		return body;
	}

	private static String paseResponse(HttpResponse response) {
		logger.info("get response from http server.");
		HttpEntity entity = response.getEntity();
		logger.info("response status: " + response.getStatusLine());
		String charset = EntityUtils.getContentCharSet(entity);
		logger.info(charset);
		String body = null;
		try {
			body = EntityUtils.toString(entity);
			logger.info(body);
		} catch (ParseException e) {
			logger.error("Failed to parse Response by http client.", e);
		} catch (IOException e) {
			logger.error("Failed to parse Response by http client.", e);
		}
		return body;
	}

	private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost) {
		logger.info("execute post...");
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpost);
		} catch (ClientProtocolException e) {
			logger.error("Failed to send request by http client.", e);
		} catch (IOException e) {
			logger.error("Failed to send request by http client.", e);
		}
		return response;
	}

	/**
	 * Post string.
	 * 
	 * @param uri
	 *            the uri
	 * @param paramMap
	 *            the param map
	 * @return the string
	 * @throws IOException
	 *             the io exception
	 */
	@SuppressWarnings("rawtypes")
	public static String post(String uri, Map paramMap) throws IOException {
		String data = "";
		HttpClient httpClient = new HttpClient();
		httpClient.setConnectionTimeout(_timeout);
		httpClient.setTimeout(_timeout);

		PostMethod method = null;
		try {
			method = new PostMethod();
			method.setURI(new URI(uri, false));
			// param
			if (paramMap != null) {
				Iterator keys = paramMap.keySet().iterator();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					method.setParameter(key, (String) paramMap.get(key));
				}
			}
			int statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + method.getStatusLine());
			}
			data = new String(method.getResponseBody(), "utf-8");
		} catch (HttpException e) {
			logger.error("Please check your provided http address!");
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (method != null)
				method.releaseConnection();
		}
		return data;
	}

	/**
	 * 按照 头信息的请求体发起的REST请求
	 * 
	 * @param uri
	 *            远端服务的URI地址
	 * @param requestBody
	 *            清秋体字符串
	 * @param headerMap
	 *            头信息
	 * @return 返回的字符串信息 string
	 * @throws NullPointerException
	 *             the null pointer exception
	 * @throws HttpException
	 *             the http exception
	 * @throws IOException
	 *             the io exception
	 * @author liujiawei
	 */
	public static String post(String uri, String requestBody, Map<String, String> headerMap)
			throws NullPointerException, HttpException, IOException {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setConnectionManagerTimeout(_timeout);
		httpClient.getParams().setSoTimeout(_timeout);
		PostMethod method = new PostMethod();
		method.setURI(new URI(uri, false));
		if (headerMap != null) {
			Iterator<String> keys = headerMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				method.setRequestHeader(key, headerMap.get(key));
			}
		}
		if (StringUtil.isNotEmpty(requestBody)) {
			StringRequestEntity stringRequestEntity = new StringRequestEntity(requestBody, null,
					null);
			method.setRequestEntity(stringRequestEntity);
		}

		long beginTime = System.currentTimeMillis();
		int statusCode = httpClient.executeMethod(method);
		long endTime = System.currentTimeMillis();

		if (statusCode != HttpStatus.SC_OK) {
			logger.error("Method failed: " + method.getStatusLine());
		}
		try {
			String responseData = new String(method.getResponseBody(), "utf-8");
			logger.info(
					"Post url:{}, request header:{}, request body:{}, response data:{}, costs {}ms.",
					uri, ToStringBuilder.reflectionToString(headerMap), requestBody, responseData,
					(endTime - beginTime));
			return responseData;
		} finally {
			if (method != null) {
				method.releaseConnection();
			}
		}
	}

	/**
	 * Post string.
	 * 
	 * @param uri
	 *            the uri
	 * @param postData
	 *            the post data
	 * @return the string
	 */
	public static String post(String uri, String postData) {
		return post(uri, postData, "application/json;charset=UTF-8");
	}

	public static String post(String uri, String postData, String contentType) {
		String data = "";
		HttpClient httpClient = new HttpClient();
		httpClient.setConnectionTimeout(_timeout);
		httpClient.setTimeout(_timeout);

		PostMethod method = null;
		try {
			method = new PostMethod();
			method.setURI(new URI(uri, false));
			if (postData != null) {
				method.setRequestBody(postData);
			}
			method.addRequestHeader("Content-Type", contentType);
			int statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + method.getStatusLine());
			}
			data = new String(method.getResponseBody(), "utf-8");
		} catch (HttpException e) {
			logger.error("Please check your provided http address!");
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (method != null)
				method.releaseConnection();
		}
		return data;
	}

	/**
	 * Put string.
	 * 
	 * @param uri
	 *            the uri
	 * @param postData
	 *            the post data
	 * @return the string
	 */
	public static String put(String uri, String postData) {
		String data = "";
		HttpClient httpClient = new HttpClient();
		httpClient.setConnectionTimeout(_timeout);
		httpClient.setTimeout(_timeout);

		PutMethod method = null;
		try {
			method = new PutMethod();
			method.setURI(new URI(uri, false));
			// param
			if (postData != null) {
				method.setRequestBody(postData);
			}
			method.addRequestHeader("Content-Type", "application/json;charset=UTF-8");
			int statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + method.getStatusLine());
			}
			data = new String(method.getResponseBody(), "utf-8");
		} catch (HttpException e) {
			logger.error("Please check your provided http address!");
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (method != null)
				method.releaseConnection();
		}
		return data;
	}

	/**
	 * Put string.
	 * 
	 * @param uri
	 *            the uri
	 * @param requestBody
	 *            the request body
	 * @param headerMap
	 *            the header map
	 * @return the string
	 * @throws NullPointerException
	 *             the null pointer exception
	 * @throws HttpException
	 *             the http exception
	 * @throws IOException
	 *             the io exception
	 */
	public static String put(String uri, String requestBody, Map<String, String> headerMap)
			throws NullPointerException, HttpException, IOException {

		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setConnectionManagerTimeout(_timeout);
		httpClient.getParams().setSoTimeout(_timeout);
		PutMethod method = new PutMethod();
		method.setURI(new URI(uri, false));
		if (headerMap != null) {
			Iterator<String> keys = headerMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				method.setRequestHeader(key, headerMap.get(key));
			}
		}
		if (StringUtil.isNotEmpty(requestBody)) {
			StringRequestEntity stringRequestEntity = new StringRequestEntity(requestBody, null,
					null);
			method.setRequestEntity(stringRequestEntity);
		}

		long beginTime = System.currentTimeMillis();
		int statusCode = httpClient.executeMethod(method);
		long endTime = System.currentTimeMillis();

		if (statusCode != HttpStatus.SC_OK) {
			logger.error("Method failed: " + method.getStatusLine());
		}
		try {
			String responseData = new String(method.getResponseBody(), "utf-8");
			logger.info(
					"Put url:{}, request header:{}, request body:{}, response data:{}, costs {}ms.",
					uri, ToStringBuilder.reflectionToString(headerMap), requestBody, responseData,
					(endTime - beginTime));
			return responseData;
		} finally {
			method.releaseConnection();
		}

	}

	/**
	 * Send string.
	 * 
	 * @param msg
	 *            the msg
	 * @param address
	 *            the address
	 * @param method
	 *            the method
	 * @return the string
	 */
	public static String send(Object msg, String address, String method) {
		String t = "ERROR_OK";
		if (method == null)
			method = "POST";
		HttpURLConnection conn = null;
		try {
			URL url = new URL(address);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json;UTF-8");
			OutputStream os = conn.getOutputStream();
			os.write(msg.toString().getBytes());
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				t = "HTTP_ERROR";
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),
						"UTF-8"));
				String response = null;
				while ((response = br.readLine()) != null) {
					try {
						Object status = response;
						t = status.toString();
					} catch (Exception e) {
						t = "HTTP_ERROR";
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			t = "HTTP_ERROR";
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return t;
	}

	/**
	 * Send map.
	 * 
	 * @param msg
	 *            the msg
	 * @param address
	 *            the address
	 * @param method
	 *            the method
	 * @param other
	 *            the other
	 * @return the map
	 * @throws IOException
	 *             the io exception
	 */
	public static Map<String, String> send(Object msg, String address, String method, String other)
			throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		String t = "ERROR_OK";
		if (method == null)
			method = "POST";
		HttpURLConnection conn = null;
		try {
			URL url = new URL(address);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json;UTF-8");
			OutputStream os = conn.getOutputStream();
			os.write(msg.toString().getBytes());
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				map.put("key", conn.getResponseCode() + "");
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),
						"UTF-8"));
				String response = null;
				while ((response = br.readLine()) != null) {
					try {
						Object status = response;
						t = status.toString();
						map.put("value", t);
						map.put("key", "200");
					} catch (Exception e) {
						map.put("key", conn.getResponseCode() + "");
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			map.put("key", conn.getResponseCode() + "");
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return map;
	}

	/**
	 * Delete rest string.
	 * 
	 * @param address
	 *            the address
	 * @return the string
	 */
	public static String deleteRest(String address) {
		HttpURLConnection conn = null;
		String t = "ERROR_OK";
		try {
			URL url = new URL(address);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json;UTF-8");
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				t = "HTTP_ERROR";
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),
						"UTF-8"));
				String response = null;
				while ((response = br.readLine()) != null) {
					try {
						Object status = response;
						t = status.toString();
					} catch (Exception e) {
						t = "HTTP_ERROR";
						logger.error(e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			t = "HTTP_ERROR";
			logger.error(e.getMessage());
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return t;
	}
}
