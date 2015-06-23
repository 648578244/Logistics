package com.dzyjy.nfc.view;

import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dzyjy.nfc.util.ImageUtility;

/**
 * ��������ѡ��˵���
 * @Description: ��������ѡ��˵���

 * @File: PopupMenuItem.java

 * @Package com.navigation.control

 * @Author Hanyonglu

 * @Date 2012-7-30 ����09:16:55

 * @Version V1.0
 */
public class PulldownMenuItem {
	// ��������
	private String menuText = null;
	// ������ɫ
	private int menuTextColor = 0;
	// ���ִ�С
	private float menuTextSize = 0;
	// �˵�ͼƬ
	private int menuImageRes = 0;
	// Context
	private Context mContext = null;
	// �˵���LinearLayout
	private LinearLayout linearLayout = null;
	// Ĭ�ϵ������������ʾ��ͼƬ���·�  
	private PulldownMenuAlign menuAlign = PulldownMenuAlign.TEXT_BOTTOM;
	
	/**
	 * Ĭ�ϵĹ�����
	 */
	public PulldownMenuItem() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ��Context�Ĺ�����
	 * @param context
	 */
	public PulldownMenuItem(Context context){
		this(context, 0, null, 0, 0, PulldownMenuAlign.TEXT_BOTTOM);
	}
	
	/**
	 * ����εĹ�����
	 * @param context
	 * @param imgRes
	 * @param text
	 * @param textColor
	 * @param textSize
	 * @param align
	 */
	public PulldownMenuItem(Context context, int imgRes, String text,
							int textColor, float textSize, 
							PulldownMenuAlign align){
		menuImageRes = imgRes;
		menuText = text;
		menuTextColor = textColor;
		menuTextSize = textSize;
		menuAlign = align;
		mContext = context;
	}
	
	/**
	 * ������
	 * @param context
	 * @param item
	 */
	public PulldownMenuItem(Context context, PulldownMenuItem item){
		this(context, 0, null, item.getMenuTextColor(), 
			 item.getMenuTextSize(), item.getMenuAlign());
	}
	
	/**
	 * ��ʼ���˵���
	 */
	private void initLayout(){
		Context context = mContext;
		linearLayout = new LinearLayout(context);
		linearLayout.setLayoutParams(new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 
				ViewGroup.LayoutParams.MATCH_PARENT));
		linearLayout.setGravity(Gravity.CENTER);

		TextView textView = getTextView(context);
		// ��������Padding
		textView.setPadding(0, 0, 0, 15);
		ImageView imageView = getImageView(context);
		// ����ͼƬPadding
		imageView.setPadding(0, 5, 0, 0);
		
