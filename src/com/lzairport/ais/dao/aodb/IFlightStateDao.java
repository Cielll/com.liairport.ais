package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.FlightState;

/**
 * ����״̬ʵ�����Dao
 * @author ZhangYu
 * @version 0.9a 19/08/14
 * @since JDK 1.6
 *
 */

@Local
public interface IFlightStateDao extends IDao<Integer, FlightState> {

}
