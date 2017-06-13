package com.lzairport.ais.utils;



/**
 * �¼����붨���
 * @author ZhangYu
 * @version 0.9a 28/06/14
 * @since JDK 1.6
 */


public class EventCodeUtil {
	//����XML�ڵ���ַ���
	/**
	 *   XML���ڵ� 
	 */
	public final static String Root="Event";
	
	/**
	 * XML�¼����Խڵ�
	 */
	public final static String Eventproperty="Property";
	
	/**
	 * XML���ݽڵ�
	 */
	public final static String EventContent="Content";
	
	/**
	 * XMLʵ����仯�¼��ڵ�
	 */
	public final static String EntityChangeEvent="EntityChangeEvent";
	
	/**
	 * ϵͳ�¼��ڵ�
	 */
	public final static String EventSystem="AisSystemEvent";
	
	
	//�����¼�����
	/**
	 * ���ڽ��¼�
	 */
	public final static String ComInEvent="ComIn";
	/**
	 * ���ڳ��¼�
	 */
	public final static String ComOutEvent="ComOut";
	
	
	//ʵ��仯�¼�����
	/**
	 *  ʵ���������¼�
	 */
	public final static String ModelsAdd="ModelAdd";
	/**
	 *  ʵ����ɾ���¼�
	 */
	public final static String ModelsRemove="ModelRemove";
	/**
	 *  ʵ��������¼�
	 */
	public final static String ModelsUpdate="ModelUpdate";
	/**
	 *  ʵ����ˢ���¼�
	 */
	public final static String ModelsRefresh="ModelRefresh";

	
	
	//Eterm�¼�����
	/**
	 * Eterm�ͻ��������¼�
	 */
	public final static String EventClientDataIn = "ClientDataIn";
	/**
	 * Eterm���������¼�
	 */
	public final static String EventEtermDataIn = "EtermDataIn";

	/**
	 * ���Ĳ����¼�
	 */
	public final static String EventTelexSave ="TelexSave";
	
	
	
	
	/**
	 * ����ϵͳ��Ӫ������ϵͳ��Ӫת���������¼�
	 */
	public final static String EventAisDayEnd="AisDayEnd";
	
	
}
