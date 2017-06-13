package com.lzairport.ais.preferencePages.base;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

/**
 * ��ʾ����������ѡ�������������ڴ���ʹ�ù�����
 * @author ZhangYu
 * @version 0.9a 27/06/14
 * @since JDK 1.6
 *
 */

public class ViewConfigGrpSelectAdapter extends SelectionAdapter {
	
	private TableViewer viewer;

	public ViewConfigGrpSelectAdapter(TableViewer viewer) {
		super();
		this.viewer = viewer;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof Combo){
			String grpStr = ((Combo)e.getSource()).getText();
			if (grpStr.equals("ȫ��")){
				//���ѡ�����ȫ����ȡ��������
				viewer.resetFilters();
			}else{
				//���򣬸���Combo�е�ѡ�������й���
				viewer.resetFilters();
				viewer.addFilter(new ViewConfigFilter(grpStr));
			}
		}
		super.widgetSelected(e);
	}
	
	

}
