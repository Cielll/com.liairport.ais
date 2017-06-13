package com.lzairport.ais.preferencePages.base;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import com.lzairport.ais.models.ViewConfig;

/**
 * ��ʾ���������Ĺ�������������ʾ������ķ�����ʾ
 * @author ZhangYu
 * @version 0.9a 27/06/14
 * @since JDK 1.6
 *
 */

public class ViewConfigFilter extends ViewerFilter {
	
	private String StrGrp;

	public ViewConfigFilter(String strGrp) {
		super();
		StrGrp = strGrp;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		// TODO Auto-generated method stub
		if (element instanceof ViewConfig) {
			//�������ʾ���������StrGrp��������ȷ�����ص�ֵ���滹�Ǽ�
			ViewConfig vc = (ViewConfig) element;
			return vc.getViewGroup().getName().equals(StrGrp);
		}
		return true;
	}

}
