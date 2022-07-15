package com.example.ctuetapp.User_Sinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;

import java.util.ArrayList;

public class Sinhvien_quanly_danhsachlop extends AppCompatActivity {
    String masv;
    ArrayList<String> ds = new ArrayList<>();
    ArrayList<String> dsmon =  new ArrayList<>();
    ListView sinhvien_danhsachlophocphan_listview_danhsachhocphan;
    Spinner sinhvien_danhsachlophocphan_spinner_hocky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien_quanly_danhsachlop);
        Sinhvien_danhsachlop_anhxa();
        masv=getIntent().getStringExtra("a");
        String loadhk= "";
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                ds);
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select khoa_hoc from SinhVien where mSv = '"+masv+"' ");
        while (MainActivity.cs1.moveToNext()) {
            loadhk = MainActivity.cs1.getString(0);
        }
        int sonam = Integer.parseInt(loadhk);
        ds.add("Chưa chọn");
        ds.add("HKI - "+sonam);
        ds.add("HKII - "+sonam);
        ds.add("HKIII - "+sonam);
        ds.add("HKI - "+(sonam+1));
        ds.add("HKII - "+(sonam+1));
        ds.add("HKIII - "+(sonam+1));
        ds.add("HKI - "+(sonam+2));
        ds.add("HKII - "+(sonam+2));
        ds.add("HKIII - "+(sonam+2));
        ds.add("HKI - "+(sonam+3));
        ds.add("HKII - "+(sonam+3));
        ds.add("HKIII - "+(sonam+3));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.sinhvien_danhsachlophocphan_spinner_hocky.setAdapter(adapter);
        int timnam = 0;
        for(int i = 1;i<ds.size();i++){
            String a = ds.get(i);
            String[] b = a.split(" ");
            int c = Integer.parseInt(b[2]);
            if(c == Integer.parseInt(loadnam().trim())){
                timnam = i;
                break;
            }
        }
        sinhvien_danhsachlophocphan_spinner_hocky.setSelection(timnam);
        sinhvien_danhsachlophocphan_spinner_hocky.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String chonhk = sinhvien_danhsachlophocphan_spinner_hocky.getSelectedItem().toString();
                MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select ma_MH,ten_MH,ten_GV from LopHP where hoc_ky = '"+chonhk+"' and ma_MH in (SELECT ma_MH from LopHP_sinhvien where mSv = '"+masv+"')");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Sinhvien_quanly_danhsachlop.this,android.R.layout.simple_list_item_1,dsmon);
                adapter.clear();
                while (MainActivity.cs2.moveToNext()) {
                    dsmon.add(MainActivity.cs2.getString(0)+" "+MainActivity.cs2.getString(1)+" "+MainActivity.cs2.getString(2));
                }
                sinhvien_danhsachlophocphan_listview_danhsachhocphan.setAdapter(adapter);
                sinhvien_danhsachlophocphan_listview_danhsachhocphan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, View view, int vitri, long id) {
                        String[] parts = sinhvien_danhsachlophocphan_listview_danhsachhocphan.getItemAtPosition(vitri).toString().split(" ");
                        String part1 = parts[0];
                        Intent myIntent = new Intent(Sinhvien_quanly_danhsachlop.this, Sinhvien_quanly_danhsachlop_thongtinlophocphan.class);
                        myIntent.putExtra("a",part1.trim());
                        startActivity(myIntent);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void Sinhvien_danhsachlop_anhxa(){
        sinhvien_danhsachlophocphan_listview_danhsachhocphan = (ListView) findViewById(R.id.sinhvien_danhsachlophocphan_listview_danhsachhocphan);
        sinhvien_danhsachlophocphan_spinner_hocky = (Spinner) findViewById(R.id.sinhvien_danhsachlophocphan_spinner_hocky);
    }
    private String loadnam(){
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        String tghientai = today.year+" " ;
        return tghientai;
    }
}