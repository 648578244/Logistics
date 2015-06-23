package com.dzyjy.nfc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.dzyjy.nfc.R;
import com.dzyjy.nfc.activity.UserInfoActivity;
import com.dzyjy.nfc.view.MyItem;

public class FragmentPageMy extends Fragment{
	private final  String TAG = "FragmentPageMy";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		View view = inflater.inflate(R.layout.fragment_my, null);
		MyItem itemInfo = (MyItem) view.findViewById(R.id.my_info);
		MyItem itemLanguage = (MyItem) view.findViewById(R.id.my_language);
		MyItem itemPassword = (MyItem) view.findViewById(R.id.my_password);
		MyItem itemVersion = (MyItem) view.findViewById(R.id.my_vesion);
		MyItem itemOption = (MyItem) view.findViewById(R.id.my_option);
		MyItem itemAbout = (MyItem) view.findViewById(R.id.my_about);
		itemInfo.setTitleText("13634253083");
		itemInfo.setPermissionsText("��ͨ�û�");
		itemInfo.setPermissionsVisibility(View.VISIBLE);
		itemLanguage.setTitleText("����");
		itemLanguage.setTypeText("����");
		itemPassword.setTitleText("�޸�����");
		itemVersion.setTitleText("�汾����");
		itemVersion.setPromptText("�Ѿ������°汾");
		itemVersion.setTypeText("2.0.0");
		itemOption.setTitleText("�������");
		itemAbout.setTitleText("��������");
		Log.i(TAG, "12312");
		itemInfo.setOnItemClickListemer(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FragmentPageMy.this.getActivity(),UserInfoActivity.class);
				startActivity(intent);
			}
		});
		itemAbout.setOnItemClickListemer(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i(TAG, "����˹�������");
			}
		});
		itemLanguage.setOnItemClickListemer(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "���������");
			}
		});
		return view;		
	}	
}