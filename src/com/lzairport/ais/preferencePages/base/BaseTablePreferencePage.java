package com.lzairport.ais.preferencePages.base;

import java.util.List;

import javax.annotation.Resource;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.springframework.context.support.AbstractApplicationContext;

import com.lzairport.ais.tableviewer.base.BaseTableViewer;
import com.lzairport.ais.tableviewer.base.TableViewerService;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.tableviewer.provider.TableViewerContentProvider;
import com.lzairport.ais.contentchange.IContentChangeProvider;
import com.lzairport.ais.dialog.selectionAdapter.DelSelectionAdapter;
import com.lzairport.ais.dialog.selectionAdapter.EditSelectionAdapter;
import com.lzairport.ais.dialog.selectionAdapter.FindSelectionAdapter;

/**
 * �����޸ĸ������ݱ����ѡ��ĳ�����<p>
 * ʵ����ʵ��5�����󷽷���ʹ�ô˳�����
 * @author ZhangYu
 * @version 0.9a 26/06/14
 * @since JDK 1.6
 *
 */

public abstract class BaseTablePreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage,ISelectionChangedListener {
	
	

	private  EditSelectionAdapter  addSelectionAdapter;
	

	private  EditSelectionAdapter  changeSelectionAdapter;
	

	private  DelSelectionAdapter   delSelectionAdapter;
	

	private FindSelectionAdapter  findSelectionAdapter; 
	
	
	private  TableViewerContentProvider viewerContentProvider;
	
	/**
	 * ���ڸ�����ͼ�����ݸı��ṩ�ߣ�JMS��Ϣ
	 */
	@Resource(name="JMSMessage")
	protected IContentChangeProvider provider;
	
	
    
	/**
	 * ������TableViewer
	 */
	protected BaseTableViewer viewer;
    
	protected Composite containerBtn;
    
	
	

	public BaseTablePreferencePage() {
	}

	
	@Override
	public void init(IWorkbench arg0) {
	}

	
	/**
	 * ����TableViewer��ͷ��Ҫ��ʾ��HeaderItems��������ĳ��󷽷�
	 * @return �����HeaderItems
	 */
	public abstract List<HeaderItem> getHeaderItems();
	
	

	/**
	 * ������TableViewer��ͷ��ʾ���Ӧ��LabelFields�ĳ��󷽷�
	 * @return �����LabelFields
	 */
	public abstract LabelProvider setViewLabelProvider();
	
	/**
	 * ȡ��TableViewer������Ҫ�����ݵĳ��󷽷�;
	 * @return TableViewerService���͵Ķ���
	 */

	public abstract TableViewerService getViewerService();
	
	/**
	 * ��ȡSpring�����ĳ��󷽷�
	 * @return Spring������
	 */
	public abstract   AbstractApplicationContext getCtx();
	
	
	/**
	 * ������ѡ��ı���ĳ��󷽷�
	 * @return ����
	 */
	public abstract String getPageTitle();
	
	

	
	/**
	 *  ������ѡ��ҳ��ĸ�Ԫ��
	 */
	@Override
	protected Control createContents(Composite parent) {
		//��ʼ��ҳ��
	    Composite containerBase = new Composite(parent, SWT.NONE);
	    containerBase.setLayout(new GridLayout(2, false));
	    Composite containerTabel = new Composite(containerBase, SWT.NONE);
	    containerTabel.setLayout(new GridLayout(1, false));
	    GridData gd_containerTabel = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
	    gd_containerTabel.heightHint = 700;
	    gd_containerTabel.widthHint = 900;
	    containerTabel.setLayoutData(gd_containerTabel);
	    
	    Label LabBase = new Label(containerTabel, SWT.NONE);
	    LabBase.setText(getPageTitle());
	    
	    viewer = new BaseTableViewer(containerTabel, SWT.BORDER | SWT.FULL_SELECTION);
	    viewerContentProvider = new TableViewerContentProvider(viewer);
	    provider.addContentChangeListener(viewerContentProvider);
		viewer.init(getHeaderItems(), viewerContentProvider, setViewLabelProvider(), getViewerService());
	    viewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	    viewer.setViewerConfigName(this.getClass().getSimpleName());
	    viewer.setViewerService(getViewerService());
	    
	    containerBtn = new Composite(containerBase, SWT.NONE);
	    GridData gd_containerBtn = new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 1);
	    gd_containerBtn.widthHint = 120;
	    containerBtn.setLayoutData(gd_containerBtn);
	    
	    
	    //���������ܰ�ť������ʽ
	    Button btnAdd = new Button(containerBtn, SWT.NONE);
	    btnAdd.setBounds(10, 27, 98, 29);
	    btnAdd.setText("����...");
	    
	    Button btnDel = new Button(containerBtn, SWT.NONE);
	    btnDel.setBounds(10, 114, 98, 29);
	    btnDel.setText("ɾ��");
	    
	    Button btnFind = new Button(containerBtn, SWT.NONE);
	    btnFind.setBounds(10, 158, 98, 29);
	    btnFind.setText("����");

	    
	    Button btnChange = new Button(containerBtn, SWT.NONE);
	    btnChange.setBounds(10, 70, 98, 29);
	    btnChange.setText("�޸�");
	    
	    addSelectionAdapter = new EditSelectionAdapter(getViewerService().getService(),getCtx(),this);
	    changeSelectionAdapter = new EditSelectionAdapter(getViewerService().getService(),getCtx(),this);
	    delSelectionAdapter = new DelSelectionAdapter(getViewerService().getService());
	    findSelectionAdapter = new FindSelectionAdapter(getViewerService().getService(),
	    		this.viewer,getCtx());
	    
	    btnAdd.addSelectionListener(addSelectionAdapter);
	    btnChange.addSelectionListener(changeSelectionAdapter);
	    btnDel.addSelectionListener(delSelectionAdapter);
	    btnFind.addSelectionListener(findSelectionAdapter);
	    viewer.addPostSelectionChangedListener(this);
   		return containerBase;
	}


	
	


	@Override
	public void dispose() {
		provider.removeContentChangeListener(viewerContentProvider);
		super.dispose();
	}


	/**
	 * ѡ�������� ������Ҫ��������ݶ���������ѡ���������Ĵ���Obj
	 */

	@Override
	public void selectionChanged(SelectionChangedEvent evt) {
		 IStructuredSelection selection = (IStructuredSelection) evt.getSelection();
		 changeSelectionAdapter.setObject(selection.getFirstElement());
		 delSelectionAdapter.setObject(selection.getFirstElement());
	}
}
