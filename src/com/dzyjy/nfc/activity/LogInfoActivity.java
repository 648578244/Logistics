package com.dzyjy.nfc.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dzyjy.nfc.R;
import com.dzyjy.nfc.adapter.LogInfoAdapter;
import com.dzyjy.nfc.model.LogInfo;

public class LogInfoActivity extends BaseActivity implements OnItemClickListener{
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loginfo_activity);
		listView = (ListView) findViewById(R.id.log_list_view);
		listView.setAdapter(new LogInfoAdapter(this,getDatas()));
		listView.setOnItemClickListener(this);
	}
	private List<LogInfo> getDatas() {
		List<LogInfo> datas = new ArrayList<LogInfo>();
			LogInfo info1 = new LogInfo();
			info1.setSendAddress("¹������óְҵ����ѧԺ"+1);
			info1.setSendMan("������Ա"+1);
			info1.setSendTime("2015-03-24 16:19");
			info1.setSendUnit("������Ϣ�о�Ժ"+1);
			info1.setNowItem(true);
			
			LogInfo info2 = new LogInfo();
			info2.setSendAddress("¹������óְҵ����ѧԺ"+2);
			info2.setSendMan("������Ա"+2);
			info2.setSendTime("2015-03-24 16:19");
			info2.setSendUnit("������Ϣ�о�Ժ"+2);
			info2.setNowItem(false);
			
			
			LogInfo info3 = new LogInfo();
			info3.setSendAddress("¹������óְҵ����ѧԺ"+3);
			info3.setSendMan("������Ա"+3);
			info3.setSendTime("2015-03-24 16:19");
			info3.setSendUnit("������Ϣ�о�Ժ"+3);
			info3.setNowItem(false);
			datas.add(info1);
			datas.add(info2);
			datas.add(info3);
		return datas;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this,LocationModeSourceActivity.class);
		startActivity(intent);
	}
}
