package com.example.quanlychitieu.QuanTrong;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlychitieu.ChiTieuThuChi;
import com.example.quanlychitieu.ChiTieuThuChi_ApdTer;
import com.example.quanlychitieu.DataBase;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.TenLCT_AdpTer;
import com.example.quanlychitieu.TenLoaiChiTieu;

import java.util.ArrayList;

public class QuanLyTongThe extends Fragment {
    public static final int chinhSua_QuanLyTongThe=1;
    public static final int luuSua_QuanLyTongThe=2;
    View v;
    DataBase ddBase;
    TextView txtTen,txtID,txtLoai;
    ListView listView;
    ArrayList<TenLoaiChiTieu> arrayList = new ArrayList<>();
    ArrayAdapter<TongCT_Adapter> arrayAdapter = null;
    TongCT_Adapter apdTer;
    static int vitri=-1,id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public void Add(){
        txtTen=(TextView)v.findViewById(R.id.txtTenCT_dongtongCT);
        txtID=(TextView)v.findViewById(R.id.txtid_dongtongCT);
        txtLoai=(TextView)v.findViewById(R.id.txtTheLoai_dongtongCT);
        listView=(ListView)v.findViewById(R.id.lv_TongTheCT);
    }
    public void BanDau(){
        ddBase = new DataBase(v.getContext(),"DanhSachTenChiTieu.sqlite",null,1);
        Cursor dd = ddBase.GetData("select * from TenChiTieu ");
        arrayAdapter=null;
        while(dd.moveToNext()){
            String ten=dd.getString(2);
            int id=dd.getInt(0);
            int idLCT=dd.getInt(1);
            arrayList.add(new TenLoaiChiTieu(ten,id,idLCT));
        }
        apdTer=new TongCT_Adapter(v.getContext(),R.layout.dongtongthethuchi,arrayList);
        listView.setAdapter(apdTer);
    }
    public void ClickListView(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vitri=position;
                TenLoaiChiTieu ct =arrayList.get(position);
                Intent intent=new Intent(v.getContext(),ChinhSuaTongTheLoaiCtT.class);
                Bundle bundle=new Bundle();
                id=ct.getId();
                bundle.putInt("Id",ct.getId());
                intent.putExtra("data",bundle);
                startActivityForResult(intent,chinhSua_QuanLyTongThe);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==QuanLyTongThe.chinhSua_QuanLyTongThe){
            if(resultCode==QuanLyTongThe.luuSua_QuanLyTongThe){
                Bundle bundle=data.getBundleExtra("data");
                int tt=bundle.getInt("TT");
               if(tt==1){
                   apdTer.notifyDataSetChanged();
                    arrayList.remove(vitri);
                    Toast.makeText(v.getContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();
               }
                else{

                   ddBase = new DataBase(v.getContext(),"DanhSachTenChiTieu.sqlite",null,1);
                   id=bundle.getInt("Id");
                   Cursor dd = ddBase.GetData("select * from TenChiTieu where Id="+id+"");
                   dd.moveToFirst();
                    TenLoaiChiTieu ct =new TenLoaiChiTieu(dd.getString(2),dd.getInt(0),dd.getInt(1));
                    arrayList.set(vitri,ct);

                }

            }
            apdTer.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.option_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_quan_ly_tong_the, container, false);
        Add();
        BanDau();
        getActivity().setTitle("Quản lý các mục chi tiêu");
        ClickListView();
        return v;
    }

}