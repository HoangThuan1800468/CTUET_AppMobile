package com.example.ctuetapp.User_Sinhvien;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sinhvien_trangchinh_quanly#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sinhvien_trangchinh_quanly extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Sinhvien_trangchinh_quanly() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sinhvien_trangchinh_quanly.
     */
    // TODO: Rename and change types and number of parameters
    public static Sinhvien_trangchinh_quanly newInstance(String param1, String param2) {
        Sinhvien_trangchinh_quanly fragment = new Sinhvien_trangchinh_quanly();
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
    TextView sinhvien_thongtinsinhvien_textview_email;
    ListView sinhvien_thongtinsinhvien_listview_thongtin;
    String[] menuItems= {"Thông tin cá nhân","Thông báo cá nhân sinh viên","Danh sách lớp học phần","Kết quả học tập","Tài khoản","Đăng xuất"};
    String masv = Sinhvien_trangchinh.masv;
    ImageView hinhssv;
    ArrayAdapter<String> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sinhvien_trangchinh_quanly,container,false);
        sinhvien_thongtinsinhvien_textview_email = (TextView) view.findViewById(R.id.sinhvien_thongtinsinhvien_textview_email);
        sinhvien_thongtinsinhvien_listview_thongtin = (ListView) view.findViewById(R.id.sinhvien_thongtinsinhvien_listview_thongtin);
        hinhssv = (ImageView) view.findViewById(R.id.hinhssv);
        MainActivity.cs3 = MainActivity.dulieu.layDuLieu("select anh from SinhVien where mSv= '"+masv+"'  ");
        while (MainActivity.cs3.moveToNext()) {
            byte[] hinhanh = MainActivity.cs3.getBlob(0);
            if(hinhanh.equals("chuaco")){
                hinhssv.setImageResource(R.drawable.logo);
            }else{
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length); // --> trả về null
                if(bitmap == null){
                    hinhssv.setImageResource(R.drawable.logo);
                }else {
                    hinhssv.setImageBitmap(bitmap); // --> không hiển thị được hình
                }
            }
        }
        MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select mail from TaiKhoan where ma= '"+masv+"'  ");
        while (MainActivity.cs2.moveToNext()){
            sinhvien_thongtinsinhvien_textview_email.setText(MainActivity.cs2.getString(0));
        }
        ListView listViewthongtin =(ListView) view.findViewById(R.id.sinhvien_thongtinsinhvien_listview_thongtin);
        adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems
        );
        listViewthongtin.setAdapter(adapter);
        listViewthongtin.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int vitri, long id) {
                if(vitri == 0){
                    Intent myIntent = new Intent(Sinhvien_trangchinh_quanly.this.getActivity(), Sinhvien_quanly_thongtincanhansinhvien.class);
                    startActivity(myIntent);
                }else if(vitri == 1){
                    Intent myIntent = new Intent(Sinhvien_trangchinh_quanly.this.getActivity(), Sinhvien_quanly_thongbaocanhansinhvien.class);
                    startActivity(myIntent);
                }else if(vitri == 2){
                    Intent myIntent = new Intent(Sinhvien_trangchinh_quanly.this.getActivity(), Sinhvien_quanly_danhsachlop.class);
                    myIntent.putExtra("a",masv);
                    startActivity(myIntent);
                }else if(vitri == 3){
                    Intent myIntent = new Intent(Sinhvien_trangchinh_quanly.this.getActivity(), Sinhvien_quanly_ketquahoctap.class);
                    myIntent.putExtra("a",masv);
                    startActivity(myIntent);
                }else if(vitri == 4){
                    Intent myIntent = new Intent(Sinhvien_trangchinh_quanly.this.getActivity(), Quanly_dangnhap_doimatkhau.class);
                    myIntent.putExtra("a",masv);
                    startActivity(myIntent);
                }else if(vitri == 5){
                    Intent myIntent = new Intent(Sinhvien_trangchinh_quanly.this.getActivity(), MainActivity.class);
                    startActivity(myIntent);
                }

            }
        });
        return view;
    }
}