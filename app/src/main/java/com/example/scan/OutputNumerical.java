package com.example.scan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewDebug;
import android.widget.TextView;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
public class OutputNumerical extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_numerical);

        Bundle b = getIntent().getExtras();
        String answer_string = b.getString("formula_type");

        Double final_output = 0.0;
        final TextView answer = (TextView) findViewById(R.id.answer);
        String name = "";
        switch(answer_string) {

            case "mean":
                ArrayList<Integer> output = b.getIntegerArrayList("output");
                final_output = calculateMean(output);
                answer.setText(final_output.toString());
                name = "Mean";
                break;

            case "median":
                output = b.getIntegerArrayList("output");
                final_output = calculateMedian(output);
                answer.setText(final_output.toString());
                name = "Median";
                break;

            case "mode":
                output = b.getIntegerArrayList("output");
                Integer[] array = output.toArray(new Integer[0]);
                final_output = calculateMode(array, output.size());
                answer.setText(final_output.toString());
                name = "Mode";
                break;

            case "standard deviation":
                output = b.getIntegerArrayList("output");
                array = output.toArray(new Integer[0]);
                final_output = calculateSD(array, output.size());
                answer.setText(final_output.toString());
                name = "Standard Deviation";
                break;

            case "variance":
                output = b.getIntegerArrayList("output");
                array = output.toArray(new Integer[0]);
                final_output = calculateVariance(array, output.size());
                answer.setText(final_output.toString());
                name = "Variance";
                break;

            case "coefficient of variance":
                output = b.getIntegerArrayList("output");
                array = output.toArray(new Integer[0]);
                final_output = calculateCoefficientOfVariation(array, output.size());
                answer.setText(final_output.toString());
                name = "Coefficient Of Variance";
                break;

            case "geometric mean":
                output = b.getIntegerArrayList("output");
                array = output.toArray(new Integer[0]);
                final_output = calculateGM(array, output.size());
                answer.setText(final_output.toString());
                name = "Geometric Mean";
                break;

            case "percentile":
                output = b.getIntegerArrayList("output");
                array = output.toArray(new Integer[0]);
                ArrayList<Integer> final_list = new ArrayList<Integer>();
                final_list = calculatePercentile(array, output.size());
                for(int i = 0; i<output.size(); i++){
                    answer.append("Input" + (i+1) + ":" + final_list.get(i) +"\n");
                }
                name = "Percentile";
                break;

            case "prob_classic":
                ArrayList<Integer> outputX = b.getIntegerArrayList("output_x");
                ArrayList<Integer> outputY = b.getIntegerArrayList("output_y");
                final_output = calculateProbClassic(outputX, outputY);
                answer.setText(final_output.toString());
                name = "Probability Classic";
                break;

            case "prob_conditional":
                double A_int_B = b.getDouble("prob_a_int_b");
                double A = b.getDouble("prob_a");
                double ans = A_int_B*A;
                final_output = ans;
                answer.setText(final_output.toString());
                name = "Probability Conditional";
                break;

            case "prob_addition_rule":
                A = b.getDouble("prob_a");
                double B = b.getDouble("prob_b");
                A_int_B = b.getDouble("prob_a_int_b");
                final_output = Double.valueOf(A + B + A_int_B);
                answer.setText(final_output.toString());
                name = "Probability Addition Rule ";
                break;

            case "prob_bayes":
                A = b.getDouble("prob_a");
                B = b.getDouble("prob_b");
                double A_or_B = b.getDouble("prob_a_or_b");
                final_output = (A_or_B*A)/B;
                answer.setText(final_output.toString());
                name = "Bayes Probability";
                break;

            case "prob_binomial":
                double numOfTrials = b.getDouble("num_of_trials");
                double probOfSuccesses = b.getDouble("prob_of_success");
                double numOfSuccesses = b.getDouble("num_of_successes");
                final_output = (double)calculateBinomialProbability(numOfTrials,probOfSuccesses, numOfSuccesses);
                answer.setText(final_output.toString());
                name = "Probability Bionomial";
                break;

            case "dist_two_point":
                Integer x1 = b.getInt("x1");
                Integer y1 = b.getInt("y1");
                Integer x2 = b.getInt("x2");
                Integer y2 = b.getInt("y2");
                final_output = (double) sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) * 1.0);
                answer.setText(final_output.toString());
                name = "Distance Two Point";
                break;
            case "correlation":

                ArrayList<Integer> X1 = b.getIntegerArrayList("inputArrayX1");
                ArrayList<Integer> Y1 = b.getIntegerArrayList("inputArrayY1");
                Integer[] arrayX = X1.toArray(new Integer[0]);
                Integer[] arrayY = Y1.toArray(new Integer[0]);
                final_output = calculateCorrelationCoefficient(arrayX, arrayY, X1.size());
                answer.setText(final_output.toString());
                name = "Correlation";
                break;

            case "kendall":
                X1 = b.getIntegerArrayList("inputArrayX1");
                Y1 = b.getIntegerArrayList("inputArrayY1");
                arrayX = X1.toArray(new Integer[0]);
                arrayY = Y1.toArray(new Integer[0]);
                final_output = (double)calculateKendall(arrayX, arrayY);
                answer.setText(final_output.toString());
                name = "Kendall";

                break;

            case "NormalDistri":
                Integer x = b.getInt("x");
                Integer u = b.getInt("u");
                double s = b.getInt("s");
                final_output  = (x-u)/s;
                answer.setText(final_output.toString());
                name = "Normal Distribution";
                break;
            case "TTestOne":
                Integer m = b.getInt("m");
                u = b.getInt("u");
                s = b.getInt("s");
                Integer n = b.getInt("n");
                final_output = (m-u)/(s/sqrt(n));
                answer.setText(final_output.toString());
                name = "T Test One";
                break;

            case "ChiSquare":
                X1 = b.getIntegerArrayList("inputArray");
                n = X1.size();
                array = X1.toArray(new Integer[0]);
                s = calculateSD(array, X1.size());
                double mean = calculateMean(X1);
                final_output = ((n-1)*pow(s,2))/pow(mean,2);
                answer.setText(final_output.toString());
                name = "Chi Square";
                break;
        }




        //(TextView)findViewById(R.id.answer).setText(ans);
    }

    private double calculateMean(ArrayList <Integer> elements) {
        if (elements == null || elements.isEmpty()) {
            return 0;
        }

        double sum = 0;
        for (Integer mark : elements) {
            sum += mark;
        }

        return sum / elements.size();
    }

    public double calculateMedian(ArrayList <Integer> sets){
        Collections.sort(sets);

        double middle = sets.size()/2;
        if (sets.size()%2 == 1) {
            middle = ((sets.size() + 1) / 2);
        } else {
            middle = ((sets.size() / 2) + (sets.size() / 2 + 1))/2;
        }
        return middle;
    }

    public double calculateMode(Integer[] a, int n) {
        int maxValue = 0, maxCount = 0, i, j;

        for (i = 0; i < n; ++i) {
            int count = 0;
            for (j = 0; j < n; ++j) {
                if (a[j] == a[i])
                    ++count;
            }

            if (count > maxCount) {
                maxCount = count;
                maxValue = a[i];
            }
        }
        return maxValue;
    }

    static double calculateVariance(Integer a[],  int n)
    {
        double sum = 0;

        for (int i = 0; i < n; i++)
            sum += a[i];
        double mean = (double)sum /
                (double)n;
        double sqDiff = 0;
        for (int i = 0; i < n; i++)
            sqDiff += (a[i] - mean) *
                    (a[i] - mean);

        return (double)sqDiff / n;
    }

    public double calculateSD(Integer numArray[], int n) {
        return sqrt(calculateVariance(numArray, n));
    }

    public double calculateCoefficientOfVariation(Integer arr[], int n)
    {
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(arr));
        return (calculateSD(arr, n) / calculateMean(arrayList));
    }
    public double calculateGM(Integer[] arr, int n)
    {

        float product = 1;

        for (int i = 0; i < n; i++)
            product = product * arr[i];
        float gm = (float)Math.pow(product, (float)1 / n);
        return gm;
    }

    public ArrayList<Integer> calculatePercentile(Integer[] arr, int n)
    {
        int i, count, percent;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (i = 0; i < n; i++)
        {
            count = 0;
            for (int j = 0; j < n; j++)
            {
                if (arr[i] > arr[j])
                {
                    count++;
                }
            }
            percent = (count * 100) / (n - 1);
            numbers.add(percent);
        }
        return  numbers;
    }

    public double calculateProbClassic(ArrayList<Integer> outputX, ArrayList<Integer> outputY){

        double ans = 1;
        double temp1, temp2, temp3;
        for(int i = 0; i<outputX.size(); i++){
            temp1 = outputX.get(i);
            temp2 = outputY.get(i);
            temp3 = temp1/temp2;
            ans = ans*temp3;
        }
        return ans;
    }

    static int calculateNCR(double n, double r)
    {
        if (r > n / 2)
            r = n - r;

        int answer = 1;
        for (int i = 1; i <= r; i++) {
            answer *= (n - r + i);
            answer /= i;
        }

        return answer;
    }

    float calculateBinomialProbability(double n, double k, double p)
    {
        return calculateNCR(n, k) * (float)Math.pow(p, k) *
                (float)Math.pow(1 - p, n - k);
    }

    double calculateCorrelationCoefficient(Integer X[], Integer Y[], int n)
    {

        int sum_X = 0, sum_Y = 0, sum_XY = 0;
        int squareSum_X = 0, squareSum_Y = 0;

        for (int i = 0; i < n; i++)
        {
            // sum of elements of array X.
            sum_X = sum_X + X[i];

            // sum of elements of array Y.
            sum_Y = sum_Y + Y[i];

            // sum of X[i] * Y[i].
            sum_XY = sum_XY + X[i] * Y[i];

            // sum of square of array elements.
            squareSum_X = squareSum_X + X[i] * X[i];
            squareSum_Y = squareSum_Y + Y[i] * Y[i];
        }

        // use formula for calculating correlation coefficient.
        double corr = ((float)(n * sum_XY - sum_X * sum_Y)
                        / sqrt((n * squareSum_X - sum_X * sum_X)
                        * (n * squareSum_Y - sum_Y * sum_Y)));

        return corr;
    }

    public static long calculateKendall(Integer[] a, Integer[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Array dimensions disagree");
        }
        int n = a.length;

        int[] ainv = new int[n];
        for (int i = 0; i < n; i++)
            ainv[a[i]] = i;

        Integer[] bnew = new Integer[n];
        for (int i = 0; i < n; i++)
            bnew[i] = ainv[b[i]];

        return (long) getInvCount(bnew);
    }

    static float getInvCount(Integer []n)
    {
        int inv_count = 0;
        for (int i = 0; i < n.length; i++)
            for (int j = i + 1; j < n.length; j++)
                if (n[i] > n[j])
                    inv_count++;
        return inv_count;
    }

}
