package com.lzairport.ais.jms;

/**
 * ��¼�ı�ʵ����ID�������Ķ�������JMS��Ϣ��֪ͨ����ͼ����
 * @author ZhangYu
 * @version 0.9a 05/26/15
 * @since JDK 1.6
 *
 */



public class ChangeEntityInfo   {
	
	private Object id;
	
	private Class<?> clazz;

	/**
	 * @return the id
	 */
	public Object getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Object id) {
		this.id = id;
	}

	/**
	 * @return the clazz
	 */
	public Class<?> getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	
	

}
