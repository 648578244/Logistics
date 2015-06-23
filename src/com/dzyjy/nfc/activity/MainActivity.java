package com.dzyjy.nfc.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.dzyjy.nfc.R;
import com.dzyjy.nfc.adapter.MainViewPageAdapter;
import com.dzyjy.nfc.constant.ConstantCategoryMenu;
import com.dzyjy.nfc.fragment.FragmentPageMain;
import com.dzyjy.nfc.fragment.FragmentPageMsg;
import com.dzyjy.nfc.fragment.FragmentPageMy;
import com.dzyjy.nfc.fragment.FragmentPageNfc;
import com.dzyjy.nfc.util.DeviceUtility;
import com.dzyjy.nfc.util.MenuUtility;
import com.dzyjy.nfc.view.PulldownMenuView;
import com.dzyjy.nfc.view.PulldownMenuView.OnMenuItemClickListener;
import com.dzyjy.nfc.view.TitleLayout;

public class MainActivity extends BaseActivity {
	private static final String TAG = "MainActivity";
	private ViewPager vp;

	// ����FragmentTabHost����
	private FragmentTabHost mTabHost;

	// ����һ������
	private LayoutInflater layoutInflater;

	// �������������Fragment����
	private Class fragmentArray[] = { Fragment.class,
			Fragment.class, Fragment.class,Fragment.class };

	// ������������Ű�ťͼƬ
	private int mImageViewArray[] = { R.drawable.tab_home_btn,
			R.drawable.tab_message_btn, R.drawable.tab_message_btn,
			R.drawable.tab_selfinfo_btn };

	// Tabѡ�������
	private String mTextviewArray[] = { "����", "�鿴", "��Ϣ", "�ҵ�" };

	private List<Fragment> list = new ArrayList<Fragment>();

	private TitleLayout mainTitle;

	private int height = 0;

	private String curTitle = "ȫ��";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);
		// ��ù㲥����ʵ��
		initView();
		initPager();
	}

	/**
	 * ��ʼ�����
	 */
	private void initView() {
		vp = (ViewPager) findViewById(R.id.pager);
		mainTitle = (TitleLayout) findViewById(R.id.main_title);
		mainTitle.setTitleNameText(mTextviewArray[0]);

		// ʵ�������ֶ���
		layoutInflater = LayoutInflater.from(this);

		// ʵ����TabHost���󣬵õ�TabHost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.pager);
		mTabHost.getTabWidget().setDividerDrawable(null);
		mTabHost.setOnTabChangedListener(new TabHostListener());
		// �õ�fragment�ĸ���
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			// Ϊÿһ��Tab��ť����ͼ�ꡢ���ֺ�����
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i])
					.setIndicator(getTabItemView(i));
			// ��Tab��ť��ӽ�Tabѡ���
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			// ����Tab��ť�ı���
//			mTabHost.getTabWidget().getChildAt(i)
//					.setBackgroundResource(R.drawable.selector_tab_background);
		}

		height = DeviceUtility.getScreenSize(this)[1]
				- mainTitle.getLayoutParams().height
				- mTabHost.getLayoutParams().height
				- DeviceUtility.getStatusBarHeight(this);
		menuUtility = new MenuUtility(MainActivity.this,
				ConstantCategoryMenu.newsImageRes,
				ConstantCategoryMenu.newsMenuTexts, height, mainTitle);
	}

	private void initPager() {
		FragmentPageMain p1 = new FragmentPageMain();
		FragmentPageNfc p2 = new FragmentPageNfc();
		FragmentPageMsg p3 = new FragmentPageMsg();
		FragmentPageMy p4 = new FragmentPageMy();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		vp.setAdapter(new MainViewPageAdapter(getSupportFragmentManager(), list));// �Զ���Fragment������
		vp.setOnPageChangeListener(new ViewPagerListener());
		vp.setOffscreenPageLimit(3);// �����ҳ�����
	}

	class ViewPagerListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			Log.i(TAG, "onPageScrollStateChanged");
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			Log.i(TAG, "onPageScrolled");
		}

		@Override
		public void onPageSelected(int index) {
			Log.i(TAG, "onPageSelected");
			/**
			 * ����ѡ���Ӧҳ���λ��
			 */
			showTitle(index);
			mTabHost.setCurrentTab(index);

		}
	}

	private class TabHostListener implements OnTabChangeListener {
		@Override
		public void onTabChanged(String tabId) {
			int position = mTabHost.getCurrentTab();
			vp.setCurrentItem(position);
		}
	}

	/**
	 * ��Tab��ť����ͼ�������
	 */
	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);

		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTextviewArray[index]);

		return view;
	}

	/**
	 * ��ʾPulldownMenuView
	 */
	protected void showPulldownMenu() {
		pullDownMenu = menuUtility.getPulldownMenuView((String) mainTitle
				.getTitleNameText());
		mainTitle.setTitleImageResource(R.drawable.ic_menu_trangle_up);
	}

	/**
	 * ����PulldownMenuView
	 */
	protected void hidePulldownMenu() {
		pullDownMenu.releasePopupMenuView();
		mainTitle.setTitleImageResource(R.drawable.ic_menu_trangle_down);
	}

	private void showTitle(int index) {
		if (index == 2) {
			mainTitle.setImageVisable(View.VISIBLE);
			mainTitle.setTitleNameText(curTitle);
			mainTitle.setTitleRlOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// ��ʼ��ʾ�����˵�
					showPulldownMenu();

					pullDownMenu
							.setOnMenuItemClickListener(new OnMenuItemClickListener() {
								@Override
								public void onMenuItemClick(
										AdapterView<?> parent, View view,
										int position) {
									curTitle = ConstantCategoryMenu.newsMenuTexts[position];
									mainTitle.setTitleNameText(curTitle);
									// ����һ���㲥 ˢ��ҳ��
									Intent intent = new Intent(); // Itent��������Ҫ���͵�����
									intent.setAction("com.gasFragment"); // ����������㲥��action
									sendBroadcast(intent); // ���͹㲥
								}

								@Override
								public void hideMenu() {
									hidePulldownMenu();
								}
							});

					pullDownMenu.show();
				}
			});
		} else {
			mainTitle.setTitleNameText(mTextviewArray[index]);
			mainTitle.setImageVisable(View.GONE);
			mainTitle.setTitleRlClickable(false);
		}
	}

	// PulldownMenuView����������
	private MenuUtility menuUtility = null;
	// PulldownMenuView����
	private PulldownMenuView pullDownMenu = null;
}
