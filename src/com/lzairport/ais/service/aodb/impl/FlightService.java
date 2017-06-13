package com.lzairport.ais.service.aodb.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;

import com.lzairport.ais.dao.impl.AisOrder;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.exception.FlightServiceException;
import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.models.aodb.BaseFlight;
import com.lzairport.ais.models.aodb.DynFlight;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.FlightDisPatch;
import com.lzairport.ais.models.aodb.FlightDisPatchItem;
import com.lzairport.ais.models.aodb.FlightLoad;
import com.lzairport.ais.models.aodb.PayingPassenger;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.models.aodb.ScheduleStopFlight;
import com.lzairport.ais.models.aodb.ShareFlight;
import com.lzairport.ais.service.DataFetchResponseInfo;
import com.lzairport.ais.service.aodb.IAirportService;
import com.lzairport.ais.service.aodb.IFlightDisPatchItemService;
import com.lzairport.ais.service.aodb.IFlightService;
import com.lzairport.ais.service.aodb.IFlightTaskService;
import com.lzairport.ais.service.aodb.IScheduleFlightService;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.OutIn;
import com.lzairport.ais.vo.FlightVO;
import com.lzairport.ais.vo.converter.IConverter;

/**
 * 
 * FileName      FlightService.java
 * @Description  ����Service�ӿڵĽӿڣ�ʵ�ֻ�������ĸ��ֻ�������
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: ����10:53:57 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015-9-21      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

public abstract  class FlightService<K,E extends Flight>  extends BaseFlightService<K, E> implements IFlightService<K,E> {
	
	@EJB(beanName="FlightConverter")
	private IConverter<Flight,FlightVO> flightConverter;
	
	@EJB
	private IFlightDisPatchItemService flightDisPatchItemService;
	
	@EJB
	private IScheduleFlightService scheduleFlightService;
	
	@EJB
	private IFlightTaskService flightTaskService;
	
	
	@EJB
	protected IAirportService airportService;
	
	
	protected static  String parkBegin = "�����λ";
	protected static  String parkEnd   = "��ɿ���";
	
	
	/**
	 * �������б��й������
	 * @param flights
	 */
	protected void clearLinkFlights(List<E> flights) {
		for(E flight:flights){
			flight.setLinkFlight(null);
			update(flight);
		}	
	}
	
	@Override
	public void createFlightDisPatchs(Flight flight) throws FlightServiceException {

		//��ȡʵ�ʵ�FLIGHTDISPATCHS������
		@SuppressWarnings("unchecked")
		Class<FlightDisPatch> dispatchClass = (Class<FlightDisPatch>) ObjectMethodUtil.getFieldGenericType(flight, Flight.FLIGHTDISPATCHS,0);
		
		//��ȡ��������е��Ȼ��ڣ���ӵ�������
		Set<FlightDisPatch> flightDisPatchs = new HashSet<FlightDisPatch>();
		List<FlightDisPatchItem> disPatchItems = flightDisPatchItemService.FindFlightDisPaths(flight);
		for (FlightDisPatchItem disPatchItem:disPatchItems){
			FlightDisPatch disPatch;
			try {
				disPatch = dispatchClass.newInstance();
				disPatch.setDisPatchItem(disPatchItem);
				flightDisPatchs.add(disPatch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new FlightServiceException("�����ڴ������񻷽�ʱ����");
			}
		}
		flight.setFlightDisPatchs(flightDisPatchs);
	}

	
	


	@Override
	@SuppressWarnings("unchecked")
	public void copyFlightDisPatchs(Flight flight,Set<? extends FlightDisPatch> ScrDispatchs) throws FlightServiceException {

	
		Class<FlightDisPatch> dispatchClass = (Class<FlightDisPatch>) ObjectMethodUtil.getFieldGenericType(flight, Flight.FLIGHTDISPATCHS, 0);
		
		//����������б�д�뺽��
		Set<FlightDisPatch> destDispatchs = new HashSet<FlightDisPatch>();
		for (FlightDisPatch scrDispatch:ScrDispatchs){
			try {
				FlightDisPatch destDispatch = dispatchClass.newInstance();
				ObjectMethodUtil.copybean(destDispatch, scrDispatch);
				destDispatch.setId(0);
				destDispatchs.add(destDispatch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new FlightServiceException("�����ڸ��Ʒ��񻷽�ʱ����");
			}
		}
		
		flight.setFlightDisPatchs(destDispatchs);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void copyFlightPayingPassengers(Flight flight,	Set<? extends PayingPassenger> scrPassengers) throws FlightServiceException {
		
		
	Class<PayingPassenger>  passengerClass = (Class<PayingPassenger>) ObjectMethodUtil.getFieldGenericType(flight, Flight.PAYINGPASSENGERS, 0);
		
		//�����ึ���ÿ��б�д�뺽��
		Set<PayingPassenger> destPassengers = new HashSet<PayingPassenger>();
		for (PayingPassenger scrPassenger:scrPassengers){
			try {
				PayingPassenger destPassenger = passengerClass.newInstance();
				ObjectMethodUtil.copybean(destPassenger, scrPassenger);
				destPassenger.setId(0);
				destPassengers.add(destPassenger);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new FlightServiceException("�����ڸ����ÿͻ���ʱ����");
			}
		}
		
		flight.setPayingPassengers(destPassengers);
	}
	
	


	@SuppressWarnings("unchecked")
	@Override
	public void copyFlightLoads(Flight flight,Set<? extends FlightLoad> scrLoads) throws FlightServiceException {
		// TODO Auto-generated method stub
		Class<FlightLoad>  loadClass = (Class<FlightLoad>) ObjectMethodUtil.getFieldGenericType(flight, Flight.LOADS, 0);
		
		//�������������д�뺽��
		Set<FlightLoad> destLoads = new HashSet<FlightLoad>();
		for (FlightLoad scrLoad:scrLoads){
			try {
				FlightLoad destLoad = loadClass.newInstance();
				ObjectMethodUtil.copybean(destLoad,scrLoad);
				destLoad.setId(0);
				destLoads.add(destLoad);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new FlightServiceException("�����ڸ�������ʱ����");
			}
		}
		
		flight.setLoads(destLoads);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public E createFlight(Flight flight) throws Exception {
	
		
		//���ҳ��ڼƻ�����ȫ�ƻ����������
		
		
		ScheduleFlight scheduleFlight = scheduleFlightService.findScheduleFlight(flight);
		
		if ((scheduleFlight == null)&&(flightTaskService.getSupplementTask().equals(flight.getTask()))){
			//����ǲ��ຽ�࣬����ģ������
			scheduleFlight = scheduleFlightService.findScheduleFlightByZP(flight);
		}
	

		if (scheduleFlight != null){
			
			//��ȫ�����������ֶ�
			if (flight.getDepAirport() == null){
				flight.setDepAirport(scheduleFlight.getDepAirport());
			}
			if (flight.getArrAirport() == null){
				flight.setArrAirport(scheduleFlight.getArrAirport());
			}
			if (flight.getTask() == null){
				flight.setTask(scheduleFlight.getTask());
			}
			
			if ((flight.getPlanLandInTime() == null)&&(scheduleFlight.getPlanLandInTime()!= null)){
				flight.setPlanLandInTime(DateTimeUtil.addMillisecond(flight.getExecDate(), 
						DateTimeUtil.HHMMToMillisecond(scheduleFlight.getPlanLandInTime())));
			}
			if ((flight.getPlanTakeOffTime() == null)&&(scheduleFlight.getPlanTakeOffTime() != null)){
				flight.setPlanTakeOffTime(DateTimeUtil.addMillisecond(flight.getExecDate(), 
								DateTimeUtil.HHMMToMillisecond(scheduleFlight.getPlanTakeOffTime())));
			}
			
			//��ȫ����
			flight.setStartAirport(scheduleFlight.getStartAirport());
			flight.setStartPlanTakeOffTime(scheduleFlight.getStartPlanTakeOffTime());
			flight.setTerminalAirport(scheduleFlight.getTerminalAirport());
			flight.setTerminalPlanLandInTime(scheduleFlight.getTerminalPlanLandInTime());
					
			//���ݳ��ں���ƻ� �������ྭͣվ
			Set<ScheduleStopFlight>  stopFlights = scheduleFlight.getStopFlights();
				createStopFlights(flight,stopFlights);
				
			}
		
			//���ú���Ϊ����
			flight.setNormal(true);
				
			//��ʼ�������oneToMany����
			flight.setLoads(new HashSet<FlightLoad>());
			flight.setPayingPassengers(new HashSet<PayingPassenger>());
			flight.setShareFlights(new HashSet<ShareFlight>());
			
			if ((flight.getDepAirport() != null) && flight.getDepAirport().equals(airportService.getLocalAirport())){
				if ((flight.getArrAirport() != null)&&flight.getArrAirport().equals(airportService.getLocalAirport())){
					//�����ɻ�������ػ������Ǳ���
					flight.setIsOutIn(OutIn.Srd);
				}else{
					//�����ɻ����Ǳ�������ػ�����Ϊ����
					flight.setIsOutIn(OutIn.Dep);
				}
				
			}else if (flight.getArrAirport() !=null &&flight.getArrAirport().equals(airportService.getLocalAirport())) {
				//�����ɻ������Ǳ�������ػ����Ǳ���
				flight.setIsOutIn(OutIn.Arr);
			}
		
			//���ú��չ�˾
			if ((flight.getAircraft() != null)&&(flight.getAircraft().getCarrier() != null)){
				flight.setAirlines(flight.getAircraft().getCarrier().getAirlines());
			}
			
			//���ú�������
			if ((flight.getDepAirport() != null)&&(flight.getArrAirport() != null)){
				if ((flight.getDepAirport().getAreaAttribute().getCnShortName().equals("����"))||
						(flight.getArrAirport().getAreaAttribute().getCnShortName().equals("����"))){
					flight.setAttribute(attributeService.getIntAttribute());
				}else if ((flight.getDepAirport().getAreaAttribute().getCnShortName().equals("����"))||
						(flight.getArrAirport().getAreaAttribute().getCnShortName().equals("����"))){
					flight.setAttribute(attributeService.getRegAttribute());
				}else{
					flight.setAttribute(attributeService.getDomAttribute());
				}
			}
			
			
			//����ƻ����ʱ��С��5�㣬˵�����������ǰһ��ĺ���
			if (flight.getPlanTakeOffTime() != null){
				String strPlnTk = DateTimeUtil.dateToHH_MM(flight.getPlanTakeOffTime());
				if (strPlnTk.compareTo("05:00") < 0){
					flight.setExecDate(DateTimeUtil.addToDay(flight.getExecDate(), -1));
					
				}
			}
			
			
			QueryConditions conditions = new QueryConditions();
			//���������ڣ�ɾ�������������
			conditions.setExpresstion(new Object[]{ Flight.FLIGHTNO,"=",flight.getFlightNO(),SYS_VARS.LinkSqlAnd,
						Flight.EXECDATE,"=",flight.getExecDate(),SYS_VARS.LinkSqlAnd,
						Flight.DEPAIRPORT,"=",flight.getDepAirport()
						});
			
			//�����ú�������Ӧ�ĵ��Ȼ���
			createFlightDisPatchs(flight);
			
			E sameFlight = findByConditionSingle(conditions);
			if ( sameFlight != null){
				remove(sameFlight);
			}
			
			if (flight.getIsOutIn() != null){
				flight =  add(flight);
			}
			return (E) flight;	
	}


	@Override
	public void flightDel(BaseFlight flight) throws FlightServiceException {

		List<E> linkFlights = findByFieldAll(DynFlight.LINKFLIGHT, flight.toString());
		for(E linkflight:linkFlights){
			linkflight.setLinkFlight(null);
			update(linkflight);
		}
		super.remove(flight);	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void linkFlights(Date startDate) throws FlightServiceException {
		

		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{Flight.EXECDATE,"<=",startDate});
		AisOrder order = new AisOrder();
		order.setName(Flight.PLANTAKEOFFTIME);
		order.setSortMode(SYS_VARS.AscSORT);
		conditions.setOrders(new AisOrder[]{order});
		List<E> flights = findByConditionAll(conditions);
		//��չ���
		clearLinkFlights(flights);
		for(E flight:flights){

			//��¼�����ӹ����������Ϣ
			//String flightInfo = flight.toString();
			//���º���״̬����Ҫ��ȡ��secondFlights�θ��µ�״̬
			flight = findByID((K) flight.getId());
			//�������û�й�������Ըú���ķɻ���ִ�е����б������඼���й���
			if ((flight != null) && (flight.getLinkFlight() == null)){
				if (flight.getDepAirport().getThreeCharCode().equals(SYS_VARS.LocalAirportThreeCode)){
					//����ǳ��ۺ��࣬˵��������������������һ����ؽ��ۺ���
					flight.setLinkFlight(SYS_VARS.LinkFlightDep);
				}else if (flight.getArrAirport().getThreeCharCode().equals(SYS_VARS.LocalAirportThreeCode)){
					//����ǽ��ۺ��࣬˵���������಻�Ǳ����ĺ��࣬�˺����ǽ��ۿ�ʼ
					flight.setLinkFlight(SYS_VARS.LinkFlightArr);
				}else{
					throw new FlightServiceException(flight+"���Ǳ����ĺ��࣬�޷����й���");				
				}
				update(flight);
				
				//����ִ�иú���ɻ������к�������
				conditions.setExpresstion(new Object[]{Flight.EXECDATE,"<=",startDate,SYS_VARS.LinkSqlAnd,
						Flight.AIRCRAFT,"=",flight.getAircraft()});
				List<E> secondFlights = findByConditionAll(conditions);
				//�����ִ�еķɻ���������������1�Ž��й���
				if (secondFlights.size() > 1){
					
					Airport previousArrAirport = flight.getArrAirport();
					Flight previousFlight = flight;
					//��ִ�иú���ɻ������к�����й���
					for (E secondFlight:secondFlights) {
						if(secondFlight.getPlanTakeOffTime() == null){
							//����ƻ����ʱ��Ϊ�㣬�޷����й���
							throw new FlightServiceException(secondFlight+"���ʱ��Ϊ�գ��޷����й���");		
						}else if (secondFlight.getDepAirport().equals(previousArrAirport) &&
							secondFlight.getPlanTakeOffTime().compareTo(previousFlight.getPlanTakeOffTime())==1){
							//��������������ɵ���ǰһ������أ���˵����������Ϊ����
							String linkFlight = previousFlight.toString();
							secondFlight.setLinkFlight(linkFlight);
							//����������һ������Ĺ������࣬�˺�����Ϊǰһ�������������
							previousArrAirport = secondFlight.getArrAirport();
							previousFlight = secondFlight;
							update(secondFlight);
						}else if (!flight.equals(secondFlight)) {
							//���������������ʼ�ĺ��࣬˵�������ʼ�ĺ��࣬���Ѱ�ҾͿ���
							throw new FlightServiceException("�޷��ҵ�"+secondFlight+"�Ĺ�������");		
						}
					}
					
				}
			}else if (flight == null) {
				throw new FlightServiceException("����ɡ�Ŀ�Ļ������������ڿ���Ϊ�գ��޷���λ�ú���");		
			}
		}                                                
	}



	@Override
	public E getLinkFlight(Flight flight) {
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{Flight.LINKFLIGHT,"=",flight.toString()});
		conditions.setFetchOneToMany("ALL");
		E linkFlight = findByConditionSingle(conditions);
		return linkFlight;
	}


	@Override
	public E getPreviousFlight(E flight) {
		// TODO Auto-generated method stub
	    String[] strs = flight.getLinkFlight().split("/");
	    if (strs.length == 3){
	    	String No   = strs[0];
	    	Date   date = DateTimeUtil.strToDate(strs[2]);
	    	OutIn outIn = OutIn.Arr;
	    	if (OutIn.Arr.equals(flight.getIsOutIn())){
	    		outIn = OutIn.Dep;
	    	}
			QueryConditions conditions = new QueryConditions();
			conditions.setExpresstion(new Object[]{Flight.FLIGHTNO,"=",No,"AND",Flight.ISOUTIN,"=",outIn,"AND",
					Flight.EXECDATE,"=",date});
			conditions.setFetchOneToMany("ALL");
			return findByConditionSingle(conditions);
	    }
		return null;
	}

	@Override
	public void convertFlights(Date startDate, Boolean cover, Boolean forcedImport) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public DataFetchResponseInfo fetchByCondition(QueryConditions conditions) throws Exception {
		List<E> flights = findByConditionAll(conditions);
		List<FlightVO> flightVOs = new ArrayList<FlightVO>();
		for (E flight:flights){
			flightVOs.add(flightConverter.getVOject(flight));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("records", flightVOs);
		result.put("totalCount", findCountByCondition(conditions));
		DataFetchResponseInfo responseInfo = new DataFetchResponseInfo();
		responseInfo.setTotalRows(findCountByCondition(conditions));
		responseInfo.setMatchingObjects(flightVOs);
		return responseInfo;
	}

	@Override
	public FlightVO toFlightVO(E flight) throws Exception {
		// TODO Auto-generated method stub
		return flightConverter.getVOject(flight);
	}

	
	@Override
	public boolean checkClearanceNormal(E flight) {
		
		/*
		 *  ֻ�г��۲������ĺ���Ž��з��������ļ��
		 */
		
		
		if (OutIn.Dep.equals(flight.getIsOutIn())&&!flight.isNormal()&&flight.getLinkFlight() != null){
			
			E previousFlight = getPreviousFlight(flight);
			/*
			 *  ����ҵ�ǰһ�εĺ��ಢ�����ʱ�䲻Ϊ�գ����з��������ļ��
			 */
			if (previousFlight != null &&  flight.getAircraft() != null && flight.getAircraft().getCraftType() != null){
				
				Date startTime  = null;
				Date endTime    = null; 
				
				for(FlightDisPatch disPatch:previousFlight.getFlightDisPatchs()){
					if (parkBegin.equals(disPatch.getDisPatchItem().getName())){
						startTime = disPatch.getEndRealTime();
					}
				}
				
				for(FlightDisPatch disPatch:flight.getFlightDisPatchs()){
					if (parkEnd.equals(disPatch.getDisPatchItem().getName())){
						endTime = disPatch.getEndRealTime();
					}
				}
					
				if (startTime != null && endTime != null){
					Long mSecond = DateTimeUtil.MillisecondBetween(startTime, endTime)/1000;
					Long cSecond = flight.getAircraft().getCraftType().getClearanceSecond();
					if (mSecond > cSecond){
						/*
						 *  ���ͣ����ʱ����ڸú���ִ�л���������ʱ��
						 */
						return false;
					}
				}
			}
		}
		
		return true;
	}


	



	

}
