package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IAircraftDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.Aircraft;

/**
 * �ɻ���Dao�ӿڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 22/08/24
 * @since JDK 1.6
 *
 */

@Stateless
public class AircraftDao extends AodbDaoImpl<Integer, Aircraft> implements
		IAircraftDao {



}
