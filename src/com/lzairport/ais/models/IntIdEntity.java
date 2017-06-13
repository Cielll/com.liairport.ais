package com.lzairport.ais.models;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * FileName      IdEntity.java
 * @Description  TODO  idΪint����ʵ���ุ�� 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��19��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��19��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@MappedSuperclass
public abstract class IntIdEntity extends DefaultEntity  implements Serializable {
	
	protected static final long serialVersionUID = 1L;
	
	public static String ID           = "id";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;

	@Override
	public Object getId() {
		return this.id;
	}

	@Override
	public void setId(Object id) {
		this.id = (int) id;
	}

}
