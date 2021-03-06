package com.healthpay.iface.service.impl;

import com.healthpay.common.Constant;
import com.healthpay.common.exception.BusException;
import com.healthpay.common.utils.DbUtil;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.iface.util.StringUtils;
import com.healthpay.iface.vo.QueryIdVo;
import com.healthpay.iface.vo.RealCardVo;
import com.healthpay.iface.vo.ResResultVo;
import com.healthpay.modules.hc.entity.HpApplycard;
import com.healthpay.modules.iface.entity.HpIfaceMerchant;
import com.healthpay.modules.sys.entity.Office;
import com.healthpay.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.service.MainBusinessService;
import com.healthpay.iface.vo.ApplycardVo;
/**
* @ClassName: A1001 
* @Description: 开卡申请接口
* @author mabaoying
* @date 2019年7月30日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A1001 extends EHCAbstractHandlerImpl{

	@Autowired
	private MainBusinessService mainBusinessService;
	@BsoftCustomField
	public ApplycardVo customField ;

	@Override
	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {
		String nationality = customField.getNationality();//国籍
		String docuType = customField.getDocuType();//证件类型
		String docuId = customField.getDocuId();//证件号码
		String status = Constant.IfaceStatus.SUCCESS;
		String reason = "";
		String healthCardId = null;//电子健康卡id

		QueryIdVo vo=new QueryIdVo(nationality,docuType,docuId);
		//先查询是否允许开卡
		ResResultVo resultVo = mainBusinessService.doA1005(vo);
		//未申请开卡
		if (Constant.IfaceStatus.NONE.equals(resultVo.getStatus())) {
			try {
				HpApplycard applycard = new HpApplycard();
				MyBeanUtils.copyBeanNotNull2Bean(customField, applycard);//数据对象空值不拷贝到目标对象
				applycard.setStr00(appId);// 申请商户
				applycard.setStr01(customField.getApplyType());//电子健康卡申请方式
				applycard.setStr02(customField.getRzfs());//认证模式
				//证件类型+证件号码（唯一）
				applycard.setIdentityId(docuType + docuId);

				Office office = officeService.get("2");//获取机构 默认发卡机构为区域卫生局
				// FIXME 发卡机构
				applycard.setOffice(office);

				String code = "";//居住地
				if (StringUtils.isNotNull(applycard.getVillagecode2())) {// 村代码
					code = applycard.getVillagecode2();
				} else if (StringUtils.isNotNull(applycard.getTowncode2())) {// 镇代码
					code = applycard.getTowncode2();
				} else if (StringUtils.isNotNull(applycard.getCountycode2())) {// 县级代码
					code = applycard.getCountycode2();
				} else if (StringUtils.isNotNull(applycard.getCitycode2())) {// 市级代码
					code = applycard.getCitycode2();
				} else if (StringUtils.isNotNull(applycard.getProvcode2())) {// 省级代码
					code = applycard.getProvcode2();
				}
				if (StringUtils.isNotNull(code)) {
					applycard.setArea1(areaService.getByCode(code));
				}

				code = null;  //户籍地
				if (StringUtils.isNotNull(applycard.getVillagecode())) {
					code = applycard.getVillagecode();
				} else if (StringUtils.isNotNull(applycard.getTowncode())) {
					code = applycard.getTowncode();
				} else if (StringUtils.isNotNull(applycard.getCountycode())) {
					code = applycard.getCountycode();
				} else if (StringUtils.isNotNull(applycard.getCitycode())) {
					code = applycard.getCitycode();
				} else if (StringUtils.isNotNull(applycard.getProvcode())) {
					code = applycard.getProvcode();
				}
				if (StringUtils.isNotNull(code)) {
					applycard.setArea(areaService.getByCode(code));
				}

				//身份证图片地址
				if (StringUtils.isNotEmpty(customField.getImg1())) {
					applycard.setUrl0(writeToFile(customField.getImg1(), appId, realPath));
				}
				if (StringUtils.isNotEmpty(customField.getImg2())) {
					applycard.setUrl1(writeToFile(customField.getImg2(), appId, realPath));
				}
				

				User createBy = new User();
				createBy.setId("1");//默认系统管理员
				applycard.setCreateBy(createBy);
				applycard.setCreateDate(DbUtil.getDate());

				// 查询商户信息
				HpIfaceMerchant hpIfaceMerchant = hpIfaceMerchantService.getFormCache(appId);
				if (hpIfaceMerchant == null) {
					hpIfaceMerchant = hpIfaceMerchantService.get(appId);
				}

				if (hpIfaceMerchant == null) {
					throw new BusException("商户" + appId + "不存在");
				}

				applycard.setSource(Long.valueOf(hpIfaceMerchant.getSource().toString()));//数据来源
				// 保存健康卡申请记录
				hpApplycardService.save(applycard);

				status = Constant.IfaceStatus.WAITAUDIT; // 待审核
				boolean syncflag = StringUtils.isNotEmpty(customField.getIcCardId());// 实体卡卡号是否为空

				// 自动审核
				if (hpIfaceMerchant.getIsAutoAudit()) {
					healthCardId = hpApplycardService.audit(applycard, "1", !syncflag); // 审核通过
					status = Constant.IfaceStatus.AUDIT; // 审核通过
				}

				// 如果实体卡卡号不为空且审核通过
				if (syncflag && StringUtils.isNotEmpty(healthCardId)) {
					Integer type = 0;
					if (null != customField.getType())
						type = customField.getType().intValue();
						RealCardVo realCardVo = new RealCardVo();
						realCardVo.setIcCardId(customField.getIcCardId());
						realCardVo.setType(type);
						realCardVo.setHealthCardId(healthCardId);
						realCardVo.setDocuId(customField.getDocuId());
						realCardVo.setDocuType(customField.getDocuType());
						realCardVo.setNationality(customField.getNationality());
						// 调用实体卡绑定接口
						mainBusinessService.doA1021(realCardVo, appId);
				}
				resultVo.setHealthCardId(healthCardId);
				resultVo.setIcCardId(customField.getIcCardId());
				reason = "";
			} catch (BusException e) {
				throw e;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new BusException("提交申请失败");
			}
			resultVo.setStatus(status);
			resultVo.setReason(reason);
		}
		return resultVo;
	}
}
