package com.lzairport.ais.utils;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * ����JNDI������jndi��Դ�Ĺ�����
 * @author ZhangYu
 * @version 0.9a 28/06/14
 * @since JDK 1.6
 */

public class JNDIUtil {
	//JNDI�����ַ�����
	
	/**
	 *  JMS��Ϣ�����ߵ�JNDI�����ַ���
	 */
	public static final String JNDI_JmsProducer ="ejb:/AisEJB//JmsProducer!com.lzairport.ais.jms.ILocalJmsProducer";
	/**
	 * Notice֪ͨ�ߵ�JNDI�����ַ���
	 */
	public static final String JNDI_Notice="ejb:/AisEJB//Notice!com.lzairport.ais.models.listener.INotice";
	/**
	 *  ModelRelationDaoʵ�������DAO��JNDI�����ַ���
	 */
	public static final String JNDI_ModelRelationDao="ejb:/AisEJB//ModelRelationDao!com.lzairport.ais.dao.IModelRelationDao";
	
	
	/**
	 * JMS���ӹ���
	 */
    public static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    /**
     *  ���ķַ�Telex ��JMS����
     */
	public static final String Ais_App_Topics = "jms/ais/topics/app";
	
	
	private static final String INITIAL_EJB_CONTEXT_FACTORY="org.jboss.ejb.client.naming";

	
	
/**
 * ����EJB�ķ���	
 * @param jndiStr JNDI�����ַ���
 * @return ��Ӧ��EJB
 */
	@SuppressWarnings("unchecked")
	public static <T> T lookupEJB(String jndiStr){
		 
		T jndiObject = null;  
		Properties properties = new Properties();
		properties.put(Context.URL_PKG_PREFIXES,  INITIAL_EJB_CONTEXT_FACTORY);
		 try {
			Context context = new InitialContext(properties);
			jndiObject = 	 (T) context.lookup(jndiStr);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jndiObject;
		
	}
	
	
	
/**
 * ����Jndi�Ķ��󷽷�	
 * @param jndiStr JNDI�����ַ���
 * @return ��Ӧ��Jndi����
*/

	@SuppressWarnings("unchecked")
	public static <T> T lookupJndi(String jndiStr){
		T jndiObject = null;  
		Properties properties = new Properties();
		
		try {
			Context context = new InitialContext(properties);
			jndiObject = 	 (T) context.lookup(jndiStr);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jndiObject;
		
	}

}
