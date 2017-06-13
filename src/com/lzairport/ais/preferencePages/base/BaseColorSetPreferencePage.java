package com.lzairport.ais.preferencePages.base;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import com.lzairport.ais.models.AisRGB;
import com.lzairport.ais.tableviewer.base.TableViewerService;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.tableviewer.provider.ListViewerContentProvider;
import com.lzairport.ais.tableviewer.provider.TableViewerLabelProvider;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * �������ø������ݱ���ʾ��ɫ����ѡ��ҳ�ĳ�����<p>
 * ʵ����ʵ��3�����󷽷�setGroupCaption() ���ñ��� 
 * colorSetFlag() ������ɫ����
 * getService() �����������ݶ����Service
 * @author ZhangYu
 * version 0.9a 26/06/14
 * @since JDK 1.6
 * 
 */


public abstract class BaseColorSetPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage, ISelectionChangedListener {
	public BaseColorSetPreferencePage() {
	}
	/**
	 *  ������ɫ�༭��
	 */
	private ColorFieldEditor bkCFESet;
	
	/**
	 * ǰ����ɫ�༭��
	 */
	private ColorFieldEditor frCFESet;
	
	/**
	 * ��Ԫ����ɫ�༭��
	 */
	private ColorFieldEditor cellfrCFESet;
	
	private Group grpBaseSet;
	
	/**
	 *	 ������ListViewer
	 */
	private ListViewer lvBaseSet;
	
	/**
	 *  ��ɫ����
	 */
	private AisRGB aisRgb=null;
	
	
	/**
	 * ����Group����ʾ����
	 * @return ��ʾ����
	 */
	protected abstract String setGroupCaption();
	
	/**
	 * ������Ҫ��LIST����ʾ���ֶ���
	 * @return
	 */
	protected  abstract java.util.List<HeaderItem> setFields();

	/**
	 * ��ɫ���÷��� ��ϸ��SYS_VARS_Util����趨
	 * @return ��ɫ���÷��� brfrColorSet cellColorSet
	 */
	protected  abstract int colorSetFlag();
	
	/**
	 * ���ز�����Service�����ڸ�ListViewer������input
	 * @return
	 */
	protected abstract TableViewerService getViewerService(); 
	
	
	/**
	 * �����������ɫ���÷���
	 * @param colorSetFlag
	 */
	private void CFEenable(int colorSetFlag){
		switch (colorSetFlag) {
		case SYS_VARS.brfrColorSet:
			cellfrCFESet.getColorSelector().setEnabled(false);
			break;

		case  SYS_VARS.cellColorSet:
			frCFESet.getColorSelector().setEnabled(false);
			bkCFESet.getColorSelector().setEnabled(false);
			break;
		}
	}
	 

	

	/**
	 *   ����ѡ�е�������������ѡ��ҳ�� bkCFESet��frCFESet�� cellfrCFESet��ɫ
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		 IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		 //��ʼ������ɫ�༭��
		 bkCFESet.getColorSelector().setColorValue(new RGB(255,255,255));
		 frCFESet.getColorSelector().setColorValue(new RGB(0,0,0));
		 cellfrCFESet.getColorSelector().setColorValue(new RGB(0,0,0));
		 
		 //������ɫ������ɫ�༭����ֵ
		 if (selection.getFirstElement() instanceof AisRGB){
			 aisRgb = (AisRGB) selection.getFirstElement();
			 if (aisRgb.getBkRGB() != null){
				 bkCFESet.getColorSelector().setColorValue(aisRgb.getBkRGB());
			 }
			 if (aisRgb.getFrRGB() != null){
				 frCFESet.getColorSelector().setColorValue(aisRgb.getFrRGB());
			 }
			 if (aisRgb.getCellRGB() != null){
				 cellfrCFESet.getColorSelector().setColorValue(aisRgb.getCellRGB());
			 }
		 }
	}
	

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	
	/**
	 *  ������ѡ��ҳ��ĸ�Ԫ��
	 */
	@Override
	protected Control createContents(Composite parent) {
		//��ʼ��ҳ��
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		grpBaseSet = new Group(container, SWT.NONE);
		grpBaseSet.setLayout(new GridLayout(1, false));
		grpBaseSet.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpBaseSet.setText(setGroupCaption());
		lvBaseSet = new ListViewer(grpBaseSet, SWT.BORDER | SWT.V_SCROLL);
		List lsBaseSet =  lvBaseSet.getList();
		GridData gd_lsBaseSet = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_lsBaseSet.heightHint = 450;
		lsBaseSet.setLayoutData(gd_lsBaseSet);
		Composite colorCompostie = new Composite(grpBaseSet, SWT.NONE);
		colorCompostie.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_compostiecon = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_compostiecon.heightHint = 91;
		gd_compostiecon.widthHint = 314;
		colorCompostie.setLayoutData(gd_compostiecon);
		//��������ɫ�༭��
		bkCFESet = new ColorFieldEditor("","������ɫ��   ", colorCompostie);
		frCFESet = new ColorFieldEditor("","������ɫ��   ", colorCompostie);
		cellfrCFESet = new ColorFieldEditor("","��Ԫ��������ɫ��", colorCompostie);
		//����ListViewer �������������ṩ����ǩ�ṩ�����ݶ��󼰴���ʽ
    	lvBaseSet.setContentProvider(new ListViewerContentProvider(lvBaseSet));
		lvBaseSet.setLabelProvider(new TableViewerLabelProvider(setFields()));
		lvBaseSet.setInput(getViewerService());
		lvBaseSet.addSelectionChangedListener(this);
		//����ʵ����ѡ��ѡ����ɫ���÷���
		CFEenable(colorSetFlag());
		return container;
	}


	/**
	 * �洢�趨��RGB�Ķ���
	 */
	@Override
	protected void performApply() {
		// TODO Auto-generated method stub
		aisRgb.setBkRGB(bkCFESet.getColorSelector().getColorValue());
		aisRgb.setFrRGB(frCFESet.getColorSelector().getColorValue());
		aisRgb.setCellRGB(cellfrCFESet.getColorSelector().getColorValue());
		getViewerService().getService().update(aisRgb);
		super.performApply();
		
	}
}


