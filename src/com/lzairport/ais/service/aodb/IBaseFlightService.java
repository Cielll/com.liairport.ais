package com.lzairport.ais.service.aodb;

import java.util.Date;
import java.util.Set;

import com.lzairport.ais.exception.FlightServiceException;
import com.lzairport.ais.models.aodb.BaseFlight;
import com.lzairport.ais.models.aodb.StopFlight;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.utils.SYS_VARS.RouteType;

/**
 * ��������Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 09/05/15
 * @param <E>
 * @since JDK 1.6
 *
 */

public interface IBaseFlightService<K, E extends BaseFlight >  extends IService<K, E> {
	
	/**
	 * ��������ľ�ͣ��·
	 * @param flight ����
	 * @param stops ��ͣ�б�
	 */
	public Set<? extends StopFlight> createStopFlights(BaseFlight flight, Set<? extends StopFlight> stops);
	
	
	
	/**
	 * ɾ��������Ϣ
	 * @param flight ����ʵ����
	 * @throws FlightServiceException
	 */
	public void flightDel(BaseFlight flight) throws FlightServiceException;
	
	/**
	 * �ӿ�ʼ����ת������  
	 * ���ڼƻ�---�ƻ�
	 * �ƻ�----��̬
	 * ��̬----��ʷ
	 * @param StartDate
	 * @throws Exception 
	 */
	public void convertFlights(Date startDate,Boolean cover,Boolean forcedImport) throws Exception;
	
	/**
	 * 
	 * @Description: TODO ��ȡ���ߵ�����
	 */
	public RouteType getRouteType(BaseFlight flight);
	
	/**
	 * 
	 * @Description: TODO  ���º���ĺϳɺ����
	 * @param flight ����ʵ��
	 * @return �ϳɵĺ���� 
	 * ���� CA1859/CA1860
	 */
	public String getBigFlightNo(BaseFlight flight);

}
