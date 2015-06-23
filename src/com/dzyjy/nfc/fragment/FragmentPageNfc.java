package com.dzyjy.nfc.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dzyjy.nfc.R;
import com.dzyjy.nfc.activity.LogInfoActivity;
import com.dzyjy.nfc.adapter.MsgListAdapter;
import com.dzyjy.nfc.model.MsgInfo;

public class FragmentPageNfc extends Fragment implements OnItemClickListener {
	// private ListView listView;
	MsgListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_nfc, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ListView listView = (ListView) getView().findViewById(
				R.id.msg_list_view);
		listView.setAdapter(adapter);
		adapter.setDatas(getDatas());
		adapter.notifyDataSetChanged();
		listView.setOnItemClickListener(this);
	}

	private void init() {
		adapter = new MsgListAdapter(getActivity());
	}

	private List<MsgInfo> getDatas() {
		List<MsgInfo> datas = new ArrayList<MsgInfo>();
		MsgInfo info1 = new MsgInfo();
		info1.setNfcID("11111111111111");
		datas.add(info1);
		MsgInfo info2 = new MsgInfo();
		info2.setNfcID("22222222222222");
		datas.add(info2);
		MsgInfo info3 = new MsgInfo();
		info3.setNfcID("33333333333333");
		datas.add(info3);
		MsgInfo info4 = new MsgInfo();
		info4.setNfcID("44444444444444");
		datas.add(info4);
		return datas;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this.getActivity(), LogInfoActivity.class);
		startActivity(intent);
	}
}