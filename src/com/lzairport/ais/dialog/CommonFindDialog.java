package com.lzairport.ais.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.service.IViewConfigService;
import com.lzairport.ais.tableviewer.base.BaseTableViewer;
import com.lzairport.ais.tableviewer.base.TableViewerService;
import com.lzairport.ais.tableviewer.celldata.CellFactory;
import com.lzairport.ais.tableviewer.celldata.EnumCell;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.Ais_String_Util;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * ͨ�õĲ���ʵ�����Ĵ��ڵ�ʵ����
 * @author ZhangYu
 * @version 0.9a 07/01/15
 * @since JDK 1.6
 */


public class CommonFindDialog extends Dialog {


	private IService<Integer,? extends Object> cmbService;
	private IService<Integer,? extends Object> service;
	private List<Control> inputList  = new ArrayList<Control>();
	private List<Combo> cmbOpers  = new ArrayList<Combo>();
	private List<Button> btns =  new ArrayList<Button>();
	private List<HeaderItem> fields;
	private Class<?> clazz;
	private AbstractApplicationContext ctx;
	private BaseTableViewer viewer;
	private Object object;

	
	public CommonFindDialog(IService<Integer,? extends Object> service,
			BaseTableViewer viewer,AbstractApplicationContext ctx) {
		super(Display.getDefault().getActiveShell());
		this.ctx = ctx;
		this.service = service;
		clazz = service.getModelClass();
		this.viewer = viewer;
		IViewConfigService viewConfigService = (IViewConfigService) ctx.getBean("viewConfigService");
		fields = viewConfigService.getVCHeaderItems(viewer.getViewerConfigName(),null);
		//����һ�����ݶ���
		String className = service.getModelClass().getName();
		try {
			object = Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fields = createFindFields(fields);
	}

	/**
	 * 
	 * @param fields
	 * @return
	 */
	private List<HeaderItem> createFindFields(List<HeaderItem> fields){
		
		List<HeaderItem> findFields = new ArrayList<HeaderItem>();
		
		for(HeaderItem field:fields){
			if ((!field.getItemType().equals(SYS_VARS.ViewItemType_Dyniamic)&&
				!field.getItemType().equals(SYS_VARS.ViewItemType_Num))&&
				 ((field.getSubEname() == null)||
					((field.getSubEname()!= null)&&(field.getSubEname().indexOf("/")==-1)))){
					//�������õĲ�ѯ����������field�ֶ�
					for (int i=0;i<field.getFindNum();i++){
						findFields.add(field);
					
				}
				
				/**
					Class<?> filedClass = ObjectMethodUtil.getFieldType(object.getClass(), field.getEname());
					if (filedClass.equals(Date.class)){
						findFields.add(field);
					}
				 * 
				 */
			}
		}
		
		return findFields;
		
	}
	
	
	/**
	 * ����ѡ�����ö��������
	 * @return  ��ֵ�õ�ʵ����
	 */
	private Object dialogToObj (){
		
		for (int i=0;i<fields.size();i++){
			String subEName = fields.get(i).getSubEname();
			String Ename = fields.get(i).getEname();
			String fieldType = fields.get(i).getItemType();
			Object Data = null;
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
			if ((Data != null)&&(!Ename.equals("id")&&btns.get(i).getSelection())){
				//�Զ������Ա�������и�ֵ
				CellFactory.createCell(object, fields.get(i)).setData(object, fields.get(i), Data, type);
			}
		}
		return object;
	}
	
	
	/**
	 * ������ʾ�����ԣ�������Ҫѡ��ĸ��ؼ�
	 * @param composite
	 * @return
	 */

	private Composite createDialogComposite(Composite composite){
		
		GridLayout gridLayout = (GridLayout) composite.getLayout();
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginWidth = 20;
		//ȷ����ʾ������ -�ֶ�����������ÿ��������Ŀؼ��� *3��Ϊ��Ҫ��һ��Label�����ݱ༭�ؼ���ѡ��ť
		gridLayout.numColumns = fields.size()/SYS_VARS.RowMaxNum*3;
		
		if (fields.size() % SYS_VARS.RowMaxNum != 0){
			//����ֶ���ȡ�಻Ϊ�� ��˵������������Ҫ�ټ�2
			gridLayout.numColumns +=3;
		}
		for (int i=0;i<fields.size();i++ ){
				String subEname = fields.get(i).getSubEname();
				String Ename = fields.get(i).getEname();
				String fieldType = fields.get(i).getItemType();
				
				btns.add(new Button(composite, SWT.CHECK));
				btns.get(i).setText(fields.get(i).getName());
				btns.get(i).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
				
				cmbOpers.add(new Combo(composite, SWT.READ_ONLY | SWT.CENTER));
				cmbOpers.get(i).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
				cmbOpers.get(i).setItems(SYS_VARS.OperationsCN.toArray(
						new String[SYS_VARS.OperationsCN.size()]));
				cmbOpers.get(i).setText("����");
				if  (fieldType.equals(SYS_VARS.ViewItemType_Bool)){
					//����ǲ����ͣ���true,false��ӽ���Ӧ��Combo
					inputList.add(new Combo(composite, SWT.READ_ONLY));
					((Combo)inputList.get(i)).setItems(new String[]{"true","false"});
					
				}else if (fieldType.equals(SYS_VARS.ViewItemType_Enum)){
					//�����ö�����ͣ���ö�����͵�������ʾ�ַ�����ӽ���Ӧ��Combo
					inputList.add(new Combo(composite, SWT.READ_ONLY));
					((Combo)inputList.get(i)).setItems(
						EnumCell.getInstance().enumCnText(object, fields.get(i)));
					
				}else if ((subEname != null) && (!subEname.trim().isEmpty())){ //���SubEname��Ϊ�գ�˵�������ֶΣ���ؼ�����ΪCombo
					
					Class<?> clazz = null;
					//���subEnames����2��˵����Ƕ���ֶΣ�ȡ���һ��ΪsubEname,�����ڶ���ΪEname
					List<String> subEnames = Ais_String_Util.SplitSubEname(subEname); 
					if (subEnames.size() >= 2){
						clazz = ObjectMethodUtil.getFieldType(object.getClass(), Ename);
						Ename = subEnames.get(subEnames.size()-2);
						subEname = subEnames.get(subEnames.size()-1);
						
					}else{
						clazz = object.getClass();
					}
					
					//��Serviceȡ�õ��������ֶ�����Ӧ�����ݶ���ļ��϶�Combo��Item���и�ֵ
					cmbService = SYS_VARS.getFieldService(ctx,clazz,Ename);
					List<? extends Object> items =  cmbService.getAll();
					inputList.add(new Combo(composite, SWT.NONE));
					for (Object item:items){
						((Combo)inputList.get(i)).add(ObjectMethodUtil.getFieldObject(item, subEname).toString());
					}
					
				} else {
					//����ؼ�����ΪText
					inputList.add(new Text(composite, SWT.BORDER));
					
				}
				inputList.get(i).setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				
				
				
					
		}
		return composite;
	}
	
	
	
	
	/**
	 * ��д�ķ����������Ի�������ĸ�Ԫ��
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		createDialogComposite(container);
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
	 *   ����ѡ�����ɲ�ѯ����
	 */
	@Override
	protected void okPressed() {
		
		Object obj = dialogToObj();
		
		List<Object> expresstion = new ArrayList<Object>();
		int chkCount =0;
		
		
		TableViewerService viewerService = viewer.getViewerService();
		QueryConditions conditions = new QueryConditions();
		
		for (int i=0;i<fields.size();i++ ){
			if (btns.get(i).getSelection()){
				chkCount++;
				if (chkCount != 1){
					//������ǵ�һ����������Ҫ����And�߼������
					expresstion.add(SYS_VARS.LinkSqlAnd);
				}
				expresstion.add(fields.get(i).getEname());
				int operIndex = SYS_VARS.OperationsCN.indexOf(cmbOpers.get(i).getText());
				expresstion.add(SYS_VARS.Operations.get(operIndex));
				if (cmbOpers.get(i).getText().trim().equals("����")){
					expresstion.add("%"+ObjectMethodUtil.getFieldObject(obj, fields.get(i).getEname())+"%");
				}else{
					expresstion.add(ObjectMethodUtil.getFieldObject(obj, fields.get(i).getEname()));
				}
			}
		}

		conditions.setExpresstion(expresstion.toArray());
		if (viewerService.getConditions() != null){
			conditions.setFetchManyToOne(viewerService.getConditions().getFetchManyToOne());
			conditions.setFetchOneToMany(viewerService.getConditions().getFetchOneToMany());
		}
		viewerService.setConditions(conditions);
		viewerService.setService(service);
		viewer.setInput(viewerService);
		super.okPressed();
	}
	
	


}
