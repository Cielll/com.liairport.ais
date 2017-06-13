package com.lzairport.ais.dao.statistics.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.statistics.IUploadTypeDao;
import com.lzairport.ais.models.statistics.UploadType;


/**
 * 
 * FileName      UploadTypeDao.java
 * @Description  ͳ��ϵͳ�ϴ���������Dao��ʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��1��12�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��1��12��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class UploadTypeDao extends AodbDaoImpl<Integer, UploadType> implements IUploadTypeDao {


}
