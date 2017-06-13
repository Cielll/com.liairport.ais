package com.lzairport.ais.service.aodb;

import java.util.Date;

import javax.ejb.Remote;

import com.lzairport.ais.exception.FlightServiceException;
import com.lzairport.ais.models.aodb.Aircraft;
import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.models.aodb.DelayReason;
import com.lzairport.ais.models.aodb.DynFlight;

/**
 * ���ද̬Service�ӿڣ����庽�ද̬�ĸ���״̬���¼��������Ϊ
 * @author ZhangYu
 * @version 0.9a 04/16/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IDynFlightService extends IFlightService<Integer, DynFlight>{
	
	/**
	 * ���º�������״̬
	 * @param flight ����ʵ����
	 * @param takeOffTime ���ʱ��
	 * @throws FlightServiceException
	 */
	
	public DynFlight setFlightTakeOff(DynFlight flight,Date takeOffTime)  throws FlightServiceException;
	
	
	/**
	 * ���ĺ���Ԥ�����ʱ��
	 * @param flight ����ʵ����
	 * @param takeOffTime ���ʱ��
	 * @throws FlightServiceException
	 */
	
	public DynFlight setFlighttAlterateTakeOffTime(DynFlight flight,Date takeOffTime)  throws FlightServiceException;
	
	/**
	 * ���º�������״̬
	 * @param flight ����ʵ����
	 * @param landInTime ���ʱ��
	 * @throws FlightServiceException
	 */
	
	public DynFlight setFlightLandIn(DynFlight flight,Date landInTime)  throws FlightServiceException;
	
	/**
	 * ���ĺ���Ԥ�����ʱ��
	 * @param flight
	 * @param takeOffTime
	 * @throws FlightServiceException
	 */
	
	public DynFlight setFlightAlterateLandInTime(DynFlight flight,Date landInTime)  throws FlightServiceException;
	

	/**
	 * ���º��������״̬
	 * @param flight ����ʵ����
	 * @param reason ����ԭ��
	 * @throws FlightServiceException
	 */
	public DynFlight setFlightDly(DynFlight flight,DelayReason reason,Date alteraTeakeOffTime) throws FlightServiceException;
	
	/**
	 * ���º���Ϊȡ��״̬
	 * @param flight ����ʵ����
	 * @param reason ȡ��ԭ��
	 * @throws FlightServiceException
	 */
	public DynFlight setFlightCnl(DynFlight flight,DelayReason reason) throws FlightServiceException;
	
	
	/**
	 * ���º���Ϊ����״̬
	 * @param flight ����ʵ����
	 * @param reason ����ԭ��
	 * @throws FlightServiceException
	 */
	public DynFlight setFlightAlternate(DynFlight flight,DelayReason reason,Airport alternateAirport) throws FlightServiceException;
	
	

	/**
	 * ���º���Ϊ����״̬
	 * @param flight ����ʵ����
	 * @param reason ����ԭ��
	 * @throws FlightServiceException
	 */
	public DynFlight setFlightReturn(DynFlight flight,DelayReason reason) throws FlightServiceException;
	
	
	/**
	 * ���º���ΪFPL״̬
	 * @throws FlightServiceException 
	 */
	public DynFlight setFlightFPL(DynFlight flight) throws FlightServiceException;
	
	

	
	/**
	 * ��������ɻ�
	 * @param flight ����ʵ����
	 * @param aircraft �����ɻ�
	 * @throws FlightServiceException
	 */
	
	public void flightChangeAircraft(DynFlight flight,Aircraft aircraft) ;
	
	
	/**
	 * ��麽���Ƿ�����
	 * @param flight ����ʵ����
	 * @return �Ƿ�
	 */
	public boolean CheckFlightDelay(DynFlight flight);
	
	/**
	 * ��鱾�������Ƿ���FPL����
	 * @param flight ����ʵ����
	 * @return �Ƿ�
	 */
	public boolean CheckLocalFPLDelay(DynFlight flight);

	/**
	 * ���һ���������ݵ�������
	 * �������Ƿ���ء���ɡ����ʱ�䣬�������ࡢ�������ݵ� 
	 * @param flight ����ʵ����
	 * @return ����������� �ַ���"",��������������ؾ��岻��������ϸ��Ϣ
	 */
	public String checkFlightCompelete(DynFlight flight);


	
	
}
