package com.lzairport.ais.tableviewer.base;

import org.eclipse.jface.viewers.Viewer;
/**
 * �����������������
 * @author ZhangYu
 * @version 0.9b 12/03/12
 * @since JDK 1.6
 *
 */
import org.eclipse.jface.viewers.ViewerSorter;

import com.lzairport.ais.tableviewer.celldata.CellFactory;
import com.lzairport.ais.tableviewer.celldata.ICell;
import com.lzairport.ais.tableviewer.header.HeaderItem;


/**
 * ͨ�õ�������<p>
 * ��һ�±�ͷΪ�����ٵ�һ��Ϊ����
 * @author ZhangYu
 * version 0.9a 28/06/14
 * @since JDK 1.6
 * 
 */

public class CommViewSorter extends ViewerSorter {
	
	/**
	 *  ��Ӧ����
	 */
	private HeaderItem field;

	/**
	 *  �������־
	 */
	private boolean asc;
	
	public CommViewSorter(HeaderItem field,boolean asc) {
		super();
		this.field = field;
		this.asc = asc;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		// TODO Auto-generated method stub
		//ȡ�ñȽϵ�������������Ӧ�е�����
		int result = 0;
		Object obj1 = null;
		Object obj2 = null;
		
		
		ICell cell = CellFactory.createCell(e1,field);
		if (cell != null){
			obj1 = cell.getData(e1, field);
			obj2 = cell.getData(e2, field);
		}
		
		
		
		//�����Ƚ��Ϊ0��e1С��e2Ϊ-1��e1����e2Ϊ1
		if ((obj1 == null) &&(obj2 == null)){
			result = 0;
		}else if (obj1 == null) {
			result = -1;
		}else if (obj2 == null){
			result = 1;
		}else if (obj1 instanceof Comparable){
			result =  ((Comparable) obj1).compareTo(obj2);
		}
		
		//���������������з�ת
		if (!asc) {
			result = -result;
		}
		
		return result;
	}
	
	

}
