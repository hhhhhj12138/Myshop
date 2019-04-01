package com.test.myshop.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.squareup.picasso.Picasso;

import com.test.myshop.CniaoApplication;
import com.test.myshop.Contants;
import com.test.myshop.LoginActivity;
import com.test.myshop.MyFavoriteActivity;
import com.test.myshop.MyOrderActivity;
import com.test.myshop.MyProblemActivity;
import com.test.myshop.R;
import com.test.myshop.bean.User;
import de.hdodenhof.circleimageview.CircleImageView;


public class MineFragment extends BaseFragment{


    @ViewInject(R.id.img_head)
    private CircleImageView mImageHead;

    @ViewInject(R.id.txt_username)
    private TextView mTxtUserName;

    @ViewInject(R.id.btn_logout)
    private Button mbtnLogout;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine,container,false);
    }

    @Override
    public void init() {
        showUser();
    }


    private  void showUser(){

        User user = CniaoApplication.getInstance().getUser();
        if(user ==null){
            mbtnLogout.setVisibility(View.GONE);
            mTxtUserName.setText(R.string.to_login);

        }
        else{

            mbtnLogout.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(user.getLogo_url()))
                Picasso.with(getActivity()).load(Uri.parse(user.getLogo_url())).into(mImageHead);
            mTxtUserName.setText(user.getUsername());

        }

    }


    @OnClick(value = {R.id.img_head,R.id.txt_username})
    public void toLoginActivity(View view){

        User user = CniaoApplication.getInstance().getUser();
        if(user !=null){
            return;
        }

        Intent intent = new Intent(getActivity(), LoginActivity.class);

        startActivityForResult(intent, Contants.REQUEST_CODE);

    }
    @OnClick(R.id.btn_logout)
    public void back_to_LoginActivity(View view){
       Contants.IS_LOGIN = false;
       Button btn_logout = (Button)getActivity().findViewById(R.id.btn_logout);
       btn_logout.setVisibility(View.INVISIBLE);
       mTxtUserName.setText("点击登陆");
       mTxtUserName.setClickable(true);
       mImageHead.setClickable(true);
    }

    @OnClick(R.id.txt_my_orders)
    public void toMyOrderActivity(View view){

        if (!Contants.IS_LOGIN) {
            AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
            dialog.setTitle("提示：");
            dialog.setMessage("请先登陆");
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "YES", (DialogInterface.OnClickListener) null);
            dialog.show();
            return;
        }
        Intent intent = new Intent(getActivity(),MyOrderActivity.class);
        startActivity(intent);

    }


    @OnClick(R.id.txt_my_problem)
    public void toMyProblemActivity(View view){
        if (!Contants.IS_LOGIN) {
            AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
            dialog.setTitle("提示：");
            dialog.setMessage("请先登陆");
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "YES", (DialogInterface.OnClickListener) null);
            dialog.show();
            return;
        }
        Intent intent = new Intent(getActivity(),MyProblemActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.txt_my_favorite)
    public void toFavoriteActivity(View view){
        if (!Contants.IS_LOGIN) {
            AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
            dialog.setTitle("提示：");
            dialog.setMessage("请先登陆");
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "YES", (DialogInterface.OnClickListener) null);
            dialog.show();
            return;
        }
        Intent intent = new Intent(getActivity(),MyFavoriteActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (data != null) Contants.IS_LOGIN = true;
            if (Contants.IS_LOGIN) {
                mTxtUserName.setText(data.getExtras().getString("name"));
                mbtnLogout.setVisibility(View.VISIBLE);
                mTxtUserName.setClickable(false);
                mImageHead.setClickable(false);
            }


    }

}
