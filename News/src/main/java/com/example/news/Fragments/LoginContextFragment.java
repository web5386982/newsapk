package com.example.news.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news.MainActivity;
import com.example.news.R;

import butterknife.ButterKnife;

/**这个类暂时没用到
 * Created by Administrator on 2016/12/28.
 */

public class LoginContextFragment  extends Fragment{
    //暂时没用这个类用的是UserCoreActivity类
    MainActivity mactivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user_core, container, false);
        ButterKnife.bind(this,view);
        mactivity = (MainActivity) getActivity();
        return view;
    }

}
