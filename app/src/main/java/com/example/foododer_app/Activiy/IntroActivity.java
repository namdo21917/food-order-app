package com.example.foododer_app.Activiy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foododer_app.R;
import com.example.foododer_app.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {

    ActivityIntroBinding binding;//Khởi tạo view binding, liên kết các view trong layout activity_intro.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // tạo một instance của ActivityIntroBinding bằng cách sử dụng một LayoutInflater(getLayoutInflater).
        binding=ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());//thiết lập root view của layout này làm nội dung cho Activity.

        setVariable();
        // sét màu nền cho thanh status bar
        getWindow().setStatusBarColor(Color.parseColor("#ffe4b5"));
    }

    private void setVariable() {
        //thiết lập các listener nút loginBtn
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra xem người dùng đã đăng nhập hay chưa
                if(mAuth.getCurrentUser()!=null){
                    startActivity(new Intent(IntroActivity.this, MainActivity.class));
                }
                // Nếu chưa đăng nhập, chuyển sang màn hình đăng nhập
                else{
                    startActivity(new Intent(IntroActivity.this, LoginActivity.class));
                }
            }
        });
        //thiết lập các listener cho nút signupBtn
        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chuyển sang màn hình đăng ký
                startActivity(new Intent(IntroActivity.this, SignupActivity.class));
            }
        });
    }
}