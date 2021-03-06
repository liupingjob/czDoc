package com.czsm;

import java.util.Properties;

import org.jboss.logging.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.czsm.config.CorsFilter;
import com.czsm.service.impl.commons.KafkaService;
import com.czsm.util.Constants;
import com.github.pagehelper.PageHelper;

/**
 * @author Mac(刘平) 20180728
 *
 */
@SpringBootApplication
@MapperScan("com.czsm.dao.*") // dao包的扫描注册
@ComponentScan(basePackages = { "com.czsm.*" }) // 所有项目基准包注册
@EnableTransactionManagement
@EnableCaching
@EnableRedisHttpSession
@EnableScheduling
public class SpringBootApllication {
	private static Logger logger = Logger.getLogger(SpringBootApllication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApllication.class, args);
		logger.info("\n项目已经启动成功  请开始使用。。。。。。。。。。。\n");
	}

	/**
	 * 分页插件集成 注册成一个Bean使用时候可直接调用
	 * 
	 * @return
	 */
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");// offset作为PageNum使用
		p.setProperty("rowBoundsWithCount", "true");// RowBounds方式是否做count查询
		p.setProperty("pageSizeZero", "true");// pageSizeZero 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页
		p.setProperty("reasonable", "true");// 分页合理化，true开启，如果分页参数不合理会自动修正。默认false不启用
		pageHelper.setProperties(p);
		return pageHelper;
	}

	/**
	 * 解决跨域后AJAX请求sessionID不一致问题 加的过滤器
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> CorsFilterRegistration() {

		FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<CorsFilter>();
		registration.setFilter(new CorsFilter());
		registration.addUrlPatterns("/*");
		registration.setName("CorsFilter");
		registration.setOrder(1);
		return registration;
	}

	/**
	 * Kafka设置一个定时器测试发送队列消息
	 */
	@Autowired
	private KafkaService kafkaSender;

	// 然后每隔1000秒执行一次
	@Scheduled(fixedRate = 1000 * 1000)
	public void testKafka() throws Exception {
		kafkaSender.sendTest();
	}

	/**
	 * 监听Kafka中的 test主题,有消息就读取
	 * 
	 * @param message
	 */
	@KafkaListener(topics = { Constants.KAFKA_TOPIC })
	public void consumer(String message) {
		logger.info("test topic message : "+ message);
	}

}
