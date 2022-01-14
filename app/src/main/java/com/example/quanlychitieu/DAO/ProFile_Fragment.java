package com.example.quanlychitieu.DAO;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlychitieu.R;



public class ProFile_Fragment extends Fragment {
    View v;
    EditText txtT,txtG,txtSdt,txtP;
    Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void Add(){
        txtT=(EditText)v.findViewById(R.id.tk_profile);
        txtSdt=(EditText)v.findViewById(R.id.sdt_profile);
        txtG=(EditText)v.findViewById(R.id.gm_profile);
        txtP=(EditText)v.findViewById(R.id.p_profilr);
        btn=(Button)v.findViewById(R.id.btnCN_ProFile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t=txtT.getText().toString().trim();
                String g=txtG.getText().toString().trim();
                String s=txtSdt.getText().toString().trim();
                String p=txtP.getText().toString().trim();
                if(t.length()>0 && s.length()>0 && g.length()>0 && p.length()>6){
                    Toast.makeText(v.getContext(),"Cập nhật thành công", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(v.getContext(),"Phải nhập đủ dữ liệu và mật khẩu trên 6", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_pro_file_, container, false);
        getActivity().setTitle("Tài khoản cá nhân");
        Add();
        return v ;
    }
}