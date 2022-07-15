package com.example.ctuetapp.User_Sinhvien;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sinhvien_trangchinh_lichhoc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sinhvien_trangchinh_lichhoc extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Sinhvien_trangchinh_lichhoc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sinhvien_trangchinh_lichhoc.
     */
    // TODO: Rename and change types and number of parameters
    public static Sinhvien_trangchinh_lichhoc newInstance(String param1, String param2) {
        Sinhvien_trangchinh_lichhoc fragment = new Sinhvien_trangchinh_lichhoc();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    CalendarView sinhvien_lichhoc_calendar_lich;
    ListView sinhvien_lichhoc_listview_danhsachhocphan;
    ArrayList<String> dsmon =  new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sinhvien_trangchinh_lichhoc,container,false);
        sinhvien_lichhoc_listview_danhsachhocphan =(ListView) view.findViewById(R.id.sinhvien_lichhoc_listview_danhsachhocphan);
        sinhvien_lichhoc_calendar_lich = (CalendarView)view.findViewById(R.id.sinhvien_lichhoc_calendar_lich);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date =formatter.format(sinhvien_lichhoc_calendar_lich.getDate());
        MainActivity.cs8 = MainActivity.dulieu.layDuLieu(" select ten_MH,ma_MH,phonghoc FROM LopHP WHERE ma_MH in (SELECT ma_MH FROM LopHP_sinhvien WHERE mSv = '"+Sinhvien_trangchinh.masv+"' and ma_MH in (SELECT ma_MH FROM LopHP_lichhoc WHERE ngayhoc = '"+date+"')) ");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Sinhvien_trangchinh_lichhoc.this.getActivity(),android.R.layout.simple_list_item_1,dsmon);
        adapter.clear();
        while (MainActivity.cs8.moveToNext()) {
            MainActivity.cs7 = MainActivity.dulieu.layDuLieu("  SELECT loai FROM LopHP_lichhoc WHERE ma_MH = '"
                                                            +MainActivity.cs8.getString(1)+"' and ngayhoc = '"+date+"'   ");
            while (MainActivity.cs7.moveToNext()){
                String loai = MainActivity.cs7.getString(0);
                if(loai.equals("lythuyet")){
                    MainActivity.cs6 = MainActivity.dulieu.layDuLieu("  SELECT tiet_LT FROM LopHP WHERE ma_MH = '"+MainActivity.cs8.getString(1)+"'  ");
                    while (MainActivity.cs6.moveToNext()){
                        dsmon.add(MainActivity.cs8.getString(0)+" "+MainActivity.cs8.getString(1)+" "
                                +MainActivity.cs8.getString(2)+" Lý thuyết: "+MainActivity.cs6.getString(0));
                    }
                }else{
                    MainActivity.cs6 = MainActivity.dulieu.layDuLieu("  SELECT tiet_TH FROM LopHP WHERE ma_MH = '"+MainActivity.cs8.getString(1)+"'  ");
                    while (MainActivity.cs6.moveToNext()){
                        dsmon.add(MainActivity.cs8.getString(0)+" "+MainActivity.cs8.getString(1)+" "
                                +MainActivity.cs8.getString(2)+" Thực hành: "+MainActivity.cs6.getString(0));
                    }
                }
            }
        }
        sinhvien_lichhoc_listview_danhsachhocphan.setAdapter(adapter);

        sinhvien_lichhoc_calendar_lich.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String ngay ="";
                if(dayOfMonth<10){
                    ngay = "0"+dayOfMonth;
                }else if (dayOfMonth>=10){
                    ngay = String.valueOf(dayOfMonth);
                }
                month++;
                if(month<=12){
                    month = month;
                }else if(month >12){
                    month = month -12;
                }
                String thang = "";
                if(month<10){
                    thang = "0"+month;
                }else if (month>=10){
                    thang = String.valueOf(month);
                }
                String date = ngay+"/"+thang+"/"+year;
                MainActivity.cs8 = MainActivity.dulieu.layDuLieu(" select ten_MH,ma_MH,phonghoc FROM LopHP WHERE ma_MH in (SELECT ma_MH FROM LopHP_sinhvien WHERE mSv = '"+Sinhvien_trangchinh.masv+"' and ma_MH in (SELECT ma_MH FROM LopHP_lichhoc WHERE ngayhoc = '"+date+"')) ");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Sinhvien_trangchinh_lichhoc.this.getActivity(),android.R.layout.simple_list_item_1,dsmon);
                adapter.clear();
                while (MainActivity.cs8.moveToNext()) {
                    MainActivity.cs7 = MainActivity.dulieu.layDuLieu("  SELECT loai FROM LopHP_lichhoc WHERE ma_MH = '"+MainActivity.cs8.getString(1)+"' and ngayhoc = '"+date+"'   ");
                    while (MainActivity.cs7.moveToNext()){
                        String loai = MainActivity.cs7.getString(0);
                        if(loai.equals("lythuyet")){
                            MainActivity.cs6 = MainActivity.dulieu.layDuLieu("  SELECT tiet_LT FROM LopHP WHERE ma_MH = '"+MainActivity.cs8.getString(1)+"'  ");
                            while (MainActivity.cs6.moveToNext()){
                                dsmon.add(MainActivity.cs8.getString(0)+" "+MainActivity.cs8.getString(1)+" "
                                        +MainActivity.cs8.getString(2)+" Lý thuyết: "+MainActivity.cs6.getString(0));
                            }
                        }else{
                            MainActivity.cs6 = MainActivity.dulieu.layDuLieu("  SELECT tiet_TH FROM LopHP WHERE ma_MH = '"+MainActivity.cs8.getString(1)+"'  ");
                            while (MainActivity.cs6.moveToNext()){
                                dsmon.add(MainActivity.cs8.getString(0)+" "+MainActivity.cs8.getString(1)+" "+
                                        MainActivity.cs8.getString(2)+" Thực hành: "+MainActivity.cs6.getString(0));
                            }

                        }

                    }

                }
                sinhvien_lichhoc_listview_danhsachhocphan.setAdapter(adapter);
            }
        });


        return view;
    }
}