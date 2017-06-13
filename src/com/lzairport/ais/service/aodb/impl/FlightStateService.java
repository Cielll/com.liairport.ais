package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IFlightStateDao;
import com.lzairport.ais.models.aodb.FlightState;
import com.lzairport.ais.service.aodb.IFlightStateService;
import com.lzairport.ais.service.impl.Service;

/**
 * ����״̬Serviceʵ���࣬���Է��ض���õĺ����״̬
 * @author ZhangYu
 * @version 0.9a 17/11/14
 * @since JDK 1.6
 *
 */

@Stateless
public class FlightStateService extends Service<Integer,FlightState> implements IFlightStateService {

	@EJB
	public void setFlightStateDao(IFlightStateDao flightStateDao){
		setDao(flightStateDao);
	}

	@Override
	public FlightState getPlnState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "PLN");
	}

	@Override
	public FlightState getPreviousTakeOffState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "ǰ�����");
	}

	@Override
	public FlightState getLocalTakeOffState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "�������");
	}

	@Override
	public FlightState getAlternateTakeOffState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "�������");
	}

	@Override
	public FlightState getReturnTakeoffState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "�������");
	}

	@Override
	public FlightState getLandInState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "���");
	}

	@Override
	public FlightState getAlternateLandInState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "�������");
	}

	@Override
	public FlightState getReturnLandInState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "�������");
	}

	@Override
	public FlightState getAlternateState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "������");
	}

	@Override
	public FlightState getReturnState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "������");
	}

	@Override
	public FlightState getDlyState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "����");
	}

	@Override
	public FlightState getCnlState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "ȡ��");
	}


	@Override
	public FlightState getFPLState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "FPL");
	}

}
