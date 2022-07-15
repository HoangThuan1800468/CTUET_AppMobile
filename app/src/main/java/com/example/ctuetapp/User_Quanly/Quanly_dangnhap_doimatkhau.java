package com.example.ctuetapp.User_Quanly;

import static com.example.ctuetapp.MainActivity.getMD5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;

public class Quanly_dangnhap_doimatkhau extends AppCompatActivity {
    TextView Quanly_doimatkhau_textview_maso;
    EditText Quanly_doimatkhau_textbox_matkhaucu,Quanly_doimatkhau_textbox_nhapmatkhaumoi,
            Quanly_doimatkhau_textbox_nhaplaimatkhaumoi;
    Button Quanly_doimatkhau_button_huy,Quanly_doimatkhau_button_xacnhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_dangnhap_doimatkhau);
        Quanly_doimatkhau_anhxa();

        Quanly_doimatkhau_textview_maso.setText(getIntent().getStringExtra("a")+" - "+ten(getIntent().getStringExtra("a")));

        Quanly_doimatkhau_button_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Quanly_doimatkhau_textbox_matkhaucu.getText().toString().trim().equals("")){
                    Toast.makeText(Quanly_dangnhap_doimatkhau.this, "Chưa nhập mật khẩu cũ", Toast.LENGTH_SHORT).show();
                }else if(Quanly_doimatkhau_textbox_nhapmatkhaumoi.getText().toString().trim().equals("")){
                    Toast.makeText(Quanly_dangnhap_doimatkhau.this, "Chưa nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
                }else if(Quanly_doimatkhau_textbox_nhaplaimatkhaumoi.getText().toString().trim().equals("")){
                    Toast.makeText(Quanly_dangnhap_doimatkhau.this, "Chưa nhập lại mật khẩu mới", Toast.LENGTH_SHORT).show();
                }else if(Quanly_doimatkhau_textbox_nhapmatkhaumoi.getText().toString().trim().equals(Quanly_doimatkhau_textbox_nhaplaimatkhaumoi.getText().toString().trim())==false){
                    Toast.makeText(Quanly_dangnhap_doimatkhau.this, "Mật khẩu nhập lại không trùng khớp", Toast.LENGTH_SHORT).show();
                }else if(ktma(getMD5(Quanly_doimatkhau_textbox_matkhaucu.getText().toString().trim()))==false){
                    Toast.makeText(Quanly_dangnhap_doimatkhau.this, "Mật khẩu cũ không trùng khớp", Toast.LENGTH_SHORT).show();
                }else {
                    String []a = Quanly_doimatkhau_textview_maso.getText().toString().split(" - ");
                    MainActivity.dulieu.themDuLieu("update TaiKhoan set mk = '" + getMD5(Quanly_doimatkhau_textbox_nhaplaimatkhaumoi.getText().toString().trim()) + "' where ma = '"+a[0]+"' ");
                    Toast.makeText(Quanly_dangnhap_doimatkhau.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        Quanly_doimatkhau_button_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void Quanly_doimatkhau_anhxa(){
        Quanly_doimatkhau_textview_maso = (TextView) findViewById(R.id.Quanly_doimatkhau_textview_maso);
        Quanly_doimatkhau_textbox_matkhaucu = (EditText) findViewById(R.id.Quanly_doimatkhau_textbox_matkhaucu);
        Quanly_doimatkhau_textbox_nhapmatkhaumoi = (EditText) findViewById(R.id.Quanly_doimatkhau_textbox_nhapmatkhaumoi);
        Quanly_doimatkhau_textbox_nhaplaimatkhaumoi = (EditText) findViewById(R.id.Quanly_doimatkhau_textbox_nhaplaimatkhaumoi);
        Quanly_doimatkhau_button_huy = (Button) findViewById(R.id.Quanly_doimatkhau_button_huy);
        Quanly_doimatkhau_button_xacnhan = (Button) findViewById(R.id.Quanly_doimatkhau_button_xacnhan);
    }
    private String ten(String a){
        String b = "";
        MainActivity.cs = MainActivity.dulieu.layDuLieu("select ten from TaiKhoan where ma = '"+a+"' ");
        while (MainActivity.cs.moveToNext()){
            b = MainActivity.cs.getString(0);
        }
        return b;
    }
    private boolean ktma(String c){
        boolean b = true;
        String d = "";
        String []a = Quanly_doimatkhau_textview_maso.getText().toString().split(" - ");
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select mk from TaiKhoan where ma = '"+a[0]+"' ");
        while (MainActivity.cs1.moveToNext()){
            d = MainActivity.cs1.getString(0);
        }
        if(d.equals(c)){
            b = true;
        }else{
            b=false;
        }
        return b;
    }
}