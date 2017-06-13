package com.lzairport.ais.tableviewer.base;

import java.util.List;

import javax.annotation.Resource;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.lzairport.ais.contentchange.IContentChangeProvider;
import com.lzairport.ais.tableviewer.base.BaseTableViewer;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.tableviewer.provider.TableViewerContentProvider;
import com.lzairport.ais.tableviewer.provider.ViewerContentProvider;



/**
 * �������ݱ��ĳ����࣬�ñ��ķ�ʽ����ʾ���ݣ������˲������Ļ���������
 * ʵ������Ҫʵ�ֳ�����ĳ��󷽷�����ʵ�������ڱ������ʾ
 * @author ZhangYu
 * version 0.9a 28/06/14
 * @since JDK 1.6
 * 
 */

public abstract class BaseTableViewPart extends ViewPart  {
	
	
	protected BaseTableViewer viewer;
	
	protected TableViewerService viewerService;  
	
	/**
	 * ���ڸ�����ͼ�����ݸı��ṩ�ߣ�JMS��Ϣ
	 */
	@Resource(name="JMSMessage")
	protected IContentChangeProvider provider;
	
	
	protected ViewerContentProvider tableViewerContentProvider;


	/**
	 *����һ��TableViewer��ʵ��������󷽷�  
	 * @param parent ����
	 * @return TableViewer��ʵ���������Ϊnull
	 */
	public abstract BaseTableViewer getTableView(Composite parent);
	
	
	/**
	 * ����View�ϰ벿�ֵ�Composite�ؼ����󷽷� 
	 * @param parent ����
	 */
	public abstract void setTopComposite(Composite parent);
	
	/**
	 * ����View�°벿�ֵ�Composite�ؼ����󷽷� 
	 * @param parent ����
	 */
	public abstract void setBottomComposite(Composite parent);
	
	
	/**
	 * ����TableViewer��ͷ��Ҫ��ʾ��HeaderItems�������
	 * @return �����HeaderItems
	 */
	public abstract List<HeaderItem> getHeaderItems();
	
	/**
	 * ������TableViewer��ͷ��ʾ���Ӧ��LabelFields
	 * @return �����LabelFields
	 */
	public abstract LabelProvider setViewLabelProvider(); 
	
	
	/**
	 * ��ȡInputData����
	 * @return Service ����
	 */
	public abstract TableViewerService getViewerService();
	

	
	public abstract void initTableAttribute();
		
	
	public BaseTableViewPart() {
	}
	
	
	/**
	 * ����View�еĸ����ؼ���
	 */
	@Override
	public void createPartControl(Composite parent) {

		setTopComposite(parent);
		viewer = getTableView(parent);
		tableViewerContentProvider = new TableViewerContentProvider(viewer);
		//
		provider.addContentChangeListener(tableViewerContentProvider);
		viewer.init(getHeaderItems(), tableViewerContentProvider, setViewLabelProvider(), getViewerService());
		viewer.setViewerConfigName(this.getClass().getSimpleName());
		viewer.setViewerService(getViewerService());
		setBottomComposite(parent);
		initTableAttribute();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}



	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
	
	

}
