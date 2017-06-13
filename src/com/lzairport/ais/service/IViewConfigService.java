package com.lzairport.ais.service;

import java.util.List;

import javax.ejb.Remote;

import com.lzairport.ais.models.ViewConfig;
import com.lzairport.ais.tableviewer.base.TableViewerService;
import com.lzairport.ais.tableviewer.header.HeaderItem;

/**
 * ��ʾ����ViewConfig��Service��ӿ�
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IViewConfigService extends IService<Integer, ViewConfig> {
	
	
	/**
	 * ����ָ��Viewer����Ӧ��HeaderItem
	 * @param viewer ��Ӧ�ı���
	 * @param viewerService ��Ӧ��ķ�����
	 * @return ָ��Viewer����Ӧ��HeaderItem
	 */
	
	public List<HeaderItem>  getVCHeaderItems(String viewer,TableViewerService viewerService);

}
