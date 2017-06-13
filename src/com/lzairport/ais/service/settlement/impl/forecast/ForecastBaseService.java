/**
 * 
 */
package com.lzairport.ais.service.settlement.impl.forecast;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.settlement.forecast.IForecastBaseDao;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.settlement.forecast.IForecastBaseService;

/**
 * 
 * FileName      ForecastBaseService.java
 * @Description  TODO Ԥ�����ݻ������ݵ�Service
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��3��24��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��3��24��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class ForecastBaseService extends Service<Integer, ForecastBase> implements IForecastBaseService {
	
	@EJB
	public void setForecastBaseDao(IForecastBaseDao dao){
		setDao(dao);
	}
	
}
