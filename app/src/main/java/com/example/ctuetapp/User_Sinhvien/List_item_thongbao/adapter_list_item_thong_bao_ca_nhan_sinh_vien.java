package com.example.ctuetapp.User_Sinhvien.List_item_thongbao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ctuetapp.R;

import java.util.ArrayList;
import java.util.List;

public class adapter_list_item_thong_bao_ca_nhan_sinh_vien extends BaseAdapter {

    Context myContext;
    int myLayout;
    List<thong_bao_ca_nhan_sinh_vien> array_listthongbao;
    public adapter_list_item_thong_bao_ca_nhan_sinh_vien(Context context, int layout, ArrayList<thong_bao_ca_nhan_sinh_vien> thongbao_list){
        myContext = context;
        myLayout = layout;
        array_listthongbao = thongbao_list;
    }

    @Override
    public int getCount() {
        return array_listthongbao.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(myLayout,null);
        TextView tengiangvien = (TextView) convertView.findViewById(R.id.tengiangvien);
        tengiangvien.setText(array_listthongbao.get(position).ten_giang_vien);

        TextView noidung_thongbao = (TextView) convertView.findViewById(R.id.noidung);
        noidung_thongbao.setText(array_listthongbao.get(position).noi_dung_thong_bao);

        TextView lophocphan = (TextView) convertView.findViewById(R.id.lophocphan);
        lophocphan.setText(array_listthongbao.get(position).ten_lop_hoc_phan);

        TextView ngaygui = (TextView) convertView.findViewById(R.id.ngaygui);
        ngaygui.setText(array_listthongbao.get(position).ngay_gui);

        return convertView;
    }
}
