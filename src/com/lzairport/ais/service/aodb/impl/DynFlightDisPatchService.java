package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IDynFlightDisPatchDao;
import com.lzairport.ais.models.aodb.DynFlightDisPatch;
import com.lzairport.ais.service.aodb.IDynFlightDisPatchService;
import com.lzairport.ais.service.impl.Service;

/**
 * ���ද̬���Ȼ��ڵ�Service�ӿڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 16/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class DynFlightDisPatchService extends
		Service<Integer, DynFlightDisPatch> implements
		IDynFlightDisPatchService {

	@EJB
	public void setDynFlightDisPatchDao(IDynFlightDisPatchDao dynFlightDisPatchDao){
		setDao(dynFlightDisPatchDao);
	}
	
	
}
