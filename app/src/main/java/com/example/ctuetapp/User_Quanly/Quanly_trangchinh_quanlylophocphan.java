package com.example.ctuetapp.User_Quanly;

import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Quanly_trangchinh_quanlylophocphan extends AppCompatActivity {
    //----------------------------------//
    EditText quanly_trangchinh_quanlylophocphan_textbox_tim;
    Button quanly_trangchinh_quanlylophocphan_button_tim;
    FloatingActionButton quanly_trangchinh_quanlylophocphan_button_them;
    Spinner quanly_trangchinh_quanlylophocphan_spinner_chonhocky;
    ListView quanly_trangchinh_quanlylophocphan_listview_lophocphan;
    //----------------------------------//
    ArrayList<String> ds = new ArrayList<>();
    ArrayList<String> dsmon =  new ArrayList<>();
    //----------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_trangchinh_quanlylophocphan);
        quanly_trangchinh_quanlylophocphan_anhxa();

        //----------------------------------//

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                ds);
        ds.add("Chưa chọn");
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select distinct hoc_ky from LopHP  ");
        while (MainActivity.cs1.moveToNext()) {
            ds.add(MainActivity.cs1.getString(0));
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.quanly_trangchinh_quanlylophocphan_spinner_chonhocky.setAdapter(adapter);

        //----------------------------------//

        quanly_trangchinh_quanlylophocphan_spinner_chonhocky.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String chonhk = quanly_trangchinh_quanlylophocphan_spinner_chonhocky.getSelectedItem().toString();

                if(quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString().equals("") && !quanly_trangchinh_quanlylophocphan_spinner_chonhocky.getSelectedItem().toString().equals("Chưa chọn")){
                    MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select ma_MH,ten_MH,ten_GV from LopHP where hoc_ky = '"+chonhk+"' ");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Quanly_trangchinh_quanlylophocphan.this,android.R.layout.simple_list_item_1,dsmon);
                    adapter.clear();
                    while (MainActivity.cs2.moveToNext()) {
                        dsmon.add(MainActivity.cs2.getString(0)+" "+MainActivity.cs2.getString(1)+" "+MainActivity.cs2.getString(2));
                    }
                    quanly_trangchinh_quanlylophocphan_listview_lophocphan.setAdapter(adapter);
                    load ();
                }else if(quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString().equals("") && quanly_trangchinh_quanlylophocphan_spinner_chonhocky.getSelectedItem().toString().equals("Chưa chọn") ){
                    MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select ma_MH,ten_MH,ten_GV from LopHP  ");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Quanly_trangchinh_quanlylophocphan.this,android.R.layout.simple_list_item_1,dsmon);
                    adapter.clear();
                    while (MainActivity.cs2.moveToNext()) {
                        dsmon.add(MainActivity.cs2.getString(0)+" "+MainActivity.cs2.getString(1)+" "+MainActivity.cs2.getString(2));
                    }
                    quanly_trangchinh_quanlylophocphan_listview_lophocphan.setAdapter(adapter);
                    load ();
                }
                else if(!quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString().equals("")&&quanly_trangchinh_quanlylophocphan_spinner_chonhocky.getSelectedItem().toString().equals("Chưa chọn")){
                    MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select ma_MH,ten_MH,ten_GV from LopHP where ma_MH like '%"+quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString()+"%' or ten_MH like '%"
                            +quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString()+"%' or ten_GV like '%"+quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString()+"%' ");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Quanly_trangchinh_quanlylophocphan.this,android.R.layout.simple_list_item_1,dsmon);
                    adapter.clear();
                    while (MainActivity.cs2.moveToNext()) {
                        dsmon.add(MainActivity.cs2.getString(0)+" "+MainActivity.cs2.getString(1)+" "+MainActivity.cs2.getString(2));
                    }
                    quanly_trangchinh_quanlylophocphan_listview_lophocphan.setAdapter(adapter);
                    load ();
                }
                else{
                    MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select ma_MH,ten_MH,ten_GV from LopHP where hoc_ky = '"+chonhk+"' and (ma_MH like '%"+quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString()
                            +"%' or ten_MH like '%"+quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString()
                            +"%' or ten_GV like '%"+quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString()+"%') ");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Quanly_trangchinh_quanlylophocphan.this,android.R.layout.simple_list_item_1,dsmon);
                    adapter.clear();
                    while (MainActivity.cs2.moveToNext()) {
                        dsmon.add(MainActivity.cs2.getString(0)+" "+MainActivity.cs2.getString(1)+" "+MainActivity.cs2.getString(2));
                    }
                    quanly_trangchinh_quanlylophocphan_listview_lophocphan.setAdapter(adapter);
                    load ();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //----------------------------------//

        quanly_trangchinh_quanlylophocphan_button_tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quanly_trangchinh_quanlylophocphan_spinner_chonhocky.setSelection(0);
                if(quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString().equals("")){
                    MainActivity.cs3 = MainActivity.dulieu.layDuLieu("select ma_MH,ten_MH,ten_GV from LopHP ");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Quanly_trangchinh_quanlylophocphan.this,android.R.layout.simple_list_item_1,dsmon);
                    adapter.clear();
                    while (MainActivity.cs3.moveToNext()) {
                        dsmon.add(MainActivity.cs3.getString(0)+" "+MainActivity.cs3.getString(1)+" "+MainActivity.cs3.getString(2));
                    }
                    quanly_trangchinh_quanlylophocphan_listview_lophocphan.setAdapter(adapter);
                    load ();
                }else{
                    Toast.makeText(Quanly_trangchinh_quanlylophocphan.this, quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString(), Toast.LENGTH_SHORT).show();
                    MainActivity.cs3 = MainActivity.dulieu.layDuLieu("select ma_MH,ten_MH,ten_GV from LopHP where ma_MH like '%"+quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString()+"%' or ten_MH like '%"
                            +quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString()+"%' or ten_GV like '%"
                            +quanly_trangchinh_quanlylophocphan_textbox_tim.getText().toString()+"%' ");

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Quanly_trangchinh_quanlylophocphan.this,android.R.layout.simple_list_item_1,dsmon);
                    adapter.clear();
                    while (MainActivity.cs3.moveToNext()) {
                        dsmon.add(MainActivity.cs3.getString(0)+" "+MainActivity.cs3.getString(1)+" "+MainActivity.cs3.getString(2));
                    }
                    quanly_trangchinh_quanlylophocphan_listview_lophocphan.setAdapter(adapter);

                    load ();
                }
            }
        });

        //----------------------------------//

        quanly_trangchinh_quanlylophocphan_button_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Quanly_trangchinh_quanlylophocphan.this,
                        Quanly_trangchinh_quanlylophocphan_themlophocphan.class);
                startActivity(myIntent);
                reload ();
            }
        });

        //----------------------------------//
    }

    public void quanly_trangchinh_quanlylophocphan_anhxa() {
        quanly_trangchinh_quanlylophocphan_textbox_tim = (EditText) findViewById(R.id.quanly_trangchinh_quanlylophocphan_textbox_tim);
        quanly_trangchinh_quanlylophocphan_button_tim = (Button) findViewById(R.id.quanly_trangchinh_quanlylophocphan_button_tim);
        quanly_trangchinh_quanlylophocphan_button_them = (FloatingActionButton) findViewById(R.id.quanly_trangchinh_quanlylophocphan_button_them);
        quanly_trangchinh_quanlylophocphan_spinner_chonhocky = (Spinner) findViewById(R.id.quanly_trangchinh_quanlylophocphan_spinner_chonhocky);
        quanly_trangchinh_quanlylophocphan_listview_lophocphan = (ListView) findViewById(R.id.quanly_trangchinh_quanlylophocphan_listview_lophocphan);
    }

    private void load (){
        quanly_trangchinh_quanlylophocphan_listview_lophocphan.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int vitri, long id) {
                String[] parts = quanly_trangchinh_quanlylophocphan_listview_lophocphan.getItemAtPosition(vitri).toString().split(" ");
                String part1 = parts[0];
                Intent myIntent = new Intent(Quanly_trangchinh_quanlylophocphan.this, Quanly_trangchinh_quanlylophocphan_thongtinlophocphan.class);
                myIntent.putExtra("a",part1);
                startActivity(myIntent);
                reload ();
            }
        });
    }

    private void reload (){
        finish();
    }

}