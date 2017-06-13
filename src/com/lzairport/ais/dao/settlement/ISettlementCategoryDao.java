package com.lzairport.ais.dao.settlement;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.settlement.SettlementCategory;

/**
 * 
 * FileName      ISettlementCategory.java
 * @Description  TODO ��������Dao�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��1��12�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2017��1��12��      zhang    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Local
public interface ISettlementCategoryDao extends IDao<Integer, SettlementCategory> {

}
