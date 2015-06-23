package com.dzyjy.nfc.view;

import com.dzyjy.nfc.util.AllUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 
 * �������
 * @author ldp
 *
 */
public class DashedLine extends View {
    private final String namespace = "http://com.smartMap.driverbook";
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private Rect mRect;
  private Context mContext;
    public DashedLine(Context context, AttributeSet attrs) {
    	
        super(context, attrs);        
        this.mContext = context;
        
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);        
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.DKGRAY);
        Path path = new Path();     
        path.moveTo(0, 0);//������һ�������Ŀ�ʼ��(x,y)��
        path.lineTo(0,AllUtil.getWindowHeight(mContext));   //   ���һ�д����һ�㵽ָ����(x,y)
        PathEffect effects = new DashPathEffect(new float[]{5,5,5,5},1);
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);
    }
}
