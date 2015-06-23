package com.dzyjy.nfc.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import com.dzyjy.nfc.R;
import com.dzyjy.nfc.adapter.MsgListAdapter;
import com.dzyjy.nfc.model.MsgInfo;

public class FragmentPageMsg extends Fragment {
	public static final String TAG = "FragmentPageMsg";
	private boolean isLoad;
	private ListView listView;

	private ReceiveBroadCast receiveBroadCast;
	
	private MsgListAdapter adapter;

	@Override
	public void onAttach(Activity activity) {
		receiveBroadCast = new ReceiveBroadCast();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("com.gasFragment");
		activity.registerReceiver(receiveBroadCast, intentFilter);
		super.onAttach(activity);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new MsgListAdapter(getActivity());
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "Msg:onCreateView");
		return inflater.inflate(R.layout.fragment_msg, null);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, "Msg：onActivityCreated");
		if (isLoad == false) {
			
			isLoad = true;
			listView = (ListView) getView().findViewById(R.id.msg_list_view);
			listView.setAdapter(adapter);
			adapter.setDatas(getDatas());
			adapter.notifyDataSetChanged();
		}
		super.onActivityCreated(savedInstanceState);
	}
	private List<MsgInfo> getDatas() {
		List<MsgInfo> datas = new ArrayList<MsgInfo>();
		MsgInfo info1 = new MsgInfo();
		info1.setNfcID("11111111111111");
		info1.setNfcType("使用过的锁");
		info1.setNfcChangeTime("20分钟前");
		info1.setNfcOption("解锁");
		datas.add(info1);
		MsgInfo info2 = new MsgInfo();
		info2.setNfcID("22222222222222");
		info2.setNfcType("未使用过的锁");
		info2.setNfcChangeTime("昨天11:32");
		info2.setNfcOption("分配给用户");
		datas.add(info2);
		MsgInfo info3 = new MsgInfo();
		info3.setNfcID("33333333333333");
		info3.setNfcType("以上锁的锁");
		info3.setNfcChangeTime("2015-04-06 15:08");
		info3.setNfcOption("上锁");
		datas.add(info3);

		return datas;
	}

	class ReceiveBroadCast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			//收到消息 先去数据库
			Log.i(TAG, "收到消息");
		}
	}

	@Override
	public void onDestroy() {
		getActivity().unregisterReceiver(receiveBroadCast);
		super.onDestroy();
	}
}