package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.DynFlightDisPatch;
import com.lzairport.ais.service.IService;

/**
 * ���ද̬���Ȼ��ڵ�Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 16/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IDynFlightDisPatchService extends
		IService<Integer, DynFlightDisPatch> {

}
