package com.czsm.onekey;

public class downloadLog {
	private final static String host = "192.168.1.110";
	private final static String user = "root";
	private final static String pass = "admin123";
	private final static String localWarPath = "C:\\log.txt";
	private final static String logFileName = "nohup.out";
	private final static String linuxWarPath = "/root";
	private static SftpUtil ftp = new SftpUtil(user, pass, host, 22);

	public static void main(String[] args) throws Exception {
		ftp.login();
		ftp.download(linuxWarPath, logFileName, localWarPath);
	}
}
