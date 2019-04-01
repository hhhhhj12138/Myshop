package com.test.myshop;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;




public class MyProblemActivity extends BaseActivity {


    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_problem);

        back = (ImageView)findViewById(R.id.pro_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
