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

public class Quanly_dangnhap_quenmatkhau extends AppCompatActivity {
    //----------------------------------//
    Button quenmatkhau_button_xacnhan;
    EditText quenmatkhau_textbox_tendangnhap, quenmatkhau_textbox_nhapmatkhaumoi;
    TextView quenmatkhau_textview_thongbao;
    //----------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_dangnhap_quenmatkhau);
        quenmatkhau_anhxa();
        quenmatkhau_button_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quenmatkhau_textbox_tendangnhap.getText().toString().trim().equals("")){
                    Toast.makeText(Quanly_dangnhap_quenmatkhau.this, "Chưa mập mã", Toast.LENGTH_SHORT).show();
                }else if(quenmatkhau_textbox_tendangnhap.getText().toString().trim().length()!=7){
                    Toast.makeText(Quanly_dangnhap_quenmatkhau.this, "Mã sai định dạng", Toast.LENGTH_SHORT).show();
                }else if(ktma(quenmatkhau_textbox_tendangnhap.getText().toString().trim())==false){
                    Toast.makeText(Quanly_dangnhap_quenmatkhau.this, "Không tìm thấy mã đăng nhập trong hệ thống", Toast.LENGTH_SHORT).show();
                }else if(quenmatkhau_textbox_nhapmatkhaumoi.getText().toString().trim().equals("")){
                    Toast.makeText(Quanly_dangnhap_quenmatkhau.this, "Chưa nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
                }else {
                    MainActivity.dulieu.themDuLieu("update TaiKhoan set tinh_trang = 'NOTACTV', mk = '"+getMD5(quenmatkhau_textbox_nhapmatkhaumoi.getText().toString().trim())+"' where ma = '"+quenmatkhau_textbox_tendangnhap.getText().toString().trim()+"'  ");
                    quenmatkhau_textview_thongbao.setText("Tài khoản của bạn đã tạm khóa! Gửi mail xin cấp lại mật khẩu tới phòng quản lý sinh viên để xác nhận mở khóa tài khoản!");
                }
            }
        });
    }
    public void quenmatkhau_anhxa() {
        quenmatkhau_button_xacnhan = (Button) findViewById(R.id.quenmatkhau_button_xacnhan);
        quenmatkhau_textbox_tendangnhap = (EditText) findViewById(R.id.quenmatkhau_textbox_tendangnhap);
        quenmatkhau_textbox_nhapmatkhaumoi = (EditText) findViewById(R.id.quenmatkhau_textbox_nhapmatkhaumoi);
        quenmatkhau_textview_thongbao =  (TextView) findViewById(R.id.quenmatkhau_textview_thongbao);
    }
    private boolean ktma(String a){
        String c ="";
        boolean b= true;
        MainActivity.cs = MainActivity.dulieu.layDuLieu("select ma from TaiKhoan where ma = '"+a+"' ");
        while (MainActivity.cs.moveToNext()){
            c = MainActivity.cs.getString(0);
        }
        if(c.equals("")){
            b = false;
        }else{
            b=true;
        }
        return b;
    }
}