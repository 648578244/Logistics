package com.dzyjy.nfc.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.dzyjy.nfc.R;
import com.dzyjy.nfc.util.ActivityCollector;
import com.dzyjy.nfc.view.MyItem;

public class UserInfoActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_info_activity);
		MyItem userAddress = (MyItem) findViewById(R.id.user_address);
		MyItem userName = (MyItem) findViewById(R.id.user_name);
		MyItem userRealName = (MyItem) findViewById(R.id.user_real_name);
		MyItem userPhone = (MyItem) findViewById(R.id.user_phone);
		userName.setTitleText("�û���:");
		userName.setTypeText("admin");
		
		userRealName.setTitleText("����:");
		userRealName.setTypeText("user");
		
		userPhone.setTitleText("�ֻ���:");
		userPhone.setTypeText("14565436789");
		
		userAddress.setTitleText("��ַ:");
		userAddress.setTypeText("App�о�Ժ");
	}
	public void loginOut(View v){
	SharedPreferences	sp = this.getSharedPreferences("loginInfo", MODE_PRIVATE);
	Editor editor = sp.edit();
	editor.putBoolean("isLogin", false);
	editor.putString("password", "");
	editor.commit();
	ActivityCollector.finishAll();
	Intent intent = new Intent(this,LoginActivity.class);
	startActivity(intent);
	}
}
