package com.lzairport.ais.models.statistics;

import com.lzairport.ais.utils.SYS_VARS.SummaryType;

/**
 * 
 * FileName      DisplayField.java
 * @Description  ���ڱ�����ʾ���ֶ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��3��4�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��3��4��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
public class DisplayField {
	
	/**
	 * ����Keyֵ
	 */
	private String  dataIndex;
	
	
	/**
	 * ���ݵı���
	 */
	private String  text;
	
	
	/**
	 * �ϼƵ�����
	 */
	private SummaryType summaryType;

	/**
	 * @return the dataIndex
	 */
	public String getDataIndex() {
		return dataIndex;
	}

	/**
	 * @param dataIndex the dataIndex to set
	 */
	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the summaryType
	 */
	public SummaryType getSummaryType() {
		return summaryType;
	}

	/**
	 * @param summaryType the summaryType to set
	 */
	public void setSummaryType(SummaryType summaryType) {
		this.summaryType = summaryType;
	}
	
	

}
