package com.lzairport.ais.webservice.aodb;

import javax.ejb.Remote;

/**
 * 
 * FileName      IFlightWebService.java
 * @Description  TODO ���ද̬��WebService�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��10��24�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��10��24��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Remote
public interface IFlightWebService {
	
	public String findDynByCondition(String condistion) throws Exception;

}
