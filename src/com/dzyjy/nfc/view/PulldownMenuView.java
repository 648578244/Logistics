package com.dzyjy.nfc.view;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.dzyjy.nfc.adapter.PulldownMenuAdapter;
import com.dzyjy.nfc.constant.ConstantCategoryMenu;
import com.dzyjy.nfc.util.ImageUtility;

/**
 * ��������ѡ��˵�View
 * @Description: ��������ѡ��˵�View

 * @File: PulldownMenuView.java

 * @Package com.navigation.control

 * @Author Hanyonglu

 * @Date 2012-7-29 ����11:03:40

 * @Version V1.0
 */
public class PulldownMenuView {
	private Context context = null;
	// PopupWindow����
	private PopupWindow popupWindow = null;
	// �˵�ͼƬ��Դ
	private int[] menuImageRes = new int[0];
	// �˵�������
	private String[] menuTexts = new String[0];
	// �˵����
	private int menuBackground = 0;
	// ��ʾ�����ز˵��Ķ�����ʽ
	private int menuAnimStyle = 0;
	// ���ִ�С
	private float menuTextSize = -1;
	// ������ɫ
	private int menuTextColor = -1;
	// �˵���ѡ�е�Ч��
	private int menuSelector = -1;
	// �˵�����
	private int menuWidth = 0;
	// �˵���߶�
	private int menuHeight = 0;
	// ��Ų˵����GridView����
	private GridView menuGridView;
	// �˵����ֵ���󳤶ȣ�������󳤶����Ի���ʾ"����"
	private int menuMaxStrLength = 4;
	// �Ƿ�Թ����ַ�����ȡ�Ż������menuMaxStrLength����
	private boolean isOptimizeText = true;
	// �˵���ʾλ�õ������View
	private View anchorView = null;
	// ��ǰѡ����
	private String currentItem = null;
	// �˵������¼�
	private OnMenuItemClickListener menuItemListener;
	// ��ʾλ�ã�Ĭ���ǴӶ�����ʾ����
	// private int menuLocation = Gravity.BOTTOM | Gravity.CENTER;
	// ���������ͼƬ�����з�ʽ��Ĭ����������ͼƬ�·�
	private PulldownMenuAlign menuAlign = PulldownMenuAlign.TEXT_BOTTOM;
	// �˵����
	private ArrayList<PulldownMenuItem> menuMenuItems = 
			new ArrayList<PulldownMenuItem>();
	
	/**
	 * Ĭ�ϵĹ�����
	 */
	public PulldownMenuView() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ��Context�Ĺ�����
	 * @param context
	 */
	public PulldownMenuView(Context context) {
		// TODO Auto-generated constructor stub
		if (null == context){
			throw new IllegalArgumentException();
		}
		
		this.context = context;
	}
	
	/**
	 * ����εĹ�����
	 * @param context
	 * @param menuImageRes
	 * @param menuTexts
	 * @param menuBackground
	 * @param menuAnimStyle
	 */
	public PulldownMenuView(Context context,int[] menuImageRes,
							String[] menuTexts,int menuBackground,
							int menuAnimStyle){
		if (null == context){
			throw new IllegalArgumentException();
		}
		
		this.context = context;
		this.menuImageRes = menuImageRes;
		this.menuTexts = menuTexts;
		this.menuBackground = menuBackground;
		this.menuAnimStyle = menuAnimStyle;
	}
	
	/**
	 * ����ͼƬ��Դ
	 * @param imageRes
	 */
	public void setImageRes(int[] imageRes){
		if (null != imageRes){
			menuImageRes = imageRes;
		}
	}
	
	/**
	 * ���ò˵�����
	 * @param backgroundRes
	 */
	public void setBackground(int backgroundRes){
		menuBackground = backgroundRes;
	}
	
	/**
	 * ���ò˵��������
	 * @param txtRes ��Դ����
	 */
	public void setMenuText(int[] textRes){
		if (null == textRes){
			return;
		}
		
		final Resources res = context.getResources();
		final int length = textRes.length;
		menuTexts = new String[length];
		
		for (int i = 0; i < length; i++){
			menuTexts[i] = res.getString(textRes[i]);
		}
	}
	
