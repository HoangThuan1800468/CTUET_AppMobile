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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;

import java.util.ArrayList;

public class Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan extends AppCompatActivity {
    //----------------------------------//
    EditText danhsachtaikhoan_textbox_tim;
    Button danhsachtaikhoan_button_tim;
    Spinner danhsachtaikhoan_spinner_loaitaikhoan;
    ListView danhsachtaikhoan_listview_danhsachtaikhoan;
    //----------------------------------//
    ArrayList<String> dsma = new ArrayList<>();
    String[] ds= {"Chưa chọn","Sinh viên","Giảng viên","Quản lý"};
    //----------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan);
        danhsachtaikhoan_anhxa();

        //----------------------------------//

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                ds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.danhsachtaikhoan_spinner_loaitaikhoan.setAdapter(adapter);

        //----------------------------------//

        danhsachtaikhoan_spinner_loaitaikhoan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                danhsachtaikhoan_textbox_tim.setText("");
                String loai = danhsachtaikhoan_spinner_loaitaikhoan.getSelectedItem().toString();
                if (loai.equals("Chưa chọn")){
                    MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select ma,ten,loai_TK from TaiKhoan ");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.this,android.R.layout.simple_list_item_1,dsma);
                    adapter.clear();
                    while (MainActivity.cs1.moveToNext()) {
                        dsma.add(MainActivity.cs1.getString(0)+" - "+MainActivity.cs1.getString(1)+" - "+MainActivity.cs1.getString(2));
                    }
                    danhsachtaikhoan_listview_danhsachtaikhoan.setAdapter(adapter);
                    loadma();
                }else {
                    loadlistview(loai);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //----------------------------------//
        danhsachtaikhoan_button_tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(danhsachtaikhoan_textbox_tim.getText().toString().trim().equals("")){
                    Toast.makeText(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.this, "Chưa nhập mã số cần tìm", Toast.LENGTH_SHORT).show();
                }else{
                    if(danhsachtaikhoan_spinner_loaitaikhoan.getSelectedItem().toString().equals("Chưa chọn")) {
                        loadlistviewtim_2(danhsachtaikhoan_textbox_tim.getText().toString().trim());
                    }else{
                        loadlistviewtim(danhsachtaikhoan_spinner_loaitaikhoan.getSelectedItem().toString(), danhsachtaikhoan_textbox_tim.getText().toString().trim());
                    }
                }
            }
        });
        //----------------------------------//
        //----------------------------------//
    }

    public void danhsachtaikhoan_anhxa() {
        danhsachtaikhoan_textbox_tim = (EditText) findViewById(R.id.danhsachtaikhoan_textbox_tim);
        danhsachtaikhoan_button_tim = (Button) findViewById(R.id.danhsachtaikhoan_button_tim);
        danhsachtaikhoan_spinner_loaitaikhoan = (Spinner) findViewById(R.id.danhsachtaikhoan_spinner_loaitaikhoan);
        danhsachtaikhoan_listview_danhsachtaikhoan = (ListView) findViewById(R.id.danhsachtaikhoan_listview_danhsachtaikhoan);
    }

    private void loadlistview(String loai){
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select ma,ten,loai_TK from TaiKhoan where loai_TK = '"+loai+"' ");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.this,android.R.layout.simple_list_item_1,dsma);
        adapter.clear();
        while (MainActivity.cs1.moveToNext()) {
            dsma.add(MainActivity.cs1.getString(0)+" - "+MainActivity.cs1.getString(1)+" - "+MainActivity.cs1.getString(2));
        }
        danhsachtaikhoan_listview_danhsachtaikhoan.setAdapter(adapter);
        loadma();
    }
    private void loadlistviewtim_2(String maso){
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select ma,ten,loai_TK from TaiKhoan where ma like '%"+maso+"%' ");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.this,android.R.layout.simple_list_item_1,dsma);
        adapter.clear();
        while (MainActivity.cs1.moveToNext()) {
            dsma.add(MainActivity.cs1.getString(0)+" - "+MainActivity.cs1.getString(1)+" - "+MainActivity.cs1.getString(2));
        }
        danhsachtaikhoan_listview_danhsachtaikhoan.setAdapter(adapter);
        loadma();
    }
    private void loadlistviewtim(String loai, String maso){
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select ma,ten,loai_TK from TaiKhoan where loai_TK = '"+loai+"' and ma like '%"+maso+"%' ");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.this,android.R.layout.simple_list_item_1,dsma);
        adapter.clear();
        while (MainActivity.cs1.moveToNext()) {
            dsma.add(MainActivity.cs1.getString(0)+" - "+MainActivity.cs1.getString(1)+" - "+MainActivity.cs1.getString(2));
        }
        danhsachtaikhoan_listview_danhsachtaikhoan.setAdapter(adapter);
        loadma();
    }

    private void loadma(){
        danhsachtaikhoan_listview_danhsachtaikhoan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int vitri, long id) {
                String[] parts = danhsachtaikhoan_listview_danhsachtaikhoan.getItemAtPosition(vitri).toString().split(" - ");
                Intent myIntent = new Intent(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.this,
                        Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan_thongtintaikhoan.class);
                myIntent.putExtra("ma",parts[0]);
                myIntent.putExtra("ten",parts[1]);
                if(parts[2].equals("Giảng viên")||parts[2].equals("Quản lý")){
                    //Tạo đối tượng
                    AlertDialog.Builder b = new AlertDialog.Builder(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.this);
                    //Thiết lập tiêu danh
                    b.setTitle("Xác nhận!");
                    b.setMessage("Xóa tài khoản "+parts[2]);
                    // Nút Ok
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if(parts[2].equals("Giảng viên")) {
                                MainActivity.dulieu.themDuLieu("DELETE FROM TaiKhoan  where ma = '" + parts[0] + "' ");
                                MainActivity.dulieu.themDuLieu("DELETE FROM GiangVien  where mGv = '" + parts[0] + "' ");
                                reload();
                                Toast.makeText(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                            }if(parts[2].equals("Quản lý")){
                                MainActivity.dulieu.themDuLieu("DELETE FROM TaiKhoan  where ma = '" + parts[0] + "' ");
                                MainActivity.dulieu.themDuLieu("DELETE FROM Quanly  where mQl = '" + parts[0] + "' ");
                                reload();
                                Toast.makeText(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                            }
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
                }else if(parts[2].equals("Sinh viên")) {
                    startActivity(myIntent);
                    finish();
                }
            }
        });
    }

    //----------------------------------//
    public interface OnBackPressedListener {
        void onBackPressed();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent myIntent = new Intent(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.this, Quanly_trangchinh_quanlytaikhoan.class);
        startActivity(myIntent);
    }
    private void reload(){
        finish();
        Intent myIntent = new Intent(Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.this, Quanly_trangchinh_quanlytaikhoan_danhsachtaikhoan.class);
        startActivity(myIntent);
    }
    //----------------------------------//
}