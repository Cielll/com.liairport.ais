package com.lzairport.ais.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.IViewConfigDao;
import com.lzairport.ais.dao.IViewRelationDao;
import com.lzairport.ais.dao.impl.AisOrder;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.ViewConfig;
import com.lzairport.ais.models.ViewGroup;
import com.lzairport.ais.models.ViewRelation;
import com.lzairport.ais.service.IViewConfigService;
import com.lzairport.ais.tableviewer.base.TableViewerService;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.tableviewer.header.IVcToHeader;
import com.lzairport.ais.tableviewer.header.VcToHeaderFactory;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * ��ʾ����ViewConfig��Service��ʵ����
 * @author ZhangYu
 * version 0.9a 24/06/14
 * @since JDK 1.6
 */

@Stateless
public class ViewConfigService  extends Service<Integer, ViewConfig> implements IViewConfigService{
	
	
	//������ViewRelation��Dao�࣬����ȡ����ʾ����
	@EJB
	private IViewRelationDao viewRelationDao;

	@EJB
	public void setViewConfigDao(IViewConfigDao viewConfigDao){
		setDao(viewConfigDao);
	}
	
	
	/**
	 * ͨ������Ķ����������Ӧ��ͼ����
	 * @param obj һ����Viewer
	 * @return ������ViewGroup
	 */
	
	private ViewGroup getViewGroup(String viewID){
		//��Ӧ��RELATIONVIEWCLASS����obj������
		ViewRelation vr = viewRelationDao.findByFieldSingle(ViewRelation.RELATIONVIEWCLASS,viewID);
		
		if (vr != null) {
			//����ViewGroup
			return  vr.getViewGroup();

		}else {
			return null;
		}
		
	}
	


	@Override
	public List<HeaderItem>  getVCHeaderItems(String viewer,TableViewerService viewerService){
		List<HeaderItem> HeaderItems = new ArrayList<HeaderItem>();
		List<ViewConfig> vcs = new ArrayList<ViewConfig>();
		if (dao != null){
			
			QueryConditions conditions = new QueryConditions();
			conditions.setExpresstion(new Object[]{ViewConfig.VIEWGROUP,"=",getViewGroup(viewer)});
			AisOrder order = new AisOrder();
			order.setName(ViewConfig.SORT);
			order.setSortMode(SYS_VARS.AscSORT);
			conditions.setOrders(new AisOrder[]{order});
			vcs =  dao.findByConditionAll(conditions);
			for (ViewConfig vc:vcs){
				IVcToHeader vcToHeader = VcToHeaderFactory.createVcToHeader(vc);
				if (vcToHeader != null){
					HeaderItems.addAll(vcToHeader.getHeaderItem(viewerService,vc));
				}
			}
		}
		return HeaderItems;
		
	}

}
