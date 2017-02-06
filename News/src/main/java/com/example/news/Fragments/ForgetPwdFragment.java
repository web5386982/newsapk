package com.example.news.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.news.MainActivity;
import com.example.news.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/27.
 */

public class ForgetPwdFragment extends Fragment {
    @Bind(R.id.up_main_mid_text)
    TextView titletext;
    MainActivity mactivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_pwd_surface, container, false);
        ButterKnife.bind(this,view);
         mactivity = (MainActivity) getActivity();
         titletext.setText("忘记密码");
        return view;
    }
    @OnClick(R.id.up_main_left_img)
    public void lefthomeclick(View view) {

        mactivity.slidmenu.showMenu();
    }

    @OnClick(R.id.up_main_right_img)
    public void rightshareclick(View view) {

        mactivity.slidmenu.showSecondaryMenu();
    }
}