		if (null != textView && null != imageView){
			// ��ȡͼƬ�Ĵ�С
			Point point = ImageUtility.getImageDimension(context, menuImageRes);
			
			switch (menuAlign){
			case TEXT_BOTTOM: // ������ʾ��ͼƬ���·�
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				linearLayout.addView(
						imageView, 
						new ViewGroup.LayoutParams(
								ViewGroup.LayoutParams.MATCH_PARENT, 
								point.y));
				linearLayout.addView(
						textView, 
						new ViewGroup.LayoutParams(
								ViewGroup.LayoutParams.MATCH_PARENT, 
								ViewGroup.LayoutParams.MATCH_PARENT));
				break;
			case TEXT_TOP:// ������ʾ��ͼƬ���Ϸ�
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				linearLayout.addView(
						textView, 
						new ViewGroup.LayoutParams(
								ViewGroup.LayoutParams.MATCH_PARENT, 
								ViewGroup.LayoutParams.MATCH_PARENT));
				linearLayout.addView(
						imageView, 
						new ViewGroup.LayoutParams(
								ViewGroup.LayoutParams.MATCH_PARENT, 
								point.y));
				break;
			case TEXT_LEFT:// ������ʾ��ͼƬ����
				linearLayout.setOrientation(LinearLayout.HORIZONTAL);
				linearLayout.addView(
						textView, 
						new ViewGroup.LayoutParams(
								ViewGroup.LayoutParams.WRAP_CONTENT, 
								ViewGroup.LayoutParams.MATCH_PARENT));
				linearLayout.addView(
						imageView, 
						new ViewGroup.LayoutParams(
								point.x, 
								ViewGroup.LayoutParams.MATCH_PARENT));
				break;
			case TEXT_RIGHT:// ������ʾ��ͼƬ���ҷ�
				linearLayout.setOrientation(LinearLayout.HORIZONTAL);
				linearLayout.addView(
						imageView, 
						new ViewGroup.LayoutParams(
								point.x, 
								ViewGroup.LayoutParams.MATCH_PARENT));
				linearLayout.addView(
						textView, 
						new ViewGroup.LayoutParams(
								ViewGroup.LayoutParams.WRAP_CONTENT, 
								ViewGroup.LayoutParams.MATCH_PARENT));
				break;
			}
		}else{
			if (null != textView){
				linearLayout.addView(
						textView, 
						new ViewGroup.LayoutParams(
								ViewGroup.LayoutParams.MATCH_PARENT, 
								ViewGroup.LayoutParams.MATCH_PARENT));
			} else if (null != imageView){
				// ��ȡͼƬ�Ĵ�С
				Point point = ImageUtility.getImageDimension(context, menuImageRes);
				linearLayout.addView(imageView, new ViewGroup.LayoutParams(point.x, point.y));
			}
		}
	}
	
	/**
	 * ���ò˵�ͼƬ
	 * @param imgResource
	 */
	public void setImageRes(int imgResource){
		menuImageRes = imgResource;
	}
	
	/**
	 * �������ִ�С
	 * @param size
	 */
	public void setMenuTextSize(float size){
		menuTextSize = size;
	}
	
	/**
	 * ����������ɫ
	 * @param color
	 */
	public void setMenuTextColor(int color){
		menuTextColor = color;
	}
	
	/**
	 * ������������
	 * @param text
	 */
	public void setMenuText(String text){
		menuText = text;
	}
	
	/**
	 * �����������з�ʽ
	 * @param align
	 */
	public void setMenuAlign(PulldownMenuAlign align){
		menuAlign = align;
	}
	
	/**
	 * ��ȡ��������
	 * @return
	 */
	public String getMenuText(){
		return menuText;
	}
	
	/**
	 * ��ȡ���ֵ���ɫ
	 * @return
	 */
	public int getMenuTextColor(){
		return menuTextColor;
	}
	
	/**
	 * ��ȡ���ֵĴ�С
	 * @return
	 */
	public float getMenuTextSize(){
		return menuTextSize;
	}
	
	/**
	 * ��ȡ�½���TextView
	 * @param context
	 * @return
	 */
	private TextView getTextView(Context context){
		if (TextUtils.isEmpty(menuText)){
			return null;
		}
		
		TextView txtView = new TextView(context);
		
		if (menuTextColor != 0){
			txtView.setTextColor(menuTextColor);
		}
		
		if (menuTextSize != 0){
			txtView.setTextSize(menuTextSize);
		}
		
		txtView.setText(menuText);
		txtView.setGravity(Gravity.CENTER);
		
		return txtView;
	}
	
	/**
	 * ��ȡ�������з�ʽ
	 * @return
	 */
	public PulldownMenuAlign getMenuAlign(){
		return menuAlign;
	}
	
	/**
	 * ��ȡ�½���ImageView
	 * @param context
	 * @return
	 */
	private ImageView getImageView(Context context){
		if (menuImageRes == 0){
			return null;
		}
		
		ImageView view = new ImageView(context);
		view.setImageResource(menuImageRes);
		
		return view;
	}
	
	/**
	 * ��ʼ���˵����ȡ�˵��������
	 * @return
	 */
	public View getView(){
		initLayout();
		return linearLayout;
	}
}
