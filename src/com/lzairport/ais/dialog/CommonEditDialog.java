package com.lzairport.ais.dialog;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.service.IViewConfigService;
import com.lzairport.ais.tableviewer.celldata.CellFactory;
import com.lzairport.ais.tableviewer.celldata.EnumCell;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * ͨ�õĲ����޸Ķ���Ĵ��ڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 03/01/15
 * @since JDK 1.6
 */


public class CommonEditDialog extends EditDialog {
	

	private IService<Integer,? extends Object> cmbService;
	
	protected List<Control> inputList  = new ArrayList<Control>();
	protected List<Label> labels =  new ArrayList<Label>();
	protected List<HeaderItem> fields;
	protected Class<?> clazz;
	protected AbstractApplicationContext ctx;
	
	

	/**
	 * �����޸���������Ҫ�ĸ��ֶ���
	 * @param service ���ݶ����Service��Ķ���������������
	 * @param object ��Ҫ��������ݶ���
	 * @param viewerName ������ȡField�б�
	 * @param ctx Spring����
	 */
	public CommonEditDialog( IService<Integer,? extends Object> service, Object object,
			String  viewerName,AbstractApplicationContext ctx) {
		super(service, object);
		this.ctx = ctx;
		clazz = service.getModelClass();
		IViewConfigService viewConfigService = (IViewConfigService) ctx.getBean("viewConfigService");
		fields = viewConfigService.getVCHeaderItems(viewerName,null);
	}
	
	@Override
	protected void objToDialog(Object object) {
		
		Object Data = null;
		
		for (int i=0;i<fields.size();i++){
			//��ȡ��Ӧ�����ݶ����Ա����������Data
			Data = CellFactory.createCell(object, fields.get(i)).getData(object, fields.get(i));
			//���ݸ��Ե����Խ��и�ֵ
			if (Data != null){
				if (inputList.get(i) instanceof Text) {
					//�����Text����Text��ʽ���и�ֵ
					((Text)inputList.get(i)).setText(Data.toString());
				}else if (inputList.get(i) instanceof Combo){
					((Combo)inputList.get(i)).setText(Data.toString());
				}
			}
		}
		
	}
	
	
	

	@Override
	protected void dialogToObj(Object object) {
		Object Data = null;
		for (int i=0;i<fields.size();i++){
			String subEName = fields.get(i).getSubEname();
			String Ename = fields.get(i).getEname();
			String fieldType = fields.get(i).getItemType();
			Data = null;
			//��ȡ���ݶ���ָ��Field�ֶε�ʵ������
			Class<?> type = ObjectMethodUtil.getFieldType(clazz, fields.get(i).getEname());
			if (inputList.get(i) instanceof Text) {
				//�����Text����ֵ��Data
				Data = ((Text)inputList.get(i)).getText();
			}else if (inputList.get(i) instanceof Combo){
				
				 if (fieldType.equals(SYS_VARS.ViewItemType_Enum) || fieldType.equals(SYS_VARS.ViewItemType_Bool)){
					 Data = ((Combo)inputList.get(i)).getText();
				 }else {
					//����Field�ֶ�����Ӧ��Service
					 cmbService = SYS_VARS.getFieldService(ctx,object.getClass(),Ename);
					//�������ֶε���Serviceȡ������Ӧ�����ݲ���ֵ��Data
					Data =  cmbService.findByFieldSingle(subEName,((Combo)inputList.get(i)).getText());
				 }
			}
			if ((Data != null)&&(!Ename.equals("id"))){
				//�Զ������Ա�������и�ֵ
				CellFactory.createCell(object, fields.get(i)).setData(object, fields.get(i), Data, type);
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
				String fieldType = fields.get(i).getItemType();
				labels.add(new Label(composite, SWT.NONE));
				labels.get(i).setText(fields.get(i).getName()+":");
				labels.get(i).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
				
				if  (fieldType.equals(SYS_VARS.ViewItemType_Bool)){
					inputList.add(new Combo(composite, SWT.READ_ONLY));
					((Combo)inputList.get(i)).setItems(new String[]{"true","false"});
					
				}else if (fieldType.equals(SYS_VARS.ViewItemType_Enum)){
					//�����ö�����ͣ���ö�����͵�������ʾ�ַ�����ӽ���Ӧ��Combo
					inputList.add(new Combo(composite, SWT.READ_ONLY));
					((Combo)inputList.get(i)).setItems(
						EnumCell.getInstance().enumCnText(object, fields.get(i)));
					
				}else if ((subEName != null) && (!subEName.trim().isEmpty())){
					//���SubEname��Ϊ�գ�˵�������ֶΣ���ؼ�����ΪCombo
					cmbService = SYS_VARS.getFieldService(ctx,object.getClass(),Ename);
					List<? extends Object> items =  cmbService.getAll();
					inputList.add(new Combo(composite, SWT.READ_ONLY));
					//��Serviceȡ�õ��������ֶ�����Ӧ�����ݶ���ļ��϶�Combo��Item���и�ֵ
					for (Object item:items){
						((Combo)inputList.get(i)).add(ObjectMethodUtil.getFieldObject(item, subEName).toString());
					}
					
				}else {
					//����ؼ�����ΪText
					Text text = new Text(composite, SWT.BORDER);
					
					inputList.add(text);
				}
				inputList.get(i).setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
					
				}
		
		return composite;
	}

}
