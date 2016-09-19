package com.example.administrator.wechatsample_guolin;

import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/17.
 */
public class FoundFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        FrameLayout frameLayout = new FrameLayout(getActivity());
        frameLayout.setLayoutParams(params);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);
        TextView textView = new TextView(getActivity());
        params.setMargins(margin, margin, margin, margin);
        textView.setLayoutParams(params);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setText("发现界面");
        textView.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, dm));
        frameLayout.addView(textView);
        return frameLayout;
    }
}
