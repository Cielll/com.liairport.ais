package com.lzairport.ais.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.IViewGroupDao;
import com.lzairport.ais.models.ViewGroup;
import com.lzairport.ais.service.IViewGroupService;

/**
 * ��ʾ����ViewGroup��Service��ӿڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a  01/05/15
 * @since JDK 1.6
 *
 */

@Stateless

public class ViewGroupService extends Service<Integer, ViewGroup> implements
		IViewGroupService  {

	@EJB
	public void setViewGroupDao(IViewGroupDao viewGroupDao){
		setDao(viewGroupDao);
	}


	

}
