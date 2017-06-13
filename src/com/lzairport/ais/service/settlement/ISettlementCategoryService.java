package com.lzairport.ais.service.settlement;

import javax.ejb.Remote;
import com.lzairport.ais.models.settlement.SettlementCategory;
import com.lzairport.ais.service.IService;


/**
 * 
 * FileName      ISettlementCategoryService.java
 * @Description  TODO ��������Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��1��12�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2017��1��12��      zhang    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */


@Remote
public interface ISettlementCategoryService extends IService<Integer, SettlementCategory> {

}
