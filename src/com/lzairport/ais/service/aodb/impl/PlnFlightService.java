package com.lzairport.ais.service.aodb.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IPlnFlightDao;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.DynFlight;
import com.lzairport.ais.models.aodb.PlnFlight;
import com.lzairport.ais.models.aodb.PlnFlightDisPatch;
import com.lzairport.ais.models.aodb.PlnFlightLoad;
import com.lzairport.ais.models.aodb.PlnStopFlight;
import com.lzairport.ais.service.aodb.IDynFlightService;
import com.lzairport.ais.service.aodb.IPlnFlightService;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * 
 * FileName      PlnFlightService.java
 * @Description  ����ƻ���Service�ӿڵ�ʵ���࣬��ɺ���ƻ��Ĳ���
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015-9-22 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015-9-22      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
@Stateless
 
public class PlnFlightService extends FlightService<Integer,PlnFlight> implements IPlnFlightService {

	
	@EJB
	private  IDynFlightService  dynFlightService ;
	
	
	@EJB
	public void setPlnFlightDao(IPlnFlightDao plnFlightDao){
		setDao(plnFlightDao);
	}
	
	/**
	 * ��һ������ƻ�תΪ��̬
	 * @param plnFlight �ƻ�����ʵ��
	 * @param Cover  �Ƿ񸲸�
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private void PlnToDyn(PlnFlight plnFlight,Boolean Cover) throws Exception {
		
		//���Ҷ�̬���Ƿ�����ͬ�ĺ���ʵ��
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{DynFlight.EXECDATE,"=",plnFlight.getExecDate(),SYS_VARS.LinkSqlAnd,
				DynFlight.DEPAIRPORT,"=",plnFlight.getDepAirport(),SYS_VARS.LinkSqlAnd,
				DynFlight.FLIGHTNO,"=",plnFlight.getFlightNO()
				});

		DynFlight dynFlight = dynFlightService.findByConditionSingle(conditions);
		
		//����º����־
		Boolean addNewFlight = false;
		
		if (dynFlight == null){
			dynFlight = new DynFlight();
			addNewFlight = true;
		}
		
		//�������ӵ��º�����߸��Ǳ�־ΪTrue
		if ((addNewFlight)||(Cover)){
			
			//���������Ϣ����
			ObjectMethodUtil.copybean(dynFlight, plnFlight);
			dynFlight.setId(0);
			
			//������Ϣ����
			Set<PlnFlightLoad> plnFlightLoads = plnFlight.getLoads();
			copyFlightLoads(dynFlight, plnFlightLoads);
			
			//���񻷽ڸ���
			Set<PlnFlightDisPatch> plnFlightDisPatchs = plnFlight.getFlightDisPatchs();
			copyFlightDisPatchs(dynFlight, plnFlightDisPatchs);
			
			//��ͣ���ഴ��
			Set<PlnStopFlight> plnStopFlights = plnFlight.getStopFlights();
			createStopFlights(dynFlight, plnStopFlights);
			
			
			//��������ͷ���FPL�Ĵ�������
			dynFlight.setDelayTimes(SYS_VARS.FlightDelayTimes);
			dynFlight.setClearanceTimes(SYS_VARS.FlightDelayTimes);
			dynFlight.setFPLTimes(SYS_VARS.FlightFPLTimes);
			
			dynFlight =  dynFlightService.add(dynFlight);
		}
		remove(plnFlight);
		
	}



	/**
	 * ������ƻ��ӿ�ʼ����ת��Ϊ��̬
	 * @throws Exception 
	 */
	@Override
	public void convertFlights(Date startDate,Boolean cover,Boolean forcedImport ) throws Exception {
		
		
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{PlnFlight.EXECDATE,"<=",startDate});
		List<PlnFlight> plnFlights = findByConditionAll(conditions);
		
		for (PlnFlight plnFlight:plnFlights){
			PlnToDyn(plnFlight,cover);
		}
	}


	

}
