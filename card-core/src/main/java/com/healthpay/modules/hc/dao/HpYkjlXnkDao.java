package com.healthpay.modules.hc.dao;

import com.healthpay.common.persistence.CrudDao;
import com.healthpay.common.persistence.annotation.MyBatisDao;
import com.healthpay.modules.hc.entity.HpYkjlXnk;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author mabaoying
 * @ClassName: HpYkjlXnkDao
 * @Description: 虚拟卡用卡记录dao层
 * @date: 2019/7/31
 * @最后修改人:
 * @最后修改时间:
 */
@MyBatisDao
public interface HpYkjlXnkDao  extends CrudDao<HpYkjlXnk> {

    List<Map<String,Object>> getHpYkjlXnlByMerIdAndTime(@Param("nowDate") String nowDate,@Param("merId") String merId);

    int addHpYkjlXnk(HpYkjlXnk hpYkjlXnk);

    int updateHpYkjlXnlList(@Param("ykjlXnkList")List<Map<String,Object>> ykjlXnkList,@Param("date") Date date);
}
