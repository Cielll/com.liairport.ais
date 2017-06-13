package com.lzairport.ais.service.statistics;

import javax.ejb.Remote;
import com.lzairport.ais.models.statistics.BaseRouteMonth;
import com.lzairport.ais.service.IService;

/**
 * 
 * FileName      IBaseRouteMonthService.java
 * @Description  TODO Ԥ�⺽��ÿ���µĻ�׼���ݵ�Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��7��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��7��7��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
@Remote
public interface IBaseRouteMonthService extends IService<Integer, BaseRouteMonth> {

}
