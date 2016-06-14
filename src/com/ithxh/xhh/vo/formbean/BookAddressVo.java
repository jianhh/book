package com.ithxh.xhh.vo.formbean;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
public class BookAddressVo implements Serializable {

	private String addressid;
	@NotBlank(message="省份不能为空")
	private String province;
	@NotBlank(message="城市不能为空")
	private String city;
	@NotBlank(message="区县不能为空")
	private String county;
	@NotBlank(message="请填写详细地址")
	private String detailaddress;
	@NotBlank(message="邮编不能为空")
	private String postalcode;
	@NotBlank(message="收货人不能为空")
	private String consignee;
	@NotBlank(message="联系电话不能为空")
	private String phonenumber;
	private String userid;
	private String isdefault;
	private String pname;
	private String cname;
	private String coname;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getConame() {
		return coname;
	}
	public void setConame(String coname) {
		this.coname = coname;
	}
	public String getAddressid() {
		return addressid;
	}
	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getDetailaddress() {
		return detailaddress;
	}
	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}
	
}
