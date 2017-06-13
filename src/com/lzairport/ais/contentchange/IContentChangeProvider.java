package com.lzairport.ais.contentchange;


/**
 * ���ݸı�֪ͨ�ӿ�
 * ����֪ͨע���������Listener���ݸı�
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 */


public interface IContentChangeProvider {
	
	/**
	 * ע��Listener�ķ�������Listener����֪ͨ�б���
	 * @param listener IPropertyChangeListener�Ľӿ�
	 */
	public abstract void addContentChangeListener(
			IContentChangeListener listener);

	/**
	 * ע��Listener����,��Listener��֪ͨ�б���ɾ��
	 * @param listener
	 */
	public abstract void removeContentChangeListener(
			IContentChangeListener listener);

	/**
	 * Provider���ݱ仯����÷���
	 * һ�����֪ͨ�б����Listener��contentChange����������֪ͨ��Listener���ݷ������
	 * @param property ��Ϣ���ͣ��ַ����ͣ�
	 * @param obj �仯�Ķ���
	 */
	public abstract void contentChangeInvoke(String property, Object newValue) ;


}
