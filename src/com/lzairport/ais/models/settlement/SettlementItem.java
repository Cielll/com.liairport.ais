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
 * FileName      SettlementItem.java
 * @Description  TODO ������ϸ��
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��7��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
@Entity
public class SettlementItem extends DefaultEntity implements Serializable {

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
	public static String PRICE = "price";	
	public static String SETTLEMENTTYPE = "settlementType";
	public static String UNITNAME = "unitName";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length=20)
	private String code;
	
	@Column(length=30)
	private String name;
	
	private Double price;
	
	@ManyToOne
	private SettlementType settlementType;
	
	@Column(length=10)
	private String unitName;
	
	@Transient
	public String typeName;
	
	
	@Override
	public Object getId() {
		return this.id;
	}

	@Override
	public void setId(Object id) {
		this.id = (Integer) id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
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
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the settlementType
	 */
	public SettlementType getSettlementType() {
		return settlementType;
	}

	/**
	 * @param settlementType the settlementType to set
	 */
	public void setSettlementType(SettlementType settlementType) {
		this.settlementType = settlementType;
	}

	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		if (this.getSettlementType() != null){
			return this.getSettlementType().getName();
		}else{
			return null;
		}
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	

}
