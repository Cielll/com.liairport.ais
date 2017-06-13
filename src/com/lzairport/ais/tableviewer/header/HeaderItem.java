package com.lzairport.ais.tableviewer.header;

import java.io.Serializable;

/**
 * ���ͷ�Ĳ���
 * @author ZhangYu
 * version 0.9a 28/06/14
 * @since JDK 1.6
 */

public class HeaderItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ����
	 */
	private String name;
	
	/**
	 * ���
	 */
	private int width;
	
	/**
	 * �ֶ�
	 */
	private String Ename;
	
	/**
	 * ���ֶ�
	 */
	private String subEname;
	
	
	/**
	 * �ֶ�����
	 */
	private String itemType;
	
	/**
	 * ��ѯ������һ������Date�ֶ�
	 */
	private int findNum;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	public String getEname() {
		return Ename;
	}
	public void setEname(String ename) {
		Ename = ename;
	}
	public String getSubEname() {
		return subEname;
	}
	public void setSubEname(String subEname) {
		this.subEname = subEname;
	}
	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}
	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	/**
	 * @return the findNum
	 */
	public int getFindNum() {
		return findNum;
	}
	/**
	 * @param findNum the findNum to set
	 */
	public void setFindNum(int findNum) {
		this.findNum = findNum;
	}
	

}
