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

import java.util.ArrayList;

public class Quanly_trangchinh_quanlytaikhoan_danhsachcaplaimatkhau extends AppCompatActivity {
    //----------------------------------//
    ListView danhsachxincaplaimatkhau_listview_danhsachtaikhoan;

    ArrayList<String> ds= new ArrayList<>();
    //----------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_trangchinh_quanlytaikhoan_danhsachcaplaimatkhau);
        danhsachcaplaimatkhau_anhxa();
        //----------------------------------//
        MainActivity.cs = MainActivity.dulieu.layDuLieu("select ma,ten,mk,loai_TK,mail from TaiKhoan where  tinh_trang = 'NOTACTV' ");
        while (MainActivity.cs.moveToNext()){
            ds.add(MainActivity.cs.getString(0)+" - "+MainActivity.cs.getString(1)+"\n"+"Mật khẩu mới: "+MainActivity.cs.getString(2)+"\n"+"Loại tài khoản: "+MainActivity.cs.getString(3)+"\n"+MainActivity.cs.getString(4));

        }
        //----------------------------------//
        ArrayAdapter<String> lisViewAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                ds
        );
        danhsachxincaplaimatkhau_listview_danhsachtaikhoan.setAdapter(lisViewAdapter);
        //----------------------------------//
        danhsachxincaplaimatkhau_listview_danhsachtaikhoan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int vitri, long id) {
                String [] ma = danhsachxincaplaimatkhau_listview_danhsachtaikhoan.getItemAtPosition(vitri).toString().split(" - ");
                //Tạo đối tượng
                AlertDialog.Builder b = new AlertDialog.Builder(Quanly_trangchinh_quanlytaikhoan_danhsachcaplaimatkhau.this);
                //Thiết lập tiêu đề
                b.setTitle("Xác nhận tài khoản");
                final EditText input = new EditText(Quanly_trangchinh_quanlytaikhoan_danhsachcaplaimatkhau.this);
                // Nút Ok
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.dulieu.themDuLieu("update TaiKhoan set tinh_trang = 'Active' where ma = '"+ma[0]+"' ");
                        Toast.makeText(Quanly_trangchinh_quanlytaikhoan_danhsachcaplaimatkhau.this, "Tài khoản đã được mở khóa", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                        finish();
                        Intent intent = new Intent(Quanly_trangchinh_quanlytaikhoan_danhsachcaplaimatkhau.this, Quanly_trangchinh_quanlytaikhoan_danhsachcaplaimatkhau.class);
                        startActivity(intent);
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
    public void danhsachcaplaimatkhau_anhxa() {
        danhsachxincaplaimatkhau_listview_danhsachtaikhoan = (ListView) findViewById(R.id.danhsachxincaplaimatkhau_listview_danhsachtaikhoan);
        }

    public interface OnBackPressedListener {
        void onBackPressed();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent myIntent = new Intent(Quanly_trangchinh_quanlytaikhoan_danhsachcaplaimatkhau.this, Quanly_trangchinh_quanlytaikhoan.class);
        startActivity(myIntent);
    }

}