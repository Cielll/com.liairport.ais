package com.lzairport.ais.tablecolor;

import com.lzairport.ais.models.AisRGB;

/**
 * �����ɫ�ӿ�
 * @author ZhangYu
 * version 0.9a 27/06/14
 * @since JDK 1.6
 */

public interface ITableColor {

	/**
	 * ����ʵ�������ɫ���� AisRGB
	 * @param object
	 * @return ��ɫ���� AisRGB
	 */
	public abstract AisRGB getRgbObj(Object object);

}