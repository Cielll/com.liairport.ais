package com.lzairport.ais;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.springframework.context.support.AbstractApplicationContext;


/**
 * Eclipse Rcp Activator �ĳ�����
 * ���ڷ���Spring������Ctx
 * @author ZhangYu
 * @since JDK 1.6 
 * @version 0.9a 24/06/14
 *
 */


public abstract class AbstractActivator extends AbstractUIPlugin {
	
	/**
	 * ���ڷ���Spring������Ctx
	 * @return Ctx
	 */
	
	public abstract  AbstractApplicationContext getCtx();


}
