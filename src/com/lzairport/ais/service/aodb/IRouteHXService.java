package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.RouteHX;
import com.lzairport.ais.service.IService;


/**
 * 
 * FileName      IRouteService.java
 * @Description  ���ߵ�Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��4��5�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��4��5��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Remote
public interface IRouteHXService extends IService<Integer, RouteHX> {

}
