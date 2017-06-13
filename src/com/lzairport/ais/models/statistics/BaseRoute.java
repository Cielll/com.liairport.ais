package com.lzairport.ais.models.statistics;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import com.lzairport.ais.models.DefaultEntity;
import com.lzairport.ais.utils.SYS_VARS.RouteType;

/**
 * 
 * FileName      BaseRoute.java
 * @Description  TODO Ԥ�⺽�ߵĻ�׼����ʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��7��7�� 
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2016��7��7��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
@Entity
public class BaseRoute extends DefaultEntity implements Serializable {

	/**  
	 * @Fields serialVersionUID : TODO(��һ�仰�������������ʾʲô)   
	 */   
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * ���������ֶ���,����ֱ�ӵ����ֶ���
	 */
	public static String ID = "id";
	public static String ROUTEHX = "routeHX";
	public static String ROUTETYPE ="routeType";
	public static String IRATE = "iRate";
	public static String ORATE = "oRate";
	public static String RATE  = "rate";
	public static String COUNTFLT = "countFLT";
	public static String SEAT = "seat";
	public static String PAX = "pax";

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	/**
	 *  ��������
	 */
	private String routeHX;
	/**
	 *  ���ۿ�����
	 */
	private double iRate;
	/**
	 *  ���ۿ�����
	 */
	private double oRate;
	/**
	 *  ƽ��������
	 */
	private double rate;
	/**
	 *   �ۼƼܴ�
	 */
	private int countFLT;
	/**
	 *  ��λ��
	 */
	private int seat;
	
	/**
	 *  ����
	 */
	private int pax;
	
	@Enumerated(EnumType.STRING)
	@Column(length=10)
	private RouteType routeType;

	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="baseRoute_id") 
	private Set<BaseRouteMonth> mRoutes;
	

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
	 * @return the routeHX
	 */
	public String getRouteHX() {
		return routeHX;
	}

	/**
	 * @param routeHX the routeHX to set
	 */
	public void setRouteHX(String routeHX) {
		this.routeHX = routeHX;
	}

	/**
	 * @return the iRate
	 */
	public double getiRate() {
		return iRate;
	}

	/**
	 * @param iRate the iRate to set
	 */
	public void setiRate(double iRate) {
		this.iRate = iRate;
	}

	/**
	 * @return the oRate
	 */
	public double getoRate() {
		return oRate;
	}

	/**
	 * @param oRate the oRate to set
	 */
	public void setoRate(double oRate) {
		this.oRate = oRate;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return the countFLT
	 */
	public int getCountFLT() {
		return countFLT;
	}

	/**
	 * @param countFLT the countFLT to set
	 */
	public void setCountFLT(int countFLT) {
		this.countFLT = countFLT;
	}

	/**
	 * @return the seat
	 */
	public int getSeat() {
		return seat;
	}

	/**
	 * @param seat the seat to set
	 */
	public void setSeat(int seat) {
		this.seat = seat;
	}

	/**
	 * @return the routeType
	 */
	public RouteType getRouteType() {
		return routeType;
	}

	/**
	 * @param routeType the routeType to set
	 */
	public void setRouteType(RouteType routeType) {
		this.routeType = routeType;
	}

	/**
	 * @return the mRoutes
	 */
	public Set<BaseRouteMonth> getmRoutes() {
		return mRoutes;
	}

	/**
	 * @param mRoutes the mRoutes to set
	 */
	public void setmRoutes(Set<BaseRouteMonth> mRoutes) {
		this.mRoutes = mRoutes;
	}

	/**
	 * @return the pax
	 */
	public int getPax() {
		return pax;
	}

	/**
	 * @param pax the pax to set
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}





	

}
