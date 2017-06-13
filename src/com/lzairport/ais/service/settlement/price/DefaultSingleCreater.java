package com.lzairport.ais.service.settlement.price;

import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;

/**
 * 
 * FileName      DefaultSingleCreater.java
 * @Description  TODO �շ���Ŀ�����ߵ����Ļ���
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��1��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2017��1��7��      zhang    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */


public abstract class DefaultSingleCreater extends DefaultCreater {


	
	/**
	 * @Description: TODO ��ȡ�շ���Ŀ����ϸ��
	 * @param flight ��Ҫ�շѵĺ���
	 * @return �շ���Ŀ����ϸ��
	 */
	protected abstract SettlementItem getSetItem(HisFlight flight);
	
	/**
	 * @Description: TODO ��ȡ�շ���Ŀ������
	 * @param flight ��Ҫ�շѵĺ���
	 * @param type   �շ�����
	 * @return ����
	 */
	protected abstract Double getNumber(HisFlight flight,SettlementType type);
	
	@Override
	public void create(HisFlight flight, SettlementType type) throws Exception {
		/*
		 * ��ȡ����ĸ����������
		 */
		SettlementItem item = getSetItem(flight);
		Double number =getNumber(flight, type);
		Double price = getPrice(flight);
		createSettlement(flight, type, item, number, price);
	}




}
