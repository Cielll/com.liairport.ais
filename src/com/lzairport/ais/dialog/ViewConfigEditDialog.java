package com.lzairport.ais.dialog;

import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.dialog.selectionAdapter.EnameSelectionAdapter;
import com.lzairport.ais.dialog.selectionAdapter.GroupSelectionAdapter;
import com.lzairport.ais.dialog.selectionAdapter.SubEnameSelectionAdapter;
import com.lzairport.ais.models.ViewConfig;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.service.IViewConfigService;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * ��ʾ���ö���ı༭�Ի�����
 * ���ڱ༭��ʾ���ö���
 * @author ZhangYu
 * @version 0.9a 25/06/14
 * @since JDK 1.6
 */

public class ViewConfigEditDialog extends CommonEditDialog {
	
	/**
	 * �ֶε�Combo
	 */
	private Combo cmbEname;
	
	/**
	 * ���ֶε�Combo
	 */
	private Combo cmbSubEname;
	
	/**
	 *  �����ֶε�Combo
	 */
	private Combo cmbViewGroup ;
	
	
	/**
	 *  ��ʾView���͵�Combo
	 */	
	private Combo cmbViewItemType;
	
	/**
	 * ����ѡ�������࣬����cmbViewGroupѡ���Ĵ���
	 */
	private GroupSelectionAdapter groupSelectionAdapter;
	
	/**
	 *  �ֶ�ѡ�������࣬����cmbEnameѡ���Ĵ���
	 */
	private EnameSelectionAdapter enameSelectionAdapter;
	
	/**
	 * ���ֶ�ѡ�������࣬����subCmbEnameѡ���Ĵ���
	 */
	private SubEnameSelectionAdapter subEnameSelectionAdapter;
	
	private IService<Integer,? extends Object> cmbService;

	public ViewConfigEditDialog(IViewConfigService service,
			Object object,Object viewer,AbstractApplicationContext ctx) {
		super(service, object,viewer.getClass().getSimpleName(),ctx);
		
	}

	
	
	
	
	
	
	@Override
	protected void objToDialog(Object object) {
		// TODO Auto-generated method stub
		super.objToDialog(object);
		// ���÷�������������ֶΣ�Ϊ�˱���֮��
		groupSelectionAdapter.setEnameTxt(cmbEname.getText());
		//���÷���������������飬Ϊ�˱���֮��
		groupSelectionAdapter.setViewGroupText(cmbViewGroup.getText());
	}


	@Override
	protected void dialogToObj(Object object) {
		Object Data = null;
		for (int i=0;i<fields.size();i++){
			String subEname = fields.get(i).getSubEname();
			String Ename = fields.get(i).getEname();
			Data = null;
			//ȡ���ֶε�ʵ������type
			Class<?> type = ObjectMethodUtil.getFieldType(clazz, fields.get(i).getEname());
			if (inputList.get(i) instanceof Text) {
				Data = ((Text)inputList.get(i)).getText();
			}else if (inputList.get(i) instanceof Combo){
				if (Ename.equals(ViewConfig.ENAME) || Ename.equals(ViewConfig.SUBENAME) || Ename.equals(ViewConfig.ITEMTYPE)){
					//����ؼ�ΪCombo���ֶ�ֵΪEname��SubEname��ITEMTYPE���Ǵ�Data����ֱ��ȡCombo���ı�,��Ϊ�����ֶε����;���ΪString
					Data = ((Combo)inputList.get(i)).getText();
				}else {
					//����Ҫ��Serviceȥ����SubEname�����ֶε�ֵȥ����Data
					 cmbService = SYS_VARS.getFieldService(ctx,object.getClass(),Ename);
					Data =  cmbService.findByFieldSingle(subEname,((Combo)inputList.get(i)).getText());
				}
					
			}
			if ((Data != null)&&(fields.get(i).getEname()!="id")){
				ObjectMethodUtil.setFieldObject(object, Ename,Data,type);
			}
		}
	}







	@Override
	protected Composite createDialogComposite(Composite composite) {
		GridLayout gridLayout = (GridLayout) composite.getLayout();
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginWidth = 20;
		//ȷ����ʾ������ -�ֶ�����������ÿ��������Ŀؼ��� *2��Ϊ��Ҫ��һ��Label�����ݱ༭�ؼ�
		gridLayout.numColumns = fields.size()/SYS_VARS.RowMaxNum*2;
		if (fields.size() % SYS_VARS.RowMaxNum != 0){
			//����ֶ���ȡ�಻Ϊ�� ��˵������������Ҫ�ټ�2
			gridLayout.numColumns +=2;
		}
		
		for (int i=0;i<fields.size();i++ ){
				String subEName = fields.get(i).getSubEname();
				String Ename = fields.get(i).getEname();
				labels.add(new Label(composite, SWT.NONE));
				labels.get(i).setText(fields.get(i).getName()+":");
				labels.get(i).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
				if (Ename.equals(ViewConfig.ENAME)){
					//�����Ename�ֶ� �ؼ�����ΪCombo
					inputList.add(new Combo(composite, SWT.NONE));
					cmbEname = (Combo) inputList.get(i);
				}else if (Ename.equals(ViewConfig.SUBENAME)){
					//�����SubEname�ֶ� �ؼ�����ΪCombo
					inputList.add(new Combo(composite, SWT.NONE));
					cmbSubEname =  (Combo) inputList.get(i);
				}else if (Ename.equals(ViewConfig.ITEMTYPE)){
					inputList.add(new Combo(composite, SWT.NONE));
					cmbViewItemType =  (Combo) inputList.get(i);
					
					cmbViewItemType.setItems(SYS_VARS.ViewItemType);
					
				}else if (Ename.equals(ViewConfig.VIEWGROUP)){
					//�����ViewGroup�ֶ� �ؼ�����ΪCombo���������еķ�������ӵ�Combo��Item��
						cmbService = SYS_VARS.getFieldService(ctx,object.getClass(),Ename);
						List<? extends Object> items = cmbService.getAll();
						inputList.add(new Combo(composite, SWT.NONE));
						for (Object item:items){
							((Combo)inputList.get(i)).add(ObjectMethodUtil.getFieldObject(item, subEName).toString());
						}
						cmbViewGroup =(Combo)inputList.get(i); 
				}else {
					inputList.add(new Text(composite, SWT.BORDER));
				}
				inputList.get(i).setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				}
		
		subEnameSelectionAdapter = new SubEnameSelectionAdapter();
		enameSelectionAdapter = new EnameSelectionAdapter(cmbSubEname,subEnameSelectionAdapter);
		groupSelectionAdapter = new GroupSelectionAdapter(cmbEname, ctx,enameSelectionAdapter);
		cmbViewGroup.addSelectionListener(groupSelectionAdapter);
		cmbEname.addSelectionListener(enameSelectionAdapter);
		cmbSubEname.addSelectionListener(subEnameSelectionAdapter);
		return composite;
	}

	
}
