package com.lzairport.ais.service.settlement;

import javax.ejb.Remote;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.service.IService;


/**
 * 
 * FileName      ISettlementItemService.java
 * @Description  TODO������Ŀ��ϸ��Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��7��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
@Remote
public interface ISettlementItemService extends IService<Integer, SettlementItem> {
	
	

}
