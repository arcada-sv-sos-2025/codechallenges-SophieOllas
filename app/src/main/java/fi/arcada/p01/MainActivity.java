package fi.arcada.p01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fi.arcada.codechallenge.R;

public class MainActivity extends AppCompatActivity {

    //Vi vill ha vår datamodel och en RecyclerView adapter
    private List<DataModel> dataList = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Hitta våra saker!
        EditText inputField1 = findViewById(R.id.inputField1);
        EditText inputField2 = findViewById(R.id.inputField2);
        Button addButton = findViewById(R.id.addButton);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);

        // Vi trycker på knappen!
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = inputField1.getText().toString();
                String value2 = inputField2.getText().toString();
                // Error handlers!
                if (!value1.isEmpty() && !value2.isEmpty()) {
                    // Ta värdena och sätt dem i vår dataList
                    dataList.add(new DataModel(value1, value2));
                    // berättar till vår adapter att det har kommit en update
                    // Det finns även mer specifika metoder (item update, item add... )
                    adapter.notifyDataSetChanged();
                    inputField1.setText("");
                    inputField2.setText("");

                    //CalcMean
                    double meanValue = Statistics.calcMean(dataList);
                    double min = Statistics.calcMin(dataList);
                    double max = Statistics.calcMax(dataList);
                    double average = Statistics.calcAverage(dataList);
                    double median = Statistics.calcMedian(dataList);
                    List<Double> modes = Statistics.calcMode(dataList);
                    double stdev = Statistics.calcStdev(dataList);
                    double lq = Statistics.calcLQ(dataList);
                    double uq = Statistics.calcUQ(dataList);
                    double qr = Statistics.calcIQR(dataList);


                    //LogCat!
                    TextView meanTextView = findViewById(R.id.meanTextView);
                    meanTextView.setText("Mean: " + meanValue);

                    TextView minTextView = findViewById(R.id.minTextView);
                    minTextView.setText("Min: " + min);

                    TextView maxTextView = findViewById(R.id.maxTextView);
                    maxTextView.setText("Max: " + max);

                    TextView averageTextView = findViewById(R.id.averageTextView);
                    averageTextView.setText("Average: " + average);

                    TextView medianTextView = findViewById(R.id.medianTextView);
                    medianTextView.setText("Median: " + median);

                    TextView modeTextView = findViewById(R.id.modeTextView);
                    modeTextView.setText("Mode: " + modes);

                    TextView stdevTextView = findViewById(R.id.stdevTextView);
                    stdevTextView.setText("Stdev: " + stdev);

                    TextView lqTextView = findViewById(R.id.lqTextView);
                    lqTextView.setText("LQ: " + lq);

                    TextView uqTextView = findViewById(R.id.uqTextView);
                    uqTextView.setText("UQ: " + uq);

                    TextView qrTextView = findViewById(R.id.qrTextView);
                    uqTextView.setText("QR: " + qr);


                }
            }
        });
    }
}
