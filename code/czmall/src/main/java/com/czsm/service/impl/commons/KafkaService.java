package com.czsm.service.impl.commons;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.czsm.util.Constants;

/**
 * Kafka工具类
 * 
 * @author Mac（刘平）
 *
 */
@Component
public class KafkaService {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * 测试方法 发送消息到kafka,主题为czsm
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
	 * 发送消息到kafka自定义的topic
	 */
	public void sendMsg(String topic, String msg) {
		kafkaTemplate.send(topic, msg);
	}
}