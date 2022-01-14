package com.example.quanlychitieu.QuanTrong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.TenLoaiChiTieu;

import java.util.ArrayList;

public class TongCT_Adapter extends BaseAdapter {
    private int layout;
    private Context mContext;
    private ArrayList<TenLoaiChiTieu> list;

    public TongCT_Adapter(Context mContext,int layout,  ArrayList<TenLoaiChiTieu> list) {
        this.layout = layout;
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        TextView txtid=(TextView)view.findViewById(R.id.txtid_dongtongCT);
        TextView txtTen=(TextView)view.findViewById(R.id.txtTenCT_dongtongCT);
        TextView txtLoai=(TextView)view.findViewById(R.id.txtTheLoai_dongtongCT);
        TenLoaiChiTieu ct= list.get(position);
        txtid.setText(ct.getId()+"");
        txtid.setVisibility(View.GONE);
        txtTen.setText(ct.getName());
        int cl=mContext.getResources().getColor(R.color.maudo);
        int clX=mContext.getResources().getColor(R.color.mauxanh);
        int l=ct.getIdLoaiCT();
        if(l==0){
            txtLoai.setText("Chi tiêu");
            txtLoai.setTextColor(cl);
        }
        else{
            txtLoai.setText("Thu lợi nhuận");
            txtLoai.setTextColor(clX);
        }
        return view;
    }
}
