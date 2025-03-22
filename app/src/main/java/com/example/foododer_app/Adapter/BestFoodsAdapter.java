package com.example.foododer_app.Adapter;

import android.content.Context;
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
import com.example.foododer_app.Domain.Foods;
import com.example.foododer_app.R;

import java.util.ArrayList;

public class BestFoodsAdapter extends RecyclerView.Adapter<BestFoodsAdapter.viewholder> {
    ArrayList<Foods> items;
    Context context;

    public BestFoodsAdapter(ArrayList<Foods> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public BestFoodsAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Lấy context của parent
        context=parent.getContext();
        // Khởi tạo view từ layout viewholder_best_deal
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_best_deal,parent,false);
        // Trả về viewholder
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BestFoodsAdapter.viewholder holder, int position) {
        // Thiết lập văn bản cho TextView 'titleTxt' với tiêu đề của món ăn
        holder.titleTxt.setText(items.get(position).getTitle());
        // Thiết lập văn bản cho TextView 'priceTxt' với giá của món ăn
        holder.priceTxt.setText("đ"+items.get(position).getPrice());
        // Thiết lập văn bản cho TextView 'timeTxt' với thời gian thực hiện của món ăn
        holder.timeTxt.setText(items.get(position).getTimeValue()+" phút");
        // Thiết lập văn bản cho TextView 'starTxt' với số sao của món ăn
        holder.starTxt.setText(""+items.get(position).getStar());

        //// Sử dụng Glide để tải ảnh
        Glide.with(context)
                .load(items.get(position).getImagePath())//Tải ảnh URL
                .transform(new CenterCrop(),new RoundedCorners(30))//Căn giữa và làm tròn góc
                .into(holder.pic);//Gắn ảnh vào ImageView

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{// Lớp ViewHolder để lưu trữ các view trong item
        TextView titleTxt,priceTxt,starTxt,timeTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {// Hàm khởi tạo để lấy các view trong item
            super(itemView);//Gọi hàm khởi tạo của lớp cha
            titleTxt=itemView.findViewById(R.id.titleTxt);
            priceTxt=itemView.findViewById(R.id.priceTxt);
            starTxt=itemView.findViewById(R.id.starTxt);
            timeTxt=itemView.findViewById(R.id.timeTxt);
            pic=itemView.findViewById(R.id.pic);
        }
    }
}
