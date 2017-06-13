package com.lzairport.ais.dao.settlement.forecast.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.settlement.forecast.IForecastBaseDao;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;

/**
 * 
 * 
 * FileName      ForecastBaseDao.java
 * @Description  TODO  Ԥ�����ݻ������ݵ�Dao
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��3��24��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��3��24��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */


@Stateless
public class ForecastBaseDao extends AodbDaoImpl<Integer, ForecastBase> implements IForecastBaseDao {

}
