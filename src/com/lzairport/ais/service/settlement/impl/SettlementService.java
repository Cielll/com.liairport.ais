package com.lzairport.ais.service.settlement.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.settlement.ISettlementDao;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.Settlement;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.service.aodb.IFlightStateService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.settlement.ISettlementService;
import com.lzairport.ais.service.settlement.ISettlementTypeService;
import com.lzairport.ais.service.settlement.price.ISettlementCreater;
import com.lzairport.ais.service.settlement.price.CreaterFactroy;


/**
 * 
 * FileName      SettlementService.java
 * @Description  TODO ���������Serviceʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��7�� 
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2016��11��7��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class SettlementService extends Service<Integer, Settlement> implements ISettlementService {
	
	@EJB
	private ISettlementTypeService settlementTypeService;
	
	@EJB
	private CreaterFactroy createrFactroy;

	@EJB
	private IFlightStateService stateService;
	@EJB
	public void setSettlementDao(ISettlementDao dao){
		setDao(dao);
	}
	
	@Override
	public void createSettlement(HisFlight flight) throws Exception {
		for (SettlementType type:settlementTypeService.getAll()){
			ISettlementCreater creater = createrFactroy.getCreater(type);
			if (creater != null && !stateService.getCnlState().equals(flight.getState())){
				creater.create(flight, type);
			}
		}
	}

}
