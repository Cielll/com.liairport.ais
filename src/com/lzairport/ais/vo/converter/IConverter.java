package com.lzairport.ais.vo.converter;

import javax.ejb.Local;

/**
 * 
 * FileName      IHisFlightConverter.java
 * @Description  ��ʷ������Ϣֵ����ͺ���ʵ��ת����Ľӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015��10��21�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015��10��21��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */


@Local
public interface IConverter<E,V>  {
	
	/**
	 * 
	 * @Description: TODO��HisFlight����ת��ΪFlightVO����
	 * @param flight ��Ҫת����HisFlight����
	 * @return FlightVO����
	 * @throws Exception
	 */
	public V getVOject(E entity) throws Exception;
	
	/**
	 * 
	 * @Description: TODO ��VO����ת��ΪHisFlight����
	 * @param flightVO ��Ҫת����FlightVO���� 
	 * @return HisFlight����
	 * @throws Exception
	 */
	public E getEntity(V vObject) throws Exception;
	
	

}