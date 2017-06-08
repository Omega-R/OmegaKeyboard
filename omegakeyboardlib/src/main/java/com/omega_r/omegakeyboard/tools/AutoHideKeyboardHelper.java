package com.omega_r.omegakeyboard.tools;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class AutoHideKeyboardHelper {

    public boolean dispatchTouchEventForDialog(MotionEvent ev, Dialog dialog, final boolean consumed,
                                                Activity activity, boolean needClearFocus) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            final View view = dialog.getWindow().getCurrentFocus();
            if (view != null) {
                final View viewTmp;
                viewTmp = dialog.getWindow().getCurrentFocus();
                final View viewNew = viewTmp != null ? viewTmp : view;
                if (viewNew.equals(view)) {
                    final Rect rect = new Rect();
                    final int[] coordinates = new int[2];

                    view.getLocationOnScreen(coordinates);
                    rect.set(coordinates[0], coordinates[1], coordinates[0] + view.getWidth(), coordinates[1] + view.getHeight());

                    final int x = (int) ev.getRawX();
                    final int y = (int) ev.getRawY();

                    if (rect.contains(x, y)) {
                        return consumed;
                    }
                } else if (viewNew instanceof EditText) {
                    return consumed;
                }
                hideKeyboard(view, activity);
                if (needClearFocus) {
                    viewNew.clearFocus();
                }
                return consumed;
            }
        }
        return consumed;
    }

    public boolean dispatchTouchEventForActivity(MotionEvent ev, final boolean consumed,
                                                  Activity activity, boolean needClearFocus) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            final View view = activity.getCurrentFocus();
            if (view != null) {
                final View viewTmp = activity.getCurrentFocus();
                final View viewNew = viewTmp != null ? viewTmp : view;
                if (viewNew.equals(view)) {
                    final Rect rect = new Rect();
                    final int[] coordinates = new int[2];

                    view.getLocationOnScreen(coordinates);
                    rect.set(coordinates[0], coordinates[1], coordinates[0] + view.getWidth(), coordinates[1] + view.getHeight());

                    final int x = (int) ev.getX();
                    final int y = (int) ev.getY();
                    if (rect.contains(x, y)) {
                        return consumed;
                    }
                } else if (viewNew instanceof EditText) {
                    return consumed;
                }
                hideKeyboard(view, activity);
                if (needClearFocus) {
                    viewNew.clearFocus();
                }
                return consumed;
            }
        }
        return consumed;
    }

    public void hideKeyboard(View view, Activity activity) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
