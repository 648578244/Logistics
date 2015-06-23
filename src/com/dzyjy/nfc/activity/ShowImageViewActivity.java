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
		titleLayout.setTitleNameText("ͼƬԤ��");
		curPath = getIntent().getStringExtra("curPhontPath");
		bitmapUtils.display(view, curPath);
		view.setOnClickListener(new ImageOnClick());
	}
	class ImageOnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			getPopupWindow();
			// ������λ����ʾ��ʽ,�ڰ�ť�����½�
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
	// ����PopupWindow���������
		private PopupWindow popupWindow;
	/***
	 * ��ȡPopupWindowʵ��
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
	 * ����PopupWindow
	 */
	protected void initPopuptWindow() {
		// TODO Auto-generated method stub

		// ��ȡ�Զ��岼���ļ�pop.xml����ͼ
		View popupWindow_view = getLayoutInflater().inflate(R.layout.pop, null,
				false);
		// ����PopupWindowʵ��,200,150�ֱ��ǿ�Ⱥ͸߶�
		popupWindow = new PopupWindow(popupWindow_view, LayoutParams.MATCH_PARENT, 150, true);
		// ���ö���Ч��
		popupWindow.setAnimationStyle(R.style.AnimationFade);
		//��������ط���ʧ		
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
		// pop.xml��ͼ����Ŀؼ�
		Button delete = (Button) popupWindow_view.findViewById(R.id.delete);
		// pop.xml��ͼ����Ŀؼ��������¼�
		// ��
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteFile();
				// �Ի�����ʧ
				popupWindow.dismiss();
			}
		});

	}
}
