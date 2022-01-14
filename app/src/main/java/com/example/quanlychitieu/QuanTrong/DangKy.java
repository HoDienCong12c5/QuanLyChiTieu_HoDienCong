package com.example.quanlychitieu.QuanTrong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlychitieu.ChiTieuThuChi;
import com.example.quanlychitieu.DanhSachCTCT;
import com.example.quanlychitieu.DataBase;
import com.example.quanlychitieu.R;

public class DangKy extends AppCompatActivity {
    EditText txtTK,txtPass,txtSDT,txtGmail;
    Button btnOk;
    ImageView btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Add();
        Intent intent=getIntent();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t=txtTK.getText().toString().trim();
                String p=txtPass.getText().toString().trim();
                String sdt=txtSDT.getText().toString().trim();
                String e=txtGmail.getText().toString().trim();
                if(t.length() >0 && p.length() >0 && sdt.length() >0 && e.length() >0 ){
                    Bundle bundle=new Bundle();
                    //Intent intent=new Intent(DangKy.this,DangNhap.class);
                    bundle.putString("TK",t);
                    bundle.putString("pas",p);
                    intent.putExtra("data",bundle);
                    setResult(2,intent);
                    finish();

                }
                else {
                    Toast.makeText(DangKy.this,"phải nhập đủ thống số",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(DangKy.this);
                builder.setTitle("xác nhận");
                builder.setMessage("Có chắc thoát ?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(3,intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }
    public  void  Add(){
        txtTK=(EditText)findViewById(R.id.editTextName_DK);
        txtPass=(EditText)findViewById(R.id.editTextPassword_DK);
        txtSDT=(EditText)findViewById(R.id.editTextMobile_DK);
        txtGmail=(EditText)findViewById(R.id.editTextEmail_DK);
        btnOk=(Button)findViewById(R.id.cirRegisterButton_DK);
        btnClose=(ImageView)findViewById(R.id.close_dk);

    }

}
