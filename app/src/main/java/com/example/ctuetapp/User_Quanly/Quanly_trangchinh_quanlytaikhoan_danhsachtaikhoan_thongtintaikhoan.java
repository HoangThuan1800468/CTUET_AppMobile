package com.example.ctuetapp.User_Quanly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan extends AppCompatActivity {
    //----------------------------------//
    EditText thongtintaikhoan_textbox_tensinhvien,thongtintaikhoan_textbox_masinhvien,
            thongtintaikhoan_textbox_sodienthoai,thongtintaikhoan_textbox_ngaysinh,
            thongtintaikhoan_textbox_diachi;
    Spinner thongtintaikhoan_spinner_gioitinh,thongtintaikhoan_spinner_lop,
            thongtintaikhoan_spinner_khoa;
    FloatingActionButton thongtintaikhoan_button_save,thongtintaikhoan_button_xoa;
    //----------------------------------//
    final Calendar myCalendar = Calendar.getInstance();
    String[] gioitinh= {"Chưa chọn","Nam","Nữ"};
    String[] lop= {"Chưa chọn","Khoa học máy tính","Kỹ thuật phần mềm","Hệ thống thông tin"};
    ArrayList<String> nam=new ArrayList<>();
    //----------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan);
        thongtintaikhoan_anhxa();
        //----------------------------------//

        xuatnam();

        //----------------------------------//

        thongtintaikhoan_textbox_tensinhvien.setText(getIntent().getStringExtra("ten"));
        thongtintaikhoan_textbox_masinhvien.setText(getIntent().getStringExtra("ma"));

        //----------------------------------//

        ArrayAdapter<String> adaptergioitinh = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                gioitinh);
        adaptergioitinh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.thongtintaikhoan_spinner_gioitinh.setAdapter(adaptergioitinh);
        thongtintaikhoan_spinner_gioitinh.setSelection(0);

        //----------------------------------//

        ArrayAdapter<String> adapterlop = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                lop);
        adapterlop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.thongtintaikhoan_spinner_lop.setAdapter(adapterlop);
        thongtintaikhoan_spinner_lop.setSelection(0);

        //----------------------------------//

        thongtintaikhoan_textbox_ngaysinh.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(thongtintaikhoan_textbox_ngaysinh);
                }
            };
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //----------------------------------//

        loadthongtinsv(thongtintaikhoan_textbox_masinhvien.getText().toString());

        //----------------------------------//

        thongtintaikhoan_button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(thongtintaikhoan_textbox_sodienthoai.getText().toString().trim().equals("")){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan.this, "Chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
                }else if(thongtintaikhoan_textbox_sodienthoai.getText().toString().trim().length()!=10){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan.this, "Số điện thoại phải có 10 số", Toast.LENGTH_SHORT).show();
                }else if(thongtintaikhoan_textbox_ngaysinh.getText().toString().trim().equals("")){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan.this, "Chưa chọn ngày sinh", Toast.LENGTH_SHORT).show();
                }else if(dknamsinh(thongtintaikhoan_textbox_ngaysinh.getText().toString())==false){

                }else if(thongtintaikhoan_spinner_gioitinh.getSelectedItem().toString().equals("Chưa chọn")||thongtintaikhoan_spinner_lop.getSelectedItem().toString().equals("Chưa chọn")){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan.this, "Có thông tin chưa chọn", Toast.LENGTH_SHORT).show();
                }else if(thongtintaikhoan_textbox_diachi.getText().toString().trim().equals("")){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan.this, "Chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                }else{
                    MainActivity.dulieu.themDuLieu("update SinhVien set sdt = '"+thongtintaikhoan_textbox_sodienthoai.getText().toString().trim()+"' , ngay_sinh = '"+thongtintaikhoan_textbox_ngaysinh.getText().toString().trim()+"' ," +
                            " gioi_tinh = '"+thongtintaikhoan_spinner_gioitinh.getSelectedItem().toString()+"'  , lop = '"+thongtintaikhoan_spinner_lop.getSelectedItem().toString()+"' ," +
                            " khoa_hoc = '"+thongtintaikhoan_spinner_khoa.getSelectedItem().toString()+"' , dia_chi = '"+thongtintaikhoan_textbox_diachi.getText().toString().trim()+"' " +
                            "where mSv = '"+thongtintaikhoan_textbox_masinhvien.getText().toString()+"' ");
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan.this, "Đã lưu thay đổi", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //----------------------------------//

        thongtintaikhoan_button_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tạo đối tượng
                AlertDialog.Builder b = new AlertDialog.Builder(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan.this);
                //Thiết lập tiêu đề
                b.setTitle("Xác nhận!");
                b.setMessage("Xóa tài khoản sinh viên");
                // Nút Ok
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.dulieu.themDuLieu("DELETE FROM TaiKhoan  where ma = '" + thongtintaikhoan_textbox_masinhvien.getText().toString() + "' ");
                        MainActivity.dulieu.themDuLieu("DELETE FROM Sinhvien  where mSv = '" + thongtintaikhoan_textbox_masinhvien.getText().toString() + "' ");
                        trove();
                        Toast.makeText(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
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
        });

        //----------------------------------//
    }

    public void thongtintaikhoan_anhxa() {
        thongtintaikhoan_textbox_tensinhvien = (EditText) findViewById(R.id.thongtintaikhoan_textbox_tensinhvien);
        thongtintaikhoan_textbox_masinhvien = (EditText) findViewById(R.id.thongtintaikhoan_textbox_masinhvien);
        thongtintaikhoan_textbox_sodienthoai = (EditText) findViewById(R.id.thongtintaikhoan_textbox_sodienthoai);
        thongtintaikhoan_textbox_ngaysinh = (EditText) findViewById(R.id.thongtintaikhoan_textbox_ngaysinh);
        thongtintaikhoan_textbox_diachi = (EditText) findViewById(R.id.thongtintaikhoan_textbox_diachi);
        thongtintaikhoan_spinner_gioitinh = (Spinner) findViewById(R.id.thongtintaikhoan_spinner_gioitinh);
        thongtintaikhoan_spinner_lop = (Spinner) findViewById(R.id.thongtintaikhoan_spinner_lop);
        thongtintaikhoan_spinner_khoa = (Spinner) findViewById(R.id.thongtintaikhoan_spinner_khoa);
        thongtintaikhoan_button_save = (FloatingActionButton) findViewById(R.id.thongtintaikhoan_button_save);
        thongtintaikhoan_button_xoa = (FloatingActionButton) findViewById(R.id.thongtintaikhoan_button_xoa);
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
        adapterkhoa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.thongtintaikhoan_spinner_khoa.setAdapter(adapterkhoa);
        thongtintaikhoan_spinner_khoa.setSelection(nam.size()-1);
    }

    private void updateLabel(EditText tg) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tg.setText(sdf.format(myCalendar.getTime()));
    }

    private void loadthongtinsv(String ma){
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select sdt,ngay_sinh,gioi_tinh,lop,khoa_hoc,dia_chi from SinhVien where mSv = '"+ma+"'  ");
        while (MainActivity.cs1.moveToNext()){
            thongtintaikhoan_textbox_sodienthoai.setText(MainActivity.cs1.getString(0));
            thongtintaikhoan_textbox_ngaysinh.setText(MainActivity.cs1.getString(1));
            thongtintaikhoan_spinner_gioitinh.setSelection(chongiatri(MainActivity.cs1.getString(2),gioitinh));
            thongtintaikhoan_spinner_lop.setSelection(chongiatri(MainActivity.cs1.getString(3),lop));
            thongtintaikhoan_spinner_khoa.setSelection(chongiatriarr(MainActivity.cs1.getString(4),nam));
            thongtintaikhoan_textbox_diachi.setText(MainActivity.cs1.getString(5));
        }
    }

    private int chongiatri(String a, String[] b){
        int i = 0;
        int c = 0;
        for(; i<b.length;i++){
            if( b[i].equals(a)==true){
                c = i;
            }
        }
        return c;
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
            Toast.makeText(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan.this, "Năm sinh phải cách hiện tại 10 năm"+Integer.parseInt(d[2]), Toast.LENGTH_SHORT).show();
        }else {
            c = true;
        }
        return c;
    }

    private void trove(){
        finish();
        Intent myIntent = new Intent(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan.this,
                Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.class);
        startActivity(myIntent);
    }

    public interface OnBackPressedListener {
        void onBackPressed();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent myIntent = new Intent(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan.this,
                Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.class);
        startActivity(myIntent);
    }
}