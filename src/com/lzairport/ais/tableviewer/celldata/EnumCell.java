package com.lzairport.ais.tableviewer.celldata;

import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.DisPatchItemType;
import com.lzairport.ais.utils.SYS_VARS.OutIn;
import com.lzairport.ais.utils.SYS_VARS.Quarter;


/**
 * ICell��ϵͳ����ö�����͵�ʵ���� ��������ʾ������ö�����͵����ݣ�����ģʽ
 * @author ZhangYu
 * version 0.9a 06/11/14
 * @since JDK 1.6
 */

public class EnumCell implements ICell {
	
	private static StaticCell staticCell = StaticCell.getInstance();
	
	private static EnumCell instance = new EnumCell();
	
	private EnumCell(){
		
	}
	
	public static  EnumCell getInstance() {
		return instance;
	}
	
	/**
	 * ����ö�����ͷ�������Ӧ�ı�ʾ�ַ�--���񻷽�
	 * @param obj
	 * @return ��ʾ�ַ���
	 */
	private Object getDisPatchItemTypeText(DisPatchItemType obj){
		return SYS_VARS.DisPatchItemTypeCn.get(obj.ordinal());
	}
	
	/**
	 * ���ݶ�Ӧ�ı�ʾ�ַ�����ö������--���񻷽�
	 * @param obj
	 * @return ��ʾ�ַ���
	 */
	private Object getDisPatchItemTypedata(String parm){
		return DisPatchItemType.values()[SYS_VARS.DisPatchItemTypeCn.indexOf(parm)];
	}
	
	
	/**
	 * ����ö�����ͷ�������Ӧ�ı�ʾ�ַ�--����
	 * @param obj
	 * @return ��ʾ�ַ���
	 */
	private Object getQuarterText(Quarter obj){
		return SYS_VARS.QuarterCn.get(obj.ordinal());
	}

	/**
	 * ���ݶ�Ӧ�ı�ʾ�ַ�����ö������--���񻷽�
	 * @param obj
	 * @return ��ʾ�ַ���
	 */
	private Object getQuarterdata(String parm){
		return Quarter.values()[SYS_VARS.QuarterCn.indexOf(parm)];
	}
	
	
	/**
	 * ����ö�����ͷ�������Ӧ�ı�ʾ�ַ�--����
	 * @param obj
	 * @return ��ʾ�ַ���
	 */
	private Object getOutInText(OutIn obj){
		return SYS_VARS.OutInCn.get(obj.ordinal());
	}
	
	/**
	 * ���ݶ�Ӧ�ı�ʾ�ַ�����ö������--���񻷽�
	 * @param obj
	 * @return ��ʾ�ַ���
	 */
	private Object getOutIndata(String parm){
		return OutIn.values()[SYS_VARS.OutInCn.indexOf(parm)];
	}
	
	

	@Override
	public Object getData(Object element, HeaderItem field) {
		// TODO Auto-generated method stub
		
		Object enumObj = staticCell.getData(element, field);
		Object obj = null; 
		if (enumObj instanceof DisPatchItemType){
			obj =  getDisPatchItemTypeText((DisPatchItemType) enumObj);
		}else if (enumObj instanceof Quarter){
			obj = getQuarterText((Quarter) enumObj);
		}else if (enumObj instanceof OutIn){
			obj = getOutInText((OutIn) enumObj);
		}
		return obj;
	}
	
	

	@Override
	public void setData(Object element, HeaderItem field, Object parm,Class<?> parmClass) {
		// TODO Auto-generated method stub
		
		if (parmClass == DisPatchItemType.class){
			ObjectMethodUtil.setFieldObject(element, field.getEname(), 
					getDisPatchItemTypedata((String) parm), parmClass);
		}else if (parmClass == Quarter.class){
			ObjectMethodUtil.setFieldObject(element, field.getEname(), 
					getQuarterdata((String) parm), parmClass);
		}else if (parmClass == OutIn.class){
			ObjectMethodUtil.setFieldObject(element, field.getEname(), 
					getOutIndata((String) parm), parmClass);
		}
	}
	
	
	/**
	 * ����ö����������Ӧ�����г�Ա��������ʾ
	 * @param element
	 * @param field
	 * @return ��ʾ�ַ�������
	 */
	public String[] enumCnText(Object element, HeaderItem field){
		
		Class<?> parmClass = ObjectMethodUtil.getFieldType(element.getClass(), field.getEname());
		String[] resultText = null;
		
		if (parmClass == DisPatchItemType.class){
			
			resultText = SYS_VARS.DisPatchItemTypeCn.toArray(new String[DisPatchItemType.values().length]);
			
		}else if (parmClass == Quarter.class){
			
		    resultText = SYS_VARS.QuarterCn.toArray(new String[Quarter.values().length]);
			
		}else if (parmClass == OutIn.class){
			
			resultText = SYS_VARS.OutInCn.toArray(new String[OutIn.values().length]);
		}
		return resultText;
	}

}
