package com.czsm.test;

import org.jboss.logging.Logger;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumer {
	private static Logger logger = Logger.getLogger(KafkaConsumer.class);

	/**
	 * 监听test主题,有消息就读取
	 * 
	 * @param message
	 */
	@KafkaListener(topics = { "test" })
	public void consumer(String message) {
		System.out.println("aaaaaaaaaaa");
		logger.info("test topic message : "+ message);
	}
}