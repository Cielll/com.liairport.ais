package com.lzairport.ais.service.settlement.price;

import javax.ejb.Local;

import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.SettlementType;

/**
 * 
 * FileName      ISettlementCreater.java
 * @Description  TODO�������ĳ���շ���Ŀ�ĵ��������߽ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��11�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��11��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Local
public interface ISettlementCreater {
	/**
	 * 
	 * @Description: TODO ���ݺ�������ĳ��������շ���ĵ��ۣ�С���㱣��2λ
	 * @param flight
	 * @throws Exception 
	 */
	public void create(HisFlight flight,SettlementType type) throws Exception;

}
