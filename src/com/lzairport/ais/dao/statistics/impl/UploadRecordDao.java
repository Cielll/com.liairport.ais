package com.lzairport.ais.dao.statistics.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.statistics.IUploadRecordDao;
import com.lzairport.ais.models.statistics.UploadRecord;


/**
 * 
 * FileName      UploadRecordDao.java
 * @Description  ͳ���ϴ���¼��Dao��ʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��1��12�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��1��12��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class UploadRecordDao extends AodbDaoImpl<Integer, UploadRecord> implements IUploadRecordDao {

	
}
