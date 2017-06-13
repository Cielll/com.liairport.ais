package com.lzairport.ais.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * ������Ϣ��ʵ����
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6	
 */
@Entity
public class ErrorLog extends DefaultEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * ���������ֶ���,����ֱ�ӵ����ֶ���
	 */
	public static String ID="id";
	
	public static String TIME="time";
	
	public static String ERRSIMPLEMESSAGE="errSimpleMessage";
	
	public static String ERRLOG="errLog";
			
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;		
	
	private Date time;
	
	private String errSimpleMessage;
	
	private String errLog;
	
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Object id) {
		// TODO Auto-generated method stub
		this.id = (Integer) id;
	}
	
	
	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	/**
	 * @return the errSimpleMessage
	 */
	public String getErrSimpleMessage() {
		return errSimpleMessage;
	}
	/**
	 * @param errSimpleMessage the errSimpleMessage to set
	 */
	public void setErrSimpleMessage(String errSimpleMessage) {
		this.errSimpleMessage = errSimpleMessage;
	}
	/**
	 * @return the errLog
	 */
	public String getErrLog() {
		return errLog;
	}
	/**
	 * @param errLog the errLog to set
	 */
	public void setErrLog(String errLog) {
		this.errLog = errLog;
	}

	

}
