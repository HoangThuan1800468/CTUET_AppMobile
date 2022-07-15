package com.example.ctuetapp.User_Giangvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;

import java.util.ArrayList;

public class Giangvien_trangchinh_quanlylophocphan extends AppCompatActivity {
    String magv;
    ArrayList<String> ds = new ArrayList<>();
    ArrayList<String> dsmon =  new ArrayList<>();
    Spinner Giangvien_quanlylophocphan_spinner_hocky;
    ListView Giangvien_quanlylophocphan_listview_danhsachhocphan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giangvien_trangchinh_quanlylophocphan);
        Giangvien_quanlylophocphan_anhxa();
        magv = getIntent().getStringExtra("a");
        //---------------------------------------------------------------------------
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                ds);
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select distinct hoc_ky from LopHP ");
        ds.add("Chưa chọn");
        while (MainActivity.cs1.moveToNext()) {
            ds.add(MainActivity.cs1.getString(0));
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.Giangvien_quanlylophocphan_spinner_hocky.setAdapter(adapter);
        Giangvien_quanlylophocphan_spinner_hocky.setSelection(0);
        //---------------------------------------------------------------------------
        Giangvien_quanlylophocphan_spinner_hocky.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String chonhk = Giangvien_quanlylophocphan_spinner_hocky.getSelectedItem().toString();

                if(Giangvien_quanlylophocphan_spinner_hocky.getSelectedItem().toString().equals("Chưa chọn")) {
                    MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select ma_MH,ten_MH,ten_GV from LopHP where ma_GV = '" + magv + "' ");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Giangvien_trangchinh_quanlylophocphan.this, android.R.layout.simple_list_item_1, dsmon);
                    adapter.clear();
                    while (MainActivity.cs2.moveToNext()) {
                        dsmon.add(MainActivity.cs2.getString(0) + " " + MainActivity.cs2.getString(1) + " " + MainActivity.cs2.getString(2));
                    }
                    Giangvien_quanlylophocphan_listview_danhsachhocphan.setAdapter(adapter);
                    load();
                }else{
                    MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select ma_MH,ten_MH,ten_GV from LopHP where hoc_ky = '" + chonhk + "' and ma_GV = '" + magv + "' ");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Giangvien_trangchinh_quanlylophocphan.this, android.R.layout.simple_list_item_1, dsmon);
                    adapter.clear();
                    while (MainActivity.cs2.moveToNext()) {
                        dsmon.add(MainActivity.cs2.getString(0) + " " + MainActivity.cs2.getString(1) + " " + MainActivity.cs2.getString(2));
                    }
                    Giangvien_quanlylophocphan_listview_danhsachhocphan.setAdapter(adapter);
                    load();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //---------------------------------------------------------------------------
    }
    public void Giangvien_quanlylophocphan_anhxa(){
        Giangvien_quanlylophocphan_spinner_hocky = (Spinner) findViewById(R.id.Giangvien_quanlylophocphan_spinner_hocky);
        Giangvien_quanlylophocphan_listview_danhsachhocphan = (ListView) findViewById(R.id.Giangvien_quanlylophocphan_listview_danhsachhocphan);
    }
    private void load() {
        Giangvien_quanlylophocphan_listview_danhsachhocphan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int vitri, long id) {
                String[] parts = Giangvien_quanlylophocphan_listview_danhsachhocphan.getItemAtPosition(vitri).toString().split(" ");
                String part1 = parts[0];
                Intent myIntent = new Intent(Giangvien_trangchinh_quanlylophocphan.this,
                        Giangvien_trangchinh_quanlylophocphan_thongtinlophoc.class);
                myIntent.putExtra("a", part1);
                startActivity(myIntent);
            }
        });
    }
}