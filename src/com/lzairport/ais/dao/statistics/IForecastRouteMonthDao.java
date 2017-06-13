package com.lzairport.ais.dao.statistics;

import javax.ejb.Local;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.statistics.ForecastRouteMonth;

/**
 * 
 * FileName      IForecastRouteMonthDao.java
 * @Description  TODO Ԥ�⺽���µ�Dao�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��7��1�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��7��1��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Local
public interface IForecastRouteMonthDao extends IDao<Integer, ForecastRouteMonth> {

}
