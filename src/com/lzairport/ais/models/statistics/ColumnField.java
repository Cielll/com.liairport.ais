package com.lzairport.ais.models.statistics;


/**
 * 
 * FileName      ColumnField.java
 * @Description  ���ֶεĳ����࣬����ʵ�ʵ����ֶζ��̳���
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��2��25�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��2��25��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
public abstract class ColumnField extends ReportField {

	/**  
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)   
	 */   
	private static final long serialVersionUID = 1L;
	
	public static String EXPRESSTION = "Expresstion";
	/**
	 * �ۺϱ��ʽ��ֻ����+,-���ߵ���
	 */
	protected Object[] expresstion;
	
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


}