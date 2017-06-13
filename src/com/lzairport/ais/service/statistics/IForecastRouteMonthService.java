package com.lzairport.ais.service.statistics;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.ForecastRouteMonth;
import com.lzairport.ais.service.IService;

/**
 * 
 * FileName      IForecastRouteMonthService.java
 * @Description  TODO Ԥ�⺽���µ�Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��7��1�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��7��1��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Remote
public interface IForecastRouteMonthService extends IService<Integer, ForecastRouteMonth> {
	
	

}
