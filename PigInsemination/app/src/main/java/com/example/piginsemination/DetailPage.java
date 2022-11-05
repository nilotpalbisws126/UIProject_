package com.example.piginsemination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.piginsemination.adapter.ArticleAdapter;
import com.example.piginsemination.model.LevelTwo;
import com.example.piginsemination.model.SubTopic;
import com.example.piginsemination.retrofit.ApiInterface;
import com.example.piginsemination.retrofit.RetrofitClient;

import java.util.List;

import me.biubiubiu.justifytext.library.JustifyTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  DetailPage extends AppCompatActivity {
    RecyclerView articleRecyclerView;
    ApiInterface apiInterface;

    ArticleAdapter articleAdapter;
    String clickedPosition;

    ImageView optionButton;

    TextView title_detail;
    TextView content_detail;
    ImageView image_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        ImageView backbutton = findViewById(R.id.back_detail_page);
        backbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title_detail=findViewById(R.id.title_detail);
        content_detail=findViewById(R.id.content_detail);
        image_detail=findViewById(R.id.image_detail);

        //zoom
//        final ZoomLinearLayout zoomLinearLayout = (ZoomLinearLayout) findViewById(R.id.zoom_linear_layout_detail);
//        zoomLinearLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                zoomLinearLayout.init(DetailPage.this);
//                return false;
//            }
//        });

        optionButton=(ImageView)findViewById(R.id.menu_detail_page);
        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu= new PopupMenu(DetailPage.this,optionButton);
                popupMenu.getMenuInflater().inflate(R.menu.menu_no_icon,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                       // Toast.makeText(DetailPage.this,item.getTitle(),Toast.LENGTH_LONG).show();
                        if(item.getTitle().equals("Home")){
                            Intent i = new Intent(DetailPage.this, MainActivity.class);
                            startActivity(i);
                        }
                        else if(item.getTitle().equals("About App")){
                            Intent i = new Intent(DetailPage.this, AboutActivity.class);
                            startActivity(i);
                        }
                        else if(item.getTitle().equals("Contact and Developers")){
                            Intent i = new Intent(DetailPage.this, Contact.class);
                            startActivity(i);
                        }
                        else if(item.getTitle().equals("Acknowledgement")){
                            Intent i = new Intent(DetailPage.this, Acknowledgement.class);
                            startActivity(i);
                        }

                        else{

                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });


        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            clickedPosition= null;
        } else {
            clickedPosition= extras.getString("ID");
        }
       // Toast.makeText(DetailPage.this,clickedPosition,Toast.LENGTH_LONG).show();

        apiInterface= RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        articleRecyclerView = findViewById(R.id.article_recycler);
        Call<List<LevelTwo>> call = apiInterface.getAllArticles();
        call.enqueue(new Callback<List<LevelTwo>>() {
            @Override
            public void onResponse(Call<List<LevelTwo>> call, Response<List<LevelTwo>> response) {
                List<LevelTwo> articlelist = response.body();

                int result=-1;
                for(int i=0;i<articlelist.size();i++){
                    if(articlelist.get(i).getCategoryId().equals(clickedPosition)){
                        result=i;
                        break;
                    }
                }
                if(result!=-1){
                    getArticleContent(articlelist.get(result).getSubTopics());
                    title_detail.setText(articlelist.get(result).getCategoryName());

                    content_detail.setText(Html.fromHtml(articlelist.get(result).getContent()));
                    //

                    if(articlelist.get(result).getImageUrl()!=null){
                        image_detail.setVisibility(View.VISIBLE);
                      //  Toast.makeText(DetailPage.this,articlelist.get(result).getImageUrl(),Toast.LENGTH_SHORT).show();
                        Glide.with(DetailPage.this)
                                .load(articlelist.get(result).getImageUrl())
                                .into(image_detail);
                        //leaf_image.setImageURI(Uri.parse(leaf_list.get(Integer.parseInt(clickedPosition)).getImage()));
                    }

                }


              //  List<SubTopic>test=articlelist.get(0).getSubTopics();
            }


            @Override
            public void onFailure(Call<List<LevelTwo>> call, Throwable t) {
                Toast.makeText(DetailPage.this,"Please check your internet connection",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(DetailPage.this,oops.class);
                startActivity(i);

            }
        });
    }


    private void getArticleContent(List<SubTopic> subTopics){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        articleRecyclerView.setLayoutManager(layoutManager);
        articleAdapter=new ArticleAdapter(this,subTopics);
        articleRecyclerView.setAdapter(articleAdapter);
        articleAdapter.notifyDataSetChanged();
    }
}

