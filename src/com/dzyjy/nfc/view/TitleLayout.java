package com.dzyjy.nfc.view;
/**
 * 标题栏
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dzyjy.nfc.R;

public class TitleLayout extends RelativeLayout {
	private ImageView titleBack;
	private ImageView titleSubmit;
	private TextView titleName;
	
	private ImageView titleImageViewTopic;

	private Context context1;
	private RelativeLayout titleRl;
	public TitleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context1 = context;
		View view = LayoutInflater.from(context).inflate(R.layout.title, this);
		titleBack = (ImageView) findViewById(R.id.back);
		titleSubmit = (ImageView) findViewById(R.id.submit);
		titleName = (TextView)view. findViewById(R.id.title_name);
		titleImageViewTopic = (ImageView) view. findViewById(R.id.title_imageViewTopic);
		titleRl = (RelativeLayout) view.findViewById(R.id.title_rl);
		titleBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Activity) getContext()).finish();
			}
		});
		titleSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(context1, "提交", Toast.LENGTH_SHORT).show();
			}
		});
	}
	public void setTitleSubmitVisibility(int b){
		titleSubmit.setVisibility(b);
	}

	public void setTitleNameText(String text) {
		titleName.setText(text);
	}
	public String getTitleNameText(){
		return titleName.getText().toString();
	}
	public void setTitleImageViewSrc(Drawable data){
		titleImageViewTopic.setImageDrawable(data);
	}
	public void setTitleImageResource(int rescource){
		titleImageViewTopic.setImageResource(rescource);
	}
	public void setBackButtonListener(OnClickListener l) {
		titleBack.setOnClickListener(l);
	}

	public void setSubmitButtonListener(OnClickListener l) {
		titleSubmit.setOnClickListener(l);
	}
	public void setImageVisable(int gone) {
		titleImageViewTopic.setVisibility(gone);		
	}
	public void setTitleRlOnClickListener(OnClickListener l){
		titleRl.setOnClickListener(l);
	}
	public void setTitleRlClickable(boolean b) {
		titleRl.setClickable(b);
	}
}
