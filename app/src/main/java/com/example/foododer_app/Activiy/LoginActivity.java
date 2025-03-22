package com.example.foododer_app.Activiy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foododer_app.R;
import com.example.foododer_app.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends BaseActivity {
ActivityLoginBinding binding;//liên kết các view trong layout activity_login.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setVariable();// thiết lập các biến

    }

    private void setVariable() {
        // Thiết lập sự kiện cho nút đăng nhập
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin tài khoản và mật khẩu từ EditText
                String email=binding.userEdt.getText().toString();
                String password=binding.passEdt.getText().toString();
                // Kiểm tra nếu tài khoản và mật khẩu không rỗng
                if(!email.isEmpty()&&!password.isEmpty()){
                    // Đăng nhập bằng tài khoản và mật khẩu
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // Kiểm tra kết quả đăng nhập
                            if(task.isSuccessful()){
                                // Đăng nhập thành công, chuyển sang màn hình chính
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                            // Đăng nhập thất bại, hiển thị thông báo
                            else{
                                Toast.makeText(LoginActivity.this, "Xác nhận thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                // Nếu tài khoản hoặc mật khẩu rỗng, hiển thị thông báo
                else {
                    Toast.makeText(LoginActivity.this, "Vui lòng điền tài khoản và mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}