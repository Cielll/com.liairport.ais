package com.lzairport.ais.service.statistics;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.ForecastRoute;
import com.lzairport.ais.service.IService;



/**
 * 
 * FileName      IForecastRouteService.java
 * @Description  TODO Ԥ�⺽�ߵ�Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��7��1�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��7��1��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
@Remote
public interface IForecastRouteService extends IService<Integer, ForecastRoute> {

	/**
	 * 
	 * @Description: TODO ��ȡ���е����亽��
	 * @return ���غ��߲�Ϊ�յ����亽��
	 */
	public List<ForecastRoute> getAllCarriages();
	
	
	/**
	 * 
	 * @Description: TODO ����ָ���ĺ��ߴ���Ԥ�⺽������
	 * @param routeHX ָ���ĺ���
	 * @return ������Ԥ�⺽��
	 */
	public ForecastRoute createRoute(String year,String routeHX);
	
	/**
	 * 
	 * @Description: TODO ����map��ֵ����ʵ�ʵ�����
	 * @param map ����ʵ�����ݵ�map
	 */
	
	/**
	 * 
	 * @Description: TODO ����map��ֵ����month֮ǰ��ʵ������
	 * @param route   Ԥ�⺽��
	 * @param map     ����ʵ�����ݵ�map
	 * @param month   �·ݽ���
	 */
	public void actualToForecast(ForecastRoute route,Map<String, Object> map,int eMonth);

	/**
	 * 
	 * @Description: TODO ���·���eMonthǰ��ʵ�����ݴ���Ԥ������
	 * @param route
	 * @param eMonth
	 */
	public void actualReplaceForecast(ForecastRoute route,int eMonth);
	
	
	/**
	 * 
	 * @Description: TODO ��ȡָ�����ȵ�����֮��
	 * @param route  Ԥ�⺽��
	 * @param quarter ָ������
	 * @param field   ָ���ֶ� 
	 * @return ����֮��
	 */
	public int  getQuarterSumValue(ForecastRoute route,int quarter,String field);
	
	/**
	 * 
	 * @Description: TODO ��ȡָ�����ȵ�����ƽ��ֵ
	 * @param route       Ԥ�⺽��
	 * @param quarter     ָ������
	 * @param field       ָ���ֶ�
	 * @return            ����ƽ��ֵ
	 */
	public double  getQuarterAvgValue(ForecastRoute route,int quarter,String field);
}
