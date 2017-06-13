package com.lzairport.ais.tableviewer.celldata;

import java.util.Date;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.ObjectMethodUtil;

/**
 * ICell�ĺ��൥Ԫ���ʵ���� ��������ʾ�������ݵ����ݣ�����ģʽ
 * @author ZhangYu
 * version 0.9a 06/11/14
 * @since JDK 1.6
 */
public class FlightStaticCell implements ICell {

	private static StaticCell staticCell = StaticCell.getInstance();
	
	private static FlightStaticCell instance = new FlightStaticCell();
	
	private FlightStaticCell(){
		
	}
	
	public static FlightStaticCell getInstance() {
		return instance;
	}	
	
	@Override
	public Object getData(Object element, HeaderItem field) {

		Object result = staticCell.getData(element, field);
		
		//������ʾ�Ĵ���  �����ʱ��
		if (result instanceof Date){
			if ((field.getEname().equals(Flight.EXECDATE))||
			   	(field.getEname().equals(ScheduleFlight.STARTTIME))||
			   	(field.getEname().equals(ScheduleFlight.ENDTIME))){
				//��������� YYYY-MM-DD
				result = DateTimeUtil.dateToYYYYMMDD((Date) result);
			}else{
				//�����ʱ������ HHMM+D
				result = DateTimeUtil.dateToHHMM((Flight)element,(Date)result);
			}
			
		}
		return result;
	}

	@Override
	public void setData(Object element, HeaderItem field, Object parm,Class<?> parmClass) {
		// TODO Auto-generated method stub
		ObjectMethodUtil.setFieldObject(element, field.getEname(),parm, parmClass);
	}



}
