package com.example.ctuetapp.User_Giangvien;

import android.os.Bundle;

import com.example.ctuetapp.databinding.ActivityGiangvienTrangchinhBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.ctuetapp.User_Giangvien.ui.main.SectionsPagerAdapter;

public class Giangvien_trangchinh extends AppCompatActivity {

private ActivityGiangvienTrangchinhBinding binding;
    public static String magv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityGiangvienTrangchinhBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        magv1 = getIntent().getStringExtra("a");
    }
}