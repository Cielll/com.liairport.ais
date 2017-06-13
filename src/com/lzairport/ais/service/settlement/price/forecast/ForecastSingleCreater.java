package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.EJB;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;
import com.lzairport.ais.service.aodb.IAreaAttributeService;
import com.lzairport.ais.service.settlement.ISettlementItemService;


/**
 * FileName      SingleForecastCreater.java
 * @Description  TODO Ԥ���շ���Ŀ�����ߵ����Ļ���
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��4��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��4��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

public abstract class ForecastSingleCreater extends DefaultForecastCreater implements IForecastSettlementCreater {
	
	@EJB
	protected ISettlementItemService itemService;

	@EJB
	protected IAreaAttributeService areaService;
	
	/**
	 * 
	 * @Description: TODO ��ȡԤ���շ���Ŀ�ĵ���
	 * @param Base  ��Ҫ�շѵĺ���
	 * @return �շ���Ŀ�ĵ���
	 */
	protected abstract Double getPrice(ForecastBase base);
	
	/**
	 * @Description: TODO ��ȡ�շ���Ŀ����ϸ��
	 * @param flight ��Ҫ�շѵĺ���
	 * @return �շ���Ŀ����ϸ��
	 */
	protected abstract SettlementItem getSetItem(ForecastBase base);
	
	/**
	 * @Description: TODO ��ȡ�շ���Ŀ������
	 * @param flight ��Ҫ�շѵĺ���
	 * @param type   �շ�����
	 * @return ����
	 */
	protected abstract Double getNumber(ForecastBase base);

	@Override
	public void create(ForecastBase base, SettlementType type) throws Exception {
		
		SettlementItem item = getSetItem(base);
		Double number =getNumber(base);
		Double price = getPrice(base);
		createForecastSettlement(base, type, item, number, price);
	}


}
