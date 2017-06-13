package com.lzairport.ais.service.statistics.impl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.dao.statistics.IBaseRouteDao;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.statistics.BaseRoute;
import com.lzairport.ais.models.statistics.BaseRouteMonth;
import com.lzairport.ais.service.aodb.IHisFlightService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IBaseRouteService;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.RouteType;



/**
 * 
 * FileName      BaseRouteService.java
 * @Description  TODO Ԥ�⺽��ÿ���µĻ�׼���ݵ�Serviceʵ����
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016��7��7�� 
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2016��7��7��      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

@Stateless
public class BaseRouteService extends Service<Integer, BaseRoute> implements IBaseRouteService {
	
	
	private static String RouteHX = "ROUTEHXGROUP";
	
	private static String Flight  = "FLIGHTGROUP";
	
	private static String Rate = "RATE";
	
	private static String SeatSum = "SEATSUM";
	
	private static String Seat ="SEAT";
	
	private static String Dep = "Dep";
	
	private static String Arr = "Arr";
	
	private static String Pax = "PAX";
	
	private static String sRouteType="ROUTETYPE";
	
	private static int LimitFLT =15;
	
	private static String CountFLT = "COUNTFLT";
	
	private static String[] headers = {Pax,CountFLT,SeatSum};
	
	private static String[] suffixs = {Dep,Arr};
	
	private static String[] mNum = {"01","02","03","04","05","06","07","08","09","10","11","12"};
	
	private static String iRate ="iRate";
	
	private static String oRate = "oRate";
	
	private static String count = "count";

	@SuppressWarnings("unchecked")
	private static Map<String,Double>[] mapValues = new Map[12];
	
	private static String defaultCN="(ȱʡ)";
	
	private DecimalFormat df = new DecimalFormat("#.0");

	@EJB
	private IHisFlightService flightService;


	
	@EJB
	public void setBaseRouteDao(IBaseRouteDao dao){
		setDao(dao);
	}
	
	
	
	public BaseRouteService() {
		super();
		df.setRoundingMode(RoundingMode.HALF_UP);
	}



	/**
	 * 
	 * @Description: TODO ���ݺ�����������һ����׼����
	 * @param routeHX ������
	 * @return ������Ϊ����ֵ�Ļ�׼����
	 */
	private BaseRoute createRoute(String routeHX){
		BaseRoute result = new BaseRoute();
		result.setRouteHX(routeHX);
		Set<BaseRouteMonth> mRoutes = new HashSet<BaseRouteMonth>();
		for(String m:mNum){
			BaseRouteMonth mRoute = new BaseRouteMonth();
			mRoute.setMonth(Integer.valueOf(m));
			mRoutes.add(mRoute);
		}
		result.setmRoutes(mRoutes);
		return result;
	}
	

	/**
	 * 
	 * @Description: TODO ��ԭʼMap��key����Ӧ��ֵ����Ŀ��Map��
	 * @param mapOrg   ԭʼ��Map
	 * @param mapDest  Ŀ���Map
	 * @param key      ��Ҫ����Keyֵ
	 */
	private void put(Map<String, Object>mapOrg,Map<String, Object>mapDest,String key){
		Object obj = mapOrg.get(key);
		if (obj != null){
			mapDest.put(key, obj);
		}else{
			mapDest.put(key,0);
		}
	}

	/**
	 * 
	 * @Description: TODO �ϲ� mapFound �� mapAdd key����Ӧ����ֵ������mapFound��
	 * @param mapFound  �ϲ�Map����
	 * @param mapAdd    �ۼ�Map
	 * @param key       ��Ҫ�ۼӵ�Keyֵ
	 */
	private void calc(Map<String, Object> mapFound,Map<String, Object> mapAdd,String key){
		Double n1 = Double.parseDouble(mapFound.get(key).toString());
		Double n2 = Double.parseDouble(mapAdd.get(key).toString());
		mapFound.put(key, n1+n2);		
	}
	
	/**
	 * 
	 * @Description: TODO ���ݴ����Map����һ���µ�Map 
	 * @param map    ԭʼ��MAP
	 * @param headers ͷ����
	 * @param suffixs ��׺����
	 * @return Map <ROUTEHX "����-�׶�/����"><PAX.01 1000> <COUNTFLT.01 12> <PAX.01.Dep 500> <PAX.01.Arr 500>
	 */
	private Map<String, Object> createMap(Map<String, Object>map){
		Map<String, Object> mResult = new HashMap<String, Object>();
		mResult.put(sRouteType, map.get(sRouteType));
		for(String m:mNum){


			for(String header:headers){
				/*����<PAX.01 1000> <COUNTFLT.01 12><SEATSUM 500>����Map*/
				put(map, mResult, header+"."+m);
				for(String suffix:suffixs){
					/*����<PAX.01.Dep 500> <PAX.01.Arr 500 >.......����MAP*/					
					put(map, mResult, header+"."+m+"."+suffix);
				}
			}
		}
		return mResult;
	}

	/**
	 * 
	 * @Description: TODO �ϲ�MAP
	 * @param mapFound  �ҵ��ĺϲ���Map
	 * @param mapAdd    �ϲ�������
	 * @param headers   
	 * @param suffixs
	 */
	private void mergeMap(Map<String, Object> mapFound,Map<String, Object> mapAdd){
		for(String m:mNum){
			
			for(String header:headers){
				/*�ۼ�<PAX.01 1000> <COUNTFLT.01 12><SEATSUM 500>����Map*/
				calc(mapFound, mapAdd, header+"."+m);
				for(String suffix:suffixs){
					/*�ۼ�<PAX.01.Dep 500> <PAX.01.Arr 500 >.......����MAP*/					
					calc(mapFound, mapAdd, header+"."+m+"."+suffix);
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @Description: TODO����ȱʡ���ߵ�����
	 */
	public void updateDefaultRoute(){
		
		/*��ֵ�ۼӵ�����*/
		for(int i=0;i<mapValues.length;i++){
			mapValues[i] = new HashMap<String, Double>();
		}
		
		QueryConditions conditions = new QueryConditions();
		for(String routeTypeCn:SYS_VARS.RouteTypeCn){
			/*��ʼ�������ۼӵ�����*/
			for(int i=0;i<mapValues.length;i++){
				mapValues[i].put(iRate, 0.0);
				mapValues[i].put(oRate, 0.0);
				mapValues[i].put(Rate, 0.0);
				mapValues[i].put(CountFLT, 0.0);
				mapValues[i].put(Pax,0.0);
				mapValues[i].put(Seat, 0.0);
				mapValues[i].put(count, 0.0);
			}
			/*����ȱʡ�������� ���磺������ת(ȱʡ)*/
			String routeHX = routeTypeCn+defaultCN;
			/*���ݶ�Ӧ��˳���ҵ���ӦRouteType��ֵ*/
			RouteType routeType = RouteType.values()[SYS_VARS.RouteTypeCn.indexOf(routeTypeCn)];
			/*��ѯ���з������������ݲ����ں��������ҵ��ں�������*/
			conditions.setExpresstion(new Object[]{BaseRoute.ROUTEHX,"<>",routeHX,"AND",BaseRoute.ROUTETYPE,"=",routeType});
			List<BaseRoute> routes = findByConditionAll(conditions);
			/*�����з��������ĺ��߷�ÿ���½����ۼ�*/
			for (BaseRoute route:routes){
				for(BaseRouteMonth mRoute:route.getmRoutes()){
					if (mRoute.getRate() > 0){
						/*������¿����ʴ����㣬˵�������ݿɽ����ۼ�*/
						int m = mRoute.getMonth()-1;
						mapValues[m].put(iRate,mapValues[m].get(iRate)+mRoute.getiRate());
						mapValues[m].put(oRate,mapValues[m].get(oRate)+mRoute.getoRate());
						mapValues[m].put(Rate,mapValues[m].get(Rate)+mRoute.getRate());
						mapValues[m].put(Seat,mapValues[m].get(Seat)+mRoute.getSeat());
						mapValues[m].put(count,mapValues[m].get(count)+1);
					}
				}
			}
			/*�����ۼӵ����ݸ���ȱʡ����*/
			BaseRoute defaultRoute = findByFieldSingle(BaseRoute.ROUTEHX, routeHX);
			if (defaultRoute == null){
				defaultRoute = createRoute(routeHX);
				defaultRoute.setRouteType(routeType);
			}
			for(BaseRouteMonth mRoute:defaultRoute.getmRoutes()){
				int m = mRoute.getMonth()-1;
				if (mapValues[m].get(count) == 0){
					/*
					 * ���count��Ӧ��ֵΪ�㣬˵����ȱʡ���߸��µ����ݶ�Ϊ0
					 * Ϊ�˱��ⱻ����Ĵ��󣬸���count��Ӧ��ֵΪ1��������Ϊ0�������������Ϊ0
					 */
					mapValues[m].put(count, 1.0);
				}
				Double mIRate = mapValues[m].get(iRate)/mapValues[m].get(count);
				Double mORate = mapValues[m].get(oRate)/mapValues[m].get(count);
				Double mRate  = mapValues[m].get(Rate)/mapValues[m].get(count);
				Double mSeat  = mapValues[m].get(Seat)/mapValues[m].get(count);
				/*
				 *  ȱʡ�Ļ�׼��������һ������û�л�׼���ݵ�Ԥ�⺽���ϣ�
				 *  ˵����׼���ݲ�û����ǰ���𽵼ܴκ��������������
				 *  �����������ۼӵ�ƽ��ֵ��Ϊ��׼������
				 *  
				 */
				mRoute.setCountFLT(mapValues[m].get(CountFLT).intValue());
				mRoute.setPax(mapValues[m].get(Pax).intValue());
				mRoute.setSeat(mSeat.intValue());
				mRoute.setiRate(Double.parseDouble(df.format(mIRate)));
				mRoute.setoRate(Double.parseDouble(df.format(mORate)));
				mRoute.setRate(Double.parseDouble(df.format(mRate)));
			}
			update(defaultRoute);
		}
	}


	@Override
	public BaseRoute update(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof BaseRoute){
			/**
			 *  �������е��·�����
			 */
			BaseRoute route = (BaseRoute) obj;
			Set<BaseRouteMonth> mRoutes = route.getmRoutes();
			int noZeroCount = 0;
			double inRate  = 0.0;
			double outRate = 0.0;
			int countFLT = 0;
            int seat = 0;					
			for(BaseRouteMonth mRoute:mRoutes){
				if (mRoute.getRate() != 0){
					noZeroCount++;
					inRate  += mRoute.getiRate();
					outRate += mRoute.getoRate();
					countFLT += mRoute.getCountFLT();
					seat += mRoute.getSeat();					
				}
				
			}
			route.setCountFLT(countFLT);
			if (noZeroCount !=0){
				route.setSeat(seat/noZeroCount);
				route.setiRate(Double.parseDouble(df.format(inRate/noZeroCount)));
				route.setoRate(Double.parseDouble(df.format(outRate/noZeroCount)));
				route.setRate(Double.parseDouble(df.format((inRate+outRate)/2/noZeroCount)));
			}else{
				route.setSeat(0);
				route.setiRate(0);
				route.setoRate(0);
				route.setiRate(0);
			}
			
			return super.update(route);
		}else{
			return null;
		}
	}


	@Override
	public void updateForReportList(List<Map<String, Object>> rList) {

		/*��Map�����뺽����Ϣ*/
		for(Map<String, Object> map:rList){
			HisFlight flight = flightService.findByFieldSingle(HisFlight.BIGFLIGHTNO,  map.get(Flight));
			if (flight != null){
				map.put(RouteHX, flight.getRouteHX());
				map.put(sRouteType,flightService.getRouteType(flight));
			}
		}
		
		
		/*�ϲ�����mList*/
		List<Map<String, Object>> mList   = new ArrayList<Map<String,Object>>();
		/*
		 * ����ͬ�ĺ��߽��кϲ�
		 */
		for(Map<String, Object> map:rList){
			Map<String, Object> mapFound =null;
			for(Map<String, Object> mapFLT:mList){
				if (map.get(RouteHX).equals(mapFLT.get(RouteHX))){
					/*����ҵ���Ӧ�ĺ���*/
					mapFound = mapFLT;
				}
			}
			
			Map<String, Object> mapAdd = createMap(map);
			
			if (mapFound == null){
				/*�����Ӧ�ĺ���û���ҵ����½�map��ӽ�mList��*/
				mapAdd.put(RouteHX, map.get(RouteHX));
				mList.add(mapAdd);
			}else{
				/*����ҵ���Ӧ�ĺ��ߣ��ϲ���Ӧ��Map*/
				mergeMap(mapFound, mapAdd);
			}
		}
		
		/*��������ߵĿ�����,��λ�������мܴ�*/
		for(Map<String, Object> map:mList){
			for(String m:mNum){
				Double seat = 0.0;
				String seatSumKey = SeatSum+"."+m;
				String countFLTKey = CountFLT+"."+m;
				/*���ܴγ���2���·���Map*/
				Double seatSum = Double.parseDouble(map.get(seatSumKey).toString());
				Double countFLT = Double.parseDouble(map.get(CountFLT+"."+m).toString());
				map.put(countFLTKey, countFLT);
				/*����*/
				if (countFLT != 0){
					seat = Double.parseDouble(df.format(seatSum/countFLT));
					map.put(Seat+"."+m, seat);
				}else{
					map.put(Seat+"."+m, 0);
				}
				
				for(String suffix:suffixs){
					 String rateKey    = Rate+"."+m+"."+suffix;
					 String paxKey     = Pax+"."+m+"."+suffix;
					 seatSumKey        = SeatSum+"."+m+"."+suffix;
					 Double pax = Double.parseDouble(map.get(paxKey).toString());
					 seatSum = Double.parseDouble(map.get(seatSumKey).toString());
					 Double rate = 0.0;
					 if (seatSum != 0.0){
						 rate = Double.parseDouble(df.format(pax/seatSum*100));
						 map.put(rateKey, rate);
					 }else{
						 map.put(rateKey, 0);
					 }
				}
				
				
			}			
		}
		
		
		/*��mList������ݸ������ݿ�*/
		for(Map<String, Object> map:mList){
			BaseRoute route = findByFieldSingle(BaseRoute.ROUTEHX, map.get(RouteHX));
			if (route == null){
				route = createRoute(map.get(RouteHX).toString());
			}
			Set<BaseRouteMonth> mRoutes = route.getmRoutes();
			int countFLT = 0;
			for(BaseRouteMonth mRoute:mRoutes){
				for(String m:mNum){
					int int_m = Integer.valueOf(m);
					if (mRoute.getMonth()  == int_m){
						Double mSeat = Double.parseDouble(map.get(Seat+"."+m).toString());
						Double mCountFLT = Double.parseDouble(map.get(CountFLT+"."+m).toString());
					    Double miRate = Double.parseDouble(map.get(Rate+"."+m+"."+Arr).toString());
					    Double moRate = Double.parseDouble(map.get(Rate+"."+m+"."+Dep).toString());
					    Double mRate = Double.parseDouble(df.format((miRate+moRate)/2));
					    Double mpax  = Double.parseDouble(map.get(Pax+"."+m).toString()); 
					    if (mRate > 0){
					    	/*��������ʴ���0�Ž��и���*/
						    countFLT += mCountFLT.intValue();
						    mRoute.setiRate(miRate);
						    mRoute.setoRate(moRate);
						    mRoute.setRate(mRate);
						    mRoute.setCountFLT(mCountFLT.intValue());
						    mRoute.setSeat(mSeat.intValue());
						    mRoute.setPax(mpax.intValue());
					    }
					}
				}
			}
			if (countFLT >= LimitFLT){
				/*�ܴ�Ҫ����ָ���ļܴ����Ž��б���*/
				if (map.get(sRouteType) != null){
					route.setRouteType((RouteType) map.get(sRouteType));
				}
				update(route);
			}
		}
		updateDefaultRoute();
		
	}



	
	
	
}
