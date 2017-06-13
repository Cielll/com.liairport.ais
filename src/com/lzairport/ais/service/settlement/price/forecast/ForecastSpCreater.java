package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.Stateless;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;

/**
 * FileName      ForecastPaCheckCreater.java
 * @Description  TODO Ԥ���ÿ��������շ���������
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��4��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��4��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class ForecastSpCreater extends ForecastSingleCreater {

	@Override
	protected Double getPrice(ForecastBase base) {
		Double price = 0.0;
		if(areaService.getDomAttribute().equals(base.getAttribute())){
			/*
			 *  ���ں���
			 */				
			price = 7.0;
		}else{
			/*
			 * ���ʺ���
			 */
			price = 12.0;
		}		
		return price;
	}

	@Override
	protected SettlementItem getSetItem(ForecastBase base) {
		
		if (areaService.getDomAttribute().equals(base.getAttribute())){
			return itemService.findByFieldSingle(SettlementItem.CODE, "SP-D");
		}else {
			return itemService.findByFieldSingle(SettlementItem.CODE, "SP-I");
		}
	}

	@Override
	protected Double getNumber(ForecastBase base) {
		return base.getPax().doubleValue();
	}

}
