package com.dzyjy.nfc.constant;

import com.dzyjy.nfc.R;

/**
 * 菜单项图片资源和文字类
 * @Description: 菜单项图片资源和文字类

 * @File: ConstantCategoryMenu.java

 * @Package com.navigation.constant

 * @Author Hanyonglu

 * @Date 2012-7-30 下午05:12:56

 * @Version V1.0
 */
public class ConstantCategoryMenu {
	/**
	 * 新闻菜单项图片资源
	 */
	public final static int[] newsImageRes = {
	 R.drawable.ic_menu_house,R.drawable.ic_menu_toutiao,R.drawable.ic_menu_gn,
		R.drawable.ic_menu_gj
	};
	
	/**
	 * 新闻菜单项选中图片资源
	 */
	public final static int[] newsImageResPress = {
		R.drawable.ic_menu_house_press,R.drawable.ic_menu_toutiao_press,R.drawable.ic_menu_gn_press,
		R.drawable.ic_menu_gj_press
	};
	
	/**
	 * 新闻菜单项文字
	 */
	public final static String[] newsMenuTexts = {
		"全部","已使用","未使用","正在" +
				"用"
	};
	

}
