package com.czsm.entity.buyer;

import java.io.Serializable;

public class BuyerUserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bid; // 买家用户编号
	private String username; // 买家用户名
	private String pwd; // 买家密码
	private String tel; // 买家联系电话
	private String vcode; // 图形验证码
	private String email; // 买家邮箱号
	private String inputVcode; // 短信或邮箱验证码

	private String bname;  //买家姓名
	private String petname;  //买家昵称
	private String birthday;  //买家生日
	private String bsex;   //买家性别
	private String bidentity;  //买家身份证号码
	private String bjob;   //买家所属行业
	private String baddress;   //买家地址
	private String remark;   //买家备注
	private String createdate;   //创建日期
	private String updatedate;   //修改日期

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
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

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBsex() {
		return bsex;
	}

	public void setBsex(String bsex) {
		this.bsex = bsex;
	}

	public String getBidentity() {
		return bidentity;
	}

	public void setBidentity(String bidentity) {
		this.bidentity = bidentity;
	}

	public String getBjob() {
		return bjob;
	}

	public void setBjob(String bjob) {
		this.bjob = bjob;
	}

	public String getBaddress() {
		return baddress;
	}

	public void setBaddress(String baddress) {
		this.baddress = baddress;
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

	public String getInputVcode() {
		return inputVcode;
	}

	public void setInputVcode(String inputVcode) {
		this.inputVcode = inputVcode;
	}

}
