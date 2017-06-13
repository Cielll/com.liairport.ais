package com.lzairport.ais.service.aodb.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IHisFlightDao;
import com.lzairport.ais.dao.impl.AisOrder;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.aodb.HisFlightLoad;
import com.lzairport.ais.models.aodb.HisPayingPassenger;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.models.statistics.AggregationColumnField;
import com.lzairport.ais.models.statistics.CumulativeColumnField;
import com.lzairport.ais.service.DataFetchResponseInfo;
import com.lzairport.ais.service.aodb.IAirportService;
import com.lzairport.ais.service.aodb.IFlightStateService;
import com.lzairport.ais.service.aodb.IHisFlightService;
import com.lzairport.ais.service.aodb.IScheduleFlightService;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.OutIn;
import com.lzairport.ais.vo.FlightVO;
import com.lzairport.ais.vo.converter.IConverter;

/**
 * ������ʷ��Service
 * @author ZhangYu
 * @version 0.9a 23/12/14
 * @since JDK 1.6
 *
 */

@Stateless
public class HisFlightService extends FlightService<Integer,HisFlight>  implements IHisFlightService{

	@EJB(beanName="HisFlightConverter")
	private IConverter<HisFlight,FlightVO> flightConverter;
	
	@EJB
	private IAirportService airportService;
	
	@EJB
	private IFlightStateService stateService;
	
	@EJB
	private IScheduleFlightService scheduleFlightService;
	
	

	@EJB
	public void setHisFlightDao(IHisFlightDao hisFlightDao){
		
		setDao(hisFlightDao);
	}
	
	
	@Override
	public void convertFlights(Date startDate, Boolean cover,Boolean forcedImport) {
		// ʲôҲ��������Ϊ��ʷ���಻��ת��
		
	}


