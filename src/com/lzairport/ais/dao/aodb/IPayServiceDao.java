package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.PayPrice;

/**
 * ���ѷ������͵�Dao�ӿ�
 * @author ZhangYu
 * @version 0.9a 28/09/14
 * @since JDK 1.6
 *
 */
@Local
public interface IPayServiceDao extends IDao<Integer, PayPrice> {

}
