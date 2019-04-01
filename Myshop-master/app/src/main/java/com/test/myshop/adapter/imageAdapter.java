package com.test.myshop.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.test.myshop.Contants;
import com.test.myshop.MainActivity;
import com.test.myshop.MyFavSendActivity;
import com.test.myshop.R;
import com.test.myshop.myDB;

import java.util.ArrayList;
import java.util.PropertyResourceBundle;
import static com.test.myshop.R.layout.image_item;


public class imageAdapter extends RecyclerView.Adapter<imageAdapter.viewHolder> {
    private Context mContext;
    private static ArrayList<Bitmap> mList;
    private LayoutInflater mLayoutinflater;
//    private myDB mydb = MainActivity.mydb;
//    private SQLiteDatabase db = MainActivity.db;

    public imageAdapter(Context context, ArrayList<Bitmap> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(mContext).inflate(R.layout.image_item, viewGroup, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, final int i) {
        viewHolder.image.setImageBitmap(mList.get(i));
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == mList.size() - 1 && !Contants.IS_FULL) {
                    Activity origin = (Activity)mContext;
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    origin.startActivityForResult(intent, 1);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
       return mList.size();

    }

    class viewHolder extends RecyclerView.ViewHolder{
        private ImageView image;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }


}
