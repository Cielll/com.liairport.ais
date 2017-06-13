package com.lzairport.ais.service.settlement.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.settlement.ISettlementTypeDao;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.settlement.ISettlementTypeService;



/**
 * 
 * FileName      SettlementTypeService.java
 * @Description  TODO ������Ŀ��Serviceʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��7��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class SettlementTypeService extends Service<Integer, SettlementType> implements ISettlementTypeService {

	@EJB
	public void setISettlementTypeDao(ISettlementTypeDao dao){
		setDao(dao);
	}
}
