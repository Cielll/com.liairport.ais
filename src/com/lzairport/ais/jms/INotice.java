package com.lzairport.ais.jms;

import javax.ejb.Remote;

/**
 * ���ݸı�ʱ֪ͨ���ͻ��˵Ľӿ�
 * @author ZhangYu
 * @since JDK 1.6 
 * @version 0.9a 26/06/14 
 * 
* */

@Remote
public interface INotice {

	/**
	 * ���ݸı�֪ͨ
	 * @param eventCode �¼�����
	 * @param property �ı����� �磺������ɾ��������
	 * @param entity ʵ����
	*/
	public abstract void changeNotice(String eventCode,String property, Object entity);

	
	
}