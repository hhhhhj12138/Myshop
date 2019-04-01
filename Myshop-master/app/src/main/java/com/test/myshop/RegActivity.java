package com.test.myshop;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class RegActivity extends BaseActivity {


    private TextView name;
    private TextView phone;
    private TextView pwd;
    private TextView rpwd;
    private Button btn_ok;
    public static myDB mydb;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        mydb = new myDB(RegActivity.this, "db", null, 1);
        db = mydb.getWritableDatabase();
        mydb.onCreate(db);
        findallwidget();
        setonListner();
    }

    @OnClick(R.id.reg_back)
    public void back(View view){
        Intent intent = new Intent(RegActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void findallwidget() {

        name =  (TextView)findViewById(R.id.name);
        pwd = (TextView)findViewById(R.id.pwd);
        rpwd = (TextView)findViewById(R.id.rpwd);
        phone =(TextView) findViewById(R.id.phone);
        btn_ok = (Button) findViewById(R.id.btn_ok);

    }

    public void setonListner() {


        btn_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String uname = name.getText().toString();
                String upwd = pwd.getText().toString();
                String urpwd = rpwd.getText().toString();
                String uphone = phone.getText().toString();
                String mobiles = phone.getText().toString();
                Pattern p = Pattern.compile("^((1[3,5,7,8][0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
                Matcher m = p.matcher(mobiles);
                if (uname.equals("")) {
                    Toast.makeText(RegActivity.this, "名字不能为空！", Toast.LENGTH_SHORT).show();
                } else if (uphone.equals("")) {
                    Toast.makeText(RegActivity.this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
                } else if (!m.matches()) {
                    Toast.makeText(RegActivity.this, "请输入正确的手机号！", Toast.LENGTH_SHORT).show();
                } else if (upwd.equals("")) {
                    Toast.makeText(RegActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                } else if (urpwd.equals("")) {
                    Toast.makeText(RegActivity.this, "确认密码不能为空！", Toast.LENGTH_SHORT).show();
                } else if (!urpwd.equals(upwd)) {
                    Toast.makeText(RegActivity.this, "确认密码错误！", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor cursor = mydb.queryrawuser(db, uphone);
                    if (cursor.moveToFirst()) {
                        Toast.makeText(RegActivity.this, "此账号已被注册！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                        mydb.insertuser(db, uphone, uname, upwd);
                        Intent intent=new Intent(RegActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }


                }
             }
        });
    }



}