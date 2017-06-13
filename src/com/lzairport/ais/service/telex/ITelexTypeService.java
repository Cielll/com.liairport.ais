package com.lzairport.ais.service.telex;

import javax.ejb.Remote;

import com.lzairport.ais.models.telex.TelexType;
import com.lzairport.ais.service.IService;

/**
 * �籨����TelexType��Service��ӿ�
 * @author ZhangYu
 * @version 0.9a 29/04/15
 * @since JDK 1.6
 *
 */

@Remote
public interface ITelexTypeService extends IService<Integer, TelexType> {
	

	public TelexType getTelexDefaultType();

}
