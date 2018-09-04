package com.czsm.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil {
	// ftp服务器ip地址
	private static final String FTP_ADDRESS = "192.168.1.110";
	// 端口号
	private static final int FTP_PORT = 22;
	// 用户名
	private static final String FTP_USERNAME = "root";
	// 密码
	private static final String FTP_PASSWORD = "admin123";
	// 图片路径
	private static final String FTP_BASEPATH = "/home/images";

	public static boolean uploadFile(String originFileName, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		ftp.setControlEncoding("GBK");
		try {
			ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
			ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.makeDirectory(FTP_BASEPATH);
			ftp.changeWorkingDirectory(FTP_BASEPATH);
			ftp.storeFile(originFileName, input);
			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

}