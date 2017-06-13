package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.PlnStopFlight;

/**
 * ����ƻ���ͣ��ʵ�����Dao�ӿ�
 * @author ZhangYu
 * @version 0.9a 16/08/14
 * @since JDK 1.6
 *
 */

@Local
public interface IPlnStopFlightDao extends IDao<Integer, PlnStopFlight> {

}
