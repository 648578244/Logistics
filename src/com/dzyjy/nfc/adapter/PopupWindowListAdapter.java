package com.dzyjy.nfc.adapter;

import java.util.List;

import com.dzyjy.nfc.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PopupWindowListAdapter extends BaseAdapter{
	private List<String> datas;
	private Context context;
	public PopupWindowListAdapter(Context context,List<String> datas){
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
		View view;
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			view = View.inflate(context, R.layout.popwindow_item, null);
			holder.text = (TextView) view.findViewById(R.id.popupwindow_text);
			view.setTag(holder);
		}else{
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.text.setText(datas.get(position).toString());
		return view;
	}
	private class ViewHolder{
		TextView text;
	}
}
