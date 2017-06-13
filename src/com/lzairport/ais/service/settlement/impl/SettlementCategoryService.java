package com.lzairport.ais.service.settlement.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.settlement.ISettlementCategoryDao;
import com.lzairport.ais.models.settlement.SettlementCategory;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.settlement.ISettlementCategoryService;


/**
 * 
 * FileName      SettlementCategoryService.java
 * @Description  TODO�������Serviceʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��1��12�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2017��1��12��      zhang    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class SettlementCategoryService extends Service<Integer, SettlementCategory>
		implements ISettlementCategoryService {

	@EJB
	public void setSettlementCategoryDao(ISettlementCategoryDao dao){
		setDao(dao);
	}
	
}
