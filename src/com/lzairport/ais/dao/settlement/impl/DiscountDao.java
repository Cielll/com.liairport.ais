package com.lzairport.ais.dao.settlement.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.settlement.IDiscountDao;
import com.lzairport.ais.models.settlement.Discount;



/**
 * 
 * FileName      DiscountDao.java
 * @Description  TODO �����ۿ�Daoʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��26�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��26��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
@Stateless
public class DiscountDao extends AodbDaoImpl<Integer, Discount> implements IDiscountDao {

	
}
