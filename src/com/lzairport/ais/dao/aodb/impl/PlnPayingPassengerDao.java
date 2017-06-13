package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IPlnPayingPassengerDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.PlnPayingPassenger;


/**
 * ����ƻ������ÿ�ʵ�����Dao��ʵ����
 * @author ZhangYu
 * @version 0.9a 19/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class PlnPayingPassengerDao extends AodbDaoImpl<Integer, PlnPayingPassenger>
		implements IPlnPayingPassengerDao {

}
