package com.lzairport.ais.models.statistics;

import com.lzairport.ais.utils.SYS_VARS.GrpDate;

/**
 * 
 * FileName      GroupField.java
 * @Description  �����ֶΣ����ڱ����ֶε��У�����GroupBy��ͳ�ƣ����ʽ�Ĳ���ֻ����һ���ֶ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��2��8�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��2��8��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */



public class GroupField extends ReportField {

	/**  
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)   
	 */   
	private static final long serialVersionUID = 1L;
	
	public static String EXPRESSTION ="Expresstion";
	
	public static String GRPDATE ="grpDate";
	
	/**
	 * ���ʽ
	 */
	private Object expresstion;
	
	
	/**
	 * ��������
	 */
	private GrpDate grpDate;
	

	/**
	 * @return the expresstion
	 */
	public Object getExpresstion() {
		return expresstion;
	}

	/**
	 * @param expresstion the expresstion to set
	 */
	public void setExpresstion(Object expresstion) {
		this.expresstion = expresstion;
	}

	/**
	 * @return the grpDate
	 */
	public GrpDate getGrpDate() {
		return grpDate;
	}

	/**
	 * @param grpDate the grpDate to set
	 */
	public void setGrpDate(GrpDate grpDate) {
		this.grpDate = grpDate;
	}

}
