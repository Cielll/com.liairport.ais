package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.HisPayingPassenger;
import com.lzairport.ais.service.DataFetchResponseInfo;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.vo.PayingPassengerVO;


/**
 * ������ʷ�����ÿ͵�Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IHisPayingPassengerService extends
		IService<Integer, HisPayingPassenger> {
	/**
	 * 
	 * @Description: ����EXTJSҳ������Ҫ��Response����
	 * @param conditions ����
	 * @return DataFetchResponseInfo���� 
	 * @throws Exception
	 */
	public DataFetchResponseInfo fetchByCondition(QueryConditions conditions) throws Exception;
	
	public HisPayingPassenger update(PayingPassengerVO vo) throws Exception;
}
