package com.dzyjy.nfc.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dzyjy.nfc.R;
import com.dzyjy.nfc.model.MsgInfo;

public class MsgListAdapter extends BaseAdapter {
	private Context context;
	private List<MsgInfo> datas;

	public MsgListAdapter(Context context) {
		this.context = context;
		datas = new ArrayList<MsgInfo>();
	}

	public void setDatas(List<MsgInfo> datas) {
		this.datas = datas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		MsgInfo info = datas.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			view = View.inflate(context, R.layout.msginfo_item, null);
			holder.nfcChangeTime = (TextView) view
					.findViewById(R.id.msg_nfc_change_time);
			holder.nfcID = (TextView) view.findViewById(R.id.msg_nfc_id);
			holder.nfcType = (TextView) view.findViewById(R.id.msg_nfc_type);
			holder.nfcOption = (TextView) view.findViewById(R.id.msg_nfc_option);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.nfcChangeTime.setText(info.getNfcChangeTime());
		holder.nfcID.setText(info.getNfcID());
		holder.nfcType.setText(info.getNfcType());
		holder.nfcOption.setText(info.getNfcOption());
		return view;
	}

	private class ViewHolder {
		TextView nfcID;
		TextView nfcType;
		TextView nfcChangeTime;
		TextView nfcOption;
	}
}
