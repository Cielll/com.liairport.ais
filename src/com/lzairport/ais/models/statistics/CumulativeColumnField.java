package com.lzairport.ais.models.statistics;



/**
 * 
 * FileName      CumulativeColumnField.java
 * @Description  �����ۼƼ�������ֶ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��2��27�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��2��27��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
public class CumulativeColumnField extends ColumnField {

	/**  
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)   
	 */   
	private static final long serialVersionUID = 1L;
	
	/**
	 * ��Ҫ��ʲô���ݽ����ۼ�
	 */
	private AggregationColumnField aggregationColumnField;
	
	
	/**
	 * �޶������
	 */
	private String year;
	
	/**
	 * �Ƿ���Ҫ���бȽ�
	 */
	private boolean compare;

	/**
	 * ������������µıȽϵı�ʶ
	 */
	private String flag;

	/**
	 * @return the aggregationColumnField
	 */
	public AggregationColumnField getAggregationColumnField() {
		return aggregationColumnField;
	}

	/**
	 * @param aggregationColumnField the aggregationColumnField to set
	 */
	public void setAggregationColumnField(AggregationColumnField aggregationColumnField) {
		this.aggregationColumnField = aggregationColumnField;
	}

	/**
	 * @return the compare
	 */
	public Boolean getCompare() {
		return compare;
	}

	/**
	 * @param compare the compare to set
	 */
	public void setCompare(Boolean compare) {
		this.compare = compare;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	
	

}
