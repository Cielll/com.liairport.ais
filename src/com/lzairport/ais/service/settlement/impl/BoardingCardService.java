package com.lzairport.ais.service.settlement.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.settlement.IBoardingCardDao;
import com.lzairport.ais.models.settlement.BoardingCard;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.settlement.IBoardingCardService;


/**
 * 
 * FileName      BoardingCardService.java
 * @Description  TODO �ǻ����շѵ�Serviceʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��11��7�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��11��7��      Administrator    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class BoardingCardService extends Service<Integer, BoardingCard> implements IBoardingCardService {
	
	@EJB
	public void setBoardingCardDao(IBoardingCardDao dao){
		setDao(dao);
	}

}
