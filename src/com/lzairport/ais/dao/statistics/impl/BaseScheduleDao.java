package com.lzairport.ais.dao.statistics.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.statistics.IBaseScheduleDao;
import com.lzairport.ais.models.statistics.BaseSchedule;

/**
 * 
 * FileName      BaseScheduleDao.java
 * @Description  TODO ��׼���Ⱥ��ߵ�Dao�ӿڵ�ʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��9��10�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��9��10��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */


@Stateless
public class BaseScheduleDao extends AodbDaoImpl<Integer, BaseSchedule> implements IBaseScheduleDao {

}
