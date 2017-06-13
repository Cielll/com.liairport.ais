package com.lzairport.ais.tableviewer.celldata;

import com.lzairport.ais.models.aodb.BaseFlight;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.SYS_VARS;


/**
 * Cell�Ľ��칤�����ô�����TableViwer�ĸ���Ԫ�����ʾ��ʽ
 * @author ZhangYu
 * version 0.9a 06/11/14
 * @since JDK 1.6
 */

public class CellFactory {

	public static ICell createCell(Object element,HeaderItem field){
		if (field.getItemType().equals(SYS_VARS.ViewItemType_Static) || field.getItemType().equals(SYS_VARS.ViewItemType_Bool)){
			//����Ǿ�̬�ֶ�
			if (element instanceof BaseFlight){
				//����Ǻ�����Ϣʵ�壬���غ�����Ϣ��̬Cell
				return FlightStaticCell.getInstance();
			}else{
				//���򷵻�ͨ��Cell
				return StaticCell.getInstance();
			}
		}else if (field.getItemType().equals(SYS_VARS.ViewItemType_Dyniamic) && field.getEname().equals(Flight.FLIGHTDISPATCHS)) {
			//����Ƕ�̬�ĺ�������ֶΣ����غ������Cell
			return FlightDisPatchCell.getInstance();
		}else if (field.getItemType().equals(SYS_VARS.ViewItemType_Dyniamic) && field.getEname().equals(ScheduleFlight.EXECWEEK)) {
			//�����ִ��Ƶ���ֶΣ�����ִ��Ƶ�ʵ�Ԫ��
			return FlightWeekCell.getInstance();
		}else if (field.getItemType().equals(SYS_VARS.ViewItemType_Enum)){
			//�����ö�����ͣ�����ö�����͵�Ԫ��
			return  EnumCell.getInstance();
		}else {
			return null;
		}
		
		
	}
	
}
