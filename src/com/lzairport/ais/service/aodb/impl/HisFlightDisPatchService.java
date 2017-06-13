package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IHisFlightDisPatchDao;
import com.lzairport.ais.models.aodb.HisFlightDisPatch;
import com.lzairport.ais.service.aodb.IHisFlightDisPatchService;
import com.lzairport.ais.service.impl.Service;

/**
 * ������ʷ���Ȼ��ڵ�Service�ӿڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 16/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class HisFlightDisPatchService extends
		Service<Integer, HisFlightDisPatch> implements
		IHisFlightDisPatchService {

	@EJB
	public void setHisFlightDisPatchDao(IHisFlightDisPatchDao hisFlightDisPatchDao){
		setDao(hisFlightDisPatchDao);
	}
	
}
