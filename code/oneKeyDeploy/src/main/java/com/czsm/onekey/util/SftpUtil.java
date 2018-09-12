package com.czsm.onekey.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * Created by  刘平 Mac 20180911
 */
public class SftpUtil {

	public ChannelSftp sftp;

	private Session session;
	/**
	 * SFTP 登录用户名
	 */
	private String username;
	/**
	 * SFTP 登录密码
	 */
	private String password;
	/**
	 * 私钥
	 */
	private String privateKey;
	/**
	 * SFTP 服务器地址IP地址
	 */
	private String host = "";
	/**
	 * SFTP 端口
	 */
	private int port;

	/**
	 * 构造基于密码认证的sftp对象
	 */
	public SftpUtil(String username, String password, String host, int port) {
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
	}

	/**
	 * 构造基于秘钥认证的sftp对象
	 */
	public SftpUtil(String username, String host, int port, String privateKey) {
		this.username = username;
		this.host = host;
		this.port = port;
		this.privateKey = privateKey;
	}

	public SftpUtil() {
	}

	/**
	 * 连接sftp服务器
	 */
	public void login() throws Exception {
		JSch jsch = new JSch();
		if (privateKey != null) {
			jsch.addIdentity(privateKey);// 设置私钥
		}

		session = jsch.getSession(username, host, port);

		if (password != null) {
			session.setPassword(password);
		}
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		// TODO 移除kerberos验证方法
		config.put("PreferredAuthentications", "publickey,keyboard-interactive,password");

		session.setConfig(config);
		session.connect();

		Channel channel = session.openChannel("sftp");
		channel.connect();

		sftp = (ChannelSftp) channel;

	}

	/**
	 * 关闭连接 server
	 */
	public void logout() {
		if (sftp != null) {
			if (sftp.isConnected()) {
				sftp.disconnect();
			}
		}
		if (session != null) {
			if (session.isConnected()) {
				session.disconnect();
			}
		}
	}

	/**
	 * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
	 *
	 * @param path     上传到该目录
	 * @param fileName sftp端文件名
	 * @param input    输入流
	 */
	public void upload(String path, String fileName, InputStream input) throws SftpException {
		try {
			sftp.cd(path);
		} catch (SftpException e) {
			// 目录不存在，则创建文件夹
			String[] dirs = path.split("/");
			for (String dir : dirs) {
				if (null == dir || "".equals(dir))
					continue;
				try {
					sftp.cd(dir);
				} catch (SftpException ex) {
					sftp.mkdir(dir);
					sftp.cd(dir);
				}
			}
		}
		sftp.put(input, fileName); // 上传文件
	}

	/**
	 * 下载文件。
	 *
	 * @param directory    下载目录
	 * @param downloadFile 下载的文件
	 * @param saveFile     存在本地的路径
	 */
	public void download(String directory, String downloadFile, String saveFile)
			throws SftpException, FileNotFoundException {
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		File file = new File(saveFile);
		sftp.get(downloadFile, new FileOutputStream(file));
	}

	/**
	 * 删除文件
	 *
	 * @param directory  要删除文件所在目录
	 * @param deleteFile 要删除的文件
	 */
	public void delete(String directory, String deleteFile) throws SftpException {
		sftp.cd(directory);
		sftp.rm(deleteFile);
	}

	/**
	 * 列出目录下的文件
	 *
	 * @param directory 要列出的目录
	 * @param
	 */
	public Vector<?> listFiles(String directory) throws SftpException {
		return sftp.ls(directory);
	}

	private final static String user = "root";
	private final static String pass = "admin123";
	private final static String warFileName = "czmailWeb.war";
	private final static String host1 = "192.168.1.110";

	private final static String linuxWarPath = "/root/czsm";
	private static SftpUtil ftp = new SftpUtil(user, pass, host1, 22);

	public static void uploadWarFile(String localWarPath) throws Exception {
		File file = new File(localWarPath);
		if (!file.exists()) {
			File directory = new File("");// 参数为空
			String courseFile = directory.getCanonicalPath();
			courseFile=courseFile.substring(0, courseFile.lastIndexOf("\\")+1)+"czmall\\\\target\\\\czmailWeb.war";
			file=new File(courseFile);
		}
		ftp.login();
		Calendar cale = Calendar.getInstance();
		try {
			String fileName = "_" + cale.get(Calendar.MONTH) + "-" + cale.get(Calendar.DAY_OF_MONTH) + "-"
					+ cale.get(Calendar.HOUR_OF_DAY) + "-" + cale.get(Calendar.MINUTE) + "-"
					+ cale.get(Calendar.SECOND);
			ftp.sftp.rename("/root/czsm/czmailWeb.war", "/root/czsm/czmailWeb" + fileName + "_backup.war");
		} catch (Exception e) {
			// TODO: handle exception
		}
		ftp.upload(linuxWarPath, warFileName, new FileInputStream(file));
	}

	// 上传文件测试
	public static void main(String[] args) throws Exception {
//		SftpUtil sftp = new SftpUtil("root", "admin123", "192.168.1.110", 22);
//		sftp.login();
//		File file = new File("D:\\SVN文档\\开发文档\\FindBugs插件安装与使用.docx");
//		InputStream is = new FileInputStream(file);
//
//		sftp.upload("/root/czsm", "FindBugs插件安装与使用.docx", is);
//		is.close();
//		sftp.logout();
//		System.out.println(sftp.getClass().getResource("/").getPath());
		File directory = new File("");// 参数为空
		String courseFile = directory.getCanonicalPath();
		System.out.println(courseFile.substring(0, courseFile.lastIndexOf("\\")));
	}
}