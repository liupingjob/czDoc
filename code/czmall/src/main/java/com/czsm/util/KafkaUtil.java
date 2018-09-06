package com.czsm.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaUtil {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * 发送消息到kafka,主题为test
	 */
	public void sendTest() {
		kafkaTemplate.send("test", "hello,kafka  " + new Date().getTime());
	}
}