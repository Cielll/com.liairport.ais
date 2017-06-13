package com.lzairport.ais.service;

import java.util.List;
import java.util.Map;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.dao.impl.QueryConditions;


/**
 * ͨ�õ�Service��ӿڣ��ṩ������ʵ�����������
 * @author ZhangYu
 * @since JDK1.6
 * @version 0.9a 24/06/2014	
 */

public interface IService<K,E extends Object>  {
	
	
	/**
	 * ��ȡService���������ʵ������ʵ���������
	 * @return	ʵ������ʵ���������
	 */
	public  Class<E> getModelClass();
	
	/**
	 * ���ò������ݵ�Dao����
	 * @param dao
	 */
	public void setDao(IDao<K, E> dao);	
	
	/**
	 * ɾ��һ�����ݶ���
	 * @param obj
	 */
	public void remove(Object obj);	
	
	/**
	 * ����һ�����ݶ���
	 * @param obj
	 */
	public E add(Object obj);
	
	/**
	 * ����һ�����ݶ���
	 * @param object
	 */
	public E update(Object object);
	
	/**
	 * �������е����ݶ���
	 * @return
	 */
	public  List <E> getAll();

	/**
	 * ����ָ��Id����������ݶ���
	 * @param id �ؼ���
	 * @return ָ��Id����������ݶ���
	 */
	public E findByID(Object id);
	
	/**
	 * ����ָ���ֶ�ֵ�õ������ݶ���
	 * @param field
	 * @param value
	 * @return ָ���ֶ�ֵ�����ݶ���
	 */
	public  List <E>  findByFieldAll(String field,Object value);

	/**
	 * ����ָ���ֶ�ֵ�����ݶ���ļ���
	 * @param field
	 * @param value
	 * @return ָ���ֶ�ֵ�����ݶ���ļ���
	 */
	public E  findByFieldSingle(String field,Object value);
	
	/**
	 * ���ݸ��������������������ʵ����󼯺�
	 * @param fields �ֶ�����
	 * @param opers ��ϵ�����
	 * @param links ���ӷ� and or
	 * @param values ��������
	 * @return ����������һ��ʵ�����
	 */
	public E  findByConditionSingle(QueryConditions conditions);
	
	/**
	 ���ݸ��������������������ʵ����󼯺�
	 * @param fields �ֶ�����
	 * @param opers ��ϵ�����
	 * @param links ���ӷ� and or
	 * @param values ��������
	 * @return ����������ʵ����󼯺�
	 */
	public  List <E>  findByConditionAll(QueryConditions conditions);
	
	/**
	 * ���ݸ�����������ȡ��������������
	 * @Description: TODO(������һ�仰�����������������)
	 * @param conditions ���������
	 * @return ��ȡ�����������������
	 */
	public int findCountByCondition(QueryConditions conditions);
	
	/**
	 * 
	 * @Description: TODO ���ݸ������������оۺϴ���
	 * @param conditions ���������
	 * @return �ۺϵĽ��
	 * @throws Exception 
	 */
	public List<Map<String,Object>> findAggregationByCondition(QueryConditions conditions) throws Exception;
	

	
	
}
