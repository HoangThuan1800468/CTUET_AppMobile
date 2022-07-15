package com.example.ctuetapp.User_Giangvien;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Giangvien_trangchinh_lichday#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Giangvien_trangchinh_lichday extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Giangvien_trangchinh_lichday() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Giangvien_trangchinh_lichday.
     */
    // TODO: Rename and change types and number of parameters
    public static Giangvien_trangchinh_lichday newInstance(String param1, String param2) {
        Giangvien_trangchinh_lichday fragment = new Giangvien_trangchinh_lichday();
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


    ArrayList<String> dsmon =  new ArrayList<>();
    CalendarView Giangvien_lichday_calender_lich;
    ListView Giangvien_lichday_listview_monhoc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giangvien_trangchinh_lichday,container,false);

        Giangvien_lichday_listview_monhoc =(ListView) view.findViewById(R.id.Giangvien_lichday_listview_monhoc);
        Giangvien_lichday_calender_lich = (CalendarView)view.findViewById(R.id.Giangvien_lichday_calender_lich);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date =formatter.format(Giangvien_lichday_calender_lich.getDate());
        MainActivity.cs8 = MainActivity.dulieu.layDuLieu(" select ten_MH,ma_MH,phonghoc FROM LopHP WHERE ma_MH in (SELECT ma_MH FROM LopHP WHERE ma_GV = '"+ Giangvien_trangchinh.magv1+"' and ma_MH in (SELECT ma_MH FROM LopHP_lichhoc WHERE ngayhoc = '"+date+"')) ");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Giangvien_trangchinh_lichday.this.getActivity(),android.R.layout.simple_list_item_1,dsmon);
        adapter.clear();
        while (MainActivity.cs8.moveToNext()) {
            MainActivity.cs7 = MainActivity.dulieu.layDuLieu("  SELECT loai FROM LopHP_lichhoc WHERE ma_MH = '"+MainActivity.cs8.getString(1)+"' and ngayhoc = '"+date+"'   ");
            while (MainActivity.cs7.moveToNext()){
                String loai = MainActivity.cs7.getString(0);
                if(loai.equals("lythuyet")){
                    MainActivity.cs6 = MainActivity.dulieu.layDuLieu("  SELECT tiet_LT FROM LopHP WHERE ma_MH = '"+MainActivity.cs8.getString(1)+"'  ");
                    while (MainActivity.cs6.moveToNext()){
                        dsmon.add(MainActivity.cs8.getString(0)+" "+MainActivity.cs8.getString(1)+" "+MainActivity.cs8.getString(2)+" Lý thuyết: "+MainActivity.cs6.getString(0));
                    }
                }else{
                    MainActivity.cs6 = MainActivity.dulieu.layDuLieu("  SELECT tiet_TH FROM LopHP WHERE ma_MH = '"+MainActivity.cs8.getString(1)+"'  ");
                    while (MainActivity.cs6.moveToNext()){
                        dsmon.add(MainActivity.cs8.getString(0)+" "+MainActivity.cs8.getString(1)+" "+MainActivity.cs8.getString(2)+" Thực hành: "+MainActivity.cs6.getString(0));
                    }

                }

            }

        }
        Giangvien_lichday_listview_monhoc.setAdapter(adapter);
        Giangvien_lichday_calender_lich.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
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
                MainActivity.cs8 = MainActivity.dulieu.layDuLieu(" select ten_MH,ma_MH,phonghoc FROM LopHP WHERE ma_MH in (SELECT ma_MH FROM LopHP WHERE ma_GV = '"+ Giangvien_trangchinh.magv1+"' and ma_MH in (SELECT ma_MH FROM LopHP_lichhoc WHERE ngayhoc = '"+date+"')) ");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Giangvien_trangchinh_lichday.this.getActivity(),android.R.layout.simple_list_item_1,dsmon);
                adapter.clear();
                while (MainActivity.cs8.moveToNext()) {
                    MainActivity.cs7 = MainActivity.dulieu.layDuLieu("  SELECT loai FROM LopHP_lichhoc WHERE ma_MH = '"+MainActivity.cs8.getString(1)+"' and ngayhoc = '"+date+"'   ");
                    while (MainActivity.cs7.moveToNext()){
                        String loai = MainActivity.cs7.getString(0);
                        if(loai.equals("lythuyet")){
                            MainActivity.cs6 = MainActivity.dulieu.layDuLieu("  SELECT tiet_LT FROM LopHP WHERE ma_MH = '"+MainActivity.cs8.getString(1)+"'  ");
                            while (MainActivity.cs6.moveToNext()){
                                dsmon.add(MainActivity.cs8.getString(0)+" "+MainActivity.cs8.getString(1)+" "+MainActivity.cs8.getString(2)+" Lý thuyết: "+MainActivity.cs6.getString(0));
                            }
                        }else{
                            MainActivity.cs6 = MainActivity.dulieu.layDuLieu("  SELECT tiet_TH FROM LopHP WHERE ma_MH = '"+MainActivity.cs8.getString(1)+"'  ");
                            while (MainActivity.cs6.moveToNext()){
                                dsmon.add(MainActivity.cs8.getString(0)+" "+MainActivity.cs8.getString(1)+" "+MainActivity.cs8.getString(2)+" Thực hành: "+MainActivity.cs6.getString(0));
                            }

                        }

                    }

                }
                Giangvien_lichday_listview_monhoc.setAdapter(adapter);
            }
        });
        return view;
    }
}