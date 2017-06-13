package com.lzairport.ais.service.settlement.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.settlement.ISettlementItemDao;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.settlement.ISettlementItemService;

/**
 * 
 * FileName      SettlementItemService.java
 * @Description  TODO ������Ŀ��ϸ��Serviceʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��7��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class SettlementItemService extends Service<Integer, SettlementItem> implements ISettlementItemService {

	@EJB
	public void setSettlementItemDao(ISettlementItemDao dao){
		setDao(dao);
	}

	
}
