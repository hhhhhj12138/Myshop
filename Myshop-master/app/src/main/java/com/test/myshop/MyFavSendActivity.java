package com.test.myshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.test.myshop.adapter.imageAdapter;
import com.test.myshop.bean.Goods;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class MyFavSendActivity extends BaseActivity {
    private Spinner spinner;
    private ImageView fav_send_back;
    private ArrayList<Bitmap> arrayList = new ArrayList<Bitmap>();
    private RecyclerView recyclerView;
    static public Bitmap bitmap;
    imageAdapter adapter;
    private Button finish;
    private EditText name;
    private EditText price;
    private EditText phone;
    private EditText notes;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_send);

        //获得视图控件
        findView();

        //选图片
        Contants.IS_FULL = false;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_delete_32);
        arrayList.add(BitmapFactory.decodeResource(getResources(), R.drawable.icon_add));
        adapter = new imageAdapter(MyFavSendActivity.this, arrayList);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyFavSendActivity.this);
        linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        //返回键
        fav_send_back= (ImageView) findViewById(R.id.fav_send_back);
        fav_send_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //下拉框
        spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String result =adapterView.getItemAtPosition(i).toString();
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

        //完成键
        finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().equals("") && !phone.getText().toString().equals("") && !price.getText().toString().equals("")
                        && !notes.getText().toString().equals("") && !(arrayList.size() == 0)) {
                    String sname = name.getText().toString();
                    String sphone = phone.getText().toString();
                    float sprice = Float.parseFloat(price.getText().toString());
                    String snote = notes.getText().toString();
                    int sspinner = spinner.getSelectedItemPosition();
                    Bitmap[] bitmaps = new Bitmap[3];
                    for (int i = 0; i < 3; i++) {
                        bitmaps[i] = arrayList.get(i);
                    }
                    Goods good = new Goods(sname, bitmaps, sspinner, sprice, sphone, snote);
                    Contants.myGoods.add(good);

                }
                Toast.makeText(MyFavSendActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
            }
        });
//        fabu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int id = 1;
//                String name = "a";
//                int price = 500;
//                Goods good = new Goods(id, name, p);
//
//            }
//        });

    }

    public void findView() {
        name = (EditText) findViewById(R.id.name);
        price = (EditText) findViewById(R.id.price);
        spinner = (Spinner) findViewById(R.id.spinner);
        phone = (EditText) findViewById(R.id.phone);
        notes = (EditText) findViewById(R.id.txt_note);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {
            Uri uri = data.getData();
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (bitmap != null) {
                arrayList.add(arrayList.size() - 1, bitmap);
                if (arrayList.size() == 4) {
                    arrayList.remove(arrayList.size() - 1);
                    Contants.IS_FULL = true;
                }
            }
        }
        adapter.notifyDataSetChanged();

    }

}
