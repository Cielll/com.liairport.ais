package com.lzairport.ais.dao.telex.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.telex.ITelexTypeDao;
import com.lzairport.ais.models.telex.TelexType;


/**
 * �籨����TelexType��Dao��ӿڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */

@Stateless
public class TelexTypeDao  extends AodbDaoImpl<Integer, TelexType>  implements ITelexTypeDao {
	
	
}
