package com.lzairport.ais.service.settlement.price.forecast;

import java.util.Set;

import javax.ejb.Stateless;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;
import com.lzairport.ais.models.settlement.forecast.ForecastLink;

/**
 * FileName      LinkForecastCreater.java
 * @Description  TODO Ԥ����񻷽��շ���Ŀ
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��4��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��4��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public  class ForecastLinkCreater extends DefaultForecastCreater {

	public void create(ForecastBase base) throws Exception {
		
		Set<ForecastLink> links = base.getLinks();
		for(ForecastLink link:links){
			SettlementItem item = link.getItem();
			Double number = base.getCountFlt() * link.getProportion()/100.0;
			Double price = item.getPrice();
			createForecastSettlement(base, item.getSettlementType(), item, number, price);
		}
	}

}
