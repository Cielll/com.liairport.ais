package com.lzairport.ais.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

/** 
 * ��д���ת��Scriptlet�� 
 * 
 * @author Spark (Email: spark.unt@gmail.com)  
 */  

/**
 * 
 * FileName      TransChineseMoneyScriptlet.java
 * @Description  TODO(��һ�仰�������ļ���ʲô)
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015-9-22 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015-9-22      Yu    1.0        1.0
 * <p>Why & What is modified: <�޸�ԭ������>
 */

public class TransChineseMoneyScriptlet extends JRDefaultScriptlet {  
    /* 
     * Ĭ�Ϲ��췽�� 
     */  
    public TransChineseMoneyScriptlet() {  
      
    }  
  
    /** 
     * ��ý��ĺ��ִ�д��ʽ <br> 
     * @param money Сд����ַ��� 
     * @return ��д�ĺ��ֽ�� 
     */  
    public static String getChineseMoney(String money) {  
        String text = transChineseMoney1(money) + transChineseMoney2(money);  
        Pattern p = Pattern.compile("���", Pattern.CASE_INSENSITIVE);  
        Matcher m = p.matcher(text);  
        text = m.replaceAll("");  
        return text;  
    }  
  
    /** 
     * �ص���������������֣�������ת�������Ĵ�д�ĸ�ʽ <br> 
     * <br> 
     * �����������������ֳ������ܷ�Χʱ�ܾ�ת�����˳���<br> 
     * @param ���ݲ����ַ���S �������� 
     * @return ����ת������ַ��� 
     */  
    public static String transChineseMoney1(String s) {  
        String ss = s;  
        String tmpnewchar = "";  
        String[] part = ss.split("\\.");  
  
        if (part[0].length() > 10) {  
            // ������ת��λ��  
            return "";  
        }  
        for (int i = 0; i < part[0].length(); i++) {  
            char perchar = part[0].charAt(i);  
            if (perchar == '0')  
                tmpnewchar = tmpnewchar + "��";  
            if (perchar == '1')  
                tmpnewchar = tmpnewchar + "Ҽ";  
            if (perchar == '2')  
                tmpnewchar = tmpnewchar + "��";  
            if (perchar == '3')  
                tmpnewchar = tmpnewchar + "��";  
            if (perchar == '4')  
                tmpnewchar = tmpnewchar + "��";  
            if (perchar == '5')  
                tmpnewchar = tmpnewchar + "��";  
            if (perchar == '6')  
                tmpnewchar = tmpnewchar + "½";  
            if (perchar == '7')  
                tmpnewchar = tmpnewchar + "��";  
            if (perchar == '8')  
                tmpnewchar = tmpnewchar + "��";  
            if (perchar == '9')  
                tmpnewchar = tmpnewchar + "��";  
  
            int j = part[0].length() - i - 1;  
            if (j == 0)  
                tmpnewchar = tmpnewchar + "Բ";  
            if (j == 1 && perchar != 0)  
                tmpnewchar = tmpnewchar + "ʰ";  
            if (j == 2 && perchar != 0)  
                tmpnewchar = tmpnewchar + "��";  
            if (j == 3 && perchar != 0)  
                tmpnewchar = tmpnewchar + "Ǫ";  
            if (j == 4 && perchar != 0)  
                tmpnewchar = tmpnewchar + "��";  
            if (j == 5 && perchar != 0)  
                tmpnewchar = tmpnewchar + "ʰ";  
            if (j == 6 && perchar != 0)  
                tmpnewchar = tmpnewchar + "��";  
            if (j == 7 && perchar != 0)  
                tmpnewchar = tmpnewchar + "Ǫ";  
            if (j == 8 && perchar != 0)  
                tmpnewchar = tmpnewchar + "��";  
            if (j == 9 && perchar != 0)  
                tmpnewchar = tmpnewchar + "ʰ";  
        }  
        return tmpnewchar;  
    }  
  
