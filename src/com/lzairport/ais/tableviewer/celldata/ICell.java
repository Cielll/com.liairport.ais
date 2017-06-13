package com.lzairport.ais.tableviewer.celldata;

import com.lzairport.ais.tableviewer.header.HeaderItem;


/**
 *  ȡ�ñ�����ݵĽӿ�
 * @author ZhangYu
 * version 0.9a 08/11/14
 * @since JDK 1.6
 */

public interface ICell {
	
	/**
	 * �������ݶ����ֶε�ֵ���������ֶεĻ��������ֶεĶ���
	 * @param element ���ݶ���
	 * @param HeaderItem ��Ӧ�ı�ͷ����
	 * @return ����ֵ
	 */
	public Object getData(Object element,HeaderItem field);
	
	public void setData(Object element,HeaderItem field,Object data,Class<?> parmClass);
	
}
