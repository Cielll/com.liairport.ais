package com.lzairport.ais.service.statistics;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.ChartPanelParams;
import com.lzairport.ais.service.IService;


/**
 * 
 * FileName      IChartPanelParamsService.java
 * @Description  TODO ͼ���Ƶ�Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��8��3�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��8��3��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Remote
public interface IChartPanelParamsService extends IService<Integer, ChartPanelParams> {

}
