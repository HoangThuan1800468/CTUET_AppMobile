package com.example.ctuetapp.User_Quanly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;

public class Quanly_trangchinh extends AppCompatActivity {
    //----------------------------------//
    Button quanly_trangchinh_button_quanlytaikhoan, quanly_trangchinh_button_quanlylophocphan,
            quanly_trangchinh_button_dangxuat;
    //----------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_trangchinh);
        quanly_trangchinh_anhxa();
        //----------------------------------------------------//

        String b = getIntent().getStringExtra("a");
        if(b.equals("admin")==false){
            quanly_trangchinh_button_quanlytaikhoan.setEnabled(false);
        }else{
            quanly_trangchinh_button_quanlytaikhoan.setEnabled(true);
        }
        quanly_trangchinh_button_quanlytaikhoan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Quanly_trangchinh.this, Quanly_trangchinh_quanlytaikhoan.class);
                startActivity(intent);
            }
        });

        quanly_trangchinh_button_quanlylophocphan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Quanly_trangchinh.this, Quanly_trangchinh_quanlylophocphan.class);
                startActivity(intent);
            }
        });
        quanly_trangchinh_button_dangxuat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        //----------------------------------------------------//
    }
    public void quanly_trangchinh_anhxa() {
        quanly_trangchinh_button_quanlytaikhoan = (Button) findViewById(R.id.quanly_trangchinh_button_quanlytaikhoan);
        quanly_trangchinh_button_quanlylophocphan = (Button) findViewById(R.id.quanly_trangchinh_button_quanlylophocphan);
        quanly_trangchinh_button_dangxuat = (Button) findViewById(R.id.quanly_trangchinh_button_dangxuat);
    }
}