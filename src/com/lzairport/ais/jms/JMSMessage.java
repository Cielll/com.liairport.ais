package com.lzairport.ais.jms;



import javax.annotation.PreDestroy;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.springframework.stereotype.Component;

import com.lzairport.ais.contentchange.ContentChangeProvider;
import com.lzairport.ais.utils.JNDIUtil;
import com.lzairport.ais.utils.XMLUtil;

/**
 * Jms��Ϣ������
 * �ͻ������ڽ���Jms���ݿ�����Ϣ��������Ӧ���¼�֪ͨ����ͼ��������
 * @author ZhangYu
 * @version 0.9a 26/06/14
 * @since JDK 1.6
 *
 */


@Component
public class JMSMessage  extends ContentChangeProvider implements MessageListener {
	
	/**
	 *  jms����
	 */
    private Connection connection;
    /**
     *  jms���ӹ���
     */
    private ConnectionFactory connectionFactory;
    /**
     *  ��Ϣ����topic
     */
    private Topic topic;
    
	
    /**
     * ����jms���ӣ���ʼ����jms��Ϣ
     */
    
	public void init (){
			//�������ӹ����ҵ���Ϣ����topic
		    connectionFactory =  JNDIUtil.lookupJndi(JNDIUtil.DEFAULT_CONNECTION_FACTORY);
			topic =  JNDIUtil.lookupJndi(JNDIUtil.Ais_App_Topics);
			try {
				connection = connectionFactory.createConnection();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				//������Ϣ�����߲�ʹ��������
				MessageConsumer consumer = session.createConsumer(topic);
				consumer.setMessageListener(this);
				connection.start();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	/**
	 * ��������ر�jms����
	 */
	
	@PreDestroy
	public void destroy() {
		try {
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *  ���յ�jms���д�����Ϣ
	 */

	@Override
	public void onMessage(Message msg) {
		try {
			//����Xml���ݱ�Ϊʵ�����entity�������仯�Ĳ�������property
			
			String xml =  ((TextMessage)msg).getText();
			String property = XMLUtil.documentGetProperty(xml);
			Object entityInfo = XMLUtil.documentGetObject(xml);
			//֪ͨע��������ļ����� ʵ��������仯������Ӧ�ĸı�
			contentChangeInvoke(property,entityInfo);

		} catch (JMSException e) {
			e.printStackTrace();
		} 
	}

}
