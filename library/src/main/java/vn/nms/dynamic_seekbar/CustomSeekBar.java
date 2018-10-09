package vn.nms.dynamic_seekbar;

/**
 * @author Minh
 * @version 1.0
 * @since 11/3/2017.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


public class CustomSeekBar extends AppCompatSeekBar {

    public static final int ROTATION_ANGLE_CW_90 = 90;
    public static final int ROTATION_ANGLE_CW_270 = 270;
    public static final int ROTATION_ANGLE_DEFAULT = 0;

    private int mRotationAngle = ROTATION_ANGLE_DEFAULT;
    private int progressDrawable;
    private boolean isHideProgressInit;

    public CustomSeekBar(Context context) {
        super(context);
        initialize(context, null, 0, 0);
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context, attrs, defStyle, 0);
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs, 0, 0);
    }

    public void clearBackground() {
        setProgressDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr, int defStyle) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomSeekBar, defStyleAttr, defStyle);
            final int rotationAngle = a.getInteger(R.styleable.CustomSeekBar_dms_rotation, 0);
            mRotationAngle = rotationAngle;
            int thumbId = a.getResourceId(R.styleable.CustomSeekBar_dms_thumbDrawable, 0);
            progressDrawable = a.getResourceId(R.styleable.CustomSeekBar_dms_progressDrawable, 0);
            isHideProgressInit = a.getBoolean(R.styleable.CustomSeekBar_dms_isHideProgressInit, false);
            if (thumbId != 0) {
                int thumbSize = a.getDimensionPixelSize(R.styleable.CustomSeekBar_dms_thumbSize, 0);
                if (thumbSize != 0) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), thumbId);
                    if (bitmap == null) {
                        Drawable drawable = getResources().getDrawable(thumbId);
                        Canvas canvas = new Canvas();
                        bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                                Bitmap.Config.ARGB_8888);
                        canvas.setBitmap(bitmap);
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        drawable.draw(canvas);
                        setThumb(drawable);
                    } else {
                        Bitmap thumb = Bitmap.createBitmap(thumbSize, thumbSize, Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(thumb);
                        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                                new Rect(0, 0, thumb.getWidth(), thumb.getHeight()), null);
                        Drawable drawable = new BitmapDrawable(getResources(), thumb);
                        setThumb(drawable);
                    }
                } else {
                    Drawable drawable = getResources().getDrawable(thumbId);
                    Canvas canvas = new Canvas();
                    Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                            Bitmap.Config.ARGB_8888);
                    canvas.setBitmap(bitmap);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    drawable.draw(canvas);
                    setThumb(drawable);
                }
                if (isHideProgressInit) {
                    setProgressDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                } else if (progressDrawable != 0) {
                    setProgressDrawable(getResources().getDrawable(progressDrawable));
                }
                a.recycle();
            }
        }
    }

    public void setThumb(int thumbId, int thumbSize) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), thumbId);
        if (bitmap != null) {
            Bitmap thumb = Bitmap.createBitmap(thumbSize, thumbSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(thumb);
            canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                    new Rect(0, 0, thumb.getWidth(), thumb.getHeight()), null);
            Drawable drawable = new BitmapDrawable(getResources(), thumb);
            setThumb(drawable);
        } else {
            setThumbDrawable(thumbId);
        }
    }

    public void setThumbDrawable(int thumbId) {
        Drawable drawable = getResources().getDrawable(thumbId);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        setThumb(drawable);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        switch (mRotationAngle) {
            case ROTATION_ANGLE_CW_90:
            case ROTATION_ANGLE_CW_270:
                super.onSizeChanged(h, w, oldh, oldw);
                break;
            default:
                super.onSizeChanged(w, h, oldw, oldh);
                break;
        }
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
    }


    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        switch (mRotationAngle) {
            case ROTATION_ANGLE_CW_90:
            case ROTATION_ANGLE_CW_270:
                super.onMeasure(heightMeasureSpec, widthMeasureSpec);
                setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
                break;
            default:
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                break;
        }
    }

    @Override
    protected void onDraw(Canvas c) {
        if (mRotationAngle == ROTATION_ANGLE_CW_90) {
            c.rotate(-90);
            c.translate(-getHeight(), 0);
        } else if (mRotationAngle == ROTATION_ANGLE_CW_270) {
            c.rotate(90);
            c.translate(0, -getWidth());
        }
        super.onDraw(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }

        switch (mRotationAngle) {
            case ROTATION_ANGLE_CW_90:
            case ROTATION_ANGLE_CW_270:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                        int i = getMax() - (int) (getMax() * event.getY() / getHeight());
                        if (mRotationAngle == ROTATION_ANGLE_CW_90) {
                            setProgress(i);
                        } else {
                            setProgress(i);
                        }
                        Log.i("Progress", getProgress() + "");
                        onSizeChanged(getWidth(), getHeight(), 0, 0);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                break;
            default:
                return super.onTouchEvent(event);
        }
        return true;
    }

}
