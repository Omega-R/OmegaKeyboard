package com.omega_r.omegakeyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SoftKeyboard.Listener {

    private SoftKeyboard mSoftKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSoftKeyboard = new SoftKeyboard();
        mSoftKeyboard.start(this);
        mSoftKeyboard.setListener(this);
        mSoftKeyboard.show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mSoftKeyboard.dispatchTouchEvent(ev, null, super.dispatchTouchEvent(ev), this, true);
    }

    @Override
    public void onKeyboardChange(boolean visible) {
        Toast.makeText(this, "Test example = "  + visible, Toast.LENGTH_LONG).show();
    }
}
