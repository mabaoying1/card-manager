package com.healthpay.iface.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.healthpay.iface.annotation.BsoftCustomField;
import com.healthpay.iface.util.StringUtils;
import com.healthpay.iface.vo.QueryAreaVo;
import com.healthpay.iface.vo.ResResultAreaVo;
import com.healthpay.iface.vo.ResResultListVo;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.utils.AreaUtils;
/**    
* @ClassName: A3001 
* @Description: 查询地区的下级数据集合
* @author mabaoying
* @date 2019年7月30日
* @最后修改人：
* @最后修改时间：
*/
@Service
public class A3001 extends EHCAbstractHandlerImpl{
	@BsoftCustomField
	QueryAreaVo vo ;

	public Object doHandler(String appId,String appSecret,String realPath) throws Exception {
		String code = vo.getCode();
		if (StringUtils.isNull(code)) {
			code = "0"; // 默认查询中国的子集
		}
		Area area = AreaUtils.getArea(code);
		String parentId = "";
		if (area != null) {
			parentId = area.getId();
		}
		List<Area> list = AreaUtils.getAreaListByParentId(parentId);
		List<ResResultAreaVo> resList = Lists.newArrayList();
		for (Area bean : list) {
			ResResultAreaVo resVo = new ResResultAreaVo();
			resVo.setCode(bean.getCode());
			resVo.setName(bean.getName());
			resVo.setLevel(bean.getType());
			resList.add(resVo);
		}
		ResResultListVo retVo = new ResResultListVo();
		retVo.setMx(resList);
		return retVo;
	}
	
}
