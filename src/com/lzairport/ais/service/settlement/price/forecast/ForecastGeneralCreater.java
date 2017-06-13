package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.Stateless;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;


/**
 * 
 * FileName      GeneralForecastCreater.java
 * @Description  TODO Ԥ��ɻ�һ�������շ�������
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��4��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��4��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class ForecastGeneralCreater extends ForecastSingleCreater {

	@Override
	protected Double getPrice(ForecastBase base) {
		// TODO Auto-generated method stub
		Double price = 0.0;
		int    seat=   base.getSeat();
		if (seat < 100){
			price = 100.0;
		}else if (seat < 200){
			price = 150.0;
		}else if (seat < 300){
			price = 300.0;
		}else {
			price = 600.0;
		}

		return price;
	}

	@Override
	protected SettlementItem getSetItem(ForecastBase base) {
		return itemService.findByFieldSingle(SettlementItem.CODE, "GENERAL");
	}

	@Override
	protected Double getNumber(ForecastBase base) {
		return base.getCountFlt().doubleValue();
	}

}