	@Override
	public DataFetchResponseInfo fetchByCondition(QueryConditions conditions) throws Exception {
		// TODO Auto-generated method stub
		
		List<HisFlight> flights = findByConditionAll(conditions);
		List<FlightVO> flightVOs = new ArrayList<FlightVO>();
		for (HisFlight flight:flights){
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
	public void updateExtraParams(HisFlight flight){
		Set<HisFlightLoad> loads = flight.getLoads();
		/**
		 *  ��ʼ������ʵ�������
		 */
		int adult = 0;      int chd = 0;       int inf = 0;        int goods = 0; 	  int mail = 0;     int luggage = 0;   
		int tra_Adult =0;	int tra_Chd = 0;   int tra_Inf = 0;    int tra_Goods = 0; int tra_Mail = 0;	int tra_Luggage = 0;
		/**
		 * ���㺽���������ȥ������صĺ���
		 */
		Integer pax = 0;
		if (loads != null && flight.getIsOutIn() != null){ 
			for(HisFlightLoad load:loads){
				if (flight.getIsOutIn().equals(OutIn.Dep)){
					/**
					 * ����ǳ��ۺ���
					 */
					if (airportService.getLocalAirport().equals(load.getLd_DepAirport())){
						/**
						 * �����������ɻ���ʱ������˵�������Ǳ�������������
						 */
						adult += load.getAdult();
						chd += load.getChd();
						inf += load.getInf();
						goods += load.getGoods();
						mail += load.getMail();
						luggage += load.getLuggage();
					}else{
						/**
						 * ��������ת����������
						 */
						tra_Adult += load.getAdult();
						tra_Chd += load.getChd();
						tra_Inf += load.getInf();
						tra_Goods += load.getGoods();
						tra_Mail += load.getMail();
						tra_Luggage += load.getLuggage();
					}
				}else  if (flight.getIsOutIn().equals(OutIn.Arr)) {
					/**
					 * ����ǽ��۵ĺ��࣬���ۺ���û����ת�ÿ�
					 */
					if (airportService.getLocalAirport().equals(load.getLd_ArrAirport())){
						adult += load.getAdult();
						chd += load.getChd();
						inf += load.getInf();
						goods += load.getGoods();
						mail += load.getMail();
						luggage += load.getLuggage();
					}
				}
	  }}
		
	  flight.setLoc_Adult(adult); 
	  flight.setLoc_Chd(chd);
	  flight.setLoc_Inf(inf);
	  flight.setLoc_Luggage(luggage);
	  flight.setLoc_Goods(goods);
	  flight.setLoc_Mail(mail);
	  flight.setTra_Adult(tra_Adult);
	  flight.setTra_Chd(tra_Chd);
	  flight.setTra_Inf(tra_Inf);
	  flight.setTra_Goods(tra_Goods);
	  flight.setTra_Mail(tra_Mail);
	  flight.setTra_Luggage(tra_Luggage);
	  
	  pax = flight.getLoc_Adult()+flight.getLoc_Chd()+flight.getTra_Adult()+flight.getTra_Chd();
	  if (flight.getAircraft() != null &&flight.getAircraft().getMaxSeat() != 0){
		  flight.setGuestRate((int) (pax.floatValue()/flight.getAircraft().getMaxSeat()*100));
	  }
	  
	  /**
	   *  ��ȫ������շ��ÿ���Ϣ
	   */
	  Set<HisPayingPassenger> passengers = flight.getPayingPassengers();
	  
	  for(HisPayingPassenger passenger:passengers){
		  passenger.setAirlines(flight.getAirlines());
		  passenger.setExecDate(flight.getExecDate());
		  passenger.setFlightNO(flight.getFlightNO());
		  if (flight.getAircraft() != null){
			  passenger.setCarrier(flight.getAircraft().getCarrier());
		  }
		  if (passenger.getPayPrice() != null){
			  passenger.setTypeName(passenger.getPayPrice().getName());
			  passenger.setPrice(passenger.getPayPrice().getPrice());
		  }
		  if ("ͷ�Ȳ�".equals(passenger.getTypeName())){
			  passenger.setCategory(HisPayingPassenger.FCLASS);
		  }else if (passenger.getTypeName() != null){
			  passenger.setCategory(HisPayingPassenger.CCLASS);
		  }
		  if(flight.getAircraft() != null){
			  passenger.setCraftno(flight.getAircraft().getCraftno());
		  }
		  if(flight.getDepAirport() != null){
			  passenger.setDepAirport(flight.getDepAirport().getThreeCharCode());
		  }
		  if(flight.getArrAirport() != null){
			  passenger.setArrAirport(flight.getArrAirport().getThreeCharCode());
		  }
	  }
	  
	  if (flight.getAircraft() != null){
		  flight.setCarrier(flight.getAircraft().getCarrier());
		  /*
		   *  ��ȡ4λ���ʹ���
		   */
		  flight.setCraftCode(flight.getAircraft().getCraftType().getIcaoCode());
		  
	  }
	  super.update(flight);
	}

	@Override
	public void convertLoad (Date startDate) {
		
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{HisFlight.EXECDATE,">=",startDate});
		List<HisFlight> flights = findByConditionAll(conditions);
		for(HisFlight flight:flights){
			updateExtraParams(flight);
		}
		
	}

	public void routeSave(HisFlight flight){
		
		
	}
	

	@Override
	public HisFlight update(Object obj) {
		HisFlight flight = super.update(obj);
		updateExtraParams(flight);
		setRouteCn(flight);
		return flight;
	}

	

	
	@Override
	public HisFlight add(Object obj) {
		HisFlight flight = super.add(obj); 
		updateExtraParams(flight);
		setRouteCn(flight);
		return flight; 
	}


	@Override
	public void update(FlightVO flightVO) throws Exception {
		HisFlight flight = flightConverter.getEntity(flightVO);
		update(flight);
		
	}


	/**
	 * 
	 * @Description: ���ۺϷ��صĽ������ָ����Alias����Integer�Ľ��������ֻ����һ������
	 * @param result �ۺϷ��صĽ��
	 * @param alias ��Ӧ�ı���
	 * @return Integer�Ľ��
	 */
	private Integer getAggregationValue(List<Map<String,Object>> result,String alias){
		if (result.size() > 0){
			Object obj = result.get(0).get(alias);
			if (obj instanceof Number) {
				
				Number value = (Number) obj;
				return value.intValue();
			}
		}
		return 0;
	}
	
	@Override
	public Integer cumulativeMonthField(Date date,CumulativeColumnField field) throws Exception {

		QueryConditions conditions = new QueryConditions();
		
		Date startDate = DateTimeUtil.getMonthFristDate(date);
		conditions.setExpresstion(new Object[]{HisFlight.EXECDATE,">=",startDate,"AND",
				HisFlight.EXECDATE,"<=",date,"AND",HisFlight.STATE,"<>",stateService.getCnlState()});
		conditions.setAggregationFields(new ArrayList<AggregationColumnField>());
		/**
		 * ���ԭ�����ֶ��ϵ�When����
		 */
		field.getAggregationColumnField().setWhenCondition(null);
		conditions.getAggregationFields().add(field.getAggregationColumnField());
		return getAggregationValue(findAggregationByCondition(conditions), field.getAggregationColumnField().getAlias());
	}


	@Override
	public Integer cumulativeYearField(Date date,CumulativeColumnField field) throws Exception{

		QueryConditions conditions = new QueryConditions();
		Date startDate = DateTimeUtil.getYearFristDate(date);
		conditions.setExpresstion(new Object[]{HisFlight.EXECDATE,">=",startDate,"AND",
				HisFlight.EXECDATE,"<=",date,"AND",HisFlight.STATE,"<>",stateService.getCnlState()});
		/**
		 * ���ԭ�����ֶ��ϵ�When����
		 */
		field.getAggregationColumnField().setWhenCondition(null);
		conditions.setAggregationFields(new ArrayList<AggregationColumnField>());
		conditions.getAggregationFields().add(field.getAggregationColumnField());
		return getAggregationValue(findAggregationByCondition(conditions), field.getAggregationColumnField().getAlias());
		
	}
	

	@Override
	public void setRouteCn(HisFlight flight) {
		String routecn = "";
		if (flight.getTerminalAirport() != null&&flight.getStartAirport()  != null){
			/**
			 * �����������
			 */
			if (flight.getStartAirport().getThreeCharCode().equals(SYS_VARS.LocalAirportThreeCode) ||
					flight.getTerminalAirport().getThreeCharCode().equals(SYS_VARS.LocalAirportThreeCode)){
				if (flight.getTerminalAirport().getThreeCharCode().equals(SYS_VARS.LocalAirportThreeCode)){
					/**
					 * ����ն˻����Ǳ��������ط����ߣ�����:
					 * MU5379 TAO-CGO-LZH
					 * 
					 */
					routecn =  flight.getBackRouteCn();
				}else{
					/**
					 * ����˵������������,���磺
					 * MU5204 LZH-SHA
					 */
					routecn =  flight.getForwardRouteCn();
				}
				
				
			}else{
				/**
				 * �������� 5��EU2201 CTU-LZH-SZX ����Ϊ CTU-LZH-SZX,����������
				 */
				routecn =  flight.getForwardRouteCn();
			}
			
		}else{
			/**
			 * ������߲����������ҳ��ں���ƻ���ȫ
			 */
			
			ScheduleFlight scheduleFlight = scheduleFlightService.findScheduleFlightByZP(flight);
			if (scheduleFlight != null){
				routecn =  scheduleFlightService.getRouteCn(scheduleFlight);
			}else{
				routecn =  "���߲�����";
			}
		}
		flight.setRouteHX(routecn);
		super.update(flight);
	}


	@Override
	public void linkFlight(HisFlight flight) {
		if (flight.getAircraft() != null && flight.getIsOutIn() != null){
			Date startDate = flight.getExecDate();
			Date endDate = flight.getExecDate();
			if (OutIn.Dep.equals(flight.getIsOutIn())){
				startDate = DateTimeUtil.addToDay(startDate, -1);
			}
			QueryConditions  conditions = new QueryConditions();
			conditions.setExpresstion(new Object[]{HisFlight.ACTUALLANDINTIME,"<",flight.getActualTakeOffTime(),"AND",HisFlight.AIRCRAFT,"=",flight.getAircraft(),
					"AND",HisFlight.EXECDATE,">=",startDate,"AND",HisFlight.EXECDATE,"<=",endDate});
			AisOrder order = new AisOrder();
			order.setName(HisFlight.ACTUALLANDINTIME);
			order.setSortMode(SYS_VARS.DescSORT);
			conditions.setOrders(new AisOrder[]{order});
			List<HisFlight> prFlights = findByConditionAll(conditions);
			if (prFlights.size() > 0 && prFlights.get(0).getIsOutIn() != null && !prFlights.get(0).getIsOutIn().equals(flight.getIsOutIn()) ){
				/*
				 * ���ǰ�κ��༯�ϲ�Ϊ���ҽ��������Բ���ͬ ,���һ��ǰ�κ����Ϊ�ú����ǰ�κ���
				 */
				flight.setLinkFlight(prFlights.get(0).toString());
			}else if (OutIn.Arr.equals(flight.getIsOutIn())){
				flight.setLinkFlight(SYS_VARS.LinkFlightArr);
			}
			update(flight);
		}
	}



	

	
}
