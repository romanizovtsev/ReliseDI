package com.example.coviddi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coviddi.DataContract.DataDbHelper;
import com.example.coviddi.ViewInterface.MainInterface;
import com.example.coviddi.ViewModels.InfoActivity;
import com.example.coviddi.ViewModels.SettingsActivity;
import com.example.coviddi.ViewModels.TestActivity;
import com.jjoe64.graphview.GraphView;

import static com.example.coviddi.DataContract.DataDbHelper.LOG_TAG;

public class MainActivity extends AppCompatActivity implements MainInterface {
    private String[] AllArray;
    private Presenter presenter;
    private int selected1;
    TextView NumbConf, NumbRecov, NumbDeath, DateText;
    GraphView graphView;
    ImageButton button_settings;
    Button Read_info;
    Button Start_test;
    String keyc = "con";
    String keyl = "langu";
    private Toast backToast;
    Resources res;
    DisplayMetrics dm;
    int langu = 0;
    String activity;
    android.content.res.Configuration conf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);
        DataDbHelper dh = new DataDbHelper(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        button_settings = (ImageButton) findViewById(R.id.Settings_Button);
        Read_info = (Button) findViewById(R.id.onfoBut);
        Start_test = (Button) findViewById(R.id.TestBut);
        NumbConf = findViewById(R.id.NumbConf);
        NumbRecov = findViewById(R.id.NumbRecov);
        NumbDeath = findViewById(R.id.NumbDeath);
        DateText = findViewById(R.id.Date);
        graphView = (GraphView) findViewById(R.id.graphView);
        presenter = new Presenter(this,DateText , graphView);
        final Spinner spinner = findViewById(R.id.spinner);
        AllArray = getResources().getStringArray(R.array.Country);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, AllArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        presenter.setDatesGraph();

        if (savedInstanceState != null) {
            activity = savedInstanceState.getString("act");
            ;
        }

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            langu = (int) arguments.get(keyl);
            String lan = arguments.get(keyl).toString();
            //  activity = arguments.get("act").toString();
            //  langu = arguments.get("langu").toString();
        }



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                selected1 = selectedItemPosition;
                presenter.loadCache(selected1);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //обработка переходов
        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    activity = "set";
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }

        });


        Read_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    activity = "info";
                    Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }

        });

        Start_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    activity = "test";
                    Intent intent = new Intent(MainActivity.this, TestActivity.class);
                    intent.putExtra(keyl, langu);
                    startActivity(intent);
                } catch (Exception e) {
                }
            }

        });
    }
    @Override
    public void ShowNumbConf(String value) {
        NumbConf.setText(value);
    }
    @Override
    public void ShowNumbRecov(String value) {
        NumbRecov.setText(value);
    }
    @Override
    public void ShowNumbDeath(String value) {
        NumbDeath.setText(value);
    }
    @Override
    public String[] getCountry() {
        return getResources()
                .getStringArray(R.array.CountryEn);
    }

    //кпопки для перехода


    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();

    }


}