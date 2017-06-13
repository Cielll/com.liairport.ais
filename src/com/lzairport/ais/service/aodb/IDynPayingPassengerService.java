package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.DynPayingPassenger;
import com.lzairport.ais.service.IService;

/**
 * ����ƻ������ÿ͵�Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IDynPayingPassengerService extends
		IService<Integer, DynPayingPassenger> {

}
