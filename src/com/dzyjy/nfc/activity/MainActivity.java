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

	// 定义FragmentTabHost对象
	private FragmentTabHost mTabHost;

	// 定义一个布局
	private LayoutInflater layoutInflater;

	// 定义数组来存放Fragment界面
	private Class fragmentArray[] = { Fragment.class,
			Fragment.class, Fragment.class,Fragment.class };

	// 定义数组来存放按钮图片
	private int mImageViewArray[] = { R.drawable.tab_home_btn,
			R.drawable.tab_message_btn, R.drawable.tab_message_btn,
			R.drawable.tab_selfinfo_btn };

	// Tab选项卡的文字
	private String mTextviewArray[] = { "操作", "查看", "消息", "我的" };

	private List<Fragment> list = new ArrayList<Fragment>();

	private TitleLayout mainTitle;

	private int height = 0;

	private String curTitle = "全部";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);
		// 获得广播管理实例
		initView();
		initPager();
	}

	/**
	 * 初始化组件
	 */
	private void initView() {
		vp = (ViewPager) findViewById(R.id.pager);
		mainTitle = (TitleLayout) findViewById(R.id.main_title);
		mainTitle.setTitleNameText(mTextviewArray[0]);

		// 实例化布局对象
		layoutInflater = LayoutInflater.from(this);

		// 实例化TabHost对象，得到TabHost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.pager);
		mTabHost.getTabWidget().setDividerDrawable(null);
		mTabHost.setOnTabChangedListener(new TabHostListener());
		// 得到fragment的个数
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i])
					.setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			// 设置Tab按钮的背景
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
		vp.setAdapter(new MainViewPageAdapter(getSupportFragmentManager(), list));// 自定义Fragment适配器
		vp.setOnPageChangeListener(new ViewPagerListener());
		vp.setOffscreenPageLimit(3);// 缓存的页面个数
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
			 * 设置选项卡对应页面的位置
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
	 * 给Tab按钮设置图标和文字
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
	 * 显示PulldownMenuView
	 */
	protected void showPulldownMenu() {
		pullDownMenu = menuUtility.getPulldownMenuView((String) mainTitle
				.getTitleNameText());
		mainTitle.setTitleImageResource(R.drawable.ic_menu_trangle_up);
	}

	/**
	 * 隐藏PulldownMenuView
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
					// 开始显示下拉菜单
					showPulldownMenu();

					pullDownMenu
							.setOnMenuItemClickListener(new OnMenuItemClickListener() {
								@Override
								public void onMenuItemClick(
										AdapterView<?> parent, View view,
										int position) {
									curTitle = ConstantCategoryMenu.newsMenuTexts[position];
									mainTitle.setTitleNameText(curTitle);
									// 发出一条广播 刷新页面
									Intent intent = new Intent(); // Itent就是我们要发送的内容
									intent.setAction("com.gasFragment"); // 设置你这个广播的action
									sendBroadcast(intent); // 发送广播
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

	// PulldownMenuView基本操作类
	private MenuUtility menuUtility = null;
	// PulldownMenuView对象
	private PulldownMenuView pullDownMenu = null;
}
