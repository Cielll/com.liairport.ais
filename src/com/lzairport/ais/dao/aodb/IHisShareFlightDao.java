package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.HisShareFlight;

/**
 * ��������ʷ��Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */
@Local
public interface IHisShareFlightDao extends IDao<Integer, HisShareFlight> {

}
