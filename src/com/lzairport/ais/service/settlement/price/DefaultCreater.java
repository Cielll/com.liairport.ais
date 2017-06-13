package com.lzairport.ais.service.settlement.price;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.Discount;
import com.lzairport.ais.models.settlement.Settlement;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.service.aodb.IAreaAttributeService;
import com.lzairport.ais.service.aodb.IHisFlightService;
import com.lzairport.ais.service.settlement.IDiscountService;
import com.lzairport.ais.service.settlement.ISettlementItemService;
import com.lzairport.ais.service.settlement.ISettlementService;
import com.lzairport.ais.utils.SYS_VARS.DiscountStyle;
import com.lzairport.ais.utils.SYS_VARS.OutIn;

/**
 * 
 * FileName DefaultCreater.java
 * 
 * @Description TODO �շ���Ŀ�����ߵĳ�����
 * @author ZhangYu Company: LZAirport
 * @version V0.9a CreateDate: 2016��11��11��
 * @ModificationHistory Date Author Version Discription
 *<p>
 *---------------------------------------------
 *<p>
 *2016��11��11�� Administrator 1.0 1.0
 *<p>
 *Why & What is modified: <�޸�ԭ������>
 */

public abstract class DefaultCreater implements ISettlementCreater {

	@EJB
	private ISettlementService settlementService;

	@EJB
	protected ISettlementItemService itemService;

	@EJB
	protected IAreaAttributeService areaService;

	@EJB
	protected IHisFlightService flightService;

	@EJB
	private IDiscountService discountService;
	
	private DecimalFormat df2 = new DecimalFormat("#.00");
	
	private DecimalFormat df3 = new DecimalFormat("#.000");

	protected Date startTime;

	protected Date endTime;

	
	
	

	/**
	 * 
	 * @Description: TODO ��ȡ�շ���Ŀ�ĵ���
	 * @param flight
	 *            ��Ҫ�շѵĺ���
	 * @return �շ���Ŀ�ĵ���
	 */
	protected abstract Double getPrice(HisFlight flight);

	/**
	 * 
	 * @Description: TODO ��ȡ�շ���Ŀ���ۿ�
	 * @param flight
	 *            ��Ҫ�շѵĺ���
	 * @param type
	 *            �շ�����
	 * @return �ۿ�
	 */
	protected Discount getDiscount(HisFlight flight, SettlementItem item) {
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[] { Discount.SETTLEMENTITEM, "=", item });
		conditions.setFetchManyToOne("ALL");
		List<Discount> discounts = discountService.findByConditionAll(conditions);
		Discount result = null;
		if (discounts != null) {
			for (Discount discount : discounts) {
				Boolean found = true;

				if (discount.getAirlines() != null && !discount.getAirlines().equals(flight.getAirlines())) {
					found = false;
				}

				if (discount.getCarrier() != null && !discount.getCarrier().equals(flight.getAircraft().getCarrier())) {
					found = false;
				}

				if (discount.getRouteHX() != null && discount.getRouteHX().length() > 0
						&& !discount.getRouteHX().equals(flight.getRouteHX())) {
					found = false;
				}

				if (discount.getFlightNO() != null && discount.getFlightNO().length() > 0
						&& !discount.getFlightNO().equals(flight.getFlightNO())) {
					found = false;
				}

				if (discount.getTask() != null && !discount.getTask().equals(flight.getTask())) {
					found = false;
				}

				if (found) {
					result = discount;
					break;
				}
			}

		}
		return result;
	}

	/**
	 * 
	 * @Description: TODO ����һ���µ��������ʵ��
	 * @param flight
	 * @param item
	 * @param number
	 * @param price
	 * @return
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	protected void createSettlement(HisFlight flight, SettlementType type, SettlementItem item, Double number,
			Double price) throws Exception {

		
		Settlement settlement = null;
		startTime = null;
		endTime   = null;
		Double auomnt = 0.0;
		Discount discount = getDiscount(flight, item);

		if (discount != null) {
			if (discount.getStyle().equals(DiscountStyle.Range)) {
				price = price * discount.getPercentage() / 100;
			} else {
				price = discount.getPrice().doubleValue();
			}
		}

		number = Double.parseDouble(df3.format(number));
		price = Double.parseDouble(df2.format(price));
		/*
		 *  +0.0001��Ϊ�����������ʱ��ȷ��323.575����ȷ����λ
		 */
		auomnt = Double.parseDouble(df2.format(price * number+0.0001));

		/*
		 *   �����С����ڶ�λ��Ϊ2��4��6��8��0���Ҽ�����λΪ0.5�� ���ۺ���+0.01
		 *   Ч�����£����� ����ͨ�����ط�253.95 ������λΪ0.5 ����Ϊ266.48 ����Ϊ266.47
		 */
		String priceStr = price.toString();
		int pos = priceStr.indexOf(".");
		if (pos != -1 && priceStr.substring(pos + 1).length() == 2 && type.getUnit() == 0.5 && price * 100 % 2 != 0) {
			if (OutIn.Dep.equals(flight.getIsOutIn())){
				auomnt = Double.parseDouble(df2.format(auomnt-0.01));
			}
		}

		if (auomnt > 0 && flight != null) {
			settlement = new Settlement();
			/*
			 * д�뺽������
			 */
			settlement.setFlightField(flight);
			settlement.setEstimate(!flight.getApprove());
			settlement.setStartTime(startTime);
			settlement.setEndTime(endTime);
			settlement.setCreateTime(new Date());
			
			/*
			 * д��۸�����
			 */
			if (discount != null && discount.getStyle().equals(DiscountStyle.Range)) {
				settlement.setDiscount(discount.getPercentage());
			} else {
				settlement.setDiscount(100);
			}
			settlement.setPrice(price);
			settlement.setAuomnt(auomnt);
			settlement.setNumber(number);
			settlement.setSettlementItem(item);
			settlement.setSettlementCategory(type.getCategory());
		}

		if (settlement != null) {
			settlementService.add(settlement);
		}

	}

}
