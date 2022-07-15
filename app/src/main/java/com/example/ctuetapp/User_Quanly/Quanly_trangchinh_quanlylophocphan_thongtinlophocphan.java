package com.example.ctuetapp.User_Quanly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.Date;
import java.util.Locale;

public class Quanly_trangchinh_quanlylophocphan_thongtinlophocphan extends AppCompatActivity {
    //----------------------------------//
    EditText thongtinlophocphan_textbox_mamonhoc,thongtinlophocphan_textbox_tenmonhoc,
            thongtinlophocphan_textbox_thoigianlythuyetbatdau,thongtinlophocphan_textbox_thoigianlythuyetketthuc,
            thongtinlophocphan_textbox_thoigianthuchanhbatdau,thongtinlophocphan_textbox_thoigianthuchanhketthuc,
            thongtinlophocphan_textbox_ghichu;
    Spinner thongtinlophocphan_spinner_giangvien,thongtinlophocphan_spinner_sotinchi,
            thongtinlophocphan_spinner_loaihocphan,thongtinlophocphan_spinner_phonghoc,
            thongtinlophocphan_spinner_tinhtrang,thongtinlophocphan_spinner_lythuyettiet,
            thongtinlophocphan_spinner_thuchanhtiet;
    FloatingActionButton thongtinlophocphan_button_luu,thongtinlophocphan_button_xoa,thongtinlophocphan_button_danhsachsinhvien;
    //----------------------------------//
    String[] dstinchi= {"Chưa chọn","1","2","3","4","10"};
    String[] dstietlythuyet={"Chưa chọn","Tiết 1,2","Tiết 3,4","Tiết 4,5","Tiết 1,2,3","Tiết 2,3,4","Tiết 3,4,5","Tiết 6,7","Tiết 8,9","Tiết 9,10","Tiết 6,7,8","Tiết 7,8,9","Tiết 8,9,10"};
    String[] dstietthuchanh={"Chưa chọn","Tiết 1,2,3,4,5","Tiết 6,7,8,9,10"};
    String[] dsloaihp={"Chưa chọn","Bắt buộc","Không bắt buộc"};
    String[] dstinhtrang={"Chưa chọn","Đang học","Đã kết thúc"};
    String[] dsphonghoc={"Chưa chọn","A001","A002","A003","A004","B001","B002","B003","B004","C001","C002","C003","C004"};
    final Calendar myCalendar = Calendar.getInstance();
    String sotinchi,lythuyettiet,thuchanhtiet,giangvien,loaihp,tinhtrang,phonghoc;
    ArrayList<String> dsgiangvien = new ArrayList<>();
    //----------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_trangchinh_quanlylophocphan_thongtinlophocphan);
        thongtinlophocphan_anhxa();
        //----------------------------------//
        thongtinlophocphan_textbox_mamonhoc.setText(getIntent().getStringExtra("a"));
        giangviends();
        //----------------------------------//
        ArrayAdapter<String> adaptersotinchi = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dstinchi);
        adaptersotinchi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.thongtinlophocphan_spinner_sotinchi.setAdapter(adaptersotinchi);
        thongtinlophocphan_spinner_sotinchi.setSelection(0);
        //----------------------------------//
        ArrayAdapter<String> adaptertietlythuyet = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dstietlythuyet);
        adaptertietlythuyet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.thongtinlophocphan_spinner_lythuyettiet.setAdapter(adaptertietlythuyet);
        thongtinlophocphan_spinner_lythuyettiet.setSelection(0);
        //----------------------------------//
        ArrayAdapter<String> adaptertietthuchanh = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dstietthuchanh);
        adaptertietthuchanh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.thongtinlophocphan_spinner_thuchanhtiet.setAdapter(adaptertietthuchanh);
        thongtinlophocphan_spinner_thuchanhtiet.setSelection(0);
        //----------------------------------//
        ArrayAdapter<String> adapterloaihp = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dsloaihp);
        adapterloaihp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.thongtinlophocphan_spinner_loaihocphan.setAdapter(adapterloaihp);
        thongtinlophocphan_spinner_loaihocphan.setSelection(0);
        //----------------------------------//
        ArrayAdapter<String> adaptertinhtrang = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dstinhtrang);
        adaptertinhtrang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.thongtinlophocphan_spinner_tinhtrang.setAdapter(adaptertinhtrang);
        thongtinlophocphan_spinner_tinhtrang.setSelection(0);
        //----------------------------------//
        ArrayAdapter<String> adapterphonghoc = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dsphonghoc);
        adapterphonghoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.thongtinlophocphan_spinner_phonghoc.setAdapter(adapterphonghoc);
        thongtinlophocphan_spinner_phonghoc.setSelection(0);
        //----------------------------------//
        thongtinlophocphan_textbox_thoigianlythuyetbatdau.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(thongtinlophocphan_textbox_thoigianlythuyetbatdau);
                }
            };
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //----------------------------------//
        thongtinlophocphan_textbox_thoigianlythuyetketthuc.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(thongtinlophocphan_textbox_thoigianlythuyetketthuc);
                }
            };
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //----------------------------------//
        thongtinlophocphan_textbox_thoigianthuchanhbatdau.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(thongtinlophocphan_textbox_thoigianthuchanhbatdau);
                }
            };
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //----------------------------------//
        thongtinlophocphan_textbox_thoigianthuchanhketthuc.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(thongtinlophocphan_textbox_thoigianthuchanhketthuc);
                }
            };
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //----------------------------------//
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select ma_MH,ten_MH,ten_GV,ma_GV,so_tc,tiet_LT,tiet_TH,ngay_LT_bd,ngay_LT_kt,ngay_TH_bd,ngay_TH_kt,ghi_chu,loaiHP,tinhtrangHP,phonghoc from LopHP where ma_MH = '"
                +thongtinlophocphan_textbox_mamonhoc.getText().toString()+"' ");
        while (MainActivity.cs1.moveToNext()) {
            thongtinlophocphan_textbox_tenmonhoc.setText(MainActivity.cs1.getString(1));
            thongtinlophocphan_spinner_giangvien.setSelection(chongiatriarr(MainActivity.cs1.getString(3)+" - "+MainActivity.cs1.getString(2),dsgiangvien));
            thongtinlophocphan_spinner_sotinchi.setSelection(chongiatri(MainActivity.cs1.getString(4),dstinchi));
            thongtinlophocphan_spinner_lythuyettiet.setSelection(chongiatri(MainActivity.cs1.getString(5),dstietlythuyet));
            thongtinlophocphan_spinner_thuchanhtiet.setSelection(chongiatri(MainActivity.cs1.getString(6),dstietthuchanh));
            thongtinlophocphan_textbox_thoigianlythuyetbatdau.setText(MainActivity.cs1.getString(7));
            thongtinlophocphan_textbox_thoigianlythuyetketthuc.setText(MainActivity.cs1.getString(8));
            thongtinlophocphan_textbox_thoigianthuchanhbatdau.setText(MainActivity.cs1.getString(9));
            thongtinlophocphan_textbox_thoigianthuchanhketthuc.setText(MainActivity.cs1.getString(10));
            thongtinlophocphan_textbox_ghichu.setText(MainActivity.cs1.getString(11));
            thongtinlophocphan_spinner_loaihocphan.setSelection(chongiatri(MainActivity.cs1.getString(12),dsloaihp));
            thongtinlophocphan_spinner_tinhtrang.setSelection(chongiatri(MainActivity.cs1.getString(13),dstinhtrang));
            thongtinlophocphan_spinner_phonghoc.setSelection(chongiatri(MainActivity.cs1.getString(14),dsphonghoc));
        }
        //----------------------------------//
        thongtinlophocphan_spinner_thuchanhtiet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(thongtinlophocphan_spinner_thuchanhtiet.getSelectedItem().toString().equals("Chưa chọn")){
                    thongtinlophocphan_textbox_thoigianthuchanhbatdau.setText("");
                    thongtinlophocphan_textbox_thoigianthuchanhbatdau.setEnabled(false);
                    thongtinlophocphan_textbox_thoigianthuchanhketthuc.setText("");
                    thongtinlophocphan_textbox_thoigianthuchanhketthuc.setEnabled(false);

                }else{
                    thongtinlophocphan_textbox_thoigianthuchanhbatdau.setEnabled(true);
                    thongtinlophocphan_textbox_thoigianthuchanhketthuc.setEnabled(true);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //----------------------------------//
        thongtinlophocphan_button_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sotinchi=thongtinlophocphan_spinner_sotinchi.getSelectedItem().toString();
                    lythuyettiet=thongtinlophocphan_spinner_lythuyettiet.getSelectedItem().toString();
                    thuchanhtiet=thongtinlophocphan_spinner_thuchanhtiet.getSelectedItem().toString();
                    giangvien=thongtinlophocphan_spinner_giangvien.getSelectedItem().toString();
                    loaihp=thongtinlophocphan_spinner_loaihocphan.getSelectedItem().toString();
                    tinhtrang=thongtinlophocphan_spinner_tinhtrang.getSelectedItem().toString();
                    phonghoc=thongtinlophocphan_spinner_phonghoc.getSelectedItem().toString();
                    String[] gv_chuoi = giangvien.split("-");
                    String magiangvien = gv_chuoi[0].trim();
                    String tengiangvien = gv_chuoi[1].trim();
                    if(dk(sotinchi)==false||dk(lythuyettiet)==false||dk(giangvien)==false){
                        Toast.makeText(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this, "Có thông tin chưa chọn", Toast.LENGTH_SHORT).show();
                    }else if(thongtinlophocphan_textbox_tenmonhoc.getText().toString().equals("")||thongtinlophocphan_textbox_thoigianlythuyetbatdau.getText().toString().equals("")
                            ||thongtinlophocphan_textbox_thoigianlythuyetketthuc.getText().toString().equals("")){
                        Toast.makeText(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this, "Có thông tin chưa điền đầy đủ", Toast.LENGTH_SHORT).show();
                    }else if(dk_time(thongtinlophocphan_textbox_thoigianlythuyetbatdau.getText().toString(),thongtinlophocphan_textbox_thoigianlythuyetketthuc.getText().toString())==false){

                    }else{
                        if(dk(thuchanhtiet)==true){
                            if(thongtinlophocphan_textbox_thoigianthuchanhbatdau.getText().toString().equals("") ||thongtinlophocphan_textbox_thoigianthuchanhketthuc.getText().toString().equals("")){
                                Toast.makeText(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this, "Có thông tin chưa điền đầy đủ", Toast.LENGTH_SHORT).show();
                            }else if(dk_time(thongtinlophocphan_textbox_thoigianthuchanhbatdau.getText().toString(),
                                    thongtinlophocphan_textbox_thoigianthuchanhketthuc.getText().toString()) == false){

                            }else{
                                MainActivity.dulieu.themDuLieu("update LopHP set ten_MH = '" + thongtinlophocphan_textbox_tenmonhoc.getText().toString()
                                        + "',ten_GV = '" + tengiangvien.trim() + "',ma_GV = '" + magiangvien.trim() + "',so_tc = " + Integer.parseInt(sotinchi)
                                        + ", tiet_LT = '" + lythuyettiet + "',tiet_TH = '" + thuchanhtiet + "',ngay_LT_bd = '"
                                        + thongtinlophocphan_textbox_thoigianlythuyetbatdau.getText().toString() + "',ngay_LT_kt = '" + thongtinlophocphan_textbox_thoigianlythuyetketthuc.getText().toString()
                                        + "',ngay_TH_bd = '" + thongtinlophocphan_textbox_thoigianthuchanhbatdau.getText().toString() + "',ngay_TH_kt = '"
                                        + thongtinlophocphan_textbox_thoigianthuchanhketthuc.getText().toString() + "',ghi_chu = '" + thongtinlophocphan_textbox_ghichu.getText().toString()
                                        + "',loaiHP = '" + loaihp + "',tinhtrangHP = '" + tinhtrang + "',phonghoc = '" + phonghoc + "' where ma_MH = '"+thongtinlophocphan_textbox_mamonhoc.getText().toString()+"' ");
                                MainActivity.dulieu.themDuLieu("DELETE FROM LopHP_lichhoc  where ma_MH = '"+thongtinlophocphan_textbox_mamonhoc.getText().toString()+"' ");
                                Quanly_trangchinh_quanlylophocphan_themlophocphan.taolichlythuyet(thongtinlophocphan_textbox_mamonhoc.getText().toString(),thongtinlophocphan_textbox_thoigianlythuyetbatdau.getText().toString(),
                                        thongtinlophocphan_textbox_thoigianlythuyetketthuc.getText().toString(),"lythuyet");
                                Quanly_trangchinh_quanlylophocphan_themlophocphan.taolichlythuyet(thongtinlophocphan_textbox_mamonhoc.getText().toString(),thongtinlophocphan_textbox_thoigianthuchanhbatdau.getText().toString(),
                                        thongtinlophocphan_textbox_thoigianthuchanhketthuc.getText().toString(),"thuchanh");
                                Toast.makeText(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this, "Đã lưu thay đổi", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            MainActivity.dulieu.themDuLieu("update LopHP set ten_MH = '" + thongtinlophocphan_textbox_tenmonhoc.getText().toString()
                                    + "',ten_GV = '" + tengiangvien.trim() + "',ma_GV = '" + magiangvien.trim() + "',so_tc = " + Integer.parseInt(sotinchi)
                                    + ", tiet_LT = '" + lythuyettiet + "',tiet_TH = '" + "Không có thực hành" + "',ngay_LT_bd = '"
                                    + thongtinlophocphan_textbox_thoigianlythuyetbatdau.getText().toString() + "',ngay_LT_kt = '" + thongtinlophocphan_textbox_thoigianlythuyetketthuc.getText().toString()
                                    + "',ngay_TH_bd = '" + "Không có thực hành" + "',ngay_TH_kt = '"
                                    + "Không có thực hành" + "',ghi_chu = '" + thongtinlophocphan_textbox_ghichu.getText().toString()
                                    + "',loaiHP = '" + loaihp + "',tinhtrangHP = '" + tinhtrang + "',phonghoc = '" + phonghoc + "' where ma_MH = '"+thongtinlophocphan_textbox_mamonhoc.getText().toString()+"' ");
                            MainActivity.dulieu.themDuLieu("DELETE FROM LopHP_lichhoc  where ma_MH = '"+thongtinlophocphan_textbox_mamonhoc.getText().toString()+"' ");
                            Quanly_trangchinh_quanlylophocphan_themlophocphan.taolichlythuyet(thongtinlophocphan_textbox_mamonhoc.getText().toString(),thongtinlophocphan_textbox_thoigianlythuyetbatdau.getText().toString(),
                                    thongtinlophocphan_textbox_thoigianlythuyetketthuc.getText().toString(),"lythuyet");
                            Toast.makeText(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this, "Đã lưu thay đổi", Toast.LENGTH_SHORT).show();
                        }

                    }
                }catch (Exception ex){
                    Toast.makeText(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this, "Chưa chọn giảng viên", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //----------------------------------//
        thongtinlophocphan_button_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tạo đối tượng
                AlertDialog.Builder b = new AlertDialog.Builder(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this);
                //Thiết lập tiêu đề
                b.setTitle("Xác nhận!");
                b.setMessage("Xóa lớp học phần?");
                // Nút Ok
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.dulieu.themDuLieu("DELETE FROM LopHP  where ma_MH = '"+thongtinlophocphan_textbox_mamonhoc.getText().toString()+"' ");
                        MainActivity.dulieu.themDuLieu("DELETE FROM LopHP_sinhvien  where ma_MH = '"+thongtinlophocphan_textbox_mamonhoc.getText().toString()+"' ");
                        MainActivity.dulieu.themDuLieu("DELETE FROM LopHP_lichhoc  where ma_MH = '"+thongtinlophocphan_textbox_mamonhoc.getText().toString()+"' ");
                        trove();
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
        thongtinlophocphan_button_danhsachsinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String part1 = thongtinlophocphan_textbox_mamonhoc.getText().toString();
                Intent myIntent = new Intent(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this,
                        Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.class);
                myIntent.putExtra("a",part1);
                startActivity(myIntent);
            }
        });
        //----------------------------------//
    }
    public void thongtinlophocphan_anhxa() {
        thongtinlophocphan_textbox_mamonhoc = (EditText) findViewById(R.id.thongtinlophocphan_textbox_mamonhoc);
        thongtinlophocphan_textbox_tenmonhoc = (EditText) findViewById(R.id.thongtinlophocphan_textbox_tenmonhoc);
        thongtinlophocphan_textbox_thoigianlythuyetbatdau = (EditText) findViewById(R.id.thongtinlophocphan_textbox_thoigianlythuyetbatdau);
        thongtinlophocphan_textbox_thoigianlythuyetketthuc = (EditText) findViewById(R.id.thongtinlophocphan_textbox_thoigianlythuyetketthuc);
        thongtinlophocphan_textbox_thoigianthuchanhbatdau = (EditText) findViewById(R.id.thongtinlophocphan_textbox_thoigianthuchanhbatdau);
        thongtinlophocphan_textbox_thoigianthuchanhketthuc = (EditText) findViewById(R.id.thongtinlophocphan_textbox_thoigianthuchanhketthuc);
        thongtinlophocphan_textbox_ghichu = (EditText) findViewById(R.id.thongtinlophocphan_textbox_ghichu);
        thongtinlophocphan_spinner_giangvien = (Spinner) findViewById(R.id.thongtinlophocphan_spinner_giangvien);
        thongtinlophocphan_spinner_sotinchi = (Spinner) findViewById(R.id.thongtinlophocphan_spinner_sotinchi);
        thongtinlophocphan_spinner_loaihocphan = (Spinner) findViewById(R.id.thongtinlophocphan_spinner_loaihocphan);
        thongtinlophocphan_spinner_phonghoc = (Spinner) findViewById(R.id.thongtinlophocphan_spinner_phonghoc);
        thongtinlophocphan_spinner_tinhtrang = (Spinner) findViewById(R.id.thongtinlophocphan_spinner_tinhtrang);
        thongtinlophocphan_spinner_lythuyettiet = (Spinner) findViewById(R.id.thongtinlophocphan_spinner_lythuyettiet);
        thongtinlophocphan_spinner_thuchanhtiet = (Spinner) findViewById(R.id.thongtinlophocphan_spinner_thuchanhtiet);
        thongtinlophocphan_button_luu = (FloatingActionButton) findViewById(R.id.thongtinlophocphan_button_luu);
        thongtinlophocphan_button_xoa = (FloatingActionButton) findViewById(R.id.thongtinlophocphan_button_xoa);
        thongtinlophocphan_button_danhsachsinhvien = (FloatingActionButton) findViewById(R.id.thongtinlophocphan_button_danhsachsinhvien);
    }
    private void updateLabel(EditText tg) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tg.setText(sdf.format(myCalendar.getTime()));
    }
    public void giangviends(){
        dsgiangvien.add("Chưa chọn");
        MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select mGv,tenGv from GiangVien ");
        while (MainActivity.cs2.moveToNext()) {
            dsgiangvien.add(MainActivity.cs2.getString(0)+" - "+MainActivity.cs2.getString(1));
        }
        ArrayAdapter<String> adaptergiangvien = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dsgiangvien);
        adaptergiangvien.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thongtinlophocphan_spinner_giangvien.setAdapter(adaptergiangvien);
        thongtinlophocphan_spinner_giangvien.setSelection(0);
    }
    public void trove(){
        finish();
        Intent myIntent = new Intent(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this, Quanly_trangchinh_quanlylophocphan.class);
        startActivity(myIntent);
    }
    private boolean dk(String a){
        boolean b=false;
        if(a.equals("Chưa chọn")==true){
            b=false;
        }else{
            b=true;
        }
        return b;
    }
    private boolean dk_time(String a,String b){
        boolean c=false;
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int monthpls = today.month + 1;
        if(monthpls>12){
            monthpls = 1;
        }
        String tghientai = today.monthDay+"/"+monthpls+"/"+today.year ;
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            Date datebatdau = formatter.parse(a);
            Date dateketthuc = formatter.parse(b);

            if(datebatdau.compareTo(dateketthuc)>0){
                c = false;
                Toast.makeText(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this, "Lỗi ngày bắt đầu sau ngày kết thúc", Toast.LENGTH_SHORT).show();
            }else {
                c = true;
            }
        }catch (Exception e1){
            c = false;
        }
        return c;
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


    public interface OnBackPressedListener {
        void onBackPressed();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent myIntent = new Intent(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.this, Quanly_trangchinh_quanlylophocphan.class);
        startActivity(myIntent);
    }
}