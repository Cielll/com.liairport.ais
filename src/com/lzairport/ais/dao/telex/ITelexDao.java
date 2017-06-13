package com.lzairport.ais.dao.telex;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.telex.TelexBO;

/**
 * �籨TelexBo��Dao��ӿ�
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */

@Local
public interface ITelexDao extends IDao<Integer,TelexBO> {

}