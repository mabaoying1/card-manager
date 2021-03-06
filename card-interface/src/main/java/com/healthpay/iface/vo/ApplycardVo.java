package com.healthpay.iface.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthpay.common.annotation.NotNull;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 
 * Description:   P001接口-开卡申请数据域
 * Filename   :   JsonApplycardVo.java  
 * @author    :   yyl 
 * @version   :   1.0  
 * Create at  :   2016年5月31日 下午3:27:12  
 *  
 *
 */
public class ApplycardVo {
	
	private String nationality ; //国籍
	private String docuType;		// 证件类型
	private String docuId;		//证件号码
	private String healthId;		// 健康档案编号
	private String icCardId;        //实体卡卡号
	private Integer type;           //实体卡类型
	private String newFarmid;		// 新农合证（卡）号
	private String name;		// 姓名
	private Date birth;		// 出生日期
	private String profession;		// 职业（代码）
	private Integer addressType;		// 地址类别（代码）
	private Integer sex;		// 性别（代码）
	private String nation;		// 民族（代码）
	private String maritalStatus;		// 婚姻状况（代码）
	private Integer educationLevel;		// 文化程度（代码）
	private String provcode;		// 省代码(户籍)
	private String provname;		// 省名称(户籍)
	private String citycode;		// 市代码(户籍)
	private String cityname;		// 市名称(户籍)
	private String countycode;		// 县级代码(户籍)
	private String countyname;		// 县级名称(户籍)
	private String towncode;		// 镇级代码(户籍)
	private String townname;		// 镇级名称(户籍)
	private String villagecode;		// 村代码(户籍)
	private String villagename;		// 村名称(户籍)
	private String address;		// 地址(户籍地)
	private String postcode;		// 邮编
	private String provcode2;		// 省代码(居住地)
	private String provname2;		// 省名称(居住地)
	private String citycode2;		// 市代码(居住地)
	private String cityname2;		// 市名称(居住地)
	private String countycode2;		// 县级代码(居住地)
	private String countyname2;		// 县级名称(居住地)
	private String towncode2;		// 镇级代码(居住地)
	private String townname2;		// 镇级名称(居住地)
	private String villagecode2;		// 村代码(居住地)
	private String villagename2;		// 村名称(居住地)
	private String address2;		// 地址(居住地)
	private Integer paytype;		// 医疗费用支付方式（代码）
	private String phone;		// 本人手机
	private String tel;		// 本人电话
	private String email;		// 本人Email
	private String attnName;		// 联系人姓名
	private String attnPhone;		// 联系人手机
	private String attnRela;		// 联系人关系
	private Date appliDate;		// 审核时间
	private Integer securityCode;		// 安全码
	private String chipSerialid;		// 芯片序列号
	private Integer cardType;		// 卡的类别
	private String version;		// 规范版本
	private String office;		// 发卡机构代码
	private Date startDate;		// 发卡时间
	private Date validDate;		// 卡有效期
	private String password;		// 支付密码  开通支付必须填密码
	private String reserved;		// 预留文字信息
	private String img1;
	private String img2;
	private String applyType;//电子健康卡申请方式
	private String rzfs;//认证模式

	public ApplycardVo(){}
	
	@NotNull(name="国籍/地区")
	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@NotNull(name="证件类型")
	public String getDocuType() {
		return docuType;
	}


	public void setDocuType(String docuType) {
		this.docuType = docuType;
	}

	@NotNull(name="证件号码")
	@Pattern(regexp = "^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|2016)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$" ,message="证件格式不正确")
	public String getDocuId() {
		return docuId;
	}


	public void setDocuId(String docuId) {
		this.docuId = docuId;
	}


