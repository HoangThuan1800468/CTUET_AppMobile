package com.example.ctuetapp.User_Quanly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ctuetapp.R;

public class Quanly_trangchinh_quanlytaikhoan extends AppCompatActivity {
    //----------------------------------//
    Button quanly_trangchinh_quanlytaikhoan_button_taotaikhoan,
            quanly_trangchinh_quanlytaikhoan_button_danhsachtaikhoan,
            quanly_trangchinh_quanlytaikhoan_button_danhsachxincaplaimatkhau;
    //----------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_trangchinh_quanlytaikhoan);
        quanly_trangchinh_quanlytaikhoan_anhxa();
        //----------------------------------//
        quanly_trangchinh_quanlytaikhoan_button_taotaikhoan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Quanly_trangchinh_quanlytaikhoan.this, Quanly_trangchinh_quanlytaikhoan_taotaikhoan.class);
                startActivity(intent);
                finish();
            }
        });

        quanly_trangchinh_quanlytaikhoan_button_danhsachtaikhoan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Quanly_trangchinh_quanlytaikhoan.this, Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.class);
                startActivity(intent);
                finish();
            }
        });

        quanly_trangchinh_quanlytaikhoan_button_danhsachxincaplaimatkhau.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Quanly_trangchinh_quanlytaikhoan.this, Quanly_trangchinh_quanlytaikhoan_danhsachcaplaimatkhau.class);
                startActivity(intent);
                finish();
            }
        });
        //----------------------------------//
    }
    public void quanly_trangchinh_quanlytaikhoan_anhxa() {
        quanly_trangchinh_quanlytaikhoan_button_taotaikhoan = (Button) findViewById(R.id.quanly_trangchinh_quanlytaikhoan_button_taotaikhoan);
        quanly_trangchinh_quanlytaikhoan_button_danhsachtaikhoan = (Button) findViewById(R.id.quanly_trangchinh_quanlytaikhoan_button_danhsachtaikhoan);
        quanly_trangchinh_quanlytaikhoan_button_danhsachxincaplaimatkhau = (Button) findViewById(R.id.quanly_trangchinh_quanlytaikhoan_button_danhsachxincaplaimatkhau);
    }
}