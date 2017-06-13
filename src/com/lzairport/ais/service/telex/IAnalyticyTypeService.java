package com.lzairport.ais.service.telex;

import javax.ejb.Remote;

import com.lzairport.ais.models.telex.AnalyticyType;
import com.lzairport.ais.service.IService;


/**
 * �籨����״̬Service�ӿڣ����Է��ض���õĵ籨����״̬
 * @author ZhangYu
 * @version 0.9a 13/12/14
 * @since JDK 1.6
 *
 */

@Remote
public interface IAnalyticyTypeService extends IService<Integer,AnalyticyType> {
	
	
	/**
	 * 
	 * @return �籨δ����״̬
	 */
	public AnalyticyType getUnAnalyticedTelexType();
	
	/**
	 * 
	 * @return �籨�������״̬
	 */
	public AnalyticyType getAnalyticedTelexType();
	
	/**
	 * 
	 * @return �籨��������״̬
	 */
	public AnalyticyType getErrAnalyticyType();
	
	
}
