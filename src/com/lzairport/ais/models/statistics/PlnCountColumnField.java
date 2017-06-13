package com.lzairport.ais.models.statistics;

import java.util.Date;

/**
 * 
 * FileName      PlnCountColumnField.java
 * @Description  �ƻ����мܴε����ֶ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��2��27�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��2��27��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
public class PlnCountColumnField extends ColumnField {

	/**  
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)   
	 */   
	private static final long serialVersionUID = 1L;
	
	/**
	 * ���ֶΣ���һ��Group�ֶ�
	 */
	private GroupField rowField;
	
	/**
	 * ��ʼʱ��
	 */
	private Date startDate;
	
	/**
	 * �����ֶ�
	 */
	private Date endDate;
	/**
	 * @return the rowField
	 */
	public GroupField getRowField() {
		return rowField;
	}

	/**
	 * @param rowField the rowField to set
	 */
	public void setRowField(GroupField rowField) {
		this.rowField = rowField;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	
}
