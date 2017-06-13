package com.lzairport.ais.service.statistics;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.UploadRecord;
import com.lzairport.ais.service.IService;


/**
 * 
 * FileName      IUploadRecordService.java
 * @Description  ͳ��ϵͳ�ϴ���¼��Service�Ľӿ�
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��1��12�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��1��12��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Remote
public interface IUploadRecordService extends IService<Integer, UploadRecord> {

}
