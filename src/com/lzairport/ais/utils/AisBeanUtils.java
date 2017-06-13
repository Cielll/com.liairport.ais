package com.lzairport.ais.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;

/**
 * 
 * FileName      AisBeanUtils.java
 * @Description  �Զ���������Կ�������
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015��10��2�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015��10��2��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

public class AisBeanUtils extends BeanUtils {

	private AisBeanUtils(){
		
	}
	
	static {
		 // ע��sql.date��ת������������BeanUtils.copyPropertiesʱ��ԴĿ���sql���͵�ֵ����Ϊ��  
        ConvertUtils.register(new SqlDateConverter(), java.util.Date.class);  
        // ע��util.date��ת������������BeanUtils.copyPropertiesʱ��ԴĿ���util���͵�ֵ����Ϊ��  
        ConvertUtils.register(new DateConverter(null), java.util.Date.class);   
	}
	
	public static void copyProperties(Object target, Object source)  
	        throws InvocationTargetException, IllegalAccessException {  
	  
	        BeanUtils.copyProperties(target, source);  
	}  
	

}
