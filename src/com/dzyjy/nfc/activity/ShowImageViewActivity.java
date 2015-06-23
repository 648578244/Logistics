package com.dzyjy.nfc.activity;

import java.io.File;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.dzyjy.nfc.R;
import com.dzyjy.nfc.util.AllUtil;
import com.dzyjy.nfc.util.BitmapHelp;
import com.dzyjy.nfc.view.TitleLayout;
import com.lidroid.xutils.BitmapUtils;

public class ShowImageViewActivity extends BaseActivity{
	private BitmapUtils bitmapUtils;
	private String curPath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.show_imageview_activity);
		bitmapUtils = BitmapHelp.getBitmapUtils(this);
		ImageView view =(ImageView) findViewById(R.id.show_imageview);
		TitleLayout titleLayout = (TitleLayout) findViewById(R.id.photo_show);
		titleLayout.setTitleNameText("图片预览");
		curPath = getIntent().getStringExtra("curPhontPath");
		bitmapUtils.display(view, curPath);
		view.setOnClickListener(new ImageOnClick());
	}
	class ImageOnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			getPopupWindow();
			// 这里是位置显示方式,在按钮的左下角
//			popupWindow.showAsDropDown(v);
//			popupWindow.showAsDropDown(v, (screenWidth-dialgoWidth)/2, 0);
			popupWindow.showAtLocation(v,
					 Gravity.BOTTOM, 0, 0);
		}
		
	}
	private void deleteFile(){
		AllUtil.deleteFile(new File(curPath));
		Intent intent = new Intent();
		intent.putExtra("curPath", curPath);
		setResult(10, intent);
		ShowImageViewActivity.this.finish();
	}
	// 声明PopupWindow对象的引用
		private PopupWindow popupWindow;
	/***
	 * 获取PopupWindow实例
	 */
	private void getPopupWindow() {

		if (null != popupWindow) {
			popupWindow.dismiss();
			return;
		} else {
			initPopuptWindow();
		}
	}
	
	/**
	 * 创建PopupWindow
	 */
	protected void initPopuptWindow() {
		// TODO Auto-generated method stub

		// 获取自定义布局文件pop.xml的视图
		View popupWindow_view = getLayoutInflater().inflate(R.layout.pop, null,
				false);
		// 创建PopupWindow实例,200,150分别是宽度和高度
		popupWindow = new PopupWindow(popupWindow_view, LayoutParams.MATCH_PARENT, 150, true);
		// 设置动画效果
		popupWindow.setAnimationStyle(R.style.AnimationFade);
		//点击其他地方消失		
		popupWindow_view.setOnTouchListener(new OnTouchListener() {			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
					}				
				return false;
			}
		});		
		// pop.xml视图里面的控件
		Button delete = (Button) popupWindow_view.findViewById(R.id.delete);
		// pop.xml视图里面的控件触发的事件
		// 打开
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteFile();
				// 对话框消失
				popupWindow.dismiss();
			}
		});

	}
}
