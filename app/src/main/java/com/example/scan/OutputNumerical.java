package com.example.scan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewDebug;
import android.widget.TextView;

import java.util.ArrayList;

public class OutputNumerical extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_numerical);

        Bundle b = getIntent().getExtras();
        String answer_string = "DummyText: 0";
        if(b != null)
            answer_string = b.getString("answer_string");
        answer_string += ": ";

        Double final_output = 0.0;
        ArrayList<Integer> output = b.getIntegerArrayList("output");  //output array is stored here
        switch(answer_string) {
            case "mean":
                final_output = 1.0;//set formula here
                break;
            case "median":
                final_output = 2.0;//set formula here
                break;
            case "mode":
                final_output = 3.0;//set formula here
                break;
            case "standard deviation":
                final_output = 4.0;//set formula here
                break;
            case "variance":
                final_output = 5.0;//set formula here
                break;
            case "coefficient of variance":
                final_output = 6.0;//set formula here
                break;
            case "geometric mean":
                final_output = 7.0;//set formula here
                break;
            case "percentile":
                final_output = 8.0;//set formula here
                break;
        }
        answer_string += final_output.toString();

        final TextView answer = (TextView) findViewById(R.id.answer);
        answer.setText(answer_string);
        //(TextView)findViewById(R.id.answer).setText(ans);
    }
}