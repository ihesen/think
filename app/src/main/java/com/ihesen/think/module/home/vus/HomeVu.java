package com.ihesen.think.module.home.vus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ihesen.think.utils.LogUtil;
import com.ihesen.think.utils.db.DbHelper;
import com.ihesen.think.R;
import com.ihesen.think.utils.db.tables.Activity;
import com.ihesen.think.module.mvp.vus.Vu;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: ihesen on 2016/4/19 16:44
 * email: hesen@ichsy.com
 */
public class HomeVu implements Vu {

    @Bind(R.id.edittext)
    EditText edittext;
    @Bind(R.id.add)
    Button add;
    @Bind(R.id.content)
    TextView content;
    @Bind(R.id.search)
    Button search;
    private View rootView;

    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.home_vo, viewGroup, false);
        ButterKnife.bind(this, rootView);
    }


    public void addData(String content) {
        String[] split = content.split(" ");
        Activity entity = new Activity();
        entity.setUserId("111111111");
        entity.setUsername(split[0]);
        entity.setMessage(split[1]);
        entity.setNewField("new field");
        long insert = DbHelper.getActivityDao().insert(entity);
        if(insert > 0){
            edittext.setText("");
        }
    }

    @OnClick({R.id.add, R.id.search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                addData(edittext.getText().toString());
                break;
            case R.id.search:
                search();
                break;
        }
    }

    private void search() {
        List<Activity> list = DbHelper.getActivityDao().queryBuilder().list();
        for (Activity activity : list) {
            LogUtil.hLog().d("HomeVu " + activity.getUsername());
            LogUtil.hLog().d("HomeVu " + activity.getMessage());
        }
    }
}
