package com.example.foododer_app.Activiy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foododer_app.Adapter.BestFoodsAdapter;
import com.example.foododer_app.Adapter.CategoryAdapter;
import com.example.foododer_app.Domain.Category;
import com.example.foododer_app.Domain.Foods;
import com.example.foododer_app.Domain.Location;
import com.example.foododer_app.Domain.Price;
import com.example.foododer_app.Domain.Time;
import com.example.foododer_app.R;
import com.example.foododer_app.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
private ActivityMainBinding binding;//liên kết các view trong layout activity_main.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLocation();
        initTime();
        initPrice();
        initBestFood();
        initCategory();
        setVariable();

    }

    private void setVariable() {
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Đăng xuất
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=binding.searchEdt.getText().toString();
                if(!text.isEmpty()){

                    Intent intent=new Intent(MainActivity.this,ListFoodsActivity.class);
                    intent.putExtra("text",text);//Thêm dữ liệu "text" với giá trị "text"
                    intent.putExtra("isSearch",true);//Thêm dữ liệu "isSearch" với giá trị true
                    startActivity(intent);//Chuyển sang ListFoodsActivity
                }
            }
        });
    }

    private void initBestFood() {
        //Lấy dữ liệu từ Firebase bảng Foods
        DatabaseReference myRef=database.getReference("Foods");
        //hiển thị thanh tiến trình (ProgressBar)
        binding.progressBarBestFood.setVisibility(View.VISIBLE);
        //tạo list chứa dữ liệu danh sách món ăn
        ArrayList<Foods> list=new ArrayList<>();
        //Truy vấn dữ liệu từ Firebase bảng Foods với thuộc tinhs BestFood = true
        Query query=myRef.orderByChild("BestFood").equalTo(true);
        //Lắng nghe sự kiện thay đổi dữ liệu
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            //Hàm này sẽ chạy khi dữ liệu thay đổi
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Kiểm tra dữ liệu có tồn tại
                if(snapshot.exists()){
                    //Duyệt qua các nút con của DataSnapshot
                    for(DataSnapshot issue: snapshot.getChildren()) {
                        list.add(issue.getValue(Foods.class));//Thêm món ăn vào list
                    }
                    //Kiểm tra list có rỗng không, nếu không rỗng thì hiện thị dữ liệu trên recyclerview
                    if(list.size()>0){
                        //Thiết lập layout cho recyclerview hiển thị dữ liệu theo dang nằm ngang
                        binding.bestFoodView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        RecyclerView.Adapter adapter=new BestFoodsAdapter(list);//Tạo Adapter 'BestFoodsAdapter' với danh sách 'list'
                        binding.bestFoodView.setAdapter(adapter);//Gán Adapter cho RecyclerView cuar bestFoodView

                    }
                    //Ẩn thanh progressBar
                    binding.progressBarBestFood.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initCategory() {
        // Lấy tham chiếu đến nút "Category" trong Firebase Database
        DatabaseReference myRef=database.getReference("Category");
        // Hiển thị thanh tiến trình (ProgressBar)
        binding.progressBarBestFood.setVisibility(View.VISIBLE);
        // Tạo list chứa dữ liệu các đối tượng Category
        ArrayList<Category> list=new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {//Xử lý sự kiện thay đổi dữ liệu
                if(snapshot.exists()){
                    // Duyệt qua các nút con của DataSnapshot
                    for(DataSnapshot issue: snapshot.getChildren()) {
                        list.add(issue.getValue(Category.class));//Thêm đối tượng Category vào list
                    }
                    // Kiểm tra list có rỗng không, nếu không rỗng thì hiện thị dữ liệu trên recyclerview
                    if(list.size()>0){
                        //hiển thị dữ liệu theo dạng lưới với 4 cột
                        binding.categoryView.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
                        RecyclerView.Adapter adapter=new CategoryAdapter(list); // Khởi tạo Adapter 'CategoryAdapter' với danh sách 'list'
                        binding.categoryView.setAdapter(adapter);// Gán Adapter cho RecyclerView của categoryView

                    }
                    //Ẩn thanh progressBar
                    binding.progressBarCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initLocation() {
        //Lấy dữ liệu từ Firebase bảng Location
        DatabaseReference myRef=database.getReference("Location");
        //tạo list chứa dữ liệu danh sách Location
        ArrayList<Location> list=new ArrayList<>();
        //Lắng nghe sự kiện thay đổi dữ liệu
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    // Duyệt qua các nút con của DataSnapshot
                    for(DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(Location.class));//Thêm đối tượng Location vào list
                    }
                    // Khởi tạo ArrayAdapter để hiển thị dữ liệu trong Spinner
                    ArrayAdapter<Location> adapter=new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    // Thiết lập layout cho dropdown của Spinner
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.locationSp.setAdapter(adapter);//Gán Adapter cho Spinner của locationSp
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initTime() {
        //Lấy dữ liệu từ Firebase bảng Time
        DatabaseReference myRef=database.getReference("Time");
        //tạo list chứa dữ liệu danh sách Time
        ArrayList<Time> list=new ArrayList<>();
        //Lắng nghe sự kiện thay đổi dữ liệu
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    // Duyệt qua các nút con của DataSnapshot
                    for(DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(Time.class));//Thêm đối tượng Time vào list
                    }
                    // Khởi tạo ArrayAdapter để hiển thị dữ liệu trong Spinner
                    ArrayAdapter<Time> adapter=new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    // Thiết lập layout cho dropdown của Spinner
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.timeSp.setAdapter(adapter);//Gán Adapter cho Spinner của timeSp
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initPrice() {
        //Lấy dữ liệu từ Firebase bảng Price
        DatabaseReference myRef=database.getReference("Price");
        //tạo list chứa dữ liệu danh sách Price
        ArrayList<Price> list=new ArrayList<>();
        //Lắng nghe sự kiện thay đổi dữ liệu
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    // Duyệt qua các nút con của DataSnapshot
                    for(DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(Price.class));//Thêm đối tượng Price vào list
                    }
                    // Khởi tạo ArrayAdapter để hiển thị dữ liệu trong Spinner
                    ArrayAdapter<Price> adapter=new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    // Thiết lập layout cho dropdown của Spinner
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.priceSp.setAdapter(adapter);//Gán Adapter cho Spinner của priceSp
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}