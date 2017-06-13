package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.DynFlightLoad;

/**
 * ���ද̬������ʵ�����Dao�ӿ�
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */


@Local
public interface IDynFlightLoadDao extends IDao<Integer, DynFlightLoad> {

}