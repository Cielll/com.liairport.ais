package com.lzairport.ais.service.statistics.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.statistics.IUploadRecordDao;
import com.lzairport.ais.models.statistics.UploadRecord;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IUploadRecordService;

/**
 * 
 * FileName      UploadRecordService.java
 * @Description  ͳ��ϵͳ�ϴ���¼��Service��ʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��1��12�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��1��12��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
@Stateless
public class UploadRecordService extends Service<Integer, UploadRecord> implements IUploadRecordService {

	@EJB
	public void setUploadRecordDao(IUploadRecordDao dao){
		setDao(dao);
	}
	

}
