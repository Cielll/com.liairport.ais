package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.Stateless;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;

/**
 * FileName      AprcForecastCreater.java
 * @Description  TODO Ԥ�����ָ�ӷ�������
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��4��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��4��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class ForecastAprcCreater extends ForecastSingleCreater {

	@Override
	protected Double getPrice(ForecastBase base) {
		Double price = 0.0;
		int    weight= base.getWeight();
		if (weight<=25){
			price = weight*2.2;
		}else if (weight <=50){
			price = weight*2.88;
		}else if (weight <= 100){
			price = weight*3.25;
		}else if (weight <200){
			price = weight*4.55;
		}else {
			price = weight*5.60;
		}
		return price;
	}

	@Override
	protected SettlementItem getSetItem(ForecastBase base) {
		return itemService.findByFieldSingle(SettlementItem.CODE, "APRC");
	}

	@Override
	protected Double getNumber(ForecastBase base) {
		return base.getCountFlt().doubleValue()*2;
	}

}
