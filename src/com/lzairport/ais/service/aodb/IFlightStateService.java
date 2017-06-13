package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.FlightState;
import com.lzairport.ais.service.IService;


/**
 * ����״̬Service�ӿڣ����Է��ض���õĺ����״̬
 * @author ZhangYu
 * @version 0.9a 12/11/14
 * @since JDK 1.6
 *
 */

@Remote
public interface IFlightStateService extends IService<Integer,FlightState> {
	
    /**
     * 
     * @return ����ƻ�״̬
     */
	public FlightState getPlnState(); 

	/**
	 * 
	 * @return ����ǰ�����
	 */
	public FlightState getPreviousTakeOffState();
	
	/**
	 * 
	 * @return ���౾�����
	 */
	public FlightState getLocalTakeOffState();

	/**
	 * 
	 * @return ���౸�����
	 */
	public FlightState getAlternateTakeOffState();
	
	
	/**
	 * 
	 * @return ���������
	 */
	public FlightState getReturnTakeoffState();
	
	/**
	 * 
	 * @return �����������
	 */
	public FlightState getLandInState();
	
	/**
	 * 
	 * @return ���౸�����
	 */
	public FlightState getAlternateLandInState();
	
	/**
	 * 
	 * @return ���෵�����
	 */
	public FlightState getReturnLandInState();
	
	/**
	 * 
	 * @return ���౸����
	 */
	public FlightState getAlternateState();
	
	/**
	 * 
	 * @return ���෵����
	 */
	public FlightState getReturnState();
	
	/**
	 * 
	 * @return ��������
	 */
	public FlightState getDlyState();
	
	/**
	 * 
	 * @return ����ȡ��
	 */
	public FlightState getCnlState();
	
	
	/**
	 * 
	 * @return  ����FPL
	 */
	public FlightState getFPLState();
	
	
}
