package com.lzairport.ais.tableviewer.provider;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import com.lzairport.ais.contentchange.*;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.jms.ChangeEntityInfo;
import com.lzairport.ais.models.DefaultEntity;
import com.lzairport.ais.models.aodb.BaseFlight;
import com.lzairport.ais.tableviewer.base.TableViewerService;
import com.lzairport.ais.utils.EventCodeUtil;


/**
 * ����ContentProvider�����࣬��ϵʵ�壬���ʵ�巢���仯�����Զ��ı�����
 * @author ZhangYu
 * version 0.9a 28/06/14
 * @since JDK 1.6
 * 
 */


public  abstract class  ViewerContentProvider implements IStructuredContentProvider,IContentChangeListener{


	/**
	 * ����ȡ������Ϊnullֵ��������ԵĴ���
	 */
	private final int MaxTryTimes = 15;
	
	/**
	 *  �ȴ����ݽ������ݿ��ʱ��
	 */
	private final int WaitTime =100;
	
	
	private TableViewerService viewerService;
	
	private static QueryConditions  conditions= new  QueryConditions();


	/**
	 * ��������
	 */
	protected abstract  void add(Object entity); 
	
	/**
	 * ɾ������
	 */
	protected abstract void remove(Object entity); 
	
	/**
	 * ���²���
	 */
	protected abstract void update(Object entity);
	
	/**
	 * ˢ�²���
	 */
	protected abstract void refresh();
	
	
	/**
	 * �������ʾ
	 */
	protected abstract void showErrMsg(String msg);

	


	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * �ȴ�һ��ʱ��ķ���
	 */
	private void waitForRefresh(){
		
		synchronized (this) {
			try {
				wait(WaitTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

	/**
	 *   ����������ݷ����仯����Ӧ�Ĳ���
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
		if (newInput instanceof TableViewerService) {
			viewerService = (TableViewerService) newInput;
		}
	}

	/**
	 * ��ȡ���ݶ�������
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		
		if (inputElement instanceof TableViewerService){
			//�������Ϊ����ȡȫ�����ݣ����������Ϊ����ȡ�������������ݼ���
			TableViewerService viewerService = (TableViewerService) inputElement;
			if (viewerService.getConditions() != null){
				return viewerService.getService().findByConditionAll(viewerService.getConditions()).toArray();
			}else{
				return viewerService.getService().getAll().toArray();
			}
			 
		} else if (inputElement instanceof List){
			//�����List��ֱ��ת��Ϊ����
			return ((List<?>) inputElement).toArray();
			
		}
		return null;
		
	}


	/**
	 *  �������ݸı�ʱ��������
	 *  ���ݴ����property���� ѡ����Ӧ�Ĵ���
	 */
	@Override
	public void contentChange(PropertyChangeEvent evt) {
		
		if (viewerService != null){
			
			String property = evt.getPropertyName();
			ChangeEntityInfo entityInfo = (ChangeEntityInfo) evt.getNewValue();
			//��������ʵ�������¼�ı�ʵ����ͬ
			if (viewerService.getService().getModelClass().equals(entityInfo.getClazz())){
				
				Object  obj = null;
				
				if (property.equals(EventCodeUtil.ModelsRemove)){
					//�����ʵ��ɾ���¼����½�һ�����ʵ�壬������ID
					try {
						
						obj = entityInfo.getClazz().newInstance();
						((DefaultEntity)obj).setId(entityInfo.getId());
						
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}else{
					//�������idһ�������ݣ�ManyToOne��OneToMany��Viewer����һ��
					conditions.setExpresstion(new Object[]{DefaultEntity.ID,"=",entityInfo.getId()});
					if (viewerService.getConditions() != null){
						conditions.setFetchManyToOne(viewerService.getConditions().getFetchManyToOne());
						conditions.setFetchOneToMany(viewerService.getConditions().getFetchOneToMany());
					}

					
				}

					
				if (property.equals(EventCodeUtil.ModelsAdd)){
						
					obj = viewerService.getService().findByConditionSingle(conditions);
					
					int tryTimes = 0;
					//���objΪnull˵������δ���£����Եȴ���ˢ������
					while ((obj == null) && (tryTimes < MaxTryTimes)){
						waitForRefresh();
						obj = viewerService.getService().findByConditionSingle(conditions);
					}
					
					if (obj == null){
						
						showErrMsg("���ݸ���ʱ���ִ������ֹ�ˢ�¸���ͼ");
						
					}else{
						add(obj);
					}
				}else if (property.equals(EventCodeUtil.ModelsRemove)){

					remove(obj);
				}else if (property.equals(EventCodeUtil.ModelsUpdate)){
					
					//����Ǻ���ʵ�壬�ȴ�һ��ʱ���ٻ�ȡ���ݣ�ԭ�����ں���ʵ������࣬�������
					if (BaseFlight.class.isAssignableFrom(entityInfo.getClazz())){
						waitForRefresh();
					}
					
					obj = viewerService.getService().findByConditionSingle(conditions);

					update(obj);
				}else if (property.equals(EventCodeUtil.ModelsRefresh)){
					refresh();
				}
			
			}

		}
		
		
		
		
	}

	
	
	
	
}
