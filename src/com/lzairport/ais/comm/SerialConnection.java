package com.lzairport.ais.comm;

import gnu.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import com.lzairport.ais.exception.SerialConnectionException;


/**
 * �������ӵ�ʵ���ࡣ<p>
 * ʵ�ִ򿪴��ڣ�������ʵ�ʴ����ڲ�����SerialHandle��
 * @author ZhangYu
 * @version 0.9b 11/30/12
 * @since JDK 1.6
 * @see ISerialHandle
 *
 */


@Component
public class SerialConnection implements SerialPortEventListener	{ 

	@Resource
	private ISerialHandle serialHandle;
	@Resource
	private SerialParameters parameters;
	private InputStream is;
	private CommPortIdentifier portId;
	private SerialPort sPort;
	private boolean open;
	
	/**
	 * ��Ĺ��캯�����Դ������Ӳ�������Ҫ������и�ֵ��
	 * @param serialHandle �Դ������ݲ���ʵ�ʵ���
	 * @param parameters ���ڲ���
	 */

	public SerialConnection() {
		super();
		open = false;
	}
	
	
	

	public void init() throws SerialConnectionException{
		this.openConnection();
		
	}
	
 

	/**
	 * @return the serialHandle
	 */
	public ISerialHandle getSerialHandle() {
		return serialHandle;
	}





	/**
     * ����parameters�������ô������Ӳ��������ʧ����ָ�ԭ�ȵĴ������Ӳ������ò��׳��쳣
     */
    public void setConnectionParameters() throws SerialConnectionException {

    	// ����ԭ�ȵ�����
    	int oldBaudRate = sPort.getBaudRate();
    	int oldDatabits = sPort.getDataBits();
    	int oldStopbits = sPort.getStopBits();
    	int oldParity   = sPort.getParity();

    	// 	���ô������Ӳ��������ʧ����ָ�ԭ�ȵĴ������Ӳ�������
    	try {
    		sPort.setSerialPortParams(parameters.getBaudRate(),
				      parameters.getDatabits(),
				      parameters.getStopbits(),
				      parameters.getParity());
    	} catch (UnsupportedCommOperationException e) {
    		parameters.setBaudRate(oldBaudRate);
    		parameters.setDatabits(oldDatabits);
    		parameters.setStopbits(oldStopbits);
    		parameters.setParity(oldParity);
    		throw new SerialConnectionException("Unsupported parameter");
    	}

    	// ���� flow control.
    	try {
    		sPort.setFlowControlMode(parameters.getFlowControlIn() 
			           | parameters.getFlowControlOut());
    	} catch (UnsupportedCommOperationException e) {
    		throw new SerialConnectionException("Unsupported flow control");
    	}
    }


    /**
     * 
     * ������parameters�еĲ�����һ���������ӣ�����serialHandle��os(�������IO)
     *�ڴ򿪴��ڵ�ÿһ����������ɹ�����رմ��ڲ��׳�<code>SerialConnectionException</code>�쳣
     *��������Ĵ��ڱ�����Ӧ�ó���ռ�õȺ�30�롣
     * @throws SerialConnectionException 
     *  
     */
	public void openConnection() throws SerialConnectionException	{
				
		// ��ȡһ��parameters�е�ָ���˿ڵ�CommPortIdentifier�Ķ���
		try {
		    portId = 
			 CommPortIdentifier.getPortIdentifier(parameters.getPortName());
		} catch (NoSuchPortException e) {
		    throw new SerialConnectionException("NoSuchPort");
		}	

		//��CommPortIdentifier����򿪴��ڶ˿�
		try {
		    sPort = (SerialPort)portId.open("���ݻ����籨����ϵͳ", 30000);
		} catch (PortInUseException e) {
		    throw new SerialConnectionException("PortInUse");
		}
		
		//���ô������Ӳ���
		try {
		    setConnectionParameters();
		} catch (SerialConnectionException e) {	
		    sPort.close();
		    throw e;
		}

		// �򿪴��ڵ������������������󣬹رմ������Ӳ��׳��쳣
		try {
		    serialHandle.setOs(sPort.getOutputStream());
		    is = sPort.getInputStream();
		} catch (IOException e) {
		    sPort.close();
		    throw new SerialConnectionException("Error opening i/o streams");
		}
		
		//���봮���¼�������
		try {
		    sPort.addEventListener(this);
		} catch (TooManyListenersException e) {
		    sPort.close();
		    throw new SerialConnectionException("too many listeners added");
		}
		

		// �������ô��������ݵ��¼�Ϊ��
		sPort.notifyOnDataAvailable(true);
	    try {
			sPort.enableReceiveTimeout(30);
		} catch (UnsupportedCommOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		open = true;
	}
	
	/**
	 * �رմ���
	 */
	
    public void closeConnection() {
    	if (!open) {
    		return;
    	}


    	if (sPort != null) {
    		try {
    			// 	�ر����������
    			serialHandle.getOs().close();
    			is.close();
    		} catch (IOException e) {
    			System.err.println(e);
    		}
    		// �رն˿�
    		sPort.close();
    	    
    	}
    	open = false;
    }
    

    /**
     * �򴮿ڷ�������
     * @param serialData
     */
    public void sendSerialData(String serialData){
    	try {
			serialHandle.OutDataHandle(serialData);
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	
	

    /**
     * �����ڵ�ͨ���¼�
     * @throws SerialConnectionException
     * @see SerialPortEvent
     */
	@Override
	public void serialEvent(SerialPortEvent ev) {
	 	// ����һ�������ַ�������
		StringBuffer inputBuffer = new StringBuffer();
		int newData = 0;

		switch (ev.getEventType()) {
		    //�������ݵ����¼�
			case SerialPortEvent.DATA_AVAILABLE:
			    while (newData != -1) {
			    	try {
			    	    newData = is.read();
				    if (newData == -1) {
				    	break;
				    }
			    	inputBuffer.append((char)newData);
			    	} catch (IOException ex) {
			    	//	throw new SerialConnectionException("IO����");
			      	}
	   		    }
			//serialHandle���������ڻ�����������
			    try {
			    	serialHandle.InDataHandle(new String(inputBuffer));
			    } catch (Exception e) {
			    	// TODO Auto-generated catch block
			    	e.printStackTrace();
			    }
			    break;

			// ͨѶ�ж��¼�
		    case SerialPortEvent.BI:
		    //	throw new SerialConnectionException("ͨѶ�ж�");
		   
		    //�ز�����¼�	
		    case SerialPortEvent.CD:
		    	break;
		    	
		    //��������¼�
		    case SerialPortEvent.CTS:
		    	break;
		    	
		    //�����豸׼�����¼�
		    case SerialPortEvent.DSR:
		    	break;
		    	
		    //֡�����¼�	
		    case SerialPortEvent.FE:
		    	break;
		    	
		    //��λ����	
		    case SerialPortEvent.OE:
		    	break;
		    	
		    //��������������	
		    case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
		    	break;
		    	
		    //��żУ���
		    case SerialPortEvent.PE:
		    	break;
		    	
		    //����ָʾ
		    case SerialPortEvent.RI:
		    	break;
 	
		    	
		}

	}

}
