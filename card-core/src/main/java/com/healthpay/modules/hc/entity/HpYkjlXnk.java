package com.healthpay.modules.hc.entity;

import java.io.Serializable;
import java.util.Date;

import com.healthpay.common.persistence.DataEntity;

/**
 *
 * @author mabaoying
 * @ClassName:  HpYkjlXnk
 * @Description: 虚拟卡用卡记录信息
 * @date: 2019年7月31日
 * @最后修改人:
 * @最后修改时间:
 *
 */
public class HpYkjlXnk extends DataEntity<HpYkjlXnk>implements Serializable {

    private String id;//id
    private String eHealthCardId;//电子健康卡id
    private String medstepCode;//诊疗环节代码
    private String terminalCode;//扫码枪终端标识号  组织机构代码+4位科室代码（科室代码见数据字典）
    private String channelCode;//刷卡终端类型编号
    private Date useTime;//用卡时间
    private Date uploadTime;//上传时间
    private String merId;//商户号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String geteHealthCardId() {
        return eHealthCardId;
    }

    public void seteHealthCardId(String eHealthCardId) {
        this.eHealthCardId = eHealthCardId;
    }

    public String getMedstepCode() {
        return medstepCode;
    }

    public void setMedstepCode(String medstepCode) {
        this.medstepCode = medstepCode;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

}
