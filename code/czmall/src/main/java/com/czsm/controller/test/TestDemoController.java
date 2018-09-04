package com.czsm.controller.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.service.commons.RedisService;
import com.czsm.service.test.TestService;
import com.czsm.util.AliyunOSSUtil;
import com.czsm.util.FtpUtil;

/**
 * demo
 * 
 * @author Mac(刘平) 20180730
 */
@Controller
@RequestMapping("test")
public class TestDemoController {
	@Autowired
	private TestService testService;
	@Autowired
	private RedisService redisService;

	/**
	 * 普通返回页面和数据写法
	 * 
	 * @return
	 */
	@RequestMapping("test")
	public String test(Model mav) {
		List<BuyerUserInfo> list = testService.findUserPager(2, 3);
		System.out.println("===========" + list.size());
		mav.addAttribute("list", list);
		return "test";
	}

	/**
	 * AJAX请求返回页面和数据写法
	 * 
	 * @return
	 */
	@RequestMapping("test2")
	@ResponseBody
	public List<BuyerUserInfo> testAjax(int pageNum) {
		List<BuyerUserInfo> list = testService.findUserPager(pageNum, 3);
		return list;
	}

	/**
	 * 测试Redis
	 * 
	 * @return
	 */
	@RequestMapping("testRedis")
	@ResponseBody
	public List<BuyerUserInfo> testRedis(int pageNum, HttpSession session) {
		boolean result = redisService.hasKey("mac");
		redisService.set("hkw", "aaaaaaaaaaaaa");
		System.out.println(result);
		boolean result1 = redisService.hasKey("hkw");
		redisService.set("hkw1", "aaaaaaaaaaaaa");
		session.setAttribute("aaa", "bbbbb");
		System.out.println(result1);
		List<BuyerUserInfo> list = testService.findUserPager(3, 3);
		return list;
	}

	/**
	 * 店铺使用动态二级域名 获取动态url
	 * 
	 * @return
	 */
	@RequestMapping(value = "/shop/{shopName}", method = RequestMethod.GET)
	@ResponseBody
	public List<BuyerUserInfo> testPath(@PathVariable String shopName) {
		System.out.println(shopName);
		List<BuyerUserInfo> list = testService.findUserPager(3, 3);
		return list;
	}

	/**
	 * 测试FTP传送图片
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("testFtp")
	@ResponseBody
	public List<BuyerUserInfo> testFtp(@RequestParam("file") MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		InputStream inputStream = file.getInputStream();
		String filePath = null;

		Boolean flag = FtpUtil.uploadFile(fileName, inputStream);
		if (flag == true) {
			System.out.println("ftp上传成功！");
			filePath = fileName;
		}

		System.out.println(filePath); // 该路径图片名称，前端框架可用ngnix指定的路径+filePath,即可访问到ngnix图片服务器中的图片
		return null;
	}

	/**
	 * 测试OSS传送图片
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("testOss")
	public String testOss(@RequestParam("file") MultipartFile file,Model model) throws IOException {
		String fileName = file.getOriginalFilename();
		InputStream inputStream = file.getInputStream();
		String path=AliyunOSSUtil.upload(inputStream,"czsm/"+fileName);

		System.out.println(path); // 该路径图片名称，前端框架可用ngnix指定的路径+filePath,即可访问到ngnix图片服务器中的图片
		model.addAttribute("path",path);
		return "test";
	}
}