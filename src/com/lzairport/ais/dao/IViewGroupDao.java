package com.lzairport.ais.dao;

import javax.ejb.Local;
import com.lzairport.ais.models.ViewGroup;


/**
 * ��ʾ����ViewGroup��Dao��ӿ�
 * @author ZhangYu
 * @version 0.9a  24/06/14
 * @since JDK 1.6
 *
 */

@Local
public interface IViewGroupDao extends IDao<Integer, ViewGroup> {

}
