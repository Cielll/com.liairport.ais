package com.lzairport.ais.models.settlement;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.lzairport.ais.models.DefaultEntity;

/**
 * 
 * FileName      SettlementCategory.java
 * @Description  TODO�������
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��12��24�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��12��24��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */



@Entity
public class SettlementCategory extends DefaultEntity implements Serializable {
	
	/**  
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)   
	 */   
	private static final long serialVersionUID = 1L;
	
	public static String ID = "id";
	public static String NAME = "name";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Override
	public Object getId() {
		return this.id;
	}

	@Override
	public void setId(Object id) {
		this.id = (Integer) id;
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

	@Override
	public String toString() {
		return this.name;
	}
	
	
	

}
