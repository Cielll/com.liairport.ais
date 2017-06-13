package com.lzairport.ais.dialog.selectionAdapter;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.dialog.EditDialog;
import com.lzairport.ais.dialog.EditDialogFactory;
import com.lzairport.ais.service.IService;


/**
 * ͨ�õı༭ѡ��������
 * @author ZhangYu
 * @version 0.9a 24/06/24
 * @since JDK 1.6
 */


public class EditSelectionAdapter extends SelectionAdapter {
	
	/**
	 * �������ݶ����Service
	 */
	private IService<Integer,? extends Object> service;
	
	
	/**
	 * ��Ҫ��������ݶ���
	 */

	private Object object;
	
	
	/**
	 *  Spring������
	 */
	private AbstractApplicationContext ctx;
	
	/**
	 *  ��ͼViewer��Ҫ��������ȡ����ͼƥ����ֶ�
	 */
	private Object viewer;

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}

	
	public EditSelectionAdapter(IService<Integer,? extends Object> service,AbstractApplicationContext ctx,Object viewer) {
		super();
		this.service = service;
		this.ctx = ctx;
		this.viewer = viewer;
	}
	
	
	/**
	 * ѡ������EditDialogFactory��������ݲ�ͬ��Viewer������ͬ��EditDialog
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {
		EditDialog dialog = EditDialogFactory.createEditDialog(service, object,ctx,viewer);
		dialog.open();
		super.widgetSelected(e);
	}

	
	

}
