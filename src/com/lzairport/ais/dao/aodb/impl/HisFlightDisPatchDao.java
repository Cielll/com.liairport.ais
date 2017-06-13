package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IHisFlightDisPatchDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.HisFlightDisPatch;


/**
 * ������ʷ���Ȼ��ڵ�Dao�ӿڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 16/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class HisFlightDisPatchDao extends
		AodbDaoImpl<Integer, HisFlightDisPatch> implements
		IHisFlightDisPatchDao{

}