	/**
	 * ���ò˵��������
	 * @param txtRes
	 */
	public void setMenuText(String[] texts){
		menuTexts = texts;
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
	 * �����ı����ͼƬ�����з�ʽ
	 * @param align
	 */
	public void setMenuAlign(PulldownMenuAlign align){
		menuAlign = align;
	}
	
	/**
	 * ���������ı�����󳤶�
	 * @param length
	 */
	public void setMaxTextLength(int length){
		menuMaxStrLength = length;
	}
	
	/**
	 * �����Ƿ�Թ����ı������Ż�
	 * @param isOptimize
	 */
	public void isOptimizeText(boolean isOptimize){
		isOptimizeText = isOptimize;
	}
	
	/**
	 * ���ò˵�����
	 * @param animStyle
	 */
	public void setAnimStyle(int animStyle){
		menuAnimStyle = animStyle;
	}
	
	/**
	 * ���ò˵��Ŀ��
	 * @param width
	 */
	public void setWidth(int width){
		menuWidth = width;
	}
	
	/**
	 * ���ò˵��ĸ߶�
	 * @param height
	 */
	public void setHeight(int height){
		menuHeight = height;
	}
	
	/**
	 * ���ò˵���ʾ��λ��
	 * @param location
	 */
//	public void setLocation(int location){
//		menuLocation = location;
//	}
	
	/**
	 * ���ò˵����ѡ�е�Ч��
	 * @param selector
	 */
	public void setSelector(int selector){
		menuSelector = selector;
	}
	
	/**
	 * ���ò˵����GridView
	 * @param gridView
	 */
	public void setMenuGridView(GridView gridView){
		menuGridView = gridView;
	}
	
	/**
	 * ���ò˵�����ʾλ�������View
	 * @param anchor
	 */
	public void setAnchorView(View anchor){
		anchorView = anchor;
	}
	
	/**
	 * ���õ�ǰѡ����
	 * @param currentItem
	 */
	public void setCurrentItem(String currentItem){
		this.currentItem = currentItem;
	}
	
	/**
	 * ��ʾ�˵�
	 * @return ��ʾ�ɹ�����true, ʧ�ܷ���false
	 */
	public boolean show(){
		if (hide()){
			return false;
		}
		
		// final Context context = context;
		final int length = menuImageRes.length;
		final int txtLength = menuTexts.length;
		Point point = new Point();
		
		if (length != 0 && txtLength != 0){
			Point p1 = getTextMaxDimenstion(menuTexts);
			Point p2 = ImageUtility.getImageMaxDimension(context,menuImageRes);
			
			switch (menuAlign){
			case TEXT_BOTTOM:
			case TEXT_TOP:
				point.x = Math.max(p1.x, p2.x);
				point.y = p1.y + p2.y;
				break;
			case TEXT_LEFT:
			case TEXT_RIGHT:
				point.x = p1.x + p2.x;
				point.y = Math.max(p1.y, p2.y);
				break;
			}
		} else{
			if (length != 0){
				point = ImageUtility.getImageMaxDimension(context,menuImageRes);
			}
			else if (txtLength != 0){
				point = getTextMaxDimenstion(menuTexts);
			}
		}
		
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		int width = menuWidth == 0 ? metrics.widthPixels : menuWidth;
		float density = metrics.density;
		int imgWidth = point.x;
		int height = point.y + 20;
		// ��ȥ5dp�ļ��һ�����ܰڷ�ͼƬ�ĸ���
		int columns = (int) ((width - 5 * density) / (imgWidth + 5 * density));
		
		int leng = length != 0 ? length : txtLength;
		int rows = columns == 0 ? 0 : leng / columns;
		
		if (columns * rows < leng){
			rows += 1;
		}
		
		final LinearLayout layout = initLayout(context);
		GridView gridView = menuGridView;
		
		if (null == gridView){
			gridView = getMenuGirdView(context, columns);
		} else{
			setMenuListener(gridView);
		}
		
		layout.addView(
				gridView, new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, 
					ViewGroup.LayoutParams.MATCH_PARENT
					)
				);
		
		// TODO �Ը߶Ƚ�������
		int h = 0;
		
		if (menuAlign == PulldownMenuAlign.TEXT_LEFT || menuAlign == PulldownMenuAlign.TEXT_RIGHT){
			h = (int) (height * rows + 5 * density);
		} else if (menuAlign == PulldownMenuAlign.TEXT_BOTTOM || menuAlign == PulldownMenuAlign.TEXT_TOP){
			h = (int) ((height + 5 * density) * rows);
		}
		
		if (txtLength != 0){
			h += 6 * density;
		}
		
		popupWindow = new PopupWindow(context);
		popupWindow.setWidth(width);
		popupWindow.setHeight(menuHeight == 0 ? h : menuHeight);
		popupWindow.setContentView(layout);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setTouchable(true);
		
		// ���ñ���Ϊnull�������ؼ�PopupWindow�ͻ�����
		popupWindow.setBackgroundDrawable(null);
		
		if (menuAnimStyle != 0){
			popupWindow.setAnimationStyle(menuAnimStyle);
		}
		
		// popupWindow.showAtLocation(layout, menuLocation, 0, 0);
		popupWindow.showAsDropDown(anchorView);
		
		return true;
	}
	
