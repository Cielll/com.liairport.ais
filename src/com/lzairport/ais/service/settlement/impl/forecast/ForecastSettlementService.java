package com.lzairport.ais.service.settlement.impl.forecast;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.settlement.forecast.IForecastSettlementDao;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;
import com.lzairport.ais.models.settlement.forecast.ForecastSettlement;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.settlement.ISettlementTypeService;
import com.lzairport.ais.service.settlement.forecast.IForecastSettlementService;
import com.lzairport.ais.service.settlement.price.forecast.ForecastCreaterFactory;
import com.lzairport.ais.service.settlement.price.forecast.ForecastLinkCreater;
import com.lzairport.ais.service.settlement.price.forecast.IForecastSettlementCreater;

/**
 * 
 * 
 * FileName      ForecastSettlementService.java
 * @Description  TODO  Ԥ����������Service
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��3��24��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��3��24��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */


@Stateless
public class ForecastSettlementService extends Service<Integer, ForecastSettlement>
		implements IForecastSettlementService {

	@EJB
	private ISettlementTypeService settlementTypeService;
	
	@EJB
	private ForecastCreaterFactory createrFactory;
	
	@EJB
	private ForecastLinkCreater linkCreater;
	
	@EJB
	public void setForecastSettlementDao(IForecastSettlementDao dao){
		setDao(dao);
	}

	@Override
	public void createSettlement(ForecastBase base) throws Exception {
		for (SettlementType type:settlementTypeService.getAll()){
			IForecastSettlementCreater creater =  createrFactory.getCreater(type);
			if (creater != null){
				creater.create(base, type);
			}
		}
		linkCreater.create(base);
	}	

}
