package com.lzairport.ais.dao.statistics.forecast;

import javax.ejb.Local;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.statistics.forecast.ForecastMonthFlight;

/**
 * FileName      IForecastMonthFlightDao.java
 * @Description  TODO Ԥ�⺽���µ�Dao�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��18��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��18��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Local
public interface IForecastMonthFlightDao extends IDao<Integer, ForecastMonthFlight> {

}
