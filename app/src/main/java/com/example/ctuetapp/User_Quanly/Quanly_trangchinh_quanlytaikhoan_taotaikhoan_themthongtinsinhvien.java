package com.example.ctuetapp.User_Quanly;

import static com.example.ctuetapp.MainActivity.getMD5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien extends AppCompatActivity {
    //----------------------------------//
    Spinner taotaikhoan_themthongtinsinhvien_spinner_gioitinh,taotaikhoan_themthongtinsinhvien_spinner_lop,
            taotaikhoan_themthongtinsinhvien_textbox_spinner_khoa;
    EditText taotaikhoan_themthongtinsinhvien_textbox_tensinhvien,taotaikhoan_themthongtinsinhvien_textbox_masinhvien,
            taotaikhoan_themthongtinsinhvien_textbox_sodienthoai,taotaikhoan_themthongtinsinhvien_textbox_ngaysinh,
            taotaikhoan_themthongtinsinhvien_textbox_diachi;
    FloatingActionButton taotaikhoan_themthongtinsinhvien_button_save;
    //----------------------------------//
    final Calendar myCalendar = Calendar.getInstance();
    String TENSV,MASV,MAIL,MATKHAU;
    String[] gioitinh= {"Chưa chọn","Nam","Nữ"};
    String[] lop= {"Chưa chọn","Khoa học máy tính","Kỹ thuật phần mềm","Hệ thống thông tin"};
    ArrayList<String> nam=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien);
        taotaikhoan_themthongtinsinhvien_anhxa();
        //----------------------------------//

        TENSV = getIntent().getStringExtra("ten");
        MASV = getIntent().getStringExtra("masv");
        MAIL = getIntent().getStringExtra("mail");
        MATKHAU = getIntent().getStringExtra("matkhau");

        //----------------------------------//

        taotaikhoan_themthongtinsinhvien_textbox_tensinhvien.setText(TENSV);
        taotaikhoan_themthongtinsinhvien_textbox_masinhvien.setText(MASV);

        //----------------------------------//

        ArrayAdapter<String> adaptergioitinh = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                gioitinh);
        adaptergioitinh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.taotaikhoan_themthongtinsinhvien_spinner_gioitinh.setAdapter(adaptergioitinh);
        taotaikhoan_themthongtinsinhvien_spinner_gioitinh.setSelection(0);

        //----------------------------------//

        ArrayAdapter<String> adapterlop = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                lop);
        adapterlop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.taotaikhoan_themthongtinsinhvien_spinner_lop.setAdapter(adapterlop);
        taotaikhoan_themthongtinsinhvien_spinner_lop.setSelection(0);

        //----------------------------------//

        taotaikhoan_themthongtinsinhvien_textbox_ngaysinh.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(taotaikhoan_themthongtinsinhvien_textbox_ngaysinh);
                }
            };
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //----------------------------------//

        xuatnam();

        //----------------------------------//

        taotaikhoan_themthongtinsinhvien_button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taotaikhoan_themthongtinsinhvien_textbox_sodienthoai.getText().toString().trim().equals("")){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien.this, "Chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
                }else if(taotaikhoan_themthongtinsinhvien_textbox_sodienthoai.getText().toString().trim().length()!=10){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien.this, "Số điện thoại phải có 10 số", Toast.LENGTH_SHORT).show();
                }else if(taotaikhoan_themthongtinsinhvien_textbox_ngaysinh.getText().toString().trim().equals("")){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien.this, "Chưa chọn năm sinh", Toast.LENGTH_SHORT).show();
                }else if(dknamsinh(taotaikhoan_themthongtinsinhvien_textbox_ngaysinh.getText().toString())==false){

                }else if(taotaikhoan_themthongtinsinhvien_spinner_gioitinh.getSelectedItem().toString().equals("Chưa chọn")||
                        taotaikhoan_themthongtinsinhvien_spinner_lop.getSelectedItem().toString().equals("Chưa chọn")){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien.this, "Có thông tin chưa chọn", Toast.LENGTH_SHORT).show();
                }else if(taotaikhoan_themthongtinsinhvien_textbox_diachi.getText().toString().trim().equals("")){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien.this, "Chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                }else{
                    MainActivity.dulieu.themDuLieu("insert into TaiKhoan(ten,ma,mk,loai_TK,mail,tinh_trang) values ('" + TENSV.trim() + "','" + MASV.trim()+ "','" + getMD5(MATKHAU.trim()) + "','Sinh viên','" + MAIL.trim() + "', 'Active'  ) ");
                    MainActivity.dulieu.themDuLieu("insert into SinhVien(tenSv, mSv, lop, ngay_sinh, gioi_tinh, khoa_hoc, sdt, dia_chi,anh) values" +
                            " ( '"+TENSV.trim()+"', '"+MASV.trim()+"', '"+taotaikhoan_themthongtinsinhvien_spinner_lop.getSelectedItem().toString().trim()+"','"+taotaikhoan_themthongtinsinhvien_textbox_ngaysinh.getText().toString().trim()+"','"
                            +taotaikhoan_themthongtinsinhvien_spinner_gioitinh.getSelectedItem().toString().trim()+"' ," +
                            " '"+taotaikhoan_themthongtinsinhvien_textbox_spinner_khoa.getSelectedItem().toString().trim()+"' , '"+taotaikhoan_themthongtinsinhvien_textbox_sodienthoai.getText().toString().trim()+"' , '"
                            +taotaikhoan_themthongtinsinhvien_textbox_diachi.getText().toString().trim()+"','chuaco' )   ");
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien.this, "Đã tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                    trove();
                }
            }
        });

        //----------------------------------//
    }

    public void taotaikhoan_themthongtinsinhvien_anhxa() {
        taotaikhoan_themthongtinsinhvien_spinner_gioitinh = (Spinner) findViewById(R.id.taotaikhoan_themthongtinsinhvien_spinner_gioitinh) ;
        taotaikhoan_themthongtinsinhvien_spinner_lop = (Spinner) findViewById(R.id.taotaikhoan_themthongtinsinhvien_spinner_lop) ;
        taotaikhoan_themthongtinsinhvien_textbox_spinner_khoa = (Spinner) findViewById(R.id.taotaikhoan_themthongtinsinhvien_textbox_spinner_khoa) ;
        taotaikhoan_themthongtinsinhvien_textbox_tensinhvien = (EditText) findViewById(R.id.taotaikhoan_themthongtinsinhvien_textbox_tensinhvien);
        taotaikhoan_themthongtinsinhvien_textbox_masinhvien = (EditText) findViewById(R.id.taotaikhoan_themthongtinsinhvien_textbox_masinhvien);
        taotaikhoan_themthongtinsinhvien_textbox_sodienthoai = (EditText) findViewById(R.id.taotaikhoan_themthongtinsinhvien_textbox_sodienthoai);
        taotaikhoan_themthongtinsinhvien_textbox_ngaysinh = (EditText) findViewById(R.id.taotaikhoan_themthongtinsinhvien_textbox_ngaysinh);
        taotaikhoan_themthongtinsinhvien_textbox_diachi = (EditText) findViewById(R.id.taotaikhoan_themthongtinsinhvien_textbox_diachi);
        taotaikhoan_themthongtinsinhvien_button_save = (FloatingActionButton) findViewById(R.id.taotaikhoan_themthongtinsinhvien_button_save);
    }

    private void updateLabel(EditText tg) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tg.setText(sdf.format(myCalendar.getTime()));
    }

    public void trove(){
        finish();
        Intent myIntent = new Intent(Quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien.this,
                Quanly_trangchinh_quanlytaikhoan_taotaikhoan.class);
        startActivity(myIntent);
    }

    private void xuatnam(){
        String date = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
        int year = Integer.parseInt(date);
        for(int a = 2000;a<=year;a++){
            nam.add(String.valueOf(a));
        }
        ArrayAdapter<String> adapterkhoa = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                nam);
        String f = MASV.substring(0,2);
        String h = "20"+f;

        adapterkhoa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.taotaikhoan_themthongtinsinhvien_textbox_spinner_khoa.setAdapter(adapterkhoa);
        taotaikhoan_themthongtinsinhvien_textbox_spinner_khoa.setSelection(nam.size()-1);
        taotaikhoan_themthongtinsinhvien_textbox_spinner_khoa.setSelection(chongiatriarr(h,nam));
    }

    private int chongiatriarr(String a, ArrayList b){
        int i = 0;
        int c = 0;
        for(; i<b.size();i++){
            if( b.get(i).equals(a)==true){
                c = i;
            }
        }
        return c;
    }

    private boolean dknamsinh(String b){
        boolean c= false;
        String date = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
        int year = Integer.parseInt(date);
        String[]d=b.split("/");
        if(year - Integer.parseInt(d[2])<10){
            c = false;
            Toast.makeText(Quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien.this, "Năm sinh phải cách hiện tại 10 năm"+Integer.parseInt(d[2]), Toast.LENGTH_SHORT).show();
        }else {
            c = true;
        }
        return c;
    }

    //----------------------------------//

    public interface OnBackPressedListener {
        void onBackPressed();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent myIntent = new Intent(Quanly_trangchinh_quanlytaikhoan_taotaikhoan_themthongtinsinhvien.this,
                Quanly_trangchinh_quanlytaikhoan_taotaikhoan.class);
        startActivity(myIntent);
    }

    //----------------------------------//
}