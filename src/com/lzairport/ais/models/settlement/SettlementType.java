package com.lzairport.ais.models.settlement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.lzairport.ais.models.DefaultEntity;


/**
 * 
 * FileName      SettlementType.java
 * @Description  TODO �շ���Ŀ
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��7��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Entity
public class SettlementType extends DefaultEntity implements Serializable {

	/**  
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)   
	 */   
	private static final long serialVersionUID = 1L;
	
	/**
	 * ���������ֶ���,����ֱ�ӵ����ֶ���
	 */
	public static String ID = "id";
	public static String CODE = "code";
	public static String NAME = "name";
	public static String UNIT="unit";
	public static String CATEGORY="category";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	/**
	 * ��Ŀ����
	 */
	@Column(length=30)
	private String name;

	
	
	
	
	private Double unit;
	
	/**
	 *   ��Ӧ�������ߴ���
	 */
	private int code;
	
	@ManyToOne
	private SettlementCategory category;
	
	@Transient
	public String categoryName;
	

	@Override
	public Object getId() {
		return this.id;
	}

	@Override
	public void setId(Object id) {
		this.id = (Integer) id;
	}



	/**
	 * @return the unit
	 */
	public Double getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(Double unit) {
		this.unit = unit;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the category
	 */
	public SettlementCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(SettlementCategory category) {
		this.category = category;
	}
	
	/**
	 *   Transient��ע���ֶ�������ǰ�˿�ܽ��н�����ʾ����������Ϊ�����ֶ�
	 */
	
	/**
	 * @return the settlementTypeName
	 */
	public String getCategoryName() {
		if (this.category != null){
			return this.category.getName();
		}else{
			return null;
		}
	}

	/**
	 * @param settlementTypeName the settlementTypeName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
