package com.example.ctuetapp.User_Sinhvien.List_item_thongbao;

public class thong_bao_ca_nhan_sinh_vien {
    public String ten_giang_vien, noi_dung_thong_bao, ten_lop_hoc_phan,ngay_gui;

    public thong_bao_ca_nhan_sinh_vien(String string_ten_giang_vien, String string_noi_dung_thong_bao, String string_ten_lop_hoc_phan,String string_ngay_gui) {
        this.ngay_gui = string_ngay_gui;
        this.ten_giang_vien = string_ten_giang_vien;
        this.noi_dung_thong_bao = string_noi_dung_thong_bao;
        this.ten_lop_hoc_phan = string_ten_lop_hoc_phan;
    }
}