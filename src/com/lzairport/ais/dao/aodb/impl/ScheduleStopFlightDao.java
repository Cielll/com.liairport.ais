package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IScheduleStopFlightDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.ScheduleStopFlight;

/**
 * ��ͣ���ڼƻ���Dao�ӿڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */

@Stateless
public class ScheduleStopFlightDao extends
		AodbDaoImpl<Integer, ScheduleStopFlight> implements
		IScheduleStopFlightDao {


}
