package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.AreaAttribute;
import com.lzairport.ais.service.IService;


/**
 * ��������ʵ�����Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IAreaAttributeService extends IService<Integer, AreaAttribute> {
	
	/**
	 * 
	 * @Description: ��ȡ��������
	 * @return ��������
	 */
	public AreaAttribute getDomAttribute();
	
	/**
	 * 
	 * @Description: ��ȡ��������
	 * @return ��������
	 */
	public AreaAttribute getIntAttribute();
	
	/**
	 * 
	 * @Description: ��ȡ��������
	 * @return ��������
	 */
	public AreaAttribute getRegAttribute();

}
