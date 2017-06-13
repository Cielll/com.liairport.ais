package com.lzairport.ais.jms;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.lzairport.ais.models.DefaultEntity;
import com.lzairport.ais.utils.EventCodeUtil;
import com.lzairport.ais.utils.XMLUtil;

/**
 *	���ݸı�ʱ֪ͨ���ͻ��˵Ľӿڵ�ʵ���� 
 * @author ZhangYu
 * @since JDK 1.6
 * @version 0.9a 26/06/14
 */

@Stateless
public class Notice  implements INotice{
	

	@EJB
	private IRemoteJmsProducer jmsProducer;



	@Override
	public void changeNotice(String eventCode,String property, Object obj) {
		String message = null;
		
		//�������仯��ʵ�����Id,����д���¼������
		ChangeEntityInfo entityInfo = new ChangeEntityInfo();
		entityInfo.setClazz(obj.getClass());
		entityInfo.setId(((DefaultEntity)obj).getId());
		
		// ����Xml��Ϣ�Թ�jms�����ߴ�����Ϣ
		Document document = DocumentHelper.createDocument();
		//���ڵ�
		Element  rootElement = document.addElement(EventCodeUtil.Root);
		//�¼�����ڵ�  ��ʵ����
		rootElement.addElement(EventCodeUtil.Eventproperty).setText(eventCode);
		Element  eventElement = rootElement.addElement(eventCode);
		//�¼����ͽڵ� ������ ɾ��
		eventElement.addElement(EventCodeUtil.Eventproperty).setText(property);
		//�����仯��ʵ�����ݽڵ㣬��ʵ����ת��ΪXml��ʽ
		eventElement.addElement(EventCodeUtil.EventContent).add(XMLUtil.objectToElement(entityInfo));
		
		message = rootElement.asXML();
		jmsProducer.noticeContentChange(message);
		
		
	}

}
