package com.richieye.examinationsystemCustomControl;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.richieye.examinationsystem.R;

/**
 * Created by RichieYe on 2016/4/14.
 */
public class CustomRoundImageView extends ImageView {
    private int mBorderThickness = 0;     //边框的宽度
    private Context mContext;
    private int defaultColor = 0xFFFFFF;      //默认的颜色

    // 如果只有其中一个有值，则只画一个圆形边框
    private int mBorderOutsideColor = 0;      //外边框的值
    private int mBorderInsideColor = 0;       //内边框的值

    private int defaultWidth = 0;         //默认组件的宽度
    private int defaultHeight = 0;        //默认组件的高度


    public CustomRoundImageView(Context context) {
        super(context);
        mContext = context;
    }

    public CustomRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setCustomAttributesets(attrs);      //设置自定义属性
    }

    public CustomRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setCustomAttributesets(attrs);
    }

    private void setCustomAttributesets(AttributeSet attrs)         //设置自定义组件的属性方法
    {
        TypedArray tArray = mContext.obtainStyledAttributes(attrs, R.styleable.roundedimageview);
        mBorderThickness = tArray.getDimensionPixelSize(R.styleable.roundedimageview_border_thickness, 0);
        mBorderOutsideColor = tArray.getColor(R.styleable.roundedimageview_border_outside_color, defaultColor);
        mBorderInsideColor = tArray.getColor(R.styleable.roundedimageview_border_inside_color, defaultColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {      //重写绘制组件的方法

        Drawable drawable = getDrawable();        //

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }

        this.measure(0, 0);

        if (drawable.getClass() == NinePatchDrawable.class) {
            return;
        }

        Bitmap bit = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = bit.copy(Bitmap.Config.ARGB_8888, true);

        if (defaultWidth == 0) {
            defaultWidth = getWidth();
        }

        if (defaultHeight == 0) {
            defaultHeight = getHeight();
        }

        int radius = 0;
        if (mBorderInsideColor != defaultColor && mBorderOutsideColor != defaultColor) {
            radius = (defaultWidth < defaultHeight ? defaultWidth : defaultHeight) / 2 - 2 * mBorderThickness;

            drawCircleBorder(canvas, radius + mBorderThickness / 2, mBorderInsideColor);

            drawCircleBorder(canvas, radius + mBorderThickness + mBorderThickness / 2, mBorderOutsideColor);
        } else if (mBorderInsideColor != defaultColor && mBorderOutsideColor == defaultColor) {
            radius = (defaultWidth < defaultHeight ? defaultWidth : defaultHeight) / 2 - mBorderThickness;

            drawCircleBorder(canvas, radius + mBorderThickness / 2, mBorderOutsideColor);
        } else {
            radius = (defaultWidth < defaultHeight ? defaultWidth : defaultHeight) / 2;
        }

        Bitmap roundBitmap = getCroppedRoundBitmap(bitmap, radius);
        canvas.drawBitmap(roundBitmap, defaultWidth / 2 - radius, defaultHeight / 2 - radius, null);
    }

    private Bitmap getCroppedRoundBitmap(Bitmap bitmap, int radius) {
        Bitmap scaledSrcBmp;
        int diameter = radius * 2;
        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
        int bmpWidth = bitmap.getWidth();
        int bmpHeight = bitmap.getHeight();
        int squareWidth = 0, squareHeight = 0;
        int x = 0, y = 0;
        Bitmap squareBitmap;
        if (bmpHeight > bmpWidth) {// 高大于宽
            squareWidth = squareHeight = bmpWidth;
            x = 0;
            y = (bmpHeight - bmpWidth) / 2;
            // 截取正方形图片
            squareBitmap = Bitmap.createBitmap(bitmap, x, y, squareWidth, squareHeight);
        } else if (bmpHeight < bmpWidth) {// 宽大于高
            squareWidth = squareHeight = bmpHeight;
            x = (bmpWidth - bmpHeight) / 2;
            y = 0;
            squareBitmap = Bitmap.createBitmap(bitmap, x, y, squareWidth, squareHeight);
        } else {
            squareBitmap = bitmap;
        }
        if (squareBitmap.getWidth() != diameter || squareBitmap.getHeight() != diameter) {
            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter, diameter, true);
        } else {
            scaledSrcBmp = squareBitmap;
        }
        Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(), scaledSrcBmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(scaledSrcBmp.getWidth() / 2,
                scaledSrcBmp.getHeight() / 2,
                scaledSrcBmp.getWidth() / 2,
                paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);
        bitmap = null;
        squareBitmap = null;
        scaledSrcBmp = null;
        return output;
    }

    private void drawCircleBorder(Canvas canvas, int radius, int color) {
        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(color);

        paint.setStyle(Paint.Style.STROKE);

        paint.setStrokeWidth(mBorderThickness);

        canvas.drawCircle(defaultWidth / 2, defaultHeight / 2, radius, paint);
    }
}
