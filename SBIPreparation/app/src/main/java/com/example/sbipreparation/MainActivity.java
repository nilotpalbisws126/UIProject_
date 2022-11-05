package com.example.sbipreparation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button tutorial;
    Button selfTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tutorial= (Button)findViewById(R.id.btn_tutorial);
        selfTest=(Button)findViewById(R.id.btn_test);

        tutorial.setOnClickListener(this);
        selfTest.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_tutorial:
                Intent intent = new Intent(this,Tutorial.class);
                startActivity(intent);
                break;
//            case R.id.btn_test:
//                break;
            default:
                break;
        }

    }
}