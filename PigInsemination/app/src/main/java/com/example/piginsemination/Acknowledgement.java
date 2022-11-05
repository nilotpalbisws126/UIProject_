package com.example.piginsemination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class Acknowledgement extends AppCompatActivity {
    ImageView optionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acknowledgement);

        ImageView backbutton = findViewById(R.id.acknowledgement_back);
        backbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        optionButton=(ImageView)findViewById(R.id.menu_acknowledgement);
        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu= new PopupMenu(Acknowledgement.this,optionButton);
                popupMenu.getMenuInflater().inflate(R.menu.menu_no_icon,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Toast.makeText(DetailPage.this,item.getTitle(),Toast.LENGTH_LONG).show();
                        if(item.getTitle().equals("Home")){
                            Intent i = new Intent(Acknowledgement.this, MainActivity.class);
                            startActivity(i);
                        }
                        else if(item.getTitle().equals("About App")){
                            Intent i = new Intent(Acknowledgement.this, AboutActivity.class);
                            startActivity(i);
                        }
                        else if(item.getTitle().equals("Contact and Developers")){
                            Intent i = new Intent(Acknowledgement.this, Contact.class);
                            startActivity(i);
                        }
                        else if(item.getTitle().equals("Acknowledgement")){
                            Intent i = new Intent(Acknowledgement.this, Acknowledgement.class);
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

    }
}