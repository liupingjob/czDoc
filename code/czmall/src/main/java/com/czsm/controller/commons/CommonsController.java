package com.czsm.controller.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czsm.entity.commons.StringMsg;
import com.czsm.util.Constants;
import com.czsm.util.MailUtil;
import com.czsm.util.NumberUtil;
import com.czsm.util.SmsUtil;
import com.czsm.util.VerifyCodeUtils;

/**
 * 公用工具类（验证码 短信 邮箱 OSS Redis Kafka等系列公众操作）
 * 
 * @author Mac (刘平) 20180804
 */
@Controller
public class CommonsController {

	/**
	 * 图片验证码生成方法 存放session
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("vcode")
	public void vcode(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 生成随机字符串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入回话session
		HttpSession session = request.getSession(true);
		// 删除以前的验证码
		session.removeAttribute(Constants.PIC_VERIFY_CODE);
		session.setAttribute(Constants.PIC_VERIFY_CODE, verifyCode.toLowerCase());
		// 生成图片 高度 宽度
		int w = 75, h = 42;
		try {
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 验证 图形验证码是否正确
	 * 
	 * @param vcode 传入用户输入的验证码
	 * @return返回结果 验证码错误或success
	 */
	@RequestMapping("validatePicVCode")
	@ResponseBody
	public StringMsg vcode(String vcode, HttpServletRequest request) {
		String msg = ""; // 接收下面判断的结果，以返回给前端
		// 这是系统的验证码
		String sVcode = (String) request.getSession().getAttribute(Constants.PIC_VERIFY_CODE);
		if (!sVcode.equals(vcode)) { // 判断用户输入的验证码是否正确
			msg = Constants.VCODE_ERROR; // 验证码错误
		} else {
			msg = Constants.VCODE_CORRECT; // 验证码正确
		}
		return StringMsg.setMsgs(msg);
	}

	/**
	 * 发送短信验证码功能
	 * 
	 * @param phoneNum 手机号码
	 * @return
	 */
	@RequestMapping("buyerSendSmsVcode")
	@ResponseBody
	public String sentSms(String phoneNum, HttpSession session) {
		// 取得验证码四位随机数
		String vcode = NumberUtil.getRandomNum(Constants.VCODE_LENGTH);
		// 发送验证码
		SmsUtil.sendSms(phoneNum, vcode);
		// 清除原session中的值
		session.removeAttribute(Constants.SMS_VERIFY_CODE);
		// 新验证码存入session
		session.setAttribute(Constants.SMS_VERIFY_CODE, vcode);
		// 返回成功信息
		return Constants.VCODE_SENT;
	}

	/**
	 * 发送邮件验证码功能
	 * 
	 * @param mailUrl 邮箱地址
	 * @return
	 */
	@RequestMapping("buyerSendMailVcode")
	@ResponseBody
	public String sentMail(String mailUrl, HttpSession session) {
		MailUtil.sendMail(mailUrl, NumberUtil.getRandomNum(Constants.VCODE_LENGTH));
		// 取得验证码四位随机数
		String vcode = NumberUtil.getRandomNum(Constants.VCODE_LENGTH);
		// 发送验证码
		MailUtil.sendMail(mailUrl, vcode);

		// 清除原session中的值
		session.removeAttribute(Constants.MAIL_VERIFY_CODE);
		// 新验证码存入session
		session.setAttribute(Constants.MAIL_VERIFY_CODE, vcode);
		// 返回成功信息
		return Constants.VCODE_SENT;
	}

	/**
	 * 验证手机验证码是否正确
	 * 
	 * @param 验证码
	 * @return
	 */
	@RequestMapping("validateSmsVCode")
	@ResponseBody
	public String validateSmsVCode(String vcode, HttpSession session) {
		// 从session中拿到手机验证码
		String realVcode = (String) session.getAttribute(Constants.SMS_VERIFY_CODE);
		// 判断是否相等
		return vcode.equals(realVcode) ? Constants.VCODE_CORRECT : Constants.VCODE_ERROR;
	}

	/**
	 * 验证邮箱验证码是否正确
	 * 
	 * @param 验证码
	 * @return
	 */
	@RequestMapping("validateMailVCode")
	@ResponseBody
	public String validateMailVCode(String vcode, HttpSession session) {
		// 从session中拿到邮箱验证码
		String realVcode = (String) session.getAttribute(Constants.MAIL_VERIFY_CODE);
		// 判断是否相等
		return vcode.equals(realVcode) ? Constants.VCODE_CORRECT : Constants.VCODE_ERROR;
	}

}
