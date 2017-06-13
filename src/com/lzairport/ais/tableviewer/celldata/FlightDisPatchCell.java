package com.lzairport.ais.tableviewer.celldata;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.FlightDisPatch;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.Ais_String_Util;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.ObjectMethodUtil;

/**
 * ICell�ĵ��Ȼ��ڵ�Ԫ���ʵ���� ��������ʾ���Ȼ��ڵ����ݣ�����ģʽ
 * @author ZhangYu
 * version 0.9a 06/11/14
 * @since JDK 1.6
 */

public class FlightDisPatchCell implements ICell {

	private static FlightDisPatchCell instance = new FlightDisPatchCell();

	private FlightDisPatchCell() {

	}

	public static FlightDisPatchCell getInstance() {
		return instance;
	}

	@Override
	public Object getData(Object element, HeaderItem field) {
		// TODO Auto-generated method stub
		Flight flight = (Flight) element;
		//ȡ�øú�������е��Ȼ���
		Set<? extends FlightDisPatch> disPatchs = flight.getFlightDisPatchs();
		//field.getSubEname()�ĸ�ʽ  ���磺(��ʼ)/FlightDisPatch.STARTREALTIME
		List<String> subEnames = Ais_String_Util.SplitSubEname(field.getSubEname());
        String eSubName =  subEnames.get(1);
        String cSubName =  subEnames.get(0);
        Object cellData = null;
        
		boolean noFound = true;
		for (FlightDisPatch disPatch :disPatchs){
			String disPatchName = disPatch.getDisPatchItem().getName()+cSubName;
			//�øú������е��Ȼ������ֶ���Ƚ�
			if (field.getName().equals(disPatchName)){
				//����ֶ�һ�£�ȡ������
				cellData = ObjectMethodUtil.getFieldObject(disPatch, eSubName); 
				noFound = false;
				break;
			}
		}
		
		if (noFound){
			//���û�иõ��Ȼ���
			return "X";
		}else {
			if (cellData instanceof Date){
				return DateTimeUtil.dateToHHMM(flight,(Date)cellData);
			}else{
				return cellData;
			}
		}
	}

	@Override
	public void setData(Object element, HeaderItem field, Object data,Class<?> parmClass) {
		// TODO Auto-generated method stub
		Flight flight = (Flight) element;
		//ȡ�øú�������е��Ȼ���

		Set<? extends FlightDisPatch> disPatchs = flight.getFlightDisPatchs();
		//field.getSubEname()�ĸ�ʽ  ���磺(��ʼ)/FlightDisPatch.STARTREALTIME
		List<String> subEnames = Ais_String_Util.SplitSubEname(field.getSubEname());
        String eSubName =  subEnames.get(1);
        String cSubName =  subEnames.get(0);
        for (FlightDisPatch disPatch :disPatchs){
			String disPatchName = disPatch.getDisPatchItem().getName()+cSubName;
			//�øú������е��Ȼ������ֶ���Ƚ�
			if (field.getName().equals(disPatchName)){
				Class<?> type = ObjectMethodUtil.getFieldType(FlightDisPatch.class, eSubName);
				ObjectMethodUtil.setFieldObject(disPatch, eSubName, data,type);
				break;
			}
		}		
	}



	
	
}
