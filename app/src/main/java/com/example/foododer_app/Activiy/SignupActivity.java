package com.example.foododer_app.Activiy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foododer_app.R;
import com.example.foododer_app.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignupActivity extends BaseActivity {
    ActivitySignupBinding binding;//liên kết các view trong layout activity_signup.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();//thiết lập các biến vào activity

    }

    private void setVariable() {
        //thêm sự kiện cho button signup
        binding.signupBtn.setOnClickListener(v -> {
            //lấy thông tin vào các biến
            String email=binding.userEdt.getText().toString();
            String password=binding.passEdt.getText().toString();
            String repeatpassword=binding.repeatpassEdt.getText().toString();
            if(email.equals("")){
                Toast.makeText(SignupActivity.this,"Vui lòng nhập email !",Toast.LENGTH_SHORT).show();
                return;
            }
            if(password.equals("")){
                Toast.makeText(SignupActivity.this,"Vui lòng nhập mật khẩu !",Toast.LENGTH_SHORT).show();
                return;
            }
            if(repeatpassword.equals("")){
                Toast.makeText(SignupActivity.this,"Vui lòng nhập lại mật khẩu !",Toast.LENGTH_SHORT).show();
                return;
            }
            if(password.length()<6){
                Toast.makeText(SignupActivity.this,"Mật khẩu phải lớn hơn 6 !",Toast.LENGTH_SHORT).show();
                return;
            }
            if(!password.equals(repeatpassword)){
                Toast.makeText(SignupActivity.this,"Mật khẩu không đúng!",Toast.LENGTH_SHORT).show();
                return;
            }
            //Tạo tài khoản bằng email và password
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, task -> {
                if(task.isComplete()){
                    //Tạo tài khoản thành công
                    Log.i(TAG,"Hoàn thành: ");//in ra thông tin
                    //đăng nhập
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                }else {
                    Log.i(TAG,"Thất bại: "+task.getException());
                    Toast.makeText(SignupActivity.this, "Xác thực thất bại!", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}