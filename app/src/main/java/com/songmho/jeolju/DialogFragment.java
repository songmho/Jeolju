package com.songmho.jeolju;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by songmho on 2015-05-28.
 */
public class DialogFragment extends android.support.v4.app.Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout layout=(LinearLayout)inflater.inflate(R.layout.fragment_dialog,container,false);
        LinearLayout  cur_container=(LinearLayout)layout.findViewById(R.id.container);

        return layout;
    }
}