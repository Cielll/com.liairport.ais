package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IScheduleStopFlightDao;
import com.lzairport.ais.models.aodb.ScheduleStopFlight;
import com.lzairport.ais.service.aodb.IScheduleStopFlightService;
import com.lzairport.ais.service.impl.Service;



/**
 * ��ͣ���ڼƻ���Service�ӿڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 04/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class ScheduleStopFlightService extends
		Service<Integer, ScheduleStopFlight> implements
		IScheduleStopFlightService {

	@EJB
	public void setScheduleStopFlightDao(IScheduleStopFlightDao scheduleStopFlightDao){
		setDao(scheduleStopFlightDao);
	}

}
