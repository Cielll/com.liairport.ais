package com.lzairport.ais.models.telex;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.lzairport.ais.models.DefaultEntity;

/**
 * �籨�������������ʵ��
 * ��:SITA��AFTN��ϵͳ���ܱ���
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK1.6
 */


@Entity
public class AllowType extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * ���������ֶ���,����ֱ�ӵ����ֶ���
	 */
	public static String ID="id";
	
	public static String NAME="name";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;		

	private String name;
	
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
		// TODO Auto-generated method stub
		return name;
	}


}
