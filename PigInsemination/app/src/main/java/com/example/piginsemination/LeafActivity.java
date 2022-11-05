package com.example.piginsemination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.piginsemination.model.Leaf;
import com.example.piginsemination.model.LevelTwo;
import com.example.piginsemination.retrofit.ApiInterface;
import com.example.piginsemination.retrofit.RetrofitClient;

import java.util.List;

import me.biubiubiu.justifytext.library.JustifyTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeafActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    String clickedPosition;

    TextView leaf_title;
    TextView leaf_content;
    ImageView leaf_image;
    ImageView optionButton;

    ImageView video1;
    ImageView video2;
    ImageView video3;
    ScaleGestureDetector scaleGestureDetector=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaf);
        leaf_title=findViewById(R.id.leaf_title);
        leaf_content=findViewById(R.id.leaf_content);
        leaf_image=findViewById(R.id.leaf_image);


        //zoom

        final ZoomLinearLayout zoomLinearLayout = (ZoomLinearLayout) findViewById(R.id.zoom_linear_layout);
        zoomLinearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                zoomLinearLayout.init(LeafActivity.this);
                return false;
            }
        });



        ImageView backbutton = findViewById(R.id.leaf_back_button);
        backbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });


        optionButton=(ImageView)findViewById(R.id.menu_leaf_page);
        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu= new PopupMenu(LeafActivity.this,optionButton);
                popupMenu.getMenuInflater().inflate(R.menu.menu_no_icon,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Toast.makeText(DetailPage.this,item.getTitle(),Toast.LENGTH_LONG).show();
                        if(item.getTitle().equals("Home")){
                            Intent i = new Intent(LeafActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                        else if(item.getTitle().equals("About App")){
                            Intent i = new Intent(LeafActivity.this, AboutActivity.class);
                            startActivity(i);
                        }
                        else if(item.getTitle().equals("Contact and Developers")){
                            Intent i = new Intent(LeafActivity.this, Contact.class);
                            startActivity(i);
                        }
                        else if(item.getTitle().equals("Acknowledgement")){
                            Intent i = new Intent(LeafActivity.this, Acknowledgement.class);
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
       // Toast.makeText(LeafActivity.this,clickedPosition,Toast.LENGTH_LONG).show();



        apiInterface= RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        Call<List<Leaf>> call = apiInterface.getAllLeaf();
        call.enqueue(new Callback<List<Leaf>>() {
            @Override
            public void onResponse(Call<List<Leaf>> call, Response<List<Leaf>> response) {
                List<Leaf> leaf_list = response.body();

                int result=-1;
                for(int i=0;i<leaf_list.size();i++){
                    if(leaf_list.get(i).getCategoryId().equals(clickedPosition)){
                        result=i;
                        break;
                    }
                }

                if(result!=-1)
                {

                    leaf_title.setText(leaf_list.get(result).getCategoryName());
                   // leaf_content.setText(leaf_list.get(result).getContent());
                   // leaf_content.setText(Html.fromHtml("<b>hi</b>"));
                    leaf_content.setText(Html.fromHtml(leaf_list.get(result).getContent()));


                    if(leaf_list.get(result).getImage()!=null){
                        leaf_image.setVisibility(View.VISIBLE);
                        Glide.with(LeafActivity.this)
                                .load(leaf_list.get(result).getImage())
                                .into(leaf_image);
                        //leaf_image.setImageURI(Uri.parse(leaf_list.get(Integer.parseInt(clickedPosition)).getImage()));



                    }


                    //videos
                    video1=findViewById(R.id.video1);
                    video2=findViewById(R.id.video2);
                    video3=findViewById(R.id.video3);
                    if(leaf_list.get(result).getCategoryId().equals("34")){

                        video1.setVisibility(View.VISIBLE);
                        video2.setVisibility(View.VISIBLE);
                        video3.setVisibility(View.VISIBLE);

                        video1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent1 = new Intent();
                                intent1.setAction((Intent.ACTION_VIEW));
                                intent1.addCategory(Intent.CATEGORY_BROWSABLE);
                                intent1.setData(Uri.parse("https://www.youtube.com/watch?v=J6zsBS5lbwA"));
                                startActivity(intent1);
                            }
                        });
                        video2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent2 = new Intent();
                                intent2.setAction((Intent.ACTION_VIEW));
                                intent2.addCategory(Intent.CATEGORY_BROWSABLE);
                                intent2.setData(Uri.parse("https://www.youtube.com/watch?v=gZH-IoN6RY4"));
                                startActivity(intent2);
                            }
                        });

                        video3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent3= new Intent();
                                intent3.setAction((Intent.ACTION_VIEW));
                                intent3.addCategory(Intent.CATEGORY_BROWSABLE);
                                intent3.setData(Uri.parse("https://www.youtube.com/watch?v=fABl4XxKOcE"));
                                startActivity(intent3);
                            }
                        });
                    }
                    else{
                        video1.setVisibility(View.GONE);
                        video2.setVisibility(View.GONE);
                        video3.setVisibility(View.GONE);



                    }




                }




            }

            @Override
            public void onFailure(Call<List<Leaf>> call, Throwable t) {
                Toast.makeText(LeafActivity.this,"Please check your internet connection",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LeafActivity.this,oops.class);
                startActivity(i);

            }
        });

    }


}