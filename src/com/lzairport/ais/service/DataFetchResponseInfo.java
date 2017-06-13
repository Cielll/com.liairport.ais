package com.lzairport.ais.service;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * FileName      DataFetchResponseInfo.java
 * @Description  �����洢���ص�Response���ݵ��࣬�����м�¼���Ϻͼ�¼����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015��10��6�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015��10��6��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

public class DataFetchResponseInfo implements Serializable {

	/**  
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)   
	 */   
	private static final long serialVersionUID = 1L;
	
	private int totalRows;
	
	private List<?> matchingObjects;

	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @return the matchingObjects
	 */
	public List<?> getMatchingObjects() {
		return matchingObjects;
	}

	/**
	 * @param matchingObjects the matchingObjects to set
	 */
	public void setMatchingObjects(List<?> matchingObjects) {
		this.matchingObjects = matchingObjects;
	}		
	
	

}
