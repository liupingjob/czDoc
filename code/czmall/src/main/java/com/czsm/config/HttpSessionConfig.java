package com.czsm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 启用springBoot结合redis自动管理session
 * 
 * @author 刘平（Mac） 20180901
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
	 
}