	/**
	 * ��ʼ��LinearLayout
	 * @param context
	 * @return
	 */
	private LinearLayout initLayout(Context context){
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setFadingEdgeLength(0);
		layout.setGravity(Gravity.CENTER);
		// �����������ֵı߾�
		// layout.setPadding(0, 0, 0, 0);
		
		layout.setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event){
				if (event.getAction() == MotionEvent.ACTION_DOWN){
					hide();
				}
				
				return false;
			}
		});
		
		return layout;
	}
	
	/**
	 * ��ʼ�����ݣ������ݼ��ص���Ӧ��View��
	 */
	private void initData(){
		PulldownMenuItem item = new PulldownMenuItem(context);
		item.setMenuAlign(menuAlign);
		item.setMenuTextColor(menuTextColor);
		item.setMenuTextSize(menuTextSize);
		int txtLength = menuTexts.length;
		int imgLength = menuImageRes.length;
		
		if (txtLength != 0 && imgLength != 0){
			for (int i = 0; i < imgLength; i++){
				PulldownMenuItem menuItem = new PulldownMenuItem(context, item);
				
				if(!currentItem.equals(menuTexts[i])){
					menuItem.setImageRes(menuImageRes[i]);
					menuItem.setMenuText(menuTexts[i]);
				}else{
					menuItem.setMenuText(menuTexts[i]);
					menuItem.setMenuTextColor(Color.parseColor("#4FA7F9"));
					menuItem.setImageRes(ConstantCategoryMenu.newsImageResPress[i]);
				}
				
				menuMenuItems.add(menuItem);
			}
		} else{
			if (txtLength != 0){
				for (int i = 0; i < txtLength; i++){
					PulldownMenuItem menuItem = new PulldownMenuItem(context, item);
					menuItem.setMenuText(menuTexts[i]);
					menuMenuItems.add(menuItem);
				}
			}else if (imgLength != 0){
				for (int i = 0; i < imgLength; i++){
					PulldownMenuItem menuItem = new PulldownMenuItem(context, item);
					menuItem.setImageRes(menuImageRes[i]);
					menuMenuItems.add(menuItem);
				}
			}
		}
	}
	
	/**
	 * ��ʼ���˵�����
	 * @param context
	 * @param columns �˵�������
	 * @return
	 */
	private GridView getMenuGirdView(Context context, int columns){
		if (menuMenuItems.isEmpty()){
			initData();
		}
		
		if (null != menuGridView){
			return menuGridView;
		}
		
		GridView gridView = new GridView(context);
		gridView.setLayoutParams(new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 
				ViewGroup.LayoutParams.MATCH_PARENT));
		// �����¼�������
		gridView.setAdapter(new PulldownMenuAdapter(menuMenuItems));
		gridView.setVerticalSpacing(1);
		gridView.setNumColumns(columns);
		gridView.setGravity(Gravity.CENTER);
		gridView.setVerticalScrollBarEnabled(false);
		
		if (menuBackground != 0){
			gridView.setBackgroundResource(menuBackground);
		}
		
		if (menuSelector != -1){
			gridView.setSelector(menuSelector);
		}
		
		gridView.setHorizontalScrollBarEnabled(false);
		setMenuListener(gridView);
		
		return gridView;
	}
	
	/**
	 * ע��˵�������¼�
	 * @param gridView
	 */
	private void setMenuListener(GridView gridView){
		if (null == gridView.getOnItemClickListener()){
			gridView.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(
						AdapterView<?> parent, 
						View view,
						int position, 
						long id){
					if (null != menuItemListener){
						menuItemListener.onMenuItemClick(parent, view, position);
					}
					
					hide();
				}
			});
		}
		
		// �����ؼ���˵������ز˵�
		gridView.setOnKeyListener(new OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event){
				if (event.getAction() == KeyEvent.ACTION_DOWN){
					switch (keyCode){
					case KeyEvent.KEYCODE_BACK:
					case KeyEvent.KEYCODE_MENU:
						hide();
						break;
					}
				}
				
				return false;
			}
		});
	}
	
	/**
	 * ��ȡ�������ı�����󳤶�
	 * @param txts
	 * @return
	 */
	private Point getTextMaxDimenstion(String[] txts){
		final Point point = new Point();
		final Rect bounds = new Rect();
		final Paint paint = new Paint();
		float size = menuTextSize != -1 ? menuTextSize : context.getResources().getDisplayMetrics().density * 16;
		paint.setTextSize(size);
		paint.setColor(menuTextColor != -1 ? menuTextColor : Color.BLACK);
		
		if (isOptimizeText){
			for (int i = 0, length = txts.length; i < length; i++){
				String str = txts[i];
				
				if (null == str){
					str = "";
				} else if (str.length() > menuMaxStrLength){
					// ���ַ������Ƚ��п���
					str = new StringBuilder()
						  .append(str.substring(0, menuMaxStrLength))
						  .append("����").toString();
				}
				
				txts[i] = str;
				paint.getTextBounds(str, 0, str.length(), bounds);
				compareDimension(point, bounds.width(), bounds.height());
			}
		} else{
			for (int i = 0, length = txts.length; i < length; i++){
				String str = txts[i];
				
				if (null == str){
					str = "";
				}
				
				txts[i] = str;
				paint.getTextBounds(str, 0, str.length(), bounds);
				compareDimension(point, bounds.width(), bounds.height());
			}
		}
		
		return point;
	}
	
	/**
	 * �Ƚϲ��ı����ߴ�
	 * @param point �������ߴ�Ķ���
	 * @param width ��
	 * @param height ��
	 */
	private void compareDimension(Point point, int width, int height){
		if (point.x < width){
			point.x = width;
		}
		
		if (point.y < height){
			point.y = height;
		}
	}
	
	/**
	 * ���ز˵�
	 * @return ���سɹ�����true��ʧ�ܷ���false
	 */
	public boolean hide(){
		if (null != popupWindow && popupWindow.isShowing()){
			popupWindow.dismiss();
			popupWindow = null;
			
			if (null != menuItemListener){
				menuItemListener.hideMenu();
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * ���˵����
	 */
	public void dismiss(){
		menuMenuItems.clear();
		menuGridView = null;
		menuTexts = new String[0];
		menuImageRes = new int[0];
		menuWidth = 0;
		menuHeight = 0;
	}
	
	/**
	 * �ͷ�PopupMenu��Դ
	 */
	public void releasePopupMenuView(){
		this.dismiss();
		this.hide();
	}
	
	/**
	 * ���ò˵��ѡ�м�����
	 * @param listener
	 */
	public void setOnMenuItemClickListener(OnMenuItemClickListener listener){
		menuItemListener = listener;
	}
	
	/**
	 * �˵���Ŀѡ�м�����
	 */
	public interface OnMenuItemClickListener{
		/**
		 * �˵��������õķ���
		 * @param parent
		 * @param view
		 * @param position
		 */
		public void onMenuItemClick(AdapterView<?> parent, View view, int position);
		
		/**
		 * �˵����ص��õķ���
		 */
		public void hideMenu();
	}
}
