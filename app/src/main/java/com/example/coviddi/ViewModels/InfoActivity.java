package com.example.coviddi.ViewModels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coviddi.MainActivity;
import com.example.coviddi.R;

public class InfoActivity extends AppCompatActivity {
    ImageView Back_Info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.info_activity);
        Back_Info=(ImageView)findViewById(R.id.info_back);
        Back_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(InfoActivity.this,  MainActivity.class);
                    startActivity(intent); finish();
                }catch (Exception e){
                }
            }

        });
    }

    @Override
    public void onBackPressed() {
        try{
            Intent intent=new Intent(InfoActivity.this,  MainActivity.class);
            startActivity(intent);
            finish();
        }catch(Exception e){}
    }
}
