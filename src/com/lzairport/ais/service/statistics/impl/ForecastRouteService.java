package com.lzairport.ais.service.statistics.impl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.dao.statistics.IBaseRouteDao;
import com.lzairport.ais.dao.statistics.IForecastRouteDao;
import com.lzairport.ais.models.statistics.BaseRoute;
import com.lzairport.ais.models.statistics.BaseRouteMonth;
import com.lzairport.ais.models.statistics.BaseSchedule;
import com.lzairport.ais.models.statistics.ForecastRoute;
import com.lzairport.ais.models.statistics.ForecastRouteMonth;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IBaseScheduleService;
import com.lzairport.ais.service.statistics.IForecastRouteMonthService;
import com.lzairport.ais.service.statistics.IForecastRouteService;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.RouteType;

/**
 * 
 * FileName      ForecastRouteService.java
 * @Description  TODO Ԥ�⺽�ߵ�Service�ӿڵ�ʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��7��1�� 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016��7��1��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class ForecastRouteService extends Service<Integer, ForecastRoute> implements IForecastRouteService {
	
	/**
	 *  ����ʵ������map��key�ļ���
	 */
	private static String[] ActualKeys = {"COUNTFLT","PAX","PAX.Arr","PAX.Dep","RATE","RATE.Dep","RATE.Arr"};
	
	/**
	 *  ����ʵ�ʶ�Ӧ�����ֶμ���
	 */
	private static String[] ActualFields ={"actualCountFLT","actualPax","actualiPax","actualoPax","actualRate","actualoRate","actualiRate"};
	
	/**
	 *  ʵ������ת����ʽ
	 */
	private static DecimalFormat df = new DecimalFormat("#.0");
	
	
	
	/**
	 *  Ԥ�⺽���µ�Service
	 */
	@EJB
	private IForecastRouteMonthService fmRouteService;
	
	/**
	 *  ��׼���Ⱥ���ƻ���Service
	 */
	@EJB
	private IBaseScheduleService baseScheduleService;
	
	/**
	 *  ��׼���ߵ�Service
	 */
	@EJB
	private IBaseRouteDao bRouteDao;
	
	
	@EJB
	public void setForecastRouteDao(IForecastRouteDao dao){
		setDao(dao);
	}
	
	

	public ForecastRouteService() {
		super();
		df.setRoundingMode(RoundingMode.HALF_UP);
	}



	/**
	 * 
	 * @Description: TODO �����Զ��ֶΣ����ݸ����µ����ݻ���
	 * @param route ��Ҫ����ĺ��� 
	 */
	private void calcAutoField(ForecastRoute route){
		/*
		 *  ��ʼ�����ܱ���
		 */
		int iPax            = 0;
		int oPax            = 0;
		int pax	            = 0;
		double iRate        = 0;
		double oRate        = 0;
		double rate         = 0;
		int countFLT        = 0;
		int compareCountFLT = 0;
		int comparePax      = 0;
		int CR              = 0;
		
		Set<ForecastRouteMonth> mRoutes = route.getFmRoutes();
		if (mRoutes != null){
			/*
			 *  �������·����ݣ�����ͳ��
			 */			
			for(ForecastRouteMonth mRoute:mRoutes){
				if (mRoute.getRate() != 0){
					iRate   += mRoute.getiRate();
					oRate   += mRoute.getoRate();
					CR++;
					
				}
				iPax            += mRoute.getiPax();
				oPax            += mRoute.getoPax();
				countFLT        += mRoute.getCountFLT();
				compareCountFLT += mRoute.getCompareCountFLT();
				comparePax      += mRoute.getComparePax();
			}
			pax = iPax + oPax;
			if (CR != 0){
				iRate =  Double.parseDouble(df.format(iRate / CR));
				oRate =  Double.parseDouble(df.format(oRate / CR));
				rate  = Double.parseDouble(df.format((iRate+oRate)/2));
			}
		}
		/*
		 *  ����ֶ�
		 */		
		route.setiPax(iPax);
		route.setoPax(oPax);
		route.setPax(pax);
		route.setComparePax(comparePax);
		route.setiRate(iRate);
		route.setoRate(oRate);
		route.setRate(rate);
		route.setCountFLT(countFLT);
		route.setCompareCountFLT(compareCountFLT);		
	}
	
	/**
	 * 
	 * @Description: TODO ����ʵ��������Ԥ�����ݵ�ƫ���monthָ����ֵΪ����
	 * @param route ��Ҫ����ĺ���
	 * @param eMonth �·ݣ����·�ǰ��ʵ�����ݺ�Ԥ��������Ҫ���бȽ�
	 */
	private void calcStageField(ForecastRoute route,int eMonth){
		/*Ԥ�����*/
		int fcstPax	           = 0;
		double fcstRate        = 0;
		int fcstCountFLT       = 0;
		int fcstCR             = 0;
		/*ʵ�ʻ���*/
		int actPax	           = 0;
		int actiPax	           = 0;
		int actoPax	           = 0;
		double actiRate         = 0;
		double actoRate         = 0;
		double actRate         = 0;
		int actCountFLT        = 0;
		int actCR              = 0;
		
		Set<ForecastRouteMonth> mRoutes = route.getFmRoutes();
		if (mRoutes != null){
			/*
			 *  �������·����ݣ�����·�<=���ޣ������ͳ��
			 */
			for(ForecastRouteMonth mRoute:mRoutes){
				if (mRoute.getMonth() <= eMonth){
					if (mRoute.getRate() != 0){
						fcstRate   += mRoute.getRate();
						fcstCR++;
					}
					if (mRoute.getActualRate() != 0){
						actRate  += mRoute.getActualRate();
						actiRate += mRoute.getActualiRate();
						actoRate += mRoute.getActualoRate();
						actCR++;
					}
					fcstPax      += mRoute.getPax();
					actPax       += mRoute.getActualPax();
					actiPax      += mRoute.getActualiPax();
					actoPax      += mRoute.getActualoPax();
					fcstCountFLT += mRoute.getCountFLT();
					actCountFLT  += mRoute.getActualCountFLT();
				}
			}
			/*
			 *  ���п����ʼ���
			 */
			if (fcstCR > 0){
				fcstRate = fcstRate / fcstCR;
			}
			if(actCR > 0){
				actRate  = actRate  / actCR;
				actiRate = actiRate / actCR;
				actoRate = actoRate / actCR;
			}
			/*
			 *  ����ֶ�
			 */
			route.setActualCountFLT(actCountFLT);
			route.setActualPax(actPax);
			route.setActualiPax(actiPax);
			route.setActualoPax(actoPax);
			route.setActualRate(Double.parseDouble(df.format(actRate)));
			route.setActualiRate(Double.parseDouble(df.format(actiRate)));
			route.setActualoRate(Double.parseDouble(df.format(actoRate)));
			route.setActualDiffCountFLT(actCountFLT-fcstCountFLT);
			route.setActualDiffPax(actPax-fcstPax);
			route.setActualDiffRate(Double.parseDouble(df.format(actRate-fcstRate)));
		}
		
		
		
	}

	
	@Override
	public ForecastRoute add(Object obj) {

		if (obj instanceof ForecastRoute){
			ForecastRoute route = (ForecastRoute) obj;
			calcAutoField(route);
			return super.add(route);
		}else{
			return null;
		}
		
	}


	@Override
	public ForecastRoute update(Object obj) {

		if (obj instanceof ForecastRoute){
			ForecastRoute route = (ForecastRoute) obj;
			calcAutoField(route);
			return super.update(route);
		}else{
			return null;
		}
		
	}


	@Override
	public List<ForecastRoute> getAllCarriages() {
		QueryConditions condistion = new QueryConditions();
		condistion.setExpresstion(new Object[]{ForecastRoute.ROUTEHX,"<>",""});
		condistion.setFetchOneToMany("ALL");
		return dao.findByConditionAll(condistion);
	}
	
	
	private ForecastRoute createRoute(String routeHX){
		ForecastRoute route = new ForecastRoute();
		route.setRouteHX(routeHX);
		Set<ForecastRouteMonth> fmRoutes = new HashSet<ForecastRouteMonth>();
		for(int i=1;i<=12;i++){
			ForecastRouteMonth mRoute = new ForecastRouteMonth();
			mRoute.setMonth(i);
			fmRoutes.add(mRoute);
		}
		route.setFmRoutes(fmRoutes);
		return route;
	}

	/**
	 * 
	 * @Description: TODO ��ȡĳ���ں��߼ƻ����еļܴ�
	 * @param year   ��
	 * @param month  ��
	 * @param routeHX ������
	 * @return �ƻ����еļܴ�
	 */
	private int getmFLTCount(String year,String month,String routeHX){
		Date startDate = DateTimeUtil.strToDate(year+"-"+month+"-01");
		Date endDate = DateTimeUtil.getMonthLastDate(startDate);
		QueryConditions conditions = new QueryConditions(); 
		conditions.setExpresstion(new Object[]{BaseSchedule.ROUTEHX,"=",routeHX});
		return baseScheduleService.CountFlightByCondition(conditions, startDate, endDate);
		
	}
	
	/**
	 * 
	 * @Description: TODO ��ȡĳ���ں��ߵļƻ���λ��
	 * @param year     ��
	 * @param month    ��
	 * @param routeHX  ������
	 * @return ��λ��
	 */
	private int getmFLTSeat(String year,String month,String routeHX){
		Date startDate = DateTimeUtil.strToDate(year+"-"+month+"-01");
		Date endDate = DateTimeUtil.getMonthLastDate(startDate);
		QueryConditions conditions = new QueryConditions(); 
		conditions.setExpresstion(new Object[]{BaseSchedule.ROUTEHX,"=",routeHX});
		return baseScheduleService.getSeatByCondition(conditions, startDate, endDate);
		
	}

	@Override
	public ForecastRoute createRoute(String year,String routeHX) {
		ForecastRoute fRoute = findByFieldSingle(ForecastRoute.ROUTEHX, routeHX);
		/* ���Ҷ�Ӧ���ߵĻ�׼����*/
		BaseRoute bRoute = bRouteDao.findByFieldSingle(BaseRoute.ROUTEHX, routeHX);
		/*
		 *  ��ѯ��Ӧ���ߵĻ�׼ȱʡ����
		 */
		RouteType routeType = baseScheduleService.getRouteType(baseScheduleService.findByFieldSingle(BaseSchedule.ROUTEHX,routeHX));
		if (routeType != null){
			String dRouteHX = SYS_VARS.RouteTypeCn.get(routeType.ordinal())+"(ȱʡ)";
			/*ȱʡ�Ļ�׼����*/
			BaseRoute dbRoute = bRouteDao.findByFieldSingle(BaseRoute.ROUTEHX, dRouteHX); 
			if (bRoute == null){
				/*�����׼����Ϊ�գ�����ȱʡ�Ļ�׼���ߴ���*/
				bRoute = dbRoute;
			}
			/*��ȡ��׼���߸��µ�����*/
			if (fRoute != null){
				/*ɾ���Ѿ����ڵ�Ԥ�⺽��*/
				remove(fRoute);;
			}
			fRoute = createRoute(routeHX);
			for(ForecastRouteMonth fmRoute:fRoute.getFmRoutes()){
				String month = String.valueOf(fmRoute.getMonth());
				/**
				 *  ׼�����м���Ļ�׼���ߵ�������
				 */
				BaseRouteMonth mRoute =null;
				/*ȷ����Ӧ��׼�����µ�����*/
				for(BaseRouteMonth bmRoute:bRoute.getmRoutes()){
					if (bmRoute.getMonth() == fmRoute.getMonth()){
						if (bmRoute.getRate() > 0){
							/*�����Ӧ�Ļ�׼���ߵ������ݲ�Ϊ0��
							 * ��ʹ�øû����������ݣ�����ʹ��ȱʡ��׼���ߵ�������
							 */
							mRoute = bmRoute;
							break;
						}else{
							for(BaseRouteMonth dbmRoute:dbRoute.getmRoutes()){
								if (dbmRoute.getMonth() == fmRoute.getMonth()){
									mRoute = dbmRoute;
									break;
								}
							}
							break;
						}
					}
				}
				
				int mFLTCount = getmFLTCount(year, month,routeHX);
				int mFLTSeat  = getmFLTSeat(year, month,routeHX);
				Double miPax = mRoute.getiRate()*mFLTCount*mFLTSeat/100;
				Double moPax = mRoute.getoRate()*mFLTCount*mFLTSeat/100;
				fmRoute.setiRate(mRoute.getiRate());
				fmRoute.setoRate(mRoute.getoRate());
				fmRoute.setiPax(miPax.intValue());
				fmRoute.setoPax(moPax.intValue());
				fmRoute.setSeat(mFLTSeat);
				fmRoute.setComparePax(mRoute.getPax());
				fmRoute.setCountFLT(mFLTCount);
				fmRoute.setCompareCountFLT(mRoute.getCountFLT()/2);
				fmRoute.autoCalc();
			}
			//��ΪExtjs�ϼƻ�����bug������һ���ָ���  
			ForecastRouteMonth fmRoute = new ForecastRouteMonth();
			fmRoute.setMonth(13);
			fRoute.getFmRoutes().add(fmRoute);
			/*����Ԥ�⺽��*/
			update(fRoute);
		}
		
		return fRoute;
	}

	/**
	 * 
	 * @Description: TODO ���÷��䷽�����ֶν��и���
	 * @param obj  ��Ҫ���ƵĶ���
	 * @param map  �������ݵ�map
	 * @param key  ��Ҫ���и������ݵ�key
	 * @param field key����Ӧ���ֶ�
	 */
	private void setActualField(Object obj,Map<String, Object> map,String key,String field){
		if (map.get(key) != null){
			Class<?>  parmClass = ObjectMethodUtil.getFieldType(obj.getClass(),field);
			ObjectMethodUtil.setFieldObject(obj, field, map.get(key), parmClass);
		}
	}

	@Override
	public void actualToForecast(ForecastRoute route,Map<String, Object> map,int eMonth) {


		/*����·�<=����,��ֵ���߸���ʵ������*/
		for(ForecastRouteMonth fmRoute:route.getFmRoutes()){
			if (fmRoute.getMonth() <= eMonth){
				for(int i=0;i<ActualKeys.length;i++){
				/*
				 *   ���ɶ�Ӧ�µ�key Pax.01
				 */
				String m;
				String key = null;
				int Separator = ActualKeys[i].indexOf(".");
				if (fmRoute.getMonth() >=10){
					m = String.valueOf(fmRoute.getMonth());
				}else{
					m = "0"+String.valueOf(fmRoute.getMonth());
				}
				if (Separator != -1 ){
					key = ActualKeys[i].substring(0, Separator)+"."+m+ActualKeys[i].substring(Separator);
				}else{
					key = ActualKeys[i]+"."+m; 
				}
				/*
				 *  д��ʵ���ֶ�
				 */
				setActualField(fmRoute, map, key, ActualFields[i]);
			}
			fmRoute.setActualCountFLT(fmRoute.getActualCountFLT()/2);	
		   }
		}
		calcStageField(route, eMonth);
		update(route);
	}


	@Override
	public void actualReplaceForecast(ForecastRoute route, int eMonth) {
		for(ForecastRouteMonth fmRoute:route.getFmRoutes()){
			if (fmRoute.getMonth() <= eMonth){
				fmRoute.setiPax(fmRoute.getActualiPax());
				fmRoute.setoPax(fmRoute.getActualoPax());
				fmRoute.setPax(fmRoute.getActualPax());
				fmRoute.setiRate(fmRoute.getActualiRate());
				fmRoute.setoRate(fmRoute.getActualoRate());
				fmRoute.setRate(fmRoute.getActualRate());
				fmRoute.setCountFLT(fmRoute.getActualCountFLT());
			}
		}
		calcStageField(route, eMonth);
		update(route);
	}

	@Override
	public int getQuarterSumValue(ForecastRoute route, int quarter, String field) {
		Set<ForecastRouteMonth> fmRoutes = route.getFmRoutes();
		int sum = 0;
		for(int m=1+3*(quarter-1);m<=3*quarter;m++){
			for(ForecastRouteMonth fmRoute:fmRoutes){
				if (fmRoute.getMonth() == m){
					/*
					 *  ����ֶεĵڶ�����ĸΪСд������Ҫ���øı��һ����ĸ��д��������ò��ı��ֱ�ӷ���
					 */
					if (Character.isLowerCase(field.charAt(1))){
						sum += (Integer) ObjectMethodUtil.getFieldObject(fmRoute, field);
					}else{
						sum += (Integer) ObjectMethodUtil.getDirectFieldObject(fmRoute, field);
					}
				}
			}
		}
		return sum;
	}

	@Override
	public double getQuarterAvgValue(ForecastRoute route, int quarter, String field) {
		Set<ForecastRouteMonth> fmRoutes = route.getFmRoutes();
		Double avg = 0.0;
		for(int m=1+3*(quarter-1);m<=3*quarter;m++){
			for(ForecastRouteMonth fmRoute:fmRoutes){
				if (fmRoute.getMonth() == m){
					/*
					 *  ����ֶεĵڶ�����ĸΪСд������Ҫ���øı��һ����ĸ��д��������ò��ı��ֱ�ӷ���
					 */
					if (Character.isLowerCase(field.charAt(1))){
						avg += (Double) ObjectMethodUtil.getFieldObject(fmRoute, field);
					}else{
						avg += (Double) ObjectMethodUtil.getDirectFieldObject(fmRoute, field);
					}
					
				}
			}
		}
		return  Double.parseDouble(df.format(avg/3));
	}
	
	
	
	
	
}
