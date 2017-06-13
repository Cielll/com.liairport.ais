package com.lzairport.ais.dao;

import java.util.List;
import java.util.Map;

import com.lzairport.ais.dao.impl.QueryConditions;

/**
 * ͨ��DAO��ӿ�
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since	JDK1.6
 *
 */



public interface IDao <K,E>{
	
	/**
	 * �����ݿⱣ��һ������
	 * @param E ʵ�����
	 * @return ���ر����IDֵ
	 */
	public E save(Object obj);
	
	
	/**
	 * �����ݿ���ɾ��һ�������Ķ���
	 * @param E ʵ�����
	 */
	public void delete(Object entity);
	
	/**
	 * ����һ������ķ���
	 * @param E ʵ����
	 */
	public E update(Object entity);
	
	
	/**
	 * �����ݿ��з��ظ���һ��ID��ʵ�����
	 * @param id	������ID
	 * @return	��ѯ����ʵ�����
	 */
	public E findById(Object id);
	
	/**
	 * �����ݿⷵ�ظ�һ���ض�ʵ��������������
	 * @return	���еĸ���ʵ��������͵���������
	 */
	public List<E> getAll();
	
	/**
	 * ��ȡDao���������ʵ������ʵ���������
	 * @return	ʵ������ʵ���������
	 */
	public  Class<E> getModelClass();
	
	/**
	 * �����ݿⷵ��һ������Name��ʵ�����
	 * @param name ������name
	 * @return ��ѯ����ʵ�����
	 */
	public E findByName(Object[] expresstion);
	
	
	/**
	 * ���ݸ������ֶ��������������һ��ʵ�����
	 * @param field �������ֶ���
	 * @param value	ָ��������
	 * @return ����������һ��ʵ�����
	 */
	public E findByFieldSingle(String field,Object value);
	
	
	
	/**
	 * ���ݸ������ֶ��������������ʵ����󼯺�
	 * @param field �������ֶ���
	 * @param value	ָ��������
	 * @return ����������ʵ����󼯺�
	 */
	public List<E> findByFieldAll(String field,Object value);

	
	
	/**
	 * ���ݸ��������������������ʵ����󼯺�
	 * @param  conditions ���������
	 * @return ����������һ��ʵ�����
	 */
	public E findByConditionSingle(QueryConditions conditions);
	
	/**
	 ���ݸ��������������������ʵ����󼯺�
	 * @param  conditions ���������
	 * @return ����������ʵ����󼯺�
	 */
	public List<E> findByConditionAll(QueryConditions conditions);
	
	/**
	 * 
	 * @Description: TODO ���ݸ�����������ȡ��������������
	 * @param conditions ���������
	 * @return ��ȡ�����������������
	 */
	public int findCountByCondition(QueryConditions conditions);



	/**
	 * 
	 * @Description: TODO ���ݸ������������оۺϴ���
	 * @param conditions ���������
	 * @return �ۺϵĽ��
	 */
	public List<Map<String,Object>> findAggregationByCondition(QueryConditions conditions) throws Exception;
}
