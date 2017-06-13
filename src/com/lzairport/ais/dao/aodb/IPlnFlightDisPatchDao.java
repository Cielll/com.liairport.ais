package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.PlnFlightDisPatch;

/**
 * ����ƻ����Ȼ��ڵ�Dao�ӿ�
 * @author ZhangYu
 * @version 0.9a 16/05/15
 * @since JDK 1.6
 *
 */

@Local
public interface IPlnFlightDisPatchDao extends IDao<Integer, PlnFlightDisPatch> {

}
