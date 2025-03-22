package com.example.foododer_app.Activiy;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foododer_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class BaseActivity extends AppCompatActivity {

 // Biến để lưu trữ đối tượng FirebaseAuth, dùng để quản lý xác thực người dùng
FirebaseAuth mAuth;
// Biến để lưu trữ đối tượng FirebaseDatabase, dùng để tương tác với Realtime Database
FirebaseDatabase database;
public String TAG="long";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Khoi tạo đối tượng
        database=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        // Thiết lập màu nền cho thanh trạng thái (status bar) là màu trắng, lấy từ tài nguyên R.color.white
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
    }
}