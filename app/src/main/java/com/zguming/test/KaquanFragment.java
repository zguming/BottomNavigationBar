package com.zguming.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by WangChang on 2016/5/15.
 */
public class KaquanFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static KaquanFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        KaquanFragment fragment = new KaquanFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
