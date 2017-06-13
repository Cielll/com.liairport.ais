package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.HisFlightDisPatch;
import com.lzairport.ais.service.IService;


/**
 * ������ʷ���Ȼ��ڵ�Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 16/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IHisFlightDisPatchService extends
		IService<Integer, HisFlightDisPatch> {

}
