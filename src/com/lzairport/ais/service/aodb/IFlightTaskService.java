package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.FlightTask;
import com.lzairport.ais.service.IService;

/**
 * �ɻ������Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IFlightTaskService extends IService<Integer, FlightTask> {
	
	/**
	 * 
	 * @Description: ���ز����ʵ����
	 * @return
	 */
	public FlightTask getSupplementTask();

}
