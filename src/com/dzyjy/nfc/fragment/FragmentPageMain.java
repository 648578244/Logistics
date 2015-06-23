package com.dzyjy.nfc.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dzyjy.nfc.R;
import com.dzyjy.nfc.activity.CameraActivity;
import com.dzyjy.nfc.adapter.PopupWindowListAdapter;

public class FragmentPageMain extends Fragment implements OnClickListener{
	private static final String TAG = "FragmentPageMain";
	private View view;
	private TextView btn;
	private  PopupWindow pop;
	private ListView mainListView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		view = inflater.inflate(R.layout.fragment_main, null);
		btn =	(TextView) view.findViewById(R.id.btn_nfc_version);
		
		Log.i(TAG, "onCreateView");
		initPopupWindow();
		btn.setOnClickListener(this);
		return view;		
	}	
	private void initPopupWindow() {
		WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);

		int windowWidth = wm.getDefaultDisplay().getWidth();
		int windowHeight = wm.getDefaultDisplay().getHeight();
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		// 引入窗口配置文件
		View view = inflater.inflate(R.layout.popwindow, null);
		mainListView = (ListView) view.findViewById(R.id.main_listview);
		List<String> list = new ArrayList<String>();
		list.add("上锁");
		list.add("检查");
		list.add("开锁");
		PopupWindowListAdapter adapter = new PopupWindowListAdapter(this.getActivity(), list);
		mainListView.setAdapter(adapter);
		mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(getActivity(),mainListView.getItemAtPosition(position).toString() , 1).show();
				actionStart();
			}
			
		});
		// 创建PopupWindow对象
		 pop = new PopupWindow(view, (int)(windowWidth/1.5f),
				LayoutParams.WRAP_CONTENT, false);
		// 需要设置一下此参数，点击外边可消失
		pop.setBackgroundDrawable(new BitmapDrawable());
		// 设置点击窗口外边窗口消失
		pop.setOutsideTouchable(true);	
		
		pop.setFocusable(true);
	}
	private void actionStart() {
		Intent intent = new Intent(getActivity(),CameraActivity.class);
		startActivity(intent);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_nfc_version:
//			actionStart();
			if (pop.isShowing()) {
				// 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
				pop.dismiss();
			} else {
				// 显示窗口
				pop.showAtLocation(v, Gravity.CENTER, 0, 0);
			}
			break;

		default:
			break;
		}
	}
}