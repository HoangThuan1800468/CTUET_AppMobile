package com.example.ctuetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ctuetapp.User_Giangvien.Giangvien_trangchinh;
import com.example.ctuetapp.User_Quanly.Quanly_dangnhap_quenmatkhau;
import com.example.ctuetapp.User_Quanly.Quanly_trangchinh;
import com.example.ctuetapp.User_Quanly.Quanly_trangchinh_quanlytaikhoan_taotaikhoan;
import com.example.ctuetapp.User_Quanly.Quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien;
import com.example.ctuetapp.User_Sinhvien.Sinhvien_trangchinh;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {
    public static database dulieu;
    public static Cursor cs, cs1,cs2,cs3,cs4,cs5,cs6,cs7,cs8;
    //----------------------------------//
    CheckBox main_checkbox_ghinhotaikhoan;
    Button main_button_dangnhap, main_button_quenmk;
    EditText main_texbox_tendangnhap, main_texbox_matkhau;
    //----------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_anhxa();


//---------------------------------------------data-----------------------------------------------------------------------------------------------
        dulieu = new database(this, "DoAn3.sqlite", null, 1);

        dulieu.themDuLieu("create table if not exists GiangVien(tenGv nvarchar(40), mGv character(7) primary key)");
        dulieu.themDuLieu("create table if not exists Quanly(tenQl nvarchar(40), mQl character(7) primary key)");
        dulieu.themDuLieu("create table if not exists LopHP(ma_MH character(9) primary key, ten_MH nvarchar(100), ten_GV nvarchar(50),ma_GV character(5), so_tc tinyint, tiet_LT character(5), tiet_TH character(5), ngay_LT_bd character(20), ngay_LT_kt character(20), ngay_TH_bd character(20), ngay_TH_kt character(20), ghi_chu text,hoc_ky character(15),loaiHP character(25),tinhtrangHP character(25),phonghoc character(4) )");
        dulieu.themDuLieu("create table if not exists LopHP_sinhvien(ma_MH character(9), mSv character(7),diemthuchanh float,diemgiuaky float,diemcuoiky float ) ");
        dulieu.themDuLieu("create table if not exists SinhVien(tenSv nvarchar(40), mSv character(7), lop nvarchar(40), ngay_sinh text, gioi_tinh nvarchar(5), khoa_hoc character(4), sdt character(10), dia_chi nvarchar(255), anh BLOB)");
        dulieu.themDuLieu("create table if not exists LopHP_lichhoc(ma_MH character(9), ngayhoc text , loai character(15) )");
        dulieu.themDuLieu("create table if not exists TaiKhoan(ten nvarchar(40), ma character(7), mk character(50), loai_TK nvarchar(40), mail character(50), tinh_trang character(7) )");
        dulieu.themDuLieu("create table if not exists ThongBao(ma_TB character(9),mGv character(7), ngay_gui text, noi_dung text, maMH character(9))");
        dulieu.themDuLieu("create table if not exists ghinho (maso character(9),mk character(50) )");

//---------------------------------------------data-----------------------------------------------------------------------------------------------
        loadghinho();
        main_texbox_matkhau.setText("");
        main_button_dangnhap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (main_texbox_tendangnhap.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, "Chưa nhập mã số", Toast.LENGTH_SHORT).show();
                } else if (main_texbox_matkhau.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, "Chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (main_texbox_tendangnhap.getText().toString().trim().equals("Admin") && getMD5(main_texbox_matkhau.getText().toString().trim()).equals("21232f297a57a5a743894a0e4a801fc3")) {
                    Toast.makeText(MainActivity.this, "Quản lý", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Quanly_trangchinh.class);
                    intent.putExtra("a","admin");
                    startActivity(intent);
                }else {
                    DangNhap(main_texbox_tendangnhap.getText().toString().trim(),getMD5( main_texbox_matkhau.getText().toString().trim()));
                }
            }
        });
        main_button_quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Quanly_dangnhap_quenmatkhau.class);
                startActivity(intent);
            }
        });

    }

    public void main_anhxa() {
        main_button_dangnhap = (Button) findViewById(R.id.main_button_dangnhap);
        main_texbox_tendangnhap = (EditText) findViewById(R.id.main_texbox_tendangnhap);
        main_texbox_matkhau = (EditText) findViewById(R.id.main_texbox_matkhau);
        main_button_quenmk = (Button) findViewById(R.id.main_button_quenmk);
        main_checkbox_ghinhotaikhoan = (CheckBox) findViewById(R.id.main_checkbox_ghinhotaikhoan);
    }

    public void DangNhap(String ms, String matkhau){  ///HÀM ĐĂNG NHẬP
        cs = dulieu.layDuLieu("select ma from TaiKhoan where ma = '"+ms+"'  ");
        String a ="";
        while (cs.moveToNext()){
            a = cs.getString(0);
        }
        if(a.equals("")){
            Toast.makeText(MainActivity.this, "Không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
        }else{
            cs1 = dulieu.layDuLieu("select mk from TaiKhoan where ma = '"+ms+"'  ");
            while (cs1.moveToNext()){
                a = cs1.getString(0);
            }
            if(a.equals(matkhau.trim())==false){
                Toast.makeText(MainActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
            }else{
                cs2 = dulieu.layDuLieu("select tinh_trang from TaiKhoan where ma = '"+ms+"'  ");
                while (cs2.moveToNext()){
                    a = cs2.getString(0);
                }
                if(a.equals("Active")==false){
                    Toast.makeText(MainActivity.this, "Tài khoản đang bị khóa", Toast.LENGTH_SHORT).show();
                }else{
                    cs3 = dulieu.layDuLieu("select loai_TK from TaiKhoan where ma = '"+ms+"'  ");
                    while (cs3.moveToNext()){
                        a = cs3.getString(0);
                    }
                    if(main_checkbox_ghinhotaikhoan.isChecked()==true){
                        dulieu.themDuLieu("DELETE FROM ghinho  ");
                        dulieu.themDuLieu("insert into ghinho(maso,mk) values('"+ms+"', '"+matkhau+"') ");
                    }else{
                        dulieu.themDuLieu("DELETE FROM ghinho  ");
                    }
                    if(a.equals("Sinh viên")){
                        Toast.makeText(MainActivity.this, "Sinh viên", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Sinhvien_trangchinh.class);
                        intent.putExtra("a",ms.trim());
                        startActivity(intent);
                    }else if(a.equals("Giảng viên")){
                        Toast.makeText(MainActivity.this, "Giảng viên", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Giangvien_trangchinh.class);
                        intent.putExtra("a",ms.trim());
                        startActivity(intent);
                    }else if(a.equals("Quản lý")){
                        Toast.makeText(MainActivity.this, "Quản lý", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Quanly_trangchinh.class);
                        intent.putExtra("a",ms.trim());
                        startActivity(intent);
                    }
                }
            }
        }
    }
    ///mã hóa mật khẩu
    public static String getMD5(String pass){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(pass.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    private void loadghinho(){
        cs4 = dulieu.layDuLieu("select maso, mk from ghinho  ");
        String a="";
        String b ="";
        while (cs4.moveToNext()){
            a= cs4.getString(0);
            b =cs4.getString(1);
        }
        if (a.equals("")){
            main_checkbox_ghinhotaikhoan.setChecked(false);
        }else {
            main_texbox_tendangnhap.setText(a);
            main_texbox_matkhau.setText(b);
            main_checkbox_ghinhotaikhoan.setChecked(true);
        }
    }
    public interface OnBackPressedListener {
        void onBackPressed();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

        // Tao su kien ket thuc app
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startActivity(startMain);
        finish();
    }
}