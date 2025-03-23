package com.example.foododer_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foododer_app.Activiy.ListFoodsActivity;
import com.example.foododer_app.Domain.Category;
import com.example.foododer_app.Domain.Foods;
import com.example.foododer_app.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {
    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public CategoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {// Phương thức tạo ViewHolder
        context=parent.getContext();
        //// Khởi tạo view từ layout viewholder_category
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewholder holder, int position) {// Phương thức bind dữ liệu cho ViewHolder
        // Thiết lập văn bản cho TextView 'titleTxt' với tên của danh mục
        holder.titleTxt.setText(items.get(position).getName());
        // Thiết lập background cho ImageView 'pic' dựa trên vị trí của danh mục
        switch (position){
            case 0: {
                holder.pic.setBackgroundResource(R.drawable.cat_0_background);
                break;
            }
            case 1: {
                holder.pic.setBackgroundResource(R.drawable.cat_0_background);
                break;
            }
            case 2: {
                holder.pic.setBackgroundResource(R.drawable.cat_0_background);
                break;
            }
            case 3: {
                holder.pic.setBackgroundResource(R.drawable.cat_0_background);
                break;
            }
            case 4: {
                holder.pic.setBackgroundResource(R.drawable.cat_0_background);
                break;
            }
            case 5: {
                holder.pic.setBackgroundResource(R.drawable.cat_0_background);
                break;
            }
            case 6: {
                holder.pic.setBackgroundResource(R.drawable.cat_0_background);
                break;
            }
            case 7: {
                holder.pic.setBackgroundResource(R.drawable.cat_0_background);
                break;
            }

        }
        // Tải ảnh cho ImageView 'pic' từ drawable
        int drawableResourceId=context.getResources().getIdentifier(
                items.get(position).getImagePath(),"drawable"
                ,holder.itemView.getContext().getPackageName());
        // Sử dụng thư viện Glide để tải ảnh
        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);

        // Thiết lập sự kiện click cho mỗi item trong RecyclerView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển sang Activity ListFoodsActivity
                Intent intent=new Intent(context, ListFoodsActivity.class);
                // Truyền ID và tên của danh mục vào Intent
                intent.putExtra("CategoryId",items.get(position).getId());
                intent.putExtra("CategoryName",items.get(position).getName());
                // Khởi động Activity ListFoodsActivity
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class viewholder extends RecyclerView.ViewHolder{// Lớp ViewHolder để lưu trữ các view trong item
        TextView titleTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.catNameTxt);

            pic=itemView.findViewById(R.id.imageCat);
        }
    }
}
