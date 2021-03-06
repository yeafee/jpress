/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import java.util.Map;
import java.util.concurrent.Callable;

import com.jfinal.weixin.sdk.cache.IAccessTokenCache;
import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.RetryUtils;

/**
 * 认证并获取 access_token API
 * http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96access_token
 * 
 * AccessToken默认存储于内存中，可设置存储于redis或者实现IAccessTokenCache到数据库中实现分布式可用
 * 
 * 具体配置：
 * <pre>
 * ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache());
 * </pre>
 */
public class AccessTokenApi {
	
	// "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	
	// 利用 appId 与 accessToken 建立关联，支持多账户
	static IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();
	
	/**
	 * 从缓存中获取 access token，如果未取到或者 access token 不可用则先更新再获取
	 */
	public static AccessToken getAccessToken() {
		String appId = ApiConfigKit.getApiConfig().getAppId();
		AccessToken result = accessTokenCache.get(appId);
		if (result != null && result.isAvailable())
			return result;
		
		refreshAccessToken();
		return accessTokenCache.get(appId);
	}
	
	/**
	 * 直接获取 accessToken 字符串，方便使用
	 * @return String accessToken
	 */
	public static String getAccessTokenStr() {
		return getAccessToken().getAccessToken();
	}
	
	/**
	 * 强制更新 access token 值
	 */
	public static synchronized void refreshAccessToken() {
		ApiConfig ac = ApiConfigKit.getApiConfig();
		String appId = ac.getAppId();
		String appSecret = ac.getAppSecret();
		final Map<String, String> queryParas = ParaMap.create("appid", appId).put("secret", appSecret).getData();
		
		// 最多三次请求
		AccessToken result = RetryUtils.retryOnException(3, new Callable<AccessToken>() {
			
			@Override
			public AccessToken call() throws Exception {
				String json = HttpUtils.get(url, queryParas);
				return new AccessToken(json);
			}
		});
		
		// 三次请求如果仍然返回了不可用的 access token 仍然 put 进去，便于上层通过 AccessToken 中的属性判断底层的情况
		accessTokenCache.set(ac.getAppId(), result);
	}

}
