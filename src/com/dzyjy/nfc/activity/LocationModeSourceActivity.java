package com.dzyjy.nfc.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.view.Window;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.dzyjy.nfc.R;

/**
 * AMapV2地图中介绍定位三种模式的使用，包括定位，追随，旋转
 */
public class LocationModeSourceActivity extends Activity implements LocationSource,
		AMapLocationListener, OnMarkerClickListener{
	private static final String TAG ="LocationModeSourceActivity";
	private AMap aMap;
	private MapView mapView;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;
	private Marker marker2;// 有跳动效果的marker对象
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.log_mapinfo_view);
        /*
         * 设置离线地图存储目录，在下载离线地图或初始化地图设置;
         * 使用过程中可自行设置, 若自行设置了离线地图存储的路径，
         * 则需要在离线地图下载和使用地图页面都进行路径设置
         * */
	    //Demo中为了其他界面可以使用下载的离线地图，使用默认位置存储，屏蔽了自定义设置
//        MapsInitializer.sdcardDir =OffLineMapUtils.getSdCacheDir(this);
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
			
		}
	}

	/**
	 * 设置一些amap的属性
	 */
	private void setUpMap() {
		
		
		aMap.setLocationSource(this);// 设置定位监听
		aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
		aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
		//设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种 
		aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
		
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	/**
	 * 此方法已经废弃
	 */
	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
	private LatLng latlng2 = new LatLng(28.0052, 120.6976);
	private LatLng latlng3 = new LatLng(28.0062, 120.6996);
	private LatLng latlng4 = new LatLng(28.0072, 120.6986);
	/**
	 * 定位成功后回调函数
	 */
	private boolean isNewLatLng = true;
	@Override
	public void onLocationChanged(AMapLocation aLocation) {
		if (mListener != null && aLocation != null) {
			mListener.onLocationChanged(aLocation);// 显示系统小蓝点
			double lat = aLocation.getLatitude();
			double lng= aLocation.getLongitude();
			if (isNewLatLng) {
				LatLng latLng = new LatLng(lat, lng);
				aMap.addNavigateArrow(new NavigateArrowOptions().add(latlng4,
						latlng2, latlng3, latLng).width(20));
				aMap.moveCamera(CameraUpdateFactory
						.newCameraPosition(new CameraPosition(latLng, 16f, 38.5f, 300)));
				isNewLatLng = false;
				
				addMarkersToMap(latLng);
			}
		}
	}

	private void addMarkersToMap(LatLng latLng) {
		
		MarkerOptions markerOption2 = new MarkerOptions();
		markerOption2.position(latlng2);
		markerOption2.title("温州市").snippet("温州市：28.0052, 120.6976");
		markerOption2.perspective(true);
		markerOption2.draggable(true);
		markerOption2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
		
		MarkerOptions markerOption3 = new MarkerOptions();
		markerOption3.position(latlng3);
		markerOption3.title("温州市").snippet("温州市：28.0062, 120.6996");
		markerOption3.perspective(true);
		markerOption3.draggable(true);
		markerOption3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
		
		MarkerOptions markerOption4 = new MarkerOptions();
		markerOption4.position(latlng4);
		markerOption4.title("温州市").snippet("温州市：28.0072, 120.6986");
		markerOption4.perspective(true);
		markerOption4.draggable(true);
		markerOption4.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
		
		ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
		giflist.add(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
		giflist.add(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_RED));
		giflist.add(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

		MarkerOptions markerOption1 = new MarkerOptions().anchor(0.5f, 0.5f)
				.position(latLng).title("温州市")
				.snippet("温州市:"+latLng.latitude+","+latLng.longitude)
				.perspective(true).draggable(true).period(50).icons(giflist);
			ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();
		markerOptionlst.add(markerOption1);
		markerOptionlst.add(markerOption2);
		markerOptionlst.add(markerOption3);
		markerOptionlst.add(markerOption4);
		List<Marker> markerlst = aMap.addMarkers(markerOptionlst, true);
		marker2 = markerlst.get(0);
	}

	/**
	 * 激活定位
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			/*
			 * mAMapLocManager.setGpsEnable(false);
			 * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
			 * API定位采用GPS和网络混合定位方式
			 * ，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
			 */
			mAMapLocationManager.requestLocationUpdates(
					LocationProviderProxy.AMapNetwork, 2000, 10, this);
		}
	}

	/**
	 * 停止定位
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destory();
		}
		mAMapLocationManager = null;
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
