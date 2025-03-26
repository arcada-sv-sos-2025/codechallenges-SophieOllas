package fi.arcada.codechallenge;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Double> myDoubleList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDoubleList.add(1.1);
        myDoubleList.add(2.2);
        myDoubleList.add(3.3);
        myDoubleList.add(4.4);

        TextView myTextView;
        myTextView = findViewById(R.id.hello);
        myTextView.setText("Min app funkar!");

        double mean = Statistics.calcMean(myDoubleList); // Call the static method
        TextView otherTextView = findViewById(R.id.hellotwo);
        otherTextView.setText("Mean: " + mean);



        Button myButton;
        myButton = findViewById(R.id.calculateButton);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }

        private void calculate() {
                double sum = 1.2 + 2.4;
                myTextView.setText("Result: " + sum);
        }
        });

    }
}