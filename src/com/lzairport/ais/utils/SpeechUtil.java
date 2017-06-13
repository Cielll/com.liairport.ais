package com.lzairport.ais.utils;

import java.util.ArrayList;
import java.util.List;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.lzairport.ais.models.aodb.Flight;

/**
 * �����Ĺ�����
 * @author ZhangYu
 * @version 0.9a 13/05/14
 * @since JDK1.6 
 * 
 */
public class SpeechUtil extends Thread {
	
	private String message;

	public SpeechUtil(String message) {
		super();
		this.message = message;
	}

	/**
	 * ����������ִ�ת�����������
	 * @param message ��Ҫת�����ַ���
	 */
	public synchronized void speakMessage() {  
		ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");   
		Dispatch sapo = sap.getObject();  
        try {  
            sap.setProperty("Volume", new Variant(80));  
            sap.setProperty("Rate", new Variant(0));  
            Dispatch.call(sapo, "Speak", new Object[]{message});  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            sapo.safeRelease();  
            sap.safeRelease();  
        }  
    }
	
	/**
	 * ������ĺ���ű���񺽵����ֶ������ַ���
	 * @param flight
	 * @return  ת������ַ���
	 */
	public static String getFlightVoiceNo(Flight flight){
		
		String airline = "";
		
		if (flight.getAircraft() != null){
			airline = flight.getAircraft().getCarrier().getAirlines().getCnShortName().replace("����", "");
		}
		
		List<String> listNo = new ArrayList<String>();
		String flightNo=airline;
		listNo.add(flight.getFlightNO().substring(2,3));
		listNo.add(" ");
		listNo.add(flight.getFlightNO().substring(3,4));
		listNo.add(" ");
		listNo.add(flight.getFlightNO().substring(4,5));
		listNo.add(" ");
		listNo.add(flight.getFlightNO().substring(5,6));
		
		for (String no:listNo){
			if (no.equals("0")){
				no = "��";
			}else if (no.equals("7")){
				no = "��";
			}else if (no.equals("2")){
				no = "��";
			}else if (no.equals("1")){
				no = "��";
			}
			flightNo +=no;
		}
		return flightNo;
	}

	@Override
	public void run() {
		speakMessage();
	}

	
}
