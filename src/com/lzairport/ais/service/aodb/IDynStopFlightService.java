package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.DynStopFlight;
import com.lzairport.ais.service.IService;

/**
 * ���ද̬��ͣ��Service�Ľӿ�
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IDynStopFlightService extends IService<Integer, DynStopFlight> {

}
