package com.lzairport.ais.tableviewer.base;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.tableviewer.provider.RowNumberLabelProvider;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * �������ݱ���ʵ���࣬��װ�˻����ı��ʵ�ַ���
 * ����init������������ͷ����������
 * @author ZhangYu
 * version 0.9a 28/06/14
 * @since JDK 1.6
 * 
 */

public class BaseTableViewer extends TableViewer {
	
	private String viewerConfigName;
	
	private TableViewerColumn numberColumn; 
	
	private TableViewerService viewerService;

	public BaseTableViewer(Composite parent, int style) {
		super(parent, style);
		this.getTable().setHeaderVisible(true);
		this.getTable().setLinesVisible(true);
	}
	
	
	
	
	/**
	 * @return the viewerConfigName
	 */
	public String getViewerConfigName() {
		return viewerConfigName;
	}

	/**
	 * @param viewerConfigName the viewerConfigName to set
	 */
	public void setViewerConfigName(String viewerConfigName) {
		this.viewerConfigName = viewerConfigName;
	}

	/**
	 * @return the viewerService
	 */
	public TableViewerService getViewerService() {
		return viewerService;
	}




	/**
	 * @param viewerService the viewerService to set
	 */
	public void setViewerService(TableViewerService viewerService) {
		this.viewerService = viewerService;
	}




	/**
	 * ����TableViewer�ı�ͷ,����������
	 *
	 */
	public  void createTableHeader(List<HeaderItem> headerItems){
		
		if (headerItems != null && !headerItems.isEmpty()){
			for (final HeaderItem headerItem:headerItems){
				//������ͷ��������
				
				
				if (SYS_VARS.ViewItemType_Num.equals(headerItem.getItemType())){
					numberColumn = new TableViewerColumn(this, SWT.LEFT);
					numberColumn.getColumn().setText(headerItem.getName());
					numberColumn.getColumn().setWidth(headerItem.getWidth());
				}else{
					TableColumn tcItem = new TableColumn(this.getTable(),SWT.CENTER|SWT.MULTI);
					final TableViewer viewer = this;
					tcItem.setText(headerItem.getName());
					tcItem.setWidth(headerItem.getWidth());
					tcItem.setMoveable(true);
					//��������ʽ
					tcItem.addSelectionListener(new SelectionAdapter() {
						private boolean asc = true;
						public void widgetSelected(SelectionEvent e) {
								viewer.setSorter(new CommViewSorter(headerItem,asc));
								asc = !asc;
							}
						
					});
				}
			}
		}
		
	}
	
	
	/**
	 * ���TableViewer�ı�ͷ
	 */
	public void clearTableHeader(){
		TableColumn[] tcItems = this.getTable().getColumns();
		for (TableColumn tcItem:tcItems){
			tcItem.dispose();
		}
		
	}
	/**
	 * ��ʼ��TableViewer������������ñ���ContentProvider��LabelProvider��Input
	 * @param headerItems ���ͷ����
	 * @param contentProvider �����ṩ��
	 * @param labelProvider ��ǩ�ṩ��
	 * @param inputData ��������
	 */
	public void init(List<HeaderItem> headerItems,IStructuredContentProvider contentProvider,
			LabelProvider labelProvider,Object inputData){
		this.createTableHeader(headerItems);
		this.setContentProvider(contentProvider);
		this.setLabelProvider(labelProvider);
		if (numberColumn != null){
			numberColumn.setLabelProvider(new RowNumberLabelProvider());
		}
		if (inputData != null ){
			this.setInput(inputData);
		}

	}
	
		



}
