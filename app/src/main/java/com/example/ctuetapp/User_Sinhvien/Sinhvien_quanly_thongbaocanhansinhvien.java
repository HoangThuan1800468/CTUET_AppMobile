package com.example.ctuetapp.User_Sinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;
import com.example.ctuetapp.User_Sinhvien.List_item_thongbao.adapter_list_item_thong_bao_ca_nhan_sinh_vien;
import com.example.ctuetapp.User_Sinhvien.List_item_thongbao.thong_bao_ca_nhan_sinh_vien;

import java.util.ArrayList;

public class Sinhvien_quanly_thongbaocanhansinhvien extends AppCompatActivity {
    ListView lvthongbao;
    ArrayList<thong_bao_ca_nhan_sinh_vien> mangthongbao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien_quanly_thongbaocanhansinhvien);
        lvthongbao =(ListView) findViewById(R.id.thongbaocanhansv);
        mangthongbao = new ArrayList<thong_bao_ca_nhan_sinh_vien>();
        MainActivity.cs = MainActivity.dulieu.layDuLieu("select ma_MH from LopHP_sinhvien where mSv = '"+Sinhvien_trangchinh.masv+"'   ");
        while (MainActivity.cs.moveToNext()){
            MainActivity.cs1= MainActivity.dulieu.layDuLieu("select ma_TB,mGv,ngay_gui,noi_dung from ThongBao where maMH = '"+MainActivity.cs.getString(0)+"'   ");
            while (MainActivity.cs1.moveToNext()){
                String noidung = MainActivity.cs1.getString(3);
                String ngay_gui = MainActivity.cs1.getString(2);
                String mGv = MainActivity.cs1.getString(1);
                String tengv="",tenmh="";
                MainActivity.cs2= MainActivity.dulieu.layDuLieu("select ten_MH,ten_GV from LopHP where ma_MH = '"+MainActivity.cs.getString(0)+"'   ");
                while (MainActivity.cs2.moveToNext()){
                    tengv = MainActivity.cs2.getString(0);
                    tenmh = MainActivity.cs2.getString(1);
                }
                mangthongbao.add(new thong_bao_ca_nhan_sinh_vien(tengv,noidung,tenmh,ngay_gui));
            }
        }


        adapter_list_item_thong_bao_ca_nhan_sinh_vien adapter = new adapter_list_item_thong_bao_ca_nhan_sinh_vien(
                Sinhvien_quanly_thongbaocanhansinhvien.this,
                R.layout.list_item_sinhvien_quanly_thongbaocanhansinhvien,
                mangthongbao
        );
        lvthongbao.setAdapter(adapter);


    }
}