package com.lzairport.ais.models.settlement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.lzairport.ais.models.DefaultEntity;
import com.lzairport.ais.models.aodb.Airlines;


/**
 * 
 * FileName      BoardingCard.java
 * @Description  TODO �ǻ����շѱ�׼
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��7��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
@Entity
public class BoardingCard extends DefaultEntity implements Serializable{
	
	/**  
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)   
	 */   
	private static final long serialVersionUID = 1L;

	/**
	 * ���������ֶ���,����ֱ�ӵ����ֶ���
	 */
	public static String ID = "id";
	public static String AIRLINES = "airlines";
	public static String PRICE1 = "price1";
	public static String PRICE2 = "price2";
	public static String PRICE3 = "price3";
	public static String PRICE4 = "price4";
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="airlines") 
	private Airlines airlines;
	
	/**
	 *   Transient��ע���ֶ�������ǰ�˿�ܽ��н�����ʾ����������Ϊ�����ֶ�
	 */
	
	@Transient
	public String airlinesName;
	
	
	/**
	 *   50�������շѱ�׼
	 */
	private int price1;
	
	/**
	 *  50-100���շѱ�׼
	 */
	private int price2;
	
	/**
	 *  100-200���շѱ�׼
	 */
	private int price3;
	
	/**
	 * 200������
	 */
	private int price4;
	
	

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
	 * @return the airlines
	 */
	public Airlines getAirlines() {
		return airlines;
	}

	/**
	 * @param airlines the airlines to set
	 */
	public void setAirlines(Airlines airlines) {
		this.airlines = airlines;
	}

	/**
	 * @return the price1
	 */
	public int getPrice1() {
		return price1;
	}

	/**
	 * @param price1 the price1 to set
	 */
	public void setPrice1(int price1) {
		this.price1 = price1;
	}

	/**
	 * @return the price2
	 */
	public int getPrice2() {
		return price2;
	}

	/**
	 * @param price2 the price2 to set
	 */
	public void setPrice2(int price2) {
		this.price2 = price2;
	}

	/**
	 * @return the price3
	 */
	public int getPrice3() {
		return price3;
	}

	/**
	 * @param price3 the price3 to set
	 */
	public void setPrice3(int price3) {
		this.price3 = price3;
	}

	
	
	public int getPrice4() {
		return price4;
	}

	public void setPrice4(int price4) {
		this.price4 = price4;
	}

	/**
	 * @return the airlinesName
	 */
	public String getAirlinesName() {
		if (this.airlines != null){
			return this.airlines.getCnShortName();
		}else{
			return null;
		}
	}

	/**
	 * @param airlinesName the airlinesName to set
	 */
	public void setAirlinesName(String airlinesName) {
		this.airlinesName = airlinesName;
	}

	
	
}
