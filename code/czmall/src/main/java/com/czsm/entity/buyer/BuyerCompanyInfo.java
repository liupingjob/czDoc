package com.czsm.entity.buyer;
/**
 * 买家公司信息表
 * @author 孙湘杰
 *
 */
public class BuyerCompanyInfo {
	private int companyId;   //买家公司编号    
	private String companyName; //买家公司名称
	private String licenseNumber;  //营业执照编号
	private String companyAddress;  //买家公司地址
	private String legalName;    //公司法人
	private String legalIdentity;  //法人身份证号
	private String companyTel;    //公司联系方式
	private String companyPlace; //公司注册地
	private String licensePic;   //营业执照图片
	
	public int getCompanyId() {
		return companyId;
	}
	
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getLicenseNumber() {
		return licenseNumber;
	}
	
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	
	public String getCompanyAddress() {
		return companyAddress;
	}
	
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	
	public String getLegalName() {
		return legalName;
	}
	
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	
	public String getLegalIdentity() {
		return legalIdentity;
	}
	
	public void setLegalIdentity(String legalIdentity) {
		this.legalIdentity = legalIdentity;
	}
	
	public String getCompanyTel() {
		return companyTel;
	}
	
	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	
	public String getCompanyPlace() {
		return companyPlace;
	}
	
	public void setCompanyPlace(String companyPlace) {
		this.companyPlace = companyPlace;
	}
	
	public String getLicensePic() {
		return licensePic;
	}
	
	public void setLicensePic(String licensePic) {
		this.licensePic = licensePic;
	}
	
	
}