	public String getHealthId() {
		return healthId;
	}
	public void setHealthId(String healthId) {
		this.healthId = healthId;
	}
	public String getNewFarmid() {
		return newFarmid;
	}
	public void setNewFarmid(String newFarmid) {
		this.newFarmid = newFarmid;
	}
	@NotNull(name="姓名")
	@Pattern(regexp = "^[a-z0-9A-Z\\u4e00-\\u9fa5]+$",message = "姓名输入不合法")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
//	@NotNull
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public Integer getAddressType() {
		return addressType;
	}
	public void setAddressType(Integer addressType) {
		this.addressType = addressType;
	}
	@NotNull(name="性别")
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	@NotNull(name="民族")
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
//	@NotNull
	public Integer getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(Integer educationLevel) {
		this.educationLevel = educationLevel;
	}
	public String getProvcode() {
		return provcode;
	}
	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}
	public String getProvname() {
		return provname;
	}
	public void setProvname(String provname) {
		this.provname = provname;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getCountycode() {
		return countycode;
	}
	public void setCountycode(String countycode) {
		this.countycode = countycode;
	}
	public String getCountyname() {
		return countyname;
	}
	public void setCountyname(String countyname) {
		this.countyname = countyname;
	}
	public String getTowncode() {
		return towncode;
	}
	public void setTowncode(String towncode) {
		this.towncode = towncode;
	}
	public String getTownname() {
		return townname;
	}
	public void setTownname(String townname) {
		this.townname = townname;
	}
	public String getVillagecode() {
		return villagecode;
	}
	public void setVillagecode(String villagecode) {
		this.villagecode = villagecode;
	}
	public String getVillagename() {
		return villagename;
	}
	public void setVillagename(String villagename) {
		this.villagename = villagename;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

//	@NotNull
	public String getProvcode2() {
		return provcode2;
	}
	public void setProvcode2(String provcode2) {
		this.provcode2 = provcode2;
	}
//	@NotNull
	public String getProvname2() {
		return provname2;
	}
	public void setProvname2(String provname2) {
		this.provname2 = provname2;
	}
//	@NotNull
	public String getCitycode2() {
		return citycode2;
	}
	public void setCitycode2(String citycode2) {
		this.citycode2 = citycode2;
	}
	public String getCityname2() {
		return cityname2;
	}
	public void setCityname2(String cityname2) {
		this.cityname2 = cityname2;
	}
	public String getCountycode2() {
		return countycode2;
	}
	public void setCountycode2(String countycode2) {
		this.countycode2 = countycode2;
	}
	public String getCountyname2() {
		return countyname2;
	}
	public void setCountyname2(String countyname2) {
		this.countyname2 = countyname2;
	}
	public String getTowncode2() {
		return towncode2;
	}
	public void setTowncode2(String towncode2) {
		this.towncode2 = towncode2;
	}
	public String getTownname2() {
		return townname2;
	}
	public void setTownname2(String townname2) {
		this.townname2 = townname2;
	}
	public String getVillagecode2() {
		return villagecode2;
	}
	public void setVillagecode2(String villagecode2) {
		this.villagecode2 = villagecode2;
	}
	public String getVillagename2() {
		return villagename2;
	}
	public void setVillagename2(String villagename2) {
		this.villagename2 = villagename2;
	}
	@NotNull(name="居住地址")
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public Integer getPaytype() {
		return paytype;
	}
	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	@NotNull(name="手机号")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getAttnName() {
		return attnName;
	}
	public void setAttnName(String attnName) {
		this.attnName = attnName;
	}
	public String getAttnPhone() {
		return attnPhone;
	}
	public void setAttnPhone(String attnPhone) {
		this.attnPhone = attnPhone;
	}
	public String getAttnRela() {
		return attnRela;
	}
	public void setAttnRela(String attnRela) {
		this.attnRela = attnRela;
	}
	
	@NotNull(name="申请时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAppliDate() {
		return appliDate;
	}
	public void setAppliDate(Date appliDate) {
		this.appliDate = appliDate;
	}
	public Integer getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(Integer securityCode) {
		this.securityCode = securityCode;
	}
	public String getChipSerialid() {
		return chipSerialid;
	}
	public void setChipSerialid(String chipSerialid) {
		this.chipSerialid = chipSerialid;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public String getIcCardId() {
		return icCardId;
	}

	public void setIcCardId(String icCardId) {
		this.icCardId = icCardId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@NotNull(name="户籍地址")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotNull(name="电子健康卡申请方式")
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	@NotNull(name="认证模式")
	public String getRzfs() {
		return rzfs;
	}

	public void setRzfs(String rzfs) {
		this.rzfs = rzfs;
	}
}
