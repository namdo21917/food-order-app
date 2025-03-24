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

public class FoodsListAdapter extends RecyclerView.Adapter<FoodsListAdapter.viewHolder> {
    ArrayList<Foods> items;
    Context context;
    public FoodsListAdapter(ArrayList<Foods> items){
        this.items = items;

    }
    @NonNull
    @Override
    public FoodsListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {// Phương thức tạo ViewHolder
        context = parent.getContext();
        View inflate= LayoutInflater.from(context).inflate(R.layout.viewholder_list_food,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodsListAdapter.viewHolder holder, int position) { // Phương thức bind dữ liệu cho ViewHolder
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.priceTxt.setText("đ"+items.get(position).getPrice());
        holder.timeTxt.setText(items.get(position).getTimeValue()+" phút");
        holder.rateTxt.setText(""+items.get(position).getStar());

        // Sử dụng Glide để tải ảnh
        Glide.with(context)
                .load(items.get(position).getImagePath())// Tải ảnh từ URL
                .transform(new CenterCrop(),new RoundedCorners(30))// Căn chỉnh ảnh ở giữa và làm tròn góc
                .into(holder.pic);// Đặt ảnh vào ImageView


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt,priceTxt,rateTxt,timeTxt;
        ImageView pic;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.titleTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            rateTxt = itemView.findViewById(R.id.rateTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            pic = itemView.findViewById(R.id.img);
        }
    }
}
