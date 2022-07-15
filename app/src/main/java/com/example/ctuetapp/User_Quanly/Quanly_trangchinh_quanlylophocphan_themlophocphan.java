package com.example.ctuetapp.User_Quanly;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import com.example.ctuetapp.User_Giangvien.Giangvien_trangchinh;
import com.example.ctuetapp.User_Giangvien.Giangvien_trangchinh_guithongbao;
import com.example.ctuetapp.User_Giangvien.Giangvien_trangchinh_quanly;
import com.example.ctuetapp.User_Giangvien.Giangvien_trangchinh_quanlylophocphan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Quanly_trangchinh_quanlylophocphan_themlophocphan extends AppCompatActivity {
    //----------------------------------//
    EditText themlophocphan_textbox_mamonhoc,themlophocphan_textbox_tenmonhoc,themlophocphan_textbox_thoigianlythuyetbatdau,
            themlophocphan_textbox_thoigianlythuyetketthuc,themlophocphan_textbox_thoigianthuchanhbatdau,themlophocphan_textbox_thoigianthuchanhketthuc,
            themlophocphan_textbox_ghichu;
    Button themlophocphan_button_huy,themlophocphan_button_luu;
    Spinner themlophocphan_spinner_hocky,themlophocphan_spinner_nam,themlophocphan_spinner_giangvien,
            themlophocphan_spinner_sotinchi,themlophocphan_spinner_loaihocphan,themlophocphan_spinner_phonghoc,
            themlophocphan_spinner_tinhtrang,themlophocphan_spinner_lythuyettiet,themlophocphan_spinner_thuchanhtiet;
    //----------------------------------//
    final Calendar myCalendar = Calendar.getInstance();
    String[] dshk= {"Chưa chọn","HKI","HKII","HKIII"};
    String[] dstinchi= {"Chưa chọn","1","2","3","4","10"};
    String[] dstietlythuyet={"Chưa chọn","Tiết 1,2","Tiết 3,4","Tiết 4,5","Tiết 1,2,3","Tiết 2,3,4","Tiết 3,4,5","Tiết 6,7","Tiết 8,9","Tiết 9,10","Tiết 6,7,8","Tiết 7,8,9","Tiết 8,9,10"};
    String[] dstietthuchanh={"Chưa chọn","Tiết 1,2,3,4,5","Tiết 6,7,8,9,10"};
    String[] dsloaihp={"Chưa chọn","Bắt buộc","Không bắt buộc"};
    String[] dstinhtrang={"Chưa chọn","Đang học","Đã kết thúc"};
    String[] dsphonghoc={"Chưa chọn","A001","A002","A003","A004","B001","B002","B003","B004","C001","C002","C003","C004"};
    String sotinchi,lythuyettiet,thuchanhtiet,hocky,giangvien,loaihp,tinhtrang,phonghoc;
    ArrayList<String> ma = new ArrayList<>();
    ArrayList<String> dsgiangvien = new ArrayList<>();
    //----------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_trangchinh_quanlylophocphan_themlophocphan);
        themlophocphan_anhxa();

        //----------------------------------//
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dshk);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.themlophocphan_spinner_hocky.setAdapter(adapter);
        themlophocphan_spinner_hocky.setSelection(0);
        //----------------------------------//
        ArrayAdapter<String> adaptersotinchi = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dstinchi);
        adaptersotinchi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.themlophocphan_spinner_sotinchi.setAdapter(adaptersotinchi);
        themlophocphan_spinner_sotinchi.setSelection(0);
        //----------------------------------//
        ArrayAdapter<String> adaptertietlythuyet = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dstietlythuyet);
        adaptertietlythuyet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.themlophocphan_spinner_lythuyettiet.setAdapter(adaptertietlythuyet);
        themlophocphan_spinner_lythuyettiet.setSelection(0);
        //----------------------------------//
        ArrayAdapter<String> adaptertietthuchanh = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dstietthuchanh);
        adaptertietthuchanh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.themlophocphan_spinner_thuchanhtiet.setAdapter(adaptertietthuchanh);
        themlophocphan_spinner_thuchanhtiet.setSelection(0);
        //----------------------------------//
        ArrayAdapter<String> adapterloaihp = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dsloaihp);
        adapterloaihp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.themlophocphan_spinner_loaihocphan.setAdapter(adapterloaihp);
        themlophocphan_spinner_loaihocphan.setSelection(0);
        //----------------------------------//
        ArrayAdapter<String> adaptertinhtrang = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dstinhtrang);
        adaptertinhtrang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.themlophocphan_spinner_tinhtrang.setAdapter(adaptertinhtrang);
        themlophocphan_spinner_tinhtrang.setSelection(0);
        //----------------------------------//
        ArrayAdapter<String> adapterphonghoc = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dsphonghoc);
        adapterphonghoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.themlophocphan_spinner_phonghoc.setAdapter(adapterphonghoc);
        themlophocphan_spinner_phonghoc.setSelection(0);
        //----------------------------------//
        hocky();
        giangviends();
        themlophocphan_textbox_mamonhoc.setText(taoma());
        //----------------------------------//
        themlophocphan_spinner_thuchanhtiet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(themlophocphan_spinner_thuchanhtiet.getSelectedItem().toString().equals("Chưa chọn")){
                    themlophocphan_textbox_thoigianthuchanhbatdau.setText("");
                    themlophocphan_textbox_thoigianthuchanhbatdau.setEnabled(false);
                    themlophocphan_textbox_thoigianthuchanhketthuc.setText("");
                    themlophocphan_textbox_thoigianthuchanhketthuc.setEnabled(false);

                }else{
                    themlophocphan_textbox_thoigianthuchanhbatdau.setEnabled(true);
                    themlophocphan_textbox_thoigianthuchanhketthuc.setEnabled(true);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //----------------------------------//
        themlophocphan_textbox_thoigianlythuyetbatdau.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(themlophocphan_textbox_thoigianlythuyetbatdau);
                }
            };
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Quanly_trangchinh_quanlylophocphan_themlophocphan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //----------------------------------//
        themlophocphan_textbox_thoigianlythuyetketthuc.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(themlophocphan_textbox_thoigianlythuyetketthuc);
                }
            };
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Quanly_trangchinh_quanlylophocphan_themlophocphan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //----------------------------------//
        themlophocphan_textbox_thoigianthuchanhbatdau.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(themlophocphan_textbox_thoigianthuchanhbatdau);
                }
            };
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Quanly_trangchinh_quanlylophocphan_themlophocphan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //----------------------------------//
        themlophocphan_textbox_thoigianthuchanhketthuc.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(themlophocphan_textbox_thoigianthuchanhketthuc);
                }
            };
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Quanly_trangchinh_quanlylophocphan_themlophocphan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //----------------------------------//
        themlophocphan_button_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    sotinchi=themlophocphan_spinner_sotinchi.getSelectedItem().toString();
                    lythuyettiet=themlophocphan_spinner_lythuyettiet.getSelectedItem().toString();
                    thuchanhtiet=themlophocphan_spinner_thuchanhtiet.getSelectedItem().toString();
                    giangvien=themlophocphan_spinner_giangvien.getSelectedItem().toString();
                    loaihp=themlophocphan_spinner_loaihocphan.getSelectedItem().toString();
                    tinhtrang=themlophocphan_spinner_tinhtrang.getSelectedItem().toString();
                    phonghoc=themlophocphan_spinner_phonghoc.getSelectedItem().toString();
                try {
                    String[] gv_chuoi = giangvien.split("-");
                    String magiangvien = gv_chuoi[0].trim();
                    String tengiangvien = gv_chuoi[1].trim();
                    hocky = themlophocphan_spinner_hocky.getSelectedItem().toString() + " " + "-" + " " + themlophocphan_spinner_nam.getSelectedItem().toString();
                    if (dk(phonghoc) == false || dk(tinhtrang) == false || dk(loaihp) == false || dk(sotinchi) == false ||
                            dk(lythuyettiet) == false  || dk(themlophocphan_spinner_hocky.getSelectedItem().toString()) == false ||
                            dk(themlophocphan_spinner_giangvien.getSelectedItem().toString()) == false) {
                        Toast.makeText(Quanly_trangchinh_quanlylophocphan_themlophocphan.this, "Có thông tin chưa chọn", Toast.LENGTH_SHORT).show();
                    } else if (themlophocphan_textbox_tenmonhoc.getText().toString().trim().equals("") || themlophocphan_textbox_thoigianlythuyetbatdau.getText().toString().equals("") ||
                            themlophocphan_textbox_thoigianlythuyetketthuc.getText().toString().equals("") ) {
                        Toast.makeText(Quanly_trangchinh_quanlylophocphan_themlophocphan.this, "Có thông tin chưa điền đày đủ", Toast.LENGTH_SHORT).show();
                    } else if (dk_time(themlophocphan_textbox_thoigianlythuyetbatdau.getText().toString(), themlophocphan_textbox_thoigianlythuyetketthuc.getText().toString()) == false ) {
                    } else {
                        if(dk(thuchanhtiet)==true){
                            if(themlophocphan_textbox_thoigianthuchanhbatdau.getText().toString().equals("") ||themlophocphan_textbox_thoigianthuchanhketthuc.getText().toString().equals("")){
                                Toast.makeText(Quanly_trangchinh_quanlylophocphan_themlophocphan.this, "Có thông tin chưa điền đầy đủ", Toast.LENGTH_SHORT).show();
                            }else if(dk_time(themlophocphan_textbox_thoigianthuchanhbatdau.getText().toString(), themlophocphan_textbox_thoigianthuchanhketthuc.getText().toString()) == false){
                                Toast.makeText(Quanly_trangchinh_quanlylophocphan_themlophocphan.this, "Lỗi ngày bắt đầu sau ngày kết thúc", Toast.LENGTH_SHORT).show();
                            }else{
                                taolichlythuyet(themlophocphan_textbox_mamonhoc.getText().toString(), themlophocphan_textbox_thoigianlythuyetbatdau.getText().toString(),
                                        themlophocphan_textbox_thoigianlythuyetketthuc.getText().toString(), "lythuyet");
                                taolichlythuyet(themlophocphan_textbox_mamonhoc.getText().toString(), themlophocphan_textbox_thoigianthuchanhbatdau.getText().toString(),
                                        themlophocphan_textbox_thoigianthuchanhketthuc.getText().toString(), "thuchanh");
                                MainActivity.dulieu.themDuLieu("insert into LopHP (ma_MH,ten_MH,ten_GV,ma_GV,so_tc,tiet_LT,tiet_TH,ngay_LT_bd,ngay_LT_kt,ngay_TH_bd,ngay_TH_kt,ghi_chu,hoc_ky,loaiHP,tinhtrangHP,phonghoc) values ('"
                                        + themlophocphan_textbox_mamonhoc.getText().toString() + "','" + themlophocphan_textbox_tenmonhoc.getText().toString() + "','"
                                        + tengiangvien.trim() + "','" + magiangvien.trim() + "'," + Integer.parseInt(sotinchi) + ",'" + lythuyettiet + "','" + thuchanhtiet + "','"
                                        + themlophocphan_textbox_thoigianlythuyetbatdau.getText().toString() + "', '" + themlophocphan_textbox_thoigianlythuyetketthuc.getText().toString() + "','"
                                        + themlophocphan_textbox_thoigianthuchanhbatdau.getText().toString() + "','" + themlophocphan_textbox_thoigianthuchanhketthuc.getText().toString() + "','"
                                        + themlophocphan_textbox_ghichu.getText().toString() + "','" + hocky + "','" + loaihp + "','" + tinhtrang + "','" + phonghoc + "'  )");
                                Toast.makeText(Quanly_trangchinh_quanlylophocphan_themlophocphan.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                trove();
                            }
                        }else{
                            taolichlythuyet(themlophocphan_textbox_mamonhoc.getText().toString(), themlophocphan_textbox_thoigianlythuyetbatdau.getText().toString(),
                                    themlophocphan_textbox_thoigianlythuyetketthuc.getText().toString(), "lythuyet");

                            MainActivity.dulieu.themDuLieu("insert into LopHP (ma_MH,ten_MH,ten_GV,ma_GV,so_tc,tiet_LT,tiet_TH,ngay_LT_bd,ngay_LT_kt,ngay_TH_bd,ngay_TH_kt,ghi_chu,hoc_ky,loaiHP,tinhtrangHP,phonghoc) values ('"
                                    + themlophocphan_textbox_mamonhoc.getText().toString() + "','" + themlophocphan_textbox_tenmonhoc.getText().toString() + "','"
                                    + tengiangvien.trim() + "','" + magiangvien.trim() + "'," + Integer.parseInt(sotinchi) + ",'" + lythuyettiet + "','Không có thực hành','"
                                    + themlophocphan_textbox_thoigianlythuyetbatdau.getText().toString() + "', '" + themlophocphan_textbox_thoigianlythuyetketthuc.getText().toString() + "','"
                                    + "Không có thực hành" + "','" + "Không có thực hành" + "','"
                                    + themlophocphan_textbox_ghichu.getText().toString() + "','" + hocky + "','" + loaihp + "','" + tinhtrang + "','" + phonghoc + "'  )");
                            Toast.makeText(Quanly_trangchinh_quanlylophocphan_themlophocphan.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            trove();
                        }

                    }
                }catch (Exception e1){
                    Toast.makeText(Quanly_trangchinh_quanlylophocphan_themlophocphan.this, "Chưa chọn giảng viên", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //----------------------------------//
        themlophocphan_button_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trove();
            }
        });
        //----------------------------------//
    }
    public void themlophocphan_anhxa() {
        themlophocphan_textbox_mamonhoc = (EditText) findViewById(R.id.themlophocphan_textbox_mamonhoc);
        themlophocphan_textbox_tenmonhoc = (EditText) findViewById(R.id.themlophocphan_textbox_tenmonhoc);
        themlophocphan_textbox_thoigianlythuyetbatdau = (EditText) findViewById(R.id.themlophocphan_textbox_thoigianlythuyetbatdau);
        themlophocphan_textbox_thoigianlythuyetketthuc = (EditText) findViewById(R.id.themlophocphan_textbox_thoigianlythuyetketthuc);
        themlophocphan_textbox_thoigianthuchanhbatdau = (EditText) findViewById(R.id.themlophocphan_textbox_thoigianthuchanhbatdau);
        themlophocphan_textbox_thoigianthuchanhketthuc = (EditText) findViewById(R.id.themlophocphan_textbox_thoigianthuchanhketthuc);
        themlophocphan_textbox_ghichu = (EditText) findViewById(R.id.themlophocphan_textbox_ghichu);
        themlophocphan_button_huy = (Button) findViewById(R.id.themlophocphan_button_huy);
        themlophocphan_button_luu = (Button) findViewById(R.id.themlophocphan_button_luu);
        themlophocphan_spinner_hocky = (Spinner) findViewById(R.id.themlophocphan_spinner_hocky);
        themlophocphan_spinner_nam = (Spinner) findViewById(R.id.themlophocphan_spinner_nam);
        themlophocphan_spinner_giangvien = (Spinner) findViewById(R.id.themlophocphan_spinner_giangvien);
        themlophocphan_spinner_sotinchi = (Spinner) findViewById(R.id.themlophocphan_spinner_sotinchi);
        themlophocphan_spinner_loaihocphan = (Spinner) findViewById(R.id.themlophocphan_spinner_loaihocphan);
        themlophocphan_spinner_phonghoc = (Spinner) findViewById(R.id.themlophocphan_spinner_phonghoc);
        themlophocphan_spinner_tinhtrang = (Spinner) findViewById(R.id.themlophocphan_spinner_tinhtrang);
        themlophocphan_spinner_lythuyettiet = (Spinner) findViewById(R.id.themlophocphan_spinner_lythuyettiet);
        themlophocphan_spinner_thuchanhtiet = (Spinner) findViewById(R.id.themlophocphan_spinner_thuchanhtiet);
    }

    public String taoma(){
        int a = 0;
        try {
            MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select ma_MH from LopHP ");
            while (MainActivity.cs1.moveToNext()) {
                ma.add(MainActivity.cs1.getString(0));
            }
            a = Integer.parseInt(ma.get(ma.size() - 1));

        }catch (Exception ex){
            a = 0;
        }
        a++;
        String b = "";
        if(a<10){
            b = "00000000"+a;
        }else if(a>9&&a<100){
            b = "0000000"+a;
        }else if(a>99&&a<1000){
            b = "000000"+a;
        }else if(a>999&a<10000){
            b = "00000"+a;
        }else if(a>9999&&a<100000){
            b = "0000"+a;
        }else if(a>99999&&a<1000000){
            b = "000"+a;
        }else if(a>999999&&a<10000000){
            b = "00"+a;
        }else if(a>9999999&&a<100000000){
            b = "0"+a;
        }else if(a>99999999&&a<1000000000){
            b = String.valueOf(a);
        }
        return b;
    }
    public void hocky(){
        String date = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
        int year = Integer.parseInt(date);
        String[] dsnam = {String.valueOf(year-1),String.valueOf(year),String.valueOf(year+1)};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                dsnam);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.themlophocphan_spinner_nam.setAdapter(adapter2);
        themlophocphan_spinner_nam.setSelection(1);
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
        themlophocphan_spinner_giangvien.setAdapter(adaptergiangvien);
        themlophocphan_spinner_giangvien.setSelection(0);
    }
    public void trove(){
        finish();
        Intent myIntent = new Intent(Quanly_trangchinh_quanlylophocphan_themlophocphan.this,
                Quanly_trangchinh_quanlylophocphan.class);
        startActivity(myIntent);
    }
    private void updateLabel(EditText tg) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tg.setText(sdf.format(myCalendar.getTime()));
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
            Date datehientai = formatter.parse(tghientai);
            if(datebatdau.compareTo(dateketthuc)>0){
                c = false;

            }else {
                c = true;
            }
        }catch (Exception e1){
            c = false;
        }
        return c;
    }
    public static void taolichlythuyet(String malop,String ngaylythuyetbatdau,String ngaylythuyetketthuc,String loai) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        // khai báo CALENDAR
        Calendar ngaylythuyetbatdau_a = Calendar.getInstance();
        Calendar ngaylythuyetketthuc_a = Calendar.getInstance();

        try {
            // chuyển chuỗi nhập vào thành DATE
            Date ngaylythuyetbatdau_ab = formatter.parse(ngaylythuyetbatdau);
            Date ngaylythuyetketthuc_ab = formatter.parse(ngaylythuyetketthuc);

            // chuyển DATE nhập vào thành CALENDAR
            ngaylythuyetbatdau_a.setTime(ngaylythuyetbatdau_ab);
            ngaylythuyetketthuc_a.setTime(ngaylythuyetketthuc_ab);

            // insert vào sqlite ngày bắt đầu với chuỗi ngày theo FORMATER "dd/MM/yyy
            MainActivity.dulieu.themDuLieu("insert into LopHP_lichhoc(ma_MH, ngayhoc, loai) values ('"+malop+"','"+formatter.format(ngaylythuyetbatdau_a.getTime())+"','"+loai+"')  ");
            // vòng lặp while xét ngày bắt đầu liên tục cộng 7 ngày cho đến khi ngày bắt đầu COMPARE TO ngày kết thúc = 0
            // Mỗi vòng lặp insert ngày được cộng thêm 7 vào sqlite
            while (ngaylythuyetbatdau_a.compareTo(ngaylythuyetketthuc_a)<0){
                ngaylythuyetbatdau_a.setTime(ngay(ngaylythuyetbatdau_a));
                MainActivity.dulieu.themDuLieu("insert into LopHP_lichhoc(ma_MH, ngayhoc, loai) values ('"+malop+"','"+formatter.format(ngaylythuyetbatdau_a.getTime())+"','"+loai+"')  ");
            }


        } catch (Exception ex) {
        }
    }
    public static  Date ngay(Calendar a){
        String b="";

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        b = formatter.format(a.getTime());

        String[]c = b.split("/");
        int ngay = Integer.parseInt(c[0]);
        int thang = Integer.parseInt(c[1]);
        int nam = Integer.parseInt(c[2]);

        ngay = ngay + 7;
        if(thang == 1 || thang == 3 || thang == 5 || thang == 7 || thang == 8 || thang == 10 || thang == 12){
            if(ngay>31){
                ngay = ngay - 31;
                thang++;
                if(thang>12){
                    nam++;
                    thang = thang - 12;
                }else{
                    thang = thang;
                }
            }else{
                ngay =ngay;
            }
        }else if(thang == 4 || thang == 6 || thang == 9 || thang == 11){
            if(ngay>30){
                thang++;
                ngay = ngay - 30;
            }else{
                ngay =ngay;
            }
        }else if(thang == 2){
            if(((nam % 4 == 0) && (nam % 100 != 0))){
                if(ngay>29){
                    thang++;
                    ngay = ngay - 29;
                }else{
                    ngay = ngay;
                }
            }else if(nam % 400 == 0){
                if(ngay>28){
                    thang++;
                    ngay = ngay - 28;
                }else{
                    ngay = ngay;
                }
            }
        }
        String d = ngay+"/"+thang+"/"+nam;
        try {
            Date ngaytrave = formatter.parse(d);
            return ngaytrave;
        }catch (Exception e){
            Date ngaytrave = null;
            return ngaytrave;
        }
    }

    public interface OnBackPressedListener {
        void onBackPressed();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent myIntent = new Intent(Quanly_trangchinh_quanlylophocphan_themlophocphan.this,
                Quanly_trangchinh_quanlylophocphan.class);
        startActivity(myIntent);
    }
}