package com.lzairport.ais.service.settlement.impl;

import java.lang.reflect.Method;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.settlement.IServiceLinkDao;
import com.lzairport.ais.models.aodb.Aircraft;
import com.lzairport.ais.models.aodb.FlightDisPatch;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.ServiceLink;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.service.aodb.IFlightStateService;
import com.lzairport.ais.service.aodb.IHisFlightService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.settlement.IServiceLinkService;
import com.lzairport.ais.service.settlement.ISettlementItemService;
import com.lzairport.ais.utils.AisBeanUtils;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.SYS_VARS.OutIn;

/**
 * 
 * FileName      ServiceLinkService.java
 * @Description  TODO�����շ�Serviceʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��7��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class ServiceLinkService extends Service<Integer, ServiceLink> implements IServiceLinkService {
	
	protected static  String parkBegin = "�����λ";
	protected static  String parkEnd   = "��ɿ���";

	private static String[] methods = {"isRoutine","isPermit","isTractor","isGuide","isFerryP","isLadder"
			,"isBridge","isDisp","isAspd"};
	private static String[] codes   = {"ROUTINE-P","PERMIT-P","TRACTOR","GUIDE","FERRY-P","LADDER"
			,"BRIDGE","DISP","ASPD"};
	
	@EJB
	private ISettlementItemService itemService;
	
	@EJB
	private IHisFlightService flightService;
	
	@EJB
	private IFlightStateService stateService;
	
	/**
	 *  ���ݳ������ÿ����� 
	 */
	private static int LadderMaxPax = 130;

	@EJB
	public void setServiceLink(IServiceLinkDao dao){
		setDao(dao);
	}
	
	
	private void createLink(Aircraft aircraft,HisFlight flight,String method,String code) throws Exception{
		
		Method m = Aircraft.class.getDeclaredMethod(method);
		if (m.invoke(aircraft).equals(true)){
			ServiceLink link  = new ServiceLink();
			SettlementItem item = itemService.findByFieldSingle(SettlementItem.CODE, code);	
			int times =0;
			Date startTime = null;
			Date endTime = null;
			AisBeanUtils.copyProperties(link, flight);
			link.setSettlementItem(item);
			link.setSettlementType(item.getSettlementType());
			switch (code) {
			case "ROUTINE-P":
				times =1;
				break;
			case "PERMIT-P":
				times =1;
				break;
			
			case "TRACTOR":
				/*
			 	 *  ǣ��������ڳ��ۺ���
				 */
				if (OutIn.Dep.equals(flight.getIsOutIn())){
					times =1;
				}else{
					times =0;
				}
				break;
			case "ASPD":	
			case "DISP":				
			case "GUIDE":
				/*
				 *  ��������ǩ�ɡ��ϼ����� ����ڽ��ۺ���
				 */
				if (OutIn.Arr.equals(flight.getIsOutIn())){
					times = 1;
				}else{
					times = 0;
				}
				break;
			case "FERRY-P":
				int pax = flight.getLoc_Adult()+flight.getTra_Adult()+flight.getLoc_Chd()+flight.getTra_Chd();
				times = pax / LadderMaxPax;
				/*�������ȡ�����10 ����һ���ڶɳ����� */
				if (pax % LadderMaxPax > 10){
					times +=1;
				}
				break;
			case "BRIDGE":	
			case "LADDER" :
				if (OutIn.Arr.equals(flight.getIsOutIn())){
					/*
					 * �ҵ�����κ���
					 */
					HisFlight linkFlight =   flightService.getLinkFlight(flight);
					if (linkFlight != null){
						
						for(FlightDisPatch disPatch:flight.getFlightDisPatchs()){
							if (parkBegin.equals(disPatch.getDisPatchItem().getName())){
								startTime = disPatch.getEndRealTime();
							}
						}
						
						/*
						 * ����Ҳ���ʵ��ʱ�䣬��ʱ����غ���ʵ�����ʱ��+10����
						 */
						if (startTime == null){
							startTime = DateTimeUtil.addMillisecond(flight.getActualLandInTime(),10*60*1000);
						}
						
						for(FlightDisPatch disPatch:linkFlight.getFlightDisPatchs()){
							if (parkEnd.equals(disPatch.getDisPatchItem().getName())){
								endTime = disPatch.getEndRealTime();
							}
						}
						
						/*
						 * ����Ҳ���ʵ��ʱ�䣬��ʱ����ɺ���ʵ�����ʱ��+10����
						 */
						if (endTime == null){
							endTime = DateTimeUtil.addMillisecond(linkFlight.getActualTakeOffTime(),-10*60*1000);
						}
						if ( startTime != null && endTime != null){
							 times = (int) (DateTimeUtil.MillisecondBetween(startTime, endTime)/60000);
						}						
						
						
					}
				}
				break;

			}
			if (times >0 || startTime != null){
				link.setTimes(times);
				link.setStartTime(startTime);
				link.setEndTime(endTime);
				add(link);
			}
		}
		
		
		
	}
	

	@Override
	public void createFlightDefaultLinks(HisFlight flight) throws Exception{
		
		Aircraft aircraft = flight.getAircraft();

		if (aircraft != null &&  !stateService.getCnlState().equals(flight.getState())){
			for(int i=0;i<methods.length;i++){
				createLink(aircraft, flight, methods[i], codes[i]);
			}
			
		}
	}
}
