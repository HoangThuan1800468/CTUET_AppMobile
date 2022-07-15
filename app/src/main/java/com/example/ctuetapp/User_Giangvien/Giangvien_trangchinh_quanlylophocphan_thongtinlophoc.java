package com.example.ctuetapp.User_Giangvien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;
import com.example.ctuetapp.User_Quanly.Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Giangvien_trangchinh_quanlylophocphan_thongtinlophoc extends AppCompatActivity {
    TextView Giangvien_thongtinlophoc_textview_mamonhoc,Giangvien_thongtinlophoc_textview_tenmonhoc,
            Giangvien_thongtinlophoc_textview_sotinchi,Giangvien_thongtinlophoc_textview_loaihocphan,
            Giangvien_thongtinlophoc_textview_tinhtrang,Giangvien_thongtinlophoc_textview_phonghoc,
            Giangvien_thongtinlophoc_textview_tietlythuyet,Giangvien_thongtinlophoc_textview_thoigianlythuyetbatdau,
            Giangvien_thongtinlophoc_textview_thoigianlythuyetketthuc,Giangvien_thongtinlophoc_textview_tietthuchanh,
            Giangvien_thongtinlophoc_textview_thoigianthuchanhbatdau,Giangvien_thongtinlophoc_textview_thoigianthuchanhketthuc;
    EditText Giangvien_thongtinlophoc_textbox_ghichu;
    Button Giangvien_thongtinlophoc_button_ketthuc;
    FloatingActionButton Giangvien_thongtinlophoc_button_luu,Giangvien_thongtinlophoc_button_danhsachsinhvien;

    String malophoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giangvien_trangchinh_quanlylophocphan_thongtinlophoc);
        Giangvien_quanlylophocphan_anhxa();
        malophoc= getIntent().getStringExtra("a");
        if(kt_tinhtrang(malophoc)==false){
            Giangvien_thongtinlophoc_button_ketthuc.setEnabled(false);
        }else{
            Giangvien_thongtinlophoc_button_ketthuc.setEnabled(true);
        }
        //---------------------------------------------------------------------------
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select ten_MH,so_tc,tiet_LT,tiet_TH,ngay_LT_bd,ngay_LT_kt,ngay_TH_bd,ngay_TH_kt,ghi_chu,loaiHP,tinhtrangHP,phonghoc from LopHP where ma_MH = '"+malophoc+"' ");
        Giangvien_thongtinlophoc_textview_mamonhoc.setText("Mã môn học:"+malophoc);
        while (MainActivity.cs1.moveToNext()) {
            Giangvien_thongtinlophoc_textview_tenmonhoc.setText("Tên môn học: "+MainActivity.cs1.getString(0));
            Giangvien_thongtinlophoc_textview_sotinchi.setText("Số tín chỉ: "+MainActivity.cs1.getString(1));
            Giangvien_thongtinlophoc_textview_tietlythuyet.setText("Lý thuyết tiết: "+MainActivity.cs1.getString(2));
            Giangvien_thongtinlophoc_textview_tietthuchanh.setText("Thực hành tiết: "+MainActivity.cs1.getString(3));
            Giangvien_thongtinlophoc_textview_thoigianlythuyetbatdau.setText("Từ ngày: "+MainActivity.cs1.getString(4));
            Giangvien_thongtinlophoc_textview_thoigianlythuyetketthuc.setText("Đến ngày: "+MainActivity.cs1.getString(5));
            Giangvien_thongtinlophoc_textview_thoigianthuchanhbatdau.setText("Từ ngày: "+MainActivity.cs1.getString(6));
            Giangvien_thongtinlophoc_textview_thoigianthuchanhketthuc.setText("Đến ngày: "+MainActivity.cs1.getString(7));
            Giangvien_thongtinlophoc_textbox_ghichu.setText(MainActivity.cs1.getString(8));
            Giangvien_thongtinlophoc_textview_loaihocphan.setText("Loại học phần: "+MainActivity.cs1.getString(9));
            Giangvien_thongtinlophoc_textview_tinhtrang.setText("Tình trạng học phần: "+MainActivity.cs1.getString(10));
            Giangvien_thongtinlophoc_textview_phonghoc.setText("Phòng học: "+MainActivity.cs1.getString(11));
        }
        //---------------------------------------------------------------------------
        Giangvien_thongtinlophoc_button_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.dulieu.themDuLieu("update LopHP set ghi_chu = '" + Giangvien_thongtinlophoc_textbox_ghichu.getText().toString() + "' where ma_MH = '"+malophoc+"' ");
                Toast.makeText(Giangvien_trangchinh_quanlylophocphan_thongtinlophoc.this, "Đã cập nhật ghi chú", Toast.LENGTH_SHORT).show();
            }
        });
        //---------------------------------------------------------------------------
        Giangvien_thongtinlophoc_button_danhsachsinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Giangvien_trangchinh_quanlylophocphan_thongtinlophoc.this,
                        Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.class);
                myIntent.putExtra("a",malophoc);
                startActivity(myIntent);
            }
        });
        //---------------------------------------------------------------------------
        Giangvien_thongtinlophoc_button_ketthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kt_ketthuc(malophoc) == false) {
                    Toast.makeText(Giangvien_trangchinh_quanlylophocphan_thongtinlophoc.this, "Có sinh viên chưa được nhập điểm nên không thể kết thúc học phần!", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder b = new AlertDialog.Builder(Giangvien_trangchinh_quanlylophocphan_thongtinlophoc.this);
                    //Thiết lập tiêu đề
                    b.setTitle("Xác nhận!");
                    b.setMessage("Kết thúc lớp học phần?");
                    // Nút Ok
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.dulieu.themDuLieu("update LopHP set tinhtrangHP = 'Đã kết thúc' where ma_MH = '" + malophoc + "' ");
                            Toast.makeText(Giangvien_trangchinh_quanlylophocphan_thongtinlophoc.this, "Đã cập nhật tình trạng lớp học phần", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            finish();
                            Intent myIntent = new Intent(Giangvien_trangchinh_quanlylophocphan_thongtinlophoc.this, Giangvien_trangchinh_quanlylophocphan_thongtinlophoc.class);
                            myIntent.putExtra("a", malophoc);
                            startActivity(myIntent);
                        }
                    });
                    //Nút Cancel
                    b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    //Tạo dialog
                    AlertDialog al = b.create();
                    //Hiển thị
                    al.show();
                }
            }
        });
        //---------------------------------------------------------------------------
    }
    public void Giangvien_quanlylophocphan_anhxa(){
        Giangvien_thongtinlophoc_textview_mamonhoc = (TextView) findViewById(R.id.Giangvien_thongtinlophoc_textview_mamonhoc);
        Giangvien_thongtinlophoc_textview_tenmonhoc = (TextView) findViewById(R.id.Giangvien_thongtinlophoc_textview_tenmonhoc);
        Giangvien_thongtinlophoc_textview_sotinchi = (TextView) findViewById(R.id.Giangvien_thongtinlophoc_textview_sotinchi);
        Giangvien_thongtinlophoc_textview_loaihocphan = (TextView) findViewById(R.id.Giangvien_thongtinlophoc_textview_loaihocphan);
        Giangvien_thongtinlophoc_textview_tinhtrang = (TextView) findViewById(R.id.Giangvien_thongtinlophoc_textview_tinhtrang);
        Giangvien_thongtinlophoc_textview_phonghoc = (TextView) findViewById(R.id.Giangvien_thongtinlophoc_textview_phonghoc);
        Giangvien_thongtinlophoc_textview_tietlythuyet = (TextView) findViewById(R.id.Giangvien_thongtinlophoc_textview_tietlythuyet);
        Giangvien_thongtinlophoc_textview_thoigianlythuyetbatdau = (TextView) findViewById(R.id.Giangvien_thongtinlophoc_textview_thoigianlythuyetbatdau);
        Giangvien_thongtinlophoc_textview_thoigianlythuyetketthuc = (TextView) findViewById(R.id.Giangvien_thongtinlophoc_textview_thoigianlythuyetketthuc);
        Giangvien_thongtinlophoc_textview_tietthuchanh = (TextView) findViewById(R.id.Giangvien_thongtinlophoc_textview_tietthuchanh);
        Giangvien_thongtinlophoc_textview_thoigianthuchanhbatdau = (TextView) findViewById(R.id.Giangvien_thongtinlophoc_textview_thoigianthuchanhbatdau);
        Giangvien_thongtinlophoc_textview_thoigianthuchanhketthuc = (TextView) findViewById(R.id.Giangvien_thongtinlophoc_textview_thoigianthuchanhketthuc);

        Giangvien_thongtinlophoc_textbox_ghichu = (EditText) findViewById(R.id.Giangvien_thongtinlophoc_textbox_ghichu);
        Giangvien_thongtinlophoc_button_ketthuc = (Button) findViewById(R.id.Giangvien_thongtinlophoc_button_ketthuc);

        Giangvien_thongtinlophoc_button_luu = (FloatingActionButton) findViewById(R.id.Giangvien_thongtinlophoc_button_luu);
        Giangvien_thongtinlophoc_button_danhsachsinhvien = (FloatingActionButton) findViewById(R.id.Giangvien_thongtinlophoc_button_danhsachsinhvien);
    }
    public boolean kt_ketthuc(String malophoc){
        boolean kt=false;
        if(Giangvien_bangdiemsinhvien.kt_thuchanh(malophoc)==true){
            MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select diemthuchanh,diemgiuaky,diemcuoiky  from LopHP_sinhvien where   ma_MH = '"+malophoc+"' ");
            while (MainActivity.cs1.moveToNext()){
                if(MainActivity.cs1.getString(0)==null||MainActivity.cs1.getString(1)==null||MainActivity.cs1.getString(2)==null) {
                    kt=false;
                    break;
                }else{
                    kt=true;
                }
            }
        }else{
            MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select diemgiuaky,diemcuoiky  from LopHP_sinhvien where   ma_MH = '"+malophoc+"' ");
            while (MainActivity.cs1.moveToNext()){
                if(MainActivity.cs1.getString(0)==null||MainActivity.cs1.getString(1)==null) {
                    kt=false;
                    break;
                }else{
                    kt=true;
                }
            }
        }

        return kt;
    }
    public static boolean kt_tinhtrang(String malophoc) {
        boolean kt=false;
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select tinhtrangHP  from LopHP where ma_MH = '"+malophoc+"' ");
        while (MainActivity.cs1.moveToNext()){
            if(MainActivity.cs1.getString(0).equals("Đã kết thúc")) {
                kt=false;
            }else{
                kt=true;
            }
        }
        return kt;
    }
}