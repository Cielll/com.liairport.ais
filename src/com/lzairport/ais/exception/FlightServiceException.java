package com.lzairport.ais.exception;



/**
 * �����¼������׳����쳣��
 * @author ZhangYu
 * @version 0.9b 12/11/14
 * @since JDK1.6
 *
 */


public class FlightServiceException extends Exception {

	/**
	 * ����������Ϣ�Ĺ��췽��
	 */
	private static final long serialVersionUID = 1L;

	public FlightServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * ��������Ϣ�Ĺ��췽��
	 * @param message
	 */
	public FlightServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
