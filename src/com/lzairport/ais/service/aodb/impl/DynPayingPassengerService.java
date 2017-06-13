package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IDynPayingPassengerDao;
import com.lzairport.ais.models.aodb.DynPayingPassenger;
import com.lzairport.ais.service.aodb.IDynPayingPassengerService;
import com.lzairport.ais.service.impl.Service;


/**
 * ���ึ���ÿ�ʵ�����Service�ӿڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class DynPayingPassengerService extends
		Service<Integer, DynPayingPassenger> implements
		IDynPayingPassengerService {
	@EJB
	public void setDynPayingPassengerDao(IDynPayingPassengerDao dynPayingPassengerDao){
		setDao(dynPayingPassengerDao);
	}

}
