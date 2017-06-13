package com.lzairport.ais.service.statistics;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.UploadType;
import com.lzairport.ais.service.IService;

/**
 * 
 * FileName      IUploadTypeService.java
 * @Description  ͳ��ϵͳ�ϴ���¼����Service�ӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��1��12�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��1��12��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Remote
public interface IUploadTypeService extends IService<Integer, UploadType> {
	
	/**
	 * 
	 * @Description: ������ͳ��ϵͳ��������
	 * @return
	 */
	public UploadType getAspLoadType();
	
	/**
	 * 
	 * @Description: ������ͳ��ϵͳ�ɻ�����
	 * @return
	 */
	public UploadType getAspPlaneType();
	
	/**
	 * 
	 * @Description: �������ݻ���PISϵͳ����
	 * @return
	 */
	public UploadType getPisDynType();

}
