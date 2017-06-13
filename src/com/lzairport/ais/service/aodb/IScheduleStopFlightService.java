package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;
import com.lzairport.ais.models.aodb.ScheduleStopFlight;
import com.lzairport.ais.service.IService;


/**
 * ��ͣ���ڼƻ���Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */


@Remote
public interface IScheduleStopFlightService extends
		IService<Integer, ScheduleStopFlight> {

}
