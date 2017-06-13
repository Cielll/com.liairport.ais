package com.lzairport.ais.jms;

import javax.ejb.Stateless;

import com.lzairport.ais.models.aodb.DynFlight;
import com.lzairport.ais.models.aodb.DynFlightDisPatch;
import com.lzairport.ais.utils.EventCodeUtil;

/**
 *	�����ද̬�ķ������ݸı�ʱ֪ͨ���ͻ��˵�ʵ���� 
 * @author ZhangYu
 * @since JDK 1.6
 * @version 0.9a 08/08/15
 */

@Stateless
public class DynFlightDispatchNotice extends Notice  implements INotice{
	
	private DynFlightDisPatch disPatch;

	@Override
	public void changeNotice(String eventCode, String property, Object obj) {
		// TODO Auto-generated method stub
		//����֪ͨDispathch���ݱ仯
		super.changeNotice(eventCode, property, obj);
		//����Ǹ������ݣ���Ҫ֪ͨ����ͼ����صĺ��ද̬����
		if (property.equals(EventCodeUtil.ModelsUpdate)){
			disPatch = (DynFlightDisPatch) obj;
			DynFlight flight = (DynFlight) disPatch.getFlight();
			super.changeNotice(eventCode, property, flight);
			
		}
	
		
	}
	
	
	
	
}
