package com.lzairport.ais.service.settlement;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.ServiceLink;
import com.lzairport.ais.service.IService;

/**
 * 
 * FileName      IServiceLinkService.java
 * @Description  TODO �����շѵ�Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��7��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Remote
public interface IServiceLinkService extends IService<Integer, ServiceLink> {
	
	/**
	 * @Description: TODO���� һ�������Ĭ�Ϸ������շ�
	 * @param hisFlight
	 * @throws Exception 
	 */
	public void createFlightDefaultLinks(HisFlight flight) throws Exception;

}
