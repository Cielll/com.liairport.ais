package com.lzairport.ais.service.settlement.forecast;

import javax.ejb.Remote;

import com.lzairport.ais.models.settlement.forecast.ForecastBase;
import com.lzairport.ais.models.settlement.forecast.ForecastSettlement;
import com.lzairport.ais.service.IService;

/**
 * 
 * 
 * FileName      IForecastSettlement.java
 * @Description  TODO Ԥ����������Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��3��24��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��3��24��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
@Remote
public interface IForecastSettlementService extends IService<Integer, ForecastSettlement> {
	
	/**
	 * 
	 * @Description: TODO  ����һ�����ߵ�����
	 * @param  Base        ���߻�������
	 * @throws Exception 
	 */
	public void createSettlement(ForecastBase base) throws Exception;

}
