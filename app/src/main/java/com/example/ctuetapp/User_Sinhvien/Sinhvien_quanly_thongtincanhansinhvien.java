package com.example.ctuetapp.User_Sinhvien;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.Calendar;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.R;
import com.example.ctuetapp.User_Quanly.Quanly_trangchinh_quanlytaikhoan;
import com.example.ctuetapp.User_Quanly.Quanly_trangchinh_quanlytaikhoan_danhsachcaplaimatkhau;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Sinhvien_quanly_thongtincanhansinhvien extends AppCompatActivity {
    FloatingActionButton sinhvien_thongtincanhansinhvien_button_luu;
    EditText sinhvien_thongtincanhansinhvien_texbox_tensv,sinhvien_thongtincanhansinhvien_texbox_masv
            ,sinhvien_thongtincanhansinhvien_texbox_sodienthoai,sinhvien_thongtincanhansinhvien_texbox_ngaysinh
            ,sinhvien_thongtincanhansinhvien_texbox_gioitinh,sinhvien_thongtincanhansinhvien_texbox_lop
            ,sinhvien_thongtincanhansinhvien_texbox_khoa,sinhvien_thongtincanhansinhvien_texbox_diachi;
    TextView sinhvien_thongtincanhansinhvien_textview_mail;
    ImageView hinhsv;
    Button chuphinh,chonhinh;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien_quanly_thongtincanhansinhvien);
        sinhvien_thongtincanhansinhvien_anhxa();

        sinhvien_thongtincanhansinhvien_texbox_masv.setText(Sinhvien_trangchinh.masv);
        loaddata(sinhvien_thongtincanhansinhvien_texbox_masv.getText().toString());
        sinhvien_thongtincanhansinhvien_texbox_ngaysinh.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel(sinhvien_thongtincanhansinhvien_texbox_ngaysinh);
                }
            };
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Sinhvien_quanly_thongtincanhansinhvien.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //---------------------------------------------------------//
        chuphinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });
        chonhinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });
        //---------------------------------------------------------//
        sinhvien_thongtincanhansinhvien_button_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sinhvien_thongtincanhansinhvien_texbox_sodienthoai.getText().toString().trim().equals("")){
                    Toast.makeText(Sinhvien_quanly_thongtincanhansinhvien.this, "Chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
                }else if(sinhvien_thongtincanhansinhvien_texbox_sodienthoai.getText().toString().trim().length()!=10){
                    Toast.makeText(Sinhvien_quanly_thongtincanhansinhvien.this, "Số điện thoại phải có 10 số", Toast.LENGTH_SHORT).show();
                }else if(sinhvien_thongtincanhansinhvien_texbox_ngaysinh.getText().toString().trim().equals("")){
                    Toast.makeText(Sinhvien_quanly_thongtincanhansinhvien.this, "Chưa chọn ngày sinh", Toast.LENGTH_SHORT).show();
                }else if(dknamsinh(sinhvien_thongtincanhansinhvien_texbox_ngaysinh.getText().toString())==false){

                }else if(sinhvien_thongtincanhansinhvien_texbox_diachi.getText().toString().trim().equals("")){
                    Toast.makeText(Sinhvien_quanly_thongtincanhansinhvien.this, "Chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                }else{
                    // chuyển image --> byte bằng bitmap
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) hinhsv.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,10,byteArray);
                    byte[] hinh = byteArray.toByteArray();//--> [B@de92768
                    //
                    MainActivity.dulieu.themDuLieu("update SinhVien set sdt = '"+sinhvien_thongtincanhansinhvien_texbox_sodienthoai.getText().toString().trim()
                            +"' , ngay_sinh = '"+sinhvien_thongtincanhansinhvien_texbox_ngaysinh.getText().toString().trim()+"' ," +
                            " dia_chi = '"+sinhvien_thongtincanhansinhvien_texbox_diachi.getText().toString().trim()+"'  where mSv = '"+sinhvien_thongtincanhansinhvien_texbox_masv.getText().toString()+"' ");
                    try {
                        MainActivity.dulieu.update_ANH(sinhvien_thongtincanhansinhvien_texbox_masv.getText().toString(), hinh);
                    }catch (Exception e){
                    }
                    Toast.makeText(Sinhvien_quanly_thongtincanhansinhvien.this, "Đã lưu thay đổi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void sinhvien_thongtincanhansinhvien_anhxa(){
        sinhvien_thongtincanhansinhvien_button_luu = (FloatingActionButton) findViewById(R.id.sinhvien_thongtincanhansinhvien_button_luu);
        sinhvien_thongtincanhansinhvien_texbox_tensv = (EditText) findViewById(R.id.sinhvien_thongtincanhansinhvien_texbox_tensv);
        sinhvien_thongtincanhansinhvien_texbox_masv = (EditText) findViewById(R.id.sinhvien_thongtincanhansinhvien_texbox_masv);
        sinhvien_thongtincanhansinhvien_texbox_sodienthoai = (EditText) findViewById(R.id.sinhvien_thongtincanhansinhvien_texbox_sodienthoai);
        sinhvien_thongtincanhansinhvien_texbox_ngaysinh = (EditText) findViewById(R.id.sinhvien_thongtincanhansinhvien_texbox_ngaysinh);
        sinhvien_thongtincanhansinhvien_texbox_gioitinh = (EditText) findViewById(R.id.sinhvien_thongtincanhansinhvien_texbox_gioitinh);
        sinhvien_thongtincanhansinhvien_texbox_lop = (EditText) findViewById(R.id.sinhvien_thongtincanhansinhvien_texbox_lop);
        sinhvien_thongtincanhansinhvien_texbox_khoa = (EditText) findViewById(R.id.sinhvien_thongtincanhansinhvien_texbox_khoa);
        sinhvien_thongtincanhansinhvien_texbox_diachi = (EditText) findViewById(R.id.sinhvien_thongtincanhansinhvien_texbox_diachi);
        sinhvien_thongtincanhansinhvien_textview_mail = (TextView) findViewById(R.id.sinhvien_thongtincanhansinhvien_textview_mail);
        hinhsv = (ImageView) findViewById(R.id.hinhsv);
        chonhinh = (Button) findViewById(R.id.chonhinh);
        chuphinh = (Button) findViewById(R.id.chuphinh);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            hinhsv.setImageBitmap(bitmap);
        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                hinhsv.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateLabel(EditText tg) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tg.setText(sdf.format(myCalendar.getTime()));
    }
    private boolean dknamsinh(String b){
        boolean c= false;
        String date = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
        int year = Integer.parseInt(date);
        String[]d=b.split("/");
        if(year - Integer.parseInt(d[2])<10){
            c = false;
            Toast.makeText(Sinhvien_quanly_thongtincanhansinhvien.this, "Năm sinh phải cách hiện tại 10 năm"+Integer.parseInt(d[2]), Toast.LENGTH_SHORT).show();
        }else {
            c = true;
        }
        return c;
    }
    private void loaddata(String ma){
        MainActivity.cs1 = MainActivity.dulieu.layDuLieu("select tenSv,sdt,ngay_sinh,gioi_tinh,lop,khoa_hoc,dia_chi,anh from SinhVien where mSv = '"+ma+"'  ");
        while (MainActivity.cs1.moveToNext()){
            sinhvien_thongtincanhansinhvien_texbox_tensv.setText(MainActivity.cs1.getString(0));
            sinhvien_thongtincanhansinhvien_texbox_sodienthoai.setText(MainActivity.cs1.getString(1));
            sinhvien_thongtincanhansinhvien_texbox_ngaysinh.setText(MainActivity.cs1.getString(2));
            sinhvien_thongtincanhansinhvien_texbox_gioitinh.setText(MainActivity.cs1.getString(3));
            sinhvien_thongtincanhansinhvien_texbox_lop.setText(MainActivity.cs1.getString(4));
            sinhvien_thongtincanhansinhvien_texbox_khoa.setText(MainActivity.cs1.getString(5));
            sinhvien_thongtincanhansinhvien_texbox_diachi.setText(MainActivity.cs1.getString(6));
            // chuyển byte --> image hiển thị lên giao diện
            byte[] hinhanh = MainActivity.cs1.getBlob(7); // --> trả về chuỗi "[B@de92768" đúng với khi lưu vào sqlite
            if(hinhanh.equals("chuaco")){
                hinhsv.setImageResource(R.drawable.logo);
            }else{
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length); // --> trả về null
                if(bitmap == null){
                    hinhsv.setImageResource(R.drawable.logo);
                }else {
                    hinhsv.setImageBitmap(bitmap); // --> không hiển thị được hình
                }
            }
            //
        }
        MainActivity.cs2 = MainActivity.dulieu.layDuLieu("select mail from TaiKhoan where ma= '"+ma+"'  ");
        while (MainActivity.cs2.moveToNext()){
            sinhvien_thongtincanhansinhvien_textview_mail.setText(MainActivity.cs2.getString(0));
        }
    }
    public interface OnBackPressedListener {
        void onBackPressed();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent myIntent = new Intent(Sinhvien_quanly_thongtincanhansinhvien.this, Sinhvien_trangchinh.class);
        myIntent.putExtra("a",sinhvien_thongtincanhansinhvien_texbox_masv.getText().toString().trim());
        startActivity(myIntent);
    }
}