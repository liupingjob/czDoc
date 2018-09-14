package com.czsm.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Kafka工具类
 * 
 * @author Mac（刘平）
 *
 */
@Component
public class KafkaUtil {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * 发送消息到kafka,主题为test
	 */
	public void sendTest() {
		kafkaTemplate.send(Constants.KAFKA_TOPIC, "hello,kafka  " + new Date().getTime());
	}

	/**
	 * 发送消息到kafka默认的topic
	 */
	public void sendMsg(String msg) {
		kafkaTemplate.send(Constants.KAFKA_TOPIC, msg);
	}

	/**
	 * 发送消息到kafka默认的topic
	 */
	public void sendMsg(String topic, String msg) {
		kafkaTemplate.send(topic, msg);
	}
}