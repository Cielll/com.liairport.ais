package com.nngairport.ais.dao.datacollect;

import javax.ejb.Local;

import com.lzairport.ais.dao.IDao;
import com.nngairport.ais.models.datacollect.Message;


/**
 * 
 * 
 * FileName      IMessageDao.java
 * @Description  TODO  IB��ϢDao�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��5��25��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��5��25��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Local
public interface IMessageDao extends IDao<Integer, Message> {

}
