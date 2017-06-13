package com.lzairport.ais.service.statistics;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.BaseRoute;
import com.lzairport.ais.service.IService;

/**
 * 
 * FileName      IBaseRouteService.java
 * @Description  TODO Ԥ�⺽�ߵĻ�׼���ݵ�Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��7��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��7��7��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Remote
public interface IBaseRouteService extends IService<Integer, BaseRoute> {
	
	/**
	 * 
	 * @Description: TODO ����ͳ�Ʊ������ȫ���ĺ��߻�������
	 * @param rList ͳ�Ʊ���
	 */
	public void updateForReportList(List<Map<String, Object>> rList);
}
