package com.lzairport.ais.tableviewer.celldata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lzairport.ais.models.aodb.BaseFlight;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.Ais_String_Util;
import com.lzairport.ais.utils.ObjectMethodUtil;


/**
 *  Cell��ͨ�õ�Ԫ���ʵ���� ȡ�þ�̬������ݣ�
 * @author ZhangYu
 * version 0.9a 08/11/14
 * @since JDK 1.6
 */

public class StaticCell implements ICell {

	private static StaticCell instance = new StaticCell();
	
	private StaticCell(){
		
	}
	
	public static StaticCell getInstance (){
		return instance;
	}

	
	@Override
	public Object getData(Object element, HeaderItem field) {
		// TODO Auto-generated method stub
		
		String subEname = field.getSubEname(); 
		String Ename = field.getEname();
		Object result = null;
		
		if ((subEname == null) || (subEname.trim() =="" )){
			result = ObjectMethodUtil.getFieldObject(element, Ename); 
		}else{
			List<String> subEnames = Ais_String_Util.SplitSubEname(subEname);
			Object obj =null;
			for (String name:subEnames){
				obj = ObjectMethodUtil.getFieldObject(element, Ename);
				//׼��ȡ��һ�ε�ʵ��Ķ���
				element = obj;
				Ename = name;
				//��������һ����subEname����ʾ����
				subEname = name;
			}
				result =  ObjectMethodUtil.getFieldObject(obj,subEname);
		}
		
		//������������� ����ͨ�õ����ڱ�ʾ��ʽ
		if ((result instanceof Date) && !(element instanceof BaseFlight)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result = sdf.format(result);
		}
		
		return result;
	}

	@Override
	public void setData(Object element, HeaderItem field, Object parm,Class<?> parmClass) {
		// TODO Auto-generated method stub
		ObjectMethodUtil.setFieldObject(element, field.getEname(),parm, parmClass);
		
	}
	
	
	

}
