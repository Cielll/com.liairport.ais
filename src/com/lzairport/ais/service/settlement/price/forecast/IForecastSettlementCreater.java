package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.Local;

import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;

/**
 * 
 * 
 * FileName      IForecastSettlementCreater.java
 * @Description  TODO Ԥ������ĳ���շ���Ŀ�����߽ӿ� 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��4��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��4��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Local
public interface IForecastSettlementCreater {

	/**
	 * ����Ԥ�⺽������ĳ����Ŀ���շѣ�С���㱣��2λ
	 * @param base         Ԥ�⺽���������
	 * @param type         �շ���Ŀ
	 * @throws Exception
	 */
	public void create(ForecastBase base,SettlementType type) throws Exception;
}
