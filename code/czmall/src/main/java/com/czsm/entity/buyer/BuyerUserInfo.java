package com.czsm.entity.buyer;

import java.io.Serializable;
/**
 * 买家用户信息
 * @author 孙湘杰
 *
 */
public class BuyerUserInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int userid; // 买家用户编号
	private String username; // 买家用户名
	private String pwd; // 买家密码
	private String vcode; // 图形验证码
	private String tel; // 买家联系电话
	private String email; // 买家邮箱号
	private String inputVcode; // 短信或邮箱验证码
	private String name;  //买家姓名
	private String petname;  //买家昵称
	private String birthday;  //买家生日
	private String sex;   //买家性别
	private String identity;  //买家身份证号码
	private String job;   //买家所属行业
	private String address;   //买家地址
	private String remark;   //买家备注
	private String headPortrait;  //买家头像
	private String createdate;   //创建日期
	private String updatedate;   //修改日期

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInputVcode() {
		return inputVcode;
	}

	public void setInputVcode(String inputVcode) {
		this.inputVcode = inputVcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
