package com.lzairport.ais.dialog.selectionAdapter;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.ui.PlatformUI;

import com.lzairport.ais.dialog.SubEnameDialog;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * ViewConfig���ֶε�ѡ��������
 * ����ViewConfig���ֶ�Comboѡ���Ĵ���
 * @author ZhangYu
 * @version 0.9a 06/08/14
 * @since JDK 1.6
 */

public class SubEnameSelectionAdapter extends SelectionAdapter {
	
	/**
	 * ���ֶε�Combo
	 */
	private Combo cmbSubEname;
	
	/**
	 * �ֶ�ѡ������������ݶ���obj,��EnameSelectionAdapter����
	 */

	private Class<?> clazz;
	
	
	/**
	 * ���ֶεĶ������ڲ������ֶε���ʾ�ֶ�
	 */

	private Class<?> subClazz;

	/**
	 * ���ֶε���ʾ����
	 */
	private String Ename;
	
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

	/**
	 * @return the ename
	 */
	public String getEname() {
		return Ename;
	}

	/**
	 * @param ename the ename to set
	 */
	public void setEname(String ename) {
		Ename = ename;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		cmbSubEname = (Combo) e.getSource();
		Ename = cmbSubEname.getText();
		subClazz = clazz;
		//���ҵ���ʵ�����һֱ��������Ӧ����ʾ�ֶ�
		while (ObjectMethodUtil.isField(subClazz, Ename)){
			Class<?> clazz = ObjectMethodUtil.getFieldType(subClazz, Ename);
			//�����ʵ������������ʾ�����ֶ�
			if (clazz.getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
				subClazz = clazz;
				if (MessageDialog.openQuestion(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell(), "ѯ��", "������Ϊ"+clazz.getSimpleName()
						+"ʵ���Ƿ����ָ����ʾ�ֶ�")) {
					
					SubEnameDialog dialog = new SubEnameDialog(this);
					dialog.setClazz(subClazz);
					if (dialog.open() == 0) {
						//���ѡ��ȷ���������ʾ�ֶ���
						cmbSubEname.setText(cmbSubEname.getText()+"/"+Ename);
					}
				}else{
					//���ѡ��ָ�������˳������ö����toString��������ʾ����
					break;
				}
			}else{
				//�������ʵ���࣬�����������ø���������ʾ����
				break;
			}
			
		}
		
		super.widgetSelected(e);
	}
	
	
}
