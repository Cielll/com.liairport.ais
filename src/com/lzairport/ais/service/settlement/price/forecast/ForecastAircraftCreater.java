package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.Stateless;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;



/**
 * FileName      AircraftForecastCreater.java
 * @Description  TODO Ԥ��ɻ������շ�������
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��4��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��4��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class ForecastAircraftCreater extends ForecastSingleCreater {

	@Override
	protected Double getPrice(ForecastBase base) {
		Double price = 0.0;
		int    seat=   base.getSeat();
		if (seat <100){
			price = 100.0;
		}else if (seat < 200){
			price = 120.0;
		}else if (seat < 300){
			price = 240.0;
		}else {
			price = 480.0;
		}
		
		if (base.isBeforeRun()) {
			price *= 1.1;
		}
		if (base.isAfterRun()) {
			price *= 1.2;
		}
		return price;
	}

	@Override
	protected SettlementItem getSetItem(ForecastBase base) {
		return itemService.findByFieldSingle(SettlementItem.CODE, "AIRCRAFT-P");
	}

	@Override
	protected Double getNumber(ForecastBase base) {
		// TODO Auto-generated method stub
		return base.getCountFlt().doubleValue();
	}

}
