package com.omega_r.omegakeyboard.detector;


import android.support.annotation.Nullable;

import com.omega_r.omegakeyboard.SoftKeyboard;

public abstract class BaseDetecor implements SoftKeyboard.Detector {

    @Nullable
    private SoftKeyboard.Listener mListener;

    @Override
    public void setListener(@Nullable SoftKeyboard.Listener listener) {
        mListener = listener;
    }

    protected void onKeyboardChange(boolean visible) {
        if (mListener != null) {
            mListener.onKeyboardChange(visible);
        }
    }
}
