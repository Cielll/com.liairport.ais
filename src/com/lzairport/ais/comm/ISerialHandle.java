package com.lzairport.ais.comm;


import java.io.OutputStream;

import com.lzairport.ais.exception.SerialConnectionException;

/**
 * ���ڴ��������ݵĳ���ӿ�,��SerialConnection�Ĺ��캯���д���.<p>
 * ����ʵ���ϴ������������ݺ�������ݵĽӿ�.
 * @author ZhangYu
 * @version 0.9b 11/30/12
 * @since JDK 1.6
 * @see SerialConnection
 */

public interface ISerialHandle {
	
	

	/**
	 * �������봮�����ݴ���ķ���
	 * @param InData
	 */

	public abstract void InDataHandle(String InData) throws  Exception;


	/**
	 * ��������������ݴ���÷���
	 * @param OutData
	 * @throws SerialConnectionException
	 * @throws Exception 
	 */
	public abstract void OutDataHandle(String OutData)
			throws Exception;

	/**
	 * ȡ�ô��������������IO
	 * @return the os
	 */
	public abstract OutputStream getOs();

	/**
	 * 
	 * ���ô��������������IO,��SerialConnection���и�ֵ
	 * @param os the os to set
	 */
	public abstract void setOs(OutputStream os);

}