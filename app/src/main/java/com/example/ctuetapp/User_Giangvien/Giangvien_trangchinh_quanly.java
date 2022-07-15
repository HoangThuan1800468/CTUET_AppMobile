package com.example.ctuetapp.User_Giangvien;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;
import com.example.ctuetapp.User_Quanly.Quanly_dangnhap_doimatkhau;
import com.example.ctuetapp.User_Quanly.Quanly_trangchinh_quanlylophocphan;
import com.example.ctuetapp.User_Quanly.Quanly_trangchinh_quanlylophocphan_themlophocphan;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Giangvien_trangchinh_quanly#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Giangvien_trangchinh_quanly extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Giangvien_trangchinh_quanly() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Giangvien_trangchinh_quanly.
     */
    // TODO: Rename and change types and number of parameters
    public static Giangvien_trangchinh_quanly newInstance(String param1, String param2) {
        Giangvien_trangchinh_quanly fragment = new Giangvien_trangchinh_quanly();
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
    TextView Giangvien_quanly_textview_email;
    ListView Giangvien_quanly_lisview_quanly;
    ArrayAdapter<String> adapter;
    ImageView imagelogo;
    String[] menuItems= {"Quản lý lớp học phần","Gửi thông báo","Tài khoản","Đăng xuất"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giangvien_trangchinh_quanly,container,false);

        Giangvien_quanly_textview_email = (TextView)view.findViewById(R.id.Giangvien_quanly_textview_email);
        Giangvien_quanly_lisview_quanly =(ListView) view.findViewById(R.id.Giangvien_quanly_lisview_quanly);
        imagelogo = (ImageView) view.findViewById(R.id.imagelogo);
        imagelogo.setImageResource(R.drawable.logo);

        MainActivity.cs = MainActivity.dulieu.layDuLieu("select mail from TaiKhoan where ma = '"+Giangvien_trangchinh.magv1+"' ");
        while (MainActivity.cs.moveToNext()){
            Giangvien_quanly_textview_email.setText(MainActivity.cs.getString(0));
        }
        adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems
        );
        Giangvien_quanly_lisview_quanly.setAdapter(adapter);
        Giangvien_quanly_lisview_quanly.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int vitri, long id) {
                if(vitri == 0){
                    Intent myIntent = new Intent(Giangvien_trangchinh_quanly.this.getActivity(), Giangvien_trangchinh_quanlylophocphan.class);
                    myIntent.putExtra("a",Giangvien_trangchinh.magv1);
                    startActivity(myIntent);
                }
                else if(vitri == 1){
                    Intent myIntent = new Intent(Giangvien_trangchinh_quanly.this.getActivity(), Giangvien_trangchinh_guithongbao.class);
                    startActivity(myIntent);
                }else if(vitri == 2){
                    Intent myIntent = new Intent(Giangvien_trangchinh_quanly.this.getActivity(), Quanly_dangnhap_doimatkhau.class);
                    myIntent.putExtra("a", Giangvien_trangchinh.magv1);
                    startActivity(myIntent);
                }else if(vitri == 3){
                    Intent myIntent = new Intent(Giangvien_trangchinh_quanly.this.getActivity(), MainActivity.class);
                    startActivity(myIntent);
                }
            }
        });
        return view;
    }
}