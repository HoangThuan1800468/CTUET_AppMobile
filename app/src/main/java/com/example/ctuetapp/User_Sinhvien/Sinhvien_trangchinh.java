package com.example.ctuetapp.User_Sinhvien;

import android.content.Intent;
import android.os.Bundle;

import com.example.ctuetapp.MainActivity;
import com.example.ctuetapp.User_Giangvien.Giangvien_bangdiemsinhvien;
import com.example.ctuetapp.User_Quanly.Quanly_trangchinh_quanlylophocphan_thongtinlophocphan_danhsachsinhvienlophocphan;
import com.example.ctuetapp.databinding.ActivitySinhvienTrangchinhBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ctuetapp.User_Sinhvien.ui.main.SectionsPagerAdapter;

public class Sinhvien_trangchinh extends AppCompatActivity {

    private ActivitySinhvienTrangchinhBinding binding;
    public static String masv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySinhvienTrangchinhBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        masv = getIntent().getStringExtra("a");
    }

}