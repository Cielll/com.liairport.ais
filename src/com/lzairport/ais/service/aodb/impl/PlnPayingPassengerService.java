package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IPlnPayingPassengerDao;
import com.lzairport.ais.models.aodb.PlnPayingPassenger;
import com.lzairport.ais.service.aodb.IPlnPayingPassengerService;
import com.lzairport.ais.service.impl.Service;


/**
 * ����ƻ������ÿ�ʵ�����Service�ӿڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class PlnPayingPassengerService extends
		Service<Integer, PlnPayingPassenger> implements IPlnPayingPassengerService {

	@EJB
	public void setPlnPayingPassengerDao(IPlnPayingPassengerDao plnPayingPassengerDao){
		setDao(plnPayingPassengerDao);
	}

}
