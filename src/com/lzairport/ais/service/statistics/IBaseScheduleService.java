package com.lzairport.ais.service.statistics;

import java.util.Date;
import javax.ejb.Remote;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.statistics.BaseSchedule;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.utils.SYS_VARS.RouteType;


/**
 * 
 * FileName      IBaseScheduleService.java
 * @Description  TODO ��׼���Ⱥ����Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��9��10�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��9��10��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Remote
public interface IBaseScheduleService extends IService<Integer, BaseSchedule> {
	
	
	/**
	 * 
	 * @Description: TODO ����yearָ����ݵļ��Ⱥ���ƻ�������׼�ļ��Ⱥ���ƻ�
	 * @param year ָ�������
	 * @throws Exception 
	 */
	public void createByScheduleYear(String baseYear,String fcstYear) throws Exception;
	
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
	 * @Description: ���ݸ���������������ƻ�ִ�еĺ�������
	 * @param conditions ����������
	 * @return �ƻ�ִ�еĺ�������
	 */
	public Integer  CountFlightByCondition(QueryConditions conditions,Date startDate,Date endDate);
	
	/**
	 * 
	 * @Description: TODO ��ȡ���ߵ�����
	 */
	public RouteType getRouteType(BaseSchedule flight);

}
