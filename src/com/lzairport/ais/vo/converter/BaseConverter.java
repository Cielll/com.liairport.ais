/**
 * 
 */
package com.lzairport.ais.vo.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import com.lzairport.ais.utils.AisBeanUtils;
import com.lzairport.ais.utils.SYS_VARS.ConverterFlag;

/**
 * FileName      BaseConverter.java
 * @Description  Vo�����Po����ת���Ļ���
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015��10��2�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015��10��2��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */
public abstract class BaseConverter<E, V>  implements IConverter<E,V> {
	
	protected Class<V> voClazz;
	
	protected Class<E> entityClazz;
	
	
	
	
	@SuppressWarnings("unchecked")
	public BaseConverter() {
		ParameterizedType  type  = (ParameterizedType)this.getClass().getGenericSuperclass();
		entityClazz = (Class<E>) type.getActualTypeArguments()[0];
		voClazz     = (Class<V>) type.getActualTypeArguments()[1];
	}

	@Override
	public V getVOject(E entity) throws Exception{
		
		V vo = voClazz.newInstance();
		copyProperties(vo, entity, ConverterFlag.PO2VO);
		return vo;
		
	}
	
	@Override
	public E getEntity(V vo) throws Exception{
		E   entity = findEntity(vo);
		if (entity == null){
			entity = entityClazz.newInstance();
		}
		copyProperties(vo, entity, ConverterFlag.VO2PO);
		return entity;
	}

	
	
	
	 /** 
     * ֵ�����������֮�����Ը��� 
     * @param dto    ֵ���� 
     * @param domain ����� 
     * @param flag   ���Ʒ��� 
	 * @throws Exception 
     */  
    protected void copyProperties(V dto, E domain, ConverterFlag flag) throws Exception {  
    	
        switch (flag) {  
  
            case PO2VO:  
                copySameProperties(dto, domain);  
                copyDiffPropertiesFromPO2VO(dto, domain);  
                break;  
  
            case VO2PO:  
                copySameProperties(domain, dto);  
                copyDiffPropertiesFromVO2PO(domain, dto);  
                break;  
  
            default:  
                break;  
        }  
  
    }  
  
    /** 
     * ͬ�����Ը��� 
     *  
     * @param target Ŀ����� 
     * @param source ��Դ���� 
     * @throws Exception 
     * @throws InvocationTargetException 
     */  
    protected void copySameProperties(Object target, Object source) throws  Exception {  
  
         AisBeanUtils.copyProperties(target, source);  
    }  
  
    protected abstract E findEntity(V vo);
    
    /** 
     * VO��ͬ�����Ը��Ƶ�PO���� 
     * @param target  ����� 
     * @param source  ֵ���� 
     * @throws Exception 
     */  
    public abstract void copyDiffPropertiesFromVO2PO(E target, V source) throws Exception;  
  
    /** 
     * PO��ͬ�����Ը��Ƶ�VO���� 
     * @param target  ֵ���� 
     * @param source  ����� 
     * @throws Exception 
     */  
    public abstract void copyDiffPropertiesFromPO2VO(V target, E source) throws Exception;  
  	
	

}
