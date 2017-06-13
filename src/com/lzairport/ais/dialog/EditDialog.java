package com.lzairport.ais.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import com.lzairport.ais.service.IService;

/**
 * ͨ�õ��޸�ʵ�����Ĵ���
 * ���ڵ���һ���޸����ݶԻ��򴰿����޸�ʵ�����ĸ�������
 * @author ZhangYu
 * @version 0.9a 25/06/14
 * @since JDK 1.6
 */


public abstract class EditDialog extends Dialog {
	
	/**
	 *  ���ݶ�����в�����Service����
	 */


	protected  IService<Integer, ? extends Object> service;
	
	/**
	 *  �޸ĵ����ݶ���
	 */
	protected Object object;

	private boolean flagAdd = false;
	



	public EditDialog(IService<Integer, ? extends Object> service,Object object) {
		super(Display.getDefault().getActiveShell());
		this.service =  service;
		if (object == null && service != null){
			flagAdd = true;
			try {
				
				//ȡ��Service���������������
				String className = service.getModelClass().getName();
				//����һ�����ݶ���
				object =  Class.forName(className).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}			
		}
		
		this.object = object;
	}
	
	
	/**
	 * ����Ҫ����Ķ�����޸ĶԻ�����и�ֵ
	 */
 
	protected abstract void objToDialog(Object object);
	
	
	/**
	 *  ���޸ĶԻ���������ݶ��账�������object����Ա�������и�ֵ
	 * @throws Exception 
	 */
	protected abstract void dialogToObj(Object object) throws Exception;
	
	/**
	 * �������������������Dialog�Ի����еĸ�Ԫ��
	 * @param composite ����Ŀؼ�
	 * @return composite  ��������и�Ԫ�صĶԻ���
	 */
	protected abstract Composite createDialogComposite(Composite composite);
	
	
	/**
	 * ��д�ķ����������Ի�������ĸ�Ԫ��
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		createDialogComposite(container);
		objToDialog(object);
		return container;
	}
	
	
	/**
	 * ����ȷ�� ȡ����ť
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "ȷ��",
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				"ȡ��", false);
	}

	
	/**
	 * ����ȷ����Ĳ���
	 */



	@Override
	protected void okPressed() {
		try {
			if (!flagAdd) {
				//���ݸ��²���
				dialogToObj(object);
				service.update(object);
			}else{
				dialogToObj(object);
				service.add(object);
			}
		} catch (Exception e) {

			MessageDialog.openError(Display.getDefault().getActiveShell(),
					"����", "���ݱ������´�������ϵϵͳ����Ա");
			e.printStackTrace();
		}
		super.okPressed();
	}
	
	

}
