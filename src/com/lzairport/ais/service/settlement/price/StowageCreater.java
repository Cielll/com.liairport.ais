package com.lzairport.ais.service.settlement.price;

import javax.ejb.Stateless;

import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;


/**
 * 
 * FileName      CommunicationCreater.java
 * @Description  TODO ͨ�����ط�����շ�������
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��15�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��15��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class StowageCreater extends DefaultSingleCreater {

	@Override
	protected Double getPrice(HisFlight flight) {
		Double price = 0.0;
		int    payLoad= 0;
		if (flight.getAircraft() != null){
			payLoad = flight.getAircraft().getSettlementLoad();
		}
		if (payLoad <= 10){
			price = 36.0;
		}else{
			price = 40.0;
		}
		return price*payLoad;
	}

	@Override
	protected SettlementItem getSetItem(HisFlight flight) {
		// TODO Auto-generated method stub
		return itemService.findByFieldSingle(SettlementItem.CODE, "STOWAGE-SUM");
	}

	@Override
	protected Double getNumber(HisFlight flight, SettlementType type) {
		// TODO Auto-generated method stub
		return type.getUnit();
	}

}
