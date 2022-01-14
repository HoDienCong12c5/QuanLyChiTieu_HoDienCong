package com.example.quanlychitieu.QuanTrong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.quanlychitieu.DanhSachCTCT;
import com.example.quanlychitieu.DataBase;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.TenLoaiChiTieu;

public class ChinhSuaTongTheLoaiCtT extends AppCompatActivity {
    EditText txtTen;
    Button btnLuu,btnXoa;
    RadioGroup rdbChon;
    RadioButton rdbThu,rdbChi;
    DataBase ddBase;
    int id,idLCT,tt=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_tong_the_loai_ct_t);
        Add();
        Intent intent=getIntent();
        BanDau(intent);
        ddBase = new DataBase(ChinhSuaTongTheLoaiCtT.this,"DanhSachTenChiTieu.sqlite",null,1);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql="SELECT ct.Id from ChiTiet ct WHERE ct.IdTenLoaiChiTieu="+id+"";
                Cursor cursor=ddBase.GetData(sql);


                    AlertDialog.Builder builder=new AlertDialog.Builder(ChinhSuaTongTheLoaiCtT.this);
                    builder.setTitle("xác nhận");
                    builder.setMessage("Có chắc xóat ?");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(cursor.getCount() >0){
                                Toast.makeText(ChinhSuaTongTheLoaiCtT.this,"không thể xóa ", Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                            else {
                            String sql1="delete FROM TenChiTieu where Id="+id+"";
                            ddBase.QueryData(sql1);
                            Toast.makeText(ChinhSuaTongTheLoaiCtT.this,"Xóa thành công", Toast.LENGTH_LONG).show();
                            txtTen.setText("");
                            rdbChi.setFocusable(false);
                            rdbThu.setFocusable(false);
                            tt=1;
                            }
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
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(tt==1){
                   Bundle bundle=new Bundle();
                   bundle.putInt("TT",tt);
                   intent.putExtra("data",bundle);
                   setResult(QuanLyTongThe.luuSua_QuanLyTongThe,intent);
                   finish();
               }
                else {
                    if(txtTen.getText().toString().trim().length() <1){
                        Toast.makeText(ChinhSuaTongTheLoaiCtT.this,"Phải đủ thông tin :"+id+"", Toast.LENGTH_LONG).show();
                    }
                    else {
                        String sql="UPDATE TenChiTieu  SET TenChiTieu ='"+txtTen.getText().toString().trim()+"' where Id="+id+"";
                        ddBase.QueryData(sql);
                        Bundle bundle=new Bundle();
                        bundle.putInt("TT",tt);
                        bundle.putInt("Id",id);
                        intent.putExtra("data",bundle);
                        setResult(QuanLyTongThe.luuSua_QuanLyTongThe,intent);
                        finish();
                    }

                }
            }
        });
    }
    public void Add(){
        txtTen=(EditText)findViewById(R.id.txtTen__EditTongTheCt);
        btnLuu=(Button)findViewById(R.id.btnLuu_EditTongTheCt);
        btnXoa=(Button)findViewById(R.id.btnXoa_EditTongTheCt);
        rdbChon=(RadioGroup)findViewById(R.id.rdgTong__EditTongTheCt);
        rdbChi=(RadioButton) findViewById(R.id.rdbChitieu_EditTongTheCt);
        rdbThu=(RadioButton) findViewById(R.id.rdbThuLn_EditTongTheCt);
    }
    public void BanDau(Intent intent){
        Bundle bd=intent.getBundleExtra("data");
        id =bd.getInt("Id");
        ddBase = new DataBase(ChinhSuaTongTheLoaiCtT.this,"DanhSachTenChiTieu.sqlite",null,1);
        Cursor dd = ddBase.GetData("select * from TenChiTieu where Id="+id+" ");
        dd.moveToFirst();
            String ten=dd.getString(2);
            id=dd.getInt(0);
             idLCT=dd.getInt(1);
            txtTen.setText(ten);
            if(idLCT==1)
                rdbThu.setChecked(true);
            else
                rdbChi.setChecked(true);

    }

}