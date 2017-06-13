package com.lzairport.ais.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * ʵ�������ʵ����
 * �������ʵ�����仯�����ʵ���ˢ��
 * @author ZhangYu
 * @version 0.9a 27/06/14
 * @since JDK1.6
 *
 */

@Entity
public class ModelRelation extends DefaultEntity implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * ���������ֶ���,����ֱ�ӵ����ֶ���
	 */
	public static String ID ="id";
	
	public static String MODELNAME = "modelName";
	
	public static String RELATIONMODELNAME = "relationModelName";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;		
	
	private String modelName;
	
	private String relationModelName;

	
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Object id) {
		// TODO Auto-generated method stub
		this.id = (Integer) id;
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the relationModelName
	 */
	public String getRelationModelName() {
		return relationModelName;
	}

	/**
	 * @param relationModelName the relationModelName to set
	 */
	public void setRelationModelName(String relationModelName) {
		this.relationModelName = relationModelName;
	}

}
