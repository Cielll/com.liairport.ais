package com.lzairport.ais.tableviewer.base;

import java.io.Serializable;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.service.IService;

/**
 * �������ݷ����࣬�������ñ���Input��������
 * @author ZhangYu
 * version 0.9a 28/07/14
 * @since JDK 1.6
 * 
 */

public class TableViewerService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * �����ѯ������
	 */
	private QueryConditions conditions;
	
	/**
	 * ʵ�ʵ����ݿ��Service��
	 * 
	 */

	private IService<Integer,? extends Object> service;
	
	/**
	 * @return the service
	 */

	public IService<Integer,? extends Object> getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */

	public void setService(IService<Integer, ? extends Object> service) {
		this.service = service;
	}

	/**
	 * @return the conditions
	 */
	public QueryConditions getConditions() {
		return conditions;
	}

	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(QueryConditions conditions) {
		this.conditions = conditions;
	}


	
	
	
}
