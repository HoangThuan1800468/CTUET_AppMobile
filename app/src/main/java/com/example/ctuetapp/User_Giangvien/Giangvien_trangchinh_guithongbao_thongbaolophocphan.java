package com.example.ctuetapp.User_Giangvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;

import java.util.ArrayList;

public class Giangvien_trangchinh_guithongbao_thongbaolophocphan extends AppCompatActivity {
    EditText Giangvien_thongbaolophocphan_textbox_noidung;
    TextView Giangvien_thongbaolophocphan_textview_lop,Giangvien_thongbaolophocphan_textview_ngay;
    Button Giangvien_thongbaolophocphan_button_gui,Giangvien_thongbaolophocphan_button_huy;
    String a, b;
    ArrayList<String> ma=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giangvien_trangchinh_guithongbao_thongbaolophocphan);
        Giangvien_thongbaolophocphan_anhxa();

        a = getIntent().getStringExtra("a");
        b = getIntent().getStringExtra("b");
        Giangvien_thongbaolophocphan_textview_lop.setText("Mã lớp: "+a + "\n"+" Tên lớp: " + b);
        Giangvien_thongbaolophocphan_textview_ngay.setText("Ngày gửi: "+tg ());

        Giangvien_thongbaolophocphan_button_gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Giangvien_thongbaolophocphan_textbox_noidung.getText().toString().trim().equals("")) {
                    Toast.makeText(Giangvien_trangchinh_guithongbao_thongbaolophocphan.this, "Chưa nhập nội dung cho thông báo", Toast.LENGTH_SHORT).show();
                } else {
                    MainActivity.dulieu.themDuLieu("insert into ThongBao(ma_TB,mGv,ngay_gui,noi_dung, maMH) values ('" + taoma()
                            + "','" + Giangvien_trangchinh.magv1 + "','"+tg()+"','"+Giangvien_thongbaolophocphan_textbox_noidung.getText().toString()+"','"+a+"'   )  ");
                    Toast.makeText(Giangvien_trangchinh_guithongbao_thongbaolophocphan.this, "Đã gửi thông báo", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        Giangvien_thongbaolophocphan_button_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void Giangvien_thongbaolophocphan_anhxa(){
        Giangvien_thongbaolophocphan_textbox_noidung = (EditText) findViewById(R.id.Giangvien_thongbaolophocphan_textbox_noidung);
        Giangvien_thongbaolophocphan_textview_lop = (TextView) findViewById(R.id.Giangvien_thongbaolophocphan_textview_lop);
        Giangvien_thongbaolophocphan_textview_ngay = (TextView) findViewById(R.id.Giangvien_thongbaolophocphan_textview_ngay);
        Giangvien_thongbaolophocphan_button_gui = (Button) findViewById(R.id.Giangvien_thongbaolophocphan_button_gui);
        Giangvien_thongbaolophocphan_button_huy = (Button) findViewById(R.id.Giangvien_thongbaolophocphan_button_huy);
    }
    private String taoma(){
        String travema = "";
        ma.clear();
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select ma_TB from ThongBao ");
        while (MainActivity.cs1.moveToNext()) {
            ma.add(MainActivity.cs1.getString(0).substring(2));
        }
        try {
            String a = ma.get(ma.size() - 1);
            int b = Integer.parseInt(a);
            b = b+1;
            String c = "";
            if(b<10){
                c = "tb000000"+b;
            }else if(b>9 && b<100){
                c = "tb00000"+b;
            }else if(b>99 && b<1000){
                c = "tb0000"+b;
            }else if(b>999 && b<10000){
                c = "tb000"+b;
            }else if(b>9999 && b<100000){
                c = "tb00"+b;
            }else if(b>99999 && b<1000000){
                c = "tb0"+b;
            }else if(b>999999 && b<10000000){
                c = "tb"+b;
            }else if(b>999999 && b<10000000){
                Toast.makeText(Giangvien_trangchinh_guithongbao_thongbaolophocphan.this, "Đã dến giới hạn mã Thông báo", Toast.LENGTH_SHORT).show();
            }
            travema = c;
        }catch (Exception e){
            String a = "tb0000000";
            travema = a;
        }
        return travema;
    }

    private String tg (){
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int monthpls = today.month + 1;
        if(monthpls>12){
            monthpls = 1;
        }
        String tghientai = today.monthDay+"/"+monthpls+"/"+today.year ;
        return tghientai;
    }
}