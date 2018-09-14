package com.czsm.onekey;
/**
 * Mac （刘平）
 * @author Administrator
 *
 */
public class OneKeyDeploy {
	//需要修改本地工作空间路径 
	private final static String localWarPath = "C:\\workspace\\czmall\\target\\czmailWeb.war";

	public static void main(String[] args) throws Exception {
		SftpUtil.uploadWarFile(localWarPath);
		SSHUtil.oneKeyDeploy();
	}
}
