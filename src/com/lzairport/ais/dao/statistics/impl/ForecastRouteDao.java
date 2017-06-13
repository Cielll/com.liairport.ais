package com.lzairport.ais.dao.statistics.impl;

import javax.ejb.Stateless;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.statistics.IForecastRouteDao;
import com.lzairport.ais.models.statistics.ForecastRoute;

/**
 * 
 * FileName      ForecastRouteDao.java
 * @Description  TODO Ԥ�⺽�ߵ�ʵ��Dao��ʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��7��1�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��7��1��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class ForecastRouteDao extends AodbDaoImpl<Integer, ForecastRoute> implements IForecastRouteDao {


}
