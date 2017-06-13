package com.lzairport.ais.models.aodb;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import com.lzairport.ais.models.DefaultEntity;
import com.lzairport.ais.utils.SYS_VARS.OutIn;



/**
 * ���������,��̬���ƻ�����ʷ���̳���,����һЩ������ĺ�������
 * @author ZhangYu
 * @version 0.9a 16/08/14
 * @since JDK 1.6
 *
 */

@MappedSuperclass
public abstract class BaseFlight extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ���������ֶ���,�������õ����ֶ���
	 */	
	public static String ARRAIRPORT = "arrAirport";
	public static String STARTAIRPORT = "startAirport";
	public static String TASK = "task";
	public static String DEPAIRPORT= "depAirport";
	public static String ATTRIBUTE ="attribute";
	public static String ISOUTIN ="isOutIn";
	public static String TERMINALPLANLANDINTIME ="terminalPlanLandInTime";
	public static String BIGFLIGHTNO ="bigFlightNO";
	public static String ALTERNATEAIRPORT ="AlternateAirport";
	public static String PLANLANDINTIME ="planLandInTime";
	public static String STARTPLANTAKEOFFTIME ="startPlanTakeOffTime";
	public static String TERMINALAIRPORT ="terminalAirport";
	public static String FLIGHTNO ="flightNO";
	public static String AIRLINES ="airlines";
	public static String PLANTAKEOFFTIME ="planTakeOffTime";
	public static String FLIGHTTIME = "flightTime";
	public static String ROUTE = "Route";
	public static String ROUTEHX ="routeHX";	
	public static String LINKFLIGHT = "linkFlight";
	public static String STOPFLIGHTS = "stopFlights";	
	public static String TRANSFER = "transfer";
	public static String RUNBEFORE = "runBefore";
	public static String RUNAFTER  = "runAfter";
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	
		
	
	@Column(length=6)
	private String startPlanTakeOffTime;
	
	@Column(length=6)
	private String terminalPlanLandInTime;

	@ManyToOne
	@JoinColumn(name="startAirport")
	private Airport startAirport;

	@ManyToOne
	@JoinColumn(name="terminalAirport")
	private Airport terminalAirport;
	
	@ManyToOne
	@JoinColumn(name="depAirport") 
	private Airport depAirport;

	@ManyToOne
	@JoinColumn(name="arrAirport") 
	private Airport arrAirport;
	
	
	
	@ManyToOne
	@JoinColumn(name="alternateAirport")
	private Airport AlternateAirport;
	
	@Column(length=16)
	private String bigFlightNO;
	
	@Column(length=8)
	private String flightNO;
	
	@Enumerated(EnumType.STRING)
	@Column(length=3)
	private OutIn isOutIn;
	
	@ManyToOne
	@JoinColumn(name="airlines") 
	private Airlines airlines;
	
	@ManyToOne
	@JoinColumn(name="task")
	private FlightTask task;
	
	@ManyToOne
	@JoinColumn(name="attribute")
	private AreaAttribute attribute; 
	
	@Column(length=4)
	private String flightTime;

	@Transient
	private String route;
	
	private String routeHX;
	
	/**
	 *  �Ƿ�����ת����
	 */
	private boolean transfer;
	
	/**
	 *   ��ǰ�����ڽ����ж�
	 */
	private boolean runBefore;
	
	/**
	 *   �������ڽ����ж�
	 */
	private boolean runAfter;
	
	/**
	 * ���غ��ྭͣ������Ϣ
	 */


	public abstract Set<? extends StopFlight> getStopFlights();

	/**
	 * ���ú��ද̬�Ĺ�����
	 */

	public abstract Set<? extends ShareFlight> getShareFlights();


	/**
	 * ���ؾ�ͣ������Ϣ
	 */

	public abstract void setStopFlights(Set<? extends StopFlight> stopFlights);

	/**
	 * ���ù�������Ϣ
	 */

	public abstract void setShareFlights(Set<? extends ShareFlight> shareFlights);

	
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Object id) {
		// TODO Auto-generated method stub
		if (id != null){
			this.id = (Integer) id;
		}
	}
	
	
	/**
	 * @return the startPlanTakeOffTime
	 */
	public String getStartPlanTakeOffTime() {
		return startPlanTakeOffTime;
	}


	/**
	 * @param startPlanTakeOffTime the startPlanTakeOffTime to set
	 */
	public void setStartPlanTakeOffTime(String startPlanTakeOffTime) {
		this.startPlanTakeOffTime = startPlanTakeOffTime;
	}


	/**
	 * @return the terminalPlanLandInTime
	 */
	public String getTerminalPlanLandInTime() {
		return terminalPlanLandInTime;
	}


	/**
	 * @param terminalPlanLandInTime the terminalPlanLandInTime to set
	 */
	public void setTerminalPlanLandInTime(String terminalPlanLandInTime) {
		this.terminalPlanLandInTime = terminalPlanLandInTime;
	}

	
	/**
	 * @return the startAiport
	 */
	public Airport getStartAirport() {
		return startAirport;
	}

	/**
	 * @param startAiport the startAiport to set
	 */
	public void setStartAirport(Airport startAirport) {
		this.startAirport = startAirport;
	}


	/**
	 * @return the terminalAirport
	 */
	public Airport getTerminalAirport() {
		return terminalAirport;
	}

	/**
	 * @param terminalAirport the terminalAirport to set
	 */
	public void setTerminalAirport(Airport terminalAirport) {
		this.terminalAirport = terminalAirport;
	}

	




	/**
	 * @return the alterateAirport
	 */
	public Airport getAlternateAirport() {
		return AlternateAirport;
	}



	/**
	 * @return the bigFlightNO
	 */
	public String getBigFlightNO() {
		return bigFlightNO;
	}

	/**
	 * @param bigFlightNO the bigFlightNO to set
	 */
	public void setBigFlightNO(String bigFlightNO) {
		this.bigFlightNO = bigFlightNO;
	}

	/**
	 * @return the flightNO
	 */
	public String getFlightNO() {
		return flightNO;
	}

	/**
	 * @param flightNO the flightNO to set
	 */
	public void setFlightNO(String flightNO) {
		this.flightNO = flightNO;
	}

	/**
	 * @return the isOffIn
	 */
	public OutIn getIsOutIn() {
		return isOutIn;
	}

	/**
	 * @param isOffIn the isOffIn to set
	 */
	public void setIsOutIn(OutIn isOutIn) {
		this.isOutIn = isOutIn;
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
	 * @return the task
	 */
	public FlightTask getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(FlightTask task) {
		this.task = task;
	}


	/**
	 * @return the attribute
	 */
	public AreaAttribute getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(AreaAttribute attribute) {
		this.attribute = attribute;
	}



	/**
	 * @return the depAiport
	 */
	public Airport getDepAirport() {
		return depAirport;
	}

	/**
	 * @param depAiport the depAiport to set
	 */
	public void setDepAirport(Airport depAirport) {
		this.depAirport = depAirport;
	}




	/**
	 * @return the arrAirport
	 */
	public Airport getArrAirport() {
		return arrAirport;
	}

	/**
	 * @param arrAirport the arrAirport to set
	 */
	public void setArrAirport(Airport arrAirport) {
		this.arrAirport = arrAirport;
	}



	/**
	 * @return the flightTime
	 */
	public String getFlightTime() {
		return flightTime;
	}

	/**
	 * @param flightTime the flightTime to set
	 */
	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	
	
	/**
	 * @param alternateAirport the alternateAirport to set
	 */
	public void setAlternateAirport(Airport alternateAirport) {
		AlternateAirport = alternateAirport;
	}

	
	


	/**
	 * @return the route
	 */

	
	
	
	public String getRoute() {
		BaseFlight flight = this;

		String Route = "";
		//ȡ��ʼ�������
		if (flight.getStartAirport() != null){
			Route += flight.getStartAirport().getShortName();
		}
		//��ͣ�������
		
		if (flight.getStopFlights() != null){
			for(StopFlight stopFlight:flight.getStopFlights()){
				if (stopFlight.getStopAirport() != null){
					Route +="-"+stopFlight.getStopAirport().getShortName();
				}
			}
			
		}

		//�յ�������
		if (flight.getTerminalAirport() != null){
			Route +="-"+flight.getTerminalAirport().getShortName();
		}
		return Route;
	}
	
	
	public String getRouteCode() {
		
		BaseFlight flight = this;

		String Route = "";
		//ȡ��ʼ�������
		if (flight.getStartAirport() != null){
			Route += flight.getStartAirport().getThreeCharCode();
		}
		//��ͣ�������
		
		if (flight.getStopFlights() != null){
			for(StopFlight stopFlight:flight.getStopFlights()){
				if (stopFlight.getStopAirport() != null){
					Route +="-"+stopFlight.getStopAirport().getThreeCharCode();
				}
			}
			
		}

		//�յ�������
		if (flight.getTerminalAirport() != null){
			Route +="-"+flight.getTerminalAirport().getThreeCharCode();
		}
		return Route;
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
	 * 
	 * @Description: ��������ĺ�����Ϣ ����MU5203 SHA-LZH
	 * @return 
	 */
	public String getForwardRouteCn(){

		BaseFlight flight = this;
		String Route = "";
		//ȡ��ʼ�������
		if (flight.getStartAirport() != null){
			Route += flight.getStartAirport().getCnName();
		}
		//��ͣ�������
		if (flight.getStopFlights() != null){
			for(StopFlight stopFlight:flight.getStopFlights()){
				if (stopFlight.getStopAirport() != null){
					Route +="-"+stopFlight.getStopAirport().getCnName();
				}
			}
			
		}
		//�յ�������
		if (flight.getTerminalAirport() != null){
			Route +="-"+flight.getTerminalAirport().getCnName();
		}
		
		return Route;
	}
	
	
	/**
	 * @Description: ���ط���ĺ�����Ϣ������MU5203 LZH-SHA
	 * @return
	 */
	public String getBackRouteCn(){
		BaseFlight flight = this;
		String Route = "";

		//�յ�������
		if (flight.getTerminalAirport() != null){
			Route +=flight.getTerminalAirport().getCnName();
		}
		//��ͣ�������
		if (flight.getStopFlights() != null){
			for(StopFlight stopFlight:flight.getStopFlights()){
				if (stopFlight.getStopAirport() != null){
					Route +="-"+stopFlight.getStopAirport().getCnName();
				}
			}
			
		}
		//ȡ��ʼ�������
		if (flight.getStartAirport() != null){
				Route += "-"+flight.getStartAirport().getCnName();
		}
		return Route;		
	}

	/**
	 * @return the runBefore
	 */
	public boolean isRunBefore() {
		return runBefore;
	}

	/**
	 * @param runBefore the runBefore to set
	 */
	public void setRunBefore(boolean runBefore) {
		this.runBefore = runBefore;
	}

	/**
	 * @return the runAfter
	 */
	public boolean isRunAfter() {
		return runAfter;
	}

	/**
	 * @param runAfter the runAfter to set
	 */
	public void setRunAfter(boolean runAfter) {
		this.runAfter = runAfter;
	}
	
	/**
	 * @return the transfer
	 */
	public boolean isTransfer() {
		return transfer;
	}


	/**
	 * @param transfer the transfer to set
	 */
	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}
	
	
	

	
}
