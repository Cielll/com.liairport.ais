package com.lzairport.ais.service.aodb.impl;

import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;

import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.BaseFlight;
import com.lzairport.ais.models.aodb.StopFlight;
import com.lzairport.ais.service.aodb.IAreaAttributeService;
import com.lzairport.ais.service.aodb.IBaseFlightService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.utils.Ais_String_Util;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.OutIn;
import com.lzairport.ais.utils.SYS_VARS.RouteType;


/**
 * ��������Service�ӿڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 09/12/14
 * @param <E>
 * @since JDK 1.6
 *
 */

public abstract class BaseFlightService<K,E extends BaseFlight>  extends Service<K,E> implements IBaseFlightService<K,E> {
	
	private static	String strTransit = "-"+SYS_VARS.LocalAirportCn+"-";	

	@EJB
	protected IAreaAttributeService attributeService;

	@Override
	@SuppressWarnings("unchecked")
	public Set<? extends StopFlight> createStopFlights(BaseFlight flight, Set<? extends StopFlight> scrStops) {

		//��ȡ��ͣ�����ʵ������
		
		Class<StopFlight> stopClass = (Class<StopFlight>) ObjectMethodUtil.getFieldGenericType(flight, BaseFlight.STOPFLIGHTS, 0);
		
		
		//����ͣ�����б�д�뺽��
		Set<StopFlight> destStops = new HashSet<StopFlight>();
		for (StopFlight scrStop:scrStops){
			try {
				StopFlight destStop = stopClass.newInstance();
				destStop.setOrderCode(scrStop.getOrderCode());
				destStop.setStopAirport(scrStop.getStopAirport());
				destStop.setPlanLandInTime(scrStop.getPlanLandInTime());
				destStop.setPlanTakeOffTime(scrStop.getPlanTakeOffTime());
				destStops.add(destStop);
				
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		flight.setStopFlights(destStops);
		
		return destStops;
	}


	
	@Override
	public RouteType getRouteType(BaseFlight flight) {
		
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

	
	@Override
	public String getBigFlightNo(BaseFlight baseFlight) {
		
		String bigFlightNo = null;
		
		if (baseFlight.getFlightNO() != null){
			/*
			 *   ȡ�ú���ų�ȥ���պ��չ�˾����Ĳ���
			 *   ���磺CA1860 ��ȡ1860
			 */
			String subFlt = baseFlight.getFlightNO().substring(2);
			String lnkFlightNo = null;

			/*
			 *  ��ȡ������ʼ��������ػ��������ִ��� 
			 */
			String startCode = null ;
			String endCode = null ;
			if (baseFlight.getStartAirport() != null && baseFlight.getTerminalAirport() != null){
				startCode = baseFlight.getStartAirport().getThreeCharCode();
				endCode = baseFlight.getTerminalAirport().getThreeCharCode();
			}

			
			if (Ais_String_Util.isNumeric(subFlt)){
				/*
				 *  ����������һλ������ ��ʼ��������ƻ����Ǳ���
				 *  ������ʼ��������ƻ���Ϊ��(˵�����߲�����)
				 */
				
				
				int fltNum = Integer.valueOf(subFlt);
				if (SYS_VARS.LocalAirportThreeCode.equals(startCode) || 
					SYS_VARS.LocalAirportThreeCode.equals(endCode) ||
					startCode == null || endCode == null){

					
					/*
					 *  ȡ��+1����-1�ĺ����
					 *  ���磺CA1860 +1 CA1861 -1 CA1859
					 */
					String fltNo_0 = baseFlight.getFlightNO().substring(0,2)+(fltNum+1);
					String fltNo_1 = baseFlight.getFlightNO().substring(0, 2)+(fltNum-1); 
					/*
					 * ����+1 -1�������ɡ���ػ����Ե��ĺ���
					 * ���磺 CA1860 LZH PEK  +1 CA1861 PEK LZH -1 CA1859 PEK LZH
					 */
					QueryConditions conditions = new QueryConditions();
					conditions.setExpresstion(new Object[]{BaseFlight.FLIGHTNO,"=",fltNo_0,
							SYS_VARS.LinkSqlAnd,BaseFlight.DEPAIRPORT,"=",baseFlight.getArrAirport(),
							SYS_VARS.LinkSqlAnd,BaseFlight.ARRAIRPORT,"=",baseFlight.getDepAirport()});
					BaseFlight lnkBaseFlight = findByConditionSingle(conditions);
					if (lnkBaseFlight != null){
						lnkFlightNo = lnkBaseFlight.getFlightNO();
					}else{
						conditions.setExpresstion(new Object[]{BaseFlight.FLIGHTNO,"=",fltNo_1,
								SYS_VARS.LinkSqlAnd,BaseFlight.DEPAIRPORT,"=",baseFlight.getArrAirport(),
								SYS_VARS.LinkSqlAnd,BaseFlight.ARRAIRPORT,"=",baseFlight.getDepAirport()});
						lnkBaseFlight = findByConditionSingle(conditions);
						if (lnkBaseFlight != null){
							lnkFlightNo = lnkBaseFlight.getFlightNO();
						}
					}
					
				}
			}
			

			
			if (lnkFlightNo != null){
				/*
				 *  ����ҵ���Ӧ�ĺ����
				 *  BigFlightNo����  ���ۺ����/���ۺ����
				 *  ����:CA1860 LZH PEK CA 1859 PEK LZH BigFlghtNo CA1859/CA1860
				 */
				if (OutIn.Dep.equals(baseFlight.getIsOutIn())){
					/*
					 *  ����ǳ���
					 */
					bigFlightNo = lnkFlightNo+"/"+baseFlight.getFlightNO();
				}else{
					/* 
					 * ����ǽ���
					 */
					bigFlightNo = baseFlight.getFlightNO()+"/"+lnkFlightNo;
				}
			}else{
				/*
				 *  ���û�й����ĺ���ţ����������ΪBigFlightNo
				 */
				bigFlightNo = baseFlight.getFlightNO();
			}
			
		}
		return bigFlightNo; 
	}
	
	

}
