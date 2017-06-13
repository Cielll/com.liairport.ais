package com.lzairport.ais.service.aodb;

import java.util.Date;

import javax.ejb.Remote;

import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.utils.SYS_VARS.GrpDate;
import com.lzairport.ais.utils.SYS_VARS.Quarter;


/**
 * ������ڼƻ���Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IScheduleFlightService extends
		IBaseFlightService<Integer, ScheduleFlight> {
	
	
	/**
	 * 
	 * @Description: ���ݸ����ĺ���ʵ�巵�ض�Ӧ�ĳ��ڼƻ�����ƻ�
	 * @param flight �����ĺ���ʵ��
	 * @return
	 */
	public ScheduleFlight findScheduleFlight(Flight flight);
	
	
	/**
	 * 
	 * @Description: ���ݸ����ĺ���ʵ�巵�ض�Ӧ�ĳ��ڼƻ�����ƻ�(���ڲ���)
	 * @param flight
	 * @return
	 */
	public ScheduleFlight findScheduleFlightByZP(Flight flight);
	
	/**
	 * 
	 * @Description: ���ݸ���������������ƻ�ִ�еĺ�������
	 * @param conditions ����������
	 * @return �ƻ�ִ�еĺ�������
	 */
	public Integer  CountFlightByCondition(QueryConditions conditions,Date startDate,Date endDate);
	
	/**
	 * 
	 * @Description: TODO ���ݸ���������������ƻ�һ���������λ��
	 * @param conditions ����������
	 * @param startDate ��ʼʱ��
	 * @param endDate   ����ʱ��
	 * @return һ�������ƽ����λ��
	 */
	public Integer getSeatByCondition(QueryConditions conditions,Date startDate,Date endDate);
	

	/**
	 * 
	 * @Description: ���ݸ�����GrpDate���ͣ�����ƻ�ִ�еĺ�������
	 * @param date 
	 * @param dateExpression
	 * @return �ƻ�ִ�еĺ�������
	 */
	public Integer CountFlightByGrpDate(GrpDate date,String dateExpression);
	
	
	

	/**
	 * 
	 * @Description: ���غ���ĺ��ߣ��������£�
	 * 1��MU5203 SHA-LZH ����Ϊ SHA-LZH-SHA
	 * 2��MU5204 LZH-SHA ����Ϊ SHA-LZH-SHA
	 * 3��Mu5379 TAO-CGO-LZH ����Ϊ LZH-CGO-TAO
	 * 4��MU5380 LZH-CGO-TAO ����Ϊ LZH-CGO-TAO
	 * 5��EU2201 CTU-LZH-SZX ����Ϊ CTU-LZH-SZX
	 * 6��EU2202 SZX-LZH-CTU ����Ϊ SZX-LZH-CTU
	 * @return
	 */
	public String getRouteCn(ScheduleFlight flight);
	
	
	/**
	 * 
	 * @Description: TODO ���ݸ������������Ƽ��Ⱥ���
	 * @param baseYear  �����Ƶļ��Ⱥ�������
	 * @param copyYear  ���Ƶļ��Ⱥ�������
	 * @param baseQuarter �����Ƶļ��Ⱥ���ĺ���
	 * @param copyQuarter ���Ƶļ��Ⱥ���ĺ���
	 * @throws Exception 
	 */
	public void replicateQuarter(String baseYear,String copyYear,Quarter baseQuarter,Quarter copyQuarter) throws Exception;
	
	
}
