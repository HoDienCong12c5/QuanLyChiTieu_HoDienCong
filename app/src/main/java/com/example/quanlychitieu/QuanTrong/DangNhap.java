package com.example.quanlychitieu.QuanTrong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlychitieu.Home_Fragment;
import com.example.quanlychitieu.R;

public class DangNhap extends AppCompatActivity {
    public final static int dk = 1;
    EditText txtTK, txtPass, txtDK;
    Button btnDN;
    String user="thanhcong",p="123";
    SharedPreferences sharedPreferences;
    Bundle bundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        sharedPreferences=getSharedPreferences("tk",MODE_PRIVATE);

        Add();
        txtTK.setText(sharedPreferences.getString("tk",""));
        txtPass.setText(sharedPreferences.getString("mk",""));
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tk, pas;
                tk = txtTK.getText().toString().trim();
                pas = txtPass.getText().toString().trim();
                if (tk.length() < 1 || pas.length() < 1) {
                    Toast.makeText(DangNhap.this, "bạn cần phải nhập đủ dữ liệu", Toast.LENGTH_LONG).show();
                } else {
                    if (tk.equals(user) && pas.equals(p)) {
                        bundle.putString("tk",txtTK.getText().toString());
                        bundle.putString("mk",txtPass.getText().toString());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("tk",txtTK.getText().toString());
                        editor .putString("mk",txtPass.getText().toString());
                        editor.putBoolean("ok",true);
                        editor.commit();
                        Intent intent = new Intent(DangNhap.this, MainActivity_Fragment.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(DangNhap.this, "Mật khẩu hoặc tài khoản chưa đúng", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void Add() {
        txtTK = (EditText) findViewById(R.id.editTextEmail_dangnhap);
        txtPass = (EditText) findViewById(R.id.editTextPassword);
        btnDN = (Button) findViewById(R.id.cirLoginButton_dangnhap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == dk) {
            if(resultCode==2){
                Bundle bundle = data.getBundleExtra("data");
                txtTK.setText(bundle.getString("TK"));
                user=txtTK.getText().toString().trim();
                txtPass.setText(bundle.getString("pas"));
                p=txtPass.getText().toString().trim();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onLoginClick(View view) {
        Intent intent = new Intent(DangNhap.this, DangKy.class);
       // startActivity(intent);
        startActivityForResult(intent,dk);
    }

}