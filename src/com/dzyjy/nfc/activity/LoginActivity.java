package com.dzyjy.nfc.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dzyjy.nfc.R;

public class LoginActivity extends BaseActivity implements OnClickListener{
	private CheckBox checkBox;
	private TextView loginUsername;
	private TextView loginPassword;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_activity);
		sp = this.getSharedPreferences("loginInfo", MODE_PRIVATE);
		if (sp.getBoolean("isLogin", false)) {
			actionStart(sp.getString("username", ""),sp.getString("password", ""));
		}
		initView();
		
	}
	private void initView() {
		checkBox = (CheckBox) findViewById(R.id.cb_remember);
		loginUsername = (TextView) findViewById(R.id.login_username);
		loginPassword = (TextView) findViewById(R.id.login_password);
		loginUsername.setText(sp.getString("username", ""));
		loginPassword.setText(sp.getString("password", ""));
		loginUsername.setOnClickListener(this);
	}
	public void loginViewClick(View v){
		Editor editor = sp.edit();
		String password = loginPassword.getText().toString();
		String username = loginUsername.getText().toString();
		switch (v.getId()) {
		case R.id.login_submit:
			if (checkBox.isChecked()) {
					editor.putString("password", password);
					editor.putString("username", username);
					editor.putBoolean("isLogin", true);
			}else{
				editor.putString("password", "");
				editor.putBoolean("isLogin", false);
			}
			editor.commit();
			actionStart(username,password);
			break;
			
		default:
			break;
		}
	}
	private void actionStart(String username,String password){
		Intent intent = new Intent(this,MainActivity.class);
		intent.putExtra("username", username);
		intent.putExtra("password", password);
		startActivity(intent);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
