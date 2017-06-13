package com.lzairport.ais.dao.settlement.forecast;

import javax.ejb.Local;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.settlement.forecast.ForecastLink;

/**
 * FileName      IForecastLinkDao.java
 * @Description  TODO ��Ԥ����񻷽ڵ�Dao�ӿ� 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��3��24��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��3��24��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Local
public interface IForecastLinkDao extends IDao<Integer, ForecastLink> {

}
