package com.dzyjy.nfc.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Environment;
import android.view.WindowManager;

public class AllUtil {
	public static List<String> ListFile() {

		File file = new File(Environment.getExternalStorageDirectory()
				+ "/rectPhoto/");
		File[] f = file.listFiles();
		 List<String> datas = new  ArrayList<String>();
//		String Path[] = new String[f.length];
		for (int i = 0; i < f.length; i++) {

//			Path[i] = f[i].getPath();
			datas.add( f[i].getPath());
		}

		return datas;

	}

	public static Bitmap prassImage(String path, int windowWidth,
			int windowHeight) {
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options opts = new Options();
			opts.inJustDecodeBounds = true;// ��ȥ��Ľ���ͼƬ����ȡͼƬ��ͷ����Ϣ
			BitmapFactory.decodeFile(path, opts);
			int imageHeight = opts.outHeight;
			int imageWidth = opts.outWidth;
			// �������ű���
			int scaleX = imageWidth / windowWidth;
			int scaleY = imageHeight / windowHeight;
			int scale = 1;
			if (scaleX > scaleY & scaleY >= -1) {
				scale = scaleX;
			}
			if (scaleY > scaleX & scaleX >= -1) {
				scale = scaleY;
			}
			// ��Ľ���ͼƬ
			opts.inJustDecodeBounds = false;
			opts.inSampleSize = scale;
			bitmap = BitmapFactory.decodeFile(path, opts);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
	}

	public static String md5(String str) {
		byte[] digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			digest = md.digest(str.getBytes());
			return bytes2hex02(digest);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String bytes2hex02(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		String tmp = null;
		for (byte b : bytes) {
			// ��ÿ���ֽ���0xFF���������㣬Ȼ��ת��Ϊ10���ƣ�Ȼ�������Integer��ת��Ϊ16����
			tmp = Integer.toHexString(0xFF & b);
			if (tmp.length() == 1)// ÿ���ֽ�8Ϊ��תΪ16���Ʊ�־��2��16����λ
			{
				tmp = "0" + tmp;
			}
			sb.append(tmp);
		}

		return sb.toString();

	}

	public static int getWindowWidth(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getWidth();
	}

	public static int getWindowHeight(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getHeight();
	}

	public static void deleteDir() {
		File dir = new File(Environment.getExternalStorageDirectory()
				+ "/rectPhoto/");
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;

		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); // ɾ�������ļ�
			else if (file.isDirectory())
				deleteDir(); // �ݹ�ķ�ʽɾ���ļ���
		}
		dir.delete();// ɾ��Ŀ¼����
	}

	public static Bitmap addUserInfo(Bitmap bitmap2) {//��ˮӡ
		SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		String date = df.format(new Date());
		Bitmap newb = null;
		newb = Bitmap.createBitmap(bitmap2.getWidth(), bitmap2.getHeight(),
				Config.RGB_565);
		Canvas canvas = new Canvas(newb);
		canvas.drawColor(Color.TRANSPARENT);
		Paint paint = new Paint();
		Typeface font = Typeface.create("����", Typeface.BOLD);
		paint.setColor(Color.RED);
		paint.setTypeface(font);
		paint.setTextSize((bitmap2.getWidth() * 10) / 200);
		canvas.drawBitmap(bitmap2, 0, 0, paint);
		canvas.drawText(date, 0, (bitmap2.getWidth() * 10) / 100, paint);
		canvas.drawText("�ֵ���", 0, bitmap2.getHeight() - 50, paint);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();
		return newb;
	}

	private static String sdState = Environment.getExternalStorageState();

	public static void deleteFile(File file) {
		if (sdState.equals(Environment.MEDIA_MOUNTED)) {
			if (file.exists()) {
				if (file.isFile()) {
					file.delete();
				}
				// �������һ��Ŀ¼
				else if (file.isDirectory()) {
					// ����Ŀ¼�����е��ļ� files[];
					File files[] = file.listFiles();
					for (int i = 0; i < files.length; i++) { // ����Ŀ¼�����е��ļ�
						deleteFile(files[i]); // ��ÿ���ļ� ������������е���
					}
				}
				file.delete();
			}
		}
	}

	public   static   String   inputStream2String(InputStream   is)   throws   IOException{
        ByteArrayOutputStream   baos   =   new   ByteArrayOutputStream();
        int   i=-1;
        while((i=is.read())!=-1){
        baos.write(i);
        }
       return   baos.toString();
} 

}
