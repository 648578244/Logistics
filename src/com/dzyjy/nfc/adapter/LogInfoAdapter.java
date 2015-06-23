package com.dzyjy.nfc.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dzyjy.nfc.R;
import com.dzyjy.nfc.model.LogInfo;

public class LogInfoAdapter extends BaseAdapter{
	private Context context;
	private List<LogInfo> datas;
	public LogInfoAdapter(Context context,List<LogInfo> datas){
		this.context = context;
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
		ViewHolder holder = null;
		View view;
		LogInfo info = datas.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			view = View.inflate(context, R.layout.loginfo_item, null);
			holder.logSendTime = (TextView) view.findViewById(R.id.log_send_time);
			holder.logSendUnit = (TextView) view.findViewById(R.id.log_send_unit);
			holder.logSendAddress = (TextView) view.findViewById(R.id.log_send_address);
			holder.logSendMan = (TextView) view.findViewById(R.id.log_send_man);
			holder.shape = (ImageView) view.findViewById(R.id.log_img_node);
			if (!info.isNowItem()) {
				holder.shape.setBackground(context.getResources().getDrawable(R.drawable.my_shape_gray));
			}
			view.setTag(holder);
		}else{
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.logSendTime.setText(info.getSendTime());
		holder.logSendUnit.setText(info.getSendUnit());
		holder.logSendAddress.setText(info.getSendAddress());
		holder.logSendMan.setText(info.getSendMan());
		
		
		return view;
	}
	private  class ViewHolder{
		TextView logSendTime;
		TextView logSendUnit;
		TextView logSendAddress;
		TextView logSendMan;
		
		ImageView shape;
	}
}
