package com.example.ctuetapp.User_Quanly;

import static com.example.ctuetapp.MainActivity.getMD5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;
import com.example.ctuetapp.database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Quanly_trangchinh_quanlytaikhoan_taotaikhoan extends AppCompatActivity {
    //----------------------------------//
    Spinner taotaikhoan_spinner_loaitaikhoan;
    EditText taotaikhoan_textbox_tentaikhoan,taotaikhoan_textbox_mataikhoan,
            taotaikhoan_textbox_matkhau,taotaikhoan_textbox_mail;
    Button taotaikhoan_button_taomail;
    FloatingActionButton taotaikhoan_button_next;
    //----------------------------------//
    String[] ds_loai = {"Chưa chọn", "Sinh viên", "Giảng viên", "Quản lý"};
    ArrayList<String> ma = new ArrayList<>();
    //----------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_trangchinh_quanlytaikhoan_taotaikhoan);
        taotaikhoan_anhxa();

        //----------------------------------//

        MainActivity.dulieu = new database(this, "DoAn3.sqlite", null, 1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                ds_loai);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.taotaikhoan_spinner_loaitaikhoan.setAdapter(adapter);
        taotaikhoan_spinner_loaitaikhoan.setSelection(0);

        //----------------------------------//

        taotaikhoan_spinner_loaitaikhoan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (taotaikhoan_spinner_loaitaikhoan.getSelectedItem().toString().equals("Chưa chọn")) {
                    taotaikhoan_textbox_mail.setText("");
                    taotaikhoan_textbox_tentaikhoan.setText("");
                    taotaikhoan_textbox_mataikhoan.setText("");
                    taotaikhoan_textbox_matkhau.setText("");
                } else if (taotaikhoan_spinner_loaitaikhoan.getSelectedItem().toString().equals("Giảng viên") || taotaikhoan_spinner_loaitaikhoan.getSelectedItem().toString().equals("Quản lý")) {
                    taotaikhoan_textbox_mataikhoan.setText(taoma(taotaikhoan_spinner_loaitaikhoan.getSelectedItem().toString()));
                    taotaikhoan_button_next.setImageResource(R.drawable.ic_action_save);
                    taotaikhoan_textbox_mataikhoan.setEnabled(false);
                    taotaikhoan_textbox_mail.setText("");
                } else {
                    taotaikhoan_textbox_mataikhoan.setText(taoma(taotaikhoan_spinner_loaitaikhoan.getSelectedItem().toString()));
                    taotaikhoan_button_next.setImageResource(R.drawable.ic_next);
                    taotaikhoan_textbox_mataikhoan.setEnabled(true);
                    taotaikhoan_textbox_mail.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //----------------------------------//

        taotaikhoan_textbox_tentaikhoan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                taotaikhoan_textbox_mail.setText("");
            }
        });
        taotaikhoan_textbox_mataikhoan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                taotaikhoan_textbox_mail.setText("");
            }
        });

        //----------------------------------//

        taotaikhoan_button_taomail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taotaikhoan_spinner_loaitaikhoan.getSelectedItem().toString().equals("Chưa chọn")) {
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Chưa chọn loại tài khoản", Toast.LENGTH_SHORT).show();
                } else if (taotaikhoan_textbox_tentaikhoan.getText().toString().equals("")) {
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Chưa nhập Họ Tên đầy đủ", Toast.LENGTH_SHORT).show();
                } else if (taotaikhoan_textbox_mataikhoan.getText().toString().trim().equals("")) {
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Chưa nhập mã số", Toast.LENGTH_SHORT).show();
                } else if (taotaikhoan_textbox_mataikhoan.getText().toString().trim().length() < 7 ||
                            taotaikhoan_textbox_mataikhoan.getText().toString().trim().length() > 7) {
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Mã số phải có độ dài bằng 7", Toast.LENGTH_SHORT).show();
                } else {
                    String a = "";
                    a = VNCharacterUtils.removeAccent(taotaikhoan_textbox_tentaikhoan.getText().toString());
                    a = a.replaceAll(" ", "");
                    if (taotaikhoan_spinner_loaitaikhoan.getSelectedItem().toString().equals("Sinh viên")) {
                        taotaikhoan_textbox_mail.setText(a + "" + taotaikhoan_textbox_mataikhoan.getText().toString() + "@student.ctuet.edu.vn");
                    } else if (taotaikhoan_spinner_loaitaikhoan.getSelectedItem().toString().equals("Giảng viên") ||
                                taotaikhoan_spinner_loaitaikhoan.getSelectedItem().toString().equals("Quản lý")) {
                        taotaikhoan_textbox_mail.setText(a + "" + taotaikhoan_textbox_mataikhoan.getText().toString() + "@edu.vn");
                    }

                }
            }
        });

        //----------------------------------//

        taotaikhoan_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loai = taotaikhoan_spinner_loaitaikhoan.getSelectedItem().toString();
                if (taotaikhoan_spinner_loaitaikhoan.getSelectedItem().toString().equals("Chưa chọn")) {
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Chưa chọn loại tài khoản", Toast.LENGTH_SHORT).show();
                } else if (taotaikhoan_textbox_tentaikhoan.getText().toString().trim().equals("")) {
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Chưa nhập Họ Tên đầy đủ", Toast.LENGTH_SHORT).show();
                } else if (taotaikhoan_textbox_mataikhoan.getText().toString().trim().equals("")) {
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Chưa nhập mã số", Toast.LENGTH_SHORT).show();
                } else if (taotaikhoan_textbox_mataikhoan.getText().toString().trim().length() < 7 || taotaikhoan_textbox_mataikhoan.getText().toString().trim().length() > 7) {
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Mã số phải có độ dài bằng 7", Toast.LENGTH_SHORT).show();
                } else if (taotaikhoan_textbox_matkhau.getText().toString().trim().equals("")) {
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (taotaikhoan_textbox_mail.getText().toString().trim().equals("")) {
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Chưa nhập mail", Toast.LENGTH_SHORT).show();
                }else if (loai.equals("Quản lý")) {
                    MainActivity.dulieu.themDuLieu("insert into TaiKhoan(ten,ma,mk,loai_TK,mail,tinh_trang) values ('" + taotaikhoan_textbox_tentaikhoan.getText().toString().trim()
                            + "','" + taotaikhoan_textbox_mataikhoan.getText().toString().trim() + "','" + getMD5(taotaikhoan_textbox_matkhau.getText().toString().trim())  + "','"
                            + loai.trim() + "','" + taotaikhoan_textbox_mail.getText().toString().trim() + "', 'Active'  ) ");
                    MainActivity.dulieu.themDuLieu("insert into Quanly(tenQl,mQl) values ('" + taotaikhoan_textbox_tentaikhoan.getText().toString().trim()
                            + "','" + taotaikhoan_textbox_mataikhoan.getText().toString().trim() + "' ) ");
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Đã tạo tài khoản " + loai, Toast.LENGTH_SHORT).show();
                    clearfrom(loai);
                }else  if (loai.equals("Giảng viên")) {
                    MainActivity.dulieu.themDuLieu("insert into TaiKhoan(ten,ma,mk,loai_TK,mail,tinh_trang) values ('" + taotaikhoan_textbox_tentaikhoan.getText().toString().trim()
                            + "','" + taotaikhoan_textbox_mataikhoan.getText().toString().trim() + "','" +getMD5( taotaikhoan_textbox_matkhau.getText().toString().trim()) + "','" + loai.trim() + "','"
                            + taotaikhoan_textbox_mail.getText().toString().trim() + "', 'Active'  ) ");
                    MainActivity.dulieu.themDuLieu("insert into GiangVien(tenGv,mGv) values ('" + taotaikhoan_textbox_tentaikhoan.getText().toString().trim() + "','"
                            + taotaikhoan_textbox_mataikhoan.getText().toString().trim() + "' ) ");
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Đã tạo tài khoản " + loai, Toast.LENGTH_SHORT).show();
                    clearfrom(loai);
                } else if (loai.equals("Sinh viên")) {
                    if(kt(taotaikhoan_textbox_mataikhoan.getText().toString())==false){
                        Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Mã số phải là ký tự số", Toast.LENGTH_SHORT).show();
                    }else{
                        ma.clear();
                        MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select ma from TaiKhoan where loai_TK = 'Sinh viên' ");
                        while (MainActivity.cs2.moveToNext()) {
                            ma.add(MainActivity.cs2.getString(0));
                        }
                        boolean a = true;
                        for(int i = 0; i<ma.size();i++){
                            String b = ma.get(i);
                            if(taotaikhoan_textbox_mataikhoan.getText().toString().trim().equals(b)==true){
                                Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Mã số sinh viên bị trùng nhau", Toast.LENGTH_SHORT).show();
                                a=false;
                                break;
                            }
                        }
                        if(a == true) {
                            Intent intent = new Intent(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this,
                                    Quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien.class);
                            intent.putExtra("ten", taotaikhoan_textbox_tentaikhoan.getText().toString());
                            intent.putExtra("masv", taotaikhoan_textbox_mataikhoan.getText().toString());
                            intent.putExtra("mail", taotaikhoan_textbox_mail.getText().toString());
                            intent.putExtra("matkhau", taotaikhoan_textbox_matkhau.getText().toString());
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }
        });

        //----------------------------------//

    }

    public void taotaikhoan_anhxa() {
        taotaikhoan_spinner_loaitaikhoan = (Spinner) findViewById(R.id.taotaikhoan_spinner_loaitaikhoan) ;
        taotaikhoan_textbox_tentaikhoan = (EditText) findViewById(R.id.taotaikhoan_textbox_tentaikhoan);
        taotaikhoan_textbox_mataikhoan = (EditText) findViewById(R.id.taotaikhoan_textbox_mataikhoan);
        taotaikhoan_textbox_matkhau = (EditText) findViewById(R.id.taotaikhoan_textbox_matkhau);
        taotaikhoan_textbox_mail = (EditText) findViewById(R.id.taotaikhoan_textbox_mail);
        taotaikhoan_button_taomail = (Button) findViewById(R.id.taotaikhoan_button_taomail);
        taotaikhoan_button_next = (FloatingActionButton) findViewById(R.id.taotaikhoan_button_next);
    }

    public String taoma(String loai) {
        String travema = "";
        MainActivity.dulieu = new database(this, "DoAn3.sqlite", null, 1);
        if(loai.equals("Quản lý")){
            ma.clear();
            MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select ma from TaiKhoan where loai_TK = 'Quản lý' ");
            while (MainActivity.cs1.moveToNext()) {
                ma.add(MainActivity.cs1.getString(0).substring(2));
            }
            try {
                String a = ma.get(ma.size() - 1);
                int b = Integer.parseInt(a);
                b = b+1;
                String c = "";
                if(b<10){
                    c = "ql0000"+b;
                }else if(b>9 && b<100){
                    c = "ql000"+b;
                }else if(b>99 && b<1000){
                    c = "ql00"+b;
                }else if(b>999 && b<10000){
                    c = "ql0"+b;
                }else if(b>9999 && b<100000){
                    c = "ql"+b;
                }else if(b>99999 && b<1000000){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Đã dến giới hạn mã Quản lý", Toast.LENGTH_SHORT).show();
                }
                travema = c;
            }catch (Exception e){
                String a = "ql00000";
                travema = a;
            }

        }else if(loai.equals("Giảng viên")){
            ma.clear();
            MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select ma from TaiKhoan where loai_TK = 'Giảng viên' ");
            while (MainActivity.cs1.moveToNext()) {
                ma.add(MainActivity.cs1.getString(0).substring(2));
            }
            try {
                String a = ma.get(ma.size() - 1);
                int b = Integer.parseInt(a);
                b = b+1;
                String c = "";
                if(b<10){
                    c = "gv0000"+b;
                }else if(b>9 && b<100){
                    c = "gv000"+b;
                }else if(b>99 && b<1000){
                    c = "gv00"+b;
                }else if(b>999 && b<10000){
                    c = "gv0"+b;
                }else if(b>9999 && b<100000){
                    c = "gv"+b;
                }else if(b>99999 && b<1000000){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Đã dến giới hạn mã Giảng viên", Toast.LENGTH_SHORT).show();
                }
                travema = c;
            }catch (Exception e){
                String a = "gv00000";
                travema = a;
            }

        }else if(loai.equals("Sinh viên")){
            ma.clear();
            MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select ma from TaiKhoan where loai_TK = 'Sinh viên' ");
            while (MainActivity.cs1.moveToNext()) {
                ma.add(MainActivity.cs1.getString(0).substring(2));
            }
            String date = new SimpleDateFormat("yy").format(Calendar.getInstance().getTime());
            int year = Integer.parseInt(date);
            try {
                int f = ma.size() - 1;
                String a = ma.get(f);
                int b = Integer.parseInt(a);
                b = b+1;
                String c = "";
                if(b<10){
                    c = year+"0000"+b;
                }else if(b>9 && b<100){
                    c = year+"000"+b;
                }else if(b>99 && b<1000){
                    c = year+"00"+b;
                }else if(b>999 && b<10000){
                    c = year+"0"+b;
                }else if(b>9999 && b<100000){
                    c = year+""+b;
                }else if(b>99999 && b<1000000){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, "Đã dến giới hạn mã Sinh viên", Toast.LENGTH_SHORT).show();
                }
                travema = c;
            }catch (Exception e){
                String a = year+"00000";
                travema = a;
            }

        }

        return travema;
    }

    private void clearfrom(String loai){
        taotaikhoan_textbox_tentaikhoan.setText("");
        taotaikhoan_textbox_mataikhoan.setText("");
        taotaikhoan_textbox_matkhau.setText("");
        taotaikhoan_textbox_mail.setText("");
        taotaikhoan_textbox_mataikhoan.setText(taoma(loai));
    }

    //----------------------------------//
    private boolean kt(String b){
        boolean a = false;
        try{
            int c = Integer.parseInt(b);
            a = true;
            return a;
        }catch (Exception e){
            a = false;
            return a;
        }

    }
    public interface OnBackPressedListener {
        void onBackPressed();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent myIntent = new Intent(Quanly_trangchinh_quanlytaikhoan_taotaikhoan.this, Quanly_trangchinh_quanlytaikhoan.class);
        startActivity(myIntent);
    }
    //----------------------------------//
}