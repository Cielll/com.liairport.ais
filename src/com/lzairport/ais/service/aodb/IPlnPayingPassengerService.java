package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;
import com.lzairport.ais.models.aodb.PlnPayingPassenger;
import com.lzairport.ais.service.IService;

/**
 * ������ʷ�����ÿ͵�Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */


@Remote
public interface IPlnPayingPassengerService extends
		IService<Integer, PlnPayingPassenger> {

}
