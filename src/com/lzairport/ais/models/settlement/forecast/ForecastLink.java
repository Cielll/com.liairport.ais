package com.lzairport.ais.models.settlement.forecast;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.lzairport.ais.models.IntIdEntity;
import com.lzairport.ais.models.settlement.SettlementItem;


/**
 * FileName      ForecastLink.java
 * @Description  TODO  ��Ԥ����񻷽� 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017��3��24��
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017��3��24��      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Entity
public class ForecastLink extends IntIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public static String ITEM       = "item";
	public static String PROPORTION = "proportion";
	
	/**
	 * ��Ŀ����
	 */
	@ManyToOne
	private SettlementItem item;
	/**
	 * ����
	 */
	private int proportion; 
	

	public SettlementItem getItem() {
		return item;
	}

	public void setItem(SettlementItem item) {
		this.item = item;
	}

	public int getProportion() {
		return proportion;
	}

	public void setProportion(int proportion) {
		this.proportion = proportion;
	}
	
	

}
