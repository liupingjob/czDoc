package com.czsm.onekey.util;

public class Constants {
	public final static String host = "192.168.1.110";
	public final static String user = "root";
	public final static String pass = "admin123";
	public final static String localWarPath = "C:\\workspace\\czmall\\target\\czmailWeb.war";
	public final static String localLogPath = "C:\\log.txt";
	public final static String logFileName = "nohup.out";
	public final static String linuxWarPath = "/root";
	public static SftpUtil ftp = new SftpUtil(user, pass, host, 22);
	
	
	public final static String showLog="tail -f /root/nohup.out";
	public final static String deploy="kill -9 $(ps -ef|grep czsm|grep -v grep|awk '{print $2}');nohup java -jar /root/czsm/czmailWeb.war";
	public final static String reboot="reboot";
	public final static String startRedisServer="kill -9 $(ps -ef|grep redis|grep -v grep|awk '{print $2}'); cd /usr/local/redis/ ; ./bin/redis-server ./redis.conf ;";
	public final static String startZeekeeperServer="kill -9 $(ps -ef|grep zookeeper|grep -v grep|awk '{print $2}'); cd /opt/zookeeper-3.5.4-beta/bin/ ;  ./zkServer.sh start ;";
	public final static String startKafkaServer="kill -9 $(ps -ef|grep kafka|grep -v grep|awk '{print $2}'); cd /opt/kafka_2.10-0.10.2.0/ ; nohup ./bin/kafka-server-start.sh config/server.properties ";
	public static final String startSolrServer = "cd /opt/solr-7.3.1 ; bin/solr start -force; ";
}
