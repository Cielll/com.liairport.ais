package com.lzairport.ais.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

/**
 * ���ö����似���Ĺ�����
 * @author ZhangYu
 * @version 0.9a 28/06/14
 * @since JDK 1.6
 */



public class ObjectMethodUtil {
	
	
	/**
	 *   BeanUitls���Զ���ע����ע���־
	 */
	private static boolean convertRegister = false; 
	
	
	
	
	/**
	 * ��ȡ��Ա������Read�������ַ���
	 * @param fieldname �ֶ���
	 * @return Read�������ַ���
	 */
	private static String getReadMethodName(String fieldname){
		return "get" + fieldname.substring(0, 1).toUpperCase()
				+ fieldname.substring(1);
	}
	
	
	/**
	 * ��ȡ��Ա������Set�������ַ���
	 * @param fieldname �ֶ���
	 * @returnSet�������ַ��� 
	 */
	private static String getSetMethodName(String fieldname){
		return "set" + fieldname.substring(0, 1).toUpperCase()
				+ fieldname.substring(1);
	}
	/**
	 * ��ȡָ���ֶε�get������׷�ݵ�����
	 * @param clazz ʵ��������
	 * @param field �ֶ�
	 * @return GET����
	 */

	private static Method getReadModelMethod(Class<?> clazz,String field){
		Method m = null;
		try {
			m= clazz.getDeclaredMethod(getReadMethodName(field));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			if  (clazz.getSuperclass().getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
				m = getReadModelMethod(clazz.getSuperclass(), field);
			}else{
			e.printStackTrace();
			}
		}
		return m;
	}
	
	/**
	 * ��ȡָ���ֶε�Set������׷�ݵ�����
	 * @param clazz ʵ��������
	 * @param field ָ���ֶ�
	 * @return Set����
	 */

	private static Method getSetModelMethod(Class<?> clazz,String field,Class<?> parmClass){
		Method m = null;
		try {

			m= clazz.getDeclaredMethod(getSetMethodName(field),parmClass);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			if  (clazz.getSuperclass().getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
				m = getSetModelMethod(clazz.getSuperclass(), field,parmClass);
			}else{
			e.printStackTrace();
			}
		}
		return m;
	}
	
	/**
	 * ��ȡ���ݶ�������Ӧ���ֶε�ֵ
	 * @param obj ���ݶ���
	 * @param field �ֶ���
	 * @return ��Ӧ���ֶε�ֵ
	 */
	public static Object getFieldObject(Object obj, String field) {
		Method m=null;

		Object returnobj = null;
			if (obj != null && field != null) {
				try {
					m = getReadModelMethod(obj.getClass(), field);
					returnobj = m.invoke(obj);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return returnobj;
	}
	
	/**
	 * ֱ�ӻ�ȡ���ݶ�������Ӧ���ֶε�ֵ
	 * @param obj ���ݶ���
	 * @param field �ֶ���
	 * @return ��Ӧ���ֶε�ֵ
	 */
	public static Object getDirectFieldObject(Object obj, String field) {
		Method m=null;

		Object returnobj = null;
			if (obj != null && field != null) {
				
				try {
					m= obj.getClass().getDeclaredMethod("get" +field);
					returnobj = m.invoke(obj);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return returnobj;
	}

	
	/**
	 * �������ݶ�������Ӧ���ֶε�ֵ
	 * @param obj ���ݶ���
	 * @param field �ֶ���
	 * @param parm ���õ�����
	 * @param parmClass �������ݵ�����
	 */
	public static void setFieldObject(Object obj, String field,Object parm,Class<?> parmClass) {
		Method m;
		if (obj != null && field != null) {
			try {
				if (parm.getClass() != parmClass){
					//����������ݵ��������ַ������ֶζ�Ӧ�����Ͳ�Ϊ�ַ���
					if (parmClass == int.class){
						//����ֶζ�Ӧ���������ͣ�ת��Ϊ����
						parm = Integer.parseInt(parm.toString());
					}
					
					if (parmClass == Date.class){
						//����ֶζ�Ӧ���������ڣ�ת��Ϊ��������
						parm =  DateTimeUtil.strToDate(parm.toString());
					}
					
					if(parmClass == double.class){
						parm = Double.parseDouble(parm.toString());
					}
					
					if(parmClass == boolean.class){
						//����ֶζ�Ӧ�����ǲ����ͣ�ת��Ϊ������
						parm = Boolean.valueOf(parm.toString());
					}
					
				}
				m = getSetModelMethod(obj.getClass(), field,parmClass);
				m.invoke(obj,parm);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				System.out.println(field+" "+parm+" "+parmClass+parm.getClass());
				//e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * ��ȡ���Ӧ�ֶε�����
	 * @param clazz ���ݶ��������
	 * @param field �ֶ���
	 * @return ��Ӧ�ֶε�����
	 */
	public static Class<?> getFieldType(Class<?> clazz, String field){
		Method method = null;
		method = getReadModelMethod(clazz, field);
		if (method != null){
			return method.getReturnType();
		}else {
			return null;
		}
	}
	

	/**
	 * �ж��ֶ��Ƿ�Ϊ��Ա����
	 * @param obj ���ݶ���
	 * @param field �ֶ���
	 * @return �ǻ��߷�
	 */

	public static Boolean isField(Class<?> clazz, String field){

		if (clazz == null){

			return false;
		}else {
			try {
				clazz.getDeclaredMethod(getReadMethodName(field));
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				if  (clazz.getSuperclass().getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
					return isField(clazz.getSuperclass(),field);
				}else{
					return false;
				}
			} 
		}	
		return true;	
			
	}
	
/**
 * ����ʵ�������е����԰�������	
 * @param clazz
 * @return ��������
 */
	public static Field[] getModelField(Class<?> clazz){
		List<Field> fields = new ArrayList<Field>(); 
		if (clazz.getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			if (clazz.getSuperclass() != null){
				//����и��࣬�ݹ�ļ�������
				fields.addAll(Arrays.asList(getModelField(clazz.getSuperclass()))); 
 			}
		}
		
		return fields.toArray(new Field[fields.size()]);
		
	}
	
	
	
	/**
	 * ��ȡ�����ֶε�ָ����ŷ��͵�ʵ������
	 * @param obj ����
	 * @param fieldName  ָ���ֶ�
	 * @return ���͵�ʵ������
	 */
	public  static   Type getFieldGenericType(Object obj,String fieldName,Integer index){

		try {
			ParameterizedType type = null;
			type = (ParameterizedType)obj.getClass().
					getDeclaredField(fieldName).getGenericType();
			return  type.getActualTypeArguments()[index];
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ���������������ͬ���Ƶ����Եķ���
	 * @param dest Ŀ�����
	 * @param orig  ԭ����
	 * @throws Exception ���ָ�ֵ�е�����
	 */
	public static void copybean(Object dest,Object orig) throws Exception{
		
		if (!convertRegister){
			ConvertUtils.register(new DateConverter(null), java.util.Date.class);   
		}
		BeanUtils.copyProperties(dest, orig);
	}


	
}
