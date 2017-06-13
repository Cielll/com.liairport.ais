package com.lzairport.ais.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * XML�Ĺ�����
 * @author ZhangYu
 * @version 0.9a 28/06/14
 * @since JDK 1.6
 */
public class XMLUtil {
	
	private static XStream xStream = new XStream(new DomDriver());
	
	private static Document document;
	

	
	/**
	 * ����Element�Ľڵ�
	 * @param xml  XML�ı�
	 * @param keyText �ؼ��ַ��� 
	 * @return keyText����Ӧ��Element
	 */
	private static Element findElement(String xml,String keyText){
		try {
			document = DocumentHelper.parseText(xml);
			Element rootElement = document.getRootElement();
			String eventText = rootElement.element(EventCodeUtil.Eventproperty).getText();
			Element element = rootElement.element(eventText);
			element = element.element(keyText);
			return element;
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	
	/**
	 * ����ת��ΪXML��Element
	 * @param object �������
	 * @return XML��Element
	 */
	public static Element objectToElement(Object object){
		try {
			
			document = DocumentHelper.parseText(xStream.toXML(object));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document.getRootElement();
		
	}

	/**
	 * ��XML�ı�ת��Ϊ����
	 * @param xml XML�ı�
	 * @return ����
	 */
	public static Object documentGetObject (String xml){
		//ȡ�����ݽڵ㣬Ȼ��ת��Ϊ����
		Element element = findElement(xml, EventCodeUtil.EventContent);
		element = (Element) element.elementIterator().next();
		return xStream.fromXML(element.asXML());
			 
	}
	
	/**
	 * ��ȡXML�ı������Խڵ���ַ���
	 * @param xml XML�ı�
	 * @return ���Խڵ���ַ���
	 */
	public static String documentGetProperty(String xml){
		Element element = findElement(xml, EventCodeUtil.Eventproperty);
		return element.getText();
		
	}

	
}
