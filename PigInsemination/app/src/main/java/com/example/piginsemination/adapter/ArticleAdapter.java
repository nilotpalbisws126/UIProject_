package com.example.piginsemination.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piginsemination.DetailPage;
import com.example.piginsemination.LeafActivity;
import com.example.piginsemination.R;
import com.example.piginsemination.model.SubTopic;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    Context context;
    List<SubTopic> subTopics;

    public ArticleAdapter(Context context, List<SubTopic> subTopics) {
        this.context = context;
        this.subTopics = subTopics;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.article_list_row_items,parent,false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, final int position) {
        holder.contentNumber.setText(String.valueOf(position+1));
        holder.contentName.setText(subTopics.get(position).getSubtopic());
        //holder.contentName.setText("oyo");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subTopics.get(position).getTotalSub().equals("0")){
                    Intent i = new Intent(context, LeafActivity.class);
                    i.putExtra("POSITION", (String.valueOf(position)));
                    i.putExtra("ID", (String.valueOf(subTopics.get(position).getCatId())));
                    context.startActivity(i);
                }
                else{
                    Intent i = new Intent(context, DetailPage.class);
                    i.putExtra("POSITION", (String.valueOf(position)));
                    i.putExtra("ID", (String.valueOf(subTopics.get(position).getCatId())));
                    context.startActivity(i);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return subTopics.size();
    }
//Load the layout
    public static class ArticleViewHolder extends RecyclerView.ViewHolder{

        TextView contentNumber, contentName;

        public ArticleViewHolder(@NonNull View itemView){

            super(itemView);
            contentName=itemView.findViewById(R.id.content_title);
            contentNumber=itemView.findViewById(R.id.content_num);

        }
    }
}


