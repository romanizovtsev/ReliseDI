package com.example.coviddi;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;

import java.util.Locale;

import static com.example.coviddi.DataContract.DataDbHelper.LOG_TAG;

public class Settings_Activity extends AppCompatActivity {
    Dialog dialog;//Окно диалога
    private MainActivity view;

    Resources res;
    DisplayMetrics dm;
    String langu;
    android.content.res.Configuration conf;

  /*  public Settings_Activity()
    {
        this.res = getResources();
        // Change locale settings in the app.
        this.dm = res.getDisplayMetrics();
        this.conf = res.getConfiguration();
        this.conf.locale = new Locale("ru");
        this.res.updateConfiguration(conf, dm);
        this.langu=Death();

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        ImageView Back_Settings=(ImageView)findViewById(R.id.back_set);
        ImageView About_us=(ImageView)findViewById(R.id.about_ap);
        ImageView Feedback=(ImageView)findViewById(R.id.Feed_back);
        Button rus=(Button)findViewById(R.id.rus);
        Button en=(Button)findViewById(R.id.en);

        //вспоминает язык



        About_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FragmentManager manager = getSupportFragmentManager();
                    About_App_Fragment myDialogFragment = new About_App_Fragment();
                    myDialogFragment.show(manager, "myDialog");

                }catch (Exception e){
                }
            }
        });

        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FragmentManager manager = getSupportFragmentManager();
                    FeedBack_Fragment myDialogFragment = new FeedBack_Fragment();
                    myDialogFragment.show(manager, "myDialog");

                }catch (Exception e){
                }
            }
        });

        rus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    res = getResources();
                    // Change locale settings in the app.
                    dm = res.getDisplayMetrics();
                    conf = res.getConfiguration();
                    conf.locale = new Locale("ru");
                    res.updateConfiguration(conf, dm);
                    langu="rus";

                    setContentView(R.layout.settings);
                }catch (Exception e){
                }
            }
        });

        en.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Resources res = getResources();
                // Change locale settings in the app.
                DisplayMetrics dm = res.getDisplayMetrics();
                android.content.res.Configuration conf = res.getConfiguration();
                conf.locale = new Locale("en");
                res.updateConfiguration(conf, dm);
                langu="en";

                setContentView(R.layout.settings);

            }
        });


        Window w= getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Back_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Settings_Activity.this,  MainActivity.class);
                    startActivity(intent); finish();
                }catch (Exception e){
                }
            }

        });



    }


    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("langu", langu);

        Log.d(LOG_TAG, "onSaveInstanceState");
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        langu=savedInstanceState.getString("langu");
        Log.d(LOG_TAG, "onRestoreInstanceState");


    }



    @Override
    public void onBackPressed() {
        try{
            Intent intent=new Intent(Settings_Activity.this,  MainActivity.class);
            startActivity(intent);
            finish();
        }catch(Exception e){}
    }

    public String Death() {
        String myString = langu;
        return myString;// or return myOldString directly.
    }

    @Override
    public void onResume() {
        super.onResume();
        langu=Death();
        if (langu=="rus"){
            res = getResources();
            // Change locale settings in the app.
            dm = res.getDisplayMetrics();
            conf = res.getConfiguration();
            conf.locale = new Locale("ru");
            res.updateConfiguration(conf, dm);
            langu="rus";
            setContentView(R.layout.settings);
        }
        else if (langu=="en"){
            Resources res = getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.locale = new Locale("en");
            res.updateConfiguration(conf, dm);
            langu="en";
            setContentView(R.layout.settings);
        }

    }

}


