package com.lzairport.ais.dao.settlement.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.settlement.ISettlementItemDao;
import com.lzairport.ais.models.settlement.SettlementItem;


/**
 * 
 * FileName      SettlementItemDao.java
 * @Description  TODO ������ϸ���Daoʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��7��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
@Stateless
public class SettlementItemDao extends AodbDaoImpl<Integer, SettlementItem> implements ISettlementItemDao {

	
}
