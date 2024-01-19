package edu.charlotte.zackwilson.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    final String TAG = "demo";
    EditText editTextView;
    RadioGroup radioGroup;
    RadioButton radioButton10;
    RadioButton radioButton15;
    RadioButton radioButton18;
    RadioButton radioButtonCustom;
    TextView discount, finalPrice;
    SeekBar seekBarCustom;
    TextView customPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextView = findViewById(R.id.editTextNumberDecimal);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton10 = findViewById(R.id.radioButton);
        radioButton15 = findViewById(R.id.radioButton2);
        radioButton18 = findViewById(R.id.radioButton3);
        radioButtonCustom = findViewById(R.id.radioButton4);
        discount = findViewById(R.id.textView11);
        finalPrice = findViewById(R.id.textView9);
        seekBarCustom = findViewById(R.id.seekBar);
        seekBarCustom.setMax(50);
        seekBarCustom.setProgress(25);

        customPercent = findViewById(R.id.textView12);

        findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Reset Button Clicked", Toast.LENGTH_SHORT).show();

                editTextView.setText("");
                radioButton10.setChecked(true);
                seekBarCustom.setProgress(25);

                discount.setText("0.00");
                finalPrice.setText("0.00");
            }
        });

        findViewById(R.id.calculateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numStr = editTextView.getText().toString();

                if (numStr.equals("")) {
                    discount.setText("");
                    finalPrice.setText("");
                    Toast.makeText(MainActivity.this, "Enter Item Price", Toast.LENGTH_SHORT).show();
                }

                try {
                    double num = Double.parseDouble(numStr);
                    double salePercent = 0.0;

                    if (radioButton10.isChecked()) {
                        salePercent = 0.10;
                    } else if (radioButton15.isChecked()) {
                        salePercent = 0.15;
                    } else if (radioButton18.isChecked()) {
                        salePercent = 0.18;
                    } else if (radioButtonCustom.isChecked()) {
                        salePercent = (double) seekBarCustom.getProgress() / 100;
                    }

                    finalPrice.setText(String.format("%.2f", num - (num * salePercent)));
                    discount.setText(String.format("%.2f", num - Double.parseDouble(finalPrice.getText().toString())));

                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }



            }
        });

        seekBarCustom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String customPercentText = progress + "%";
                customPercent.setText(customPercentText);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


    }
}