    /** 
     * �ص��������С�����֣�������ת�������Ĵ�д�ĸ�ʽ <br> 
     * <br> 
     * ����������С�����ֳ�����λʱϵͳ�Զ��ضϡ�<br> 
     *  
     * @param ���ݲ����ַ��� 
     *  
     * @return ����ת������ַ��� 
     */  
    public static String transChineseMoney2(String s) {  
        String ss = s;  
        String tmpnewchar1 = "";  
        String[] part = ss.split("\\.");  
  
        if (ss.indexOf(".") != -1) {  
            if (part[1].length() > 2) {  
                // MessageDialog.openInformation(null,"��ʾ","С����֮��ֻ�ܱ�����λ,ϵͳ���Զ��ض�");  
                part[1] = part[1].substring(0, 2);  
            }  
            for (int i = 0; i < part[1].length(); i++) {  
                char perchar = part[1].charAt(i);  
//              System.out.println(perchar);  
                if (perchar == '0')  
                    tmpnewchar1 = tmpnewchar1 + "��";  
                if (perchar == '1')  
                    tmpnewchar1 = tmpnewchar1 + "Ҽ";  
                if (perchar == '2')  
                    tmpnewchar1 = tmpnewchar1 + "��";  
                if (perchar == '3')  
                    tmpnewchar1 = tmpnewchar1 + "��";  
                if (perchar == '4')  
                    tmpnewchar1 = tmpnewchar1 + "��";  
                if (perchar == '5')  
                    tmpnewchar1 = tmpnewchar1 + "��";  
                if (perchar == '6')  
                    tmpnewchar1 = tmpnewchar1 + "½";  
                if (perchar == '7')  
                    tmpnewchar1 = tmpnewchar1 + "��";  
                if (perchar == '8')  
                    tmpnewchar1 = tmpnewchar1 + "��";  
                if (perchar == '9')  
                    tmpnewchar1 = tmpnewchar1 + "��";  
  
                if (i == 0 && perchar != 0)  
                    tmpnewchar1 = tmpnewchar1 + "��";  
                if (i == 1 && perchar != 0)  
                    tmpnewchar1 = tmpnewchar1 + "��";  
            }  
        }  
        return tmpnewchar1;  
    }  
  
  
/** Begin EVENT_AFTER_COLUMN_INIT This line is generated by iReport. Don't modify or move please! */  
public void afterColumnInit() throws JRScriptletException  
{  
    super.beforeColumnInit();  
}  
/** End EVENT_AFTER_COLUMN_INIT This line is generated by iReport. Don't modify or move please! */  
/** Begin EVENT_AFTER_DETAIL_EVAL This line is generated by iReport. Don't modify or move please! */  
public void afterDetailEval() throws JRScriptletException  
{  
    Double sumTaxMoney = getVariableValue("SumPrice") == null ? new Double(0.0)  
    : (java.lang.Double) getVariableValue("SumPrice");  
  
//  System.out.println("sumTaxMoney = " + sumTaxMoney);  
    String cnMoney = getChineseMoney(sumTaxMoney+"");  
//  System.out.println("cnMoney = " + cnMoney);  
    this.setVariableValue("cnPrice", cnMoney);  
    super.afterDetailEval();  
}  
/** End EVENT_AFTER_DETAIL_EVAL This line is generated by iReport. Don't modify or move please! */  
/** Begin EVENT_AFTER_GROUP_INIT This line is generated by iReport. Don't modify or move please! */  
public void afterGroupInit(String groupName) throws JRScriptletException  
{  
    super.afterGroupInit(groupName);  
}  
/** End EVENT_AFTER_GROUP_INIT This line is generated by iReport. Don't modify or move please! */  
/** Begin EVENT_AFTER_PAGE_INIT This line is generated by iReport. Don't modify or move please! */  
public void afterPageInit() throws JRScriptletException  
{  
    super.afterPageInit();  
}  
/** End EVENT_AFTER_PAGE_INIT This line is generated by iReport. Don't modify or move please! */  
/** Begin EVENT_AFTER_REPORT_INIT This line is generated by iReport. Don't modify or move please! */  
public void afterReportInit() throws JRScriptletException  
{  
      
      
      
}  
/** End EVENT_AFTER_REPORT_INIT This line is generated by iReport. Don't modify or move please! */  
/** Begin EVENT_BEFORE_COLUMN_INIT This line is generated by iReport. Don't modify or move please! */  
public void beforeColumnInit() throws JRScriptletException  
{  
          
}  
/** End EVENT_BEFORE_COLUMN_INIT This line is generated by iReport. Don't modify or move please! */  
/** Begin EVENT_BEFORE_DETAIL_EVAL This line is generated by iReport. Don't modify or move please! */  
public void beforeDetailEval() throws JRScriptletException  
{  
      
}  
/** end EVENT_BEFORE_DETAIL_EVAL Please don't touch or move this comment*/  
  
/** End EVENT_BEFORE_DETAIL_EVAL This line is generated by iReport. Don't modify or move please! */  
/** Begin EVENT_BEFORE_GROUP_INIT This line is generated by iReport. Don't modify or move please! */  
public void beforeGroupInit(String groupName) throws JRScriptletException  
{  
      
}  
/** End EVENT_BEFORE_GROUP_INIT This line is generated by iReport. Don't modify or move please! */  
/** Begin EVENT_BEFORE_PAGE_INIT This line is generated by iReport. Don't modify or move please! */  
public void beforePageInit() throws JRScriptletException  
{  
      
}  
/** End EVENT_BEFORE_PAGE_INIT This line is generated by iReport. Don't modify or move please! */  
/** Begin EVENT_BEFORE_REPORT_INIT This line is generated by iReport. Don't modify or move please! */  
public void beforeReportInit() throws JRScriptletException  
{  
      
}  
  
/** End EVENT_BEFORE_REPORT_INIT This line is generated by iReport. Don't modify or move please! */  
  
}  