package com.lzairport.ais.models.statistics.forecast;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.lzairport.ais.models.IntIdEntity;
import com.lzairport.ais.models.aodb.Airlines;

/**
 * 
 * 
 * FileName      BenchmarkMonth.java
 * @Description  TODO ��׼�����µ�ʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��15��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��15��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Entity
public class BenchmarkMonth extends IntIdEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static String YEAR              = "year";
	public static String MONTH             = "month";
	public static String AIRLINES          = "airlines"; 
	public static String CRAFTCODE         = "craftCode"; 
	public static String COUNTFLT          = "countFlt"; 
	public static String AVGSEAT           = "avgSeat"; 
	public static String INRATE            = "inRate";
	public static String OUTRATE           = "outRate";
	public static String LOCALRATE         = "localRate";
	public static String AVGINCARGO        = "avgInCargo";
	public static String AVGOUTCARGO       = "avgOutCargo";
	
	/**
	 *  �������ֶ�
	 */
	
	
	/**
	 * Ԥ�����
	 */
	@Column(length=4)
	private String year;
	
	/**
	 *  �·�
	 */
	private int month;

	/**
	 * ���չ�˾
	 */
	@ManyToOne
	private Airlines airlines;
	
	/**
	 * Ԥ�����
	 */
	@Column(length=4)
	private String craftCode;
	
	/**
	 * �ܴ�
	 */
	private int countFlt;
	
	/**
	 *   ��λ��
	 */
	private int avgSeat;
	

	/**
	 * ���ۿ�����
	 */
	private double inRate;
	/**
	 *  ���ۿ�����
	 */
	private double outRate;
	/**
	 * ���س����ÿ�����
	 */
	private double localRate;
	
	/**
	 *   ƽ�����ۻ���
	 */
	private int avgInCargo;
	
	/**
	 * ƽ�����ۻ���
	 */
	private int avgOutCargo;

	
	/**
	 *  ����ǰ��չʾ���ֶ�
	 */
	@Transient
	private String airlinesName;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Airlines getAirlines() {
		return airlines;
	}

	public void setAirlines(Airlines airlines) {
		this.airlines = airlines;
	}

	

	public String getCraftCode() {
		return craftCode;
	}

	public void setCraftCode(String craftCode) {
		this.craftCode = craftCode;
	}

	public int getCountFlt() {
		return countFlt;
	}

	public void setCountFlt(int countFlt) {
		this.countFlt = countFlt;
	}

	public int getAvgSeat() {
		return avgSeat;
	}

	public void setAvgSeat(int avgSeat) {
		this.avgSeat = avgSeat;
	}

	public double getInRate() {
		return inRate;
	}

	public void setInRate(double inRate) {
		this.inRate = inRate;
	}

	public double getOutRate() {
		return outRate;
	}

	public void setOutRate(double outRate) {
		this.outRate = outRate;
	}

	public double getLocalRate() {
		return localRate;
	}

	public void setLocalRate(double localRate) {
		this.localRate = localRate;
	}

	public int getAvgInCargo() {
		return avgInCargo;
	}

	public void setAvgInCargo(int avgInCargo) {
		this.avgInCargo = avgInCargo;
	}

	public int getAvgOutCargo() {
		return avgOutCargo;
	}

	public void setAvgOutCargo(int avgOutCargo) {
		this.avgOutCargo = avgOutCargo;
	}

	public String getAirlinesName() {
		if (this.getAirlines() != null){
			return this.getAirlines().getCnShortName();
		}else{
			return null;
		}
	}

	
	
	
}
