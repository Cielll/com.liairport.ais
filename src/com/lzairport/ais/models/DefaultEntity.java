package com.lzairport.ais.models;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.lzairport.ais.jms.DefaultEntityListeners;

/**
 * ʵ�������Ҫ�̳еĸ���
 * ��Ҫע�� Ejbʵ�����������������ݷ����ı�����Ӧ��֪ͨ
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */

@MappedSuperclass
@EntityListeners(DefaultEntityListeners.class)
public abstract class DefaultEntity {

	/**
	 * ���������ֶ���,����ֱ�ӵ����ֶ���
	 */
	public static String ID="id";
	
	/**
	 * @return the id
	 */
	public abstract Object getId();

	/**
	 * @param id the id to set
	 */
	public abstract void setId(Object id); 
	
	
	/**
	 * 
	 * @Description: TODO(������һ�仰�����������������)
	 * @return
	 */
	public Object getIdValue(){
		return this.getId();
	}

	
	/**
	 * ��д��equals����
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (DefaultEntity.class.isAssignableFrom(obj.getClass())&&!this.getIdValue().equals(0)){
			//���obj��Entity����һ�»����������࣬�Ƚ�����Id
			//���ID Ϊ0 ˵��������û�н������ݿ⣬�޷���ID���бȽ�
			return (this.getIdValue() .equals(((DefaultEntity)obj).getIdValue()));
		}
		//�����ø���ķ������бȽϣ�һ���ǱȽ��ڴ��ַ
		return super.equals(obj);
	}

	
	
	

}
