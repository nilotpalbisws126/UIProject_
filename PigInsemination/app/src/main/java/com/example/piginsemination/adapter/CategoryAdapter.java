package com.example.piginsemination.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.piginsemination.DetailPage;
import com.example.piginsemination.LeafActivity;
import com.example.piginsemination.R;
import com.example.piginsemination.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context context;
    List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_row_items,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
        holder.categoryName.setText(categoryList.get(position).getCategoryName());

        if(categoryList.get(position).getTotalCourses().equals("0")){
            holder.subtopic_text.setText("");
            holder.totalCategory.setText("");
        }
        else{
            holder.subtopic_text.setText("Subtopics");
            holder.totalCategory.setText(categoryList.get(position).getTotalCourses());

        }
        Glide.with(context).load(categoryList.get(position).getImage()).into(holder.categoryImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categoryList.get(position).getTotalCourses().equals("0")){
                    Intent i = new Intent(context, LeafActivity.class);
                    i.putExtra("POSITION", (String.valueOf(position)));
                    i.putExtra("ID", (String.valueOf(categoryList.get(position).getCategoryId())));
                    context.startActivity(i);
                }
                else{
                    Intent i = new Intent(context, DetailPage.class);
                    i.putExtra("POSITION", (String.valueOf(position)));
                    i.putExtra("ID", (String.valueOf(categoryList.get(position).getCategoryId())));
                    context.startActivity(i);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView categoryImage;
        TextView categoryName, totalCategory, subtopic_text;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.course_image);
            categoryName = itemView.findViewById(R.id.course_name);
            totalCategory = itemView.findViewById(R.id.total_course);
            subtopic_text=itemView.findViewById(R.id.subtopic_text);
        }

    }
}
