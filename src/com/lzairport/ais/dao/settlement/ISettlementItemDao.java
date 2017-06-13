package com.lzairport.ais.dao.settlement;

import javax.ejb.Local;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.settlement.SettlementItem;

/**
 * FileName      ISettlementItemDao.java
 * @Description  TODO  ������Ŀ��ϸ��Dao�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��7��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
@Local
public interface ISettlementItemDao extends IDao<Integer, SettlementItem> {

}
