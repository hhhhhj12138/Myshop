package com.test.myshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;


import com.test.myshop.bean.Goods;
import com.test.myshop.http.OkHttpHelper;
import com.test.myshop.widget.ClearEditText;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {



    @ViewInject(R.id.etxt_phone)
    private ClearEditText mEtxtPhone;
    @ViewInject(R.id.etxt_pwd)
    private ClearEditText mEtxtPwd;
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    public static myDB mydb;
    public static SQLiteDatabase db;
    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
        TextView Reg=(TextView)findViewById(R.id.txt_toReg);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegActivity.class);
                startActivity(intent);
            }
        });
        ViewUtils.inject(this);
        mydb = new myDB(LoginActivity.this, "db", null, 1);
        db = mydb.getWritableDatabase();
        mydb.onCreate(db);

        initToolBar();
    }

    private void initToolBar(){


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    @OnClick(R.id.iv_back)
    public void back(View view){
        finish();
    }

    @OnClick(R.id.btn_login)
    public void login(View view){

        String phone = mEtxtPhone.getText().toString().trim();
        String pwd = mEtxtPwd.getText().toString().trim();
        if(phone.equals("")) {
            Toast.makeText(LoginActivity.this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
        }
        else if (!phone.equals("") && pwd.equals("")) {
            Toast.makeText(LoginActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
        }
        else {
            Cursor cursor = mydb.queryrawuser(db, phone);
            if (!cursor.moveToFirst()) {
                Toast.makeText(LoginActivity.this, "用户不存在！", Toast.LENGTH_SHORT).show();
            } else {
                if (!cursor.getString(cursor.getColumnIndex("password")).equals(pwd)) {
                    Toast.makeText(LoginActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                } else {
                    String name =cursor.getString(cursor.getColumnIndex("name"));
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("phone",phone);
                    bundle.putString("name",name);
                    intent.putExtras(bundle);
                    intent.putExtra("id",1);
                    setResult(Contants.REQUEST_CODE, intent);
                    finish();
                }
            }
        }
        Cursor cursor = mydb.getallgoods(db);
        while (cursor.moveToNext()) {
            String sname = cursor.getString(cursor.getColumnIndex("name"));
            String sphone = cursor.getString(cursor.getColumnIndex("phone"));
            int sspinner = cursor.getInt(cursor.getColumnIndex("spinner"));
            float sprice = cursor.getFloat(cursor.getColumnIndex("price"));
            String snotes = cursor.getString(cursor.getColumnIndex("notes"));
            byte[] in = null;
            Bitmap[] bmpout = null;
            for (int i = 1; i <= 3; i++) {
                in = cursor.getBlob(cursor.getColumnIndex("image" + i));
                bmpout[i-1] = BitmapFactory.decodeByteArray(in, 0, in.length);
            }
            Goods good = new Goods(sname, bmpout, sspinner, sprice, sphone, snotes);
            Contants.allGoods.add(good);
        }
        cursor = mydb.querygoods(db, phone);
        while (cursor.moveToNext()) {
            String sname = cursor.getString(cursor.getColumnIndex("name"));
            String sphone = cursor.getString(cursor.getColumnIndex("phone"));
            int sspinner = cursor.getInt(cursor.getColumnIndex("spinner"));
            float sprice = cursor.getFloat(cursor.getColumnIndex("price"));
            String snotes = cursor.getString(cursor.getColumnIndex("notes"));
            byte[] in = null;
            Bitmap[] bmpout = null;
            for (int i = 1; i <= 3; i++) {
                in = cursor.getBlob(cursor.getColumnIndex("image" + i));
                bmpout[i-1] = BitmapFactory.decodeByteArray(in, 0, in.length);
            }
            Goods good = new Goods(sname, bmpout, sspinner, sprice, sphone, snotes);
            Contants.myGoods.add(good);
        }
    }
}