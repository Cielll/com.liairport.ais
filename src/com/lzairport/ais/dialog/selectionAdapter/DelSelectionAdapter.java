package com.lzairport.ais.dialog.selectionAdapter;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import com.lzairport.ais.service.IService;

/**
 * ͨ��ɾ��ѡ�����������
 * @author ZhangYu
 * @version 0.9a 25/06/24
 * @since JDK 1.6
 */


public class DelSelectionAdapter extends SelectionAdapter {
	
	/**
	 * �������ݶ����Service
	 */
	private IService<Integer,? extends Object> service;
	
	/**
	 * ��Ҫ��������ݶ���
	 */
	private Object object;

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
	
	public DelSelectionAdapter(IService<Integer,? extends Object> service) {
		super();
		this.service = service;
	}

	
	/**
	 * ����ɾ����¼�Ի�����ȷ�Ϻ�ɾ������
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {
		if (MessageDialog.openQuestion(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), "ɾ����¼", "�Ƿ�Ҫɾ���˼�¼")) {
			try {
				service.remove(object);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				MessageDialog.openError(Display.getDefault().getActiveShell(), 
						"����", "�޷�ɾ���ü�¼���п����Ǹü�¼�й�����Ϣ������ϵϵͳ����Ա");
			}
		}		
		super.widgetSelected(e);
	}
	
	

}
