package com.example.piginsemination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.piginsemination.adapter.CategoryAdapter;
import com.example.piginsemination.model.Category;
import com.example.piginsemination.retrofit.ApiInterface;
import com.example.piginsemination.retrofit.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView categoryRecyclerView;
    CategoryAdapter categoryAdapter;
    ImageView scroll ;


    ApiInterface apiInterface;

//    NavigationView navigationView;
//    ActionBarDrawerToggle toggle;
//   DrawerLayout drawerLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface= RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        categoryRecyclerView = findViewById(R.id.items_recycler);
        Call<List<Category>> call = apiInterface.getAllCategory();
        scroll= (ImageView) findViewById(R.id.scroll);


        final DrawerLayout drawer = findViewById(R.id.drawer);
        ImageView menuIcon = (ImageView) findViewById(R.id.toggle);
        menuIcon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//
//
//        navigationView=(NavigationView)findViewById(R.id.nav_menu);
//        drawerLayout= (DrawerLayout)findViewById(R.id.drawer);
//
//        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
//
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.menu_about:
//                        Toast.makeText(getApplicationContext(),"about",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case R.id.menu_home:
//                        Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case R.id.menu_contact:
//                        Toast.makeText(getApplicationContext(),"contact",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                }
//
//                return true;
//            }
//        });


        Glide.with(this).load("https://media.tenor.com/images/223885406da0fd34deaecb476f8f0160/tenor.gif").into(scroll);

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categoryList = response.body();
                getAllCategory(categoryList);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Please check your internet connection",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,oops.class);
                startActivity(i);

            }
        });




    }
    private void getAllCategory(List<Category> categoryList){
        RecyclerView.LayoutManager layoutManager= new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter=new CategoryAdapter(this,categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        categoryRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {

                    scroll.setVisibility(View.INVISIBLE);
                }
                else {

                    scroll.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Context context=getApplicationContext();
        if(id==R.id.menu_home){
           // Intent i = new Intent(context, MainActivity.class);
          //  context.startActivity(i);
          //  Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.menu_about){
            Intent i1 = new Intent(MainActivity.this, AboutActivity.class);
            this.startActivity(i1);
          //  Toast.makeText(getApplicationContext(),"about",Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.menu_contact){
            Intent i2 = new Intent(MainActivity.this, Contact.class);
            this.startActivity(i2);
           // Toast.makeText(getApplicationContext(),"contact",Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.menu_acknowledgement){
            Intent i3 = new Intent(MainActivity.this, Acknowledgement.class);
            this.startActivity(i3);
            // Toast.makeText(getApplicationContext(),"contact",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        
        return true;
    }
}