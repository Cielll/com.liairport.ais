package com.lzairport.ais.models.statistics.forecast;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.lzairport.ais.models.IntIdEntity;
import com.lzairport.ais.models.aodb.AreaAttribute;

/**
 * FileName      ForecastRouteHX.java
 * @Description  TODO Ԥ�⺽��ʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��15��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��15��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Entity
public class ForecastRouteHX extends IntIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static String ID        = "id";
	public static String ROUTEHX   = "routeHX";
	public static String FMFLIGHTS = "fmFlights";
	public static String TRANSFER  = "transfer";
	public static String ATTRIBUTE = "attribute";
	
	
	/**
	 * ����
	 */
	@Column(length=20)
	private String routeHX;
	
	/**
	 *  �Ƿ�����ת����
	 */
	private boolean transfer;

	@ManyToOne
	private AreaAttribute attribute; 
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="forecastRouteHX_id")
	private Set<ForecastMonthFlight> fmFlights; 


	public String getRouteHX() {
		return routeHX;
	}

	public void setRouteHX(String routeHX) {
		this.routeHX = routeHX;
	}
	
	

	public boolean isTransfer() {
		return transfer;
	}

	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}

	public AreaAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(AreaAttribute attribute) {
		this.attribute = attribute;
	}

	public Set<ForecastMonthFlight> getFmFlights() {
		return fmFlights;
	}

	public void setFmFlights(Set<ForecastMonthFlight> fmFlights) {
		this.fmFlights = fmFlights;
	}
	
	
	
	
	

}

