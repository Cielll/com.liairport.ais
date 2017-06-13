package com.lzairport.ais.service.statistics.forecast;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.forecast.BenchmarkRouteHX;
import com.lzairport.ais.service.IService;


/**
 * FileName      IBenchmarkRouteHX.java
 * @Description  TODO Ԥ�⺽��Ļ�׼����Service�ӿ� 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��18��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��18��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Remote
public interface IBenchmarkRouteHXService extends IService<Integer, BenchmarkRouteHX> {
	
	
	/**
	 * @Description: TODO ����ͳ�Ʊ������ȫ���ĺ��߻�������
	 * @param rList ͳ�Ʊ���
	 * @param year  ָ�������
	 */
	public void updateForReportList(List<Map<String, Object>> rList,String year);
	
}
