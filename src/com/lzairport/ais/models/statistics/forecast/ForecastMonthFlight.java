package com.lzairport.ais.models.statistics.forecast;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.lzairport.ais.models.IntIdEntity;
import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.AreaAttribute;
import com.lzairport.ais.models.aodb.FlightTask;

/**
 * FileName      ForecastMonthFlight.java
 * @Description  TODO Ԥ�⺽��ÿ�µ�ʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��15��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��15��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Entity
public class ForecastMonthFlight extends IntIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String YEAR          = "year";
	public static String MONTH         = "month";
	public static String BIGFLIGHTNO   = "bigFlightNO";
	public static String CRAFTCODE     = "craftCode";
	public static String AIRLINES      = "airlines";
	public static String TASK          = "task";
	public static String ATTRIBUTE     = "attribute";
	public static String COUNTFLT      = "countFlt";
	public static String AVGSEAT       = "avgSeat";
	public static String INRATE        = "inRate";
	public static String OUTRATE       = "outRate";
	public static String LOCALRATE     = "localRate";
	public static String INCARGO       = "inCargo";
	public static String OUTCARGO      = "outCargo";
	public static String INPAX         = "inPax";
	public static String OUTPAX        = "outPax";
	public static String LOCALPAX      = "localPax";
	
	
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
	 * �󺽰��
	 */
	private String bigFlightNO;

	/**
	 * ����
	 */
	@Column(length=15)
	private String craftCode;
	
	/**
	 * ���չ�˾
	 */
	@ManyToOne
	private Airlines airlines;
	
	/**
	 * ��������
	 */
	@ManyToOne
	private FlightTask task;
	
	/**
	 *  ����
	 */
	@ManyToOne
	private AreaAttribute attribute;
	
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
	 * ���ۻ���
	 */
	private int inCargo;
	
	/**
	 * ���ۻ���
	 */
	private int outCargo;
	
	/**
	 * ��������
	 */
	private int inPax;
	
	/**
	 * ��������
	 */
	private int outPax;

	/**
	 * ���س�������
	 */
	private int localPax;
	
	


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

	public String getBigFlightNO() {
		return bigFlightNO;
	}

	public void setBigFlightNO(String bigFlightNO) {
		this.bigFlightNO = bigFlightNO;
	}

	public String getCraftCode() {
		return craftCode;
	}

	public void setCraftCode(String craftCode) {
		this.craftCode = craftCode;
	}

	public Airlines getAirlines() {
		return airlines;
	}

	public void setAirlines(Airlines airlines) {
		this.airlines = airlines;
	}

	public FlightTask getTask() {
		return task;
	}

	public void setTask(FlightTask task) {
		this.task = task;
	}

	public AreaAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(AreaAttribute attribute) {
		this.attribute = attribute;
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

	public int getInCargo() {
		return inCargo;
	}

	public void setInCargo(int inCargo) {
		this.inCargo = inCargo;
	}

	public int getOutCargo() {
		return outCargo;
	}

	public void setOutCargo(int outCargo) {
		this.outCargo = outCargo;
	}

	public int getInPax() {
		return inPax;
	}

	public void setInPax(int inPax) {
		this.inPax = inPax;
	}

	public int getOutPax() {
		return outPax;
	}

	public void setOutPax(int outPax) {
		this.outPax = outPax;
	}

	public int getLocalPax() {
		return localPax;
	}

	public void setLocalPax(int localPax) {
		this.localPax = localPax;
	}
	
	

}
