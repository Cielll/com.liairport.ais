

package com.lzairport.ais.dao.impl;

import java.io.Serializable;
import java.util.List;
import com.lzairport.ais.models.statistics.AggregationColumnField;
import com.lzairport.ais.models.statistics.GroupField;


/**
 * 
 * ��ѯ������
 * @author ZhangYu
 * version 0.9a 28/07/14
 * @since JDK 1.6
 * 
 */

public class QueryConditions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * �������ʽ
	 */
	private Object[] expresstion;
	
	/**
	 *  �����ֶ�
	 */
	private AisOrder[] orders;
	
	
	/**
	 *  ��ҳ��һ����¼���
	 */
	private int first;
	
	/**
	 * ��ҳ�����
	 */
	private int max;

	/**
	 *  ����һ�Զ�ı�ʶ��
	 */
	private String fetchOneToMany;
	
	/**
	 *  ���Ӷ��һ�ı�ʶ��
	 */
	private String fetchManyToOne;
	
	
	/**
	 *  �����ֶ�
	 */
	private List<GroupField> groupFields;
	
	/**
	 *  �ۺ��ֶ�
	 */
	private List<AggregationColumnField> aggregationFields;
	
	
	
	
	

	
	
	
	/**
	 * @return the expresstion
	 */
	public Object[] getExpresstion() {
		return expresstion;
	}
	
	
	

	/**
	 * @param expresstion the expresstion to set
	 */
	public void setExpresstion(Object[] expresstion) {
		this.expresstion = expresstion;
	}

	




	/**
	 * @return the orders
	 */
	public AisOrder[] getOrders() {
		return orders;
	}




	/**
	 * @param orders the orders to set
	 */
	public void setOrders(AisOrder[] orders) {
		this.orders = orders;
	}




	/**
	 * @return the first
	 */
	public int getFirst() {
		return first;
	}




	/**
	 * @param first the first to set
	 */
	public void setFirst(int first) {
		this.first = first;
	}




	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}




	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}




	/**
	 * @return the fetchOneToMany
	 */
	public String getFetchOneToMany() {
		return fetchOneToMany;
	}




	/**
	 * @param fetchOneToMany the fetchOneToMany to set
	 */
	public void setFetchOneToMany(String fetchOneToMany) {
		this.fetchOneToMany = fetchOneToMany;
	}




	/**
	 * @return the fetchManyToOne
	 */
	public String getFetchManyToOne() {
		return fetchManyToOne;
	}




	/**
	 * @param fetchManyToOne the fetchManyToOne to set
	 */
	public void setFetchManyToOne(String fetchManyToOne) {
		this.fetchManyToOne = fetchManyToOne;
	}




	/**
	 * @return the groupFields
	 */
	public List<GroupField> getGroupFields() {
		return groupFields;
	}




	/**
	 * @param groupFields the groupFields to set
	 */
	public void setGroupFields(List<GroupField> groupFields) {
		this.groupFields = groupFields;
	}




	/**
	 * @return the aggregationFields
	 */
	public List<AggregationColumnField> getAggregationFields() {
		return aggregationFields;
	}




	/**
	 * @param aggregationFields the aggregationFields to set
	 */
	public void setAggregationFields(List<AggregationColumnField> aggregationFields) {
		this.aggregationFields = aggregationFields;
	}

	
}
