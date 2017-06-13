package com.lzairport.ais.service.aodb;

import java.util.Date;
import javax.ejb.Remote;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.statistics.CumulativeColumnField;
import com.lzairport.ais.service.DataFetchResponseInfo;
import com.lzairport.ais.vo.FlightVO;

/**
 * ������ʷ��Service�ӿ�
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */

@Remote
public interface IHisFlightService extends IFlightService<Integer, HisFlight> {

	/**
	 * 
	 * @Description: ����EXTJSҳ������Ҫ��Response����
	 * @param conditions ����
	 * @return DataFetchResponseInfo���� 
	 * @throws Exception
	 */
	public DataFetchResponseInfo fetchByCondition(QueryConditions conditions) throws Exception;
	
	/**
	 * 
	 * @Description: ����flightVO���󱣴�HisFlight����
	 * @param flightVO
	 * @throws Exception 
	 */
	public void update(FlightVO flightVO) throws Exception;


	/**
	 * @Description: ��ָ������֮��Ĺ���������������д�뺽��ʵ���е�������
	 * @param startDate
	 */
	public void convertLoad(Date startDate);

	
	/**
	 * @Description: ��ָ��������������д�뺽��ʵ���е�������,�����������
	 * �����ϵͳЧ��
	 * @param flight
	 */
	public void updateExtraParams(HisFlight flight);
	
	/**
	 * 
	 * @Description: �ۼƵ�ָ�����ڵ����ÿ�������
	 * @param date ָ������
	 * @return ���ÿ�������
	 * @throws Exception
	 */
	public Integer cumulativeMonthField(Date date,CumulativeColumnField field) throws Exception;
	
	/**
	 * 
	 * @Description: �ۼƵ�ָ�����ڵ����ÿ�������
	 * @param date ָ������
	 * @return ���ÿ�������
	 * @throws Exception
	 */
	public Integer cumulativeYearField(Date date,CumulativeColumnField field) throws Exception ;

	
	/**
	 * 
	 * @Description: ���غ���ĺ��ߣ��������£�
	 * 1��MU5203 SHA-LZH ����Ϊ SHA-LZH-SHA
	 * 2��MU5204 LZH-SHA ����Ϊ SHA-LZH-SHA
	 * 3��Mu5379 TAO-CGO-LZH ����Ϊ LZH-CGO-TAO
	 * 4��MU5380 LZH-CGO-TAO ����Ϊ LZH-CGO-TAO
	 * 5��EU2201 CTU-LZH-SZX ����Ϊ CTU-LZH-SZX
	 * 6��EU2202 SZX-LZH-CTU ����Ϊ SZX-LZH-CTU
	 * @return
	 */
	public void setRouteCn(HisFlight flight);
	
	/**
	 * 
	 * @Description: TODO ���Һ������һ�����Ӻ���
	 * @param flight
	 */
	public void linkFlight(HisFlight flight);
	


}
