package com.czsm.onekey;

import java.util.Scanner;

import com.czsm.onekey.util.Constants;
import com.czsm.onekey.util.SSHUtil;
import com.czsm.onekey.util.SftpUtil;

public class OneKeyOption {
	public static void main(String[] args) throws Exception {
		System.out.println("****欢迎使用一键部署服务******");
		System.out.println(" 1、一键部署web应用到Linux服务器 ");
		System.out.println(" 2、下载Linux服务器日志文件到本地");
		System.out.println(" 3、查看Linux服务器实时日志显示");
		System.out.println(" 4、重启Linux服务器【慎重】");
		System.out.println(" 5、重启后启动redis服务器");
		System.out.println(" 6、启动zookeeper服务器");
		System.out.println(" 7、启动kafka 服务器");
		System.out.print("请输入您要的操作的序号然后回车     ");
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		if (input == 1) {
			System.out.println("*******************一键部署Linux测试前置条件******************************");
			System.out.println("一键部署功能将会首先在linux上进行备份原项目 如部署不成功可随机回退版本");
			System.out.println("          1、确定项目组成功代码已提交SVN并且本机SVN代码已更新 ");
			System.out.println("          2、确定代码本地代码启动正常并且基本功能（如：首页）正常访问 ");
			System.out.println("          3、确定已运行run-->maven install  并提示成功");
			System.out.println("          4、确定已通知到项目组（如企业QQ群） 项目正准备部署中");
			System.out.print(" **********以上都已确定请输入y按回车将直接部署************************");
			scan.next();
			SftpUtil.uploadWarFile(Constants.localWarPath);
			SSHUtil.sshCommand(Constants.deploy);
		} else if (input == 2) {
			System.out.println("*************下载服务器日志******************");
			System.out.println("服务器日志默认下载到c盘根目录下C:\\log.txt");
			System.out.println("*******请手动关闭此java运行类**************");
			Constants.ftp.login();
			Constants.ftp.download(Constants.linuxWarPath, Constants.logFileName, Constants.localLogPath);
		} else if (input == 3) {
			SSHUtil.sshCommand(Constants.showLog);
		} else if (input == 4) {
			System.out.println("测试服务器重启需要5分钟左右时间 ");
			System.out.println("确定已通知到项目组（如企业QQ群） 服务器正在重启中");
			System.out.println("重启服务器完成后需要启动redis zookeeper kafka服务");
			System.out.println("确证要重启服务器请输入y按回车键");
			scan.next();
			SSHUtil.sshCommand(Constants.reboot);
		} else if (input == 5) {
			System.out.println("正在启动Redis服务 看到 Configuration loaded  表示启动成功");
			SSHUtil.sshCommand(Constants.startRedisServer);
		} else if (input == 6) {
			System.out.println("正在启动zooKeeper服务 看到 STARTED  表示启动成功");
			SSHUtil.sshCommand(Constants.startZeekeeperServer);
		} else if (input == 7) {
			System.out.println("正在启动Kafka服务 看到 追加到 nohup.out 表示启动成功");
			SSHUtil.sshCommand(Constants.startKafkaServer);
		}
		scan.close();
	}
}
