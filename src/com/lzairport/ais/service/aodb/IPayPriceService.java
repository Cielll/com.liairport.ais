package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.PayPrice;
import com.lzairport.ais.service.IService;


/**
 * ���ѷ������͵�Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IPayPriceService extends IService<Integer, PayPrice> {
	
	
	/**
	 * ����Pay������������չ�˾���Ҽ۸�������û���ҵ���Ĭ�ϼ۸�
	 * @param PayCode ���Ѵ��� ��FF1,FF2,FC
	 * @param airlines 
	 * @return �۸����
	 */
	public PayPrice getPayPrice(String PayCode,Airlines airlines);

}
