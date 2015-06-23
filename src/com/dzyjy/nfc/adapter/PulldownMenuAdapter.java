package com.dzyjy.nfc.adapter;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dzyjy.nfc.view.PulldownMenuItem;

/**
 * ��������ѡ��˵�����������
 * @Description: ��������ѡ��˵�����������

 * @File: PopupMenuAdapter.java

 * @Package com.navigation.adapter

 * @Author Hanyonglu

 * @Date 2012-7-29 ����09:51:12

 * @Version V1.0
 */
public class PulldownMenuAdapter extends BaseAdapter{
	private ArrayList<PulldownMenuItem> menuItems = null;
	
	public PulldownMenuAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	public PulldownMenuAdapter(ArrayList<PulldownMenuItem> menuItems){
		this.menuItems = menuItems;
	}
	
	@Override
	public int getCount(){
		return menuItems.size();
	}

	@Override
	public Object getItem(int position){
		return position;
	}

	@Override
	public long getItemId(int position){
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		/**
		 * ���ڴ˴�Item�ڿɼ���Ļ�й��ã����Բ��ػ��档
		 */
		if (null == convertView){
			convertView = menuItems.get(position).getView();
		}
		
		return convertView;
	}
}
