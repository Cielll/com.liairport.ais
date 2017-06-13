package com.lzairport.ais.service.statistics.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.beanutils.BeanUtils;
import com.lzairport.ais.dao.aodb.IScheduleFlightDao;
import com.lzairport.ais.dao.impl.AisOrder;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.dao.statistics.IBaseScheduleDao;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.models.statistics.BaseSchedule;
import com.lzairport.ais.service.aodb.IAreaAttributeService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IBaseScheduleService;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.OutIn;
import com.lzairport.ais.utils.SYS_VARS.RouteType;
import com.lzairport.ais.utils.SYS_VARS.Week;

/**
 * 
 * FileName      BaseScheduleService.java
 * @Description  TODO ��׼���Ⱥ���Service�ӿڵ�ʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��9��10�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��9��10��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
 
@Stateless
public class BaseScheduleService extends Service<Integer, BaseSchedule> implements IBaseScheduleService {

	private static String[] compareFields ={BaseSchedule.CRAFTTYPE,BaseSchedule.ROUTEHX,BaseSchedule.BIGFLIGHTNO,BaseSchedule.ATTRIBUTE}; 
	
	@EJB
	private IScheduleFlightDao scheduleFlightDao;
	
	@EJB
	private IAreaAttributeService attributeService;
	
	private static	String strTransit = "-"+SYS_VARS.LocalAirportCn+"-";	
	
	private Date baseFirstDate;
	
	private Date baseLastDate;
	
   
	
	
	@EJB
	private void setBaseScheduleDao(IBaseScheduleDao dao){
		setDao(dao);
	}
	
	
	/**
	 * @Description: TODO ����һ�����Ⱥ��෵��һ����׼���Ⱥ���
	 * @param scheduleFlight  ���Ⱥ���ʵ��
	 * @throws Exception  
	 */
	private BaseSchedule createBaseSchedule(ScheduleFlight scheduleFlight,String fcstYear) throws Exception{
		BaseSchedule baseSchedule = new BaseSchedule();
		ObjectMethodUtil.copybean(baseSchedule, scheduleFlight);
		
		/*
		 *  ��fcstYear �滻date�е�Year
		 */
		String startMD = DateTimeUtil.dateToYYYYMMDD(scheduleFlight.getStartTime()).substring(4 );
		String endMD = DateTimeUtil.dateToYYYYMMDD(scheduleFlight.getEndTime()).substring(4);
		baseSchedule.setStartTime(DateTimeUtil.strToDate(fcstYear+startMD));
		baseSchedule.setEndTime(DateTimeUtil.strToDate(fcstYear+endMD));

		/*
		 *  startTime ������fcstYear-01-01�Ժ�
		 *  endTime   ������fcstYear-12-31��ǰ
		 */
		if (scheduleFlight.getStartTime().compareTo(baseFirstDate) < 0 ){
			baseSchedule.setStartTime(DateTimeUtil.strToDate(fcstYear+"-01-01"));
		}
		if(scheduleFlight.getEndTime().compareTo(baseLastDate) > 0){
			baseSchedule.setEndTime(DateTimeUtil.strToDate(fcstYear+"-12-31"));
		}
		
		return baseSchedule;
	}
	
	
	/**
	 * 
	 * @Description: TODO �жϺ����Ƿ���Խ��кϲ�
	 * @param merge    ԭ����    
	 * @param org  �ϲ�����
	 * @return
	 * @throws Exception
	 *  �ϲ�����:
	 *  LZH-PEK CA1859/1860 2016-01-01 2016-03-27  12346(��׼)
	 *  LZH-PEK CA1859/1860 2016-01-01 2016-03-27  57(�ϲ�)    Ƶ�ʺϲ�
	 *  LZH-PEK CA1859/1860 2016-03-28 2016-10-27  12346(�ϲ�) ʱ��ϲ� 
	 */
	private boolean isMerge(BaseSchedule merge,BaseSchedule org) throws Exception{
		
		/*
		 * ��������ֶ����Բ�һ�£�����false 
		 */
		for(String field:compareFields){
			Object orgObj = BeanUtils.getProperty(merge, field);
			Object compareObj = BeanUtils.getProperty(org, field);
			if (!orgObj.equals(compareObj)){
				return false;
			}
		}
		
		if (merge.getStartTime().equals(org.getStartTime())&&merge.getEndTime().equals(org.getEndTime())){
			/*
			 *  �����ʼ���ںͽ������ڶ�һ�� ˵��������ִ��Ƶ�ʲ�һ��
			 *  �û�׼�������Ƶ�ʺϲ�����
			 */
			return true;
		}else{
			/*
			 *  ���ִ�����ڲ�һ�£�����false;
			 */
			if (merge.getExecWeek().size() == org.getExecWeek().size()){
				for(Week week:org.getExecWeek()){
					if (!merge.getExecWeek().contains(week)){
						return false;
					}
				}
			}else{
				return false;
			}
			
			/*
			 * ����ϲ���StartTime-1 = ���ϲ���EndTime �����棬����Ϊ��
			 * ����ϲ���endTime+1 = ���ϲ���StartTime �����棬����Ϊ��
			 */
			Date orgStartDate = DateTimeUtil.addToDay(org.getStartTime(),-1);
			Date orgEndDate   = DateTimeUtil.addToDay(org.getEndTime(),+1);
			if (orgStartDate.equals(merge.getEndTime()) ||
					orgEndDate.equals(merge.getStartTime())){
				/* 
				 *  �ú������ʱ��ϲ�����
				 */
				return true;
			}else{
				return false;
			}
			
		}
		
		
		
		
		
	}
	
	
	/**
	 * @Description: TODO �ҵ��ܺͻ�׼���Ⱥ���ϲ��Ļ�׼����
	 * @param baseSchedule ��׼���Ⱥ���
	 * @return  ���Ժϲ��Ļ�׼���Ⱥ���
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private BaseSchedule findMergeBaseSchedule(BaseSchedule baseSchedule) throws Exception{
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{BaseSchedule.BIGFLIGHTNO,"=",baseSchedule.getBigFlightNO()});
		AisOrder order = new AisOrder();
		order.setName(BaseSchedule.STARTTIME);
		order.setSortMode(SYS_VARS.AscSORT);
		conditions.setOrders(new AisOrder[]{order});
		List<BaseSchedule> baseSchedules = dao.findByConditionAll(conditions);
		if (baseSchedules.size() > 0){
			BaseSchedule lastBaseSchedule = baseSchedules.get(baseSchedules.size()-1);
			
			if (isMerge(lastBaseSchedule,baseSchedule)){
				return lastBaseSchedule; 
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * @Description: TODO �ϲ���׼���Ⱥ���
	 * @param merge ���ϲ��Ļ�׼���Ⱥ���
	 * @param org  �ϲ��Ļ�׼���Ⱥ���
	 * @return �ϲ���Ļ�׼���Ⱥ���
	 */
	private void mergeBaseSchedule(BaseSchedule merge,BaseSchedule org){
		/*
		 *  �ϲ���ʼʱ���
		 *  
		 */
		if (merge.getEndTime().compareTo(org.getEndTime()) > 0){
			merge.setStartTime(org.getStartTime());
		}else{
			merge.setEndTime(org.getEndTime());			
		}
		
		/*
		 *  �ϲ�ִ��Ƶ��
		 */
		for(Week week:org.getExecWeek()){
			if (!merge.getExecWeek().contains(week)){
				merge.getExecWeek().add(week);
			}
		}
		
		
	}

	@Override
	public void createByScheduleYear(String baseYear,String fcstYear) throws Exception {
		

		/*�����׼���Ⱥ�������м�¼*/
		for(BaseSchedule baseSchedule:dao.getAll()){
			dao.delete(baseSchedule);
		}
		
		/*
		 *  �������з��������ļ��Ⱥ���ļ�¼���� (>=baseYear-01-01 and <=baseYear-12-31),��StartTime��������;
		 */
		baseFirstDate = DateTimeUtil.strToDate(baseYear+"-01-01");
		baseLastDate  = DateTimeUtil.strToDate(baseYear+"-12-31");
		
		
		
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{ScheduleFlight.STARTTIME,"<=",baseLastDate,
				"AND",ScheduleFlight.ENDTIME,">=",baseFirstDate,"AND",ScheduleFlight.ISOUTIN,"=",OutIn.Dep});
		AisOrder timeOrder = new AisOrder();
		timeOrder.setName(ScheduleFlight.STARTTIME);
		timeOrder.setSortMode(SYS_VARS.AscSORT);
		conditions.setOrders(new AisOrder[]{timeOrder});
		List<ScheduleFlight> scheduleFlights = scheduleFlightDao.findByConditionAll(conditions);
		
		/*
		 *  ���ݼ��Ⱥ����¼���ϣ��ϲ�����������׼���Ⱥ���
		 */
		for(ScheduleFlight scheduleFlight:scheduleFlights){
			BaseSchedule baseSchedule      = createBaseSchedule(scheduleFlight,fcstYear);
			BaseSchedule mergeBaseSchedule = findMergeBaseSchedule(baseSchedule);
			if (mergeBaseSchedule != null){
				mergeBaseSchedule(mergeBaseSchedule,baseSchedule);
				update(mergeBaseSchedule);
			}else{
				add(baseSchedule);
			}
		}
	}


	@Override
	public Integer CountFlightByCondition(QueryConditions conditions, Date startDate, Date endDate) {

		List<BaseSchedule> baseSchedules = findByConditionAll(conditions);
		Date date = startDate;
		int count = 0;
		while (date.compareTo(endDate)<=0){
			Week week = DateTimeUtil.getWeek(date);
			
			for (BaseSchedule BaseSchedule:baseSchedules){
				if (BaseSchedule.getExecWeek().contains(week)	&& BaseSchedule.getEndTime().compareTo(date)>=0 
						&& BaseSchedule.getStartTime().compareTo(date) <=0){
					count++;
				}
			}
			date = DateTimeUtil.addToDay(date, 1);
		}
		return count;
	}


	@Override
	public Integer getSeatByCondition(QueryConditions conditions, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		List<BaseSchedule> baseSchedules = findByConditionAll(conditions);
		Date date = startDate;
		int count = 0;
		int seatSum = 0;
		while (date.compareTo(endDate)<=0){
			Week week = DateTimeUtil.getWeek(date);
			
			for (BaseSchedule baseSchedule:baseSchedules){
				if (baseSchedule.getExecWeek().contains(week)&& baseSchedule.getEndTime().compareTo(date)>=0 
						&& baseSchedule.getStartTime().compareTo(date) <=0){
					seatSum += baseSchedule.getCraftType().getSeatNum();
					count++;
				}
			}
			date = DateTimeUtil.addToDay(date, 1);
		}
		if (count != 0){
			return seatSum/count;
		}else{
			return 0;
		}
	}


	@Override
	public RouteType getRouteType(BaseSchedule flight) {
		boolean bTransit = false;
		/* 
		 *   �жϺ����Ƿ�Ϊ��ת���� 
		 *   �ж�����"����-����-�ɶ�"�ַ����е�"-����-" 
		 */
		
		if (flight.getRouteHX() != null  && flight.getRouteHX().indexOf(strTransit) != -1){
			bTransit = true;
		}
		
		if(flight.getAttribute() != null){
			/*����������ǹ��ڲ�������ת���෵�ع�����ת*/
			if (attributeService.getDomAttribute().equals(flight.getAttribute())){
				if (bTransit){
					return RouteType.Dom_Tra;
				}else{
					return RouteType.Dom;
				}
			}if (attributeService.getIntAttribute().equals(flight.getAttribute())){
				if (bTransit){
					return RouteType.Int_Tra;
				}else{
					return RouteType.Int;
				}
			}
		}
		
		return null;
	}

	
}
