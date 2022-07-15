package com.example.ctuetapp.User_Sinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;

public class Sinhvien_quanly_danhsachlop_thongtinlophocphan extends AppCompatActivity {
    TextView sinhvien_thongtinlophocphan_textview_mamonhoc,sinhvien_thongtinlophocphan_textview_tenmonhoc,
            sinhvien_thongtinlophocphan_textview_tengv,sinhvien_thongtinlophocphan_textview_magv,
            sinhvien_thongtinlophocphan_textview_loaihocphan,sinhvien_thongtinlophocphan_textview_phonghoc,
            sinhvien_thongtinlophocphan_textview_sotinchi,sinhvien_thongtinlophocphan_textview_tinhtrang,
            sinhvien_thongtinlophocphan_textview_lythuyetbatdau,sinhvien_thongtinlophocphan_textview_lythuyet_tiet,
            sinhvien_thongtinlophocphan_textview_lythuyetketthuc, sinhvien_thongtinlophocphan_textview_thuchanhbatdau,
            sinhvien_thongtinlophocphan_textview_thuchanhtiet,sinhvien_thongtinlophocphan_textview_thuchanhketthuc;
    String malop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien_quanly_danhsachlop_thongtinlophocphan);
        Sinhvien_thongtinlophocphan_anhxa();
        malop = getIntent().getStringExtra("a");
        sinhvien_thongtinlophocphan_textview_mamonhoc.setText("Mã môn học: " +malop);

        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select ten_MH,ten_GV,ma_GV,so_tc,tiet_LT,tiet_TH,ngay_LT_bd,ngay_LT_kt,ngay_TH_bd,ngay_TH_kt,loaiHP,tinhtrangHP,phonghoc from LopHP where ma_MH = '"+malop+"' ");
        while (MainActivity.cs1.moveToNext()){
            sinhvien_thongtinlophocphan_textview_tenmonhoc.setText("Tên môn học: "+MainActivity.cs1.getString(0));
            sinhvien_thongtinlophocphan_textview_tengv.setText("Tên giảng viên: "+MainActivity.cs1.getString(1));
            sinhvien_thongtinlophocphan_textview_magv.setText("Mã giảng viên: "+MainActivity.cs1.getString(2));
            sinhvien_thongtinlophocphan_textview_sotinchi.setText("Số tín chỉ: "+MainActivity.cs1.getString(3));
            sinhvien_thongtinlophocphan_textview_lythuyet_tiet.setText("Tiết lý thuyết: "+MainActivity.cs1.getString(4));
            sinhvien_thongtinlophocphan_textview_thuchanhtiet.setText("Tiết thực hành: "+MainActivity.cs1.getString(5));
            sinhvien_thongtinlophocphan_textview_lythuyetbatdau.setText("Ngày bắt đầu lý thuyết: "+MainActivity.cs1.getString(6));
            sinhvien_thongtinlophocphan_textview_lythuyetketthuc.setText("Ngày kết thúc lý thuyết: "+MainActivity.cs1.getString(7));
            sinhvien_thongtinlophocphan_textview_thuchanhbatdau.setText("Ngày thực hành bắt đầu: "+MainActivity.cs1.getString(8));
            sinhvien_thongtinlophocphan_textview_thuchanhketthuc.setText("Ngày thực hành kết thúc: "+MainActivity.cs1.getString(9));
            sinhvien_thongtinlophocphan_textview_loaihocphan.setText("Loại học phần: "+MainActivity.cs1.getString(10));
            sinhvien_thongtinlophocphan_textview_tinhtrang.setText("Tình trạng học phần: "+MainActivity.cs1.getString(11));
            sinhvien_thongtinlophocphan_textview_phonghoc.setText("Phòng học: "+MainActivity.cs1.getString(12));
        }
    }
    public void Sinhvien_thongtinlophocphan_anhxa(){
        sinhvien_thongtinlophocphan_textview_mamonhoc = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_mamonhoc);
        sinhvien_thongtinlophocphan_textview_tenmonhoc = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_tenmonhoc);
        sinhvien_thongtinlophocphan_textview_tengv = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_tengv);
        sinhvien_thongtinlophocphan_textview_magv = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_magv);
        sinhvien_thongtinlophocphan_textview_loaihocphan = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_loaihocphan);
        sinhvien_thongtinlophocphan_textview_phonghoc = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_phonghoc);
        sinhvien_thongtinlophocphan_textview_sotinchi = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_sotinchi);
        sinhvien_thongtinlophocphan_textview_tinhtrang = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_tinhtrang);
        sinhvien_thongtinlophocphan_textview_lythuyetbatdau = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_lythuyetbatdau);
        sinhvien_thongtinlophocphan_textview_lythuyet_tiet = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_lythuyet_tiet);
        sinhvien_thongtinlophocphan_textview_lythuyetketthuc = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_lythuyetketthuc);
        sinhvien_thongtinlophocphan_textview_thuchanhbatdau = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_thuchanhbatdau);
        sinhvien_thongtinlophocphan_textview_thuchanhtiet = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_thuchanhtiet);
        sinhvien_thongtinlophocphan_textview_thuchanhketthuc = (TextView) findViewById(R.id.sinhvien_thongtinlophocphan_textview_thuchanhketthuc);
    }
}