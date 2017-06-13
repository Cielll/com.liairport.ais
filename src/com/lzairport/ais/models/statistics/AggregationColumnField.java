package com.lzairport.ais.models.statistics;

import com.lzairport.ais.utils.SYS_VARS.AggregationFieldType;

/**
 * 
 * FileName      AggregationColumnField.java
 * @Description  �ۺ����ֶΣ�����������Ҫ�����ݷ�����ֶΣ�ʵ����ȥ��ѯ���ݵ��ֶ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��2��27�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��2��27��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

public class AggregationColumnField extends ColumnField {


	private static final long serialVersionUID = 1L;
	
	
	
	public static String AGGREGATIONTYPE ="aggregationType";
	
	public static String COMPARE ="compare"; 
	
	public static String WHENCONDITION = "whenCondition";
	
	/**
	 * �ۺϵ�����
	 */
	private AggregationFieldType aggregationType;
	
	
	
	/**
	 * �Ƚϱ�־��ѡ��������+Compare��ֵ
	 */
	private boolean compare;
	
	
	/**
	 * �ۺ�����
	 */
	private Object[] whenCondition;
	

	/**
	 * @return the aggregationType
	 */
	public AggregationFieldType getAggregationType() {
		return aggregationType;
	}

	/**
	 * @param aggregationType the aggregationType to set
	 */
	public void setAggregationType(AggregationFieldType aggregationType) {
		this.aggregationType = aggregationType;
	}



	/**
	 * @return the compare
	 */
	public boolean getCompare() {
		return compare;
	}

	/**
	 * @param compare the compare to set
	 */
	public void setCompare(boolean compare) {
		this.compare = compare;
	}

	/**
	 * @return the whenCondition
	 */
	public Object[] getWhenCondition() {
		return whenCondition;
	}

	/**
	 * @param whenCondition the whenCondition to set
	 */
	public void setWhenCondition(Object[] whenCondition) {
		this.whenCondition = whenCondition;
	}



	

}
