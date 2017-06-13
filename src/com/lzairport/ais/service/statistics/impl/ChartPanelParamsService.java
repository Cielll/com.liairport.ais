package com.lzairport.ais.service.statistics.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.statistics.IChartPanelParamsDao;
import com.lzairport.ais.models.statistics.ChartPanelParams;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IChartPanelParamsService;


/**
 * 
 * FileName      ChartPanelParamsService.java
 * @Description  TODO ͼ���Ƶ�Serviceʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��8��3�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��8��3��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class ChartPanelParamsService extends Service<Integer, ChartPanelParams> implements IChartPanelParamsService {

	@EJB
	public void setChartPanelParamsDao(IChartPanelParamsDao dao){
		setDao(dao);
	}
}
