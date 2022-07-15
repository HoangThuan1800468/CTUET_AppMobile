package com.example.ctuetapp.User_Sinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;

public class Sinhvien_quanly_ketquahoctap_ketquahoctaptungmon extends AppCompatActivity {
    String masv,malop,tinhtrang;
    TextView sinhvien_ketquahoctaptungmon_textview_mamonhoc,sinhvien_ketquahoctaptungmon_textview_tenmonhoc,
            sinhvien_ketquahoctaptungmon_textview_diemgiuaky,sinhvien_ketquahoctaptungmon_textview_diemthuchanh,
            sinhvien_ketquahoctaptungmon_textview_diemcuoiky,sinhvien_ketquahoctaptungmon_textview_diemhe10,
            sinhvien_ketquahoctaptungmon_textview_diemhe4,sinhvien_ketquahoctaptungmon_textview_xeploai,
            sinhvien_ketquahoctaptungmon_textview_ketqua,sinhvien_ketquahoctaptungmon_textview_ghichu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien_quanly_ketquahoctap_ketquahoctaptungmon);
        Sinhvien_ketquahoctaptungmon_anhxa();
        masv = getIntent().getStringExtra("b");
        malop = getIntent().getStringExtra("a");
        sinhvien_ketquahoctaptungmon_textview_mamonhoc.setText("Mã môn học: "+malop);
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select ten_MH,tinhtrangHP,ghi_chu from LopHP where ma_MH = '"+malop+"' ");
        while (MainActivity.cs1.moveToNext()){
            sinhvien_ketquahoctaptungmon_textview_tenmonhoc.setText("Tên môn học: "+MainActivity.cs1.getString(0));
            tinhtrang = MainActivity.cs1.getString(1);
            sinhvien_ketquahoctaptungmon_textview_ghichu.setText(MainActivity.cs1.getString(2));
        }
        String th="",gk="",ck="";
        if(kt_thuchanh(malop)){
            MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select diemthuchanh,diemgiuaky,diemcuoiky from LopHP_sinhvien where ma_MH = '"+malop+"' and mSv = '"+masv+"'  ");
            while (MainActivity.cs2.moveToNext()) {
                sinhvien_ketquahoctaptungmon_textview_diemthuchanh.setText("Điểm thực hành: " + MainActivity.cs2.getString(0));
                sinhvien_ketquahoctaptungmon_textview_diemgiuaky.setText("Điểm giữa kỳ: " + MainActivity.cs2.getString(1));
                sinhvien_ketquahoctaptungmon_textview_diemcuoiky.setText("Điểm cuối kỳ: " + MainActivity.cs2.getString(2));
                th = MainActivity.cs2.getString(0);
                gk = MainActivity.cs2.getString(1);
                ck = MainActivity.cs2.getString(2);
            }
        }else{
            MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select diemgiuaky,diemcuoiky from LopHP_sinhvien where ma_MH = '"+malop+"' and mSv = '"+masv+"'  ");
            while (MainActivity.cs2.moveToNext()) {
                sinhvien_ketquahoctaptungmon_textview_diemgiuaky.setText("Điểm giữa kỳ: " + MainActivity.cs2.getString(0));
                sinhvien_ketquahoctaptungmon_textview_diemcuoiky.setText("Điểm cuối kỳ: " + MainActivity.cs2.getString(1));
                gk = MainActivity.cs2.getString(0);
                ck = MainActivity.cs2.getString(1);
            }
        }
        if(tinhtrang.equals("Đang học")){
            sinhvien_ketquahoctaptungmon_textview_diemhe10.setText("Điểm hệ 10: Đang chờ");
            sinhvien_ketquahoctaptungmon_textview_diemhe4.setText("Điểm hệ 4: Đang chờ");
            sinhvien_ketquahoctaptungmon_textview_xeploai.setText("Xếp loai: Đang chờ");
            sinhvien_ketquahoctaptungmon_textview_ketqua.setText("Kết quả: Đang chờ");
        }else {
            if (kt_thuchanh(malop)) {
                double a = Float.parseFloat(th);
                double b = Float.parseFloat(gk);
                double c = Float.parseFloat(ck);
                double diem1 = ((a + b) * 40 / 100) + (c * 60 / 100);
                double diem2 = 0;
                String chu = "";
                String xl = "";
                if (diem1 < 4.0 || b==0||c==0) {
                    diem2 = 0;
                    chu = "F";
                    xl = "Rớt môn";
                }
                if (diem1 >= 4.0 && diem1 < 5.0) {
                    diem2 = 1.0;
                    chu = "D";
                    xl = "Yếu";
                } else if (diem1 >= 5.0 && diem1 < 5.5) {
                    diem2 = 1.5;
                    chu = "D+";
                    xl = "Yếu";
                } else if (diem1 >= 5.5 && diem1 < 6.5) {
                    diem2 = 2.0;
                    chu = "C";
                    xl = "Trung bình";
                } else if (diem1 >= 6.5 && diem1 < 7.0) {
                    diem2 = 2.5;
                    chu = "C+";
                    xl = "Trung bình";
                } else if (diem1 >= 7.0 && diem1 < 8.0) {
                    diem2 = 3.0;
                    chu = "B";
                    xl = "Khá";
                } else if (diem1 >= 8.0 && diem1 < 8.5) {
                    diem2 = 3.5;
                    chu = "B+";
                    xl = "Khá";
                } else if (diem1 >= 8.5 && diem1 < 9.0) {
                    diem2 = 3.7;
                    chu = "A";
                    xl = "Giỏi";
                } else if (diem1 >= 9.0) {
                    diem2 = 4.0;
                    chu = "A+";
                    xl = "Giỏi";
                }
                    sinhvien_ketquahoctaptungmon_textview_diemhe10.setText("Điểm hệ 10: " + diem1);
                    sinhvien_ketquahoctaptungmon_textview_diemhe4.setText("Điểm hệ 4: " + diem2);
                    sinhvien_ketquahoctaptungmon_textview_xeploai.setText("Xếp loai: " + chu);
                    sinhvien_ketquahoctaptungmon_textview_ketqua.setText("Kết quả: " + xl);
                    if(b==0){
                        sinhvien_ketquahoctaptungmon_textview_ghichu.setText("Ghi chú: Rớt môn do vắng thi giữa kỳ hoặc cấm thi");
                    }else if(c==0){
                        sinhvien_ketquahoctaptungmon_textview_ghichu.setText("Ghi chú: Rớt môn do vắng thi cuối kỳ hoặc cấm thi");
                    }

            } else{
            double b = Float.parseFloat(gk);
            double c = Float.parseFloat(ck);
            double diem1 = ((b) * 40 / 100) + (c * 60 / 100);
            double diem2 = 0;
            String chu = "";
            String xl = "";
            if (diem1 < 4.0 || b==0||c==0) {
                diem2 = 0;
                chu = "F";
                xl = "Rớt môn";
            }
            if (diem1 >= 4.0 && diem1 < 5.0) {
                diem2 = 1.0;
                chu = "D";
                xl = "Yếu";
            } else if (diem1 >= 5.0 && diem1 < 5.5) {
                diem2 = 1.5;
                chu = "D+";
                xl = "Yếu";
            } else if (diem1 >= 5.5 && diem1 < 6.5) {
                diem2 = 2.0;
                chu = "C";
                xl = "Trung bình";
            } else if (diem1 >= 6.5 && diem1 < 7.0) {
                diem2 = 2.5;
                chu = "C+";
                xl = "Trung bình";
            } else if (diem1 >= 7.0 && diem1 < 8.0) {
                diem2 = 3.0;
                chu = "B";
                xl = "Khá";
            } else if (diem1 >= 8.0 && diem1 < 8.5) {
                diem2 = 3.5;
                chu = "B+";
                xl = "Khá";
            } else if (diem1 >= 8.5 && diem1 < 9.0) {
                diem2 = 3.7;
                chu = "A";
                xl = "Giỏi";
            } else if (diem1 >= 9.0) {
                diem2 = 4.0;
                chu = "A+";
                xl = "Giỏi";
            }
                sinhvien_ketquahoctaptungmon_textview_diemhe10.setText("Điểm hệ 10: " + diem1);
                sinhvien_ketquahoctaptungmon_textview_diemhe4.setText("Điểm hệ 4: " + diem2);
                sinhvien_ketquahoctaptungmon_textview_xeploai.setText("Xếp loai: " + chu);
                sinhvien_ketquahoctaptungmon_textview_ketqua.setText("Kết quả: " + xl);
                if(b==0){
                    sinhvien_ketquahoctaptungmon_textview_ghichu.setText("Ghi chú: Rớt môn do vắng thi giữa kỳ hoặc cấm thi");
                }else if(c==0){
                    sinhvien_ketquahoctaptungmon_textview_ghichu.setText("Ghi chú: Rớt môn do vắng thi cuối kỳ hoặc cấm thi");
                }
            }

        }
    }
    public void Sinhvien_ketquahoctaptungmon_anhxa(){
        sinhvien_ketquahoctaptungmon_textview_mamonhoc = (TextView) findViewById(R.id.sinhvien_ketquahoctaptungmon_textview_mamonhoc);
        sinhvien_ketquahoctaptungmon_textview_tenmonhoc = (TextView) findViewById(R.id.sinhvien_ketquahoctaptungmon_textview_tenmonhoc);
        sinhvien_ketquahoctaptungmon_textview_diemgiuaky = (TextView) findViewById(R.id.sinhvien_ketquahoctaptungmon_textview_diemgiuaky);
        sinhvien_ketquahoctaptungmon_textview_diemthuchanh = (TextView) findViewById(R.id.sinhvien_ketquahoctaptungmon_textview_diemthuchanh);
        sinhvien_ketquahoctaptungmon_textview_diemcuoiky = (TextView) findViewById(R.id.sinhvien_ketquahoctaptungmon_textview_diemcuoiky);
        sinhvien_ketquahoctaptungmon_textview_diemhe10 = (TextView) findViewById(R.id.sinhvien_ketquahoctaptungmon_textview_diemhe10);
        sinhvien_ketquahoctaptungmon_textview_diemhe4 = (TextView) findViewById(R.id.sinhvien_ketquahoctaptungmon_textview_diemhe4);
        sinhvien_ketquahoctaptungmon_textview_xeploai = (TextView) findViewById(R.id.sinhvien_ketquahoctaptungmon_textview_xeploai);
        sinhvien_ketquahoctaptungmon_textview_ketqua = (TextView) findViewById(R.id.sinhvien_ketquahoctaptungmon_textview_ketqua);
        sinhvien_ketquahoctaptungmon_textview_ghichu = (TextView) findViewById(R.id.sinhvien_ketquahoctaptungmon_textview_ghichu);
    }
    public static boolean kt_thuchanh(String a){
        boolean b = false;
        String c="";
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select tiet_TH  from LopHP where ma_MH = '"+a+"'  ");
        while (MainActivity.cs1.moveToNext()){
            c = MainActivity.cs1.getString(0);
        }
        if(c.equals("Không có thực hành")){
             b = false;
        }else{
            b = true;
        }
        return b;
    }
}