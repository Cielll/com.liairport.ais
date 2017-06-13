package com.lzairport.ais.service.aodb.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IDynFlightDao;
import com.lzairport.ais.dao.impl.AisOrder;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.exception.FlightServiceException;
import com.lzairport.ais.models.aodb.Aircraft;
import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.models.aodb.DelayReason;
import com.lzairport.ais.models.aodb.DynFlight;
import com.lzairport.ais.models.aodb.DynFlightDisPatch;
import com.lzairport.ais.models.aodb.DynFlightLoad;
import com.lzairport.ais.models.aodb.DynPayingPassenger;
import com.lzairport.ais.models.aodb.DynStopFlight;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.service.aodb.IDynFlightService;
import com.lzairport.ais.service.aodb.IFlightStateService;
import com.lzairport.ais.service.aodb.IHisFlightService;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * 
 * FileName      DynFlightService.java
 * @Description  ���ද̬��Service��ʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate:  09/05/15
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015-9-21      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class DynFlightService extends FlightService<Integer, DynFlight>
		implements IDynFlightService {

	@EJB
	private IFlightStateService flightStateService;

	@EJB
	private IHisFlightService hisFlightService;

	/**
	 * ������ʱ�䣬�����жϲ������ַ���ʱ��
	 */
	private static int SetdeviationMinute = 20;

	/**
	 * ������ɴ���������ƽ���������ʱ��֮��
	 */
	private static int FlightTakeOffNum = 5;

	

	

	@EJB
	public void setDynFlightDao(IDynFlightDao dynFlightDao) {
		setDao(dynFlightDao);
	}

	/**
	 * ��һ�����ද̬�鵵����ʷ�������ɾ���ú��ද̬
	 * 
	 * @param dynFlight
	 *            ���ද̬ʵ��
	 * @param cover
	 *            ���Ǳ�־
	 * @throws Exception 
	 */
	private void DynToHis(DynFlight dynFlight, Boolean cover) throws Exception {

		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[] { HisFlight.EXECDATE, "=",
				dynFlight.getExecDate(), SYS_VARS.LinkSqlAnd,
				HisFlight.DEPAIRPORT, "=", dynFlight.getDepAirport(),
				SYS_VARS.LinkSqlAnd, HisFlight.ARRAIRPORT, "=",
				dynFlight.getArrAirport(), SYS_VARS.LinkSqlAnd,
				HisFlight.FLIGHTNO, "=", dynFlight.getFlightNO() });
		HisFlight hisFlight = hisFlightService
				.findByConditionSingle(conditions);

		// ����º����־
		Boolean addNewFlight = false;

		if (hisFlight == null) {
			hisFlight = new HisFlight();
			addNewFlight = true;
		}

		// �������ӵ��º�����߸��Ǳ�־ΪTrue
		if ((addNewFlight) || (cover)) {
			// ���������Ϣ����
			ObjectMethodUtil.copybean(hisFlight,dynFlight);
			hisFlight.setId(0);
			
			// ��������
			Set<DynFlightLoad> dynFlightLoads = dynFlight.getLoads();
			copyFlightLoads(hisFlight, dynFlightLoads);

			// ���񻷽ڸ���
			Set<DynFlightDisPatch> dynFlightDisPatchs = dynFlight.getFlightDisPatchs();
			copyFlightDisPatchs(hisFlight, dynFlightDisPatchs);

			// �����ÿ͸���
			Set<DynPayingPassenger> dynPayingPassengers = dynFlight.getPayingPassengers();
			copyFlightPayingPassengers(hisFlight, dynPayingPassengers);
			
			//��ͣ���ഴ��
			Set<DynStopFlight> dynStopFlights =  dynFlight.getStopFlights();
			createStopFlights(hisFlight, dynStopFlights);
			
			//���ú��չ�˾
			if ((hisFlight.getAircraft() != null)&&(hisFlight.getAircraft().getCarrier() != null)){
				hisFlight.setAirlines(hisFlight.getAircraft().getCarrier().getAirlines());
			}
			
			//���ú�������
			if ((hisFlight.getDepAirport() != null)&&(hisFlight.getArrAirport() != null)){
				if ((hisFlight.getDepAirport().getAreaAttribute().getCnShortName().equals("����"))||
						(hisFlight.getArrAirport().getAreaAttribute().getCnShortName().equals("����"))){
					hisFlight.setAttribute(attributeService.getIntAttribute());
				}else if ((hisFlight.getDepAirport().getAreaAttribute().getCnShortName().equals("����"))||
						(hisFlight.getArrAirport().getAreaAttribute().getCnShortName().equals("����"))){
					hisFlight.setAttribute(attributeService.getRegAttribute());
				}else{
					hisFlight.setAttribute(attributeService.getDomAttribute());
				}
			}
			//���ݷɻ��Ļ����������ú���Ŀ�����λ���ɹ�ҵ��
			if (hisFlight.getAircraft() != null){
				hisFlight.setAvailableLoad(hisFlight.getAircraft().getAvailableLoad());
				hisFlight.setAvailableSeat(hisFlight.getAircraft().getAvailableSeat());
			}

			//�������ΪFalse
			hisFlight.setApprove(false);
			if (dynFlight.getTakeOffLandInCount() < 1 && dynFlight.getState().equals(flightStateService.getLandInState())){
				hisFlight.setTakeOffLandInCount(1);
			}else if(dynFlight.getState().equals(flightStateService.getCnlState())){
				hisFlight.setTakeOffLandInCount(0);
			}
			hisFlight.setBigFlightNO(hisFlightService.getBigFlightNo(hisFlight));
			hisFlight = hisFlightService.add(hisFlight);
			hisFlightService.updateExtraParams(hisFlight);
			
		}
		this.remove(dynFlight);
	}

	@Override
	public String checkFlightCompelete(DynFlight flight) {
		String errStr = "";

		if (!flight.getState().equals(flightStateService.getCnlState())) {
			// ���಻����ȡ��,��麽������������ԣ������ȡ�����Ժ��Լ��
			if (!flight.getState().equals(flightStateService.getLandInState())) {
				errStr += "�������״̬\n ";
			}

			if (flight.getActualTakeOffTime() == null) {
				errStr += "���ʱ��Ϊ��\n";
			}

			if (flight.getActualLandInTime() == null) {
				errStr += "���ʱ��Ϊ��\n";
			}

			if (flight.getLinkFlight() == null) {
				errStr += "��������Ϊ��\n";
			}

			if (flight.getLoads() == null) {
				errStr += "����Ϊ��\n";
			}

			if (flight.getGuestRate() == 0 || flight.getGuestRate() > 100) {
				errStr += "������Ϊ0���ߴ���100\n ";
			}

		}

		if (!errStr.equals("")) {
			errStr = "\n" + flight + " ���ݲ�������ԭ������:\n" + errStr;
			errStr += "-------------------------------------------------------------------------------";
		}
		return errStr;

	}

	private static String AvailableTakeOff = " PLN FPL ������� ������� ���� ǰ����� ������� ������� ������� ";

	@Override
	public DynFlight setFlightTakeOff(DynFlight flight, Date takeOffTime)
			throws FlightServiceException {

		long calcMillis = 0;
		int count = 0;
		long fplMillis = DateTimeUtil.HHMMToMillisecond(flight.getFlightTime());
		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (fplMillis == 0) {
			// ���û����FPL��¼�ķ���ʱ��
			throw new FlightServiceException(flight.getFlightNO()
					+ "�������״̬ʧ��\r" + "ԭ�򣺺���Ԥ�Ʒ���ʱ��Ϊ0");
		} else if (AvailableTakeOff.indexOf(stateStr) != -1) {
			// �������״̬����������,�������ʱ��,�������״̬
			if (flight.getState().equals(
					flightStateService.getAlternateLandInState())) {
				// ����������ɴ���,������ƽ������ʱ��,ֱ�Ӳ���FPL�ķ���ʱ��
				flight.setState(flightStateService.getAlternateTakeOffState());
				flight.setAlternateActualTakeOffTime(takeOffTime);
			} else {
				if (flight.getState().equals(
						flightStateService.getReturnLandInState())) {
					// ����������ɴ���
					flight.setState(flightStateService.getReturnTakeoffState());
				} else if (flight.getDepAirport().getThreeCharCode()
						.equals(SYS_VARS.LocalAirportThreeCode)) {
					// ����Ǳ������
					flight.setState(flightStateService.getLocalTakeOffState());
				} else {
					// ǰ�����
					flight.setState(flightStateService
							.getPreviousTakeOffState());
				}
				flight.setActualTakeOffTime(takeOffTime);

				// �������FlightTakeOffNum�θú��ߵķ���ʱ��,������ƽ������ʱ��
				QueryConditions conditions = new QueryConditions();
				conditions.setExpresstion(new Object[] { HisFlight.FLIGHTNO,
						"=", flight.getFlightNO(), SYS_VARS.LinkSqlAnd,
						HisFlight.DEPAIRPORT, "=", flight.getDepAirport()});
				AisOrder order = new AisOrder();
				order.setName(HisFlight.EXECDATE);
				order.setSortMode(SYS_VARS.DescSORT);
				conditions.setOrders(new AisOrder[] { order });
				conditions.setMax(FlightTakeOffNum);
				List<HisFlight> hisFlights = hisFlightService
						.findByConditionAll(conditions);
				for (HisFlight hisFlight : hisFlights) {
					if ((hisFlight.getActualTakeOffTime() != null) &&(hisFlight.getActualLandInTime() != null)){
						count++;
						calcMillis = calcMillis+DateTimeUtil.MillisecondBetween(hisFlight.getActualTakeOffTime(),
								hisFlight.getActualLandInTime());
					}
				}

				if (count != 0) {
					calcMillis = calcMillis / count;
				}
			}

			// ����FPL�ķ���ʱ������
			long deviationMinute = Math.abs(fplMillis - calcMillis)
					/ (1000 * 60);
			// ����Ԥ����ص�ʱ�䣬�������������ʱ�䣬����FPL�ķ���ʱ�䣬���С�������ü����ƽ������ʱ��
			if ((calcMillis != 0) && (deviationMinute < SetdeviationMinute)) {
				flight.setAlterateLandinTime(DateTimeUtil
						.roundDate(DateTimeUtil.addMillisecond(takeOffTime,
								calcMillis)));
			} else {
				flight.setAlterateLandinTime(DateTimeUtil
						.roundDate(DateTimeUtil.addMillisecond(takeOffTime,
								fplMillis)));
			}
			return this.update(flight);
		} else {
			// �������״̬�����������У��׳��쳣
			throw new FlightServiceException(flight.getFlightNO()
					+ "�������״̬ʧ��\r" + "ԭ�򣺺���״̬����" + AvailableTakeOff + "�е�һ��");
		}

	}

	private static String AvailableAlterateTakeOff = " PLN FPL ������� ������� ���� ";

	@Override
	public DynFlight setFlighttAlterateTakeOffTime(DynFlight flight, Date takeOffTime)
			throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableAlterateTakeOff.indexOf(stateStr) != -1) {
			flight.setAlteraTeakeOffTime(takeOffTime);
			update(flight);
		} else {
			throw new FlightServiceException(flight.getFlightNO()
					+ "�������Ԥ�����ʱ��ʧ��\r" + "ԭ�򣺺���״̬����" + AvailableAlterateTakeOff
					+ "�е�һ��");
		}

		return flight;
	}

	private static String AvailableLandIn = " ǰ����� ������� ������� ������ ������� ������ ������� ������� ��� ";

	@Override
	public DynFlight setFlightLandIn(DynFlight flight, Date landInTime)
			throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";
		

		if (AvailableLandIn.indexOf(stateStr) != -1) {

			//����Ǻ������״̬��أ��𽵼ܴ�+1
			if (flight.getState().equals(flightStateService.getLocalTakeOffState()) ||
					flight.getState().equals(flightStateService.getPreviousTakeOffState())||
					flight.getState().equals(flightStateService.getReturnState())||
					flight.getState().equals(flightStateService.getReturnTakeoffState())
					){
				flight.setTakeOffLandInCount(flight.getTakeOffLandInCount()+1);
			}
			
			if (flight.getState()
					.equals(flightStateService.getAlternateState())) {
				// ���౸�����
				flight.setState(flightStateService.getAlternateLandInState());
				flight.setAlternateActualLandInTime(landInTime);
			} else if (flight.getState().equals(
					flightStateService.getReturnState())) {
				// ���෵�����
				flight.setState(flightStateService.getReturnLandInState());
				flight.setActualTakeOffTime(null);

			} else {
				//�������״̬�Ǳ������ ������أ���ֻ����ʱ��
				if (!flight.getState().equals(flightStateService.getReturnLandInState())
						&&!flight.getState().equals(flightStateService.getAlternateLandInState())){
					flight.setState(flightStateService.getLandInState());
				}
				flight.setActualLandInTime(landInTime);
			}
			super.update(flight);
		} else {
			// �������״̬�����������У��׳��쳣
			throw new FlightServiceException(flight.getFlightNO()
					+ "�������״̬ʧ��\r" + "ԭ�򣺺���״̬����" + AvailableLandIn + "�е�һ��");

		}
		return flight;

	}

	private static String AvailableAlterateLandIn = " ǰ����� ������� ������� ������ ������� ������ ";

	@Override
	public DynFlight setFlightAlterateLandInTime(DynFlight flight, Date landInTime)
			throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableAlterateLandIn.indexOf(stateStr) != -1) {
			flight.setAlterateLandinTime(landInTime);
			update(flight);
		} else {
			throw new FlightServiceException(flight.getFlightNO()
					+ "�������Ԥ�����ʱ��ʧ��\r" + "ԭ�򣺺���״̬����" + AvailableAlterateLandIn
					+ "�е�һ��");
		}
		return flight;
	}

	private static String AvailableDly = " PLN FPL ǰ����� ������� ������� ������ ������� ������ ������� �������  ";

	@Override
	public DynFlight setFlightDly(DynFlight flight, DelayReason reason,
			Date alteraTeakeOffTime) throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableDly.indexOf(stateStr) != -1) {
			flight.setNormal(false);
			flight.setAlteraTeakeOffTime(alteraTeakeOffTime);
			flight.setInternalDelayReason(reason);
			flight.setState(flightStateService.getDlyState());
			super.update(flight);
		} else {
			// �������״̬���ڷ�����������У��׳��쳣
			throw new FlightServiceException(flight.getFlightNO()
					+ "��������״̬ʧ��\r" + "ԭ�򣺺���״̬����" + AvailableDly + "�е�һ��");
		}
		return flight;

	}

	private static String AvailableCNL = " PLN FPL ���� ������� ������� ";

	@Override
	public DynFlight setFlightCnl(DynFlight flight, DelayReason reason)
			throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableCNL.indexOf(stateStr) != -1) {
			flight.setNormal(false);
			flight.setState(flightStateService.getCnlState());
			flight.setInternalDelayReason(reason);
			super.update(flight);
		} else {
			// �������״̬���ڷ�������״̬�У��׳��쳣
			throw new FlightServiceException(flight.getFlightNO()
					+ "����ȡ��״̬ʧ��\r" + "ԭ�򣺺���״̬����" + AvailableCNL + "�е�һ��");
		}
		return flight;

	}

	private static String AvailableAlternate = " ǰ����� ������� ������� ������ ������� ";

	@Override
	public DynFlight setFlightAlternate(DynFlight flight, DelayReason reason,
			Airport alternateAirport) throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableAlternate.indexOf(stateStr) != -1) {
			flight.setNormal(false);
			flight.setState(flightStateService.getAlternateState());
			flight.setAlternateAirport(alternateAirport);
			flight.setInternalDelayReason(reason);
			super.update(flight);

		} else {
			// �������״̬���ڷ�������״̬�У��׳��쳣
			throw new FlightServiceException(flight.getFlightNO()
					+ "���±���״̬ʧ��\r" + "ԭ�򣺺���״̬����" + AvailableAlternate + "�е�һ��");

		}
		return flight;

	}

	private static String AvailableReturn = " ǰ����� ������� �������  ������� ������ ";

	@Override
	public DynFlight setFlightReturn(DynFlight flight, DelayReason reason)
			throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableReturn.indexOf(stateStr) != -1) {
			flight.setNormal(false);
			flight.setState(flightStateService.getReturnState());
			flight.setInternalDelayReason(reason);
			super.update(flight);

		} else {
			// �������״̬���ڷ�������״̬�У��׳��쳣
			throw new FlightServiceException(flight.getFlightNO()
					+ "���·���״̬ʧ��\r" + "ԭ�򣺺���״̬����" + AvailableReturn + "�е�һ��");

		}
		return flight;

	}

	private static String AvailableFPL = " PLN FPL ���� ������� ������� ";

	@Override
	public DynFlight setFlightFPL(DynFlight flight) throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableFPL.indexOf(stateStr) != -1) {
			if (flight.getState().equals(flightStateService.getPlnState())) {
				flight.setState(flightStateService.getFPLState());
			}
			super.update(flight);
		} else {
			// �������״̬���ڷ���FPL�У��׳��쳣
			throw new FlightServiceException(flight.getFlightNO()
					+ "����FPL״̬ʧ��\r" + "ԭ�򣺺���״̬����" + AvailableFPL + "�е�һ��");
		}

		return flight;
	}

	@Override
	public void flightChangeAircraft(DynFlight flight, Aircraft aircraft) {

		// �����ɻ�
		flight.setAircraft(aircraft);
		update(flight);
		// ���ҹ����ĺ��࣬�����ɻ�
		DynFlight linkFlight = getLinkFlight(flight);
		while (linkFlight != null) {
			linkFlight.setAircraft(aircraft);
			update(linkFlight);
			linkFlight = getLinkFlight(linkFlight);
		}
	}

	@Override
	public void convertFlights(Date startDate, Boolean cover,Boolean forcedImport) throws Exception {

		// ����ָ�����ڵĺ���
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[] { DynFlight.EXECDATE, "<=",
				startDate });
		List<DynFlight> flights = findByConditionAll(conditions);

		for (DynFlight dynFlight : flights) {
			// ���������������������Ҫǿ�Ƶ���
			if (checkFlightCompelete(dynFlight).equals("") || forcedImport) {
				DynToHis(dynFlight, cover);
			}
		}
	}

	@Override
	public boolean CheckFlightDelay(DynFlight flight) {
		// �������״̬����δ��ɵ�״̬��
		if (flight.getState().equals(flightStateService.getPlnState())
				|| flight.getState().equals(flightStateService.getFPLState())) {

			if ((flight.getDepAirport() != null) && (flight.getPlanTakeOffTime() != null)){
				// ȡ�û������ж�����ʱ�� ��һ����� 15���� ���� �Ϻ�30����
				int IntervalDelayTime = flight.getDepAirport()
						.getIntervalDelayTime();
				// ���������ж�ʱ�䣬���ڴ�ʱ�亽������С�ڴ�ʱ�亽������
				Date notMaxDelayTime = DateTimeUtil.addMillisecond(
						flight.getPlanTakeOffTime(), IntervalDelayTime);
				
				
				if (new Date().compareTo(notMaxDelayTime) == 1) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean CheckLocalFPLDelay(DynFlight flight) {
		// ��������Ǽƻ�״̬���Ǳ�����ɺ���
		if	((flight.getDepAirport() != null)&& 
		(flight.getState().equals(flightStateService.getPlnState())
				&& flight.getDepAirport().getThreeCharCode().equals(SYS_VARS.LocalAirportThreeCode))) {
			// ����δ��FPL�жϵ�ʱ�䣬���ڴ�ʱ�亽��δ��FPL����True,С�ڷ���False
			Date notMaxDelayFPLTime = DateTimeUtil.addMillisecond(
					flight.getPlanTakeOffTime(), SYS_VARS.FlightFPLSecond);
		    
			if (new Date().compareTo(notMaxDelayFPLTime) == 1) {
				return true;
			}
		}
		return false;
	}

	

}
