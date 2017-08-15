package com.example.tinylove.View;

import com.example.tinylove.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

public class CustomImageView extends ImageView
{

	private Paint mPaint;

    private int mWidth;

    private int mHeight;

    private int mRadius;//Բ�뾶

    private RectF mRect;

    private int mRoundRadius;// Բ�Ǵ�С


    private BitmapShader mBitmapShader;//ͼ����Ⱦ

    private Matrix mMatrix;

    private int mType;// ��¼��Բ�λ���Բ�Ǿ���

    public static final int TYPE_CIRCLE = 0;// Բ��
    public static final int TYPE_ROUND = 1;// Բ�Ǿ���
    public static final int TYPE_OVAL = 2;//��Բ��
    public static final int DEFAUT_ROUND_RADIUS = 10;//Ĭ��Բ�Ǵ�С

    public CustomImageView(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mMatrix = new Matrix();
        mRoundRadius = DEFAUT_ROUND_RADIUS;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // ����ǻ���Բ�Σ���ǿ�ƿ�ߴ�Сһ��
        if (mType == TYPE_CIRCLE) {
            mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
            mRadius = mWidth / 2;
            setMeasuredDimension(mWidth, mWidth);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (null == getDrawable()) {
            return;
        }
        setBitmapShader();
        if (mType == TYPE_CIRCLE) {
            canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
        } else if (mType == TYPE_ROUND) {
            mPaint.setColor(Color.RED);
            canvas.drawRoundRect(mRect, mRoundRadius, mRoundRadius, mPaint);
        }else if(mType == TYPE_OVAL){
            canvas.drawOval(mRect, mPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        mRect = new RectF(0, 0, getWidth(), getHeight());
    }

    /**
     * ����BitmapShader
     */
    private void setBitmapShader() {
        Drawable drawable = getDrawable();
        if (null == drawable) {
            return;
        }
        Bitmap bitmap = drawableToBitmap(drawable);
        // ��bitmap��Ϊ��ɫ��������һ��BitmapShader
        mBitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        float scale = 1.0f;
        if (mType == TYPE_CIRCLE) {
            // �õ�bitmap���ߵ�Сֵ
            int bSize = Math.min(bitmap.getWidth(), bitmap.getHeight());
            scale = mWidth * 1.0f / bSize;

        } else if (mType == TYPE_ROUND || mType == TYPE_OVAL) {
            // ���ͼƬ�Ŀ���߸���view�Ŀ�߲�ƥ�䣬�������Ҫ���ŵı��������ź��ͼƬ�Ŀ�ߣ�һ��Ҫ��������view�Ŀ�ߣ�������������ȡ��ֵ��
            scale = Math.max(getWidth() * 1.0f / bitmap.getWidth(), getHeight() * 1.0f / bitmap.getHeight());
        }
        // shader�ı任��������������Ҫ���ڷŴ������С
        mMatrix.setScale(scale, scale);
        // ���ñ任����
        mBitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(mBitmapShader);

    }

    /**
     * drawableתbitmap
     * 
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            return bitmapDrawable.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }
    /**
     * ��λdpת��λpx
     */
    public int dpTodx(int dp){
        
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,  
                dp, getResources().getDisplayMetrics());  
    }
    
    public int getType() {
        return mType;
    }
    /**
     * ����ͼƬ���ͣ�Բ�Ρ�Բ�Ǿ��Ρ���Բ��
     * @param mType
     */
    public void setType(int mType) {
        if(this.mType != mType){
            this.mType = mType;
            invalidate();
        }
        
    }
    public int getRoundRadius() {
        return mRoundRadius;
    }
    /**
     * ����Բ�Ǵ�С
     * @param mRoundRadius
     */
    public void setRoundRadius(int mRoundRadius) {
        if(this.mRoundRadius != mRoundRadius){
            this.mRoundRadius = mRoundRadius;
            invalidate();
        }
        
    }
    
}
