package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.Stateless;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;

/**
 * FileName      ForecastPaCreater.java
 * @Description  TODO Ԥ���ÿͷ����������
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��4��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��4��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class ForecastPaCreater extends ForecastSingleCreater {

	@Override
	protected Double getPrice(ForecastBase base) {

		Double price = 0.0;
		if(areaService.getDomAttribute().equals(base.getAttribute())){
		/*
		 *  ���ں���
		 */				
			price = 42.0;
		}else{
		/*
		 * ���ʺ���
		 */
			price = 70.0;
		}
		return price;
	}

	@Override
	protected SettlementItem getSetItem(ForecastBase base) {
		if (areaService.getDomAttribute().equals(base.getAttribute())){
			return itemService.findByFieldSingle(SettlementItem.CODE, "PA-D");
		}else {
			return itemService.findByFieldSingle(SettlementItem.CODE, "PA-I");
		}
	}

	@Override
	protected Double getNumber(ForecastBase base) {
		return base.getPax().doubleValue();
	}

	
}
