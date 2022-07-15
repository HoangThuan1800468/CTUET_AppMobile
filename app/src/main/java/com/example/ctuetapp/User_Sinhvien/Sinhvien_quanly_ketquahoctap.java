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
import android.widget.TextView;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;

import java.util.ArrayList;

public class Sinhvien_quanly_ketquahoctap extends AppCompatActivity {
    Spinner sinhvien_ketquahoctap_spinner_hocky;
    ListView sinhvien_ketquahoctap_listview_dshp;
    String masv;
    ArrayList<String> ds = new ArrayList<>();
    ArrayList<String> dsmon =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien_quanly_ketquahoctap);
        Sinhvien_ketquahoctap_anhxa();
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
        // Layout for All ROWs of Spinner.  (Optional for ArrayAdapter).
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.sinhvien_ketquahoctap_spinner_hocky.setAdapter(adapter);
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
        sinhvien_ketquahoctap_spinner_hocky.setSelection(timnam);

        sinhvien_ketquahoctap_spinner_hocky.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String chonhk = sinhvien_ketquahoctap_spinner_hocky.getSelectedItem().toString();
                MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select ma_MH,ten_MH,tinhtrangHP from LopHP where hoc_ky = '"+chonhk+"' and ma_MH in (SELECT ma_MH from LopHP_sinhvien where mSv = '"+masv+"')");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Sinhvien_quanly_ketquahoctap.this,android.R.layout.simple_list_item_1,dsmon);
                adapter.clear();
                while (MainActivity.cs2.moveToNext()) {
                    MainActivity.cs3 = MainActivity.dulieu.layDuLieu("select diemthuchanh,diemgiuaky,diemcuoiky  from LopHP_sinhvien where  mSv = '"+masv+"' and ma_MH = '"+MainActivity.cs2.getString(0)+"' ");
                    while (MainActivity.cs3.moveToNext()) {
                        if(Sinhvien_quanly_ketquahoctap_ketquahoctaptungmon.kt_thuchanh(MainActivity.cs2.getString(0))) {
                            dsmon.add(MainActivity.cs2.getString(0) + " " + MainActivity.cs2.getString(1) + " "
                                    + MainActivity.cs2.getString(2) + "\n" + "\n" + "Thực hành: " + MainActivity.cs3.getString(0)
                                    + " || Giữa kỳ: " + MainActivity.cs3.getString(1) + " || Cuối kỳ: " + MainActivity.cs3.getString(2) + "\n");
                        }else{
                            dsmon.add(MainActivity.cs2.getString(0) + " " + MainActivity.cs2.getString(1) + " "
                                    + MainActivity.cs2.getString(2) + "\n" + "\n" + "  Giữa kỳ: " + MainActivity.cs3.getString(1) + " || Cuối kỳ: " + MainActivity.cs3.getString(2) + "\n");
                        }
                    }
                }
                sinhvien_ketquahoctap_listview_dshp.setAdapter(adapter);
                sinhvien_ketquahoctap_listview_dshp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, View view, int vitri, long id) {
                        String[] parts = sinhvien_ketquahoctap_listview_dshp.getItemAtPosition(vitri).toString().split(" ");
                        String part1 = parts[0];
                        Intent myIntent = new Intent(Sinhvien_quanly_ketquahoctap.this, Sinhvien_quanly_ketquahoctap_ketquahoctaptungmon.class);
                        myIntent.putExtra("a",part1.trim());
                        myIntent.putExtra("b",masv);
                        startActivity(myIntent);
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void Sinhvien_ketquahoctap_anhxa(){
        sinhvien_ketquahoctap_spinner_hocky = (Spinner) findViewById(R.id.sinhvien_ketquahoctap_spinner_hocky);
        sinhvien_ketquahoctap_listview_dshp = (ListView) findViewById(R.id.sinhvien_ketquahoctap_listview_dshp);
    }
    private String loadnam(){
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        String tghientai = today.year+" " ;
        return tghientai;
    }

}