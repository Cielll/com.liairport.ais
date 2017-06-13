package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.HisStopFlight;
import com.lzairport.ais.service.IService;

/**
 * ���ྭͣ��ʷ��Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IHisStopFlightService extends IService<Integer, HisStopFlight> {

}
