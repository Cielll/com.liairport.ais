package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IAirlinesDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.Airlines;

/**
 * ���չ�˾Dao�ӿڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 22/08/24
 * @since JDK 1.6
 *
 */

@Stateless
public class AirlinesDao extends AodbDaoImpl<Integer, Airlines> implements
		IAirlinesDao {


}
