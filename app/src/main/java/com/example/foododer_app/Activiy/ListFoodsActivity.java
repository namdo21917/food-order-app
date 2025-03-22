package com.example.foododer_app.Activiy;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foododer_app.Adapter.FoodsListAdapter;
import com.example.foododer_app.Domain.Foods;
import com.example.foododer_app.R;
import com.example.foododer_app.databinding.ActivityListFoodsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListFoodsActivity extends BaseActivity {
    ActivityListFoodsBinding binding;// Liên kết các view trong layout activity_list_foods.xml
    private RecyclerView.Adapter adapterListFood;// Biến để lưu trữ Adapter cho RecyclerView
    private int categoryId;//Biến để lưu trữ ID của loại món ăn
    private String categoryName;// Biến để lưu trữ tên loại món ăn
    private String searchText;// Biến để lưu trữ từ khóa tìm kiếm
    private boolean isSearch;// Biến để xác định xem có đang thực hiện tìm kiếm hay không

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityListFoodsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        initList();
        setVariable();
    }

    private void setVariable() {

    }
    // Khởi tạo danh sách món ăn
    private void initList() {
        // Lấy tham chiếu đến nút "Foods" trong Firebase Database
        DatabaseReference myRef = database.getReference("Foods");
        // Hiển thị thanh progress bar
        binding.progressBar.setVisibility(View.VISIBLE);
        // Khởi tạo danh sách món ăn
        ArrayList<Foods> list=new ArrayList<>();

        //Biến lưu trữ truy vấn
        Query query;
        // Kiểm tra xem đang thực hiện tìm kiếm hay không
        if(isSearch){
            /// Tạo truy vấn tìm kiếm theo tiêu đề (Title)
            query=myRef.orderByChild("Title").startAt(searchText).endAt(searchText+"\uf8ff");
        }else{
            // Tạo truy vấn tìm kiếm theo loại món ăn
            query=myRef.orderByChild("CategoryId").equalTo(categoryId);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {//xử lý dữ liệu thay đổi
                // Kiểm tra xem dữ liệu có tồn tại hay không
                if(snapshot.exists()){
                    // Lấy danh sách món ăn từ dataSnapshot
                    for(DataSnapshot issue :snapshot.getChildren()){
                        list.add(issue.getValue(Foods.class));//Thêm món ăn vào danh sách dưới dạng đối tượng Foods
                    }
                    // Kiểm tra xem danh sách món ăn có rỗng hay không
                    if(list.size()>0){
                        // Hiển thị danh sách món ăn theo dạng lưới 2 cột
                        binding.foodlistView.setLayoutManager(new GridLayoutManager(ListFoodsActivity.this,2));
                        // Khởi tạo Adapter 'FoodsListAdapter' với danh sách 'list'
                        adapterListFood=new FoodsListAdapter(list);
                        // Liên kết Adapter 'adapterListFood' với RecyclerView 'binding.foodlistView'
                        binding.foodlistView.setAdapter(adapterListFood);

                    }
                    // Ẩn thanh progress bar
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentExtra() {
        // Lấy dữ liệu từ intent
        categoryId=getIntent().getIntExtra("CategoryId",0);
        categoryName=getIntent().getStringExtra("CategoryName");
        searchText=getIntent().getStringExtra("text");
        isSearch=getIntent().getBooleanExtra("isSearch",false);

        // Thiết lập văn bản cho TextView
        binding.titleTxt.setText(categoryName);
        // Thiết lập sự kiện cho nút "Back"
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}