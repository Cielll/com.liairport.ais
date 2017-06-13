package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.service.IService;

/**
 * ����Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IAirportService extends IService<Integer, Airport> {
	
	/**
	 * ȡ�ù�����ת����
	 * @return
	 */
	public Airport getTransitDomAirport();
	
	public Airport getLocalAirport();

}
