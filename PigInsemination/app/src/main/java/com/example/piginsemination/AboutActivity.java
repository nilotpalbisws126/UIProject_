package com.example.piginsemination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import me.biubiubiu.justifytext.library.JustifyTextView;


public class AboutActivity extends AppCompatActivity {

    ImageView optionButton;
    TextView about_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageView backbutton = findViewById(R.id.about_back_button);
        backbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        optionButton=(ImageView)findViewById(R.id.menu_about_page);
        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu= new PopupMenu(AboutActivity.this,optionButton);
                popupMenu.getMenuInflater().inflate(R.menu.menu_no_icon,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Toast.makeText(DetailPage.this,item.getTitle(),Toast.LENGTH_LONG).show();
                        if(item.getTitle().equals("Home")){
                            Intent i = new Intent(AboutActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                        else if(item.getTitle().equals("About App")){
                            Intent i = new Intent(AboutActivity.this, AboutActivity.class);
                            startActivity(i);
                        }
                        else if(item.getTitle().equals("Contact and Developers")){
                            Intent i = new Intent(AboutActivity.this, Contact.class);
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

        //Text View
        about_content=findViewById(R.id.about_content_justified);
        String about_text= getResources().getString(R.string.about_content);

        about_content.setText(Html.fromHtml(about_text));


    }
}