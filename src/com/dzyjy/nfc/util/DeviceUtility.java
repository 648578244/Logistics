package com.dzyjy.nfc.util;

import java.lang.reflect.Field;

import android.content.Context;
import android.view.WindowManager;

/**
 * �豸����������
 * @Description: �豸����������

 * @File: DeviceUtility.java

 * @Package com.navigation.utility

 * @Author Hanyongjian

 * @Company HuajunTec

 * @Date 2012-8-11 ����06:23:26

 * @Version V1.0
 */
public class DeviceUtility {
	/**
	 * ��ȡ��Ļ�ĳߴ�
	 * @param context
	 * @return
	 */
	public static int[] getScreenSize(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(
						    Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();//��Ļ���
		int height = wm.getDefaultDisplay().getHeight();//��Ļ�߶�
		int[] size = {width,height};
		
		return size;
	}
	
//	/**
//	 * ��ȡ״̬���߶�
//	 * @param context
//	 * @return
//	 */
//	public static int getStatusBarHeight(Context context){
//		int statusBarHeight = 0;
//		int screenHeight = getScreenSize(context)[1];
//		
//		switch(screenHeight){
//		case 240:
//			statusBarHeight = 20;
//			break;
//		case 480:
//			statusBarHeight = 25;
//			break;
//		case 800:
//			statusBarHeight = 38;
//			break;
//		default:
//			break;
//		}
//		
//		return statusBarHeight;
//	}
	
	public static int getStatusBarHeight(Context context){ 
        Class<?> c = null; 
        Object obj = null; 
        Field field = null;
        
        int x = 0, statusBarHeight = 0;
        
        try { 
            c = Class.forName("com.android.internal.R$dimen"); 
            obj = c.newInstance(); 
            field = c.getField("status_bar_height"); 
            x = Integer.parseInt(field.get(obj).toString()); 
            statusBarHeight = context.getResources().getDimensionPixelSize(x);  
        } catch (Exception e1) { 
            e1.printStackTrace(); 
        }  
        
        return statusBarHeight; 
    }
}
