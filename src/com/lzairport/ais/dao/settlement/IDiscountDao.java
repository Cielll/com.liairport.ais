package com.lzairport.ais.dao.settlement;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.settlement.Discount;

/**
 * 
 * FileName      IDiscount.java
 * @Description  TODO �����ۿ۵�Dao�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��26�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��26��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Local
public interface IDiscountDao extends IDao<Integer, Discount> {

}
