package com.lzairport.ais.tableviewer.provider;



import java.util.List;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import com.lzairport.ais.models.AisRGB;
import com.lzairport.ais.tablecolor.TableColorFactory;
import com.lzairport.ais.tableviewer.celldata.CellFactory;
import com.lzairport.ais.tableviewer.celldata.ICell;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.ObjectMethodUtil;

/**
 * ����������JAVA BEAN�ı���ContentProvider��
 * @author ZhangYu
 * version 0.9a 28/06/14
 * @since JDK 1.6
 * 
 */

public class TableViewerLabelProvider extends LabelProvider implements
		ITableLabelProvider,ITableColorProvider {
	
	private List<HeaderItem> fields;
	

	public TableViewerLabelProvider(List<HeaderItem> fields) {
		super();
		this.fields = fields;
	}
	
	


	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		//ȡ�ö���ĵ�һ�ֶ�ֵ��һ������ListViewer


		ICell cell = CellFactory.createCell(element,fields.get(0)) ;
		Object cellData = null; 
				
		if (cell != null){
			cellData =  cell.getData(element, fields.get(0));
		}
		
		if (cellData != null){
			return cellData.toString();
		}else {
			return null;
		}
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		//��ʾͼ�ζ���ֵ
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		//ȡ�ñ��ÿһ������Ӧ�Ķ����ֶε�ֵ
		ICell cell = CellFactory.createCell(element,fields.get(columnIndex)) ;
		Object cellData = null;
				
		if (cell != null){
			cellData =  cell.getData(element, fields.get(columnIndex));
		}
		
		if (cellData != null){
			return cellData.toString();
		}else {
			return null;
		}			

	}

	@Override
	public Color getBackground(Object element, int columnIndex) {
		//��ȡ�����Ӧ�ı�����ɫ
		RGB rgb = null;
		rgb = TableColorFactory.getTableBKColor(element);
		if (rgb != null){
			return new Color (null,rgb);
			
		}
		return null; 
	}
		

	@Override
	public Color getForeground(Object element, int columnIndex) {
		//��ȡ�����Ӧ��������ɫ
		RGB rgb = null;
		Object Obj = ObjectMethodUtil.getFieldObject(element, fields.get(columnIndex).getEname());
		if ((Obj != null ) && (AisRGB.class.isAssignableFrom(Obj.getClass()))){
			//�����������һ��AisRGB������ö���ȡ�����CellRGB()����Ϊ������ɫ
			rgb = ((AisRGB)Obj).getCellRGB();
		}else {
			rgb = TableColorFactory.getTableFRColor(element);
			
		}
		if (rgb != null){
			return new Color (null,rgb);
		}
		return null; 
	}

}
