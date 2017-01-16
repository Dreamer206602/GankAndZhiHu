package com.booboomx.gank.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.view.ViewParent;
import android.view.animation.OvershootInterpolator;

import com.booboomx.gank.BuildConfig;
import com.booboomx.gank.R;

/**
 * Created by booboomx on 17/1/16.
 */

public class SplashView  extends View{

    public static final int DEFAULT_HOLE_FILL_COLOR= Color.WHITE;
    public static final int  DEFAULT_ICON_COLOR=Color.rgb(23,169,229);
    public static final int  DEFAULT_DURATION=500;
    public static final boolean DEFAULT_REMOVE_FROM_PARENT_ON_END=true;

    public static final int PARENT_STROKE_WIDTH=2;
    private Drawable mIcon;
    private int mHoleFillColor=DEFAULT_HOLE_FILL_COLOR;
    private int mIconColor=DEFAULT_ICON_COLOR;
    private long mDuration=DEFAULT_DURATION;
    private boolean mRemoveFromParentOnEND=true;
    private float mCurrentScale=1;

    private int mWidth,mHeight;
    private int mIconWidth,mIconHeight;
    private float mMaxScale=1;

    private Paint mPaint=new Paint();

    public static interface ISPlashListener{
        public void onStart();
        public void  onUpdate(float completionFraction);
        public void  onEnd();
    }

    public static final String TAG="SplashView";
    public SplashView(Context context) {
        this(context,null);
    }

    public SplashView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SplashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
        setupAttributes(attrs);
    }


    private void setupAttributes(AttributeSet attrs) {

        Context context=getContext();
        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.SplashView);

        int numAttrs=a.getIndexCount();
        for (int i = 0; i < numAttrs; i++) {

            int attr= a.getIndex(i);
            switch (attr){
                case R.styleable.SplashView_splashIcon:
                    setIconDrawable(a.getDrawable(i));
                    break;

                case R.styleable.SplashView_iconColor:
                    setIconColor(a.getColor(i,DEFAULT_ICON_COLOR));
                    break;
                case R.styleable.SplashView_holeFillColor:
                    setHoleFillColor(a.getColor(i,DEFAULT_HOLE_FILL_COLOR));
                    break;

                case R.styleable.SplashView_duration:
                    setDuration(a.getInt(i,DEFAULT_DURATION));
                    break;

                case R.styleable.SplashView_removeFromParentOnEnd:
                    setRemoveFromParentOnEnd(a.getBoolean(i,DEFAULT_REMOVE_FROM_PARENT_ON_END));
                    break;
            }
            a.recycle();

        }

    }

    private void setRemoveFromParentOnEnd(boolean shouldRemove) {
        mRemoveFromParentOnEND=shouldRemove;
    }

    private void setDuration(long duration) {
        if(duration<0){
            throw new IllegalArgumentException("duration cannot be less than 0");
        }
        mDuration=duration;

    }

    private void setIconColor(int color) {
        mIconColor=color;
    }

    private void setHoleFillColor(int color) {
        mHoleFillColor=color;
    }


    public void setIconResource(int resId){
        Drawable icon=getResources().getDrawable(resId);
        if(icon==null){
            throw new IllegalArgumentException("no drawable found for the resId"+resId);
        }
        setIconDrawable(icon);
    }

    private void setIconDrawable(Drawable icon) {
        mIcon=icon;
        if(mIcon!=null){
            mIconWidth=mIcon.getIntrinsicWidth();
            mIconHeight=mIcon.getIntrinsicHeight();

            Rect iconBounds=new Rect();
            iconBounds.left=0;
            iconBounds.top=0;
            iconBounds.right=mIconWidth;
            iconBounds.bottom=mIconHeight;
            mIcon.setBounds(iconBounds);

        }else{
            mIconWidth=0;
            mIconHeight=0;

        }

        setMaxScale();
    }

    private void setMaxScale() {

        if(mIconWidth<1||mIconHeight<1){
            mMaxScale=1;
            return;
        }

        mMaxScale=2*Math.max(mWidth/mIconHeight,mHeight/mIconHeight);

        if(mMaxScale<1){
            mMaxScale=1;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        float iconWidth=mIconWidth*mCurrentScale;
        float iconHeight=mIconHeight*mCurrentScale;


        float mIconLeft=(mWidth-iconWidth)/2;
        float mIconRight=mIconLeft+iconWidth;
        float mIconTop=(mHeight-iconHeight)/2;
        float mIconBottom=mIconTop+iconHeight;

        if(mCurrentScale<2){
            mPaint.setColor(mHoleFillColor);
            canvas.drawRect(mIconLeft,mIconTop,mIconRight,mIconBottom,mPaint);
        }


        mPaint.setColor(mIconColor);
        canvas.drawRect(0,0,mIconLeft,mHeight,mPaint);
        canvas.drawRect(mIconLeft,0,mIconRight,mIconTop,mPaint);
        canvas.drawRect(mIconLeft,mIconBottom,mIconRight,mHeight,mPaint);
        canvas.drawRect(mIconRight,0,mWidth,mHeight,mPaint);


        if(mIcon!=null){
            canvas.save();
            canvas.translate(mIconLeft,mIconTop);

            canvas.scale(mCurrentScale,mCurrentScale);

            mIcon.draw(canvas);
            canvas.restore();
        }else if(BuildConfig.DEBUG){
            Log.i(TAG, "icon is not set when the view needs to be drawn");
        }



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged: "+"width->"+w+"-->height"+h);
        mWidth=w;
        mHeight=h;


        setMaxScale();



    }

    private void initialize() {

        setBackgroundColor(Color.TRANSPARENT);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(PARENT_STROKE_WIDTH);

    }


    /**
     *
     * @param listener
     */
    public void splashAndDisappear(final ISPlashListener listener){

        final ValueAnimator animator = ValueAnimator.ofFloat(1, mMaxScale);
        animator.setDuration(mDuration);

        animator.setInterpolator(new OvershootInterpolator(1F));//插值器

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                mCurrentScale=1+mMaxScale-(Float) animation.getAnimatedValue();

                invalidate();


                if(listener!=null){
                    listener.onUpdate(animation.getCurrentPlayTime()/mDuration);
                }


            }
        });


        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(listener!=null){
                    listener.onStart();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(mRemoveFromParentOnEND){
                    ViewParent parent = getParent();

                    if(parent!=null&&parent instanceof ViewManager){
                        ViewManager viewManager= (ViewManager) parent;
                        viewManager.removeView(SplashView.this);
                    }else if(BuildConfig.DEBUG){

                    }

                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        post(new Runnable() {
            @Override
            public void run() {
                animator.reverse();
            }
        });



    }


}

