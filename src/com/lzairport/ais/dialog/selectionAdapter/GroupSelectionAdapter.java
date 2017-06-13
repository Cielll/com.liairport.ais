package com.lzairport.ais.dialog.selectionAdapter;

import java.lang.reflect.Field;
import java.util.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.models.ViewConfig;
import com.lzairport.ais.models.ViewGroup;
import com.lzairport.ais.service.IViewConfigService;
import com.lzairport.ais.service.IViewGroupService;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * �����ѡ����������
 * ����ViewConfig����Comboѡ���Ĵ���
 * @author ZhangYu
 * @version 0.9a 25/06/24
 * @since JDK 1.6
 */


public class GroupSelectionAdapter extends SelectionAdapter {
	
	/**
	 *  �ֶε�Combo
	 */
	private Combo cmbEname;
	
	/**
	 * �����Combo
	 */
	private Combo cmbViewGroup;
	
	/**
	 *  ��ʾ���õ�Service
	 */
	
	private IViewConfigService viewConfigService;
	
	/**
	 *  ��ʾ�����Service
	 */
	
	private IViewGroupService viewGroupService;
	
	/**
	 *  ����ķ���Combo��ѡ�������
	 */
	private String ViewGroupText;
	
	/**
	 *  ������ֶ�Combo��ѡ�������
	 */
	private String EnameTxt;
	
	
	
	private EnameSelectionAdapter adapter ;

	public GroupSelectionAdapter(Combo cmbEname, AbstractApplicationContext ctx,EnameSelectionAdapter adapter) {
		super();
		this.cmbEname = cmbEname;
		this.adapter = adapter;
		this.viewConfigService = (IViewConfigService) ctx.getBean("viewConfigService");
		this.viewGroupService = (IViewGroupService) ctx.getBean("viewGroupService");
		
	}

	/**
	 * @return the viewGroupText
	 */
	public String getViewGroupText() {
		return ViewGroupText;
	}

	/**
	 * @param viewGroupText the viewGroupText to set
	 */
	public void setViewGroupText(String viewGroupText) {
		ViewGroupText = viewGroupText;
	}

	/**
	 * @return the enameTxt
	 */
	public String getEnameTxt() {
		return EnameTxt;
	}

	/**
	 * @param enameTxt the enameTxt to set
	 */
	public void setEnameTxt(String enameTxt) {
		EnameTxt = enameTxt;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() instanceof Combo) {
			cmbViewGroup = (Combo) e.getSource();
			if (cmbViewGroup.getText()!=null){
				//ȡ�õ�ǰѡ��ķ���
				ViewGroup group = viewGroupService.findByFieldSingle(ViewGroup.NAME, cmbViewGroup.getText());
				//ȡ�õ�ǰѡ��ķ�������Ӧ��ʵ������
				String ModelClassName = group.getModelClassName();
				try {
					cmbEname.removeAll();
					//ȡ��ʵ����������Ӧ�����г�Ա���� 
					Object obj =  Class.forName(ModelClassName).newInstance();
					Field[]	fields =ObjectMethodUtil.getModelField(obj.getClass());
					//ȡ�ô˷�������Ӧ��������ʾ������
				    List<ViewConfig> vcs = viewConfigService.findByFieldAll(ViewConfig.VIEWGROUP, group);
				    for (Field field:fields){
				    	boolean contains = false;
				    	for (ViewConfig vc:vcs){
				    		if (field.getName().equals(vc.getEname())){
				    			contains = true;
				    		}
				    	}
				    	
				    	if ((!contains)&&ObjectMethodUtil.isField(obj.getClass(), field.getName())) {
				    		//������ֶβ����ڶ�Ӧ����ʾ��������û�У�������ֶε�Combo��
				    		cmbEname.add(field.getName());
				    	}else if (cmbViewGroup.getText().equals(ViewGroupText)&&(field.getName().equals(EnameTxt))){
				    		//�����������趨����Ҳ�����ֶε�Combo��
				    		cmbEname.add(field.getName());
				    	}else{
				    		//����ǹ�����Ҳ�����ֶ�Combo��
				    		if (ObjectMethodUtil.isField(obj.getClass(), field.getName())){
				    			Class<?> clazz = ObjectMethodUtil.getFieldType(obj.getClass(), field.getName());
								if (clazz.getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
						    		cmbEname.add(field.getName());
								}
				    		}
				    	}
				    }
				//���²�����ʵ����󴫸�EnameSelectionAdapter
				adapter.setObj(obj);
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IllegalAccessException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}

		}		
		super.widgetSelected(e);
	}



	
	

}
