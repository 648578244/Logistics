package com.dzyjy.nfc.activity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.dzyjy.nfc.R;
import com.dzyjy.nfc.util.AllUtil;
import com.dzyjy.nfc.view.TitleLayout;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class CameraActivity extends BaseActivity implements Callback, OnClickListener {

	private static final String TAG = "CameraActivity";
	private static final String POST_URL = "http://10.10.10.49:8081/web/uploadFile.do";
	private String savePath = Environment.getExternalStorageDirectory()
			+ "/rectPhoto/";
	private ImageView photoImage0;
	private ImageView photoImage1;
	private ImageView photoImage2;

	private int imageIndex = 0;
	private SurfaceView surfaceView;
	SurfaceHolder mSurfaceHolder;
	boolean isPreview;
	TitleLayout titleLayout;
	private AutoFocusCallback mAutoFocusCallback;
	List<String> newImages = new ArrayList<String>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.camera_activity);
		titleLayout = (TitleLayout) findViewById(R.id.camera_title);
		titleLayout.setTitleSubmitVisibility(View.VISIBLE);
		titleLayout.setTitleNameText("拍照");
		titleLayout.setSubmitButtonListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				upload(v);
			}
		});
		titleLayout.setBackButtonListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
				
			}
		});
		photoImage0 = (ImageView) findViewById(R.id.photo_image0);
		photoImage1 = (ImageView) findViewById(R.id.photo_image1);
		photoImage2 = (ImageView) findViewById(R.id.photo_image2);
		
		photoImage0.setEnabled(false);
		photoImage1.setEnabled(false);
		photoImage2.setEnabled(false);
		photoImage0.setTag(null);
		photoImage1.setTag(null);
		photoImage2.setTag(null);
		photoImage0.setOnClickListener(this);
		photoImage1.setOnClickListener(this);
		photoImage2.setOnClickListener(this);
		ImageButton callCameraButton = (ImageButton) findViewById(R.id.btn_callcamera);

		callCameraButton.setOnClickListener(new PhotoOnClickListener());

	}

	public void upload(View view) {
		int index = 0;
		showProgressDialog();
		Log.i(TAG,( photoImage0.getTag() == null)+"");
		if (photoImage0.getTag() == null) {
		} else {
			createNewImage(photoImage0);
			index++;
		}
		if (photoImage1.getTag() == null) {
		} else {
			createNewImage(photoImage1);
			index++;
		}
		if (photoImage2.getTag() == null) {
		} else {
			createNewImage(photoImage2);
			index++;
		}
		if (index < 3) {
			dismissProgressDialog();
			Toast.makeText(this, "需要上传三张照片", Toast.LENGTH_SHORT).show();
			return;
		}
		RequestParams params = new RequestParams();
		for (int i = 0; i < newImages.size(); i++) {
			File file = new File(newImages.get(i));
			params.addBodyParameter("filename" + i, file);
		}
		uploadFile(params);

	}

	private void createNewImage(ImageView imageview) {
		Bitmap bitmap = AllUtil.prassImage(imageview.getTag().toString(),
				AllUtil.getWindowWidth(this), AllUtil.getWindowHeight(this));
		Bitmap newB = AllUtil.addUserInfo(bitmap);// 加水印
		saveJpeg(newB, true, imageview);
	}

	private void uploadFile(RequestParams params) {
		HttpUtils http = new HttpUtils();

		http.send(HttpRequest.HttpMethod.POST, POST_URL, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Toast.makeText(CameraActivity.this, "失败", 1).show();
						
						dismissProgressDialog();
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						Toast.makeText(CameraActivity.this, "成功", 1).show();
					
							newImages.clear();
						initCameraView();
						dismissProgressDialog();
					}
				});
	}

	private void initCameraView() {
		AllUtil.deleteDir();
		showImageItem();// 刷新照片预览
		imageIndex = 0;

	}

	private void showImageItem() {
		Resources res = getResources();
		Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
		photoImage0.setImageBitmap(bmp);
		photoImage0.setEnabled(false);
		photoImage0.setTag(null);
		photoImage1.setImageBitmap(bmp);
		photoImage1.setEnabled(false);
		photoImage1.setTag(null);
		photoImage2.setImageBitmap(bmp);
		photoImage2.setEnabled(false);
		photoImage2.setTag(null);
	}

	private ProgressDialog dialog;

	private void showProgressDialog() {
		dialog = new ProgressDialog(this);
		dialog.setMessage("正在上传照片...");
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.show();
	}

	private void dismissProgressDialog() {
		dialog.dismiss();
	}

	@Override
	protected void onStart() {
		initSurfView();
		super.onStart();
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		Resources res = getResources();
		Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
		
		 if (resultCode == 10) {
			String path =  intent.getStringExtra("curPath");
			newImages.remove(path);
			if (photoImage0.getTag() != null ) {
				if (photoImage0.getTag().equals(path)) {
					photoImage0.setTag(null);
					photoImage0.setImageBitmap(bmp);
					photoImage0.setEnabled(false);
					imageIndex = 0;
				}
			}
			
			if (photoImage1.getTag() != null ) {
				if (photoImage1.getTag().equals(path)) {
					photoImage1.setTag(null);
					photoImage1.setImageBitmap(bmp);
					photoImage1.setEnabled(false);
					imageIndex = 1;
				}
			}
			
			
			if (photoImage2.getTag() != null ) {
				if (photoImage2.getTag().equals(path)) {
					photoImage2.setTag(null);
					photoImage2.setImageBitmap(bmp);
					photoImage2.setEnabled(false);
					imageIndex = 2;
				}
			}
		 }

	}

	private void initSurfView() {
		surfaceView = (SurfaceView) findViewById(R.id.surface_view);
		mSurfaceHolder = surfaceView.getHolder();
		mSurfaceHolder.addCallback(this);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mAutoFocusCallback = new AutoFocusCallback() {

			@Override
			public void onAutoFocus(boolean success, Camera camera) {
				if (success) {
					Log.i(TAG, "对焦成功");
				} else {

				}
			}
		};
	}

	private Camera mCamera = null;

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// initCamera();
		mCamera.autoFocus(new AutoFocusCallback() {
			@Override
			public void onAutoFocus(boolean success, Camera camera) {
				if (success) {
					initCamera();// 实现相机的参数初始化
					camera.cancelAutoFocus();// 只有加上了这一句，才会自动对焦。
				}
			}

		});
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (mCamera == null) {
			mCamera = Camera.open();
		}

		try {
			mCamera.setPreviewDisplay(mSurfaceHolder);
			initCamera();
		} catch (Exception e) {
			if (null != mCamera) {

				mCamera.release();
				mCamera = null;
			}
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
	}

	public void initCamera() {
		if (isPreview) {
			mCamera.stopPreview();
		}
		if (null != mCamera) {
			Camera.Parameters mParameters = mCamera.getParameters();

			mParameters.setPictureFormat(PixelFormat.JPEG);//

			mParameters.setPictureSize(1280, 960);
			mParameters.setPreviewSize(960, 720);
			mCamera.setDisplayOrientation(90);
			mParameters
					.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
			mCamera.setParameters(mParameters);
			mCamera.startPreview();
			// mCamera.autoFocus(mAutoFocusCallback);
			mCamera.cancelAutoFocus();
			isPreview = true;
		}
	}

	Bitmap mBitmap;
	PictureCallback mJpegCallback = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			if (null != data) {
				mBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
				mCamera.stopPreview();
				isPreview = false;
			}
			Matrix matrix = new Matrix();
			matrix.postRotate((float) 90.0);
			Bitmap rotabBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
					mBitmap.getWidth(), mBitmap.getHeight(), matrix, false);
			// 保存图片到sdcard

			if (null != rotabBitmap) {
				int index = imageIndex % 3;
				if (index == 0) {
					saveJpeg(rotabBitmap, false, photoImage0);
				} else if (index == 1) {
					saveJpeg(rotabBitmap, false, photoImage1);
				} else if (index == 2) {
					saveJpeg(rotabBitmap, false, photoImage2);
				}

				imageIndex++;
			}
			// 再次进入预览
			mCamera.startPreview();
			isPreview = true;
		}

	};
	ShutterCallback mShutterCallback = new ShutterCallback() {

		@Override
		public void onShutter() {
			// TODO Auto-generated method stub

		}
	};

	public class PhotoOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			if (isPreview && mCamera != null) {
				mCamera.takePicture(mShutterCallback, null, mJpegCallback);
				// if (imageIndex == 0) {
				// initTimerTask();
				// }

			}
		}

	}

	private void saveJpeg(Bitmap bm, boolean isNew, ImageView imageView) {
		File folder = new File(savePath);
		if (!folder.exists()) {// 如果文件夹不存在则创建
			folder.mkdir();
		}
		long dataTake = System.currentTimeMillis();
		String jpegName = savePath + dataTake + ".jpg";
		Log.i("Tag", "saveJpeg:jpegName--" + jpegName);
		try {
			FileOutputStream fout = new FileOutputStream(jpegName);
			BufferedOutputStream bos = new BufferedOutputStream(fout);
			bm.compress(Bitmap.CompressFormat.JPEG, 40, bos);
			bos.flush();
			bos.close();
			Log.i(TAG, "saveJpeg:存储完毕!");
			if (isNew) {
				newImages.add(jpegName);
			} else {
				showMyPhoto(jpegName, imageView);
			}
		} catch (Exception e) {
			Log.i("Tag", "saveJpeg:存储失败!");
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		mCamera.stopPreview();
		mCamera.release();
		initCameraView();
		mCamera = null;
	}

	private void showMyPhoto(String path, ImageView imageView) {
		BitmapUtils bitmapUtils = new BitmapUtils(this);
		imageView.setTag(path);
		imageView.setEnabled(true);
		bitmapUtils.display(imageView, path);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(CameraActivity.this, ShowImageViewActivity.class);
		intent.putExtra("curPhontPath", v.getTag().toString());
		startActivityForResult(intent, 100);		
	}

	// 定时任务////////////////////////////////////////////////////////////////////////
	// private final Timer timer = new Timer();
	// private TimerTask task;
	// Handler handler = new Handler() {
	// @Override
	// public void handleMessage(Message msg) {
	// // TODO Auto-generated method stub
	// initCameraView();
	// Toast.makeText(CameraActivity.this, "照片已过期，请重新拍照", 1).show();
	// super.handleMessage(msg);
	// }
	// };
	//
	// private void initTimerTask() {
	// task = new TimerTask() {
	// @Override
	// public void run() {
	// Message message = new Message();
	// message.what = 1;
	// handler.sendMessage(message);
	// }
	// };
	// timer.schedule(task, 30000);
	// }
}
