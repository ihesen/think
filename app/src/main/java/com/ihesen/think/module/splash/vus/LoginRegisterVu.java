package com.ihesen.think.module.splash.vus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ihesen.think.module.mvp.vus.Vu;
import com.ihesen.think.module.splash.activities.HomeActivity;
import com.ihesen.think.R;
import com.ihesen.think.module.splash.fragments.LoginFragment;
import com.ihesen.think.module.splash.fragments.RegisterFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: ihesen on 2016/4/19 10:52
 * email: hesen@ichsy.com
 */
public class LoginRegisterVu implements Vu {

    @Bind(R.id.login)
    Button login;
    @Bind(R.id.register)
    Button register;
    private View rootView;

    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.login_register_vu, viewGroup, false);
        ButterKnife.bind(this, rootView);
    }


    @OnClick({R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                ((HomeActivity) rootView.getContext()).v.setToolbarText("登录");
                ((HomeActivity) rootView.getContext()).fragmentManager.beginTransaction().replace(R.id.framelayout, LoginFragment.getNewInstance()).addToBackStack(LoginFragment.class.getName()).commit();
                break;
            case R.id.register:
                ((HomeActivity) rootView.getContext()).v.setToolbarText("注册");
                ((HomeActivity) rootView.getContext()).fragmentManager.beginTransaction().replace(R.id.framelayout, RegisterFragment.getNewInstance()).addToBackStack(RegisterFragment.class.getName()).commit();
                break;
        }
    }
}
