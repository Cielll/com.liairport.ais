package com.lzairport.ais.service.settlement.price;

import javax.ejb.Stateless;
import com.lzairport.ais.models.aodb.FlightDisPatch;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.SYS_VARS.OutIn;
/**
 * FileName      ParkCreater.java
 * @Description  TODO ͣ�����շ���Ŀ������
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��13�� 
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2016��11��13��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
@Stateless
public class ParkCreater extends DefaultSingleCreater {
	
	protected static  String parkBegin = "�����λ";
	protected static  String parkEnd   = "��ɿ���";
	
	private int getDiff(HisFlight flight){
		Long interval =  0L ;
		HisFlight prFlight = null;
		HisFlight ntFlight = null;
		if (OutIn.Dep.equals(flight.getIsOutIn())){
			prFlight =  flightService.getPreviousFlight(flight);
            ntFlight =  flight; 					
		}else if (OutIn.Arr.equals(flight.getIsOutIn())) {
			ntFlight = flightService.getLinkFlight(flight);
            prFlight = flight; 					
		}
		if (prFlight != null && ntFlight != null){
			for(FlightDisPatch disPatch:prFlight.getFlightDisPatchs()){
				if (parkBegin.equals(disPatch.getDisPatchItem().getName())){
					startTime = disPatch.getEndRealTime();
				}
			}
			
			for(FlightDisPatch disPatch:ntFlight.getFlightDisPatchs()){
				if (parkEnd.equals(disPatch.getDisPatchItem().getName())){
					endTime = disPatch.getEndRealTime();
				}
			}
		}
		
		if (startTime != null && endTime != null){
			
			/*
			 *  ���ʱ��(Сʱ)
			 */
			interval = DateTimeUtil.MillisecondBetween(startTime, endTime)/3600000;
			/*
			 *  �������2СʱС��24Сʱ����1������24Сʱÿ24Сʱ��1
			 */
		}
		return interval.intValue();
	}

	@Override
	protected Double getPrice(HisFlight flight) {
		Double price = 0.0;
		int    weight= 0;
		if (flight.getAircraft() != null){
			weight = flight.getAircraft().getMaxWeight(); 
		}
		if (areaService.getDomAttribute().equals(flight.getAttribute())){
			/*
			 *  ���ں���
			 */
			if (weight<=25){
				price = 270.0;
			}else if (weight <=50){
				price = 800.0;
			}else if (weight <= 100){
				price = 1400.0+26*(weight-50);
			}else if (weight <200){
				price = 2700.0+26*(weight-100);
			}else {
				price = 5300.0+33*(weight-200);
			}
		}else{
			/*
			 * ���ʺ���
			 */
			if (weight<=25){
				price = 2000.0;
			}else if (weight <=50){
				price = 2200.0;
			}else if (weight <= 100){
				price = 2600.0+40*(weight-50);
			}else if (weight <200){
				price = 4200.0+44*(weight-100);
			}else {
				price = 8600.0+56*(weight-200);
			}
		}
		
		int diff = getDiff(flight); 
		if (diff <= 2){
			/*
			 *   ���2Сʱ��������
			 */
			price = price * 0;
		}else if (diff <= 6){
			/*
			 *   2-6 Сʱ 20% 
			 */
			price = price * 0.2;
		}else if (diff <= 24 ){
			/*
			 *   6-24Сʱ25%
			 */
			price = price *0.25 ;
		}else{
			/*
			 *  24Сʱ����ÿ24Сʱ25%������24Сʱ��24Сʱ��
			 */
			Double basePrice = price *0.25 ;
			price = 0.0;
			do{
				price += basePrice;
				diff = diff -24;
			}while(diff > 0 );
		}
		
		
		return price;
	}

	@Override
	protected SettlementItem getSetItem(HisFlight flight) {
		
		return itemService.findByFieldSingle(SettlementItem.CODE, "PARK");
	}

	@Override
	protected Double getNumber(HisFlight flight, SettlementType type) {

	
		
		return type.getUnit();
	}

}
