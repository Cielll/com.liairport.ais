package com.lzairport.ais.tableviewer.header;

import java.util.List;

import com.lzairport.ais.models.ViewConfig;
import com.lzairport.ais.tableviewer.base.TableViewerService;

/**
 * ȡ�ö�̬ViewConfig��HeaderItems�Ľӿ�
 * @author ZhangYu
 * version 0.9a 06/11/14
 * @since JDK 1.6
 */

public interface IVcToHeader {

	public List<HeaderItem> getHeaderItem(TableViewerService viewerService,ViewConfig vc);



}
