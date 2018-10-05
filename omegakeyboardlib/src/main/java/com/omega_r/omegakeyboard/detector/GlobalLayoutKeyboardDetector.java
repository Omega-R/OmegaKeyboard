package com.omega_r.omegakeyboard.detector;


import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

public class GlobalLayoutKeyboardDetector extends BaseDetecor
        implements ViewTreeObserver.OnGlobalLayoutListener {

    private View mContentView;
    private int mInitialValue;
    private boolean mIsKeyboardShown;

    private Rect mRect = new Rect();

    public GlobalLayoutKeyboardDetector(View contentView) {
        mContentView = contentView;
    }

    public void start() {
        mContentView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    public void stop() {
        if (Build.VERSION.SDK_INT >= 16) {
            mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } else {
            mContentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
    }

    @Override
    public void onGlobalLayout() {
        if (mInitialValue == 0) {
            mInitialValue = getValue();
        } else {
            int value = getValue();
            if (mInitialValue > value) {
                if (!mIsKeyboardShown) {
                    mIsKeyboardShown = true;
                    onKeyboardChange(true);
                }
            } else {
                if (mIsKeyboardShown) {
                    mIsKeyboardShown = false;
                    mContentView.post(new Runnable() {
                        @Override
                        public void run() {
                            onKeyboardChange(false);
                        }
                    });
                }
            }
        }
    }

    private int getValue() {
        mContentView.getWindowVisibleDisplayFrame(mRect);
        return mRect.height();
    }

    @Override
    public boolean isKeyboardShown() {
        return mIsKeyboardShown;
    }
}
