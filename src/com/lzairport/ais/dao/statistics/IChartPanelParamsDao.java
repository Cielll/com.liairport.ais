package com.lzairport.ais.dao.statistics;

import javax.ejb.Local;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.statistics.ChartPanelParams;


/**
 * 
 * FileName      IChartPanelParamsDao.java
 * @Description  TODO ͼ��������Dao�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��8��3�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��8��3��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Local
public interface IChartPanelParamsDao extends IDao<Integer, ChartPanelParams> {

}
