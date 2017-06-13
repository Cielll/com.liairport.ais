package com.lzairport.ais.service.aodb;

import java.util.Date;
import java.util.Set;

import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.exception.FlightServiceException;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.FlightDisPatch;
import com.lzairport.ais.models.aodb.FlightLoad;
import com.lzairport.ais.models.aodb.PayingPassenger;
import com.lzairport.ais.service.DataFetchResponseInfo;
import com.lzairport.ais.vo.FlightVO;

/**
 * 
 * FileName      ����Service�ӿڣ������������ĸ��ֻ�������
 * @Description  TODO(��һ�仰�������ļ���ʲô)
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 12/11/14
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015-9-21      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
public interface IFlightService<K,E extends Flight> extends IBaseFlightService<K, E>{

	
	/**
	 * 
	 * @Description: ����EXTJSҳ������Ҫ��Response����
	 * @param conditions ����
	 * @return DataFetchResponseInfo���� 
	 * @throws Exception
	 */
	public DataFetchResponseInfo fetchByCondition(QueryConditions conditions) throws Exception;
	
	/**
	 * ����һ������
	 * @param flight ����ʵ����
	 * @throws Exception 
	 * @throws FlightServiceException
	 */
	public E createFlight(Flight flight) throws Exception ;
	
	
	
	/**
	 * ���ݺ��ഴ���ú�������е��Ȼ���
	 * @param flight ����ʵ����
	 * @throws FlightServiceException 
	 */
	public void createFlightDisPatchs(Flight flight) throws FlightServiceException;
	
	/**
	 * 
	 * @Description: ����disPatchs��������ķ��񻷽�
	 * @param flight ����ʵ��
	 * @param disPatchs ���񻷽ڼ���
	 * @throws FlightServiceException 
	 */
	public void copyFlightDisPatchs(Flight flight, Set<? extends FlightDisPatch> scrDispatchs ) throws FlightServiceException;
	
	
	/**
	 * 
	 * @Description: ����passengers��������ĸ����ÿ���Ϣ
	 * @param flight ����ʵ��
	 * @param passengers �����ÿͼ���
	 * @throws FlightServiceException 
	 */
	public void copyFlightPayingPassengers(Flight flight, Set<? extends PayingPassenger> scrPassengers) throws FlightServiceException;
	
	
	/**
	 * 
	 * @Description: ����scrLoads����������������
	 * @param flight ����ʵ��
	 * @param scrLoads �������ݼ���
	 * @throws FlightServiceException 
	 */
	public void copyFlightLoads(Flight flight,Set<? extends FlightLoad> scrLoads) throws FlightServiceException;

	
	/**
	 * �Ӻ��࿪ʼ���ڽ�����������Ӧ�Ĺ���
	 * @param startDate  �������࿪ʼ����
	 */
	public void linkFlights(Date startDate) throws  FlightServiceException;
	
	
	/**
	 * �ҵ�����Ĺ�������
	 * @param linkFlight ���������ַ���
	 * @return ���غ���Ĺ�������
	 */
	public E getLinkFlight(Flight flight);
	
	
	/**
	 * 
	 * @Description: TODO �ҵ������ǰ�κ���
	 * @return ���غ����ǰ�κ���
	 */
	public E getPreviousFlight(E flight);
	
	
	/**
	 * 
	 * @Description: TODO ������ʵ����ת��ΪFlightVO
	 * @param flight ����ʵ����
	 * @return
	 * @throws Exception 
	 */
	public FlightVO toFlightVO(E flight) throws Exception;
	
	
	/**
	 * 
	 * @Description: TODO �ж�һ�������Ƿ��������
	 * @param flight ����ʵ����
	 * @return �Ƿ�
	 */
	public boolean checkClearanceNormal(E flight);
	
	

	
}
