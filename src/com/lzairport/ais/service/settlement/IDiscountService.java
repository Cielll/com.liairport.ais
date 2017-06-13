package com.lzairport.ais.service.settlement;

import javax.ejb.Remote;
import com.lzairport.ais.models.settlement.Discount;
import com.lzairport.ais.service.IService;

/**
 * 
 * FileName      IDiscountService.java
 * @Description  TODO �����ۿ�Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��26�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��26��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Remote
public interface IDiscountService extends IService<Integer, Discount> {

}
