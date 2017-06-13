package com.lzairport.ais.service.settlement;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.Settlement;
import com.lzairport.ais.service.IService;

/**
 * 
 * FileName      ISettlementService.java
 * @Description  TODO���������Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��7��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Remote
public interface ISettlementService extends IService<Integer, Settlement> {
	/**
	 * 
	 * @Description: TODO ����һ�����������
	 * @param flight
	 * @throws Exception 
	 */
	public void createSettlement(HisFlight flight) throws Exception;
	

}
