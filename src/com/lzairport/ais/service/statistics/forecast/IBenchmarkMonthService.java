package com.lzairport.ais.service.statistics.forecast;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.forecast.BenchmarkMonth;
import com.lzairport.ais.service.IService;

/**
 * FileName      IBenchmarkMonthService.java
 * @Description  TODO ��׼�����µ�Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��4��18��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��4��18��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Remote
public interface IBenchmarkMonthService extends IService<Integer, BenchmarkMonth> {

}
