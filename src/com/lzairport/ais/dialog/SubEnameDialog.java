package com.lzairport.ais.dialog;

import java.lang.reflect.Field;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import com.lzairport.ais.dialog.selectionAdapter.SubEnameSelectionAdapter;
import com.lzairport.ais.utils.ObjectMethodUtil;



/**
 * SubEname��ʾ���ֶ����ĶԻ���
 * ����ѡ���Ӧ���ֶ���ʵ�����ʾ�ֶ�
 * @author ZhangYu
 * @version 0.9a 06/08/14
 * @since JDK 1.6
 */

public class SubEnameDialog extends Dialog {

	/**
	 * ���ֶ�ѡ��������
	 * ���ڽ�������
	 */
	private SubEnameSelectionAdapter adapter;
	
	/**
	 * �������ʾ�ֶε�ʵ�����
	 */
	private Class<?> clazz;
	
	
	/**
	 * ��ʾʵ�����������ֶε�Combo
	 */
	private Combo cmbLkSubEname;
	
	public SubEnameDialog(SubEnameSelectionAdapter adapter) {
		super(Display.getDefault().getActiveShell());
		// TODO Auto-generated constructor stub
		this.adapter = adapter;
	}

	

	/**
	 * @return the clazz
	 */
	public Class<?> getClazz() {
		return clazz;
	}


	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}







	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(2, false));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label labLkSubEname = new Label(container, SWT.NONE);
		labLkSubEname.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		labLkSubEname.setText("\u663E\u793A\u7684\u5B50\u5B57\u6BB5\uFF1A");
		
		cmbLkSubEname = new Combo(container, SWT.NONE);
		cmbLkSubEname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Field[]	fields = clazz.getDeclaredFields();
		if (clazz != null){
			for (Field field:fields){
				if (ObjectMethodUtil.isField(clazz, field.getName())){
					//������ֶξͽ�������SubEname��Combo��
					cmbLkSubEname.add(field.getName());
				}
			}
		}
		
		
		return container;
	}



	@Override
	protected void okPressed() {

		adapter.setEname(cmbLkSubEname.getText());
		super.okPressed();
	}
	
	
	
}
