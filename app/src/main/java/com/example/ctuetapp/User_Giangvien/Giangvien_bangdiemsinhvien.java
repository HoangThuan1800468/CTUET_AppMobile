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
import com.example.ctuetapp.User_Quanly.Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Giangvien_bangdiemsinhvien extends AppCompatActivity {
    //----------------------------------//
    TextView bangdiemsinhvien_textview_tensinhvien,bangdiemsinhvien_textview_masinhvien;
    EditText bangdiemsinhvien_textbox_diemthuchanh,bangdiemsinhvien_textbox_diemgiuaky,bangdiemsinhvien_textbox_diemcuoiky;
    Button bangdiemsinhvien_button_luu,bangdiemsinhvien_button_xoa;
    //----------------------------------//
    String masinhvien,malop;
    //----------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giangvien_bangdiemsinhvien);
        Giangvien_bangdiemsinhvien_anhxa();
        //----------------------------------//
        masinhvien = getIntent().getStringExtra("a");
        malop = getIntent().getStringExtra("b");
        //----------------------------------//
        loadtt( masinhvien, malop);
        kt_thuchanh(malop);
        if(kt_thuchanh(malop)){
            bangdiemsinhvien_textbox_diemthuchanh.setEnabled(true);
        }else{
            bangdiemsinhvien_textbox_diemthuchanh.setEnabled(false);
        }
        if(Giangvien_trangchinh_quanlylophocphan_thongtinlophoc.kt_tinhtrang(malop)==false){
            bangdiemsinhvien_button_luu.setEnabled(false);
            bangdiemsinhvien_button_xoa.setEnabled(false);
        }else{
            bangdiemsinhvien_button_luu.setEnabled(true);
            bangdiemsinhvien_button_xoa.setEnabled(true);
        }
        //----------------------------------//
        bangdiemsinhvien_button_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                if (bangdiemsinhvien_textbox_diemthuchanh.getText().toString().equals("") && bangdiemsinhvien_textbox_diemgiuaky.getText().toString().equals("") && bangdiemsinhvien_textbox_diemcuoiky.getText().toString().equals("")) {
                    Toast.makeText(Giangvien_bangdiemsinhvien.this, "Chưa nhập điểm", Toast.LENGTH_SHORT).show();
                } else if (!bangdiemsinhvien_textbox_diemthuchanh.getText().toString().equals("") && bangdiemsinhvien_textbox_diemgiuaky.getText().toString().equals("") && bangdiemsinhvien_textbox_diemcuoiky.getText().toString().equals("")) {
                    if (ktdiem(bangdiemsinhvien_textbox_diemthuchanh.getText().toString()) == true) {
                        nhapdiem("diemthuchanh", bangdiemsinhvien_textbox_diemthuchanh.getText().toString(), masinhvien, malop);
                    }
                } else if (bangdiemsinhvien_textbox_diemthuchanh.getText().toString().equals("") && !bangdiemsinhvien_textbox_diemgiuaky.getText().toString().equals("") && bangdiemsinhvien_textbox_diemcuoiky.getText().toString().equals("")) {
                    if (ktdiem(bangdiemsinhvien_textbox_diemgiuaky.getText().toString()) == true) {
                        nhapdiem("diemgiuaky", bangdiemsinhvien_textbox_diemgiuaky.getText().toString(), masinhvien, malop);
                    }
                } else if (bangdiemsinhvien_textbox_diemthuchanh.getText().toString().equals("") && bangdiemsinhvien_textbox_diemgiuaky.getText().toString().equals("") && !bangdiemsinhvien_textbox_diemcuoiky.getText().toString().equals("")) {
                    if (ktdiem(bangdiemsinhvien_textbox_diemcuoiky.getText().toString()) == true) {
                        nhapdiem("diemcuoiky", bangdiemsinhvien_textbox_diemcuoiky.getText().toString(), masinhvien, malop);
                    }
                } else if (!bangdiemsinhvien_textbox_diemthuchanh.getText().toString().equals("") && !bangdiemsinhvien_textbox_diemgiuaky.getText().toString().equals("") && !bangdiemsinhvien_textbox_diemcuoiky.getText().toString().equals("")) {
                    if (ktdiem(bangdiemsinhvien_textbox_diemthuchanh.getText().toString()) == true && ktdiem(bangdiemsinhvien_textbox_diemgiuaky.getText().toString()) == true && ktdiem(bangdiemsinhvien_textbox_diemcuoiky.getText().toString()) == true) {
                        capnhatdiem(bangdiemsinhvien_textbox_diemthuchanh.getText().toString(), bangdiemsinhvien_textbox_diemgiuaky.getText().toString(), bangdiemsinhvien_textbox_diemcuoiky.getText().toString(), masinhvien, malop);
                    }
                } else if (!bangdiemsinhvien_textbox_diemthuchanh.getText().toString().equals("") && !bangdiemsinhvien_textbox_diemgiuaky.getText().toString().equals("") && bangdiemsinhvien_textbox_diemcuoiky.getText().toString().equals("")) {
                    if (ktdiem(bangdiemsinhvien_textbox_diemthuchanh.getText().toString()) == true && ktdiem(bangdiemsinhvien_textbox_diemgiuaky.getText().toString()) == true) {
                        nhapdiem2("diemthuchanh", "diemgiuaky", bangdiemsinhvien_textbox_diemthuchanh.getText().toString(), bangdiemsinhvien_textbox_diemgiuaky.getText().toString(), masinhvien, malop);
                    }
                } else if (!bangdiemsinhvien_textbox_diemthuchanh.getText().toString().equals("") && bangdiemsinhvien_textbox_diemgiuaky.getText().toString().equals("") && !bangdiemsinhvien_textbox_diemcuoiky.getText().toString().equals("")) {
                    if (ktdiem(bangdiemsinhvien_textbox_diemthuchanh.getText().toString()) == true && ktdiem(bangdiemsinhvien_textbox_diemcuoiky.getText().toString()) == true) {
                        nhapdiem2("diemthuchanh", "diemcuoiky", bangdiemsinhvien_textbox_diemthuchanh.getText().toString(), bangdiemsinhvien_textbox_diemcuoiky.getText().toString(), masinhvien, malop);
                    }
                } else if (bangdiemsinhvien_textbox_diemthuchanh.getText().toString().equals("") && !bangdiemsinhvien_textbox_diemgiuaky.getText().toString().equals("") && !bangdiemsinhvien_textbox_diemcuoiky.getText().toString().equals("")) {
                    if (ktdiem(bangdiemsinhvien_textbox_diemgiuaky.getText().toString()) == true && ktdiem(bangdiemsinhvien_textbox_diemcuoiky.getText().toString()) == true) {
                        nhapdiem2("diemgiuaky", "diemcuoiky", bangdiemsinhvien_textbox_diemgiuaky.getText().toString(), bangdiemsinhvien_textbox_diemcuoiky.getText().toString(), masinhvien, malop);
                    }
                }
            }catch (Exception e){
                    Toast.makeText(Giangvien_bangdiemsinhvien.this, "Cập nhật điểm không thành công! Vui lòng nhập số điểm là số chẵn!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //----------------------------------//
        bangdiemsinhvien_button_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(Giangvien_bangdiemsinhvien.this);
                b.setTitle("Xác nhận!");
                b.setMessage("Xóa sinh viên "+masinhvien +" khỏi lớp học phần?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        xoa( malop,masinhvien);
                        dialog.cancel();
                    }
                });
                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });
    }
    public void Giangvien_bangdiemsinhvien_anhxa() {
        bangdiemsinhvien_textview_tensinhvien = (TextView) findViewById(R.id.bangdiemsinhvien_textview_tensinhvien);
        bangdiemsinhvien_textview_masinhvien = (TextView) findViewById(R.id.bangdiemsinhvien_textview_masinhvien);
        bangdiemsinhvien_textbox_diemthuchanh = (EditText) findViewById(R.id.bangdiemsinhvien_textbox_diemthuchanh);
        bangdiemsinhvien_textbox_diemgiuaky = (EditText) findViewById(R.id.bangdiemsinhvien_textbox_diemgiuaky);
        bangdiemsinhvien_textbox_diemcuoiky = (EditText) findViewById(R.id.bangdiemsinhvien_textbox_diemcuoiky);
        bangdiemsinhvien_button_luu = (Button) findViewById(R.id.bangdiemsinhvien_button_luu);
        bangdiemsinhvien_button_xoa = (Button) findViewById(R.id.bangdiemsinhvien_button_xoa);
    }
    private void loadtt(String masinhvien,String malop){
        MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select tenSv  from Sinhvien where mSv = '"+masinhvien+"' ");
        String ten="";
        while (MainActivity.cs2.moveToNext()){
            ten = MainActivity.cs2.getString(0);
        }
        String ten_sinh_vien = "Tên sinh viên: "+ten;
        String ma_sinh_vien = "Mã sinh viên: "+masinhvien;
        bangdiemsinhvien_textview_tensinhvien.setText(ten_sinh_vien);
        bangdiemsinhvien_textview_masinhvien.setText(ma_sinh_vien);
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select diemthuchanh ,diemgiuaky ,diemcuoiky  from LopHP_sinhvien where ma_MH = '"+malop+"' and mSv = '"+masinhvien+"' ");
        while (MainActivity.cs1.moveToNext()){
            try {
                bangdiemsinhvien_textbox_diemthuchanh.setText(MainActivity.cs1.getString(0));
            }catch (Exception e){
                bangdiemsinhvien_textbox_diemthuchanh.setText("");
            }
            try {
                bangdiemsinhvien_textbox_diemgiuaky.setText(MainActivity.cs1.getString(1));
            }catch (Exception e){
                bangdiemsinhvien_textbox_diemgiuaky.setText("");
            }
            try {
                bangdiemsinhvien_textbox_diemcuoiky.setText(MainActivity.cs1.getString(2));
            }catch (Exception e){
                bangdiemsinhvien_textbox_diemcuoiky.setText("");
            }
        }

    }
    private boolean ktdiem(String a){
        boolean b  = true;
        if(Integer.parseInt(a)>10){
            Toast.makeText(Giangvien_bangdiemsinhvien.this, "Nhập điểm hệ số 10", Toast.LENGTH_SHORT).show();
            b = false;
            return b;
        }else{
            b=true;
            return b;
        }

    }
    public static boolean kt_thuchanh(String a){
        String c="";
        boolean b = false;
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select tiet_TH  from LopHP where ma_MH = '"+a+"'  ");
        while (MainActivity.cs1.moveToNext()){
            c = MainActivity.cs1.getString(0);
        }
        if(c.equals("Không có thực hành")){
            b =false;
        }else{
            b =true;
        }
        return b;
    }
    private void xoa(String malop,String mSv){
        MainActivity.dulieu.themDuLieu("  DELETE FROM LopHP_sinhvien  where ma_MH = '" + malop + "' and mSv = '"+mSv+"'   ");
        Toast.makeText(Giangvien_bangdiemsinhvien.this, "Đã xóa sinh viên khỏi lớp học phần", Toast.LENGTH_SHORT).show();

    }
    private void nhapdiem(String loaidiem,String diem,String masv,String lophocphan){
        MainActivity.dulieu.themDuLieu("  update LopHP_sinhvien set "+loaidiem+" = " + Integer.parseInt(diem) + " where mSv = '"+masv+"'  and  ma_MH = '"+lophocphan+"'  ");
        Toast.makeText(Giangvien_bangdiemsinhvien.this, "Đã cập nhật điểm "+loai(loaidiem)+" cho sinh viên "+masv+" thành công", Toast.LENGTH_SHORT).show();

    }
    private void nhapdiem2(String loaidiem1,String loaidiem2,String diem1,String diem2,String masv,String lophocphan){
        MainActivity.dulieu.themDuLieu("  update LopHP_sinhvien set "+loaidiem1+" = " + Integer.parseInt(diem1) + ", "+loaidiem2+" = " + Integer.parseInt(diem2) + " where mSv = '"+masv+"'  and  ma_MH = '"+lophocphan+"'  ");
        Toast.makeText(Giangvien_bangdiemsinhvien.this, "Đã cập nhật điểm "+loai(loaidiem1)+" và "+loai(loaidiem2)+"cho sinh viên "+masv+" thành công", Toast.LENGTH_SHORT).show();
    }
    private void capnhatdiem(String diemthuchanh,String diemgiuaky,String diemcuoiky,String masv,String lophocphan){
        MainActivity.dulieu.themDuLieu("  update LopHP_sinhvien set diemthuchanh = " + Integer.parseInt(diemthuchanh) + " , diemgiuaky = " + Integer.parseInt(diemgiuaky) + " , diemcuoiky = " + Integer.parseInt(diemcuoiky) + " where mSv = '"+masv+"'  and  ma_MH = '"+lophocphan+"'  ");
        Toast.makeText(Giangvien_bangdiemsinhvien.this, "Đã cập nhật điểm cho sinh viên "+masv+" thành công", Toast.LENGTH_SHORT).show();
    }
    private String loai(String a){
        String b ="";
        if(a.equals("diemgiuaky")){
            b = "Điểm giữa kỳ";
        }else if(a.equals("diemthuchanh")){
            b = "Điểm thực hành";
        }else if(a.equals("diemcuoiky")){
            b = "Điểm cuối kỳ";
        }
        return b;
    }

    public interface OnBackPressedListener {
        void onBackPressed();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent myIntent = new Intent(Giangvien_bangdiemsinhvien.this, Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.class);
        myIntent.putExtra("a",malop);
        startActivity(myIntent);

    }
}