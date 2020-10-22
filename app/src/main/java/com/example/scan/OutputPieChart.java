package com.example.scan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class OutputPieChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_pie_chart);

        Bundle b = getIntent().getExtras();
        String array_string = b.getString("formula_type");

        PieChart pieChart = findViewById(R.id.pieChart);

        switch (array_string) {
            case "pie_chart":
                ArrayList<Integer> output = b.getIntegerArrayList("output");
                makeChart(pieChart, output);
                for (int i = 0; i < output.size(); i++)
                    array_string += " " + output.get(i).toString();
                break;
            case "chi_square_distribution":
                output = b.getIntegerArrayList("output");
                makeChart(pieChart, output);
                for (int i = 0; i < output.size(); i++)
                    array_string += " " + output.get(i).toString();
                break;
            case "dist_correlation":
                ArrayList<Integer> outputX1 = b.getIntegerArrayList("outputX1");
                ArrayList<Integer> outputY1 = b.getIntegerArrayList("outputY1");
                ArrayList<Integer> outputX2 = b.getIntegerArrayList("outputX2");
                ArrayList<Integer> outputY2 = b.getIntegerArrayList("outputY2");
                //makeChart(pieChart, SET ARRAY WHICH IS TO BE PRINTED ON PIE CHART AS OUTPUT HERE);
                //PASS ARRAY WHICH IS TO BE PRINTED ON PIE CHART AS A STRING INTO array_string, for display below the pie chart
                break;
            case "dist_kendall":
                outputX1 = b.getIntegerArrayList("outputX1");
                outputY1 = b.getIntegerArrayList("outputY1");
                outputX2 = b.getIntegerArrayList("outputX2");
                outputY2 = b.getIntegerArrayList("outputY2");
                //makeChart(pieChart, SET ARRAY WHICH IS TO BE PRINTED ON PIE CHART AS OUTPUT HERE);
                //PASS ARRAY WHICH IS TO BE PRINTED ON PIE CHART AS A STRING INTO array_string, for display below the pie chart
                break;
        }

        final TextView answer = (TextView) findViewById(R.id.answer);
        answer.setText(array_string);//FINAL ARRAY IS SHOWN HERE

    }

    static void makeChart(PieChart pieChart, ArrayList<Integer> output) {
        Integer total = 0;


        ArrayList<PieEntry> pie_list = new ArrayList<>();
        for (int i = 0; i < output.size(); i++) {
            pie_list.add(new PieEntry(output.get(i)));
            total += output.get(i);
        }

        PieDataSet pieDataSet = new PieDataSet(pie_list, "");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Total:" + total.toString());
        pieChart.animateY(1000);

    }
}