package com.lzairport.ais.models.statistics;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * FileName      ChartParams.java
 * @Description  ͼ��Ĳ���
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��4��14�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��4��14��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@MappedSuperclass
public class ChartParams  implements Serializable {
	
	/**  
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)   
	 */   
	private static final long serialVersionUID = 1L;
	
	/**
	 *  ��ʼʱ��
	 */
	private Date startDate;
	
	/**
	 *  ����ʱ��
	 */
	private Date endDate;
	
	/**
	 *  ͼ������(Pie,Bar,Line)
	 */
	private String chartType;
	
	/**
	 * ͳ������(Pax,Goods,Flt,Rate)
	 */
	private String statisticsType;
	
	/**
	 *  ����1
	 */
	private String group_1;
	
	/**
	 *  ����2
	 */
	private String group_2;
	
	/**
	 *  ����3
	 */
	private String group_3;
	
	/**
	 *  �ϲ���ֵ
	 */
	private int limitValue;
	
	/**
	 *  ͬ�ȱ�־
	 */
	private boolean compare;
	
	/**
	 *  Ԥ���־
	 */
	private boolean forecast;
	
	/**
	 *  ��������
	 */
	private int groupCount;
	
	
	/**
	 *  ��������  ������Ϊ���ݿ��ֶ�
	 */
	@Transient
	private List<LimitCondistion> condistions;

	/**
	 * @return the startDate
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  	
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  	
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the statisticsType
	 */
	public String getStatisticsType() {
		return statisticsType;
	}

	/**
	 * @param statisticsType the statisticsType to set
	 */
	public void setStatisticsType(String statisticsType) {
		this.statisticsType = statisticsType;
	}

	/**
	 * @return the group_1
	 */
	public String getGroup_1() {
		return group_1;
	}

	/**
	 * @param group_1 the group_1 to set
	 */
	public void setGroup_1(String group_1) {
		this.group_1 = group_1;
	}

	/**
	 * @return the group_2
	 */
	public String getGroup_2() {
		return group_2;
	}

	/**
	 * @param group_2 the group_2 to set
	 */
	public void setGroup_2(String group_2) {
		this.group_2 = group_2;
	}

	/**
	 * @return the group_3
	 */
	public String getGroup_3() {
		return group_3;
	}

	/**
	 * @param group_3 the group_3 to set
	 */
	public void setGroup_3(String group_3) {
		this.group_3 = group_3;
	}

	/**
	 * @return the limitValue
	 */
	public int getLimitValue() {
		return limitValue;
	}

	/**
	 * @param limitValue the limitValue to set
	 */
	public void setLimitValue(int limitValue) {
		this.limitValue = limitValue;
	}

	/**
	 * @return the compare
	 */
	public boolean isCompare() {
		return compare;
	}

	/**
	 * @param compare the compare to set
	 */
	public void setCompare(boolean compare) {
		this.compare = compare;
	}

	/**
	 * @return the condistions
	 */
	public List<LimitCondistion> getCondistions() {
		return condistions;
	}

	/**
	 * @param condistions the condistions to set
	 */
	public void setCondistions(List<LimitCondistion> condistions) {
		this.condistions = condistions;
	}

	/**
	 * @return the chartType
	 */
	public String getChartType() {
		return chartType;
	}

	/**
	 * @param chartType the chartType to set
	 */
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	/**
	 * @return the forecast
	 */
	public boolean isForecast() {
		return forecast;
	}

	/**
	 * @param forecast the forecast to set
	 */
	public void setForecast(boolean forecast) {
		this.forecast = forecast;
	}

	/**
	 * @return the groupCount
	 */
	public int getGroupCount() {
		return groupCount;
	}

	/**
	 * @param groupCount the groupCount to set
	 */
	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	
	
	
	

}
