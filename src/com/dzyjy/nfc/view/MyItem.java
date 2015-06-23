package com.dzyjy.nfc.view;
/**
 * 选项卡  “我的” 的item 
 */
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dzyjy.nfc.R;

public class MyItem extends RelativeLayout{
	private final String TAG = "MyItem";
	private Context myContext;
	private TextView titleText;
	private TextView typeText;
	private TextView promptText;
	private TextView permissionsText;
	private RelativeLayout relativeLayout;
	public MyItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.myContext = context;
		LayoutInflater.from(context).inflate(R.layout.fragment_my_item, this);
		titleText = (TextView) findViewById(R.id.title);
		promptText = (TextView) findViewById(R.id.prompt);
		typeText = (TextView) findViewById(R.id.type);
		permissionsText = (TextView) findViewById(R.id.permissions);
		relativeLayout = (RelativeLayout) findViewById(R.id.my_item);
	}
	
	public void setPromptText(String text){
		promptText.setText(text);
	}
	public void setTitleText(String text){
		titleText.setText(text);
	}
	public void setTypeText(String text){
		typeText.setText(text);
	}
	public void setOnItemClickListemer(OnClickListener l){
		relativeLayout.setOnClickListener(l);
	}

	public void setPermissionsText(String string) {
		// TODO Auto-generated method stub
		permissionsText.setText(string);
	}
	public void setPermissionsVisibility(int b){
		permissionsText.setVisibility(b);
	}
}
