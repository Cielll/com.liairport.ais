package com.lzairport.ais.service.settlement.price;

import java.util.List;

import javax.ejb.EJB;

import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.ServiceLink;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.service.settlement.IServiceLinkService;

/**
 * 
 * FileName      DefaultMultipleCreater.java
 * @Description  TODO �շ���Ŀ�����߶���Ļ���
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��1��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2017��1��7��      zhang    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */


public abstract class DefaultLinkCreater extends DefaultCreater {
	
	@EJB
	protected IServiceLinkService serviceLinkService;
	
	protected SettlementItem item;


	
	/**
	 * 
	 * @Description: TODO ��ȡ�շ���Ŀ������
	 * @param flight ��Ҫ�շѵĺ���
	 * @param type   �շ�����
	 * @return ����
	 */
	protected abstract Double getNumber(SettlementType type,ServiceLink link);

	@Override
	public void create(HisFlight flight, SettlementType type) throws Exception {
		/*
		 * ��ѯ���񻷽�
		 */
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{ServiceLink.FLIGHTNO,"=",flight.getFlightNO(),
				"AND",ServiceLink.ISOUTIN,"=",flight.getIsOutIn(),"AND",ServiceLink.EXECDATE,"=",flight.getExecDate(),
				"AND",ServiceLink.SETTLEMENTTYPE,"=",type});
		conditions.setFetchOneToMany("ALL");
		List<ServiceLink> links = serviceLinkService.findByConditionAll(conditions);
		for(ServiceLink link:links){
			item = link.getSettlementItem();
			Double number = getNumber(type,link);
			Double price = getPrice(flight);
			createSettlement(flight, type, item, number, price);
		}
		
	}
	
	

}
