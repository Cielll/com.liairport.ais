package com.lzairport.ais.service.statistics.impl;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.statistics.IBaseRouteMonthDao;
import com.lzairport.ais.models.statistics.BaseRouteMonth;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IBaseRouteMonthService;


/**
 * 
 * FileName      BaseRouteMonthService.java
 * @Description  TODO Ԥ�⺽��ÿ���µĻ�׼���ݵ�Serviceʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��7��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��7��7��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class BaseRouteMonthService extends Service<Integer, BaseRouteMonth> implements IBaseRouteMonthService {
	
	
	private DecimalFormat df = new DecimalFormat("#.0");
	
	@EJB
	public void setBaseRouteMonthDao(IBaseRouteMonthDao dao){
		setDao(dao);
	}

	@Override
	public BaseRouteMonth update(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof BaseRouteMonth){
			/**
			 *  ����ƽ��������
			 */
			BaseRouteMonth month = (BaseRouteMonth) obj;
			month.setRate((month.getiRate()+month.getoRate())/2);
			df.setRoundingMode(RoundingMode.HALF_UP);
			month.setRate(Double.parseDouble(df.format(month.getRate())));
			return super.update(month);
		}else{
			return null;
		}
		
		
	}
	
	
	
	
	
}
