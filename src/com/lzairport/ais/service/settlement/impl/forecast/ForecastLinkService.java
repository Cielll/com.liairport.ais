package com.lzairport.ais.service.settlement.impl.forecast;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.settlement.forecast.IForecastLinkDao;
import com.lzairport.ais.models.settlement.forecast.ForecastLink;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.settlement.forecast.IForecastLinkService;
/**
 * 
 * 
 * FileName      ForecastLinkService.java
 * @Description  TODO ��Ԥ����񻷽ڵ�Service
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��3��24��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��3��24��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class ForecastLinkService extends Service<Integer, ForecastLink> implements IForecastLinkService {

	@EJB
	public void setForecastLinkDao(IForecastLinkDao dao){
		setDao(dao);
	}

}
