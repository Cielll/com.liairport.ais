package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.HisFlightDisPatch;

/**
 * ������ʷ���Ȼ��ڵ�Dao�ӿ�
 * @author ZhangYu
 * @version 0.9a 19/05/15
 * @since JDK 1.6
 *
 */

@Local
public interface IHisFlightDisPatchDao extends IDao<Integer, HisFlightDisPatch> {

}
