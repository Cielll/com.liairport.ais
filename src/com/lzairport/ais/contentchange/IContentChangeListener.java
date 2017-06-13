package com.lzairport.ais.contentchange;

import java.beans.PropertyChangeEvent;
import java.util.EventListener;

/**
 * �������ӿ�
 * ����ע����Provider���Խ���Provider���ݸı�
 * @author ZhangYu
 * @since JDK1.6
 * @version 09.a 24/06/2014
 */

public interface IContentChangeListener extends EventListener {
	
	
	/**
	 * Provider���ݸı�����ô˷��������ڴ������ݱ仯���߼�
	 * @param evt  ���ݱ仯�¼�
	 */
	void contentChange(PropertyChangeEvent evt)  throws Exception;

}
