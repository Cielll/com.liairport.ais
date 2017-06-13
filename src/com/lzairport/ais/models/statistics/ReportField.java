package com.lzairport.ais.models.statistics;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import com.lzairport.ais.models.DefaultEntity;

/**
 * 
 * FileName      ReportField.java
 * @Description  ����ͳ�Ʒ��鱨���ֶεĻ���
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��2��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��2��7��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@MappedSuperclass
public class ReportField  extends  DefaultEntity implements  Serializable {
	
	/**  
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)   
	 */   
	
	private static final long serialVersionUID = 1L;


	public static String STREXPRESSTION="strExpresstion";
	
	public static String ALIAS="alias";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	
		
	
	private String strExpresstion;

	private String alias;
	



	
	
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Object id) {
		// TODO Auto-generated method stub
		if (id != null){
			this.id = (Integer) id;
		}
	}


	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}


	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the strExpresstion
	 */
	public String getStrExpresstion() {
		return strExpresstion;
	}

	/**
	 * @param strExpresstion the strExpresstion to set
	 */
	public void setStrExpresstion(String strExpresstion) {
		this.strExpresstion = strExpresstion;
	}


	
}
