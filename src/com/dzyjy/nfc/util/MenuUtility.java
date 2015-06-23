package com.dzyjy.nfc.util;

import android.content.Context;
import android.view.View;

import com.dzyjy.nfc.R;
import com.dzyjy.nfc.view.PulldownMenuView;

/**
 * PulldownMenuView����������
 * @Description: PulldownMenuView����������

 * @File: PulldownMenuUtility.java

 * @Package com.navigation.utility

 * @Author Hanyonglu

 * @Date 2012-7-30 ����11:41:04

 * @Version V1.0
 */
public class MenuUtility {
	private Context context = null;
	// PulldownMenuView����
	private PulldownMenuView menu = null;
	// ͼƬ��Դ
	private int[] imageRes = null;
	// ��������
	private String[] texts = null;
	// �˵��߶�
	private int height = 0;
	private View anchorView = null;
	
	/**
	 * Ĭ�ϵĹ�����
	 */
	public MenuUtility() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ��Context�Ĺ�����
	 * @param context
	 */
	public MenuUtility(Context context) {
		// TODO Auto-generated constructor stub
		this(context,null,null,0,null);
	}
	
	/**
	 * ����εĹ�����
	 * @param context
	 * @param imageRes
	 * @param texts
	 */
	public MenuUtility(Context context,int[] imageRes,String[] texts,int height,View anchorView){
		this.context = context;
		this.imageRes = imageRes;
		this.texts = texts;
		this.height = height;
		this.anchorView = anchorView;
	}
	
	/**
	 * ����ͼƬ��Դ
	 * @param imageRes
	 */
	public void setImageRes(int[] imageRes){
		this.imageRes = imageRes;
	}
	
	/**
	 * ������������
	 * @param texts
	 */
	public void setTexts(String[] texts){
		this.texts = texts;
	}
	
	/**
	 * ���ø߶�
	 * @param height
	 */
	public void setHeight(int height){
		this.height = height;
	}
	
	/**
	 * ������ʾ��λ��
	 * @param anchor
	 */
	public void setAnchorView(View anchor){
		anchorView = anchor;
	}
	
	/**
	 * ��ȡPulldownMenuView����
	 * ����������ʽչ�ֳ����˵�
	 * @return
	 */
	public PulldownMenuView getPulldownMenuView(String currentItem){
		PulldownMenuView menu = new PulldownMenuView(context);
		menu.setImageRes(imageRes);
		menu.setMenuText(texts);
//		menu.setHeight(height);
		menu.setAnchorView(anchorView);
		menu.setCurrentItem(currentItem);
		menu.setBackground(R.drawable.navigation_bg);
		
		return menu;
	}
	
	/**
	 * ��ȡPulldownMenuView����
	 * �����ϵ����ķ�ʽչ�ֳ����˵�
	 * @return
	 */
	public PulldownMenuView getPopupMenuView(){
		PulldownMenuView menu = new PulldownMenuView(context);
		menu.setImageRes(imageRes);
		menu.setMenuText(texts);
		// menu.setLocation(Gravity.BOTTOM | Gravity.CENTER);
		menu.setAnimStyle(R.style.pulldown_in_out);
		menu.setBackground(R.drawable.navigation_bg);
		
		return menu;
	}
}
