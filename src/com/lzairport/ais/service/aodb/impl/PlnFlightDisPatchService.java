package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IPlnFlightDisPatchDao;
import com.lzairport.ais.models.aodb.PlnFlightDisPatch;
import com.lzairport.ais.service.aodb.IPlnFlightDisPatchService;
import com.lzairport.ais.service.impl.Service;


/**
 * ����ƻ����Ȼ��ڵ�Service�ӿڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 16/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class PlnFlightDisPatchService extends
		Service<Integer, PlnFlightDisPatch> implements IPlnFlightDisPatchService  {

	@EJB
	public void setFlightDisPatchDao(IPlnFlightDisPatchDao plnFlightDisPatchDao){
		setDao(plnFlightDisPatchDao);
	}
	
}
