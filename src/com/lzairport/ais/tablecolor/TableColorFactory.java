package com.lzairport.ais.tablecolor;

import org.eclipse.swt.graphics.RGB;

import com.lzairport.ais.models.AisRGB;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * ���ر�񱳾���ɫ��������ɫ�Ľ��칤��
 * @author ZhangYu
 * version 0.9a 27/06/14
 * @since JDK 1.6
 */


public class TableColorFactory {
	

	/**
	 * ���ݴ���Ķ���ʵ�����ͷ���һ��AisRGB����
	 * @param object �����ʵ�����
	 * @return ʵ���������Ӧ����ɫAisRGB
	 */
	
	private  static  AisRGB getAisGRB(Object object){
		AisRGB  aisRGB = null;
		//����ʵ��������AisRGb�����
		int index = SYS_VARS.RGBInClassName.indexOf(object.getClass().getName());
		if (index != -1){
			try {
				//������Ų��Ҷ�Ӧ��ITableColor����������������	
				 aisRGB = ((ITableColor)Class.forName(SYS_VARS.RGBOutClassName.get(index)).newInstance()).getRgbObj(object);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		return aisRGB;
	}
	
	/**
	 * ���ر�񱳾���ɫ
	 * @param object �����ʵ�����
	 * @return ʵ���������Ӧ�ı�����ɫAisRGB
	 */
	public static RGB getTableBKColor(Object object){

		AisRGB rgb = getAisGRB(object);
		if (rgb != null) {
			return rgb.getBkRGB();
		}
		return null;
	}
	
	
	/**
	 * ���ر��������ɫ
	  * @param object �����ʵ�����
	 * @return ʵ���������Ӧ��������ɫAisRGB
	 */
	public static RGB getTableFRColor(Object object){

		AisRGB rgb = getAisGRB(object);
		if (rgb != null) {
			return rgb.getFrRGB();
		}
		return null;
	}

}
