package com.lzairport.ais.tablecolor;

import com.lzairport.ais.models.AisRGB;
import com.lzairport.ais.models.telex.TelexBO;


/**
 * ����TelexBo����ı����ɫ����AisRGB
  *@author ZhangYu
 * version 0.9a 27/06/14
 * @since JDK 1.6
 */

public class TelexBkColor implements ITableColor {

	
	@Override
	public AisRGB getRgbObj(Object object) {
		// TODO Auto-generated method stub
		//����TelexBo�Ĵ����������ر��������ɫ
		return ((TelexBO)object).getAnalyticyType();
	}
}
