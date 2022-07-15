package com.example.ctuetapp.User_Quanly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;
import com.example.ctuetapp.User_Giangvien.Giangvien_bangdiemsinhvien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan extends AppCompatActivity {
    //----------------------------------//
    ListView danhsachsinhvienlophocphan_danhsachsinhvien;
    FloatingActionButton danhsachsinhvienlophocphan_button_add;
    //----------------------------------//
    String malop;
    ArrayList<String> ds_sv = new ArrayList<>();
    //----------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan);
        danhsachsinhvienlophocphan_anhxa();
        //----------------------------------//
        malop = getIntent().getStringExtra("a");
        kt_tinhtranglophoc(malop);
        //----------------------------------//
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select mSv from LopHP_sinhvien where ma_MH = '"+malop+"' ");
        //----------------------------------//
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.this,android.R.layout.simple_list_item_1,ds_sv);
        adapter.clear();
        while (MainActivity.cs1.moveToNext()) {
            MainActivity.cs3 = MainActivity.dulieu.layDuLieu("select tenSv from SinhVien where mSv = '"+MainActivity.cs1.getString(0)+"' ");
            while (MainActivity.cs3.moveToNext()){
                ds_sv.add(MainActivity.cs1.getString(0)+" - "+MainActivity.cs3.getString(0));
            }
        }
        //----------------------------------//
        danhsachsinhvienlophocphan_danhsachsinhvien.setAdapter(adapter);
        //----------------------------------//
        danhsachsinhvienlophocphan_danhsachsinhvien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int vitri, long id) {
                String [] a = danhsachsinhvienlophocphan_danhsachsinhvien.getItemAtPosition(vitri).toString().split(" - ");
                Intent myIntent = new Intent(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.this,
                        Giangvien_bangdiemsinhvien.class);
                myIntent.putExtra("a",a[0]);
                myIntent.putExtra("b",malop);
                startActivity(myIntent);
                finish();
            }
        });
        //----------------------------------//
        danhsachsinhvienlophocphan_button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tạo đối tượng
                AlertDialog.Builder b = new AlertDialog.Builder(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.this);
                //Thiết lập tiêu đề
                b.setTitle("Thêm vào danh sách");
                b.setMessage("Nhập mã số sinh viên:");
                final EditText input = new EditText(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.this);
                input.setInputType(2);
                b.setView(input);
                // Nút Ok
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String masv = input.getText().toString();
                        if(xetma(masv)==false) {

                        }else if(ktmasvtontai(masv)==false) {

                        }else if(xetma(masv)==true && ktmasvtontai(masv)==true){
                            MainActivity.dulieu.themDuLieu("insert into LopHP_sinhvien (ma_MH,mSv) values ('" + malop + "','" + masv + "')");
                            Toast.makeText(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.this, "Đã thêm sinh viên " + masv + " vào lớp học phần", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            reload();
                        }
                    }
                });
                //Nút Cancel
                b.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
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
    public void danhsachsinhvienlophocphan_anhxa() {
        danhsachsinhvienlophocphan_danhsachsinhvien = (ListView) findViewById(R.id.danhsachsinhvienlophocphan_danhsachsinhvien);
        danhsachsinhvienlophocphan_button_add = (FloatingActionButton) findViewById(R.id.danhsachsinhvienlophocphan_button_add);
    }
    private void reload(){
        Intent myIntent = new Intent(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.this,
                Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.class);
        myIntent.putExtra("a",malop);
        startActivity(myIntent);
        finish();
    }
    private boolean xetma(String a){
        boolean b = true;
        if(a.equals("")){
            Toast.makeText(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.this, "Chưa nhập mã sinh viên", Toast.LENGTH_SHORT).show();
            b = false;
        }else if(a.length()>7||a.length()<7){
            Toast.makeText(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.this, "Mã sinh viên có 7 ký tự là số", Toast.LENGTH_SHORT).show();
            b = false;
        }else {
            ArrayList<String> c = new ArrayList<>();
            MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select mSv from LopHP_sinhvien where ma_MH = '" + malop + "' ");
            while (MainActivity.cs2.moveToNext()) {
                c.add(MainActivity.cs2.getString(0));
            }
            for (int i = 0; i < c.size(); i++) {
                if (c.get(i).equals(a) == true) {
                    b = false;
                    Toast.makeText(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.this, "Sinh viên đã có trong lớp học phần", Toast.LENGTH_SHORT).show();
                    break;
                }
            }

        }
        return b;
    }
    private boolean ktmasvtontai(String m){
        boolean a = true;
        String c="";
        MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select mSv from Sinhvien where mSv = '" + m + "' ");
        while (MainActivity.cs2.moveToNext()) {
            c = MainActivity.cs2.getString(0);
        }
        if(c.equals("")==true){
            a = false;
            Toast.makeText(Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan.this, "Sinh viên không tồn tại", Toast.LENGTH_SHORT).show();
        }
        return a;
    }
    private void kt_tinhtranglophoc(String m){
        String c="";
        MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select tinhtrangHP from LopHP where ma_MH = '" + m + "' ");
        while (MainActivity.cs2.moveToNext()) {
            c = MainActivity.cs2.getString(0);
        }
        if(c.equals("Đã kết thúc")){
            danhsachsinhvienlophocphan_button_add.setEnabled(false);
        }else{
            danhsachsinhvienlophocphan_button_add.setEnabled(true);
        }
    }
}