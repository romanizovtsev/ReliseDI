package com.example.coviddi.DataContract;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.coviddi.R;

public class TestResult extends Fragment {
    int result;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.testresult, container, false);
        Button end=rootView.findViewById(R.id.close);
        Button restart=rootView.findViewById(R.id.restart);
        result=getArguments().getInt("score");
        TextView res=rootView.findViewById(R.id.textView8);
        res.setText("Вы набрали "+result+" баллов");
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().beginTransaction().remove(TestResult.this).commit();
                getActivity().finish();
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().beginTransaction().remove(TestResult.this).commit();
            }
        });
        return rootView;
    }
}
