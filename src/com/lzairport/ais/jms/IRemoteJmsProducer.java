package com.lzairport.ais.jms;

import javax.ejb.Remote;

/**
 * ��Ϣ�����߽ӿ�
 * @author ZhangYu
 * @since JDK 1.6
 * @version 0.9a 26/06/14
 * 
 */

@Remote
public interface IRemoteJmsProducer {
	
	/**
	 * ����jms��Ϣ
	 * @param xmlMesaage  XML��ʽ����Ϣ
	 */
	public void noticeContentChange(String xmlMesaage);
}