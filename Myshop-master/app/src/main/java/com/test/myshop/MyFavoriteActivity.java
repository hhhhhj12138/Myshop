package com.test.myshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.myshop.adapter.BaseAdapter;
import com.test.myshop.adapter.FavoriteAdatper;
import com.test.myshop.adapter.decoration.CardViewtemDecortion;
import com.test.myshop.bean.Favorites;
import com.test.myshop.http.OkHttpHelper;
import com.test.myshop.http.SpotsCallBack;
import com.test.myshop.widget.CNiaoToolBar;

public class MyFavoriteActivity extends BaseActivity {


    @ViewInject(R.id.toolbar)
    private CNiaoToolBar mToolbar;


    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerview;


    private FavoriteAdatper mAdapter;

    private ImageView back;


    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();

    private Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);
        back = (ImageView)findViewById(R.id.fa_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        send = (Button) findViewById(R.id.my_gd_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyFavoriteActivity.this,MyFavSendActivity.class);
                startActivity(intent);
            }
        });



    }




}
