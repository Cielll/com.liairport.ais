package com.lzairport.ais.dialog.selectionAdapter;

import java.lang.reflect.Field;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * ViewConfig�ֶε�ѡ��������
 * ����ViewConfig�ֶ�Comboѡ���Ĵ���
 * @author ZhangYu
 * @version 0.9a 25/06/24
 * @since JDK 1.6
 */



public class EnameSelectionAdapter extends SelectionAdapter {
	
	/**
	 *  �ֶε�Combo
	 */
	private Combo cmbEname;
	
	/**
	 *  ���ֶε�Combo
	 */
	private Combo cmbSubEname;
	
	
	/**
	 * ���ݶ���,����GroupSelection��ѡ��
	 * 
	 */
	private Object obj;
	
	private SubEnameSelectionAdapter adapter;

	public EnameSelectionAdapter(Combo cmbSubEname,SubEnameSelectionAdapter adapter) {
		super();
		this.cmbSubEname = cmbSubEname;
		this.adapter = adapter;
	}
	
	
	

	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}




	/**
	 * @param obj the obj to set
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}




	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() instanceof Combo) {
			cmbEname = (Combo) e.getSource();
			if (cmbEname.getText() != null){
				//ȡ���ֶ�Combo����ֵ
				String Ename = cmbEname.getText();
				cmbSubEname.removeAll();
				if (ObjectMethodUtil.isField(obj.getClass(), Ename)){
					Class<?> clazz = ObjectMethodUtil.getFieldType(obj.getClass(), Ename);
					//�����ʵ���
					if (clazz.getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
						try {
							//���SubEname��Items
							//Model�����еĳ�Ա�������������Ǽ������ֶε�Combo��
							Field[]	fields =ObjectMethodUtil.getModelField(clazz);
							for (Field field:fields){
								if (ObjectMethodUtil.isField(clazz, field.getName())){
									//������ֶξͽ�������SubEname��Combo��
									cmbSubEname.add(field.getName());
								}
							}
							//���²�����ʵ����󴫸�SubEnameSelectionAdapter
							adapter.setClazz(clazz);
						} catch (SecurityException e1) {
							// TODO Auto-generated catch block
						}	
					}					
					
				}
				//ȡ���ֶε�����
				
				
			}
		}
		super.widgetSelected(e);
	}
	
	

}
